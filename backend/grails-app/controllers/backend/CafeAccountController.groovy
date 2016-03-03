package backend

import exception.ExceptionHandler

class CafeAccountController implements ExceptionHandler {
    CafeAccountService cafeAccountService

    def createAccount() {
        cafeAccountService.createAccount(request.JSON.login, request.JSON.password, request.JSON.cafeId)
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

    def changeMenuFlag() {
        cafeAccountService.changeMenuFlag(request.JSON.token, request.JSON.status)
        render "OK"
    }
}
