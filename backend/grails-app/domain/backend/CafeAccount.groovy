package backend

class CafeAccount extends Account {
    boolean showMenu = false
    AccountStatus accountStatus = AccountStatus.DEACTIVATED
    Cafe cafe

    static hasMany = [orders: CafeOrder, products: Product]

    static constraints = {
    }
}
