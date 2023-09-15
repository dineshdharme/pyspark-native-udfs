package com.help.udf

import org.apache.spark.sql.api.java.UDF1

import scala.collection.mutable
import util.control.Breaks._
import scala.reflect.runtime.currentMirror
import scala.tools.reflect.ToolBox

class CountZeros extends UDF1[Array[Int], Int] {

  override def call(given_array: Array[Int]): Int = {


    //println("Printing all element")
    //given_array.foreach(ele => print (ele + ",  "))
    //println("adding the debug printing ")
    var maxCount = -1

    var runningCount = -1
    var insideLoop = false

    for( ele <- given_array ){

        if (ele == 1) {
          // initialize count to 0
          runningCount = 0
          insideLoop = true


        }
        if (ele == 0 && insideLoop) {
          runningCount += 1

        }
        if (ele == 2 && insideLoop) {
          insideLoop = false
          if (maxCount == -1) {
            maxCount = runningCount
          }
          if (runningCount > maxCount) {
            maxCount = runningCount
          }

        }



      //println( "ele ", ele, " maxCount  ", maxCount, "  runningCount  ", runningCount, " insideLoop flag  ", insideLoop)
    }


    //println("maxCount" , maxCount)
    maxCount
  }
}
