resolvers += "Typesafe Repository" at "https://repo.typesafe.com/typesafe/releases/"

/*
libraryDependencies ++= Seq(
	"org.scoverage" % "sbt-scoverage" % "1.5.1",
	"com.codacy" % "sbt-codacy-coverage" % "1.3.11"
)
*/

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.5.1")

addSbtPlugin("com.codacy" % "sbt-codacy-coverage" % "1.3.11")

