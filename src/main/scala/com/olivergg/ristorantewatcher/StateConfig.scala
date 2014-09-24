package com.olivergg.ristorantewatcher

import com.olivergg.ionic.StateProviderAware
import com.olivergg.ionic.UrlRouterProviderAware
import scala.scalajs.js.Dynamic.{ literal => lit }
import com.olivergg.ionic.IState
import com.olivergg.ionic.State
import com.olivergg.ionic.View

object StateConfig extends StateProviderAware with UrlRouterProviderAware {

  override def initialize() {

    stateProvider
      .state("tab", new State(
        someurl = "/tab",
        isAbstract = true,
        someTemplateUrl = "templates/tabs.html"
      ))

      .state("tab.dash", new State(
        someurl = "/dash",
        someViews = Map("tab-dash" -> new View("templates/tab-dash.html", "DashCtrl"))
      ))
      
      //TODO : more states !

  }
}

