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

        for(def it : productsIds) {
            def product = Product.findById(it.id)
            newOrder.addToPositions(new OrderrPosition(product: product, amount: it.amount))
        }

        account.addToOrderrs(newOrder)
        if (!account.save(flush: true))
            throw new Exception("Nie udalo sie zapisac zmian w koncie")
    }
}
