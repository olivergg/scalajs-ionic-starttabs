package com.olivergg.html.template

import scalatags.Text.all._
import com.olivergg.ionic.IonicHtmlTags._
import com.olivergg.HtmlCompilable

object TabChats extends HtmlCompilable {
  override def filePath: String = "templates/tab-chats.html"

  override def output: String = {
    ionView(viewTitle := "Chats")(
      ionContent(
        ionList(
          ionItem(cls := "item-remove-animate item-avatar item-icon-right", ngRepeat := "chat in chats", ngType := "item-text-wrap",
            href := "#/tab/chats/{{chat.id}}")(
              img(ngSrc := "{{chat.face}}"),
              h2("{{chat.name}}"),
              p("{{chat.lastText}}"),
              i(cls := "icon ion-chevron-right icon-accessory")(" "),
              ionOptionButton(cls := "button-assertive", ngClick := "remove(chat)")("Delete")
            )
        )
      )
    ).toString()
  }
}