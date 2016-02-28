package backend

import exception.ExceptionHandler

class MenuController implements ExceptionHandler {
    MenuService menuService

    def addProduct() {
        menuService.addProduct(request.JSON.token, request.JSON.name, request.JSON.description, request.JSON.price, request.JSON.category)
        render "OK"
    }

    def updateDescription() {
        menuService.updateDescription(request.JSON.token, request.JSON.productId, request.JSON.desciption)
        render "OK"
    }

    def updatePrice() {
        menuService.updatePrice(request.JSON.token, request.JSON.productId, request.JSON.price)
        render "OK"
    }

    def removeProduct() {
        menuService.removeProduct(request.JSON.token, request.JSON.productId)
        render "OK"
    }
}
