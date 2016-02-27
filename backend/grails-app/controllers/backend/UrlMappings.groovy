package backend

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

        "/cafe/getAll" {
            controller = "Cafe"
            action = "getAll"
        }

        "/cafe/findByLocation" {
            controller = "Cafe"
            action = "findByLocation"
        }

        "/cafe/getLocation" {
            controller = "Cafe"
            action = "getLocation"
        }

        "/cafe/menu/addProduct" {
            controller = "Menu"
            action = "addProduct"
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

        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
