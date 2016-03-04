package backend

import grails.transaction.Transactional


import javax.naming.AuthenticationException

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
                //TODO powrównanie przez bcrypt
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

        def account = getAuthorizedUser(currentToken)
        account.removeFromTokens(token)
        token.delete(flush: true)
    }

    def generator = { String alphabet, int n ->
        new Random().with {
            (1..n).collect { alphabet[nextInt(alphabet.length())] }.join()
        }
    }
    /**
     * Check is user is authorized
     * @param currentToken
     * @return account
     * @throws AuthenticationException - if user is not authorized
     */
    def getAuthorizedUser = { def currentToken ->
        def token = Token.findByToken(currentToken)
        if (token == null)
            throw new AuthenticationException("Brak takiego tokenu")
        return token.account
    }

    def changePassword(def token, def oldPassword, def newPassword) {
        def user = getAuthorizedUser(token)
        //TODO porównanie przez bcrypt
        if (user.password != oldPassword) {
            throw new AuthenticationException("Podane obecne hasło jest nieprawidłowe")
        }
        validPassword(newPassword);
        //TODO zaszyfrować hasło
        user.password = newPassword
        user.save()
    }

    def validPassword = { def password ->
        if (password.length() < 5) {
            throw new Exception("Nowe hasło musi mieć długość większą niż 5 znaków")
        }
        //TODO dopisać walidację
    }
}
