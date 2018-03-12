/**entropy1.sc
project: IFT598Test   NEW ScalaIDE 4.7.0
ws: eclipsews470
sc: entropy1.sc
This illustrates reading in a csv file, parsing it an loading
up a case class StickFigure instances.
These instances are then the bases for further processing.
This is a companion file to
doodles.sc in this same project
Take Away Technology:
1. Reading in a csv file
2. Parsing it by splitting on commas
3. Loading case class StickFigure instances , useful for further processing



2018-01-20 revised r.r.
*/
package main.scala.apps
import scala.io.Source
import scala.math._

object entropy1 {
// a file I made up matching the '1' and '0'  of the stick figures of Fawcett Ch 3 pg 49
 val fn = "C:/aaprograms/datasets/stickFigEntropy.csv"
                                                  //> fn  : String = C:/aaprograms/datasets/stickFigEntropy.csv
 val figureData = Source.fromFile(fn).getLines.toList
                                                  //> figureData  : List[String] = List(1,1,1,0, 1,1,1,0, 1,1,1,0, 1,1,1,0, 1,1,1,
                                                  //| 0, 0,1,0,0, 0,1,0,0, 0,1,0,0, 0,1,0,0, 0,0,1,1, 1,0,0,1, 1,0,0,0)
 figureData.foreach(println)                      //> 1,1,1,0
                                                  //| 1,1,1,0
                                                  //| 1,1,1,0
                                                  //| 1,1,1,0
                                                  //| 1,1,1,0
                                                  //| 0,1,0,0
                                                  //| 0,1,0,0
                                                  //| 0,1,0,0
                                                  //| 0,1,0,0
                                                  //| 0,0,1,1
                                                  //| 1,0,0,1
                                                  //| 1,0,0,0
                                                  
 val stickFiguresArrayStrings = figureData.map(rowString => rowString.split(","))
                                                  //> stickFiguresArrayStrings  : List[Array[String]] = List(Array(1, 1, 1, 0), Ar
                                                  //| ray(1, 1, 1, 0), Array(1, 1, 1, 0), Array(1, 1, 1, 0), Array(1, 1, 1, 0), Ar
                                                  //| ray(0, 1, 0, 0), Array(0, 1, 0, 0), Array(0, 1, 0, 0), Array(0, 1, 0, 0), Ar
                                                  //| ray(0, 0, 1, 1), Array(1, 0, 0, 1), Array(1, 0, 0, 0))
 val stickFigures = stickFiguresArrayStrings. map(array => {
     StickFigure(array(0).trim.toInt, array(1).trim.toInt, array(2).trim.toInt, array(3).trim.toInt)
      }
     )                                            //> stickFigures  : List[main.scala.apps.StickFigure] = List(StickFigure(1,1,1,
                                                  //| 0), StickFigure(1,1,1,0), StickFigure(1,1,1,0), StickFigure(1,1,1,0), Stick
                                                  //| Figure(1,1,1,0), StickFigure(0,1,0,0), StickFigure(0,1,0,0), StickFigure(0,
                                                  //| 1,0,0), StickFigure(0,1,0,0), StickFigure(0,0,1,1), StickFigure(1,0,0,1), S
                                                  //| tickFigure(1,0,0,0))
 stickFigures.foreach(println)                    //> StickFigure(1,1,1,0)
                                                  //| StickFigure(1,1,1,0)
                                                  //| StickFigure(1,1,1,0)
                                                  //| StickFigure(1,1,1,0)
                                                  //| StickFigure(1,1,1,0)
                                                  //| StickFigure(0,1,0,0)
                                                  //| StickFigure(0,1,0,0)
                                                  //| StickFigure(0,1,0,0)
                                                  //| StickFigure(0,1,0,0)
                                                  //| StickFigure(0,0,1,1)
                                                  //| StickFigure(1,0,0,1)
                                                  //| StickFigure(1,0,0,0)

}//end entropy1

case class StickFigure(label : Int, bodyType : Int, headType: Int, color: Int)