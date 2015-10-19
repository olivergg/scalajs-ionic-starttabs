package com.olivergg.starttabs.service

import com.greencatsoft.angularjs.core.HttpService
import com.greencatsoft.angularjs.{Factory, Service, injectable}
import com.olivergg.ionic.{IonicLoading, LoadingOpt}
import prickle.{Unpickle, Unpickler}

import scala.concurrent.Future
import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow
import scala.scalajs.js.JSON
import scala.util.{Failure, Success, Try}

@injectable("$betterhttpService")
class BetterHttpService(http: HttpService, loading: IonicLoading) extends Service {

  def getJsonAndUnpickle[T: Unpickler](url: String): Future[T] = {
    loading.show(LoadingOpt("Loading..."))
    val getFuture = http.get(url) // implicit conversion occurs here.
    getFuture.onFailure {
      case err =>
        loading.hide()
        loading.show(LoadingOpt("Please check your network connection", duration = 3000))
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
