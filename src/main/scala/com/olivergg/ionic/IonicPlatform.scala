package com.olivergg.ionic

import scala.scalajs.js

import com.greencatsoft.angularjs.Injectable
trait IonicPlatform extends Injectable {
  //TODO : the ready should return a Promise.
  def ready[T](a: js.Function0[T]): Unit = ???
}

object IonicPlatform {

  val Name = "$ionicPlatform"
}