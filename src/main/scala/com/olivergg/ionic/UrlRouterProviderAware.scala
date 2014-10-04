package com.olivergg.ionic

import scala.scalajs.js

import com.greencatsoft.angularjs.InjectionTarget
trait UrlRouterProviderAware extends InjectionTarget {

  implicit var urlRouterProvider: UrlRouterProvider = _

  override def dependencies = super.dependencies :+ UrlRouterProvider.Name  

  override def inject(args: Seq[js.Any]) {
    super.inject(args)
    var index = dependencies.indexOf(UrlRouterProvider.Name) ensuring (_ >= 0)
    this.urlRouterProvider = args(index).asInstanceOf[UrlRouterProvider]
  }

}