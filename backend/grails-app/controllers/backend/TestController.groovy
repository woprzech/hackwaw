package backend

class TestController {

    def create() {
        def cafe1 = new Cafe(location: new Location(x: 51.00, y: 21.00), name: "Kawiarnia 1")
        def cafe2 = new Cafe(location: new Location(x: 51.11, y: 21.11), name: "Kawiarnia 2")
        cafe1.save(flush: true)
        cafe2.save(flush: true)

        def account1 = new CafeAccount(cafe: cafe1, login: "login1", password: "pass1")
        def account2 = new CafeAccount(cafe: cafe2, login: "login2", password: "pass2")
        account1.save(flush: true)
        account2.save(flush: true)

        def kawa = new Category(name: "Kawa")
        def ciastka = new Category(name: "Ciastka")
        def inne = new Category(name: "Inne")
        kawa.save(flush: true)
        ciastka.save(flush: true)
        inne.save(flush: true)

        def menu1 = cafe1.menu
        def latte = new Product(name: "Latte", category: kawa, description: "latte...", price: 500)
        def espresso = new Product(name: "Espresso", category: kawa, description: "espresso...", price: 500)
        def podwojne = new Product(name: "Podwojne Espresso", category: kawa, description: "podwojne espresso...", price: 600)
        menu1.addToProducts(latte)
        menu1.addToProducts(espresso)
        menu1.addToProducts(podwojne)

        def order1 = new CafeOrder(userName: "Witek")
        order1.addToPositions(new CafeOrderPosition(product: latte, amount: 4))
        order1.addToPositions(new CafeOrderPosition(product: espresso, amount: 2))
        account1.addToOrders(order1)

        // TODO uzupelnic o wiecej danych

        render "OK"
    }
}
