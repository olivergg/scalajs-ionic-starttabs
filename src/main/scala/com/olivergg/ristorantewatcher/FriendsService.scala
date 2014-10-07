package com.olivergg.ristorantewatcher

import scala.concurrent.Future
import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow
import scala.scalajs.js
import scala.scalajs.js.Any.fromString
import scala.scalajs.js.JSON
import scala.scalajs.js.annotation.JSExportAll
import scala.util.Failure
import scala.util.Success
import scala.util.Try
import com.greencatsoft.angularjs.http.HttpPromise.promise2future
import com.greencatsoft.angularjs.http.HttpService
import com.olivergg.ristorantewatcher.dto.Friend
import com.olivergg.ristorantewatcher.dto.IPAddress
import org.scalajs.dom.{ XMLHttpRequest, Event }
import js.annotation.JSExport
import org.scalajs.dom.extensions.Ajax
import prickle._
import com.olivergg.ristorantewatcher.dto.IPAddress

class FriendsService()(implicit http: HttpService) {

  // Some fake testing data
  private var friends: Array[Friend] = Array(
    Friend(0, "Scruff McGruff"),
    Friend(1, "G.I. Joe"),
    Friend(2, "Miss Frizzle"),
    Friend(3, "Ash Ketchum")
  )

  def all(): Array[Friend] = {

    println("calling all")

    
    /////////////////////////////////
    ////////// first attempt
    val f: Future[js.Any] = http.get("http://ip.jsontest.com/")
    val g: Future[Try[IPAddress]] = f.map(JSON.stringify(_))
      							     .map(Unpickle[IPAddress].fromString(_))
    g.onSuccess {
      case Success(ip) => println(s"response from server IP = $ip")
      case Failure(err) => println(s"something went wrong = $err")
    }

    ////// second attempt.
    Ajax.get(url = "http://ip.jsontest.com/", timeout = 60) onComplete {
      case Success(data: XMLHttpRequest) => {
        Unpickle[IPAddress].fromString(data.responseText) match {
          case Success(ip) => println(s"response from server IP = $ip")
          case Failure(err) => println(s"something went wrong = $err")
        }
      }
      case Failure(f) => {
        println("network error "+f)
      }
    }
    ////////////////////////////////////
    
    friends
  }

  def get(id: Int): Friend = {
    println(s"calling get for id = $id")
    friends(id)
  }
}

object FriendsService {
  var friendsService: FriendsService = _

  def apply()(implicit http: HttpService): FriendsService = {
    if (friendsService == null) {
      friendsService = new FriendsService()(http)
      friendsService
    }
    else {
      friendsService
    }
  }
}
