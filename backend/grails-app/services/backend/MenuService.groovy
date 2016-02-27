package backend

import grails.transaction.Transactional

@Transactional
class MenuService {

    def addProduct(def login, def token, def name, def description, def price, def categoryName) {
        def account = CafeAccount.findByLogin((String) login)
        if (account != null) {
            checkToken(account, token)
            if (Product.findByNameAndMenu(name, account.cafe.menu) != null) {
                throw new Exception("Produkt o takiej nazwie juz istnieje")
            } else {
                def category = Category.findByName(categoryName)
                if (category != null) {
                    account.cafe.menu.addToProducts(new Product(name: name, description: description, price: price, category: category))
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

    def updateDescription(def login, def token, def name, def description) {
        def account = CafeAccount.findByLogin((String) login)
        if (account != null) {
            checkToken(account, token)
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

    def updatePrice(def login, def token, def name, def price) {
        def account = CafeAccount.findByLogin((String) login)
        if (account != null) {
            checkToken(account, token)
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

    def checkToken(def account, def currentToken) {
        for (def token : account.tokens) {
            if (token.token.equals(currentToken))
                return
        }

        throw new Exception("Tokeny sie nie zgadzaja!")
    }
}
