package com.olivergg.html.template

import scalatags.Text.all._
import com.olivergg.ionic.IonicHtmlTags._
import com.olivergg.HtmlCompilable

object TabFriends extends HtmlCompilable {
  override def filePath: String = "templates/tab-friends.html"

  override def output: String = {
    ionView(title := "Friends")(
      ionContent()(
        ionList()(
          ionItem(ngRepeat := "friend in friends", ngType := "item-text-wrap", href := "#/tab/friend/{{friend.id}}")(
            "{{friend.name}}"
          )
        )
      )
    ).toString()
  }
}