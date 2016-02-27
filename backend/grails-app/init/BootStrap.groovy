import backend.*

class BootStrap {

    def init = { servletContext ->
        def catCookies = new Category(name: "Ciastka").save()
        def catCoffees = new Category(name: "Kawy").save()
        def blackCaffee1 = new Product(category: catCoffees, price: Long.valueOf(500), name: "Kawa Czarna", description: "Smaczniutka czarniutka kawunia").save()
        def whiteCaffee1 = new Product(category: catCoffees, price: Long.valueOf(600), name: "Kawa Biała", description: "Kawka z melczkiem").save()
        def crispyCookie1 = new Product(category: catCookies, price: Long.valueOf(400), name: "Ciastko", description: "Świerzutkie, chrupiące ciasteczko").save()
        def blackCaffee2 = new Product(category: catCoffees, price: Long.valueOf(500), name: "Kawa Czarna", description: "Smaczniutka czarniutka kawunia").save()
        def whiteCaffee2 = new Product(category: catCoffees, price: Long.valueOf(600), name: "Kawa Biała", description: "Kawka z melczkiem").save()
        def crispyCookie2 = new Product(category: catCookies, price: Long.valueOf(400), name: "Ciastko", description: "Świerzutkie, chrupiące ciasteczko").save()
        def menu1 = new Menu()
        menu1.products = [[blackCaffee1, crispyCookie1, whiteCaffee1]] as HashSet
        menu1.save()
        def menu2 = new Menu()
        menu2.products = [[blackCaffee2, crispyCookie2, whiteCaffee2]] as HashSet
        menu2.save()
        def locWawcode = new Location(x: 21.0418707, y: 52.2545864).save()
        def locStarbunio = new Location(x: 21.018492, y: 52.236136).save()
        def cafWawCode = new Cafe(location: locWawcode, name: "WawCodeCafe", menu: menu1).save()
        def cafStarbunio = new Cafe(location: locStarbunio, name: "Starbunio", menu: menu2).save()
        new CafeAccount(login: "seksimyszka", password: "majtunie", cafe: cafStarbunio).save()
        new CafeAccount(login: "bialymisio", password: "puch", cafe: cafWawCode).save()
    }
    def destroy = {
    }
}
