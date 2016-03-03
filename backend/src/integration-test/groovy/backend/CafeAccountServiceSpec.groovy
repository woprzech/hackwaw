package backend

import grails.test.mixin.TestFor
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import spock.lang.*

@Integration
@Rollback
@TestFor(RegistrationController)
class CafeAccountControllerSpec extends Specification {

    void "Creating account test"() {
        when:
        request.JSON.email = "aaaaa"
        request.JSON.cafeName = "aaaaa"
        request.JSON.phone = "aaaaa"
        request.JSON.address = "aaaaa"
        request.method = 'POST'
        controller.create()

        then:
        response.text == "OK"
    }
}
