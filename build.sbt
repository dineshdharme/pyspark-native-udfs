

name := "pyspark-native-udfs"

version := "0.1.0"

scalaVersion := "2.12.10"


val sparkVersion = "3.4.1"

resolvers += "jitpack" at "https://jitpack.io"

libraryDependencies ++= Seq(
  //"com.github.pathikrit" %% "better-files" % "3.8.0",
  //"com.github.mrpowers" % "spark-daria" % "v2.3.0_0.20.0",
  //"uk.com.robust-it" % "cloning" % "1.9.10",
  "org.apache.spark" %% "spark-core" % sparkVersion % Provided,
  "org.apache.spark" %% "spark-sql" % sparkVersion % Provided,
  "org.apache.spark" %% "spark-mllib" % sparkVersion % Provided,
  "org.scala-lang" % "scala-compiler" % scalaVersion.value
)

(Compile) / mainClass := Some("com.help.stackoverflow.CheckUDFs")

assemblyMergeStrategy in assembly := {
  //case PathList("META-INF", xs @ _*) => MergeStrategy.discard

  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first

}


