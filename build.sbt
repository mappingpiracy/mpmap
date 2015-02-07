import play.PlayJava

name := """MPMAP"""

version := "0.2"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

// Java dependencies

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

// WebJars

libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.3.0-2",
  "org.webjars" % "bootstrap" % "3.1.1-2",
  "org.webjars" % "angularjs" % "1.3.11",
  "org.webjars" % "angular-ui" % "0.4.0-3",
  "org.webjars" % "angular-ui-bootstrap" % "0.12.0",
  "org.webjars" % "leaflet" % "0.7.3",
  "org.webjars" % "angular-leaflet-directive" % "0.7.10"
)

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