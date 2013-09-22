class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		//login
		"/"(controller:"login", action:"auth")
		"/logout"(controller:"logout", action:"index")

		"500"(view:'/error')
		"404"(view:"/404")

		//partners
		"/partners"(controller:"partner", action:"list")
	}
}
