package com.olivergg.starttabs.controller

import com.greencatsoft.angularjs.core.Scope
import com.greencatsoft.angularjs.extensions.StateParams
import com.greencatsoft.angularjs.{AbstractController, injectable}
import com.olivergg.starttabs.dto.Friend
import com.olivergg.starttabs.scalaservice.FriendsService

import scala.scalajs.js
import scala.scalajs.js.native

@injectable("FriendDetailCtrl")
class FriendDetailController(scope: FriendDetailScope, stateParams: StateParams) extends AbstractController[FriendDetailScope](scope) {

  println("init FriendDetailCtrl")
  val fid: String = stateParams("friendId").asInstanceOf[String]
  scope.friend = FriendsService.get(fid.toInt)

}

@js.native
trait FriendDetailScope extends Scope {
  var friend: Friend = native
}