package grails.backbone.demo

class Person {
	
	String firstName
	String lastName
	String middleInitial
	String favoriteColor

    static constraints = {
		lastName(nullable: true)
		middleInitial(nullable: true)
    }
}
