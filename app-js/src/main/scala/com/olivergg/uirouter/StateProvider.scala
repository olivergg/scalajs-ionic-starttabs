package com.olivergg.uirouter

import com.greencatsoft.angularjs.injectable
import scala.scalajs.js
import scala.scalajs.js.JSConverters.JSRichGenMap

@injectable("$stateProvider")
trait StateProvider extends js.Object {
  def state(name: String, config: IState): StateProvider = ???
  def state(config: IState): StateProvider = ???
  def decorator(name: String = ???, decorator: js.Function2[IState, js.Function, Any] = ???): js.Dynamic = ???
}


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
    if (!templateUrl.isEmpty()) out.templateUrl = templateUrl
    if (!views.isEmpty) out.views = views.toJSDictionary
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
