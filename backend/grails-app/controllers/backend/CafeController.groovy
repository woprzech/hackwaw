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
        def x = Double.valueOf((String) params.userX)
        def y = Double.valueOf((String) params.userY)
        def rad = Double.valueOf((String) params.rad)

        def cafes = Cafe.executeQuery("from Cafe where location_x between :x1 and :x2 and location_y between :y1 and :y2",
                [x1: (x - rad), x2: (x + rad), y1: (y - rad), y2: (y + rad)])

        render cafes as JSON
    }

    def getAllProducts() {
        def products = cafeService.getMenu(params.cafeId, params.category)
        render products as JSON
    }
}
