package com.olivergg.ristorantewatcher

import scala.scalajs.js
import scala.scalajs.js.Any.fromArray
import com.greencatsoft.angularjs.controller.AbstractController
import com.greencatsoft.angularjs.scope.Scope
import com.greencatsoft.angularjs.scope.ScopeAware
import com.greencatsoft.angularjs.http.HttpServiceAware
import com.olivergg.ristorantewatcher.dto.Friend

object FriendsController extends AbstractController("FriendsCtrl") with ScopeAware with HttpServiceAware {

  override def initialize(scope: ScopeType) {
    println("init scope " + scope)
    scope.friends = FriendsService().all
  }

  trait ScopeType extends Scope {
    var friends: js.Array[Friend]
  }
}