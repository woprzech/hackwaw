package backend

class MenuController {

    def addProduct() {
        def account = CafeAccount.findByLogin((String) session["loggedAccount"])
        if (account != null) {
            if (Product.findByNameAndMenu(params.name, account.cafe.menu) != null) {
                render(status: 406, text: "Produkt o takiej nazwie istnieje")
            } else {
                def category = Category.findByName(params.category)
                if (category != null) {
                    account.cafe.menu.addToProducts(new Product(name: params.name, description: params.description, price: params.price, category: category))
                    if (account.save())
                        render(text: "OK")
                    else
                        render(status: 406, text: "Nie udalo sie zapisac")
                } else {
                    render(status: 404, text: "Nie ma takiej kategorii")
                }
            }
        } else {
            render(status: 401, text: "Musisz sie najpierw zalogowac")
        }
    }
}
