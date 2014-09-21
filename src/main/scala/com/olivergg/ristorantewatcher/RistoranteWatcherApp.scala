package com.olivergg.ristorantewatcher

import scala.scalajs.js.Any.fromArray
import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

import com.greencatsoft.angularjs.Module
import com.greencatsoft.angularjs.angular

@JSExport
object RistoranteWatcherApp extends JSApp {

  override def main(): Unit = {
    println("start main")
    val app = angular.module("starter", Array("ionic", "starter.controllers", "starter.services"))
    Module(app).run(PlatformInitializer)
    		   .config(StateConfig)
    println("end main")
  }

}
