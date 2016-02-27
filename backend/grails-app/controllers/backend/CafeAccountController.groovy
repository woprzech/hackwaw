package backend

import exception.ExceptionHandler

class CafeAccountController implements ExceptionHandler {
    CafeAccountService cafeAccountService

    def createAccount() {
        cafeAccountService.createAccount(params.login, params.password, params.cafeId)
        render "OK"
    }

    def login() {
        cafeAccountService.login(params.login, params.password)
        session["loggedAccount"] = params.login
        render "OK"
    }

    def logout() {
        cafeAccountService.logout(params.token)
        session.invalidate()
        render "OK"
    }
}
