package com.olivergg.starttabs.controller

import com.greencatsoft.angularjs.core.Scope
import com.greencatsoft.angularjs.{AbstractController, injectable}
import com.olivergg.starttabs.dto.Chat
import com.olivergg.starttabs.scalaservice.ChatsService

import scala.scalajs.js
import scala.scalajs.js.JSConverters.array2JSRichGenTrav

@injectable("ChatsCtrl")
class ChatsController(scope: ChatsScopeType) extends AbstractController[ChatsScopeType](scope) {

  println("init ChatsCtrl")
  scope.chats = ChatsService.all().toJSArray
  // removing elements will only impact scope.chats array, not the underlying initial array defined in ChatsService.
  scope.remove = { chat: Chat => scope.chats.splice(scope.chats.indexOf(chat), 1) }

}

@js.native
trait ChatsScopeType extends Scope {
  var chats: js.Array[Chat] = js.native
  var remove: js.Function1[Chat, _] = js.native
}