package com.olivergg.ristorantewatcher.controller

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportAll

import com.greencatsoft.angularjs.controller.AbstractController
import com.greencatsoft.angularjs.http.HttpServiceAware
import com.greencatsoft.angularjs.scope.Scope
import com.greencatsoft.angularjs.scope.ScopeAware
import com.olivergg.ionic.StateParamsAware
import com.olivergg.ristorantewatcher.dto.Friend
import com.olivergg.ristorantewatcher.scalaservice.FriendsService

object FriendDetailController extends AbstractController("FriendDetailCtrl") with ScopeAware with StateParamsAware with HttpServiceAware {

  override def initialize(scope: ScopeType) {
    println("init scope " + scope)
    val fid: String = stateParams.dynamic.friendId.asInstanceOf[js.String]
    scope.friend = FriendsService.get(fid.toInt)
  }

  trait ScopeType extends Scope {
    var friend: Friend
  }
}