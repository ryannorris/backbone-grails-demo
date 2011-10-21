class UrlMappings {

	static mappings = {
		"/$controller/create" { action = [GET:"create"] }
		"/$controller/edit/$id" { action = [GET:"edit"] }
		"/$controller/$id" { action = [GET:"show", PUT:"update", DELETE:"delete"] }
		"/$controller" { action = [GET:"list", POST:"save"] }

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
