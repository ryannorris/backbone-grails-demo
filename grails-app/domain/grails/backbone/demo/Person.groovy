package grails.backbone.demo

class Person {
	
	String firstName
	String lastName
	String favoriteColor

    static constraints = {
		lastName(nullable: true)
    }
}
