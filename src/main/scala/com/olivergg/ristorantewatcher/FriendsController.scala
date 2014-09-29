package com.olivergg.ristorantewatcher

import scala.scalajs.js.Any.fromFunction0
import com.greencatsoft.angularjs.controller.Controller
import com.greencatsoft.angularjs.scope.Scope
import com.greencatsoft.angularjs.scope.ScopeAware
import com.greencatsoft.angularjs.controller.AbstractController
import scala.scalajs.js

object FriendsController extends AbstractController("FriendsCtrl") with ScopeAware {

  override def initialize(scope: ScopeType) {
    println("init scope " + scope)
    scope.friends = FriendsService.all
  }

  trait ScopeType extends Scope {
    var friends: js.Array[Friend]
  }
}