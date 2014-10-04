package com.olivergg.ristorantewatcher

import scala.scalajs.js

import com.greencatsoft.angularjs.controller.AbstractController
import com.greencatsoft.angularjs.scope.Scope
import com.greencatsoft.angularjs.scope.ScopeAware
import com.olivergg.ionic.StateParamsAware

object FriendDetailController extends AbstractController("FriendDetailCtrl") with ScopeAware with StateParamsAware {

  override def initialize(scope: ScopeType) {
    println("init scope " + scope)
    val fid: String = stateParams.dynamic.friendId.asInstanceOf[js.String]
    scope.friend = FriendsService.get(fid.toInt)
  }

  trait ScopeType extends Scope {
    var friend: Friend
  }
}