package com.olivergg.starttabs.controller

import scala.scalajs.js
import scala.scalajs.js.JSConverters.array2JSRichGenTrav
import scala.scalajs.js.annotation.JSExportAll
import com.greencatsoft.angularjs.Controller
import com.greencatsoft.angularjs.core.Scope
import com.greencatsoft.angularjs.injectable
import com.olivergg.starttabs.dto.Friend
import com.olivergg.starttabs.scalaservice.FriendsService
import com.olivergg.starttabs.scalaservice.ChatsService
import com.olivergg.starttabs.dto.Chat
import com.greencatsoft.angularjs.AbstractController

@injectable("ChatsCtrl")
class ChatsController(scope: ChatsScopeType) extends AbstractController[ChatsScopeType](scope) {

  println("init ChatsCtrl")
  scope.chats = ChatsService.all().toJSArray
  // removing elements will only impact scope.chats array, not the underlying initial array defined in ChatsService.
  scope.remove = { chat: Chat => scope.chats.splice(scope.chats.indexOf(chat), 1) }

}

trait ChatsScopeType extends Scope {
  var chats: js.Array[Chat] = js.native
  var remove: js.Function1[Chat, _] = js.native
}