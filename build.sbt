val scala3Version = "3.5.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "kitty",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core" % "2.13.0",
      "org.http4s" %% "http4s-ember-server" % "0.23.17",
      "org.http4s" %% "http4s-circe" % "0.23.30",
      "org.http4s" %% "http4s-dsl" % "0.23.30",
      "io.circe" %% "circe-generic" % "0.14.10",
      "io.circe" %% "circe-parser" % "0.14.10",
      "ch.qos.logback" % "logback-classic" % "1.5.6",
      "org.scalameta" %% "munit" % "1.0.4" % Test
    ),

    Compile / run / fork := true,

    Global / onChangedBuildSource := ReloadOnSourceChanges
  ) 
