package com.olivergg.ristorantewatcher

import scala.scalajs.js.JSConverters.array2JSRichGenTrav
import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport
import com.greencatsoft.angularjs.Module
import com.olivergg.ristorantewatcher.controller.DashController
import com.olivergg.ristorantewatcher.controller.FriendsController
import com.olivergg.ristorantewatcher.controller.FriendDetailController
import com.olivergg.ristorantewatcher.controller.AccountController
import com.greencatsoft.angularjs.Angular
import com.olivergg.ristorantewatcher.service.BetterHttpServiceFactory

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
    val module = Angular.module("starter", Array("ionic", "starter.controllers", "ngCordova"))
    module.run(PlatformInitializer)
    module.config(StateConfig)
    module.factory(BetterHttpServiceFactory)

    val controllers = Angular.module("starter.controllers", Array.empty[String])
    controllers.controller(DashController)
    controllers.controller(FriendsController)
    controllers.controller(FriendDetailController)
    controllers.controller(AccountController)
    

    println("end main")

  }
}
