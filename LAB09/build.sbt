import sbt.ThisBuild
import scalariform.formatter.preferences._
name := "LAB09"

version in ThisBuild := "1.4.5"

scalaVersion in ThisBuild := "2.12.12"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.2"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.2" % "test"



lazy val core = Project("core", file("core")).settings(
  libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.2",
  libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.2" % "test",
  scalacOptions ++= Seq(
    "-encoding", "utf8", 
    "-unchecked", 
    "-language:implicitConversions", 
    "-Ywarn-unused-import",
    "-Yrangepos"),
   )


lazy val actions = Project("actions", file("actions")).dependsOn(core).settings(
  libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.2",
  libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.2" % "test",
  scalacOptions ++= Seq(
    "-encoding", "utf8",
    "-unchecked", 
    "-language:implicitConversions", 
    "-Ywarn-unused-import",
    "-Yrangepos"),
)

lazy val root = (project in file(".")).aggregate(core,actions)

//for scalafix
inThisBuild(
List(
  scalaVersion := "2.12.12",
  semanticdbEnabled := true,
 semanticdbVersion := scalafixSemanticdb.revision
)
)

 scalacOptions ++= Seq(
  "-encoding", "utf8", 
  "-unchecked", 
  "-language:implicitConversions", 
  "-Ywarn-unused-import",
  "-Yrangepos"

)
//Q : Would scalacOption in This Build be enough to set these options for all subprojects?

scalariformPreferences in ThisBuild := scalariformPreferences.value
  .setPreference(AlignSingleLineCaseStatements, true) // better reading for pattern matching
  .setPreference(DoubleIndentConstructorArguments, true)






coverageEnabled := true
coverageEnabled in actions := true
coverageEnabled in core := true

coverageMinimum := 80
coverageMinimum in actions := 80
coverageMinimum in core := 80

coverageFailOnMinimum := true


