package backend

import grails.converters.JSON

class CategoryController {

    def getAll() {
        render Category.list()  as JSON
    }
}
