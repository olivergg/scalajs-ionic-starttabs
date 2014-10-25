package com.olivergg.ristorantewatcher

import scala.scalajs.js.Any.fromFunction0
import scala.scalajs.js.Dynamic.{ global => g }
import scala.scalajs.js.UndefOr
import scala.scalajs.js
import com.olivergg.ionic.IonicPlatform
import com.greencatsoft.angularjs.inject
import com.greencatsoft.angularjs.Runnable

object PlatformInitializer extends Runnable {

  @inject
  var ionicPlatform: IonicPlatform = _
  
  override def initialize() {
    ionicPlatform.ready { () =>
      println("ionicPlatform is ready")
      /// FIXME : unsuccessful attemp to use cordova plugins from the browser (it should work on a real device though) 
      // should use Cordova API bindings.
      //      if (!g.window.cordova.isInstanceOf[Unit] && !g.window.cordova.plugins.Keyboard.isInstanceOf[Unit]) {
      //      val cordova: UndefOr[js.Dynamic] = g.window.cordova.asInstanceOf[UndefOr[js.Dynamic]]
      //      val cordovaPluginsKeyboard: UndefOr[js.Dynamic] = g.window.Keyboard.asInstanceOf[UndefOr[js.Dynamic]]
      //      if (cordova.isDefined && cordovaPluginsKeyboard.isDefined) {
      //        cordovaPluginsKeyboard.get.hideKeyboardAccessoryBar(true)
      //      }

      //      if (!g.window.StatusBar.isInstanceOf[Unit]) {
      //        // org.apache.cordova.statusbar required
      //        g.window.StatusBar.styleDefault();
      //      }
    }
  }
}
