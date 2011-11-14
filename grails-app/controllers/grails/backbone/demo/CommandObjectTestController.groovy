package grails.backbone.demo

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.ListUtils;
import org.codehaus.groovy.grails.validation.Validateable;

/**
 * This file demonstrates how you can leverage nested command objects in a complex JSON scenario
 * @author rnorris
 *
 */
class CommandObjectTestController {

    def save = {
		SomeDTO cmd = new SomeDTO(request.JSON)
		
		log.debug(cmd.foo)
		log.debug(cmd.nested.zoo)
		log.debug(cmd.moreNested[0].car)
		log.debug("got this far")
		
		if(! cmd.validate()) {
			log.error("could not validate! ${cmd.errors}")
		} 
	}
}

@Validateable
class SomeDTO {
	String foo
	String bar
	
	AnotherDTO nested = new AnotherDTO()
	
	List<AnotherDTO> moreNested
	
	static constraints = {
		moreNested(validator: { val, obj ->
			return val.every { it.validate() } ?: ['attributes.validation.failed']  
		})
	}
}

@Validateable
class AnotherDTO {
	String zoo
	String car
	
	static constraints = {
		zoo(blank: false)
	}
}

// { "foo": "Ryan", "bar": "Norris", "nested": { "zoo": "Southwicks", "car": "Audi" }, moreNested: [ { "zoo": "Yorks", "car": "Volvo" }, { "zoo": "National", "car": "Honda" }] }
