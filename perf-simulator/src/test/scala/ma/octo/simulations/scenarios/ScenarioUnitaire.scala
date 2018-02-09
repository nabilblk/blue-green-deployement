package ma.octo.simulations.scenarios

import io.gatling.core.Predef._
import ma.octo.simulations.api.Dashboard
import ma.octo.utils.Config

import scala.concurrent.duration._

object ScenarioUnitaire {
  val scn = scenario("Blue Green Scenario")
    .during(Config.DURATION minutes) {
    exec(
      Dashboard.echo
    )
  }
}
