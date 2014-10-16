package com.olivergg.html

import com.olivergg.ionic.IonicHtmlTags._
import scalatags.Text.all._
import scalatags.Text.TypedTag
class Index {
  //TODO : implement the complete index.html file.
  def output(param: String): String = {
    html(
      head(
        meta(charset := "utf-8"),
        meta(name := "viewport", content := "initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width"),

        script("some script")
      ),
      body(
        h1(span("This is my title "), span(param)),
        ionNavBar("test"),
        div(
          p("This is my first paragraph"),
          p("This is my second paragraph")
        )
      )
    ).toString()
  }
}
