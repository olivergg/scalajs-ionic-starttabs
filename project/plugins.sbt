resolvers += Resolver.url("scala-js-releases",
  url("http://dl.bintray.com/content/scala-js/scala-js-releases"))(
  Resolver.ivyStylePatterns)

resolvers += "softprops-maven" at "http://dl.bintray.com/content/softprops/maven"

resolvers += Resolver.url(
  "bintray-sbt-plugin-releases",
  url("http://dl.bintray.com/content/sbt/sbt-plugin-releases"))(
  Resolver.ivyStylePatterns)

addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.5")

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.5.0")

addSbtPlugin("me.lessis" % "bintray-sbt" % "0.1.1")

libraryDependencies ++= Seq(
  // Add your project dependencies here,
  "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.2",
  "org.clapper" % "classutil_2.11" % "1.0.5"
)
