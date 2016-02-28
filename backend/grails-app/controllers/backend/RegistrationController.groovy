package backend

import exception.ExceptionHandler

class RegistrationController implements ExceptionHandler {
    RegistrationService registrationService

    def create() {
        registrationService.register(request.JSON.address, request.JSON.cafeName, request.JSON.email, request.JSON.phone)
        render "OK"
    }
}
