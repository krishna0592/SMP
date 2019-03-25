package config

object IngestionConstant {

  val SMP_INGESTION_MASTER = "smp_ingestion_master"
  val SMP_INGESTION_APPNAME = "smp_ingestion_appName"
  val SMP_INGESTION_LOGLEVEL = "smp_ingestion_loglevel"

  val SMP_INGESTION_KAFKA_BOOTSTRAP_URL = "smp_ingestion_kafka_bootstrap_server_url"
  val SMP_INGESTION_KAFKA_GROUP = "smp_ingestion_kafka_group"

  val SMP_INGESTION_ES_INDEX_AUTO_CREATE = "smp_ingestion_es_index_auto_create"
  val SMP_INGESTION_ES_HOST = "smp_ingestion_es_host"
  val SMP_INGESTION_ES_PORT = "smp_ingestion_es_port"
  val SMP_INGESTION_ES_NODES_WAN_ONLY = "smp_ingestion_es_nodes_wan_only"
  val SMP_INGESTION_ES_BATCH_SIZE = "smp_ingestion_es_batch_size"
  val SMP_INGESTION_ES_SPARK_NODES_WAN_ONLY = "smp_ingestion_es_spark_nodes_wan_only"
  val SMP_INGESTION_ES_NET_SSL = "smp_ingestion_es_net_ssl"
}
