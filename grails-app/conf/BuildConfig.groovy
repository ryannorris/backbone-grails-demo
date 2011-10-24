grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
//grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.project.dependency.resolution = {
	// inherit Grails' default dependencies
	inherits("global") {
		// uncomment to disable ehcache
		// excludes 'ehcache'
	}
	log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
	repositories {
		grailsPlugins()
		grailsHome()
		grailsCentral()

		// uncomment the below to enable remote dependency resolution
		// from public Maven repositories
		mavenLocal()
		mavenCentral()
		mavenRepo "https://nexus.codehaus.org/content/repositories/snapshots"
		//mavenRepo "http://snapshots.repository.codehaus.org"
		//mavenRepo "http://repository.codehaus.org"
		//mavenRepo "http://download.java.net/maven/2/"
		//mavenRepo "http://repository.jboss.com/maven2/"
	}


	def gebVersion = "0.6.0"
	def seleniumVersion = "2.9.0"

	dependencies {
		test "org.codehaus.geb:geb-spock:$gebVersion"
		test "org.codehaus.geb:geb-easyb:$gebVersion"
		test("org.seleniumhq.selenium:selenium-htmlunit-driver:$seleniumVersion") { exclude "xml-apis" }
		test("org.seleniumhq.selenium:selenium-chrome-driver:$seleniumVersion")
		test("org.seleniumhq.selenium:selenium-firefox-driver:$seleniumVersion")
	}

	plugins {
		test ":spock:0.5-groovy-1.7"
		test ":geb:0.6.0"
	}
}
