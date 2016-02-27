package backend

class CafeOrder {

    static belongsTo = [cafeAccount: CafeAccount]
    String userName
    static hasMany = [products: Product]
    Date orderDate
    Date receiptionDate
    BigDecimal totalPrice


    static constraints = {
        userName size: 5..20, blank: false, unique: false
        totalPrice blank: false
        orderDate blank: false
        receiptionDate blank: false
    }
}
