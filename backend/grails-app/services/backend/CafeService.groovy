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

        def cafeAccount = CafeAccount.findByCafe(cafe)
        if(cafeAccount == null)
            throw new Exception("Nie znaleziono konta dla danej kawiarni")

        if (categoryName == null) {
            return cafeAccount.products
        } else {
            def category = Category.findByName(categoryName)
            if (category == null)
                throw new Exception("Nie znaleziono takiej kategorii")
            def products = []

            for (def prod : cafeAccount.products) {
                if (prod.category == category)
                    products.add(prod)
            }
            return products
        }
    }

    def getMenuByToken(def token) {
        def foundToken = Token.findByToken(token)
        if (foundToken == null)
            throw new Exception("Nie znaleziono takiego tokenu")

        def cafeAccount = (CafeAccount) foundToken.account
        return cafeAccount.products
    }
}
