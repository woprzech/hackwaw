package backend

import exception.ExceptionHandler
import grails.converters.JSON

class CafeAccountController implements ExceptionHandler {
    CafeAccountService cafeAccountService

    def createAccount() {
        cafeAccountService.createAccount(params.login, params.password, params.cafeId)
        render "OK"
    }

    def login() {
        def token = cafeAccountService.login(params.login, params.password)
        session["loggedAccount"] = params.login
        render token as JSON
    }

    def logout() {
        cafeAccountService.logout(params.token)
        session.invalidate()
        render "OK"
    }
}
