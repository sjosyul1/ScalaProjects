/**IFT598Test
  using ScalaIDE 4.7.0  the NEW IDE
  project: IFT598Test
  ws: eclipsews470
  sc: doodles
This was intended simply as a check on a few functions to do entropy
it rapidly got out of hand!!
I am going to use these functions to do the  Fawcett text CH 3 Stick figures
I struggled as to how to compute the entropy of the subtypes, like bodyType
headType and colorType. Finally came up with just a match on the string (label,bodytype, . . .)
which I call the sublistDiscriminator

for example, bodyType is composed of two subsets: subset1 == rectangles ( coded with '1' )
                                                  subset2 == ovals      (coded with '0 )
each of thesesubsets  has an entropy and their relative weights combine to give an expected value of
       entropy
so, output of the 'ent' function is:
entropy(subset1, subset2,  expected value of subset1 combined with subset2 by means of relative weights)
NOTE: as a special case so Ican  use the same function to calc the 'label' entropy, using as sublists,
the label set itself

The plan is to read in a file such that each compoent  matches (label, bodytype, head type, colortype)
using one,zero to distinguish the  subtypes


HOWEVER For this little test, I manually set up a list of StickFigures, shown as X (label, bodytype, headtype, colortype)

The top set , the parent set is a special case and I tried to fit it in by using its entropy as it subsets as well
SO for the parent set I would return (parent entropy, parent entropy, parent entropy)
For the subsets I would return ( the entopy of the '1' subset, entropy of the '0' subset, aggregate entropy of the '1' and '0' subsets
NOTE: I haven't computed the Information Gain, just the component entropies


The 	ENT  function takes the whole data set and a subset designation) and then proceeds to filter out subset based on the subset designation.
for example, If I want to segment on 'bodytype I would select all StickFigures with bodytype component all the ones, and
all the zerioes of the  second component of the row. assuming the second component holds the body types ( both ones and zeroes)
rr 2018-01-10/20
*/

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