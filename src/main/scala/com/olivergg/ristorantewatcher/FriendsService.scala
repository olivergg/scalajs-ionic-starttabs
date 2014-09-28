package com.olivergg.ristorantewatcher

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

import com.greencatsoft.angularjs.Factory
import com.greencatsoft.angularjs.InjectionTarget

object FriendsServiceImpl extends Factory[Unit] with FriendsService {

  override val name = "Friends"

  private var friends: Array[Friend] = _

  //  def apply(): Unit = {
  //    println("apply friendsserviceimpl factory")
  friends = Array(
    Friend(0, "Scruff McGruff"),
    Friend(1, "G.I. Joe"),
    Friend(2, "Miss Frizzle"),
    Friend(3, "Ash Ketchum")
  )
  //  }
  def all(): Array[Friend] = {
    println("calling all")
    println(friends.mkString(","))
    friends
  }

  def get(id: Int): Friend = {
    println(s"get for id = $id")
    val out = friends(id)
    println(s"get out = $out")
    out
  }

  def apply(): Unit = {
    println("apply friendserviceimpl (factory)")
  }
}

trait FriendsService {
  def all(): Array[Friend]
  def get(id: Int): Friend
}
object FriendsService {

  val Name = "Friends"
}

trait FriendsServiceAware extends InjectionTarget {
  implicit var friendsService: FriendsService = FriendsServiceImpl

  //  override def dependencies = super.dependencies :+ FriendsService.Name

  //  override def inject(args: Seq[js.Any]) {
  //    //super.inject(args)
  //    println("injecting friends service aware ")
  //    this.friendsService = FriendsServiceImpl
  ////    var index = dependencies.indexOf(FriendsService.Name) ensuring (_ >= 0)
  ////    this.friendsService = args(index).asInstanceOf[FriendsService]
  //
  //  }
}
