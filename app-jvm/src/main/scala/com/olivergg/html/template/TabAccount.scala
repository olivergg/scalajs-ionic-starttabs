package com.olivergg.html.template

import scalatags.Text.all._
import com.olivergg.ionic.IonicHtmlTags._
import com.olivergg.HtmlCompilable

object TabAccount extends HtmlCompilable {
  override def filePath: String = "templates/tab-account.html"

  override def output: String = {
    ionView(viewTitle := "Account")(
      ionContent(cls := "padding")(
        h1("Account"),
        p("Your IP address is = {{ip.ip}}"),
        ionList(
          ionItem(cls := "item-toggle")(
            "Enable Friends",
            label(cls := "toggle")(
              input(`type` := "checkbox", ngModel := "settings.enableFriends"),
              div(cls := "track")(div(cls := "handle"))
            )
          )
        )
      )
    ).toString()
  }
}