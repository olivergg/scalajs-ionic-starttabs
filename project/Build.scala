import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import scala.language.reflectiveCalls
import scala.reflect.io.Path
import scala.scalajs.tools.io.FileVirtualFile
import scala.scalajs.tools.io.VirtualJSFile
import org.apache.commons.io.FileUtils
import scala.util.Try
import sbt.Build
import scala.util.Success
import scala.util.Failure
/**
 *  See https://github.com/typesafehub/sbteclipse/wiki/Using-sbteclipse to develop this file in eclipse.
 *
 *  If you want to get Eclipse support for the sbt build definition, e.g. for your Build.scala file, follow these steps:
 *
 * If you are not using sbteclipse as as global plugin, which is the recommended way, but as a local plugin for your project, you first have to add sbteclipse as a plugin (addSbtPlugin(...)) to the build definition project, i.e. to project/project/plugins.sbt
 * In the sbt session, execute reload plugins
 * Set the name of the build definition project to something meaningful: set name := "sbt-build"
 * Execute eclipse and then reload return
 * Import the build definition project into Eclipse and add the root directory to the build path
 *
 *
 */
object MyBuild extends Build {

  // customizable val here
  lazy val outputCompiledJS = new File("ionic/www/js")
  lazy val outputCompiledHTML = new File("ionic/www")
  lazy val htmlScalaSourceDir = "com/olivergg/html"

  /////////////////////////////////////////
  /// private vals below
  // Pretty printer for HTML
  private val prettier = new scala.xml.PrettyPrinter(120, 4)
  // Regex to capture the complete path of a class in the filesystem
  private val MatchFQCN = """.*classes\/(.*)\.class""".r

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
    println("Invoking copySeqToOutputJS on a seq of VirtualJSFile")
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
   * Structural type equivalent to the HtmlCompilable trait.
   * FIXME Ideally, we should use the HtmlCompilable trait here, but it is not visible from the build definition.
   * a solution would be to put it in a separate project and make both build definition and projection definition depends on it.
   *
   */
  type HtmlCompilableStructType = {
    def useOptMode: Boolean
    def setOptMode(optMode: String): Unit
    def setModuleName(moduleName: String): Unit
    def filePath: String
    def output: String
    def withDocType: Boolean
  }

  /**
   * The inputFiles are the classpath files. We use an implicit parameter to avoid confusion with the .scala
   * files that are actually going to be compiled to html (those are determined using reflection)).
   * TODO : find a way to leverage incremental compilation to only compile files that have actually been modified (could be tricky since this task depends on the fullClassPath in Runtime to retrieve the classes).
   */
  def compileToHtml(fqcn: String = "", optMode: OptMode, moduleName: String)(implicit classPathFiles: Seq[sbt.File]): Unit = {
    // println(s"Entering compileToHtml for fqcn = $fqcn, optMode=$optMode, moduleName=$moduleName")
    // see http://www.scala-sbt.org/0.13.2/docs/Howto/classpaths.html
    val loader: ClassLoader = sbt.classpath.ClasspathUtilities.toLoader(classPathFiles)
    val withoutJarClassPathFiles = classPathFiles.filterNot(f => f.getAbsolutePath().endsWith(".jar"))
    require(withoutJarClassPathFiles.size == 1, "there should be only one non jar class path element")
    val htmlScalaSourceAbsoluteDir = new File(withoutJarClassPathFiles(0).getAbsolutePath() + "/" + htmlScalaSourceDir)

    val filteredIterator = Path(htmlScalaSourceAbsoluteDir) walkFilter { p =>
      // recursively search for .class files that do not contain a  $  sign.
      p.isDirectory || (!p.name.contains("""$""") && p.name.endsWith(".class"))
    }

    /**
     * Local method to convert a filesystem file path of a .class to the fully qualified class name.
     */
    def convertToFQCN(path: Path): String = path.path match {
      case MatchFQCN(innerPath) => innerPath.replaceAll("""/""", """.""")
      case _ => println("something went wrong with the matching of the path " + path); path.path
    }
    /**
     * Local method to create a filtering method to keep only the FQCN that matches the input fqcn parameter if it is not empty.
     */
    def matchInputFqcn(in: String): Boolean = (fqcn.isEmpty() || in == fqcn)
    val iteratorMappedToFQCN = filteredIterator.map(convertToFQCN(_)).filter(matchInputFqcn(_))

    for (f <- iteratorMappedToFQCN) {
      // load the Object using reflection (see http://www.scala-lang.org/old/node/7065).
      val classz = Class.forName(f + "$", true, loader)
      if (!classz.isInterface()) {
        val tryClassInstance = Try(classz.getField("MODULE$").get(null).asInstanceOf[HtmlCompilableStructType])
        tryClassInstance match {
          case Success(classInstance) if (!classInstance.useOptMode || optMode != NotRelevant) => {
            classInstance.setOptMode(optMode.name)
            classInstance.setModuleName(moduleName)
            val fileName = classInstance.filePath
            val fragString = classInstance.output
            val withDocType = classInstance.withDocType
            // append a DOCTYPE (if needed) and pretty format the string to write
            val stringToWrite = (if (withDocType) "<!DOCTYPE html>\n" else "") + prettier.format(scala.xml.XML.loadString(fragString))
            val pathToWrite = Paths.get(outputCompiledHTML.getAbsolutePath() + "/" + fileName)
            Files.write(pathToWrite, stringToWrite.getBytes(StandardCharsets.UTF_8))
            println(s"compileToHtml succeeded : $f compiled to $pathToWrite")
          }
          case Failure(err) => println(s"Failed compiling $f with error $err but continue anyway to treat other files !")
          case _ => println(s"Ignoring $f")
        }
      }
    }
  }

  abstract sealed class OptMode(val name: String)
  case object FastOpt extends OptMode("fastOpt")
  case object FullOpt extends OptMode("fullOpt")
  case object NotRelevant extends OptMode("DONTUSE")

}