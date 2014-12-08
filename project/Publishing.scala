import sbt.Keys._
import sbt._

object Publishing extends Plugin {
  val nexus = "http://nexus.nsw.arbor.net:8081/content/repositories/"
  lazy val release = Some("releases" at nexus + "releases")
  lazy val snapshots = Some("snapshots" at nexus + "snapshots")
  lazy val local = Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository")))

  override def settings =
    Seq(
      publishTo <<= version { (v: String) =>
        if (v.trim endsWith "SNAPSHOT")
          snapshots
        else
          release
      }
    )
}
