package com.help.udf

import org.apache.spark.sql.api.java.UDF3
import org.apache.spark.sql.api.java.UDF4

import scala.reflect.runtime.currentMirror
import scala.tools.reflect.ToolBox


class EvaluateBooleanExpression extends UDF4[Int, Int, Int, String, Boolean] {

  override def call(a_value:Int, b_value:Int, c_value:Int,  given_expression: String): Boolean = {

    var new_expression = given_expression.replaceAll("A", a_value.toString)
    new_expression = new_expression.replaceAll("B", b_value.toString)
    new_expression = new_expression.replaceAll("C", c_value.toString)
    new_expression = new_expression.replaceAll("0", false.toString)
    new_expression = new_expression.replaceAll("1", true.toString)
    //println("Here's the new expression ", new_expression)

    val toolbox = currentMirror.mkToolBox()
    val calc = toolbox.eval(toolbox.parse(new_expression))

    val convertedCalc = calc.toString.toBoolean
    //println("Here's the new expression ", new_expression)

    convertedCalc
  }
}
