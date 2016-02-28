package backend

import exception.ExceptionHandler
import grails.converters.JSON

class CafeController implements ExceptionHandler {
    CafeService cafeService

    // testowanie
    def index() {
        def kawa = new Category(name: "Kawa").save()
        kawa.save()
        new Category(name: "Ciastka").save()
        new Category(name: "Inne").save()

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

        def cafes = []
//        def locations = Location.findByXBetweenAndYBetween(x - rad, x + rad, y - rad, y + rad)

//        for (def loc : locations) {
//            cafes.add(Cafe.findByLocation(loc))
//        }

        render cafes as JSON
    }

    def getAllProducts() {
        def menu = cafeService.getMenu(params.cafeId)
        render menu.products as JSON
    }
}
