name := "pe"

description := "Solutions and micro-libs I made while solving some interesting problems"

libraryDependencies in ThisBuild ++= Seq(
  "org.scalaz"                      %% "scalaz-core"      % "7.1.0",
  "com.github.scala-blitz"          %% "scala-blitz"      % "1.1",
  "org.specs2"                      %% "specs2"           % "2.4.11"  % "test",
  "org.scalacheck"                  %% "scalacheck"       % "1.11.6"  % "test",
  "com.storm-enroute"               %% "scalameter"       % "0.6"     % "test"
)
