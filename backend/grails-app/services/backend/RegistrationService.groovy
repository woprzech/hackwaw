package backend

import grails.transaction.Transactional

@Transactional
class RegistrationService {

    def register(def address, def cafeName, def email, def phone) {
        Registration registration = new Registration(address: address, cafeName: cafeName, email: email, phone: phone)

        if (!registration.save())
            throw new Exception("Nie udalo sie zapisac rejestracji")
    }
}
