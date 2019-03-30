package config

object SMPConstant extends Enumeration {

// spark config property values
  val SMP_MASTER = "smp_master"
  val SMP_APPNAME = "smp_appName"
  val SMP_VERSION = "version"
  val SMP_SPARK_LOGLEVEL = "smp_spark_loglevel"

// SMP Log Details
  val SMP_LOG_SEPARATOR = "smp_log_seperator"
  type LogLevel = Value
  val COUNT = Value("COUNT")
  val BASIC = Value("BASIC")
  val DBQUERY = Value("DBQUERY")
  val EXCEPTION = Value("Exception")

// kafka property values
  val SMP_KAFKA_BOOTSTRAP_URL = "smp_kafka_bootstrap_server_url"
  val SMP_KAFKA_GROUP = "smp_kafka_group"

// ElasticSearch property values
  val SMP_ES_INDEX_AUTO_CREATE = "smp_es_index_auto_create"
  val SMP_ES_HOST = "smp_es_host"
  val SMP_ES_PORT = "smp_es_port"
  val SMP_ES_NODES_WAN_ONLY = "smp_es_nodes_wan_only"
  val SMP_ES_BATCH_SIZE = "smp_es_batch_size"
  val SMP_ES_SPARK_NODES_WAN_ONLY = "smp_es_spark_nodes_wan_only"
  val SMP_ES_NET_SSL = "smp_es_net_ssl"
}
