package exception

// TODO dodac inne handlery
trait ExceptionHandler {

    def handleException(Exception e) {
        response.status = 404
        render("Error: " + e.getMessage())
    }
}