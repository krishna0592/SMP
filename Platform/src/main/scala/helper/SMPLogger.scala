package helper

import java.text.SimpleDateFormat
import java.util.Date
import java.util.logging.Level

import config.{SMPConfigurations, SMPConstant}

object SMPLogger {

  def logCount(module: String, customer: String, method: String, message: String, data: String): Unit ={
    infoLog(module,customer,method,SMPConstant.COUNT,message,data)
  }

  def logDBQuery(module: String, customer: String, method: String, message: String, data: String): Unit ={
    infoLog(module,customer,method,SMPConstant.DBQUERY,message,data)
  }

  def info(module: String, method: String, message: String): Unit ={
    info(module,null,method,message,"")
  }

  def info(module: String, customer: String, method: String, message: String, data: String): Unit ={
    infoLog(module,customer,method,SMPConstant.BASIC,message,data)
  }

  def exception(module: String, customer: String, method: String, message: String, data: String): Unit ={
    infoLog(module,customer,method,SMPConstant.EXCEPTION,message,data)
  }

  private def infoLog(module: String, customer: String, method: String, log_level: SMPConstant.Value, message: String, data: String): Unit ={
    val separator = if(
      SMPConfigurations.getString(SMPConstant.SMP_LOG_SEPARATOR) != null &&
        SMPConfigurations.getString(SMPConstant.SMP_LOG_SEPARATOR).nonEmpty
    ){
      " " + SMPConfigurations.getString(SMPConstant.SMP_LOG_SEPARATOR) + " "
    }else{
      " | "
    }

    val logContent: Unit = {
      val sb = SMPConfigurations.getSB()

      sb.append(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS").format(new Date()))
      sb.append(separator)

      sb.append(Level.INFO)
      sb.append(separator)

      sb.append(module)
      sb.append(separator)

      sb.append(SMPConfigurations.getVersion())
      sb.append(separator)

      if(customer == null) sb.append("") else sb.append(customer)
      sb.append(separator)

      sb.append(method)
      sb.append(separator)

      sb.append(log_level)
      sb.append(separator)

      sb.append(message)
      sb.append(separator)

      sb.append(data)

      println(sb.toString)

      /**
        * write now printing the log statement into console, if required publish the logs into KAFKA topic
        *
        * sample log statements:
        * 30/03/2019 06:21:43:038 | INFO | ingestion | 1.0.0.0 |  | test | BASIC | Test log statement |
        * 30/03/2019 06:21:43:042 | INFO | ingestion | 1.0.0.0 | testCustomer | testMethod | COUNT | Test log statement | 123
        * 30/03/2019 07:11:21:100 | INFO | SMP-Platform | 1.0.0.0 |  |  | Exception | NumberFormatException - couldn't able to convert value 1a | stack trace if required
        */


      sb.setLength(0)
    }

  }
}
