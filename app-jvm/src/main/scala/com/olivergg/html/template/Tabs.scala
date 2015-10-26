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
    ionTabs(cls := "tabs-icon-top tabs-color-active-positive")(
      // <!-- Dashboard Tab -->
      ionTab(title := "Status", iconOff := "ion-ios-pulse", iconOn := "ion-ios-pulse-strong", href := "#/tab/dash")(
        ionNavView(name := "tab-dash")
      ),

      // <!-- Chats Tab -->
      ionTab(title := "Chats", iconOff := "ion-ios-chatboxes-outline", iconOn := "ion-ios-chatboxes", href := "#/tab/chats")(
        ionNavView(name := "tab-chats")
      ),

      // <!-- Friends Tab -->
      ionTab(title := "Friends", iconOff := "ion-ios-heart-outline", iconOn := "ion-ios-heart", href := "#/tab/friends")(
        ionNavView(name := "tab-friends")
      ),

      //<!-- Account Tab -->
      ionTab(title := "Account", iconOff := "ion-ios-gear-outline", iconOn := "ion-ios-gear", href := "#/tab/account")(
        ionNavView(name := "tab-account")
      )
    ).toString()
  }
}