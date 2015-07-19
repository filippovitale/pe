name := "pe"

description := "Solutions and micro helper lib I made while solving some interesting problems"

libraryDependencies ++= {
  val scalazV = "7.1.3"
  val scalaBlitzV = "1.1"
  val specs2V = "3.6.2"
  val scalacheckV = "1.12.4"
  val scalameterV = "0.6"

  Seq(
    "org.scalaz" %% "scalaz-core" % scalazV,
    "com.github.scala-blitz" %% "scala-blitz" % scalaBlitzV,
    "org.specs2" %% "specs2-core" % specs2V % "test",
    "org.specs2" %% "specs2-scalacheck" % specs2V % "test",
    "org.scalacheck" %% "scalacheck" % scalacheckV % "test",
    "com.storm-enroute" %% "scalameter" % scalameterV % "test"
  )
}
