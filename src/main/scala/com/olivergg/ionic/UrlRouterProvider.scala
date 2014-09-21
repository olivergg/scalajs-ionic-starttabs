package com.olivergg.ionic

import com.greencatsoft.angularjs.Injectable

trait UrlRouterProvider extends Injectable with IUrlRouterProvider

object UrlRouterProvider {
  
  val Name = "$urlRouterProvider"
}