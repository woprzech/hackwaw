package backend

import exception.ExceptionHandler

class MenuController implements ExceptionHandler {
    MenuService menuService

    def addProduct() {
        menuService.addProduct(params.token, params.name, params.description, params.price, params.category)
        render "OK"
    }

    def updateDescription() {
        menuService.updateDescription(params.token, params.name, params.desciption)
        render "OK"
    }

    def updatePrice() {
        menuService.updatePrice(params.token, params.name, params.price)
        render "OK"
    }

    def removeProduct() {
        menuService.removeProduct(params.token, params.name)
        render "OK"
    }
}
