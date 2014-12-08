name := "pe"

description := "Solutions and micro-libs I made while solving some interesting problems"

libraryDependencies in ThisBuild ++= Seq(
  "org.scalaz"                      %% "scalaz-core"      % "7.1.0",
  "org.specs2"                      %% "specs2"           % "2.4.11"  % "test",
  "org.scalacheck"                  %% "scalacheck"       % "1.11.6"  % "test",
  "com.storm-enroute"               %% "scalameter"       % "0.6"     % "test"
)

// TODO make scala.js work again
//import scala.scalajs.sbtplugin.ScalaJSPlugin._
//import com.lihaoyi.workbench.Plugin._

//scalaJSSettings

//workbenchSettings

//lazy val scalajs = Def.setting(Seq(
//  "org.scala-lang.modules.scalajs"  %%% "scalajs-jquery" % "0.6",
//  "com.scalatags"                   %%% "scalatags"     % "0.4.2"
//))

//lazy val root = (project in file("."))
//  .settings(buildSettings: _*)
//  .settings(libraryDependencies ++= scalajs.value)

//bootSnippet := "PE().main(document.getElementById('canvas'));"

//updateBrowsers <<= updateBrowsers.triggeredBy(ScalaJSKeys.fastOptJS in Compile)
