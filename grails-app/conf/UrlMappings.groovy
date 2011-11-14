class UrlMappings {

	static mappings = {
		"/$controller/create"(parseRequest: true) { action = [GET:"create"] }
		"/$controller/edit/$id"(parseRequest: true) { action = [GET:"edit"] }
		"/$controller/$id"(parseRequest: true) { action = [GET:"show", PUT:"update", DELETE:"delete"] }
		"/$controller"(parseRequest: true) { action = [GET:"list", POST:"save"] }

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
