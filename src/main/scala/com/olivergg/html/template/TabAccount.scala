package com.olivergg.html.template

import com.olivergg.html.HtmlCompilable
import scalatags.Text.all._
import com.olivergg.ionic.IonicHtmlTags._

class TabAccount extends HtmlCompilable {
  override def filePath(optMode: String): String = "templates/tab-account.html"
  override def withDocType = false

  override def output(optMode: String, moduleName: String): String = {
    ionView(title := "Account")(
      ionContent(cls := "padding")(
        h1("Account"),
        p("Your IP address is = {{ip.ip}}")
      )
    ).toString()
  }
}