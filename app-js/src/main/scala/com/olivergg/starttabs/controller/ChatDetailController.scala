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

object ChatDetailController extends Controller {

  override val name = "ChatDetailCtrl"
  
  @inject
  var stateParams: StateParams = _
  
  
  override def initialize(scope: ScopeType) {
    println("init scope " + scope)
    val cid: String = stateParams("chatId").asInstanceOf[String]
    scope.chat = ChatsService.get(cid.toInt)
  }

  trait ScopeType extends Scope {
    var chat: Chat = native
  }
}