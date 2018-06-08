class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/AHP/matrixGeneration")
        "500"(view:'/error')



        "/ping"(controller: "ping") {
            action = [GET: "ping"]
        }

        "/"(view:"/index")
        "500"(view:'/error')
//
//        "/create/hierarchy"(controller: "create", parseRequest: true) {
//            action = [POST: "getPairwaseMatrices"]
//        }

//        "/create/matrix"(controller: "create", parseRequest: true) {
//            action = [GET: "generateMatrix"]
//        }


	}
}
