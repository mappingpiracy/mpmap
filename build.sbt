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
  "org.mybatis" % "mybatis" % "3.2.8",
  "commons-io" % "commons-io" % "2.3",
  javaCore,
  javaJdbc
)

// playAssetsDirectories <+= baseDirectory / "app/assets"

// Add mappers to classpath
unmanagedResourceDirectories in Compile <+= baseDirectory( _ / "app/dao/mappers" )

Seq(
  scalaSource in Compile <<= baseDirectory / "app",
  javaSource in Compile <<= baseDirectory / "app",
  sourceDirectory in Compile <<= baseDirectory / "app",
  scalaSource in Test <<= baseDirectory / "test",
  javaSource in Test <<= baseDirectory / "test",
  sourceDirectory in Test <<= baseDirectory / "test",
  resourceDirectory in Compile <<= baseDirectory / "conf"
)