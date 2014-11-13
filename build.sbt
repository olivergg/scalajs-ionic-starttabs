// See http://www.scala-js.org/doc/sbt/cross-building.html 
// and https://github.com/scala-js/scalajs-cross-compile-example/blob/master/build.sbt 
// for cross building configuration.
// There are 3 projects defined : root, appJS and appJVM.
// root is just an aggregation of appJS and appJVM but can be imported in a IDE (to edit the build definition) as well.
// appJS and appJVM share the appSharedSettings
// appJS use scalaJSSettings and lessSettings which are specific for javascript development.
lazy val root = project.in(file(".")).aggregate(appJS, appJVM).settings(
  name := "Scala-js Ionic Starter Application Tabs (Root)",
  publish := {},
  publishLocal := {}
)



/// Defined a task that returns the function to compile some scala files to HTML files (using ScalaTags).
/// the OptMode is defined in Build.scala
lazy val getCompileToHtmlPartialFunctionTask = taskKey[(String,OptMode,String) => Unit]("Get the partial function to compile scala files to HTML")

/// Defined a task that compiles (for the fastopt stage) all the files contained in htmlScalaSourceDir to HTML files in the outputCompiledHTML folder.
lazy val compileHtmlDevTask = taskKey[Unit]("Compile all scala files contained in htmlScalaSourceDir (defined in the Build.scala) to HTML")

/// Defined a task that compiles (for the fullopt stage) all the files contained in htmlScalaSourceDir to HTML files in the outputCompiledHTML folder.
lazy val compileHtmlProdTask = taskKey[Unit]("Compile all scala files contained in htmlScalaSourceDir (defined in the Build.scala) to HTML")

/// Define a task to clean up the output JS directory
lazy val cleanOutputJS = taskKey[Unit]("Clean the output JS directory")

lazy val appSharedSettings = Seq(
    version := "0.1-SNAPSHOT",
    scalaVersion := "2.11.4",
    unmanagedSourceDirectories in Compile += baseDirectory.value / ".." / "app-shared" / "src" / "main" / "scala",
    // Download and link sources for library dependencies (when using sbt eclipse)
	EclipseKeys.withSource := true,
	// do not skip the parents so that the root project can be imported in eclipse.
	EclipseKeys.skipParents in ThisBuild := false,
	/// add local maven .m2 repo to resolve dependencies.
	resolvers ++= Seq("Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",Resolver.mavenLocal)
)

lazy val appJS = project.in(file("app-js"))
   // Turn this project into a Scala.js project by importing these settings
  .settings(scalaJSSettings: _*)
  // Turn this project into a less css aware project by importing these settings.
  .settings(lessSettings: _*)
  .settings(appSharedSettings: _*)
  .settings(
    // Add JS-specific settings here
    name := "Scala-js Ionic Starter Application Tabs",
    normalizedName := "ionic-starttabs",
    libraryDependencies ++= Seq(
    "org.scala-lang.modules.scalajs" %%% "scalajs-dom" % "0.6",
    "com.greencatsoft" %%% "scalajs-angular" % "0.2",
    "com.github.benhutchison" %%% "prickle" % "1.0.2"
	),
	ScalaJSKeys.jsDependencies += scala.scalajs.sbtplugin.RuntimeDOM,
	/// Webjars dependencies
	ScalaJSKeys.jsDependencies += "org.webjars" % "ionic" % "1.0.0-beta.13" / "ionic.bundle.min.js",
	skip in ScalaJSKeys.packageJSDependencies := false,
	////// SourceMaps configuration ////////////////////////
	ScalaJSKeys.emitSourceMaps in (Compile, ScalaJSKeys.fastOptJS) := true,
	// Don't emit source Maps for fullOpt stage.
	ScalaJSKeys.emitSourceMaps in (Compile, ScalaJSKeys.fullOptJS) := false,
	/// a symlink to the src folder may need to be added in the ionic/www folder in order to make SourceMaps work. 
	ScalaJSKeys.relativeSourceMaps := true,
	///// launcher js configuration ////////////////////////
	/// See http://www.scala-js.org/doc/tutorial.html : Automatically Creating a Launcher
	ScalaJSKeys.persistLauncher in Compile := true,
	ScalaJSKeys.persistLauncher in Test := false,
	//// HTML5 Cordova, web application related modifications below
	// Target directory for the CSS compiled with less.
	(resourceManaged in (Compile, LessKeys.less)) := baseDirectory.value / "ionic" / "www" / "css" / "compiled",
	/// Change the crossTarget of different tasks to output the fastOpt, fullOpt, jsdeps and launcher JS files in a custom output directory.
	crossTarget in (Compile, ScalaJSKeys.fastOptJS) := outputCompiledJS,
	crossTarget in (Compile, ScalaJSKeys.packageScalaJSLauncher ) := outputCompiledJS,
	crossTarget in (Compile, ScalaJSKeys.packageJSDependencies ) := outputCompiledJS,
	crossTarget in (Compile, ScalaJSKeys.fullOptJS) := outputCompiledJS,
	cleanOutputJS := {
		implicit val s: TaskStreams = streams.value
		cleanOutputJSDir()
	}
	)
	

lazy val appJVM = project.in(file("app-jvm"))
  .settings(appSharedSettings: _*)
  .settings(
    // Add JVM-specific settings here
    name := "Scala-js Ionic Starter Application Tabs (JVM)",
    normalizedName := "ionic-starttabs",
    libraryDependencies ++= Seq(
    "com.scalatags" %%% "scalatags" % "0.4.2"
	),
	getCompileToHtmlPartialFunctionTask := {
	  implicit val classPathFiles:Seq[sbt.File] = (fullClasspath in Runtime).value.files
	  implicit val s: TaskStreams = streams.value
	  // return the partially applied function "compileToHtml" (a method defined in Build.scala with a (String,OptMode,String) => Unit signature)
	  // the result of the task (accesible with .value) is then a function that can be applied.
	  compileToHtml _
	},
	compileHtmlDevTask := {
		getCompileToHtmlPartialFunctionTask.value("",FastOpt, moduleName.value)
	},
	compileHtmlProdTask :={
		getCompileToHtmlPartialFunctionTask.value("",FullOpt, moduleName.value)
	}
  )


addCommandAlias("dist", ";cleanOutputJS ;packageJSDependencies ;packageScalaJSLauncher ;compileHtmlProdTask ;fullOptJS") 




