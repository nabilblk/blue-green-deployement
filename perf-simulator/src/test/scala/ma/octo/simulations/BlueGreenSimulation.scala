package ma.octo.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import ma.octo.simulations.scenarios.ScenarioUnitaire
import ma.octo.utils.Config

import scala.concurrent.duration._

class BlueGreenSimulation extends Simulation {

  val httpProtocol = http.baseURL("http://"++Config.SERVER++":"++Config.HTTP_PORT)

  setUp(
    ScenarioUnitaire.scn
      .inject(rampUsers(Config.RAMP_USER) over (1 minutes))).protocols(httpProtocol)

}
