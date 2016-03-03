package backend

import exception.ExceptionHandler
import grails.converters.JSON

class AccountController implements ExceptionHandler {
    AccountService accountService

    def login() {
        def token = accountService.login(request.JSON.login, request.JSON.password, request.JSON.token)
        render token as JSON
    }

    def logout() {
        accountService.logout(request.JSON.token)
        render "OK"
    }

    def changePassword() {
        // TODO
    }

    def changeLogin() {
        // TODO
    }
}
