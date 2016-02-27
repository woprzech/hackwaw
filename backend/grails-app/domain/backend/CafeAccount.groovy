package backend

class CafeAccount {
    Cafe cafe
    String login
    String password
    static hasMany = [orders: CafeOrder, tokens: Token]

    static constraints = {
        login size: 5..20, blank: false, unique: true
        password size: 5..20, blank: false, unique: false
    }
}
