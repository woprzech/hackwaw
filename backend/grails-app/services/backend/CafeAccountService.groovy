package backend

import grails.transaction.Transactional

@Transactional
class CafeAccountService {

    def createAccount(def login, def password, def cafeId) {
        def cafe = Cafe.findById(cafeId)

        if (CafeAccount.findByCafe(cafe) == null) {
            def newAccount = new CafeAccount(login: login, password: password, cafe: cafe)
            if (!newAccount.save()) {
                throw new Exception("Nie udalo sie stworzyc konta")
            }
        } else
            throw new Exception("Istnieje konto dla tej restauracji")
    }

    def login(def userName, def password, def token) {
        def account = CafeAccount.findByLogin(userName)
        if (token != null) {
            if (account != null) {
                if (!account.password == password) {
                    throw new Exception("Zle haslo")
                } else {
                    def str = generator((('A'..'Z') + ('0'..'9')).join(), 128)
                    def newToken = new Token(token: str)
                    account.addToTokens(newToken)
                    return newToken
                }
            } else
                throw new Exception("Nie znaleziono uzytkownika z takim loginem")
        } else {
            def foundToken = Token.findByToken(token)
            if (foundToken == null)
                throw new Exception("Nie znaleziono takiego tokenu")
            else
                return foundToken
        }
    }

    def logout(def currentToken) {
        def account = getUserByToken(currentToken)
        if (account != null) {
            for (int i = 0; i < account.tokens.size(); i++) {
                if (account.tokens[i] == currentToken) {
                    def token = account.tokens[i]
                    account.removeFromTokens(token)
                    token.delete()
                    break;
                }
            }
        } else
            throw new Exception("Nie znaleziono uzytkownika z takim loginem")
    }

    def getUserByToken(def currentToken) {
        def token = Token.findByToken(currentToken)
        def account = token.cafeAccount
        return account
    }

    def generator = { String alphabet, int n ->
        new Random().with {
            (1..n).collect { alphabet[nextInt(alphabet.length())] }.join()
        }
    }

    def getCafeAccountByCafeId(def cafeId) {
        def cafe = Cafe.findById(cafeId)
        if (cafe == null) {
            throw new Exception("Cafe o takim id nie istnieje")
        }
        def cafeAccount = CafeAccount.findByCafe(cafe)
        if (cafeAccount == null) {
            throw new Exception("Wybrana kawiarnia nie ma możliwości składania zamówień")
        }
        return cafeAccount

    }
}
