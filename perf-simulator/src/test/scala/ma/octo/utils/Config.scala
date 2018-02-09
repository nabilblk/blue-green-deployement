package ma.octo.utils

/**
 * Created by macpro on 02/04/2015.
 */
object Config {
  /**
   * Server name. Default is localhost
   */
  val SERVER = System.getProperty("server", "localhost");
  /**
   * http port. default is 9001
   */
  val HTTP_PORT = System.getProperty("httpPort", "9999");

  /**
   * Application Context . default is "/"
   */
  val APP_CONTEXT = System.getProperty("context", "/");

  /**
   * Default think time in seconds.
   */
  val THINK_TIME_DEFAULT_SECONDS = Integer.getInteger("thinkTime", 3).toInt

  /**
   * Number of RAMP user . default is 10
   */
  val RAMP_USER = Integer.getInteger("rampUser", 10).toInt

  /**
    * Duration . default is 10
    */
  val DURATION = Integer.getInteger("duration", 10).toInt


}
