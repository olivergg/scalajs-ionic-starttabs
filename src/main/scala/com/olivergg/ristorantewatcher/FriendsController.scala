package com.olivergg.ristorantewatcher

import scala.scalajs.js.Any.fromFunction0
import com.greencatsoft.angularjs.controller.Controller
import com.greencatsoft.angularjs.scope.Scope
import com.greencatsoft.angularjs.scope.ScopeAware
import com.greencatsoft.angularjs.controller.AbstractController

object FriendsController extends AbstractController("FriendsCtrl")  with FriendsServiceAware with ScopeAware {

  override type ScopeType = FriendsScope

  
  override def initialize(scope: ScopeType) {
    println(" init scope " + scope)
//    scope.friends = friendsService.all
    scope.dynamic.friends = () => friendsService.all

  }

  override def initialize() {
    println(" init " + name)
    super.initialize()
    println(currentScope )

  }
  
  abstract class FriendsScope extends Scope {
    var friends: Array[Friend]
  }
}