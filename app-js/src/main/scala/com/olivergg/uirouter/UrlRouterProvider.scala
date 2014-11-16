package com.olivergg.uirouter

import com.greencatsoft.angularjs.injectable
import scala.scalajs.js
import scala.scalajs.js.RegExp

@injectable("$urlRouterProvider")
trait UrlRouterProvider extends js.Object {
  def when(whenPath: RegExp, handler: js.Function): UrlRouterProvider = ???
  def when(whenPath: RegExp, toPath: String): UrlRouterProvider = ???
  def when(whenPath: IUrlMatcher, hanlder: js.Function): UrlRouterProvider = ???
  def otherwise(handler: js.Function): UrlRouterProvider = ???
  def otherwise(path: String): UrlRouterProvider = ???
  def rule(handler: js.Function): UrlRouterProvider = ???

}

trait IUrlMatcher extends js.Object {
  def concat(pattern: String): IUrlMatcher = ???
  def exec(path: String, searchParams: js.Any): js.Any = ???
  def parameters(): js.Array[String] = ???
  def format(values: js.Any): String = ???
}