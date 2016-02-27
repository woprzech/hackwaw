package backend

import grails.converters.JSON

class CafeController {

    // testowanie
    def index() {
        new Cafe(name: "Kawiarnia 1", locationX: 52.13, locationY: 21.00).save()
        new Cafe(name: "Kawiarnia 2", locationX: 52.15, locationY: 21.20).save()
        new Cafe(name: "Kawiarnia 3", locationX: 52.14, locationY: 21.30).save()
        new Cafe(name: "Kawiarnia 4", locationX: 52.16, locationY: 21.11).save()

        new Category(name: "Kawa").save()
        new Category(name: "Ciastka").save()
        new Category(name: "Herbata").save()
    }

    def getAll() {
        render Cafe.list() as JSON
    }

    def add() {
        def cafe = new Cafe(params)
        if (Cafe.findByLocationXAndLocationY(cafe.locationX, cafe.locationY) != null) {
            render(status: 406, text: "Kawiarnia o podanych lokalizacjach juz istnieje")
        } else {
            if (cafe.save()) {
                render(text: "Zapisano kawiarnie")
            } else {
                render(status: 406, text: "Nie udalo sie zapisac kawiarni")
            }
        }
    }

    def findNearCafes() {
        def x = params.userX
        def y = params.userY
        def rad = params.rad

        def foundCafes = Cafe.findByLocationXBetweenAndLocationYBetween(x - rad, x + rad, y - rad, y + rad)

        render foundCafes as JSON
    }
}
