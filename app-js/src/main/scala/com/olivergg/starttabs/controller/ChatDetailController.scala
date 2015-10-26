package com.olivergg.starttabs.controller

import com.greencatsoft.angularjs.core.Scope
import com.greencatsoft.angularjs.extensions.StateParams
import com.greencatsoft.angularjs.{AbstractController, injectable}
import com.olivergg.starttabs.dto.Chat
import com.olivergg.starttabs.scalaservice.ChatsService

import scala.scalajs.js
import scala.scalajs.js.native

@injectable("ChatDetailCtrl")
class ChatDetailController(scope: ChatDetailScope, stateParams: StateParams) extends AbstractController[ChatDetailScope](scope) {

  println("init ChatDetailCtrl")
  val cid: String = stateParams("chatId").asInstanceOf[String]
  scope.chat = ChatsService.get(cid.toInt)

}

@js.native
trait ChatDetailScope extends Scope {
  var chat: Chat = native
}