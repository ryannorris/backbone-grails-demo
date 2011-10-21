import grails.backbone.demo.Person

class BootStrap {

    def init = { servletContext ->
		new Person(firstName: 'Ryan', favoriteColor: "Black").save()
		new Person(firstName: 'Liz', favoriteColor: "Blue").save()
		new Person(firstName: 'Andrea', favoriteColor: "Red, Pink, and Green").save()
		new Person(firstName: 'Katie', favoriteColor: "Blue").save()
    }
	
    def destroy = {
    }
}
