package backend

import grails.converters.JSON

class CafeOrderController {
    CafeOrderService cafeOrderService

    def newOrder() {
        cafeOrderService.createCafeOrder(request.userName, request.cafeId, request.productIds, request.orderDate,
                request.receiptionDate)
        render "OK"
    }

    def getOrders() {
        render cafeOrderService.getCafeOrdersByCafeId(params.token) as JSON
    }
}
