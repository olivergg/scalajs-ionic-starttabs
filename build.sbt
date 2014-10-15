// Turn this project into a Scala.js project by importing these settings
scalaJSSettings

// Turn this project into a less css aware project by importing these settings.
lessSettings

name := "Scala-js Ionic Starter Application Tabs"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.2"

// Download and link sources for library dependencies
EclipseKeys.withSource := true

resolvers += 
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies ++= Seq(
    "org.scala-lang.modules.scalajs" %%% "scalajs-dom" % "0.6",
    "com.scalatags" %%% "scalatags" % "0.4.0",
    "com.greencatsoft" %%% "scalajs-angular" % "0.2-SNAPSHOT",
    "com.github.benhutchison" %%% "prickle" % "1.0.2"
)

ScalaJSKeys.jsDependencies += scala.scalajs.sbtplugin.RuntimeDOM

ScalaJSKeys.jsDependencies += "org.webjars" % "ionic" % "1.0.0-beta.5b" / "ionic.bundle.min.js"

skip in ScalaJSKeys.packageJSDependencies := false

ScalaJSKeys.emitSourceMaps := true

/// a symlink to the src folder may need to be added in the ionic/www folder in order to make SourceMaps work. 
ScalaJSKeys.relativeSourceMaps := true


//// HTML5 Cordova, web application related modifications below

// Target directory for the CSS compiled with less.
(resourceManaged in (Compile, LessKeys.less)) := baseDirectory.value / "ionic" / "www" / "css" / "compiled"

// Extends the original fastOptJS and fullOptJS tasks by calling a custom method from the project/Build.scala file.
// (See http://www.scala-sbt.org/0.13.1/docs/Detailed-Topics/Tasks.html#modifying-an-existing-task)
ScalaJSKeys.packageJSDependencies in Compile := {
	val originalResult=(ScalaJSKeys.packageJSDependencies in Compile).value
	copyToCordova(originalResult)
	originalResult
}

ScalaJSKeys.fastOptJS in Compile := {
	val originalResult=(ScalaJSKeys.fastOptJS in Compile).value
	copySeqVirtualJSFileToCordova(originalResult.allCode)
	// see http://www.scala-sbt.org/0.13.2/docs/Howto/classpaths.html
	val files:Seq[File] = (fullClasspath in Runtime).value.files
	val loader: ClassLoader = sbt.classpath.ClasspathUtilities.toLoader(files)
	/// we instantiate the Index class here
	loader.loadClass("com.olivergg.Index").newInstance()
	originalResult
}

ScalaJSKeys.fullOptJS in Compile := { 
	val originalResult=(ScalaJSKeys.fullOptJS in Compile).value
	copySeqVirtualJSFileToCordova(originalResult.allCode)
	originalResult
}
