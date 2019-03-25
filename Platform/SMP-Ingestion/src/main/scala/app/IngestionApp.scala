package app

import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.StreamingContext
import config.SparkConfigs.{sparkSession,sparkContext,streamingContext}

object IngestionApp {

  var spark: SparkSession = sparkSession()
  var sc: SparkContext = sparkContext()
  var ssc: StreamingContext = streamingContext()

  def main(args: Array[String]): Unit = {

  }





















  /**
    * Note:
    * 1. For logging use the compare the Airframe log vs slf4j vs log4j (https://github.com/wvlet/airframe/blob/master/docs/src/main/tut/docs/comparison.md)
    * 2. compare dataframe vs dataset approach and choose a best one
    * 3. use the property files and update the values across the code
    *
    *
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
