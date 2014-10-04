package com.olivergg.ionic

import scala.scalajs.js

import com.greencatsoft.angularjs.InjectionTarget


trait StateParamsAware extends InjectionTarget {

  implicit var stateParams: StateParams = _

  override def dependencies = super.dependencies :+  StateParams.Name 

  override def inject(args: Seq[js.Any]) {
    super.inject(args)
    var index = dependencies.indexOf(StateParams.Name) ensuring (_ >= 0)
    this.stateParams = args(index).asInstanceOf[StateParams]
  }
  
   implicit class DynamicStateParam(stateParam: StateParams) {

    def dynamic = stateParam.asInstanceOf[js.Dynamic]
  }

  
}