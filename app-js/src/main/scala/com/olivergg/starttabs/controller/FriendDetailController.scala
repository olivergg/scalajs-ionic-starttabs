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
import com.greencatsoft.angularjs.AbstractController

@injectable("FriendDetailCtrl")
class FriendDetailController(scope: FriendDetailScope, stateParams: StateParams) extends AbstractController[FriendDetailScope](scope) {

  println("init FriendDetailCtrl")
  val fid: String = stateParams("friendId").asInstanceOf[String]
  scope.friend = FriendsService.get(fid.toInt)

}

trait FriendDetailScope extends Scope {
  var friend: Friend = native
}