package backend

import exception.ExceptionHandler

class MenuController implements ExceptionHandler {
    MenuService menuService

    def addProduct() {
        menuService.addProduct(session["loggedAccount"], params.name, params.description, params.price, params.category)
        render "OK"
    }

    def updateDescription() {
        menuService.updateDescription(session["loggedAccount"], params.name, params.desciption)
        render "OK"
    }

    def updatePrice() {
        menuService.updatePrice(session["loggedAccount"], params.name, params.price)
        render "OK"
    }
}
