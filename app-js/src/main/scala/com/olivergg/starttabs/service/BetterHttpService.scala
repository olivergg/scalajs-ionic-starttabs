package com.olivergg.starttabs.service

import com.greencatsoft.angularjs.core.HttpService
import com.greencatsoft.angularjs.injectable
import com.greencatsoft.angularjs.NamedService
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

@injectable("$betterhttpService")
class BetterHttpService (implicit val http:HttpService, val loading:IonicLoading){

  def getJsonAndUnpickle[T: Unpickler](url: String): Future[T] = {
    loading.show(LoadingOpt("Loading..."))
    val getFuture: Future[js.Any] = http.get(url) // implicit conversion occurs here.
    val intermediateFuture: Future[Try[T]] = getFuture.map(JSON.stringify(_)).map(Unpickle[T].fromString(_))
    val outFuture: Future[T] = intermediateFuture.flatMap {
      case Success(s) => loading.hide();Future.successful(s)
      case Failure(f) => loading.hide();Future.failed(f)
    }
    outFuture
  }

}

object BetterHttpServiceFactory extends Factory[BetterHttpService] {

  override val name = "$betterhttpService"

  @inject
  implicit var http: HttpService = _
  
  @inject
  implicit var loading : IonicLoading = _

  override def apply(): BetterHttpService = new BetterHttpService()
}
