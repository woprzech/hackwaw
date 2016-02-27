package backend

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Location {
    double x
    double y

    static constraints = {
        x blank: false
        y blank: false
    }
}
