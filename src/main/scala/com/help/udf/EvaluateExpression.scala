package com.help.udf

import org.apache.spark.sql.api.java.UDF3
import scala.reflect.runtime.currentMirror
import scala.tools.reflect.ToolBox
import scala.util.control.Breaks._


class EvaluateExpression extends UDF3[Double, Double, String, Double] {

  override def call(x_value:Double, y_value:Double,  given_expression: String): Double = {

    var new_expression = given_expression.replaceAll("x", x_value.toString)
    new_expression = new_expression.replaceAll("y", y_value.toString)

    //println("Here's the new expression ", new_expression)

    val toolbox = currentMirror.mkToolBox()
    val calc = toolbox.eval(toolbox.parse(new_expression))

    calc.toString.toDouble
  }
}
