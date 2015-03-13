package com.olivergg.starttabs

import scala.scalajs.js.Any.fromFunction0
import scala.scalajs.js.Dynamic.{ global => g }
import scala.scalajs.js.UndefOr
import scala.scalajs.js
import com.olivergg.ionic.IonicPlatform
import com.greencatsoft.angularjs.inject
import com.greencatsoft.angularjs.Runnable
import com.greencatsoft.angularjs.injectable

@injectable("PlatformInitializer")
class PlatformInitializer(ionicPlatform: IonicPlatform) extends Runnable {

  ionicPlatform.ready { () => println("ionicPlatform is ready") }
  //TODO : use ngCordova to communicate with the cordova API in an angular way
  //TODO : find a way to use cordova plugins with ionic serve in a web browser.
}
