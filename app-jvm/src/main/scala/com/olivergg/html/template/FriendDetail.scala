package com.olivergg.html.template

import scalatags.Text.all._
import com.olivergg.ionic.IonicHtmlTags._
import com.olivergg.HtmlCompilable

/**
 * This template loads for the 'tab.friend-detail' state (see StateConfig)
 * 'friend' is a $scope variable created in the FriendsCtrl controller (see #FriendsController)
 * The FriendsCtrl pulls data from a scala service FriendsService (not to be confused with angular services).
 * The Friends service returns an array of friend data
 */
object FriendDetail extends HtmlCompilable {
  override def filePath: String = "templates/friend-detail.html"
  
  override def output: String = {
    ionView(title := "{{friend.name}}")(
      ionContent(hasHeader := "true", ionPadding := "true")(
        h1("{{friend.name}}"),
        p("Friend id = {{friend.id}}")
      )
    ).toString()
  }
}