package backend

import exception.ExceptionHandler
import grails.converters.JSON

class CafeController implements ExceptionHandler {
    CafeService cafeService

    // testowanie
    def index() {
        new Category(name: "Ciastka").save()
        def kawa = new Category(name: "Kawa")
        kawa.save()
        new Category(name: "Herbata").save()

        def cafe = new Cafe(name: "Kawiarnia", location: new Location(x: 52.00, y: 21.00), menu: new Menu())
        cafe.save()

        cafe.menu.addToProducts(new Product(category: kawa, price: 5.00, name: "Espresso1", description: "123"))
        cafe.menu.addToProducts(new Product(category: kawa, price: 6.66, name: "Podwojne Espresso", description: "321"))

        if (cafe.save())
            render "OK"
        else
            render "ERROR"

        cafe = new Cafe(name: "Kawiarnia_2", location: new Location(x: 52.00, y: 21.00), menu: new Menu())
        cafe.save()

        cafe.menu.addToProducts(new Product(category: kawa, price: 5.00, name: "Espresso1", description: "123"))
        cafe.menu.addToProducts(new Product(category: kawa, price: 6.66, name: "Podwojne Espresso", description: "321"))

        if (cafe.save())
            render "OK"
        else
            render "ERROR"
    }

    def getAll() {
        render Cafe.list() as JSON
    }

    def add() {
        def cafe = new Cafe(params)
        cafeService.saveCafe(cafe)
        render "OK"
    }

    def findByLocation() {
        def x = params.userX
        def y = params.userY
        def rad = params.rad

        def foundCafes = Cafe.findByLocationXBetweenAndLocationYBetween(x - rad, x + rad, y - rad, y + rad)

        render foundCafes as JSON
    }
}
