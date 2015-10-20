// See http://www.scala-js.org/doc/sbt/cross-building.html
// and https://github.com/scala-js/scalajs-cross-compile-example/blob/master/build.sbt
// for cross building configuration.
// There are 3 projects defined : root, appJS and appJVM.
// root is just an aggregation of appJS and appJVM but can be imported in a IDE (to edit the build definition) as well.
// appJS and appJVM share the appSharedSettings
// appJS uses scalaJSSettings which is specific for javascript development.
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

///////////////////// END OF TAKS DEFINITION ///////////

lazy val appSharedSettings = Seq(
  version := "0.1-SNAPSHOT",
  scalaVersion := "2.11.7",
  unmanagedSourceDirectories in Compile += baseDirectory.value / ".." / "app-shared" / "src" / "main" / "scala",
  // Download and link sources for library dependencies (when using sbt eclipse)
  EclipseKeys.withSource := true,
  // do not skip the parents so that the root project can be imported in eclipse.
  EclipseKeys.skipParents in ThisBuild := false,
  /// add local maven .m2 repo to resolve dependencies.
  resolvers ++= Seq("Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots", Resolver.mavenLocal)
)

lazy val appJS = project.in(file("app-js"))
   // Turn this project into a Scala.js project by enabling ScalaJSPlugin
  .enablePlugins(ScalaJSPlugin)
  .settings(appSharedSettings: _*)
  .settings(
    // Add JS-specific settings here
    name := "Scala-js Ionic Starter Application Tabs",
    normalizedName := "ionic-starttabs",
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.8.0",
      "com.greencatsoft" %%% "scalajs-angular" % "0.6-SNAPSHOT",
      "com.github.benhutchison" %%% "prickle" % "1.1.9"
    ),
    jsDependencies += RuntimeDOM,
    /// Webjars dependencies
    jsDependencies += "org.webjars" % "ionic" % "1.1.0" / "ionic.bundle.js" minified "ionic.bundle.min.js",
    // jsDependencies += "org.webjars" % "angular-sanitize" % "1.3.11" / "angular-sanitize.min.js",
    skip in packageJSDependencies := false,
    ////// SourceMaps configuration ////////////////////////
    emitSourceMaps in(Compile, fastOptJS) := true,
    // Don't emit source Maps for fullOpt stage.
    emitSourceMaps in(Compile, fullOptJS) := false,
    /// a symlink to the src folder may need to be added in the ionic/www folder in order to make SourceMaps work.
    relativeSourceMaps := true,
    ///// launcher js configuration ////////////////////////
    /// See http://www.scala-js.org/doc/tutorial.html : Automatically Creating a Launcher
    persistLauncher in Compile := true,
    persistLauncher in Test := false,
    //// HTML5 Cordova, web application related modifications below
    /// Change the crossTarget of different tasks to output the fastOpt, fullOpt, jsdeps and launcher JS files in a custom output directory.
    crossTarget in(Compile, fastOptJS) := outputCompiledJS,
    crossTarget in(Compile, packageScalaJSLauncher) := outputCompiledJS,
    crossTarget in(Compile, packageJSDependencies) := outputCompiledJS,
    crossTarget in(Compile, fullOptJS) := outputCompiledJS,
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
      "com.lihaoyi" %% "scalatags" % "0.5.2"
    ),
    getCompileToHtmlPartialFunctionTask := {
      implicit val classPathFiles: Seq[sbt.File] = (fullClasspath in Runtime).value.files
      implicit val s: TaskStreams = streams.value
      // return the partially applied function "compileToHtml" (a method defined in Build.scala with a (String,OptMode,String) => Unit signature)
      // the result of the task (accesible with .value) is then a function that can be applied.
      compileToHtml
    },
    compileHtmlDevTask := {
      getCompileToHtmlPartialFunctionTask.value("", FastOpt, moduleName.value)
    },
    compileHtmlProdTask := {
      getCompileToHtmlPartialFunctionTask.value("", FullOpt, moduleName.value)
    }
  )

addCommandAlias("watch", "~ ;packageJSDependencies ;packageScalaJSLauncher ;compileHtmlDevTask ;fastOptJS")
addCommandAlias("dist", ";cleanOutputJS ;packageJSDependencies ;packageScalaJSLauncher ;compileHtmlProdTask ;fullOptJS")
