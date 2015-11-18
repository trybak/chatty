name := "chatty"

version := "1.0"

def versionOfScala = "2.11.7"
scalaVersion := versionOfScala

libraryDependencies += "com.typesafe.akka" %% "akka-http-experimental" % "1.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.5"
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.4.0"
libraryDependencies += "org.scala-lang" % "scala-reflect" % versionOfScala
libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.0.4"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"
libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json-experimental" % "1.0"