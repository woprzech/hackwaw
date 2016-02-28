package backend

import grails.transaction.Transactional

@Transactional
class MenuService {

//    def addProduct(def token, def name, def description, def price, def categoryName) {
//        def account = getUserByToken(token)
//        if (account != null) {
//            if (Product.findByNameAndMenu(name, account.cafe.menu) != null) {
//                throw new Exception("Produkt o takiej nazwie juz istnieje")
//            } else {
//                def category = Category.findByName(categoryName)
//                if (category != null) {
//                    account.cafe.menu.addToProducts(new Product(name: name, description: description, price: new BigDecimal(price), category: category))
//                    if (!account.save())
//                        throw new Exception("Nie udalo sie zapisac produktu")
//                } else {
//                    throw new Exception("Nie znaleziono kategorii")
//                }
//            }
//        } else {
//            throw new Exception("Musisz sie najpierw zalogowac")
//        }
//    }

//    def updateDescription(def token, def productId, def description) {
//        def account = (CafeAccount) getUserByToken(token)
//        if (account != null) {
//            def product = Product.findByIdAndMenu(productId, account.cafe.menu)
//            if (product != null) {
//                product.description = description
//            } else {
//                throw new Exception("Nie znaleziono takiego produktu")
//            }
//        } else {
//            throw new Exception("Musisz sie najpierw zalogowac")
//        }
//    }
//
//    def updatePrice(def token, def productId, def price) {
//        def account = (CafeAccount) getUserByToken(token)
//        if (account != null) {
//            def product = Product.findByIdAndMenu(productId, account.cafe.menu)
//            if (product != null) {
//                product.description = price
//            } else {
//                throw new Exception("Nie znaleziono takiego produktu")
//            }
//        } else {
//            throw new Exception("Musisz sie najpierw zalogowac")
//        }
//    }

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
            } else {
                account.cafe.menu.addToProducts(new Product(name: name, description: description, price: new BigDecimal(price), category: category))
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
