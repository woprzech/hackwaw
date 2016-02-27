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
