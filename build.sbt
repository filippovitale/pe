import scala.scalajs.sbtplugin.ScalaJSPlugin._
import com.lihaoyi.workbench.Plugin._

scalaJSSettings

workbenchSettings

lazy val buildSettings = Seq(
  version := "0.0.1-SNAPSHOT",
  organization := "it.filippovitale",
  homepage := Some(url("http://www.filippovitale.it/")),
  licenses := Seq("MIT License" -> url("http://opensource.org/licenses/mit-license.php/")),
  scalaVersion := "2.11.4",
  scalacOptions in Test ++= Seq("-Yrangepos"),
  resolvers += Resolver.sonatypeRepo("public"),
  libraryDependencies ++= Seq(
    "org.specs2"                       %% "specs2"        % "2.4.13"  % "test",
    "org.scalacheck"                   %% "scalacheck"    % "1.12.0"  % "test"
  )
)

lazy val scalajs = Def.setting(Seq(
  "org.scala-lang.modules.scalajs"  %%% "scalajs-jquery" % "0.6",
  "com.scalatags"                   %%% "scalatags"     % "0.4.2"
))


lazy val root = (project in file("."))
  .settings(buildSettings: _*)
  .settings(libraryDependencies ++= scalajs.value)
  .settings(name := "pe")

scalaSource in Compile := baseDirectory.value / "src"

resourceDirectory in Compile := baseDirectory.value / "resources"

scalaSource in Test := baseDirectory.value / "test"

resourceDirectory in Test := baseDirectory.value / "test-resources"

bootSnippet := "PE().main(document.getElementById('canvas'));"

updateBrowsers <<= updateBrowsers.triggeredBy(ScalaJSKeys.fastOptJS in Compile)
