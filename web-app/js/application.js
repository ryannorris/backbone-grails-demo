var Medullan = {
	Model : {},
	View : {},
	Router : {}
};

// fix the _.template method so that processing instructions don't conflict with
// GSP
_.templateSettings = {
	interpolate : /\{\{(.+?)\}\}/g
};

Medullan.Router = (function() {

	/*
	 * we create one router to manage history and navigation
	 */
	var friendsRouter = Backbone.Router.extend({

		routes : {
			"" : "index",
			"friend/:id" : "friendDetail"
		},

		/*
		 * I'm instantiating my parent views in the constructor, as well as the
		 * colections/models that manage the state of the application
		 */
		initialize : function() {
			this.friends = new Medullan.Model.People();
			this.friendList = new Medullan.View.FriendListView({
				collection : this.friends
			});
			this.friendList.hide();

			this.selectedFriend = new Medullan.Model.Person();
			this.friendDetailView = new Medullan.View.FriendDetailView({
				model : this.selectedFriend
			});
			this.friendDetailView.hide();
		},

		// maps to /
		index : function() {

			// when coming into this state, hide the detail view and show the
			// list view
			this.friendDetailView.hide();
			this.friendList.show();

			var self = this;

			// when a person is selected in the person list, show that person's
			// information
			this.friendList.bind("personSelected", function(e) {
				self.navigate("friend/" + e.person.id, true);
			});

			// load the person data from the server, typically done from a
			// bootstrap
			this.friends.reset(window.friendsBootstrap);
		},

		// maps to friend/friendId (friend/3)
		friendDetail : function(friendId) {

			// here's where we demonstrate how updating model attributes drive
			// the re-rendering of the model. We also save ourselves a roundrtip
			// by loading
			// right from the current client state
			this.selectedFriend.set(this.friends.get(friendId).toJSON());
			
			// change the window title
			document.title = "About Your Friend: " + this.selectedFriend.get('firstName');

			// just like with "/", now toggle to hide the list and show the
			// detail
			this.friendList.hide();
			this.friendDetailView.show();
		}

	});

	return {
		FriendsRouter : friendsRouter
	};

})();

Medullan.Model = (function() {

	var person = Backbone.Model.extend({});

	var people = Backbone.Collection.extend({
		model : person,
		url : "person",

		// we need to implement this due to impedence issues between grails and
		// backbone
		// as far as collection semantics are concerned
		parse : function(response) {
			return response.people;
		}
	});

	return {
		Person : person,
		People : people
	};

})();

Medullan.View = (function() {

	// i set up an inheritance model to enforce DRY principles
	// basicControl is a base class, and is effectively no instantiable from
	// outside the Medullan.View
	// namespace
	var basicControl = Backbone.View.extend({
		show : function() {
			$(this.el).removeClass("hidden");
		},

		hide : function() {
			$(this.el).addClass("hidden");
		}
	});

	var nameView = basicControl.extend({
		template : _.template($("#name-display-template").html()),

		events : {
			"click" : "name_onClick",
			"mouseover" : "name_onMouseover",
			"mouseout" : "name_onMouseout"
		},

		initialize : function() {
			_.bindAll(this, "render", "name_onClick", "name_onMouseover",
					"name_onMouseout");
			_.extend(this, Backbone.Events);

			this.model.bind("change", this.render);
		},

		render : function() {
			var name = this.template(this.model.toJSON());
			$(this.el).append(name);

			// i always return "this" from render so that if the state of the
			// render
			// is managed internal to the object, i can always get this from the
			// "el"
			// property
			return this;
		},

		name_onClick : function() {
			this.trigger("clicked", {
				data : this.model
			});
		},

		name_onMouseover : function() {
			$(this.el).toggleClass("highlight");
		},

		name_onMouseout : function() {
			$(this.el).toggleClass("highlight");
		}
	});

	var friendDetail = basicControl.extend({
		el : "#friendDetail",

		initialize : function() {
			_.bindAll(this, "render");
			// when the underlying model changes (e.g. old.set(new.toJSON())) we
			// re-render
			this.model.bind("change", this.render);
		},

		render : function() {
			// here is where we bind data into the DOM for friend detail
			$(".friendName").text(this.model.get("firstName"));
			$(".favoriteColor").text(this.model.get("favoriteColor"));
			return this;
		}
	});

	var friendList = basicControl.extend({
		el : "#friendList",

		events : {
			"click button[name='save']" : "button_onClick"
		},

		initialize : function() {
			_.bindAll(this, "render", "button_onClick", "addFriend",
					"renderOne", "nameClicked");
			_.extend(this, Backbone.Events);

			// we bind two methods to the collection - one to deal with the
			// addition of ONE friend
			this.collection.bind("add", this.addFriend);

			// ...and this one when we change the whole collection
			this.collection.bind("all", this.render);
		},

		addFriend : function(model) {
			this.renderOne(model);

			$("input[name='firstName']").val('');
			$("input[name='favoriteColor']").val('');
		},

		render : function() {
			var self = this;

			// when the entire collection changes, we re-render ALL of the
			// models in the backing collection
			$(".names").empty(); // first, empty the container node

			this.collection.each(function(person) {
				self.renderOne(person); // delegate to a convenience method
			});
		},

		renderOne : function(person) { // render one person at a time
			var theName = new nameView({
				model : person
			});

			// when the name is clicked, fire an event so parent views (this
			// view)
			// can respond to it with some desired behavior
			theName.bind("clicked", this.nameClicked);

			// this is where we actually put the element into the DOM
			$(".names").append(theName.render().el);
		},

		nameClicked : function(e) {
			this.trigger("personSelected", {
				person : e.data
			});
		},

		button_onClick : function() {
			// modify the underlying collection with a new person
			// you could argue that this should be the job of the router
			this.collection.create({
				firstName : $("input[name='firstName']").val(),
				favoriteColor : $("select[name='favoriteColor']").val()
			});
		}
	});

	return {
		FriendListView : friendList,
		FriendDetailView : friendDetail
	};
})();