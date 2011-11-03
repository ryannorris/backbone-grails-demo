dataSource {
	pooled = true
	driverClassName = "org.hsqldb.jdbcDriver"
	username = "sa"
	password = ""
}
hibernate {
	cache.use_second_level_cache = true
	cache.use_query_cache = true
	cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
	show_sql = false
}
// environment specific settings
environments {
	development {
		dataSource {
			dbCreate = "none" // one of 'create', 'create-drop','update'
			url = "jdbc:mysql://localhost:3306/migrations-dev"
			username = "root"
			password = ""
			driverClassName = "com.mysql.jdbc.Driver"
			dbCreate = "create-drop"
		}
	}

	test {
		dataSource {
			driverClassName = "com.mysql.jdbc.Driver"
			url = "jdbc:mysql://localhost:3306/migrations-test"
			username = "root"
			dbCreate = "create-drop"
			password = ""
		}
	}
	
	production {
		dataSource {
			dbCreate = "update"
			url = "jdbc:hsqldb:file:prodDb;shutdown=true"
		}
	}
}
