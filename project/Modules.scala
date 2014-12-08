import Settings._
import sbt.Keys._
import sbt._
import sbtassembly.AssemblyPlugin.autoImport._

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
          mainClass in (Compile, assembly) := Some("it.filippovitale.Main"),
          test in assembly := {},
          javacOptions in run ++= Seq(
            "-encoding", "UTF-8",
            "-XX:+UnlockCommercialFeatures", "-XX:+FlightRecorder",
            "-XX:FlightRecorderOptions=samplethreads=true"))
    ).dependsOn(common)

  lazy val all =
    Project(id = "all"
      , base = file(".")
      , settings = standardSettings) dependsOn(common, solution) aggregate(common, solution)
  // needs both dependsOn and aggregate to produce dependencies in the pom
}
