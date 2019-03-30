package app

import config.{SMPConfigurations, SMPConstant}
import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.StreamingContext
import config.SparkConfigs.{additionalSparkConfigs, sparkContext, sparkSession, streamingContext}
import helper.SMPLogger

object MainApp {

  var spark: SparkSession = sparkSession()
  var sc: SparkContext = sparkContext()
  var ssc: StreamingContext = streamingContext()

  def main(args: Array[String]): Unit = {
    SMPLogger.info("SMP-Platform","Main","Platform Execution started ...........")

    additionalSparkConfigs()
  }




  /**
    * Note:
    * List of technology used are
    * 1. spark
    * 2. scala
    * 3. local; kafka, hdfs - streaming
    * 4. Hbase, cassandra, Mysql, Hive, Elastic search
    * 5. Amazon s3, Azure Blob, SFTP / FTP
    * 6. csv, txt, tsv, json, parquet, avro
    *
    *
     */

}
