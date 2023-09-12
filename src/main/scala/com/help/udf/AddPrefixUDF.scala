package com.help.udf

import org.apache.spark.sql.api.java.UDF1

import scala.util.Try


/** Validate IBAN (Whitespace removed). If valid, no execption is thrown in IbanUtil and true is returned
  * If Invalid, an exception is thrown and false is returned.  If null, false is also returned.
  */
class AddPrefixUDF extends UDF1[String, String] {

  override def call(potentialIBAN: String): String = {

     "hello" + potentialIBAN + "world"

  }
}
