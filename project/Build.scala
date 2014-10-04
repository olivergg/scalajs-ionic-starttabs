import sbt._
import Keys._
import org.apache.commons.io.FileUtils
import scala.scalajs.tools.io._

object MyBuild extends Build {

  val outputCordovaJS = "ionic/www/js"

  def copyToCordova(jsFileList: Seq[VirtualJSFile]): Unit = {
    println("Invoking copyToCordova")
    val output = new File(outputCordovaJS)
    jsFileList.foreach {
      x =>
        x match {
          case ax: FileVirtualFile => {
            val fjs = new File(ax.path)
            val fmap = new File(ax.path.toString + ".map")
            println(s"Copying file ${ax.path} to $outputCordovaJS")
            FileUtils.copyFileToDirectory(fjs, output)
            if (fmap.exists()) {
              println(s"Copying file ${ax.path.toString + ".map"} to $outputCordovaJS")
              FileUtils.copyFileToDirectory(fmap, output)
            }
          }
          case _ => ;
        }
    }
  }

}