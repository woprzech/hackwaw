package backend

import grails.converters.JSON

class CafeOrderController {
    CafeOrderService cafeOrderService

    def newOrder() {
        cafeOrderService.createCafeOrder(request.JSON.userName, request.JSON.cafeId, request.JSON.productIds, request.JSON.orderDate,
                request.JSON.receiptionDate)
        render "OK"
    }

    def getOrders() {
        JSON.use('deep') {
            render cafeOrderService.getCafeOrdersByCafeId(params.token) as JSON
        }
    }

    def remOrder() {
//        def data = JSON.parse(params.sendData)
//        def rows = data.rows
        System.out.println(request.JSON.token)
        cafeOrderService.realizeOrder(request.JSON.token, request.JSON.orderId)
        render "OK"
    }
}
