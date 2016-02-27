package backend

class Product {
    Category category
    double price
    String name
    String description

    static belongsTo = [menu: Menu]

    static constraints = {
        name size: 1..20, blank: false
    }
}
