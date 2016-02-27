package backend

class CafeAccountController {

    def createAccount() {
        def cafeId = params.cafeId
        def cafe = Cafe.findById(Long.valueOf(cafeId))

        if (CafeAccount.findByCafe(cafe) == null) {
            def newAccount = new CafeAccount(login: params.login, password: params.password, cafe: cafe)
            if (newAccount.save()) {
                render "OK"
            } else {
                render(status: 406, text: "Nie udalo sie stworzyc konta")
            }
        } else
            render(status: 406, text: "Istnieje konto dla tej restauracji")
    }

    def login() {
        def login = params.login
        def account = CafeAccount.findByLogin(login)
        if (account != null) {
            if (account.password.equals(params.password)) {
                session["loggedAccount"] = account.login
                render "OK"
            } else
                render(status: 403, text: "Zle haslo")
        } else
            render(status: 404, text: "Nie znaleziono uzytkownika z takim loginem")
    }

    def logout() {
        session.invalidate()

        render "OK"
    }
}
