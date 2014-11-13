package com.olivergg.html.template

import scalatags.Text.all._
import com.olivergg.ionic.IonicHtmlTags._
import com.olivergg.HtmlCompilable

object TabDash extends HtmlCompilable {
  override def filePath: String = "templates/tab-dash.html"

  override def output: String = {
    ionView(title := "Dashboard")(
      ionContent(cls := "padding")(
        h1("Dash"),
        div("Some content here")
      )
    ).toString()
  }
}