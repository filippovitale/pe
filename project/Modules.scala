import Settings._
import sbt.Keys._
import sbt._
import sbtassembly.AssemblyPlugin.autoImport._

trait Modules {
  val scalazV = "7.1.3"
  val spireV = "0.10.1"
  val scalaBlitzV = "1.1"
  val specs2V = "3.6.4"
  val scalacheckV = "1.12.5"
  val scalameterV = "0.7"

  lazy val common = Project(
    id = "pe-common",
    base = file("pe-common"),
    settings = standardSettings ++ Seq(
      javacOptions in run ++= Seq(
        "-encoding", "UTF-8"
      )
    )
  )

  lazy val solution = Project(
    id = "pe-solution",
    base = file("pe-solution"),
    settings = standardSettings ++ Seq(
      mainClass in(Compile, assembly) := Some("Main"),
      test in assembly := {},
      javacOptions in run ++= Seq(
        "-encoding", "UTF-8",
        "-XX:+UnlockCommercialFeatures", "-XX:+FlightRecorder",
        "-XX:FlightRecorderOptions=samplethreads=true",
        "-Djava.library.path=/opt/aparapi-1.0.0"))
  ).dependsOn(common % "compile->compile")

  lazy val all =
    Project(id = "all",
      base = file("."),
      settings = standardSettings).dependsOn(common, solution).aggregate(common, solution)
}
