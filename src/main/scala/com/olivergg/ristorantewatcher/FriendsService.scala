package com.olivergg.ristorantewatcher

import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow
import scala.scalajs.js.annotation.JSExportAll

import com.greencatsoft.angularjs.Injectable
import com.greencatsoft.angularjs.http.HttpPromise.promise2future
import com.greencatsoft.angularjs.http.HttpService

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
    val f = http.get("http://ip.jsontest.com/")
    f.onSuccess {
      case t => println("response from server " + t)
    }
    friends
  }

  def get(id: Int): Friend = {
    println(s"calling get for id = $id")
    val out = friends(id)
    out
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
