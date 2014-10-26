package com.olivergg.html
/**
 * Use this trait to indicate that a class is candidate for compilation to HTML.
 */
trait HtmlCompilable {

  /**
   * The HTML string to write
   */
  def output(optMode: String, moduleName: String): String
  
  /**
   * The path of the file to write
   */
  def filePath(optMode:String) : String
  
  /**
   * Generate an HTML5 DOCTYPE.
   */
  def withDocType : Boolean

}