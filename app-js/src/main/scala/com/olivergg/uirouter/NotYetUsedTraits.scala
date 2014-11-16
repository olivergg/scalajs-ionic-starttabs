package com.olivergg.uirouter

import scala.scalajs.js
import scala.scalajs.js.JSConverters.JSRichGenMap
import scala.scalajs.js.annotation.JSBracketAccess

import com.greencatsoft.angularjs.core.Promise
/// this file contains not yet used traits generated thanks to https://github.com/sjrd/scala-js-ts-importer

trait ITypedState[T] extends js.Object {
  var name: String = ???
  var template: String = ???
  var templateUrl: String = ???
  var templateProvider: js.Function0[String] = ???
  var controller: js.Any = ???
  var controllerAs: String = ???
  var controllerProvider: js.Any = ???
  var url: String = ???
  var params: js.Array[js.Any] = ???
  var `abstract`: Boolean = ???
  var onEnter: js.Function = ???
  var onExit: js.Function = ???
  var data: T = ???
}

trait IUrlMatcherFactory extends js.Object {
  def compile(pattern: String): IUrlMatcher = ???
  def isMatcher(o: js.Any): Boolean = ???
}

trait IStateOptions extends js.Object {
  var location: js.Any = ???
  var inherit: Boolean = ???
  var relative: IState = ???
  //var notify: Boolean = ???
}

trait IHrefOptions extends js.Object {
  var lossy: Boolean = ???
  var inherit: Boolean = ???
  var relative: IState = ???
  var absolute: Boolean = ???
}

trait IStateService extends js.Object {
  def go(to: String, params: js.Any = ???, options: IStateOptions = ???): Promise = ???
  def transitionTo(state: String, params: js.Any = ???, updateLocation: Boolean = ???): Unit = ???
  //def transitionTo(state: String, params: js.Any = ???, options: IStateOptions = ???): Unit = ???
  def includes(state: String, params: js.Any = ???): Boolean = ???
  def is(state: String, params: js.Any = ???): Boolean = ???
  def href(state: IState, params: js.Any = ???, options: IHrefOptions = ???): String = ???
  def get(state: String): IState = ???
  def get(): js.Array[IState] = ???
  var current: IState = ???
  var params: js.Any = ???
  def reload(): Unit = ???
}

trait IUrlRouterService extends js.Object {
  def sync(): Unit = ???
}

trait IUiViewScrollProvider extends js.Object {
  def useAnchorScroll(): Unit = ???
}



