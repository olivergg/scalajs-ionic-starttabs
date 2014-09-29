package com.olivergg.ristorantewatcher

import com.greencatsoft.angularjs.controller.AbstractController
import com.greencatsoft.angularjs.http.HttpServiceAware
import com.greencatsoft.angularjs.location.LocationAware
import com.greencatsoft.angularjs.scope.ScopeAware

object AccountController extends AbstractController("AccountCtrl") with ScopeAware {

  override def initialize() {
    println("init " + name)
    super.initialize()
    //TODO do something
  }
}

