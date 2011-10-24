/**
 * 
 */
package com.medullan.core.test

import grails.converters.JSON;
import grails.test.ControllerUnitTestCase
import grails.web.JSONBuilder
import groovy.lang.MetaClass
import groovy.xml.StreamingMarkupBuilder

/**
 * @author rnorris
 *
 */
abstract class MedullanControllerUnitTestCase extends ControllerUnitTestCase {

	def renderers = [new JSONRenderer()]

	@Override
	protected void setUp() {
		super.setUp()
		
		controller.metaClass.render = { Map attrs, Closure closure ->
			renderers.each { renderer ->
				if(renderer.doesRender(attrs.contentType)) {
					controller.response.outputStream << renderer.doRender(closure)
				}
			}
		}
	}
}

interface Renderable {
	/**
	 * 
	 * @param contentType
	 * @return
	 */
	boolean doesRender(String contentType);
	/**
	 * 
	 * @param closure
	 * @return
	 */
	String doRender(Closure closure);
}

class JSONRenderer implements Renderable {
	boolean doesRender(String contentType) {
		"application/json".equals(contentType)
	}

	String doRender(Closure closure) {
		JSONBuilder builder = new JSONBuilder()
		JSON json = builder.build(closure)
		
		json.toString()
	}
}

class XMLRenderer implements Renderable {
	boolean doesRender(String contentType) {
		"application/xml".equals(contentType) || "text/xml".equals(contentType)
	}

	String doRender(Closure closure) {
		def builder = new StreamingMarkupBuilder()
		if (map["encoding"]) builder.encoding = map["encoding"]

		builder.bind(closure)
	}
}
