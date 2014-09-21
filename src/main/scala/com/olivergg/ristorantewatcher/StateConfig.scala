package com.olivergg.ristorantewatcher

import com.olivergg.ionic.StateProviderAware
import com.olivergg.ionic.UrlRouterProviderAware
import scala.scalajs.js.Dynamic.{ literal => lit }
import com.olivergg.ionic.IState

object StateConfig extends StateProviderAware with UrlRouterProviderAware {

  override def initialize() {

    //TODO :
    //stateProvider.state("tab", config)
    stateProvider.state("tab", new State(
      url = "/tab",
      `abstract` = true,
      templateUrl = "templates/tabs.html"
    ))

  }
}

class State(url: String, `abstract`: Boolean, templateUrl: String) extends IState