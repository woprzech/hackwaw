package backend

import grails.transaction.Transactional

@Transactional
class CafeOrderService {

    CafeAccountService cafeAccountService


    def createCafeOrder(def userName, def cafeId, def productIds, def orderDate,
                        def receiptionDate) {
        def cafeAccount = cafeAccountService.getCafeAccountByCafeId(cafeId)
        if (cafeAccount == null) {
            throw new Exception("Kawiarnia nie posiada systemu zamawiania")
        }
        def totalPrice = 0
        def orderProducts = []
        def temp = []
//        temp = productIds
        for (def it : productIds) {
            System.out.println("to mam: " + it.id)
            def product = Product.findById(it.id)
            if (product == null) {
                throw new Exception("produkt nie istnieje")
            }
            orderProducts << product
            totalPrice += product.price
        }
        System.out.println(orderProducts)
//        System.out.println(temp)
//        temp.each { it ->
//            System.out.println("to mam: " + it)
//            def product = Product.findById(it)
//            orderProducts << product
//            totalPrice += product.price
//        }
        try {
            cafeAccount.addToOrders(new CafeOrder(cafeAccount: cafeAccount, userName: userName, products: orderProducts,
                    orderDate: orderDate, receiptionDate: receiptionDate, totalPrice: totalPrice))
        } catch (Exception e) {
            System.out.println("oops")
        }

    }

    def getCafeOrdersByCafeId(def token) {
        def foundToken = Token.findByToken(token)
        if (foundToken == null) {
            throw new Exception("Nie jesteś zalogowanym użytkownikiem")
        }
//        return ((Token) foundToken).cafeAccount.getOrders()
        return CafeOrder.findByCafeAccount(((Token) foundToken).cafeAccount)
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
//        order.removeFromProducts(order.products)
        order.delete()

    }


}
