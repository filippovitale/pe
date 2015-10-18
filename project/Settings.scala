import sbt.Keys._
import sbt._

object Settings {

  lazy val scalacFlags = Seq(
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
  )

  lazy val standardSettings =
    Defaults.coreDefaultSettings ++
      net.virtualvoid.sbt.graph.Plugin.graphSettings ++ // dependency plugin settings
      Seq[Def.Setting[_]](
        organization := "it.filippovitale",
        homepage := Some(url("https://filippovitale.it/")),
        licenses := Seq("MIT License" -> url("http://opensource.org/licenses/mit-license.php/")),
        pomIncludeRepository := { (repo: MavenRepository) => false}, // no repositories in the pom
        scalaVersion := "2.11.7",
        autoScalaLibrary := true,
        scalacOptions ++= scalacFlags,
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
        ivyScala := ivyScala.value map {
          _.copy(overrideScalaVersion = true)
        }
      )
}
