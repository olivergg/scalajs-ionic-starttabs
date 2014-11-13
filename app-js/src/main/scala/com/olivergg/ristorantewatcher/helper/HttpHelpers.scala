package com.olivergg.ristorantewatcher.helper

import com.greencatsoft.angularjs.core.HttpService
import prickle.Unpickler
import scala.concurrent.Future
import com.greencatsoft.angularjs.inject
import scala.scalajs.js.JSON
import prickle.Unpickle
import scala.util.Try
import scala.scalajs.js
import scala.concurrent.ExecutionContext

object HttpHelpers {

  def getJsonAndUnpickle[T: Unpickler](url: String)(implicit http: HttpService, ec: ExecutionContext ): Future[Try[T]] = {
    val getFuture: Future[js.Any] = http.get(url) // implicit conversion occurs here.
    val outFuture: Future[Try[T]] = getFuture.map(JSON.stringify(_)).map(Unpickle[T].fromString(_))
    outFuture
  }

}