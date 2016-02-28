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
        for (def it : productIds) {
            def product = Product.findById(it.id)
            if (product == null) {
                throw new Exception("produkt nie istnieje")
            }
            orderProducts << product
            totalPrice += product.price
        }
        def order = new CafeOrder(cafeAccount: cafeAccount, userName: userName, products: orderProducts,
                orderDate: orderDate, receiptionDate: receiptionDate, totalPrice: totalPrice)
        order.save()
        cafeAccount.addToOrders(order)

    }

    def getCafeOrdersByCafeId(def token) {
        def foundToken = Token.findByToken(token)
        if (foundToken == null) {
            throw new Exception("Nie jesteś zalogowanym użytkownikiem")
        }
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
