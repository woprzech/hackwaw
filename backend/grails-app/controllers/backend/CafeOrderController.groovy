package backend

import exception.ExceptionHandler
import grails.converters.JSON

class CafeOrderController implements ExceptionHandler {
    CafeOrderService cafeOrderService

    def create() {
        cafeOrderService.createOrder(request.JSON.cafeId, request.JSON.userName, request.JSON.productIds, request.JSON.minutes)
        render "OK"
    }

    def remOrder() {
        cafeOrderService.removeOrder(request.JSON.token, request.JSON.orderId)
        render "OK"
    }

    def getOrders() {
        JSON.registerObjectMarshaller(CafeOrder) { CafeOrder order ->
            return [
                    id: order.id,
                    positions: order.positions,
                    userName: order.userName,
                    code: order.receiptionCode
            ]
        }
        render cafeOrderService.getOrders(request.JSON.token) as JSON
    }
}
