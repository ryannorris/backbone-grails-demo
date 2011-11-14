package grails.backbone.demo

class CompanyController {

    def list = { 
		withFormat {
			html {
				render(view: 'list')
			}
		}	
	}
}
