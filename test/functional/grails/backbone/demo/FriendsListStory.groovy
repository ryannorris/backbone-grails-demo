package grails.backbone.demo;

import geb.Page

using "geb"

/*
 * How I see it is this...
 * 
 * Someone takes the story when work is starting, and defines each acceptance criteria as a "scenario."  So
 * as an example, if the story for this project is "as a user i must be able to add a friend to my friend list,"
 * then the acceptance criteria would be something like "must be able to specify their name" and "must be able to specify
 * their favorite color."
 * 
 * This example is a bit non-atomic, but it generally demonstrates how we would tie tests to vague acceptance criteria.
 */

// an unimplemented scenario

scenario "remove a friend from our friends list", {
	when "we go to the friends page"
	then "we can click on the link to remove a friend"
	then "we are asked to confirm removing our friend"
	and "if we approve it"
	then "the friend isn't on our list anymore"
}

// implemented scenarios

scenario "see our friends", {

	when "we go to the friends page", { to FriendListPage }

	then "we are on the friends page", { at FriendListPage }

	and "our friends are listed", {
		assert page.friendsList.children().size() == 4
	}
}

scenario "add a new friend", {

	when "we go to the friends page", { to FriendListPage }

	then "we are on the friends page", {
		at FriendListPage
		report "FriendListPage"
	}

	and "we want to add a new friend", {
		
		page.newFriendName << "Misha"
		// page.favoriteColor.click()
		page.favoriteColor = "Blue"
		page.saveButton.click()

		assert page.friendLink("Misha")
	}
}

scenario "view friend information", {
	when "we click on a friends name", {
		page.friendLink("Misha").click()
	}

	then "we go to the friend detail page", { at FriendDetailPage }

	and "their favorite color is displayed", {
		assert page.favoriteColor.text() == "Blue"
	}
}

class FriendListPage extends Page {
	static url = "person"
	static at = { title == "My Friends" }
	static content = {
		friendLink(wait: true, to: FriendDetailPage) { name ->
			$(".names .name", text: name)
		}

		friendsList { $(".names") }
		newFriendName { $("input[name='firstName']") }
		favoriteColor { $("select[name='favoriteColor']") }
		saveButton { $("button[name='save']") }
	}
}

class FriendDetailPage extends Page {
	static at = { title.startsWith("About Your Friend:") }
	static content = {
		friendName { $(".friendName") }
		favoriteColor { $(".favoriteColor") }
	}
}
