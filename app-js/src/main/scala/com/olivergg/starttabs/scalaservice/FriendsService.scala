package com.olivergg.starttabs.scalaservice

import com.olivergg.starttabs.dto.Friend

object FriendsService {

  // Some fake testing data
  private var friends: Array[Friend] = Array(
    Friend(0, "Ben Sparrow", "Enjoys drawing things", "https://pbs.twimg.com/profile_images/514549811765211136/9SgAuHeY.png"),
    Friend(1, "Max Lynx", "Odd obsession with everything", "https://avatars3.githubusercontent.com/u/11214?v=3&s=460"),
    Friend(2, "Andrew Jostlen", "Wears a sweet leather Jacket. I\'m a bit jealous", "https://pbs.twimg.com/profile_images/609810148769427456/dhzhuaNA.jpg"),
    Friend(3, "Adam Bradleyson", "I think he needs to buy a boat", "https://pbs.twimg.com/profile_images/479090794058379264/84TKj_qa.jpeg"),
    Friend(4, "Perry Governor", "Just the nicest guy", "http://pbs.twimg.com/profile_images/467390551830970368/80rkMI5v.jpeg")
  )

  def all(): Array[Friend] = {
    println("calling all in FriendService")
    friends
  }

  def get(id: Int): Friend = {
    println(s"calling get in FriendService for id = $id")
    friends(id)
  }
}

