package backend

class Cafe {
    String name
    double locationX, locationY

    static constraints = {
        name size: 1..20, unique: false, blank: false
        locationX blank: false
        locationY blank: false
    }
}
