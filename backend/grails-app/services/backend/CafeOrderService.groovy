package backend

import grails.transaction.Transactional

@Transactional
class CafeOrderService {

    def createOrder(def cafeId, def name, def productsIds, def minutes) {
        def cafe = Cafe.findById(cafeId)
        if (cafe == null)
            throw new Exception("Nie znaleziono takiej kawiarni")
        def account = CafeAccount.findByCafe(cafe)
        if (account == null)
            throw new Exception("Nie ma konta dla takiej kawiarni")

        def newOrder = new CafeOrder(userName: name)
        newOrder.receiptionDate.setMinutes(newOrder.orderDate.getMinutes() + minutes)
        newOrder.receiptionCode = generateCode()
        if (!newOrder.save(flush: true))
            throw new Exception("Nie udalo sie zapisac zamowienia")

        for (def it : productsIds) {
            def product = Product.findById(it.id)
            newOrder.addToPositions(new CafeOrderPosition(product: product, amount: it.amount))
        }

        account.addToOrders(newOrder)
        if (!account.save(flush: true))
            throw new Exception("Nie udalo sie zapisac zmian w koncie")
    }

    def removeOrder(def token, def orderId) {
        def account = (CafeAccount) getUserByToken(token)
        if (account != null) {
            println orderId
            def order = CafeOrder.findById(orderId)
            if (order != null) {
                account.removeFromOrders(order)
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
            return account.orders
        } else {
            throw new Exception("Musisz sie najpierw zalogowac")
        }
    }

    def getUserByToken(def currentToken) {
        def token = Token.findByToken(currentToken)
        if (token == null) {
            throw new Exception("Musisz się najpierw zalogować")
        }
        def account = token.cafeAccount
        return account
    }

    def generateCode() {
        Random random = new Random()
        int code = random.nextInt(8999) + 1000
        return code
    }
}
