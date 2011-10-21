<html>
    <head>
        <title>Backbone MVC Demo</title>
        <meta name="layout" content="main" />
        <style>
        	.names { width: 300px; }
        	.hidden { display: none; }
        	.highlight { background-color: #33FF33; cursor: pointer; }
        </style>
    </head>
    <body>
		
		<div id="friendList">
			<h3>Your Friends</h3>
			<div class="names"></div>
		
			<hr />
			
			<form>
				
				<div class="label"><label>Add a new friend (name and favorite color!)</label>
				<input type="text" name="firstName" size="35" />
				<select name="favoriteColor" size="1">
					<option value="Red">Red</option>
					<option value="Blue">Blue</option>
					<option value="Green">Green</option>
				</select>
				
				<button type="button" name="save">Save!</button></div>
				
			</form>
		</div>
		
		<div id="friendDetail">
			<div class="detail">
				<h3 class="friendName"></h3>
				<div>
				<span>Their favorite color is: </span><span class="favoriteColor"></span></div>
			</div>
			
			<div>
			<a href="javascript:history.go(-1)">Back to Friends</a>
			</div>
		</div>
		
		<script type="text/template" id="name-display-template">
			<div class="name">{{firstName}}</div>
		</script>
		
		<g:javascript library="jquery-1.6.2.min" />
		<g:javascript library="underscore-min" />
		<g:javascript library="backbone-min" />
        <g:javascript library="application" />
		
        <g:javascript>
        
        $(function() {
        	new Medullan.Router.FriendsRouter();
        	Backbone.history.start();
        });
        
        </g:javascript>
		
		
    </body>
</html>
