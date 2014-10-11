package com.olivergg.ristorantewatcher.controller

import com.greencatsoft.angularjs.controller.AbstractController
import com.greencatsoft.angularjs.scope.ScopeAware

object DashController extends AbstractController("DashCtrl") with ScopeAware {

  override def initialize() {
    println("init " + name)
  }
}

