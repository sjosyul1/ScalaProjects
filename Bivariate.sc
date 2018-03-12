  import scala.io.Source
  import java.math._
  
object Bivariate
{
println("Welcome to the Scala worksheet")         //> Welcome to the Scala worksheet
   
  case class Bivariate(x : Double, y : Double)
  
  val fileName = "C:/Users/srina/Bivariate.txt.txt"
                                                  //> fileName  : String = C:/Users/srina/Bivariate.txt.txt
  val lines = Source.fromFile(fileName).getLines.toList
                                                  //> lines  : List[String] = List((1,4),(1,7),(3,9),(3,12),(4,11),(4,12),(5,17),(
                                                  //| 6,13),(6,18),(7,17))
 		
 	val  s = lines(0)                         //> s  : String = (1,4),(1,7),(3,9),(3,12),(4,11),(4,12),(5,17),(6,13),(6,18),(7
                                                  //| ,17)
	
	val pattern = """([0-9]+,[0-9]+)""".r     //> pattern  : scala.util.matching.Regex = ([0-9]+,[0-9]+)
	
	val parsed = pattern.findAllIn(lines(0)).toList
                                                  //> parsed  : List[String] = List(1,4, 1,7, 3,9, 3,12, 4,11, 4,12, 5,17, 6,13, 6
                                                  //| ,18, 7,17)
 
  
  val x = for(i <- 0 to (parsed.size) - 1) yield Bivariate(parsed(i).take(1).toDouble,parsed(i).drop(2).toDouble)
                                                  //> x  : scala.collection.immutable.IndexedSeq[Bivariate.Bivariate] = Vector(Biv
                                                  //| ariate(1.0,4.0), Bivariate(1.0,7.0), Bivariate(3.0,9.0), Bivariate(3.0,12.0)
                                                  //| , Bivariate(4.0,11.0), Bivariate(4.0,12.0), Bivariate(5.0,17.0), Bivariate(6
                                                  //| .0,13.0), Bivariate(6.0,18.0), Bivariate(7.0,17.0))

	val xCoord = x.map(_.x)                   //> xCoord  : scala.collection.immutable.IndexedSeq[Double] = Vector(1.0, 1.0, 3
                                                  //| .0, 3.0, 4.0, 4.0, 5.0, 6.0, 6.0, 7.0)
	val yCoord = x.map(_.y)                   //> yCoord  : scala.collection.immutable.IndexedSeq[Double] = Vector(4.0, 7.0, 9
                                                  //| .0, 12.0, 11.0, 12.0, 17.0, 13.0, 18.0, 17.0)
	val XMean = xCoord.sum/xCoord.size        //> XMean  : Double = 4.0

	val YMean = yCoord.sum/yCoord.size        //> YMean  : Double = 12.0
	
	val xc = xCoord.map(x => x-XMean).toVector//> xc  : Vector[Double] = Vector(-3.0, -3.0, -1.0, -1.0, 0.0, 0.0, 1.0, 2.0, 2.
                                                  //| 0, 3.0)
	val yc = yCoord.map(y => y-YMean).toVector//> yc  : Vector[Double] = Vector(-8.0, -5.0, -3.0, 0.0, -1.0, 0.0, 5.0, 1.0, 6.
                                                  //| 0, 5.0)
  
  def norm(a : Vector[Double]) : Double = {
  		val sqs = a.map(x => math.pow(x,2))
  		val norm = math.sqrt(sqs.sum)
  		norm
  }                                               //> norm: (a: Vector[Double])Double
  
  def dot(av : Vector[Double],bv : Vector[Double]) : Double ={
  	val dot = (for((a,b) <- av zip bv) yield (a * b)).sum
  	dot
  }                                               //> dot: (av: Vector[Double], bv: Vector[Double])Double
  
  val Rcorrelation = dot(xc,yc)/(norm(xc)*norm(yc))
                                                  //> Rcorrelation  : Double = 0.9039935293326326
	val angleBetweenxcyc = math.acos(Rcorrelation).toDegrees
                                                  //> angleBetweenxcyc  : Double = 25.311932308365023

  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
}