import Settings._
import sbt.Keys._
import sbt._
import sbtassembly.AssemblyPlugin.autoImport._

import scala.scalajs.sbtplugin.ScalaJSPlugin._

//import com.lihaoyi.workbench.Plugin._

trait Modules {
  lazy val common =
    Project(id = "pe-common"
      , base = file("pe-common")
      , settings = standardSettings
    )

  lazy val solution =
    Project(id = "pe-solution"
      , base = file("pe-solution")
      , settings = standardSettings ++ Seq(
        mainClass in(Compile, assembly) := Some("Main"),
        test in assembly := {},
        javacOptions in run ++= Seq(
          "-encoding", "UTF-8",
          "-XX:+UnlockCommercialFeatures", "-XX:+FlightRecorder",
          "-XX:FlightRecorderOptions=samplethreads=true"))
    ).settings(scalaJSSettings: _*).dependsOn(common)

  lazy val all =
    Project(id = "all"
      , base = file(".")
      , settings = standardSettings) dependsOn(common, solution) aggregate(common, solution)
  // needs both dependsOn and aggregate to produce dependencies in the pom
}
