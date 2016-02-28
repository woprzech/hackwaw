package backend

import grails.transaction.Transactional

@Transactional
class MenuService {

    def removeProduct(def token, def productId) {
        def account = getUserByToken(token)
        if (account != null) {
            def product = Product.findByIdAndMenu(productId, account.cafe.menu)
            if (product != null) {
                account.cafe.menu.removeFromProducts(product)
                product.delete()
            } else {
                throw new Exception("Nie znaleziono takiego produktu")
            }
        } else {
            throw new Exception("Musisz sie najpierw zalogowac")
        }
    }

    def updateProduct(def token, def productId, def newName, def newDescription, def newPrice, def newCategoryId) {
        def account = (CafeAccount) getUserByToken(token)
        def category = Category.findById(newCategoryId)
        println newCategoryId
        if (category == null)
            throw new Exception("Nie znaleziono takiej kategorii")

        if (account != null) {
            def product = Product.findByIdAndMenu(productId, account.cafe.menu)
            if (product != null) {
                product.description = newDescription[0]
                product.name = newName[0]
                product.price = newPrice[0]
                product.category = category
                product.save()
            } else {
                account.cafe.menu.addToProducts(new Product(name: newName[0], description: newDescription[0], price: newPrice[0], category: category))
            }
        } else {
            throw new Exception("Musisz sie najpierw zalogowac")
        }
    }

    def getUserByToken(def currentToken) {
        def token = Token.findByToken(currentToken)
        def account = token.cafeAccount
        return account
    }
}
