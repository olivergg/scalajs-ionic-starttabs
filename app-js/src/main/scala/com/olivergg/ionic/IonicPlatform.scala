package com.olivergg.ionic

import scala.scalajs.js
import com.greencatsoft.angularjs.injectable

@injectable("$ionicPlatform")
trait IonicPlatform extends js.Object {
  //TODO : the ready should return a Promise.
  def ready[T](a: js.Function0[T]): Unit = ???
}
