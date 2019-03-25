package config

import app.IngestionApp.{sc, spark, ssc}
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.DataStreamReader
import org.apache.spark.streaming.{Seconds, StreamingContext}


object SparkConfigs {

  def sparkSession(): SparkSession ={
    SparkSession.builder()
      .master(IngestionConfigurations.getString(IngestionConstant.SMP_INGESTION_MASTER))
      .appName(IngestionConfigurations.getString(IngestionConstant.SMP_INGESTION_APPNAME))
      .getOrCreate()
  }


  def sparkContext(): SparkContext ={
    spark.sparkContext
  }


  def streamingContext(): StreamingContext ={
    new StreamingContext(sc,Seconds(2))
  }


  def additionalSparkConfigs(): Unit ={
    sc.setLogLevel(IngestionConfigurations.getString(IngestionConstant.SMP_INGESTION_LOGLEVEL))
    elsticSearchConfig()
  }


  def kafkaConfigRDD(): Map[String,Object] = {
    Map[String, Object](
      "bootstrap.servers" -> IngestionConfigurations.getString(IngestionConstant.SMP_INGESTION_KAFKA_BOOTSTRAP_URL),
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> IngestionConfigurations.getString(IngestionConstant.SMP_INGESTION_KAFKA_GROUP)
    )
  }

  def kafkaConfigDataFrame(topics: String): DataStreamReader  ={
    spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", IngestionConfigurations.getString(IngestionConstant.SMP_INGESTION_KAFKA_BOOTSTRAP_URL))
      .option("subscribe", topics)
    //.option("sep", "|")
    //.option("startingOffsets", "earliest")
  }


  def elsticSearchConfig(): Unit ={
    spark.conf.set("es.index.auto.create",IngestionConfigurations.getBoolean(IngestionConstant.SMP_INGESTION_ES_INDEX_AUTO_CREATE))
    spark.conf.set("spark.es.nodes",IngestionConfigurations.getString(IngestionConstant.SMP_INGESTION_ES_HOST))
    spark.conf.set("spark.es.port",IngestionConfigurations.getInt(IngestionConstant.SMP_INGESTION_ES_PORT))
    spark.conf.set("spark.es.nodes.wan.only",IngestionConfigurations.getBoolean(IngestionConstant.SMP_INGESTION_ES_NODES_WAN_ONLY))
    spark.conf.set("es.batch.size.entries",IngestionConfigurations.getInt(IngestionConstant.SMP_INGESTION_ES_BATCH_SIZE))
    spark.conf.set("es.nodes.wan.only",IngestionConfigurations.getBoolean(IngestionConstant.SMP_INGESTION_ES_SPARK_NODES_WAN_ONLY))
    spark.conf.set("es.net.ssl",IngestionConfigurations.getBoolean(IngestionConstant.SMP_INGESTION_ES_NET_SSL))
  }

}
