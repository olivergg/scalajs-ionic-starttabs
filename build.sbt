// Turn this project into a Scala.js project by importing these settings
scalaJSSettings

// Turn this project into a less css aware project by importing these settings.
lessSettings

name := "Scala-js Ionic Starter Application Tabs"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.4"

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
ScalaJSKeys.emitSourceMaps in (Compile, ScalaJSKeys.fastOptJS) := true

// Don't emit source Maps for fullOpt stage.
ScalaJSKeys.emitSourceMaps in (Compile, ScalaJSKeys.fullOptJS) := false

/// a symlink to the src folder may need to be added in the ionic/www folder in order to make SourceMaps work. 
ScalaJSKeys.relativeSourceMaps := true


///// launcher js configuration ////////////////////////
/// See http://www.scala-js.org/doc/tutorial.html : Automatically Creating a Launcher
ScalaJSKeys.persistLauncher in Compile := true

ScalaJSKeys.persistLauncher in Test := false


//// HTML5 Cordova, web application related modifications below

// Target directory for the CSS compiled with less.
(resourceManaged in (Compile, LessKeys.less)) := baseDirectory.value / "ionic" / "www" / "css" / "compiled"


/// Defined a task that returns the function to compile some scala files to HTML files (using ScalaTags).
/// the OptMode is defined in Build.scala
lazy val getCompileToHtmlPartialFunctionTask = taskKey[(String,OptMode,String) => Unit]("Get the partial function to compile scala files to HTML")

getCompileToHtmlPartialFunctionTask := {
  implicit val classPathFiles:Seq[sbt.File] = (fullClasspath in Runtime).value.files
  // return the partially applied function "compileToHtml" (a method defined in Build.scala with a (String,OptMode,String) => Unit signature)
  // the result of the task (accesible with .value) is then a function that can be applied.
  compileToHtml _
}


/// Defined a task that compiles all the files contained in htmlScalaSourceDir to HTML files in the outputCompiledHTML folder.
lazy val compileAllToHtmlTask = taskKey[Unit]("Compile all scala files contained in htmlScalaSourceDir (defined in the Build.scala) to HTML")

compileAllToHtmlTask := {
	getCompileToHtmlPartialFunctionTask.value("",NotRelevant, moduleName.value)
}


// Extends the original fastOptJS and fullOptJS tasks to copy the .js files to the output js folder (using a method defined in project/Build.scala)
// (See http://www.scala-sbt.org/0.13.1/docs/Detailed-Topics/Tasks.html#modifying-an-existing-task)
ScalaJSKeys.fastOptJS in Compile := {
	val originalResult=(ScalaJSKeys.fastOptJS in Compile).value
	getCompileToHtmlPartialFunctionTask.value("com.olivergg.html.Index",FastOpt, moduleName.value)
	originalResult
}

ScalaJSKeys.fullOptJS in Compile := { 
	val originalResult=(ScalaJSKeys.fullOptJS in Compile).value
	getCompileToHtmlPartialFunctionTask.value("com.olivergg.html.Index",FullOpt, moduleName.value)
	originalResult
}

/// Change the crossTarget of different tasks to output the fastOpt, fullOpt, jsdeps and launcher JS files in a custom output directory.
crossTarget in (Compile, ScalaJSKeys.fastOptJS) := outputCompiledJS

crossTarget in (Compile, ScalaJSKeys.packageScalaJSLauncher ) := outputCompiledJS

crossTarget in (Compile, ScalaJSKeys.packageJSDependencies ) := outputCompiledJS

crossTarget in (Compile, ScalaJSKeys.fullOptJS) := outputCompiledJS

/// Define a task to clean up the output JS directory
lazy val cleanOutputJS = taskKey[Unit]("Clean the output JS directory")

cleanOutputJS := {
	cleanOutputJSDir()
}
