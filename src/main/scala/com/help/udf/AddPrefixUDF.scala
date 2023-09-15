package com.help.udf

import org.apache.spark.sql.api.java.UDF1

import scala.util.Try



class AddPrefixUDF extends UDF1[String, String] {

  override def call(potentialIBAN: String): String = {

     "hello" + potentialIBAN + "world"

  }
}
