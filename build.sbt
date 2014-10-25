// Turn this project into a Scala.js project by importing these settings
scalaJSSettings

// Turn this project into a less css aware project by importing these settings.
lessSettings

name := "Scala-js Ionic Starter Application Tabs"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.2"

// Download and link sources for library dependencies (when using sbt eclipse)
EclipseKeys.withSource := true

resolvers += 
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

/// add local maven .m2 repo to resolve dependencies.
resolvers += Resolver.mavenLocal

libraryDependencies ++= Seq(
    "org.scala-lang.modules.scalajs" %%% "scalajs-dom" % "0.6",
    "com.scalatags" %%% "scalatags" % "0.4.2",
    "com.greencatsoft" %%% "scalajs-angular" % "0.2",
    "com.github.benhutchison" %%% "prickle" % "1.0.2"
)


ScalaJSKeys.jsDependencies += scala.scalajs.sbtplugin.RuntimeDOM

/// Webjars dependencies
ScalaJSKeys.jsDependencies += "org.webjars" % "ionic" % "1.0.0-beta.13" / "ionic.bundle.min.js"

skip in ScalaJSKeys.packageJSDependencies := false


////// SourceMaps configuration ////////////////////////
ScalaJSKeys.emitSourceMaps := true

/// a symlink to the src folder may need to be added in the ionic/www folder in order to make SourceMaps work. 
ScalaJSKeys.relativeSourceMaps := true


///// launcher js configuration ////////////////////////
/// See http://www.scala-js.org/doc/tutorial.html : Automatically Creating a Launcher
ScalaJSKeys.persistLauncher in Compile := true

ScalaJSKeys.persistLauncher in Test := false


//// HTML5 Cordova, web application related modifications below

// Target directory for the CSS compiled with less.
(resourceManaged in (Compile, LessKeys.less)) := baseDirectory.value / "ionic" / "www" / "css" / "compiled"


// Extends the original packageJSDependencies to copy the .js dependencies files to the ionic folder (using a method defined in project/Build.scala)
// (See http://www.scala-sbt.org/0.13.1/docs/Detailed-Topics/Tasks.html#modifying-an-existing-task)
ScalaJSKeys.packageJSDependencies in Compile := {
	val originalResult=(ScalaJSKeys.packageJSDependencies in Compile).value
	copyToOutputJS(originalResult)
	originalResult
}

// Extends the original packageScalaJSLauncher to copy the .js launcher
// (See http://www.scala-sbt.org/0.13.1/docs/Detailed-Topics/Tasks.html#modifying-an-existing-task)
ScalaJSKeys.packageScalaJSLauncher in Compile := {
	val originalResult=(ScalaJSKeys.packageScalaJSLauncher in Compile).value
	copyToOutputJS(originalResult.data)
	originalResult
}


/// Defined a task that returns the function to compile some scala files to HTML files (using ScalaTags).
/// the OptMode is defined in Build.scala
lazy val compileToHtmlTask = taskKey[(OptMode,String) => Unit]("Compile scala files inside the html package to HTML files in the output HTML directory")

compileToHtmlTask := {
  implicit val classPathFiles:Seq[sbt.File] = (fullClasspath in Runtime).value.files
  // return the partially applied function "compileToHtml" (a method defined in Build.scala with a (OptMode,String) => Unit signature)
  // the result of the task (accesible with .value) is then a function that can be applied.
  compileToHtml _
}

// Extends the original fastOptJS and fullOptJS tasks to copy the .js files to the output js folder (using a method defined in project/Build.scala)
// (See http://www.scala-sbt.org/0.13.1/docs/Detailed-Topics/Tasks.html#modifying-an-existing-task)
ScalaJSKeys.fastOptJS in Compile := {
	val originalResult=(ScalaJSKeys.fastOptJS in Compile).value
	copySeqToOutputJS(originalResult.allCode)
	compileToHtmlTask.value(FastOpt, moduleName.value)
	originalResult
}

ScalaJSKeys.fullOptJS in Compile := { 
	val originalResult=(ScalaJSKeys.fullOptJS in Compile).value
	copySeqToOutputJS(originalResult.allCode)
	compileToHtmlTask.value(FullOpt, moduleName.value)
	originalResult
}

