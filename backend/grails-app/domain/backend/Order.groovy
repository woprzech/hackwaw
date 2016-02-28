package backend

class CafeOrder {

    static belongsTo = [cafeAccount: CafeAccount]
    String userName
    static hasMany = [positions: CafeOrderPosition]
    Date orderDate
    Date receiptionDate
    BigDecimal totalPrice
    static fetchMode = [positions: 'eager']


    static constraints = {
        userName size: 5..20, blank: false, unique: false
        totalPrice blank: false
        orderDate blank: false
        receiptionDate blank: false
    }
}
