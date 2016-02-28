package backend

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

        "/cafe/add" {
            controller = "Cafe"
            action = "add"
        }

        "/cafe/getMenu" {
            controller = "Cafe"
            action = "getAllProducts"
        }

        "/cafe/getAll" {
            controller = "Cafe"
            action = "getAll"
        }

        "/cafe/findByLocation" {
            controller = "Cafe"
            action = "findByLocation"
        }

        "/cafe/menu/addProduct" {
            controller = "Menu"
            action = [POST:  "addProduct"]
        }

        "/cafe/menu/update/description" {
            controller = "Menu"
            action = [POST: "updateDescription"]
        }

        "/cafe/menu/update/price" {
            controller = "Menu"
            action = [POST: "updatePrice"]
        }

        "/cafe/menu/remove" {
            controller = "Menu"
            action = [POST: "removeProduct"]
        }

        "/account/login" {
            controller = "CafeAccount"
            action = [POST: "login"]
        }

        "/account/logout" {
            controller = "CafeAccount"
            action = [POST: "logout"]
        }

        "/account/create" {
            controller = "CafeAccount"
            action = [POST: "createAccount"]
        }

        "/order/create" {
            controller = "CafeOrder"
            action = [POST: "newOrder"]
        }

        "/orders" {
            controller = "CafeOrder"
            action = "getOrders"
        }

        "/orders/realize" {
            controller = "CafeOrder"
            action = [POST: "remOrder"]
        }

        "/categories/get" {
            controller = "Category"
            action = [GET: "getAll"]
        }

        "/registration" {
            controller = "Registration"
            action = [POST: "register"]
        }

        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
