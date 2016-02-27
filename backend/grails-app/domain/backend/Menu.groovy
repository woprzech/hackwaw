package backend

class Menu {
//    static belongsTo = [cafe: Cafe]

    static hasMany = [products: Product]

    static constraints = {
    }
}
