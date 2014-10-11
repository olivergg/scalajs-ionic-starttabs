package com.olivergg.ristorantewatcher

import scala.scalajs.js.Any.fromArray
import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport
import com.greencatsoft.angularjs.Module
import com.greencatsoft.angularjs.angular
import com.olivergg.ristorantewatcher.controller.DashController
import com.olivergg.ristorantewatcher.controller.FriendsController
import com.olivergg.ristorantewatcher.controller.FriendDetailController
import com.olivergg.ristorantewatcher.controller.AccountController

/**
 * The main entry point of the application. The main method is called from the index.html.
 */
@JSExport
object RistoranteWatcherApp extends JSApp {
  override def main(): Unit = {
    println("start main")

    // angular.module is a global place for creating, registering and retrieving Angular modules
    // 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
    // the 2nd parameter is an array of 'requires'
    // 'starter.controllers' is defined in controllers below
    val app = angular.module("starter", Array("ionic", "starter.controllers"))
    Module(app)
      .run(PlatformInitializer)
      .config(StateConfig)

    val controllers = angular.module("starter.controllers", Array.empty[String])
    Module(controllers)
      .controller(DashController)
      .controller(FriendsController)
      .controller(FriendDetailController)
      .controller(AccountController)
    
      println("end main")
      
      
  }
}
