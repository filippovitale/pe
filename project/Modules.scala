import Settings._
import sbt.Keys._
import sbt._
import sbtassembly.AssemblyPlugin.autoImport._

//import scala.scalajs.sbtplugin.ScalaJSPlugin._

//import com.lihaoyi.workbench.Plugin._
//import ScalaJSKeys._

trait Modules {
  lazy val common =
    Project(id = "pe-common"
      , base = file("pe-common")
      , settings = standardSettings
    )

//  lazy val scalajs = Def.setting(Seq(
//    "org.scala-lang.modules.scalajs" %%% "scalajs-jquery" % "0.6.4",
//    "com.scalatags" %%% "scalatags" % "0.4.3-M3"
//  ))

  lazy val solution =
    Project(id = "pe-solution"
      , base = file("pe-solution")
      , settings = standardSettings ++ Seq(
        mainClass in(Compile, assembly) := Some("Main"),
        test in assembly := {},
        //bootSnippet := "PE().main(document.getElementById('canvas'));",
        //updateBrowsers <<= updateBrowsers.triggeredBy(ScalaJSKeys.fastOptJS in Compile),
        javacOptions in run ++= Seq(
          "-encoding", "UTF-8",
          "-XX:+UnlockCommercialFeatures", "-XX:+FlightRecorder",
          "-XX:FlightRecorderOptions=samplethreads=true",
          "-Djava.library.path=/opt/aparapi-1.0.0"))
    )//.settings(scalaJSSettings: _*)
     //.settings(workbenchSettings: _*)
     //.settings(buildSettings: _*)
     //.settings(libraryDependencies ++= scalajs.value)
      .dependsOn(common)

  lazy val all =
    Project(id = "all"
      , base = file(".")
      , settings = standardSettings) dependsOn(common, solution) aggregate(common, solution)
  // needs both dependsOn and aggregate to produce dependencies in the pom
}
