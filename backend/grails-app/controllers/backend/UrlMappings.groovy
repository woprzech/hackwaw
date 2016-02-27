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
            action = "addProduct"
        }

        "/cafe/menu/update/description" {
            controller = "Menu"
            action = "updateDescription"
        }

        "/cafe/menu/remove" {
            controller = "Menu"
            action = "removeProduct"
        }

        "/account/login" {
            controller = "CafeAccount"
            action = "login"
        }

        "/account/logout" {
            controller = "CafeAccount"
            action = "logout"
        }

        "/account/create" {
            controller = "CafeAccount"
            action = "createAccount"
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

        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
