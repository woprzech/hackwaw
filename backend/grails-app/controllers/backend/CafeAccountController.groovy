package backend

import exception.ExceptionHandler

class CafeAccountController implements ExceptionHandler {
    CafeAccountService cafeAccountService

    def createAccount() {
        cafeAccountService.createAccount(params.login, params.password, params.cafeId)
        render "OK"
    }

    def login() {
        cafeAccountService.login(session, params.login, params.password)
        session["loggedAccount"] = params.login
        render "OK"
    }

    def logout() {
        session.invalidate()
        render "OK"
    }
}
