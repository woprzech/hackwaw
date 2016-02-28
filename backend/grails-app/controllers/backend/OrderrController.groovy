package backend

import exception.ExceptionHandler

class OrderrController implements ExceptionHandler {
    OrderrService orderrService

    def create() {
        orderrService.createOrder(request.JSON.cafeId, request.JSON.userName, request.JSON.productIds)
        render "OK"
    }
}
