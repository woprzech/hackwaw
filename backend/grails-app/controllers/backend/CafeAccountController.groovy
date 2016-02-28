package backend

import exception.ExceptionHandler
import grails.converters.JSON

class CafeAccountController implements ExceptionHandler {
    CafeAccountService cafeAccountService

    def createAccount() {
        cafeAccountService.createAccount(request.JSON.login, request.JSON.password, request.JSON.cafeId)
        render "OK"
    }

    def login() {
        def token = cafeAccountService.login(request.JSON.login, request.JSON.password)
        render token as JSON
    }

    def logout() {
        cafeAccountService.logout(request.JSON.token)
        render "OK"
    }
}
