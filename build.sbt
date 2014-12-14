import play.PlayJava

name := """mpmap-play"""

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "joda-time" % "joda-time" % "2.5",
  "postgresql" % "postgresql" % "9.1-901.jdbc4",
  "org.mybatis" % "mybatis" % "3.1.1",
  "org.mybatis" % "mybatis-guice" % "3.3"
)