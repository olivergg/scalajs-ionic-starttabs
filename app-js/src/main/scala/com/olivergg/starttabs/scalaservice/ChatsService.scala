package com.olivergg.starttabs.scalaservice

import com.olivergg.starttabs.dto.Chat

object ChatsService {

  // fake data
  private var chats: Array[Chat] = Array(
    Chat(0, "Ben Sparrow", "You on your way?", "https://pbs.twimg.com/profile_images/514549811765211136/9SgAuHeY.png"),
    Chat(1, "Max Lynx", "Hey, it\'s me", "https://avatars3.githubusercontent.com/u/11214?v=3&s=460"),
    Chat(2, "Andrew Jostlin", "Did you get the ice cream?", "https://pbs.twimg.com/profile_images/609810148769427456/dhzhuaNA.jpg"),
    Chat(3, "Adam Bradleyson", "I should buy a boat", "https://pbs.twimg.com/profile_images/479090794058379264/84TKj_qa.jpeg"),
    Chat(4, "Perry Governor", "Look at my mukluks!", "http://pbs.twimg.com/profile_images/467390551830970368/80rkMI5v.jpeg")
  )

  def all(): Array[Chat] = {
    println("calling all from ChatService")
    chats
  }

  def get(id: Int): Chat = {
    println(s"calling get in ChatService for id = $id")
    chats(id)
  }
}
