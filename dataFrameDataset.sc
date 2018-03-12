/** project: IFT443Spring2018
ws: eclipsewsOld
sc: dataFrameDataset
Reading the Sarkar txt,(breast Cancer classification ) working thru his examples

2018-02-10
*/

package apps
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.SQLImplicits
import org.apache.spark.sql.{Dataset, DataFrame}
import org.apache.spark.sql.Row
import org.apache.spark.sql.functions.{col, explode, length, split, substring}
import org.apache.spark.sql.types._

import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.{RandomForestClassificationModel, RandomForestClassifier}
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorAssembler, VectorIndexer}

import org.apache.spark.ml.classification.{RandomForestClassificationModel, RandomForestClassifier}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
case class CancerClass(sample : Long,
                       cThick : Int,
                       uCSize: Int,
                       UCShape: Int,
                       mAdhes : Int,
                       sECSize : Int,
                          bNuc : Int,
                         bChrom: Int,
                           nNuc: Int,
                         mitosis: Int,
                            clas: Int)

   
object dataFrameDataset {
 Logger.getLogger("org").setLevel(Level.OFF)
  val spark = SparkSession.builder
             .master("local[*]")
             .appName("IFT443Spring2018")
             .getOrCreate()                       //> Using Spark's default log4j profile: org/apache/spark/log4j-defaults.proper
                                                  //| ties
                                                  //| spark  : org.apache.spark.sql.SparkSession = org.apache.spark.sql.SparkSess
                                                  //| ion@3e5d4f6b
 import spark.implicits._
 spark.version                                    //> res0: String = 2.2.0
spark.catalog.currentDatabase                     //> res1: String = default
spark.catalog.listDatabases.show()                //> +-------+----------------+--------------------+
                                                  //| |   name|     description|         locationUri|
                                                  //| +-------+----------------+--------------------+
                                                  //| |default|default database|file:/C:/1022SAVE...|
                                                  //| +-------+----------------+--------------------+
                                                  //| 

val fn = "c:/aaprograms/datasets/BreastCancer/breastCancer.csv"
                                                  //> fn  : String = c:/aaprograms/datasets/BreastCancer/breastCancer.csv
// "add" is a method on the StructType object
val recordSchema = new StructType()
                    .add("sample","long")
                    .add("cThick","integer")
                    .add("uCSize","integer")
                    .add("uCShape","integer")
                    .add("mAdhes","integer")
                    .add("sECSize","integer")
                    .add("bNuc","integer")
                    .add("bChrom","integer")
                    .add("nNuc","integer")
                    .add("mitosis","integer")
                    .add("clas","integer")        //> recordSchema  : org.apache.spark.sql.types.StructType = StructType(StructFi
                                                  //| eld(sample,LongType,true), StructField(cThick,IntegerType,true), StructFiel
                                                  //| d(uCSize,IntegerType,true), StructField(uCShape,IntegerType,true), StructFi
                                                  //| eld(mAdhes,IntegerType,true), StructField(sECSize,IntegerType,true), Struct
                                                  //| Field(bNuc,IntegerType,true), StructField(bChrom,IntegerType,true), StructF
                                                  //| ield(nNuc,IntegerType,true), StructField(mitosis,IntegerType,true), StructF
                                                  //| ield(clas,IntegerType,true))
                    
val dfBreast = spark.read.format("csv").option("header", false).schema(recordSchema).load(fn)
                                                  //> dfBreast  : org.apache.spark.sql.DataFrame = [sample: bigint, cThick: int .
                                                  //| .. 9 more fields]
 //dfBreast.show()
 dfBreast.createOrReplaceTempView("BreastCancer")
 val sqlDF = spark.sql("Select clas from BreastCancer")
                                                  //> sqlDF  : org.apache.spark.sql.DataFrame = [clas: int]
spark.catalog.currentDatabase                     //> res2: String = default
spark.catalog.listDatabases.show()                //> +-------+----------------+--------------------+
                                                  //| |   name|     description|         locationUri|
                                                  //| +-------+----------------+--------------------+
                                                  //| |default|default database|file:/C:/1022SAVE...|
                                                  //| +-------+----------------+--------------------+
                                                  //| 
  
 //sqlDF.take(5).foreach(println)
 //now create a Dataset using the case class "CancerClass" with 4 partitions
 val cancerRDD = spark.sparkContext.textFile(fn, 4)
     .map{ line=> {
       val ar = line.split(",")
       CancerClass(ar(0).trim.toLong,
                   ar(1).trim.toInt,
                   ar(2).trim.toInt,
                   ar(3).trim.toInt,
                   ar(4).trim.toInt,
                   ar(5).trim.toInt,
                   ar(6).trim.toInt,
                   ar(7).trim.toInt,
                   ar(8).trim.toInt,
                   ar(9).trim.toInt,
                   ar(10).trim.toInt)
                   
                  }
         }//end cancerRDD                         //> cancerRDD  : org.apache.spark.rdd.RDD[apps.CancerClass] = MapPartitionsRDD[
    cancerRDD.partitions.size                     //| 2] at map at apps.dataFrameDataset.scala:73
   val cancerDS = cancerRDD.toDS()                //> cancerDS  : org.apache.spark.sql.Dataset[apps.CancerClass] = [sample: bigin
                                                  //| t, cThick: int ... 9 more fields]
   //**** For some reason, defining a function causes an abort!! but
   // using a val does work., the def bin below blows up while the val is ok???
   
   //*** NO GOOD   def binx(s: Int): Int = s match { case 2 => 0 case 4 => 1}
  //OK but can be edited to be shorter as below val bin: Int => Int = (i: Int) => if (i == 4) 1 else 0
    val bin = (i: Int) => if (i == 4) 1 else 0    //> bin  : Int => Int = <function1>
  
 
   //spark.udf.register("udfBinarizeClas", (s: Int) => s match {case 2 => 0 case 4 => 1 } )
  spark.udf.register("udfBinarizeClas", (s: Int) => bin(s))
                                                  //> res3: org.apache.spark.sql.expressions.UserDefinedFunction = UserDefinedFun
                                                  //| ction(<function1>,IntegerType,Some(List(IntegerType)))
   
  
   val sqlUDF = spark.sql("Select * , udfBinarizeClas(clas) from BreastCancer")
                                                  //> sqlUDF  : org.apache.spark.sql.DataFrame = [sample: bigint, cThick: int ...
                                                  //|  10 more fields]
   sqlUDF.take(10).foreach(println)               //> [1000025,5,1,1,1,2,1,3,1,1,2,0]
                                                  //| [1002945,5,4,4,5,7,10,3,2,1,2,0]
                                                  //| [1015425,3,1,1,1,2,2,3,1,1,2,0]
                                                  //| [1016277,6,8,8,1,3,4,3,7,1,2,0]
                                                  //| [1017023,4,1,1,3,2,1,3,1,1,2,0]
                                                  //| [1017122,8,10,10,8,7,10,9,7,1,4,1]
                                                  //| [1018099,1,1,1,1,2,10,3,1,1,2,0]
                                                  //| [1018561,2,1,2,1,2,1,3,1,1,2,0]
                                                  //| [1033078,2,1,1,1,2,1,1,1,5,2,0]
                                                  //| [1033078,4,2,1,1,2,1,2,1,1,2,0]
   
      
  
   
   
   
}//end sc.dataFrameDataset