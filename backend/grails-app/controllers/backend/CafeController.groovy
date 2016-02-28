package backend

import exception.ExceptionHandler
import grails.converters.JSON

class CafeController implements ExceptionHandler {
    CafeService cafeService

    def getAll() {
        render Cafe.list() as JSON
    }

    def add() {
        def cafe = new Cafe(params)
        cafeService.saveCafe(cafe)
        render "OK"
    }

    static double kmForDegree = 111.196672

    def findByLocation() {
        def x = Double.valueOf((String) params.userX)
        def y = Double.valueOf((String) params.userY)
        def rad = Double.valueOf((String) params.rad) / kmForDegree

        def cafes = Cafe.executeQuery("from Cafe where location_x between :x1 and :x2 and location_y between :y1 and :y2",
                [x1: (x - rad), x2: (x + rad), y1: (y - rad), y2: (y + rad)])

        render cafes as JSON
    }

    def getAllProducts() {
        def products = cafeService.getMenu(params.cafeId, params.category)
        render products as JSON
    }

    def getAllProductsByToken() {
        def products = cafeService.getMenuByToken(request.JSON.token)
        render products as JSON
    }
}
