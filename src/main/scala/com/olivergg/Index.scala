package com.olivergg
import scalatags.Text.all._
import com.olivergg.ionic.IonicHtmlTags._
class Index {
  //TODO FIXME : implement the complete index.html file.
  def frag(param: String) = html(
    head(
      meta(charset := "utf-8"),
      meta(name := "viewport", content := "initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width"),

      script("some script")
    ),
    body(
      h1(span("This is my title "),span(param)),
      ionNavBar("test"),
      div(
        p("This is my first paragraph"),
        p("This is my second paragraph")
      )
    )
  )
  println("this is just a test string printed out to check if this class can be instantiated from an SBT task (using introspection)")
  println("this should be written to a file ")
  println(frag("someParam"))
}
