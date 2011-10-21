<html>
    <head>
        <title>Backbone MVC Demo</title>
        <meta name="layout" content="main" />
        <g:javascript>
        
        $(function() {
        	var people = new Medullan.Model.People();
        	var personView = new Medullan.View.PersonView({ collection: people });
        	
        	people.fetch();
        });
        
        </g:javascript>
    </head>
    <body>
		
		<div id="root">
			<div class="names">
			
			</div>
		
			<form>
				
				<div class="label"><label for="firstName">First Name</label>
				<input type="text" name="firstName" size="35" /></div>
				
				<button type="button">Save!</button>
				
			</form>
		</div>
    </body>
</html>
