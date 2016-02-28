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
            action = [GET: "getAllProducts", POST: "getAllProductsByToken"]
        }

        "/cafe/getAll" {
            controller = "Cafe"
            action = "getAll"
        }

        "/cafe/findByLocation" {
            controller = "Cafe"
            action = "findByLocation"
        }

        "/cafe/menu/product/update" {
            controller = "Menu"
            action = [POST: "updateProduct"]
        }

        "/order/add" {
            controller = "Orderr"
            action = [POST: "create"]
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
            controller = "Orderr"
            action = [POST: "newOrder"]
        }

        "/orders" {
            controller = "Orderr"
            action = [GET: "getOrders"]
        }

        "/order/realize" {
            controller = "Orderr"
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
