package com.olivergg.ionic

import com.greencatsoft.angularjs.Injectable

trait StateParams extends Injectable with IStateParams

object StateParams {

  val Name = "$stateParams"
}