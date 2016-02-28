package backend

import grails.transaction.Transactional

@Transactional
class CafeService {

    def saveCafe(def cafe) {
        if (Cafe.findByLocationXAndLocationY(cafe.locationX, cafe.locationY) != null) {
            throw new Exception("Kawiarnia o podanych lokalizacjach juz istnieje", cafe)
        } else {
            if (!cafe.save()) {
                throw new Exception("Nie udalo sie zapisac kawiarni")
            }
        }
    }

    def getMenu(def cafeId, def categoryName) {
        def cafe = Cafe.findById(cafeId)
        if (cafe == null)
            throw new Exception("Nie znaleziono takiej kawiarni")

        if (categoryName == null) {
            return cafe.menu.products
        } else {
            def category = Category.findByName(categoryName)
            if (category == null)
                throw new Exception("Nie znaleziono takiej kategorii")
            def products = []
            for (def prod : cafe.menu.products) {
                if (prod.category == category)
                    products.add(prod)
            }
            return products
        }
    }

    def getCafeByToken(def token) {
        def foundToken = Token.findByToken(token)
        if (foundToken == null)
            throw new Exception("Nie znaleziono takiego tokenu")

        return foundToken.cafeAccount.cafe.menu.products
    }
}
