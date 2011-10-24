package com.medullan.core

import grails.test.*
import grails.web.JSONBuilder

import com.medullan.core.BootstrapTagLib

class BootstrapTagLibTests extends TagLibUnitTestCase {
	StringWriter out
	
    protected void setUp() {
        super.setUp()
		
		out = new StringWriter();
		BootstrapTagLib.metaClass.out = out
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testJsonificationOfSingleValue() {
		// arrange
		def target = [ foo: "bar", zoo: [ car: "red" ]]
		
		// act
		BootstrapTagLib tagLib = new BootstrapTagLib()
		tagLib.jsonify(target: target)
		
		// assert
		assert out.toString() == '{"foo":"bar","zoo":{"car":"red"}}'
    }
}
