class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				id matches:/\d+/
			}
		}

		//login
		"/"(controller:"login", action:"auth")
		"/logout"(controller:"logout", action:"index")

		"500"(view:'/error')
		"404"(view:"/404")

		//partners
		"/partners"(controller:"partner", action:"list")
		//error here if i uncomment this line i wont be able to access to create partner action and 405 error if i try to update a partner
		//"/partner/$id"(controller:"partner", action:"show")
		"/partner/status/$id?"(controller:"partner", action:"changeStatus")

		//users
		"/users"(controller:"user", action:"list")

		//fees
		"/fee/$id"(controller:"fee", action:"list")
	}
}
