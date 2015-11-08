import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbtassembly.AssemblyPlugin.autoImport._

object Build extends sbt.Build {
  val scalazV = "7.1.4"
  val spireV = "0.10.1"
  val scalaBlitzV = "1.2"
  val specs2V = "3.6.5"
  val scalacheckV = "1.12.5"
  val scalameterV = "0.7"

  import sbt._
  import sbt.Keys._

  lazy val standardSettings = Defaults.coreDefaultSettings ++
    net.virtualvoid.sbt.graph.Plugin.graphSettings ++
    Seq[Def.Setting[_]](
      organization := "it.filippovitale",
      homepage := Some(url("https://filippovitale.it/")),
      licenses := Seq("MIT License" -> url("http://opensource.org/licenses/mit-license.php/")),
      pomIncludeRepository := { (repo: MavenRepository) => false}, // no repositories in the pom
      scalaVersion := "2.11.7",
      autoScalaLibrary := true,
      scalacOptions ++= Seq(
        "-deprecation",
        "-feature",
        "-language:existentials",
        "-language:experimental.macros",
        "-language:higherKinds",
        "-language:implicitConversions",
        "-unchecked",
        "-Yno-adapted-args",
        "-Yrangepos",
        "-Xmax-classfile-name", "240"
      ),
      resolvers ++= Seq(
        "Tools Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
        "Tools Releases" at "http://oss.sonatype.org/content/repositories/releases",
        "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases",
        "apache-public" at "https://repository.apache.org/content/groups/public/",
        "cloudera" at "https://repository.cloudera.com/artifactory/cloudera-repos/",
        "KrakenApps Maven Repository" at "http://download.krakenapps.org/",
        "clojars" at "http://clojars.org/repo",
        "Maven Java Net Snapshots and Releases" at "https://maven.java.net/content/groups/public/",
        "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
        "artifactory scalasbt" at "http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases"
      ),
      libraryDependencies ++= Seq(
        "org.scalaz" %% "scalaz-core" % scalazV,
        "org.spire-math" %% "spire" % spireV,
        "com.github.scala-blitz" %% "scala-blitz" % scalaBlitzV,
        "org.specs2" %% "specs2-core" % specs2V % "test",
        "org.specs2" %% "specs2-scalacheck" % specs2V % "test",
        "org.scalacheck" %% "scalacheck" % scalacheckV % "test",
        "com.storm-enroute" %% "scalameter" % scalameterV % "test"
      ),
      ivyScala := ivyScala.value map {
        _.copy(overrideScalaVersion = true)
      }
    )

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
  ).dependsOn(common)
    //.enablePlugins(ScalaJSPlugin).settings(libraryDependencies ++= Seq("org.scala-js" %%% "scalajs-dom" % "0.8.0"))

  lazy val all =
    Project(id = "all",
      base = file("."),
      settings = standardSettings).dependsOn(common, solution).aggregate(common, solution)
}
