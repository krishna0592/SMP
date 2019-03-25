package config

import java.util.Properties

object IngestionConfigurations {

  private var configProperties: Properties = null

  private def getConfigProperties(): Properties ={
    if(configProperties == null){
      configProperties = new Properties()
      configProperties.load(getClass.getClassLoader.getResourceAsStream("ingestion.properties"))
    }
    configProperties
  }

  def getString(key: String): String ={
    getConfigProperties().get(key).asInstanceOf[String]
  }

  def getBoolean(key: String): Boolean = {
    getConfigProperties().get(key).asInstanceOf[Boolean]
  }

  def getInt(key: String): Int ={
    getConfigProperties().get(key).asInstanceOf[Int]
  }
}
