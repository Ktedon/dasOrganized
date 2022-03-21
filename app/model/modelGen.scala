package models

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

object CodeGen extends App {
  slick.codegen.SourceCodeGenerator.run(
    "slick.jdbc.PostgresProfile",
    "org.postgresql.Driver",
    "jdbc:postgresql://localhost/parchoixdb?user=parchoix&password=admin222",
    "/home/bradly/Documents/Software/JVM/daso/app",
    "model",
    None,
    None,
    true,
    false
  )
}
