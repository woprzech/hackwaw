package backend

class CafeAccount {
    Cafe cafe
    String login
    String password

    static hasMany = [tokens: Token, orders: CafeOrder]

    static fetchMode = [orders: 'eager']

    static constraints = {
        login size: 5..20, blank: false, unique: true
        password size: 5..20, blank: false, unique: false
    }
}
