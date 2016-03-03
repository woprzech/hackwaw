package backend

import exception.ExceptionHandler

class AdminAccountController implements ExceptionHandler {
    AdminAccountService adminAccountService

    def createCafe() {
        adminAccountService.createCafe(request.JSON.token, request.JSON.cafeName, request.JSON.posX, request.JSON.posY)
        render "OK"
    }

    def removeCafe() {
        adminAccountService.removeCafe(request.JSON.token, request.JSON.cafeId)
        render "OK"
    }

    def updateCafeData() {
        adminAccountService.updateCafe(request.JSON.token, request.JSON.cafeId, request.JSON.cafeName, request.JSON.posX, request.JSON.posY)
        render "OK"
    }

    def setAccountStatus() {
        adminAccountService.setAccountStatus(request.JSON.token, request.JSON.cafeId, request.JSON.status)
        render "OK"
    }
}
