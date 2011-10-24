RUNNING
=======

== Installing the Database ==

We're using the database migrations plugin to handle movement between database versions.  To patch your database (or install for the first time):

   grails dbm-update
   grails dbm-changelog-sync

TESTING
=======

I've set up geb to test everything functionally.  The grails command to run the tests in firefox is:

grails -Dgeb.env=firefox test-app functional:easyb

Working on getting it set up in HtmlUnit, but this will do for now to show how it works.

TRACKING DATABASE CHANGES
=========================

If you make changes to the database, you should amend the changelog with:

   grails dbm-gorm-diff <filename> --add
   
Where <filename> is a prescribed name that clearly indicates the reason for the change (this may be related
to a user story or task).  You can omit both parameters and the changelog will be written to standard out.

You can apply your changes by performing

   grails dbm-update
   grails dbm-changelog-sync
   
When your changes are satisfactory, you can merge your diff file into the main changelog.xml file.