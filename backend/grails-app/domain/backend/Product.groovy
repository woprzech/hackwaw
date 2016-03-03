package backend

class Product {
    Category category
    long price
    String name
    String description

    static belongsTo = [cafeAccount: CafeAccount]

    static constraints = {
        name size: 1..20, blank: false
    }
}
