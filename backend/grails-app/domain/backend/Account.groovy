package backend

class Account {
    String login
    String password

    static hasMany = [tokens: Token]

    static constraints = {
        login size: 5..20, blank: false, unique: true
        password size: 5..20, blank: false, unique: false
    }
}
