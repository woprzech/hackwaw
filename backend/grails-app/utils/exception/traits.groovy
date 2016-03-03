package exception

trait ExceptionHandler {

    def handleException(Exception e) {
        response.status = 406
        render(e.getMessage())
    }
}