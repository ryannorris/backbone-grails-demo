package grails.backbone.demo

class PersonController {

    def list = {
		
		List<Person> friends = Person.list()
		
		withFormat {
			json {
				render(contentType: 'application/json') {
					people = array {
						friends.each { p ->
							person(firstName: p.firstName, id: p.id, favoriteColor: p.favoriteColor)
						}
					}
				}
			}
			
			html {
				render(view: 'list', model: [ friends: friends ])
			}
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
