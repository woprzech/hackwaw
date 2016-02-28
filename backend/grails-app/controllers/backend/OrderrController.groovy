package backend

import exception.ExceptionHandler
import grails.converters.JSON

class OrderrController implements ExceptionHandler {
    OrderrService orderrService

    def create() {
        orderrService.createOrder(request.JSON.cafeId, request.JSON.userName, request.JSON.productIds, request.JSON.minutes)
        render "OK"
    }

    def remOrder() {
        orderrService.removeOrder(request.JSON.token, request.JSON.orderId)
        render "OK"
    }

    def getOrders() {
        JSON.use('deep') {
            render orderrService.getOrders(params.token) as JSON
        }
//        render orderrService.getOrders(params.token) as JSON
    }
}
