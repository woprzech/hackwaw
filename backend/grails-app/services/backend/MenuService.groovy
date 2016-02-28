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
        if (category == null)
            throw new Exception("Nie znaleziono takiej kategorii")

        if (account != null) {
            def product = Product.findByIdAndMenu(productId, account.cafe.menu)
            if (product != null) {
                product.description = newDescription
                product.name = newName
                product.price = new BigDecimal(newPrice)
                product.category = category
                product.save()
            } else {
                account.cafe.menu.addToProducts(new Product(name: newName, description: newDescription, price: new BigDecimal(newPrice), category: category))
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
