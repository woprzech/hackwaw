package backend

class Product {
    Category category
    Long price
    String name
    String description

    static belongsTo = [menu: Menu]

    static constraints = {
        name size: 1..20, blank: false
    }
}
