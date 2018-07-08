
name := "<%= projectDirectory %>"

lazy val commonSettings = Seq(
  version := "0.0.1",
  organization := "com.github.daggerok",
  scalaVersion := "2.12.6",
  test in assembly := {}
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    mainClass in assembly := Some("daggerok.Application"),
    // more settings here ...
  )

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.13",
  "org.scalactic" %% "scalactic" % "3.0.5",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
)
