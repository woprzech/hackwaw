package backend

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.*

@Mock(Registration)
@TestFor(RegistrationService)
class RegistrationServiceSpec extends Specification {

    void "Creating registration test"() {
        given:
        def sampleReg = new Registration(address: "Wola", cafeName: "Kawiarnia", email: "A@A.BB", phone: "0-700-000-900")
        def regNum = Registration.count()

        when:
        service.register(sampleReg.address, sampleReg.cafeName, sampleReg.email, sampleReg.phone)
        def reg = Registration.findByCafeName("Kawiarnia")

        then:
        Registration.count() == regNum + 1
        reg != null
        reg.address == sampleReg.address
        reg.cafeName == sampleReg.cafeName
        reg.email == sampleReg.email
        reg.phone == sampleReg.phone
    }
}