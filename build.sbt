name := "learning_scalaz"

version := "0.1"

scalaVersion := "2.12.6"

val scalazVersion = "7.2.26"

scalacOptions in ThisBuild ++= Seq(
  "-language:_",
  "-Ypartial-unification",
  "-Xfatal-warnings",
  "-language:higherKinds"
)

libraryDependencies ++= Seq(
  "com.github.mpilquist" %% "simulacrum"     % "0.13.0",
  "org.scalaz"           %% "scalaz-core"    % "7.2.26",
  "org.scalaz" %% "scalaz-scalacheck-binding" % "7.2.26-scalacheck-1.13",
  "org.scalaz" %% "scalaz-scalacheck-binding" % "7.2.26-scalacheck-1.13" % Test

)

libraryDependencies ++= Seq(
//  "org.scalaz" %% "scalaz-core" % scalazVersion,
//  "org.scalaz" %% "scalaz-effect" % scalazVersion,
//  "org.scalaz" %% "scalaz-typelevel" % scalazVersion,
//  "org.scalaz" %% "scalaz-scalacheck-binding" % scalazVersion % "test"
)

libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.7")
addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)

scalacOptions += "-feature"
initialCommands in console := "import scalaz._, Scalaz._"

initialCommands in console in Test := "import scalaz._, Scalaz._, scalacheck.ScalazProperties._, scalacheck.ScalazArbitrary._,scalacheck.ScalaCheckBinding._"
