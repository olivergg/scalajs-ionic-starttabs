package com.olivergg.starttabs.controller

import com.greencatsoft.angularjs.core.Scope
import com.greencatsoft.angularjs.{AbstractController, injectable}
import com.olivergg.starttabs.dto.IPAddress
import com.olivergg.starttabs.service.BetterHttpService

import scala.scalajs.js
import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow


@injectable("AccountCtrl")
class AccountController(scope: AccountScope, betterHttp: BetterHttpService) extends AbstractController[AccountScope](scope) {

  println("init AccountController")
  scope.ip = IPAddress("Unknown")
  val ipAddressFut = betterHttp.getJsonAndUnpickle[IPAddress]("http://www.telize.com/jsonip")
  ipAddressFut.onSuccess {
    case ip => println(s"response from server IP = $ip"); scope.ip = ip
  }

}

@js.native
trait AccountScope extends Scope {
  var ip: IPAddress = js.native
}

