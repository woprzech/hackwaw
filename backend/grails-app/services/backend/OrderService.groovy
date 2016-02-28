package backend

import grails.transaction.Transactional

@Transactional
class CafeOrderService {

    CafeAccountService cafeAccountService


    def createCafeOrder(def userName, def cafeId, def productIds, def orderDate,
                        def receiptionDate) {
        def cafeAccount = cafeAccountService.getCafeAccountByCafeId(cafeId)
        println userName + " " + cafeId + " " + orderDate
        if (cafeAccount == null) {
            throw new Exception("Kawiarnia nie posiada systemu zamawiania")
        }
        def totalPrice = 0
        def orderProducts = []
//        for (def it : productIds) {
//            def product = Product.findById(it.id)
//            if (product == null) {
//                throw new Exception("produkt nie istnieje")
//            }
//            orderProducts << product
//            totalPrice += product.price
//        }
        println userName
        def order = new CafeOrder(cafeAccount: cafeAccount, userName: userName, orderDate: orderDate, receiptionDate: receiptionDate, totalPrice: totalPrice)
        println order.userName
        cafeAccount.addToOrders(order)
        println order.userName
    }

    def getCafeOrdersByCafeId(def token) {
        def foundToken = Token.findByToken(token)
        if (foundToken == null) {
            throw new Exception("Nie jesteś zalogowanym użytkownikiem")
        }
        System.out.println(((Token) foundToken).cafeAccount)
        System.out.println(CafeOrder.findByCafeAccount(((Token) foundToken).cafeAccount))
        def orders = CafeOrder.findAllByCafeAccount(((Token) foundToken).cafeAccount)

        for (def order : orders) {
//            System.out.println(order.positions)
//            order.setPositions(CafeOrderPosition.findAllByOrder(order))
        }
        return orders
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
        def positions = CafeOrderPosition.findAllByOrder(order)
//        order.removeFromPositions(positions)
        for (def position : positions) {
            position.delete()
        }
//        order.removeFromProducts(order.products)

//        order.removeFromPositions(order.positions)
//        CafeOrderPosition.
        order.delete()

    }


}
