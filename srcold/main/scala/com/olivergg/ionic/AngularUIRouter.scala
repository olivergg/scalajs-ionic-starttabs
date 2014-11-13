package com.olivergg.ionic

import scala.scalajs.js
import scala.scalajs.js.JSConverters.JSRichGenMap
import scala.scalajs.js.RegExp
import scala.scalajs.js.annotation.JSBracketAccess
import com.greencatsoft.angularjs.core.Promise


trait IState extends js.Object {
  var name: String = ???
  var template: String = ???
  var templateUrl: String = ???
  var templateProvider: js.Any = ???
  var controller: js.Any = ???
  var controllerAs: String = ???
  var controllerProvider: js.Any = ???
  var url: String = ???
  var params: js.Array[js.Any] = ???
  var `abstract`: Boolean = ???
  var onEnter: js.Function = ???
  var onExit: js.Function = ???
  var data: js.Any = ???
  var views: js.Dictionary[IView] = ???
}

object State {
  def apply(url: String, isAbstract: Boolean = false, templateUrl: String = "", views: Map[String, IView] = Map.empty): IState = {
    val out = new js.Object().asInstanceOf[IState]
    out.url = url
    out.`abstract` = isAbstract
    if (!templateUrl.isEmpty()) {
      out.templateUrl = templateUrl
    }
    if (!views.isEmpty) {
      out.views = views.toJSDictionary
    }
    out
  }
}

trait IView extends js.Object {
  var templateUrl: String = ???
  var controller: String = ???
}

object View {
  def apply(templateUrl: String, controller: String): IView = {
    val out = new js.Object().asInstanceOf[IView]
    out.templateUrl = templateUrl
    out.controller = controller
    out
  }
}

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

trait IServiceProvider extends js.Object

trait IStateProvider extends IServiceProvider {
  def state(name: String, config: IState): IStateProvider = ???
  def state(config: IState): IStateProvider = ???
  def decorator(name: String = ???, decorator: js.Function2[IState, js.Function, Any] = ???): js.Dynamic = ???
}

trait IUrlMatcher extends js.Object {
  def concat(pattern: String): IUrlMatcher = ???
  def exec(path: String, searchParams: js.Any): js.Any = ???
  def parameters(): js.Array[String] = ???
  def format(values: js.Any): String = ???
}

trait IUrlMatcherFactory extends js.Object {
  def compile(pattern: String): IUrlMatcher = ???
  def isMatcher(o: js.Any): Boolean = ???
}

trait IUrlRouterProvider extends IServiceProvider {
  def when(whenPath: RegExp, handler: js.Function): IUrlRouterProvider = ???
  def when(whenPath: RegExp, toPath: String): IUrlRouterProvider = ???
  def when(whenPath: IUrlMatcher, hanlder: js.Function): IUrlRouterProvider = ???
  def otherwise(handler: js.Function): IUrlRouterProvider = ???
  def otherwise(path: String): IUrlRouterProvider = ???
  def rule(handler: js.Function): IUrlRouterProvider = ???
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

trait IStateParamsService extends js.Object {
  @JSBracketAccess
  def apply(key: String): js.Any = ???
  @JSBracketAccess
  def update(key: String, v: js.Any): Unit = ???
}

trait IStateParams extends js.Object {
  @JSBracketAccess
  def apply(key: String): js.Any = ???
  @JSBracketAccess
  def update(key: String, v: js.Any): Unit = ???
}

trait IUrlRouterService extends js.Object {
  def sync(): Unit = ???
}

trait IUiViewScrollProvider extends js.Object {
  def useAnchorScroll(): Unit = ???
}



