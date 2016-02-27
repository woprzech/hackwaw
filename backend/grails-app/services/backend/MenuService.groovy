package backend

import grails.transaction.Transactional

@Transactional
class MenuService {

    // TODO tylko to testowania
    def addProduct(def token, def name, def description, def price, def categoryName) {
        def account = getUserByToken(token)
        if (account != null) {
            if (Product.findByNameAndMenu(name, account.cafe.menu) != null) {
                throw new Exception("Produkt o takiej nazwie juz istnieje")
            } else {
                def category = Category.findByName(categoryName)
                if (category != null) {
                    account.cafe.menu.addToProducts(new Product(name: name, description: description, price: new BigDecimal(price), category: category))
                    if (!account.save())
                        throw new Exception("Nie udalo sie zapisac produktu")
                } else {
                    throw new Exception("Nie znaleziono kategorii")
                }
            }
        } else {
            throw new Exception("Musisz sie najpierw zalogowac")
        }
    }

    def updateDescription(def token, def name, def description) {
        def account = (CafeAccount) getUserByToken(token)
        if (account != null) {
            def product = Product.findByNameAndMenu(name, account.cafe.menu)
            if (product != null) {
                product.description = description
            } else {
                throw new Exception("Nie znaleziono takiego produktu")
            }
        } else {
            throw new Exception("Musisz sie najpierw zalogowac")
        }
    }

    def updatePrice(def token, def name, def price) {
        def account = (CafeAccount) getUserByToken(token)
        if (account != null) {
            def product = Product.findByNameAndMenu(name, account.cafe.menu)
            if (product != null) {
                product.description = price
            } else {
                throw new Exception("Nie znaleziono takiego produktu")
            }
        } else {
            throw new Exception("Musisz sie najpierw zalogowac")
        }
    }

    def removeProduct(def token, def name) {
        def account = getUserByToken(token)
        if (account != null) {
            def product = Product.findByNameAndMenu(name, account.cafe.menu)
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

    def getUserByToken(def currentToken) {
        def token = Token.findByToken(currentToken)
        def account = token.cafeAccount
        return account
    }
}
