package grails.backbone.demo

import grails.converters.JSON
import grails.test.*

import com.medullan.core.test.MedullanControllerUnitTestCase

class PersonControllerTests extends MedullanControllerUnitTestCase {
	protected void setUp() {
		super.setUp()
	}

	protected void tearDown() {
		super.tearDown()
	}

	void testWhenWeRetrieveWeGetEveryoneAsJSON() {
		// arrange
		mockDomain(Person, [
			new Person(firstName: "Ryan", favoriteColor: "Blue"),
			new Person(firstName: "Liz", favoriteColor: "Red")
		])

		mockRequest.format = "json"

		// act

		controller.list()

		// assert

		def result = JSON.parse(mockResponse.contentAsString)
		assert result.people.size() == 2
		assert result.people*.firstName == ['Ryan', 'Liz']
	}


	void testWhenWeRetrieveWeGetEveryoneAsGSP() {
		// arrange
		mockDomain(Person, [
			new Person(firstName: "Ryan", favoriteColor: "Blue"),
			new Person(firstName: "Liz", favoriteColor: "Red")
		])

		mockRequest.format = "html"

		// act

		controller.list()

		// assert

		assert controller.modelAndView.viewName == "list"
		assert controller.modelAndView.model.friends*.firstName == ['Ryan', 'Liz']
	}
	
	void testWhenWeSaveNewPeopleThatWeSendTheSavedPersonBackInTheResponse() {
		// arrange
		
		mockDomain(Person)
		
		mockRequest.format = "json"
		mockRequest.applicationType = "application/json"
		
		controller.request.content = '{ "firstName": "Ryan", "favoriteColor": "Red" }'
		
		// act
		controller.save()
		
		// assert
		def result = JSON.parse(mockResponse.contentAsString)
		assert result.firstName == "Ryan"
		assert result.favoriteColor == "Red"
		assert result.id == 1
	}
}
