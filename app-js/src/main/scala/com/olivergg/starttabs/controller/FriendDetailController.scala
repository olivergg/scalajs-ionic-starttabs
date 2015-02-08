package com.olivergg.starttabs.controller

import scala.scalajs.js.annotation.JSBracketAccess
import scala.scalajs.js.annotation.JSExportAll
import scala.scalajs.js.native
import com.greencatsoft.angularjs.Controller
import com.greencatsoft.angularjs.core.Scope
import com.greencatsoft.angularjs.inject
import com.greencatsoft.angularjs.injectable
import com.olivergg.starttabs.dto.Friend
import com.olivergg.starttabs.scalaservice.FriendsService
import com.greencatsoft.angularjs.extensions.StateParams

object FriendDetailController extends Controller {

  override val name = "FriendDetailCtrl"
  
  @inject
  var stateParams: StateParams = _
  
  
  override def initialize(scope: ScopeType) {
    println("init scope " + scope)
    val fid: String = stateParams("friendId").asInstanceOf[String]
    scope.friend = FriendsService.get(fid.toInt)
  }

  trait ScopeType extends Scope {
    var friend: Friend = native
  }
}