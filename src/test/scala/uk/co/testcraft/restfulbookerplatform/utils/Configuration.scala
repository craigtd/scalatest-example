package uk.co.testcraft.restfulbookerplatform.utils

class Configuration(val url: String, val BROWSER: String = System.getProperty("browser", "firefox"))


object Environment extends Enumeration {
  type Name = Value
  val Local, Dev, Qa, Staging = Value
}

object Configuration {

  lazy val environment: Environment.Name = {
    val environmentProperty = Option(System.getProperty("environment")).getOrElse("local").toLowerCase
    environmentProperty match {
      case "local" => Environment.Local
      case "qa" => Environment.Qa
      case "dev" => Environment.Dev
      case "staging" => Environment.Staging
      case _ => throw new IllegalArgumentException(s"Environment '$environmentProperty' not known")
    }
  }

  lazy val settings: Configuration = create()

  private def create(): Configuration = {
    environment match {
      case Environment.Local =>
        new Configuration(
          url = "http://localhost:3003"
        )
      case _ => throw new IllegalArgumentException(s"Environment '$environment' not known")
    }
  }
}