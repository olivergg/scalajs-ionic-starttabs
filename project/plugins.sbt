resolvers += Resolver.url("scala-js-releases",
        url("http://dl.bintray.com/content/scala-js/scala-js-releases"))(
 	    Resolver.ivyStylePatterns)

resolvers += "softprops-maven" at "http://dl.bintray.com/content/softprops/maven"

addSbtPlugin("me.lessis" % "less-sbt" % "0.2.2")

addSbtPlugin("org.scala-lang.modules.scalajs" % "scalajs-sbt-plugin" % "0.5.4")

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.5.0")

libraryDependencies ++= Seq(
  // Add your project dependencies here,
  "org.apache.commons" % "commons-io" % "1.3.2"
)
