lazy val buildSettings = Seq(
  version := "0.0.1-SNAPSHOT",
  organization := "it.filippovitale",
  homepage := Some(url("http://www.filippovitale.it/")),
  licenses := Seq("MIT License" -> url("http://opensource.org/licenses/mit-license.php/")),
  scalaVersion := "2.10.3",
  scalacOptions := Seq("-deprecation", "-unchecked", "-Yrangepos"),
  resolvers += Resolver.sonatypeRepo("public")
)

lazy val root = (project in file(".")).
  settings(buildSettings: _*).
  settings(name := "pe")

scalaSource in Compile := file("src")

libraryDependencies ++= Seq(    "org.specs2" %% "specs2" % "2.3.7" % "test"  )
