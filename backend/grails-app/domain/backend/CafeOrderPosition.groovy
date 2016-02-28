package backend

class CafeOrderPosition {

    Product product
    static belongsTo = ["order": CafeOrder]
    int amount

    static constraints = {
    }
}
