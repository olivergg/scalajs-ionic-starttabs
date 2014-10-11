package com.olivergg.ristorantewatcher.controller

import scala.scalajs.js
import scala.scalajs.js.Any.fromArray
import scala.scalajs.js.annotation.JSExportAll

import com.greencatsoft.angularjs.controller.AbstractController
import com.greencatsoft.angularjs.scope.Scope
import com.greencatsoft.angularjs.scope.ScopeAware
import com.olivergg.ristorantewatcher.dto.Friend
import com.olivergg.ristorantewatcher.scalaservice.FriendsService

object FriendsController extends AbstractController("FriendsCtrl") with ScopeAware {

  override def initialize(scope: ScopeType) {
    println("init scope " + scope)
    scope.friends = FriendsService.all
  }

  trait ScopeType extends Scope {
    var friends: js.Array[Friend]
  }
}