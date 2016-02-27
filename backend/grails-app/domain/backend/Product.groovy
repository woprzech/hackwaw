package backend

class Product {
    Category category
    double price
    String name
    String description

    static belongsTo = [menu: Menu]

    static constraints = {
        category blank: false
        name size: 1..20, blank: false
        description size: 0..50, blank: true
    }
}
