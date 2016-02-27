package backend

import grails.transaction.Transactional

import java.security.InvalidParameterException

@Transactional
class CafeAccountService {

    def createAccount(def login, def password, def cafeId) {
        def cafe = Cafe.findById(Long.valueOf(cafeId))

        if (CafeAccount.findByCafe(cafe) == null) {
            def newAccount = new CafeAccount(login: login, password: password, cafe: cafe)
            if (!newAccount.save()) {
                throw new Exception("Nie udalo sie stworzyc konta")
            }
        } else
            throw new Exception("Istnieje konto dla tej restauracji")
    }

    def login(def session, def userName, def password) {
        def account = CafeAccount.findByLogin(userName)
        if (account != null) {
            if (!account.password.equals(password)) {
                throw new InvalidParameterException("Zle haslo")
            }
        } else
            throw new ClassNotFoundException("Nie znaleziono uzytkownika z takim loginem")
    }
}
