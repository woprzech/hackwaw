package backend

class Token {
    String token

    static belongsTo = [cafeAccount: CafeAccount]

    static constraints = {
        token size: 128..128, blank: false, unique: false, index: 'token_Idx'
    }
}
