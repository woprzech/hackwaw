package backend

import exception.ExceptionHandler

class CafeAccountController implements ExceptionHandler {
    CafeAccountService cafeAccountService

    def createAccount() {
        cafeAccountService.createAccount(params.login, params.password, params.cafeId)
        render "OK"
    }

    def login() {
        cafeAccountService.login(session["loggedAccount"], params.login, params.password)
        session["loggedAccount"] = params.login
        render "OK"
    }

    def logout() {
        cafeAccountService.logout(session["loggedAccount"], params.token)
        session.invalidate()
        render "OK"
    }
}
