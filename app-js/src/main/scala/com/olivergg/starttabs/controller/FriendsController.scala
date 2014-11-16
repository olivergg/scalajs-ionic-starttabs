package com.olivergg.starttabs.controller

import scala.scalajs.js
import scala.scalajs.js.JSConverters.array2JSRichGenTrav
import scala.scalajs.js.annotation.JSExportAll

import com.greencatsoft.angularjs.Controller
import com.greencatsoft.angularjs.core.Scope
import com.greencatsoft.angularjs.injectable
import com.olivergg.starttabs.dto.Friend
import com.olivergg.starttabs.scalaservice.FriendsService

object FriendsController extends Controller {
  
  override val name = "FriendsCtrl"
  
  override def initialize(scope: ScopeType) {
    println("init scope " + scope)
    scope.friends = FriendsService.all.toJSArray
  }

  trait ScopeType extends Scope {
    var friends: js.Array[Friend]
  }
}