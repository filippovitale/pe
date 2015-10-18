libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % scalazV,
  "org.spire-math" %% "spire" % spireV,
  "com.github.scala-blitz" %% "scala-blitz" % scalaBlitzV,
  "org.specs2" %% "specs2-core" % specs2V % "test",
  "org.specs2" %% "specs2-scalacheck" % specs2V % "test",
  "org.scalacheck" %% "scalacheck" % scalacheckV % "test",
  "com.storm-enroute" %% "scalameter" % scalameterV % "test"
)
