package config

import java.util.Properties

import helper.SMPLogger

import scala.util.Try

object SMPConfigurations {

  private var configProperties: Properties = null
  private var version: Properties = null
  private var sb: StringBuilder = null

  private def getConfigProperties(): Properties ={
    if(configProperties == null){
      configProperties = new Properties()
      configProperties.load(getClass.getClassLoader.getResourceAsStream("ingestion.properties"))
    }
    configProperties
  }

  private def versionProp(): Properties ={
    if (version == null){
      version = new Properties()
      version.load(getClass.getClassLoader.getResourceAsStream("version.properties"))
    }
    version
  }

  def getVersion(): String ={
    versionProp().get(SMPConstant.SMP_VERSION).toString
  }


  def getString(key: String): String ={
    getConfigProperties().get(key).asInstanceOf[String]
  }

  def getBoolean(key: String): Boolean = {
    Try(getString(key).toBoolean).getOrElse{
      SMPLogger.exception("SMP-Platform","","","ClassCastException  - Exception while parsing Boolean parsing " + getString(key),"")
      false   //Note: if exception occured by default we are assigning false
    }
  }

  def getInt(key: String): Int ={
    Try(getString(key).toInt).getOrElse {
      SMPLogger.exception("SMP-Platform", "", "", "NumberFormatException - couldn't able to convert value " + getString(key), "")
      0 //Note: if exception occured by default we are assigning 0
    }
  }

  def getSB(): StringBuilder = {
    if(sb == null){
      sb = new StringBuilder()
    }
    sb
  }
}
