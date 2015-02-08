package com.olivergg.starttabs

import com.greencatsoft.angularjs.Config
import com.greencatsoft.angularjs.inject
import com.greencatsoft.angularjs.extensions.StateProvider
import com.greencatsoft.angularjs.extensions.UrlRouterProvider
import com.greencatsoft.angularjs.extensions.State
import com.greencatsoft.angularjs.extensions.View
/**
 * This object should be passed to the config method of an angular module in order to setup the states.
 */
object StateConfig extends Config {

  @inject
  var stateProvider: StateProvider = _
  @inject
  var urlRouterProvider: UrlRouterProvider = _

  override def initialize() {
    println("initialize StateConfig")
    // Ionic uses AngularUI Router which uses the concept of states
    // Learn more here: https://github.com/angular-ui/ui-router
    // Set up the various states which the app can be in.
    // Each state's controller can be found in controllers.js

    // setup an abstract state for the tabs directive
    stateProvider
      .state("tab", State(
        url = "/tab",
        isAbstract = true,
        templateUrl = "templates/tabs.html"
      ))

      // Each tab has its own nav history stack:
      .state("tab.dash", State(
        url = "/dash",
        views = Map("tab-dash" -> View("templates/tab-dash.html", "DashCtrl"))
      ))

      .state("tab.friends", State(
        url = "/friends",
        views = Map("tab-friends" -> View("templates/tab-friends.html", "FriendsCtrl"))
      ))

      .state("tab.chats", State(
        url = "/chats",
        views = Map("tab-chats" -> View("templates/tab-chats.html", "ChatsCtrl"))
      ))

      .state("tab.chat-detail", State(
        url = "/chats/:chatId",
        views = Map("tab-chats" -> View("templates/chat-detail.html", "ChatDetailCtrl"))
      ))

      .state("tab.friend-detail", State(
        url = s"/friend/:friendId",
        views = Map("tab-friends" -> View("templates/friend-detail.html", "FriendDetailCtrl"))
      ))

      .state("tab.account", State(
        url = "/account",
        views = Map("tab-account" -> View("templates/tab-account.html", "AccountCtrl"))
      ))

    // if none of the above states are matched, use this as the fallback
    urlRouterProvider.otherwise("/tab/dash")

  }
}

