package com.olivergg.starttabs.controller

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportAll
import com.olivergg.starttabs.dto.Friend
import com.olivergg.starttabs.scalaservice.FriendsService
import com.olivergg.uirouter.StateParams
import com.greencatsoft.angularjs.core.Scope
import com.greencatsoft.angularjs.inject
import com.greencatsoft.angularjs.Controller

object FriendDetailController extends Controller {

  override val name = "FriendDetailCtrl"
  
  @inject
  var stateParams: StateParams = _
  
  
  override def initialize(scope: ScopeType) {
    println("init scope " + scope)
    val fid: String = stateParams("friendId").asInstanceOf[js.String]
    scope.friend = FriendsService.get(fid.toInt)
  }

  trait ScopeType extends Scope {
    var friend: Friend
  }
}