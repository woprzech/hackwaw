package exception

trait ExceptionHandler {

    def handleException(Exception e) {
        response.status = 404
        render(e.getMessage())
    }
}