package com.medullan.core

import grails.web.JSONBuilder;

class BootstrapTagLib {
	/**
	 * Renders a given object in the returned model out as JSON to the page.
	 * 
	 * Useful when bootstrapping data.
	 * 
	 * 
	 */
	def jsonify = { attrs ->
		def jsonClosure = {
			attrs['target']
		}
		
		JSONBuilder builder = new JSONBuilder()
		out << builder.build(jsonClosure).toString()
	}
}
