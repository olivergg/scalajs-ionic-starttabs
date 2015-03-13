package com.olivergg.starttabs.controller

import com.greencatsoft.angularjs.Controller
import com.greencatsoft.angularjs.core.Scope
import com.greencatsoft.angularjs.injectable
import com.greencatsoft.angularjs.AbstractController

@injectable("DashCtrl")
class DashController(scope:Scope) extends AbstractController[Scope](scope) {

  println("init DashController")
}

