package com.olivergg.ristorantewatcher

import com.olivergg.ionic.StateProviderAware
import com.olivergg.ionic.UrlRouterProviderAware
import scala.scalajs.js.Dynamic.{ literal => lit }
import com.olivergg.ionic.IState
import com.olivergg.ionic.State
import com.olivergg.ionic.View

object StateConfig extends StateProviderAware with UrlRouterProviderAware {

  override def initialize() {
    println("initialize StateConfig")
    stateProvider
      .state("tab", State(
        url = "/tab",
        isAbstract = true,
        templateUrl = "templates/tabs.html"
      ))

      .state("tab.dash", State(
        url = "/dash",
        views = Map("tab-dash" -> View("templates/tab-dash.html", "DashCtrl"))
      ))

      .state("tab.friends", State(
        url = "/friends",
        views = Map("tab-friends" -> View("templates/tab-friends.html", "FriendsCtrl"))
      ))

      .state("tab.friends-detail", State(
        url = "/friends/:friendId",
        views = Map("tab-friends" -> View("templates/friend-detail.html", "FriendDetailCtrl"))
      ))

      .state("tab.account", State(
        url = "/account",
        views = Map("tab-account" -> View("templates/tab-account.html", "AccountCtrl"))
      ))

    urlRouterProvider.otherwise("/tab/dash")

    //TODO : more states !

  }
}

