package grails.backbone.demo

import geb.Page

using "geb"

scenario "edit two fields on a page", {
	when "i go to the company page", {
		to CompanyPage
		at CompanyPage
	}
	and "i enter the first name", {
		page.fieldOne.click()
		page.fieldOne << "Ryan"
	}
	and "i enter the last name", {
		page.fieldTwo.click()
		page.fieldTwo << "Norris"
		
	}
	then "the names have values", {
		assert page.fieldOne.value("Ryan")
		assert page.fieldTwo.value("Norris")
	}
}

class CompanyPage extends Page {
	static url = "company"
	static at = { title == "Company" }
	static content = {
		fieldOne { $("input", name: "field1") }
		fieldTwo { $("input", name: "field2") }
	}
}