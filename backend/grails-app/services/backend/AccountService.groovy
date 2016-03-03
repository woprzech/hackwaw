package backend

import grails.transaction.Transactional

@Transactional
class AccountService {

    def login(def userName, def password, def token) {
        def account = Account.findByLogin(userName)
        def foundToken = Token.findByToken(token)
        if (foundToken == null) {
            if (account != null) {
                if (account.class == CafeAccount.class
                        && ((CafeAccount) account).accountStatus == AccountStatus.DEACTIVATED)
                    throw new Exception("Konto nieaktywne")

                if (account.password == password) {
                    throw new Exception("Zle haslo")
                } else {
                    def str = generator((('A'..'Z') + ('0'..'9')).join(), 128)
                    def newToken = new Token(token: str)
                    account.addToTokens(newToken)
                    return newToken
                }
            } else
                throw new Exception("Nie znaleziono uzytkownika z takim loginem")
        } else
            return foundToken
    }

    def logout(def currentToken) {
        def token = Token.findByToken(currentToken)
        if (token == null)
            throw new Exception("Nie znaleziono takiego tokenu")

        def account = getAccountByToken(currentToken)
        account.removeFromTokens(token)
        token.delete(flush: true)
    }

    def generator = { String alphabet, int n ->
        new Random().with {
            (1..n).collect { alphabet[nextInt(alphabet.length())] }.join()
        }
    }

    def getAccountByToken(def currentToken) {
        def token = Token.findByToken(currentToken)
        if (token == null)
            throw new Exception("Brak takiego tokenu")
        return token.account
    }
}
