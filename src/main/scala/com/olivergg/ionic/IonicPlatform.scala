package com.olivergg.ionic

import com.greencatsoft.angularjs.Injectable
import scala.scalajs.js
trait IonicPlatform extends Injectable {
  //TODO : the ready should return a Promise.
  def ready[T](a: js.Function0[T]): Unit = ???
}

object IonicPlatform {

  val Name = "$ionicPlatform"
}