package com.olivergg.ristorantewatcher

object FriendsService {

  // Some fake testing data
  private var friends: Array[Friend] = Array(
    Friend(0, "Scruff McGruff"),
    Friend(1, "G.I. Joe"),
    Friend(2, "Miss Frizzle"),
    Friend(3, "Ash Ketchum")
  )

  def all(): Array[Friend] = {
    println("calling all")
    friends
  }

  def get(id: Int): Friend = {
    println(s"calling get for id = $id")
    val out = friends(id)
    out
  }
}
