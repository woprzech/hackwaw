package backend

import grails.converters.JSON

class CafeController {

    def getAll() {
        render Cafe.list() as JSON
    }

    def add() {
        def cafe = new Cafe(params)
        if(Cafe.findByLocationXAndLocationY(cafe.locationX, cafe.locationY) != null) {
            render(status: 406, text: "Kawiarnia o podanych lokalizacjach juz istnieje")
        } else {
            if(cafe.save()) {
                render(text: "Zapisano kawiarnie")
            } else {
                render(status: 406, text: "Nie udalo sie zapisac kawiarni")
            }
        }
    }

    def findNearCafes() {
        def x = params.userX
        def y = params.userY
    }
}
