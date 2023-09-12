package com.help.stackoverflow


import com.help.udf.{AddPrefixUDF, SplitExtractValues}

import java.util.{Arrays}
import scala.collection.mutable.{ArrayBuffer, ListBuffer, Map}

object CheckUDFs {

  def main(args: Array[String]): Unit = {

    val udf = new AddPrefixUDF

    val data = ArrayBuffer[String](
      "NLINGB1234", //Too short
      "KW81", //Wrong check digit
      "1234567890")


    for (input <- data) {

      print(input, "results = ", udf.call(input))

    }


    val extractValuesUdf = new SplitExtractValues

    val listOfStrings = ArrayBuffer[String](
      "DK1 , DK2", "DK3 & DK4", "DK5 / 6", "DK7 & 8", "DK9 , 10 & 11", "DK12 + DK13", "DK14.DK15", "DK16 - DK18", "DK18 TO DK20", "DK18     to    DK20", "DK16-DK20 + DK24 TO DK28")


    for (input <- listOfStrings) {
      val result = extractValuesUdf.call(input)
      println(input +  " results = " +  result.toSeq)
    }
  }
}
