resolvers += Resolver.url("scala-js-releases",
        url("http://dl.bintray.com/content/scala-js/scala-js-releases"))(
 	    Resolver.ivyStylePatterns)

resolvers += "softprops-maven" at "http://dl.bintray.com/content/softprops/maven"

resolvers += Resolver.url(
  "bintray-sbt-plugin-releases",
  url("http://dl.bintray.com/content/sbt/sbt-plugin-releases"))(
    Resolver.ivyStylePatterns)

addSbtPlugin("me.lessis" % "less-sbt" % "0.2.2")

addSbtPlugin("org.scala-lang.modules.scalajs" % "scalajs-sbt-plugin" % "0.5.5")

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.5.0")

addSbtPlugin("me.lessis" % "bintray-sbt" % "0.1.1")

libraryDependencies ++= Seq(
  // Add your project dependencies here,
  "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.2",
  "org.clapper" % "classutil_2.11" % "1.0.5"
)
