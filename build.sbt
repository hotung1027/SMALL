name := "Scala-Matlab"

version := "0.1"

scalaVersion := "2.12.4"

val SCALACTIC_FILL_FILE_PATHNAMES = "yes"

scalacOptions ++= Seq(
	"-feature",
	"-deprecation",
	"-encoding", "UTF-8",
	"-unchecked",
	"-Xlint",
	"-Yno-adapted-args",
	"-Ywarn-dead-code",
	"-Ywarn-value-discard",
	"-Xfuture",
	"-Xexperimental"
)


// https://mvnrepository.com/artifact/org.scala-lang/scala-reflect
libraryDependencies ++=
	Seq(
		"org.scala-lang" % "scala-reflect" % "2.12.4",
		"org.scalacheck" %% "scalacheck" % "1.13.5" % Test,
		"junit" % "junit" % "4.10" % Test,
		"org.scalactic" %% "scalactic" % "3.0.4" ,
		"org.scalatest" %% "scalatest" % "3.0.4" % "test",
		//The fastutil library provides convenient collection classes for primitive types that are compatible with the Java standard library.
		"it.unimi.dsi" % "fastutil" % "7.0.7" % "provided"
	)
