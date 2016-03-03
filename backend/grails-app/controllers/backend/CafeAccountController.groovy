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
        def token = cafeAccountService.login(request.JSON.login, request.JSON.password, request.JSON.token)
        render token as JSON
    }

    def logout() {
        cafeAccountService.logout(request.JSON.token)
        render "OK"
    }

    def removeProduct() {
        cafeAccountService.removeProduct(request.JSON.token, request.JSON.productId)
        render "OK"
    }

    def updateProduct() {
        cafeAccountService.updateProduct(request.JSON.token, request.JSON.productId, request.JSON.name, request.JSON.description, request.JSON.price, request.JSON.categoryId)
        render "OK"
    }
}
