package com.olivergg.html.template

import scalatags.Text.all._
import com.olivergg.ionic.IonicHtmlTags._
import com.olivergg.HtmlCompilable
/**
 * Create tabs with an icon and label, using the tabs-positive style.
 * Each tab's child <ion-nav-view> directive will have its own
 * navigation history that also transitions its views in and out.
 */
object Tabs extends HtmlCompilable {
  def filePath: String = "templates/tabs.html"

  def output: String = {
    ionTabs(cls := "tabs-icon-top tabs-background-positive tabs-striped tabs-light")(
      // <!-- Dashboard Tab -->
      ionTab(title := "Dashboard", icon := "icon ion-home", href := "#/tab/dash")(
        ionNavView(name := "tab-dash")
      ),
      // <!-- Friends Tab -->
      ionTab(title := "Friends", icon := "icon ion-heart", href := "#/tab/friends")(
        ionNavView(name := "tab-friends")
      ),

      //<!-- Account Tab -->
      ionTab(title := "Account", icon := "icon ion-gear-b", href := "#/tab/account")(
        ionNavView(name := "tab-account")
      )
    ).toString()
  }
}