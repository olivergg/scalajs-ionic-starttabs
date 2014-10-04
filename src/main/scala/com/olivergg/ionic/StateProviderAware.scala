package com.olivergg.ionic

import scala.scalajs.js

import com.greencatsoft.angularjs.InjectionTarget

trait StateProviderAware extends InjectionTarget {

  implicit var stateProvider: StateProvider = _

  override def dependencies = super.dependencies :+  StateProvider.Name 

  override def inject(args: Seq[js.Any]) {
    super.inject(args)
    var index = dependencies.indexOf(StateProvider.Name) ensuring (_ >= 0)
    this.stateProvider = args(index).asInstanceOf[StateProvider]
  }

  
}