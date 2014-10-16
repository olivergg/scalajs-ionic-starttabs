import sbt._
import Keys._
import org.apache.commons.io.FileUtils
import scala.scalajs.tools.io._
import java.nio.file.{ Paths, Files }
import java.nio.charset.StandardCharsets
/**
 *  See https://github.com/typesafehub/sbteclipse/wiki/Using-sbteclipse to develop this file in eclipse.
 */
object MyBuild extends Build {

  val outputCordovaJS = new File("ionic/www/js")
  val outputCordovaHTML = new File("ionic/www")

  lazy val prettier = new scala.xml.PrettyPrinter(80, 4)

  /**
   * Copy the given file to the output cordova js folder
   */
  def copyToCordova(file: java.io.File): Unit = {
    println(s"Copying file ${file.getAbsolutePath()} to $outputCordovaJS ")
    FileUtils.copyFileToDirectory(file, outputCordovaJS)
  }

  /**
   * Copy each file of the VirtualJSFile seq along with its associated Source Maps file if it exists.
   */
  def copySeqVirtualJSFileToCordova(jsFileList: Seq[VirtualJSFile]): Unit = {
    println("Invoking copyToCordova on a seq of VirtualJSFile")
    jsFileList.foreach {
      x =>
        x match {
          case ax: FileVirtualFile => {
            val fjs = new File(ax.path)
            val fmap = new File(ax.path.toString + ".map")
            copyToCordova(fjs)
            if (fmap.exists()) {
              copyToCordova(fmap)
            }
          }
          case _ => ;
        }
    }
  }

  /**
   * The inputFiles are the classpath files. We use an implicit parameter to avoid confusion with the .scala
   * files that are actually going to be compiled to html.
   */
  def compileToHtml()(implicit classPathFiles: Seq[sbt.File]): Unit = {
    // see http://www.scala-sbt.org/0.13.2/docs/Howto/classpaths.html
    val loader: ClassLoader = sbt.classpath.ClasspathUtilities.toLoader(classPathFiles)

    ///TODO : improve the following code, allow multiple html files to be compiled. store the settings (params, file name, etc..) elsewhere.
    val scalaHtmlClassName = "com.olivergg.html.Index"
    /// we instantiate the Index class here
    val index = loader.loadClass(scalaHtmlClassName).newInstance.asInstanceOf[{ def output(param: String): String }]
    val fragString = index.output("someParam")
    val stringToWrite = prettier.format(scala.xml.XML.loadString(fragString))
    // requires java 7 at least.
    val pathToWrite = Paths.get(outputCordovaHTML.getAbsolutePath() + "/index-poc.html")
    Files.write(pathToWrite, stringToWrite.getBytes(StandardCharsets.UTF_8))
    println(s"compileToHtml succeeded : $scalaHtmlClassName compiled to $pathToWrite")
  }

}