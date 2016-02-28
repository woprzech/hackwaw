package backend

import grails.transaction.Transactional

@Transactional
class OrderrService {

    def createOrder(def cafeId, def name, def productsIds) {
        def account = CafeAccount.findByCafe(Cafe.findById(cafeId))
        if (account == null)
            throw new Exception("Nie ma takiej kawiarni!")

        def newOrder = new Orderr(userName: name)
        println newOrder.userName
        println newOrder.receiptionDate
        println newOrder.orderDate
        if (!newOrder.save(flush: true))
            throw new Exception("Nie udalo sie zapisac zamowienia")

        for (def it : productsIds) {
            def product = Product.findById(it.id)
            newOrder.addToPositions(new OrderrPosition(product: product, amount: it.amount))
        }

        account.addToOrderrs(newOrder)
        if (!account.save(flush: true))
            throw new Exception("Nie udalo sie zapisac zmian w koncie")
    }

    def delete(def token, def orderId) {
        def account = (CafeAccount) getUserByToken(token)
        if (account != null) {
            def order = Orderr.findById(orderId)
            if (order != null) {
                account.removeFromOrderrs(order)
                order.delete()
            } else {
                throw new Exception("Nie znaleziono takiego zamówienia")
            }
        } else {
            throw new Exception("Musisz sie najpierw zalogowac")
        }
    }

    def getOrders(def token) {
        def account = (CafeAccount) getUserByToken(token)
        if (account != null) {
            return account.orderrs
        } else {
            throw new Exception("Musisz sie najpierw zalogowac")
        }
    }

    def getUserByToken(def currentToken) {
        def token = Token.findByToken(currentToken)
        if(token == null){
            throw new Exception("Musisz się najpierw zalogować")
        }
        def account = token.cafeAccount
        return account
    }


}
