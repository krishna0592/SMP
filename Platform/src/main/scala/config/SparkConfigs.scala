package config

import app.MainApp.{sc, spark, ssc}
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.DataStreamReader
import org.apache.spark.streaming.{Seconds, StreamingContext}


object SparkConfigs {

  def sparkSession(): SparkSession ={
    SparkSession.builder()
      .master(SMPConfigurations.getString(SMPConstant.SMP_MASTER))
      .appName(SMPConfigurations.getString(SMPConstant.SMP_APPNAME))
      .getOrCreate()
  }


  def sparkContext(): SparkContext ={
    spark.sparkContext
  }


  def streamingContext(): StreamingContext ={
    new StreamingContext(sc,Seconds(2))
  }


  def additionalSparkConfigs(): Unit ={
    sc.setLogLevel(SMPConfigurations.getString(SMPConstant.SMP_SPARK_LOGLEVEL))
    elsticSearchConfig()
  }


  def kafkaConfigRDD(): Map[String,Object] = {
    Map[String, Object](
      "bootstrap.servers" -> SMPConfigurations.getString(SMPConstant.SMP_KAFKA_BOOTSTRAP_URL),
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> SMPConfigurations.getString(SMPConstant.SMP_KAFKA_GROUP)
    )
  }

  def kafkaConfigDataFrame(topics: String): DataStreamReader  ={
    spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", SMPConfigurations.getString(SMPConstant.SMP_KAFKA_BOOTSTRAP_URL))
      .option("subscribe", topics)
    //.option("sep", "|")
    //.option("startingOffsets", "earliest")
  }


  def elsticSearchConfig(): Unit ={
    spark.conf.set("es.index.auto.create",SMPConfigurations.getBoolean(SMPConstant.SMP_ES_INDEX_AUTO_CREATE))
    spark.conf.set("spark.es.nodes",SMPConfigurations.getString(SMPConstant.SMP_ES_HOST))
    spark.conf.set("spark.es.port",SMPConfigurations.getInt(SMPConstant.SMP_ES_PORT))
    spark.conf.set("spark.es.nodes.wan.only",SMPConfigurations.getBoolean(SMPConstant.SMP_ES_NODES_WAN_ONLY))
    spark.conf.set("es.batch.size.entries",SMPConfigurations.getInt(SMPConstant.SMP_ES_BATCH_SIZE))
    spark.conf.set("es.nodes.wan.only",SMPConfigurations.getBoolean(SMPConstant.SMP_ES_SPARK_NODES_WAN_ONLY))
    spark.conf.set("es.net.ssl",SMPConfigurations.getBoolean(SMPConstant.SMP_ES_NET_SSL))
  }

}
