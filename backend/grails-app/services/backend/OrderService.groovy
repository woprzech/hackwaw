package backend

import grails.transaction.Transactional

@Transactional
class CafeOrderService {

    CafeAccountService cafeAccountService


    def createCafeOrder(def userName, def cafeId, List<Integer> productIds, def orderDate,
                        def receiptionDate) {
        def cafeAccount = cafeAccountService.getCafeAccountByCafeId(cafeId)
        def totalPrice = 0
        for (Product product : products) {
            totalPrice += product.price
        }
        def orderProducts = []
        productIds.each { it -> orderProducts << Product.findById(it) }
        cafeAccount.addToOrders(new CafeOrder(cafeAccount: cafeAccount, userName: userName, products: orderProducts,
                orderDate: orderDate, receiptionDate: receiptionDate, totalPrice: totalPrice))
    }

    def getCafeOrdersByCafeId(def token) {
        def foundToken = Token.findByToken(token)
        if (foundToken == null) {
            throw new Exception("Nie jesteś zalogowanym użytkownikiem")
        }
        return ((Token) foundToken).cafeAccount.getOrders()
    }

    def realizeOrder(def token, def orderId) {
        System.out.println(token)
        def foundToken = Token.findByToken(token)
        if (foundToken == null) {
            throw new Exception("Nie jesteś zalogowanym użytkownikiem")
        }
        def order = CafeOrder.findById(orderId)
        if (order == null) {
            throw new Exception("Zamówienie nie istnieje")
        }
        order.cafeAccount.removeFromOrders(order)
        order.removeFromProducts(order.products)
        order.delete()

    }


}
