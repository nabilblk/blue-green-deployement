package ma.octo.simulations.api

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import ma.octo.utils.Config

import scala.concurrent.duration.DurationInt

object Dashboard {

  def echo = group("API_ECHO") {
    exec(http("all_produit")
      .get(Config.APP_CONTEXT++"""/dashboard/echo""")
      .check(status.is(200)))
      .pause(10 millisecond)
  }

}
