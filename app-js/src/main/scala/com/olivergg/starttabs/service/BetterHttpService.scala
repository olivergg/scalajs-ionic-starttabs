package com.olivergg.starttabs.service

import com.greencatsoft.angularjs.core.HttpService
import com.greencatsoft.angularjs.injectable
import com.greencatsoft.angularjs.inject
import com.olivergg.ionic.IonicLoading
import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow
import prickle.Unpickler
import scala.concurrent.Future
import scala.util.Try
import scala.scalajs.js.JSON
import prickle.Unpickle
import scala.util.Success
import scala.util.Failure
import scala.scalajs.js
import com.olivergg.ionic.LoadingOpt
import com.greencatsoft.angularjs.Factory
import com.greencatsoft.angularjs.Service

@injectable("$betterhttpService")
class BetterHttpService(http: HttpService, loading: IonicLoading) extends Service {

  def getJsonAndUnpickle[T: Unpickler](url: String): Future[T] = {
    loading.show(LoadingOpt("Loading..."))
    val getFuture: Future[js.Any] = http.get(url) // implicit conversion occurs here.
    getFuture.onFailure {
      case err => {
        loading.hide()
        loading.show(LoadingOpt("Please check your network connection", duration = 3000))
      }
    }
    val intermediateFuture: Future[Try[T]] = getFuture.map(JSON.stringify(_)).map(Unpickle[T].fromString(_))
    val outFuture: Future[T] = intermediateFuture.flatMap {
      case Success(s) =>
        loading.hide(); Future.successful(s)
      case Failure(f) => loading.hide(); Future.failed(f)
    }
    outFuture
  }

}

@injectable("$betterhttpService")
class BetterHttpServiceFactory(http: HttpService, loading: IonicLoading) extends Factory[BetterHttpService] {
  def apply(): BetterHttpService = new BetterHttpService(http, loading)
}
