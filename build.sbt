ThisBuild / organization := "com.riemers"
ThisBuild / scalaVersion := "3.6.2"
ThisBuild / version      := "0.1.0-SNAPSHOT"

ThisBuild / githubWorkflowJavaVersions          := Seq(JavaSpec.temurin("17"))
ThisBuild / githubWorkflowPublishTargetBranches := Seq()
ThisBuild / githubWorkflowTargetBranches        := Seq("main")

lazy val root = (project in file("."))
  .settings(
    name := "advent-of-code-2024",
    libraryDependencies ++= Seq(
      "dev.zio"       %% "zio"               % "2.1.13",
      "dev.zio"       %% "zio-streams"       % "2.1.13",
      "org.typelevel" %% "cats-core"         % "2.12.0",
      "dev.zio"       %% "zio-test"          % "2.1.13" % Test,
      "dev.zio"       %% "zio-test-sbt"      % "2.1.13" % Test,
      "dev.zio"       %% "zio-test-magnolia" % "2.1.13" % Test
    ),
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  )
