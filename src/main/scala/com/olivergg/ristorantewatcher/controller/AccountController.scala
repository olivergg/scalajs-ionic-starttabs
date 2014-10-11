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

object AccountController extends AbstractController("AccountCtrl") with ScopeAware with HttpServiceAware {

  override def initialize(scope: ScopeType) {
    println("init " + name)

    /// the following code does not work...we can't change the scope in the onComplete of an Ajax call ??
    //    val f = Ajax.get(url = "http://ip.jsontest.com/", timeout = 60) onComplete {
    //      case Success(data: XMLHttpRequest) => {
    //        Unpickle[IPAddress].fromString(data.responseText) match {
    //          case Success(ip) => println(ip); scope.ip = ip
    //          case Failure(err) => println(s"something went wrong = $err")
    //        }
    //      }
    //      case Failure(f) => {
    //        println("network error " + f)
    //      }
    //    }

    val f: Future[js.Any] = http.get("http://ip.jsontest.com/")
    val g: Future[Try[IPAddress]] = f.map(JSON.stringify(_))
      								 .map(Unpickle[IPAddress].fromString(_))
    g.onSuccess {
      case Success(ip) =>
        println(s"response from server IP = $ip"); scope.ip = ip
      case Failure(err) => println(s"something went wrong = $err")
    }

  }

  trait ScopeType extends Scope {
    var ip: IPAddress
  }
}

