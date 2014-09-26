package com.olivergg.ristorantewatcher

import scala.scalajs.js.Dynamic.{global => g}
import com.olivergg.ionic.IonicAware
import scala.scalajs.js.Any.fromBoolean
import scala.scalajs.js.Any.fromFunction0
import scala.scalajs.js.Dynamic.{global => g}

object PlatformInitializer extends IonicAware {

  override def initialize() {
    ionicPlatform.ready { () =>
      println("ionicPlatform is ready")
      // should use typesafe window from WindowAware here instead of dynamic call.
//      if (!g.window.cordova.isInstanceOf[Unit] && !g.window.cordova.plugins.Keyboard.isInstanceOf[Unit]) {
//        g.cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
//      }
//      if (!g.window.StatusBar.isInstanceOf[Unit]) {
//        // org.apache.cordova.statusbar required
//        g.window.StatusBar.styleDefault();
//      }
    }
  }
}