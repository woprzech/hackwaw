package backend

import grails.transaction.Transactional

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

    def login(def sessionUser, def userName, def password) {
        if (userName.equals(sessionUser))
            throw new Exception("Uzytkownik juz zalogowany")

        def account = CafeAccount.findByLogin(userName)
        if (account != null) {
            if (!account.password.equals(password)) {
                throw new Exception("Zle haslo")
            } else {
                def str = generator((('A'..'Z') + ('0'..'9')).join(), 128)
                def token = new Token(token: str)
                account.addToTokens(token)
            }
        } else
            throw new Exception("Nie znaleziono uzytkownika z takim loginem")
    }

    def logout(def userName, def currentToken) {
        def account = CafeAccount.findByLogin(userName)
        if (account != null) {
            for (int i = 0; i < account.tokens.size(); i++) {
                if (account.tokens[i].equals(currentToken)) {
                    account.removeFromTokens(account.tokens[i])
                    break;
                }
            }
        } else
            throw new Exception("Nie znaleziono uzytkownika z takim loginem")
    }

    def generator = { String alphabet, int n ->
        new Random().with {
            (1..n).collect { alphabet[nextInt(alphabet.length())] }.join()
        }
    }
}
