package com.olivergg.html.template
import com.olivergg.ionic.IonicHtmlTags._
import scalatags.Text.all._
import scalatags.Text.tags2.{ title => htitle }
import com.olivergg.html.HtmlCompilable

class Test extends HtmlCompilable {

  override def output(optMode: String, moduleName: String): String = {
    div(
      div("test div1"),
      div("test div2"),
      br,
      span("test span")
    ).toString()
  }
  
  override def filePath(optMode:String) : String = "templates/test.html"
  
  override def withDocType = false
}