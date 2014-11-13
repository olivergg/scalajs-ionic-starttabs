package com.olivergg
/**
 * A trait that indicates that a scala object is compilable to HTML (using scalatags).
 */
trait HtmlCompilable {
  var optMode: String = _
  var moduleName: String = _

  /**
   * A method to override to indicate that the optMode will be used
   */
  def useOptMode : Boolean = false
  /**
   * Set the optMode to use 
   */
  def setOptMode(optMode: String): Unit = this.optMode = optMode
  /**
   * Set the module name (the sbt moduleName).
   */
  def setModuleName(moduleName: String): Unit = this.moduleName = moduleName
  /**
   * Set the output file path
   */
  def filePath: String
  /**
   * Returns the string that is written to the html file
   */
  def output: String
  /**
   * Indicates whether a DOCTYPE will be generated.
   */
  def withDocType: Boolean = false
  

}