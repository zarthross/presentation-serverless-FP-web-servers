enablePlugins(TutPlugin)

scalaVersion in ThisBuild := "2.12.8"

tutSourceDirectory := baseDirectory.value / "slides"
watchSources += baseDirectory.value / "slides"
tut := {
	val r = tut.value
	val revealFolder = baseDirectory.value / "reveal.js"
	tutTargetDirectory.value.listFiles().foreach(
		file => Sync.copy(file, revealFolder / file.name)
	)
	r
}
tutQuick := {
	val r = tutQuick.value
	val revealFolder = baseDirectory.value / "reveal.js"
	tutTargetDirectory.value.listFiles().foreach(
		file => Sync.copy(file, revealFolder / file.name)
	)
	r
}

scalacOptions ++= Seq(
	"-Ypartial-unification",
	"-feature",                          // Emit warning and location for usages of features that should be imported explicitly.
	"-language:existentials",            // Existential types (besides wildcard types) can be written and inferred
	"-language:experimental.macros",     // Allow macro definition (besides implementation and application)
	"-language:higherKinds",             // Allow higher-kinded types
	"-language:implicitConversions"     // Allow definition of implicit functions called views
)

libraryDependencies ++= Seq(
	"org.typelevel" %% "cats-effect" % "1.2.0",
	"org.typelevel" %% "cats-core" % "1.6.0"
)

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.7")
