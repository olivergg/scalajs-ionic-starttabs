import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import scala.scalajs.tools.io.FileVirtualFile
import scala.scalajs.tools.io.VirtualJSFile
import org.apache.commons.io.FileUtils
import scala.language.reflectiveCalls
import sbt.Build
import sbt.File
import java.io.File
/**
 *  See https://github.com/typesafehub/sbteclipse/wiki/Using-sbteclipse to develop this file in eclipse.
 */
object MyBuild extends Build {

  lazy val outputCompiledJS = new File("ionic/www/js")
  lazy val outputCompiledHTML = new File("ionic/www")

  lazy val htmlScalaSourcePackage = "com.olivergg.html"

  // a seq of triplet ( class name , function to get the html file name, boolean with doctype)
  // class name : the name of the class relative to the package defined in htmlScalaSourcePackage
  // function : a function (String => String) that takes the optMode in parameter and returns the file path (relative to the output html folder).
  // boolean : indicate whether a DOCTYPE should be generated.
  lazy val classNameToHtmlSeq: Seq[(String, (OptMode => String), Boolean)] = Seq(
    ("Index", { optMode: OptMode =>
      optMode match {
        case FastOpt => "index-dev.html"
        case FullOpt => "index-prod.html"
      }
    }, true),
    ("template.Test", { _: OptMode => "templates/test.html" }, false))

  /////////////////////////////////////////
  private val prettier = new scala.xml.PrettyPrinter(120, 4)

  /**
   * Copy the given file to the output cordova js folder
   */
  def copyToOutputJS(file: java.io.File): Unit = {
    println(s"Copying file ${file.getAbsolutePath()} to $outputCompiledJS ")
    FileUtils.copyFileToDirectory(file, outputCompiledJS)
  }

  /**
   * Copy each file of the VirtualJSFile seq along with its associated Source Maps file if it exists.
   */
  def copySeqToOutputJS(jsFileList: Seq[VirtualJSFile]): Unit = {
    println("Invoking copyToCordova on a seq of VirtualJSFile")
    jsFileList.foreach {
      x =>
        x match {
          case ax: FileVirtualFile => {
            val fjs = new File(ax.path)
            val fmap = new File(ax.path.toString + ".map")
            copyToOutputJS(fjs)
            if (fmap.exists()) {
              copyToOutputJS(fmap)
            }
          }
          case _ => ;
        }
    }
  }

  /**
   * The inputFiles are the classpath files. We use an implicit parameter to avoid confusion with the .scala
   * files that are actually going to be compiled to html (those are set with the classNameToHtmlMap)).
   */
  def compileToHtml(optMode: OptMode, moduleName: String)(implicit classPathFiles: Seq[sbt.File]): Unit = {
    // see http://www.scala-sbt.org/0.13.2/docs/Howto/classpaths.html
    val loader: ClassLoader = sbt.classpath.ClasspathUtilities.toLoader(classPathFiles)

    for ((className, funcToGetFilePath, withDocType) <- classNameToHtmlSeq) {
      val fqClassName = htmlScalaSourcePackage + "." + className
      /// we instantiate the Index class here
      val index = loader.loadClass(fqClassName).newInstance.asInstanceOf[{ def output(optMode: String, moduleName: String): String }]
      // the raw string from scalatags
      val fragString = index.output(optMode.name, moduleName)
      // pretty format the string
      val stringToWrite = (if (withDocType) "<!DOCTYPE html>\n" else "") + prettier.format(scala.xml.XML.loadString(fragString))
      val pathToWrite = Paths.get(outputCompiledHTML.getAbsolutePath() + "/" + funcToGetFilePath(optMode))
      Files.write(pathToWrite, stringToWrite.getBytes(StandardCharsets.UTF_8))
      println(s"compileToHtml succeeded : $className compiled to $pathToWrite")
    }

  }

  abstract sealed class OptMode(val name: String)
  object FastOpt extends OptMode("fastOpt")
  object FullOpt extends OptMode("fullOpt")

}