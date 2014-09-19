package com.olivergg.ristorantewatcher

import scala.scalajs.js.JSApp
import scalatags.JsDom.all._
import org.scalajs.dom.{ document, window }
import scala.scalajs.js.annotation.JSExport
import com.greencatsoft.angularjs.{ Module, angular }
import com.greencatsoft.angularjs.InjectionTarget
import scala.scalajs.js
import com.greencatsoft.angularjs.Injectable
import js.Dynamic.{ global => g }
import com.greencatsoft.angularjs.dom.WindowAware

@JSExport
object RistoranteWatcherApp extends JSApp {

  override def main(): Unit = {

    val app = angular.module("starter", Array("starter.controllers", "starter.services"))

    Module(app).run(PlatformInitializer)

  }

  implicit def jsDynamicToBoolean(j: js.Dynamic): Boolean = j.asInstanceOf[Boolean]

  object PlatformInitializer extends IonicAware with WindowAware {
    override def initialize() {
      ionicPlatform.ready {
        // should use typesafe window from WindowAware here instead of dynamic call.
        if (g.window.cordova && g.window.cordova.plugins.Keyboard) {
          g.cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
        }
        if (g.window.StatusBar) {
          // org.apache.cordova.statusbar required
          g.StatusBar.styleDefault();
        }
      }
    }
  }
  trait IonicAware extends InjectionTarget {

    implicit var ionicPlatform: IonicPlatform = _

    override def dependencies = super.dependencies :+ IonicPlatform.Name

    override def inject(args: Seq[js.Any]) {
      super.inject(args)
      var index = dependencies.indexOf(IonicPlatform.Name) ensuring (_ >= 0)
      this.ionicPlatform = args(index).asInstanceOf[IonicPlatform]
    }

  }

  trait IonicPlatform extends Injectable {
    def ready(a: => Unit): Unit = ???
  }

  object IonicPlatform {

    val Name = "$ionicPlatform"
  }
}
