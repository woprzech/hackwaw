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

        "/cafes" {
            controller = "Cafe"
            action = "getAll"
        }

        "/cafe/findByLocation" {
            controller = "Cafe"
            action = "findByLocation"
        }

        "/cafe/menu/product/update" {
            controller = "CafeAccount"
            action = [POST: "updateProduct"]
        }

        "/order/add" {
            controller = "CafeOrder"
            action = [POST: "create"]
        }

        "/cafe/menu/product/remove" {
            controller = "CafeAccount"
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
            action = [GET: "getOrders", POST: "getOrders"]
        }

        "/order/realize" {
            controller = "CafeOrder"
            action = [POST: "remOrder"]
        }

        "/categories" {
            controller = "Category"
            action = [GET: "getAll"]
        }

        "/registration" {
            controller = "Registration"
            action = [POST: "register"]
        }

        "/test/createData" {
            controller = "Test"
            action = [GET: "create"]
        }

        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
