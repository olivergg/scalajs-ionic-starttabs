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
import com.olivergg.starttabs.scalaservice.ChatsService
import com.olivergg.starttabs.dto.Chat
import com.greencatsoft.angularjs.AbstractController

@injectable("ChatDetailCtrl")
class ChatDetailController(scope: ChatDetailScope, stateParams: StateParams) extends AbstractController[ChatDetailScope](scope) {

  println("init ChatDetailCtrl")
  val cid: String = stateParams("chatId").asInstanceOf[String]
  scope.chat = ChatsService.get(cid.toInt)

}

trait ChatDetailScope extends Scope {
  var chat: Chat = native
}