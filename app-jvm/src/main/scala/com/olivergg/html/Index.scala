package com.olivergg.html

import com.olivergg.ionic.IonicHtmlTags._
import scalatags.Text.all._
import scalatags.Text.tags2.{ title => htitle }
import com.olivergg.HtmlCompilable

object Index extends HtmlCompilable {

  override def useOptMode = true
  override def filePath: String = optMode match {
    case "fastOpt" => "index-dev.html"
    case "fullOpt" => "index-prod.html"
  }
  override def withDocType: Boolean = true

  override def output : String = {
    val jsFileBaseName = s"js/$moduleName"
    val optModeSuffix = optMode match {
      case "fastOpt" => "fastopt.js"
      case "fullOpt" => "opt.js"
    }
    html(
      head(
        meta(charset := "utf-8"),
        meta(name := "viewport", content := "initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width"),
        htitle("Scala.js Ionic Start Tabs Application"),
        link(href := "lib/ionic/css/ionic.min.css", rel := "stylesheet"),
        link(href := "css/style.css", rel := "stylesheet"),
        // ionic/angularjs is included in the bundled jsdeps created by scala-js. 
        //See the webjar dependencies in the build.sbt to add more javascript dependencies
        script(src := s"js/$moduleName-jsdeps.js")(" "),
        // ngCordova (this will not work in the web browser...)
        script(src := "ng-cordova.min.js")(" "),
        // cordova script (this will be a 404 during development)
        script(src := "cordova.js")(" "),
        // your app's js
        script(src := s"js/$moduleName-$optModeSuffix")(" "),
        script(src := s"js/$moduleName-launcher.js")(" ")

      ),
      body(ngApp := "starter", animation := "no-animation")(
        ionNavBar(cls := "bar-stable bar-positive", animation := "no-animation" /*nav-title-slide-ios7"*/ )(
          ionNavBackButton(cls := "button-icon icon  ion-ios7-arrow-back")("Back")
        ),
        /**
         * The views will be rendered in the <ion-nav-view> directive below
         * Templates are in the /templates folder (but you could also
         * have templates inline in this html file if you'd like).
         */
        ionNavView(animation := "no-animation")
      )
    ).toString()
  }

}
