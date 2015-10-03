libraryDependencies ++= {
  val scalazV = "7.1.3"
  val spireV = "0.10.1"
  val scalaBlitzV = "1.1"
  val specs2V = "3.6.4"
  val scalacheckV = "1.12.5"
  val scalameterV = "0.7"

  Seq(
    "org.scalaz" %% "scalaz-core" % scalazV,
    "org.spire-math" %% "spire" % spireV,
    "com.github.scala-blitz" %% "scala-blitz" % scalaBlitzV,
    "org.specs2" %% "specs2-core" % specs2V % "test",
    "org.specs2" %% "specs2-scalacheck" % specs2V % "test",
    "org.scalacheck" %% "scalacheck" % scalacheckV % "test",
    "com.storm-enroute" %% "scalameter" % scalameterV % "test"
  )
}
