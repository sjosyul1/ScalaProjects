
package main.scala.apps
import scala.io.Source
import scala.math._

object doodles {

println(s" yo entropy ")                          //>  yo entropy 
def ent( figs: List[X], subListDiscriminator: String= "label"): (Double, Double, Double) = {
  def ln2(d: Double) = log(d)/log(2.0)
  def e( ones: Int, zeros: Int):Double = {val total= (ones + zeros).toDouble
                                          val p = ones.toDouble/total
                                          val q = 1.0 - p
                                           if (p * q == 0) 0.0
                                           else  -( p * ln2(p) + q * ln2(q) )
                                }//end e()
   // this variable will hold (entropy of the
   val entropy:(Double,Double, Double) = subListDiscriminator match {
    case "label" =>{ val ones  = figs.count(fig => fig.label == 1)
                     val zeros = figs.count(fig => fig.label == 0)
                     println(s" $ones,  $zeros")
                     val temp = e(ones,zeros)
                      (temp, temp, temp )
                     }
    case "bodyType" => { val rectangleBods = figs.filter(fig => fig.bodyType == 1)
                         val rectangleCount = rectangleBods.size
                         val ones =  rectangleBods.count(fig => fig.label == 1)
                         val zeros = rectangleBods.count(fig => fig.label == 0)
                         val rectangleEntropy:Double = e(ones,zeros)
                         val ovalBods = figs.filter(fig => fig.bodyType == 0 )
                         val ovalBodsCount = ovalBods.size
                         val onesx =  ovalBods.count(fig => fig.label == 1)
                         val zerosx = ovalBods.count(fig => fig.label == 0)
                         val ovalEntropy:Double = e(onesx,zerosx)
                         val total = (rectangleCount + ovalBodsCount).toDouble
                         (rectangleEntropy, ovalEntropy,
                           rectangleCount/total * rectangleEntropy + ovalBodsCount/total * ovalEntropy )
                
                     }
    //case "headtypes" =>  /* code for this type*/
    //case "colortype" =>  /* code for this type*/
    // case _ => is the default, 'no match'  and just returns dummy values
      case _ => (42.0, 42.0, 42.0)
   }//end match
 
  entropy
   

}//end ent                                        //> ent: (figs: List[main.scala.apps.X], subListDiscriminator: String)(Double, 
                                                  //| Double, Double)


//just some test to see if this is working...
//e() is just a utility function that, when given the number of ones and zeros in a set, just calcs that entropy
// So, if there were 5 ones, and 5 zeros, e(5,5) would be 1 ( I guard agains zero ones or zeros), i.e. e(0,5) = 0.0
val x1 = X(1, 1, 0, 0 )                           //> x1  : main.scala.apps.X = X(1,1,0,0)
def e( ones: Int, zeros: Int):Double = {val total= (ones + zeros).toDouble
                                  val p = ones/total
                                  val q = 1.0 - p
                                  if ( p * q == 0) 0.0
                                  else  -( p * log(p)/log(2.0) + q * log(q)/log(2.0))
                                }//end e()        //> e: (ones: Int, zeros: Int)Double
 e( 3,3)                                          //> res0: Double = 1.0
 e(4,0)                                           //> res1: Double = 0.0
//** just some made up test instances, using a case class called 'X'rather than  a
// better name like 'StickFigure'
val testXs = List(X(1, 0, 1, 0), X(1,0,1,1), X(1, 0, 1, 0), X(0,1,0,0), X(0,1,1,0), X(0,0,1,0))
                                                  //> testXs  : List[main.scala.apps.X] = List(X(1,0,1,0), X(1,0,1,1), X(1,0,1,0)
                                                  //| , X(0,1,0,0), X(0,1,1,0), X(0,0,1,0))
ent ( testXs, "label")                            //>  3,  3
                                                  //| res2: (Double, Double, Double) = (1.0,1.0,1.0)
ent ( testXs, "bodyType")                         //> res3: (Double, Double, Double) = (0.0,0.8112781244591328,0.5408520829727552
                                                  //| )
}// end doodles.sc

case class X(label: Int, bodyType: Int, headType: Int, colorType: Int)
