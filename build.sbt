lazy val buildSettings = Seq(
  version := "0.0.1-SNAPSHOT",
  organization := "it.filippovitale",
  homepage := Some(url("http://www.filippovitale.it/")),
  licenses := Seq("MIT License" -> url("http://opensource.org/licenses/mit-license.php/")),
  scalaVersion := "2.10.4",
  //  scalacOptions := Seq("-deprecation", "-unchecked", "-Yrangepos"),
  scalacOptions in Test ++= Seq("-Yrangepos"),
  resolvers += Resolver.sonatypeRepo("public"),
  libraryDependencies ++= Seq(
    "org.specs2" %% "specs2" % "2.3.10" % "test",
    "org.scalacheck" %% "scalacheck" % "1.10.1" % "test"
  )
)

lazy val root = (project in file(".")).settings(buildSettings: _*).settings(name := "pe")

scalaSource in Compile := baseDirectory.value / "src"

scalaSource in Test := baseDirectory.value / "test"
