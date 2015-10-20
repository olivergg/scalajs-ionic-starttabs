package com.olivergg.starttabs.controller

import com.greencatsoft.angularjs.core.Scope
import com.greencatsoft.angularjs.{AbstractController, injectable}
import com.olivergg.starttabs.dto.Friend
import com.olivergg.starttabs.scalaservice.FriendsService

import scala.scalajs.js
import scala.scalajs.js.JSConverters.array2JSRichGenTrav

@injectable("FriendsCtrl")
class FriendsController(scope: FriendsScope) extends AbstractController[FriendsScope](scope) {

  println("init FriendsCtrl")
  scope.friends = FriendsService.all().toJSArray

}

@js.native
trait FriendsScope extends Scope {
  var friends: js.Array[Friend] = js.native
}