package grails.backbone.demo

class PersonController {

    def list = {
		
		withFormat {
			json {
				List<Person> shown = Person.list()
				render(contentType: 'application/json') {
					people = array {
						shown.each { p ->
							person(firstName: p.firstName, id: p.id, favoriteColor: p.favoriteColor)
						}
					}
				}
			}
			
			html { }
		}	
	}
	
	def save = {
		Person newPerson = new Person(request.JSON)
		newPerson.save(failOnError: true)
		
		withFormat {
			json {
				render(contentType: 'application/json') {
					firstName = newPerson.firstName
					id = newPerson.id
					favoriteColor = newPerson.favoriteColor
				}
			}
		}
	}
}
