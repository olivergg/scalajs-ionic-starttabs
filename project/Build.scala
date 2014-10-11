import sbt._
import Keys._
import org.apache.commons.io.FileUtils
import scala.scalajs.tools.io._

object MyBuild extends Build {

  val outputCordovaJS = new File("ionic/www/js")

  def copyToCordova(file: java.io.File): Unit = {
    println(s"Copying file ${file.getAbsolutePath()} to $outputCordovaJS ")
    FileUtils.copyFileToDirectory(file, outputCordovaJS)
  }

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

}