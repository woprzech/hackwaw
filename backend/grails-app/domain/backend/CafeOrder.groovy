package backend

class CafeOrder {
    int receiptionCode = 0
    Date orderDate = new Date()
    Date receiptionDate = new Date()
    String userName

    static hasMany = [positions: CafeOrderPosition]

    static constraints = {
        userName unique: false, blank: null
    }
}
