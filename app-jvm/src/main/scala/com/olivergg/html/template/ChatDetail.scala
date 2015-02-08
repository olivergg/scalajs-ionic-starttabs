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
object ChatDetail extends HtmlCompilable {
  override def filePath: String = "templates/chat-detail.html"

  override def output: String = {
    ionView(viewTitle := "{{chat.name}}")(
      ionContent(cls := "padding")(
        img(ngSrc := "{{chat.face}}", style := "width:64px; height:64px"),
        p("{{chat.lastText}}")
      )
    ).toString()
  }
}