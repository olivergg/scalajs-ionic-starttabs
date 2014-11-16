package com.olivergg.uirouter

import com.greencatsoft.angularjs.injectable
import scala.scalajs.js
import scala.scalajs.js.annotation.JSBracketAccess

@injectable("$stateParams")
trait StateParams extends js.Object {
  @JSBracketAccess
  def apply(key: String): js.Any = ???
  @JSBracketAccess
  def update(key: String, v: js.Any): Unit = ???
}
