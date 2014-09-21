package com.olivergg.ionic

import com.greencatsoft.angularjs.InjectionTarget
import scala.scalajs.js
trait IonicAware extends InjectionTarget {

  implicit var ionicPlatform: IonicPlatform = _

  override def dependencies = super.dependencies :+ IonicPlatform.Name

  override def inject(args: Seq[js.Any]) {
    super.inject(args)
    var index = dependencies.indexOf(IonicPlatform.Name) ensuring (_ >= 0)
    this.ionicPlatform = args(index).asInstanceOf[IonicPlatform]
  }

}