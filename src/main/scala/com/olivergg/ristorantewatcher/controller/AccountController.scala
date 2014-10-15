package com.olivergg.ristorantewatcher.controller

import scala.concurrent.Future
import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow
import scala.scalajs.js
import scala.scalajs.js.JSON
import scala.scalajs.js.annotation.JSExportAll
import scala.util.Failure
import scala.util.Success
import scala.util.Try
import com.greencatsoft.angularjs.controller.AbstractController
import com.greencatsoft.angularjs.http.HttpPromise.promise2future
import com.greencatsoft.angularjs.http.HttpServiceAware
import com.greencatsoft.angularjs.scope.Scope
import com.greencatsoft.angularjs.scope.ScopeAware
import com.olivergg.ristorantewatcher.dto.IPAddress
import prickle.Unpickle
import org.scalajs.dom.extensions.Ajax
import org.scalajs.dom.XMLHttpRequest

object AccountController extends AbstractController("AccountCtrl") with ScopeAware with HttpServiceAware {

  override def initialize(scope: ScopeType) {
    println("init " + name)
    val ff: Future[js.Any] = http.get("http://ip.jsontest.com/")
    val gg: Future[Try[IPAddress]] = ff.map(JSON.stringify(_))
      								                 .map(Unpickle[IPAddress].fromString(_))
    gg.onSuccess {
      case Success(ip)  => println(s"response from server IP = $ip"); scope.ip = ip
      case Failure(err) => println(s"something went wrong = $err")
    }

  }

  trait ScopeType extends Scope {
    var ip: IPAddress
  }
}

