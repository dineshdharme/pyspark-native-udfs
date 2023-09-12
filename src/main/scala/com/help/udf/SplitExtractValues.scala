package com.help.udf

import org.apache.spark.sql.api.java.UDF1
import util.control.Breaks._

/** Validate IBAN (Whitespace removed). If valid, no execption is thrown in IbanUtil and true is returned
  * If Invalid, an exception is thrown and false is returned.  If null, false is also returned.
  */
class SplitExtractValues extends UDF1[String, Array[Int]] {

  def find_index(list: List[Int], k:Int): Int ={
    var foundIndex = -1
    breakable {

      for (ii <- list.indices) {
          if(list(ii) > k){
            foundIndex = ii - 1
            break;
          }
      }
    }
    foundIndex
  }


  override def call(givenString: String): Array[Int] = {

    val regexInteger = "\\d+".r

    val allMatches = regexInteger.findAllMatchIn(givenString).toList
    //println("all matches only strings", allMatches)
    //println("all matches with all details", allMatches.map(ele => "start="  + ele.start +" end=" + ele.end + " matched="+ ele.matched))
    val num_start_index_list =  allMatches.map(ele => ele.start)
    val num_index_list =  allMatches.zipWithIndex.map(ele => ele._2)
    var num_match_list =  allMatches.map(ele => ele.matched.toInt)


    val regexRangeTO = "TO".r
    val allMatchesRangeTO = regexRangeTO.findAllMatchIn(givenString).toList
    val TO_start_index_list = allMatchesRangeTO.map(ele => ele.start)

    val regexRangeto = "to".r
    val allMatchesRangeto = regexRangeto.findAllMatchIn(givenString).toList
    val to_start_index_list = allMatchesRangeto.map(ele => ele.start)

    val regexRangeMinus = "-".r
    val allMatchesRangeMinus = regexRangeMinus.findAllMatchIn(givenString).toList
    val minus_start_index_list = allMatchesRangeMinus.map(ele => ele.start)

    val all_range_matches = TO_start_index_list ++ to_start_index_list ++ minus_start_index_list

    for (jj <- all_range_matches.indices) {

      val ele = all_range_matches(jj)

      //println("num_match_list  ", num_match_list)
      //println("to_start_index_list  ", all_range_matches)
      //println("ele  ", ele)
      val found_index = find_index(num_start_index_list, ele)
      //println("found index", found_index)

      val match_index = num_index_list(found_index)
      num_match_list = num_match_list.slice(0, match_index) ++ Range(num_match_list(match_index), num_match_list(match_index + 1)).toList ++ num_match_list.slice(match_index + 1, num_match_list.length)

    }

    //println("final num match list = ", num_match_list)

    num_match_list.toArray

  }
}
