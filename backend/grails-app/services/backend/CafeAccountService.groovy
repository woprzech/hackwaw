package backend

import grails.transaction.Transactional

@Transactional
class CafeAccountService {

    def createAccount(def login, def password, def cafeId) {
        def cafe = Cafe.findById(cafeId)

        if (CafeAccount.findByCafe(cafe) == null) {
            def newAccount = new CafeAccount(login: login, password: password, cafe: cafe)
            if (!newAccount.save()) {
                throw new Exception("Nie udalo sie stworzyc konta")
            }
        } else
            throw new Exception("Istnieje konto dla tej restauracji")
    }

    def getUserByToken(def currentToken) {
        def token = Token.findByToken(currentToken)
        if (token == null)
            throw new Exception("Brak takiego tokenu")
        if (token.account.class != CafeAccount.class)
            throw new Exception("Zly typ konta")
        return token.account
    }

    def updateProduct(def token, def productId, def newName, def newDescription, def newPrice, def newCategoryId) {
        def account = (CafeAccount) getUserByToken(token)
        def category = Category.findById(newCategoryId)
        println newCategoryId
        if (category == null)
            throw new Exception("Nie znaleziono takiej kategorii")

        if (account != null) {
            def product = Product.findByIdAndCafeAccount(productId, account)
            if (product != null) {
                product.description = newDescription
                product.name = String.valueOf(newName)
                product.price = Long.parseLong(newPrice)
                product.category = category
                product.save()
            } else {
                account.addToProducts(new Product(name: newName, description: newDescription, price: Long.parseLong(newPrice), category: category))
            }
        } else {
            throw new Exception("Musisz sie najpierw zalogowac")
        }
    }

    def removeProduct(def token, def productId) {
        def account = getUserByToken(token)
        if (account != null) {
            def product = Product.findByIdAndCafeAccount(productId, account)
            if (product != null) {
                account.removeFromProducts(product)
                product.delete()
            } else {
                throw new Exception("Nie znaleziono takiego produktu")
            }
        } else {
            throw new Exception("Musisz sie najpierw zalogowac")
        }
    }

    def changeMenuFlag(def token, def status) {
        CafeAccount account = (CafeAccount) getUserByToken(token)
        account.cafe.hasMenu = status
        account.showMenu = status
        if (!account.save(flush: true) || !account.cafe.save(flush: true))
            throw new Exception("Nie udalo sie zapisac zmian")
    }
}
