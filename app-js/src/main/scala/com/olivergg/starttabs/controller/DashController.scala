package com.olivergg.starttabs.controller

import com.greencatsoft.angularjs.Controller

object DashController extends Controller {

  override val name = "DashCtrl"
  
  override def initialize() {
    println("init " + name)
  }
}

