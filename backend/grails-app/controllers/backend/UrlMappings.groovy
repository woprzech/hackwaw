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

        "/cafe/menu" {
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
            controller = "Account"
            action = [POST: "login"]
        }

        "/account/logout" {
            controller = "Account"
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

        "/admin/cafe/add" {
            controller = "AdminAccount"
            action = [POST: "createCafe"]
        }

        "/admin/cafe/update" {
            controller = "AdminAccount"
            action = [POST: "updateCafeData"]
        }

        "/admin/cafe/remove" {
            controller = "AdminAccount"
            action = [POST: "removeCafe"]
        }

        "/account/change/menuFlag" {
            controller = "CafeAccount"
            action = [POST: "changeMenuFlag"]
        }

        "/account/change/password" {
            controller = "CafeAccount"
            action = [POST: "changeMenuFlag"]
        }


        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
