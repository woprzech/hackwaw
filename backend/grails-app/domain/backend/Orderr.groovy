package backend

class Orderr {
    String userName
    Date orderDate = new Date()
    Date receiptionDate = new Date()

    static hasMany = [positions: OrderrPosition]

    static constraints = {
    }
}
