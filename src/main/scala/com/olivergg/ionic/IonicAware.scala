package com.olivergg.ionic

import scala.scalajs.js

import com.greencatsoft.angularjs.InjectionTarget
trait IonicAware extends InjectionTarget {

  implicit var ionicPlatform: IonicPlatform = _

  override def dependencies = super.dependencies :+ IonicPlatform.Name

  override def inject(args: Seq[js.Any]) {
    super.inject(args)
    var index = dependencies.indexOf(IonicPlatform.Name) ensuring (_ >= 0)
    this.ionicPlatform = args(index).asInstanceOf[IonicPlatform]
  }

}