package backend

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

        "/helloWorld"(controller: "UserController") {
            action = [GET: "show"]
        }
        "500"(view: '/error')
        "404"(view: '/notFound')

    }
}
