package backend

import grails.transaction.Transactional

@Transactional
class AdminAccountService {

    // TODO przy tworzeniu cafe, tworzyc od razu konto z loginem = cafeId
    def createCafe(def token, def cafeName, double posX, double posY) {
        checkIfAdmin(token)
        def cafe = new Cafe(name: cafeName, location: new Location(x: posX, y: posY))
        if (!cafe.save(flush: true))
            throw new Exception("Nie udalo sie zapisac kawiarni")
    }

    // TODO poprawic
    def removeCafe(def token, def cafeId) {
        checkIfAdmin(token)
        def cafe = getCafe(cafeId)
        def account = CafeAccount.findByCafe(cafe)
        if (!account.delete(flush: true) || !cafe.delete(flush: true))
            throw new Exception("Nie udalo sie usunac kawiarni")
    }

    def updateCafe(def token, def cafeId, def cafeName, double posX, double posY) {
        checkIfAdmin(token)
        def cafe = getCafe(cafeId)
        cafe.name = cafeName
        cafe.location.x = posX
        cafe.location.y = posY
        if (!cafe.save(flush: true))
            throw new Exception("Nie udalo sie aktualizowac kawiarni")
    }

    def getCafe(def cafeId) {
        def cafe = Cafe.findById(cafeId)
        if (cafe == null)
            throw new Exception("Nie ma takiej kawiarni")
        return cafe
    }

    def setAccountStatus(def token, def cafeAccountId, AccountStatus status) {
        checkIfAdmin(token)
        def cafeAccount = CafeAccount.findByCafe(cafeAccountId)
        if (cafeAccount == null)
            throw new Exception("Nie ma takiego konta")
        cafeAccount.setAccountStatus(status)
        if (status == AccountStatus.PREMIUM)
            cafeAccount.cafe.premium = true
    }

    def checkIfAdmin(def currentToken) {
        def token = Token.findByToken(currentToken)
        if (token == null)
            throw new Exception("Brak takiego tokenu")
        if (token.account == null)
            throw new Exception("Brak konta dla takiego tokenu")
        if (token.account.class != AdminAccount.class)
            throw new Exception("Brak uprawnien")
    }
}
