RUNNING
=======

Just run-app.  It uses an in-memory database.

TESTING
=======

I've set up geb to test everything functionally.  The grails command to run the tests in firefox is:

grails -Dgeb.env=firefox test-app functional:easyb

Working on getting it set up in HtmlUnit, but this will do for now to show how it works.