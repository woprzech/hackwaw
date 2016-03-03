package backend

class Cafe {
    Location location
    String name

    static embedded = ['location']

    static constraints = {
        name size: 1..20, unique: false, blank: false
        location unique: true, blank: false
    }
}
