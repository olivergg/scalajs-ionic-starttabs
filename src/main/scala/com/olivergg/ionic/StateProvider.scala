package com.olivergg.ionic

import com.greencatsoft.angularjs.Injectable

trait StateProvider extends Injectable with IStateProvider

object StateProvider {

  val Name = "$stateProvider"
}