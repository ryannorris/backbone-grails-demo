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
			url = "jdbc:mysql://localhost:3306/migration-db"
            username = "root"
            password = ""
            driverClassName = "com.mysql.jdbc.Driver"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:hsqldb:mem:testDb"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            url = "jdbc:hsqldb:file:prodDb;shutdown=true"
        }
    }
}
