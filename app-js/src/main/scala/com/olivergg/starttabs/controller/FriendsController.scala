package com.olivergg.starttabs.controller

import scala.scalajs.js
import scala.scalajs.js.JSConverters.array2JSRichGenTrav
import scala.scalajs.js.annotation.JSExportAll
import com.greencatsoft.angularjs.Controller
import com.greencatsoft.angularjs.core.Scope
import com.greencatsoft.angularjs.injectable
import com.olivergg.starttabs.dto.Friend
import com.olivergg.starttabs.scalaservice.FriendsService
import com.greencatsoft.angularjs.AbstractController

@injectable("FriendsCtrl")
class FriendsController(scope: FriendsScope) extends AbstractController[FriendsScope](scope) {

  println("init FriendsCtrl")
  scope.friends = FriendsService.all.toJSArray

}

trait FriendsScope extends Scope {
  var friends: js.Array[Friend] = js.native
}