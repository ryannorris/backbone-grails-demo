package grails.backbone.demo

import grails.web.JSONBuilder;

class BootstrapTagLib {
	/**
	 * Renders a given object in the returned model out as JSON to the page.
	 * 
	 * Useful when bootstrapping data.
	 * 
	 * 
	 */
	def jsonify = { attrs, body ->
		def jsonClosure = {
			attrs['target']
		}
		
		JSONBuilder builder = new JSONBuilder()
		out << builder.build(jsonClosure).toString()
	}
}
