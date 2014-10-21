package com.olivergg.html.template
import com.olivergg.ionic.IonicHtmlTags._
import scalatags.Text.all._
import scalatags.Text.tags2.{ title => htitle }

class Test {
  def output(optMode: String, moduleName: String): String = {
    div(
      div("test div1"),
      div("test div2"),
      br
    ).toString()
  }
}