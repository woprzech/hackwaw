package backend

class Token {
    String token

    static belongsTo = [account: Account]

    static constraints = {
        token size: 128..128, blank: false, unique: false, index: 'token_Idx'
    }
}
