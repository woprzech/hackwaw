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

    def getMenu(def cafeId) {
        def cafe = Cafe.findById(cafeId)
        if(cafe == null)
            throw new Exception("Nie znaleziono takiej kawiarni")

        return cafe.menu
    }
}
