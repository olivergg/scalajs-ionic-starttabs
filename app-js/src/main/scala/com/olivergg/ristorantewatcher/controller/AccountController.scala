package com.olivergg.ristorantewatcher.controller

import scala.concurrent.Future
import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow
import scala.scalajs.js
import scala.scalajs.js.JSON
import scala.scalajs.js.annotation.JSExportAll
import scala.util.Failure
import scala.util.Success
import scala.util.Try
import com.olivergg.ristorantewatcher.dto.IPAddress
import prickle.Unpickle
import org.scalajs.dom.extensions.Ajax
import org.scalajs.dom.XMLHttpRequest
import com.greencatsoft.angularjs.Controller
import com.greencatsoft.angularjs.inject
import com.greencatsoft.angularjs.core.HttpService
import com.greencatsoft.angularjs.core.Scope
import com.olivergg.ristorantewatcher.dto.IPAddress

object AccountController extends Controller {

  override val name = "AccountCtrl"
  
  @inject
  var http: HttpService = _
  
  override def initialize(scope: ScopeType) {
    println("init " + name)
    val ff: Future[js.Any] = http.get("http://ip.jsontest.com/")
    val gg: Future[Try[IPAddress]] = ff.map(JSON.stringify(_))
      								                 .map(Unpickle[IPAddress].fromString(_))
    gg.onSuccess {
      case Success(ip)  => println(s"response from server IP = $ip"); scope.ip = ip
      case Failure(err) => println(s"something went wrong = $err"); scope.ip = IPAddress("ERROR")
    }

  }

  trait ScopeType extends Scope {
    var ip: IPAddress
  }
}

