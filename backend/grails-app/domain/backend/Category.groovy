package backend

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Category {
    String name

    static constraints = {
        name size: 1..20, blank: false, unique: true
    }
}
