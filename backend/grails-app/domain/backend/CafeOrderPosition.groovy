package backend

class CafeOrderPosition {
    Product product
    int amount

    static constraints = {
        amount min: 1, blank: false, unique: false
        product unique: false, blank: false
    }
}
