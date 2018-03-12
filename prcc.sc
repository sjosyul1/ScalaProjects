object prcc
{
def hi = "hi"                                     //> hi: => String
def hi1: String = "hi"                            //> hi1: => String
def multiplier( x: Int, y:Int) : Int = {x*y}      //> multiplier: (x: Int, y: Int)Int
multiplier (4,9)                                  //> res0: Int = 36
def safeTrim(s:String): String={if (s==null) return null
s.trim()}                                         //> safeTrim: (s: String)String
def log(d: Double)= println(f"srinath value $d%.2f")
                                                  //> log: (d: Double)Unit
def log1(d:Double):Unit=println(f"srinath value is $d%.2f")
                                                  //> log1: (d: Double)Unit
 log(2.343444)                                    //> srinath value 2.34
 
 def log2(d:Double){println(f"got value $d%.2f ")}//> log2: (d: Double)Unit
 
  def hi3(): String ="hi"                         //> hi3: ()String
    def formtEur(amt :Double)=f"$amt%.2f"         //> formtEur: (amt: Double)String
    formtEur(3.46)                                //> res1: String = 3.46
    formtEur{val rate =1.32; 0.25+0.1735+rate*5.4}//> res2: String = 7.55

def power(x:Int,n:Int):Long =
{
if(n>=1) x*power(x,n-1)
else 1
}                                                 //> power: (x: Int, n: Int)Long
power(2,8)                                        //> res3: Long = 256

def power1(x:Int,n:Int):Long ={
if(n<1)1
else x* power1(x,n-1)
}                                                 //> power1: (x: Int, n: Int)Long
//nestable function
def max(a:Int,b:Int,c:Int)={
def max (a:Int,b:Int)= if(a>b) a else b
max(a,max(b,c))
}                                                 //> max: (a: Int, b: Int, c: Int)Int

max(42,181,19)                                    //> res4: Int = 181
def greet(prefix:String, name:String)= s"$prefix $name";
                                                  //> greet: (prefix: String, name: String)String
val greeting1= greet(name="Srinath", prefix="josyula")
                                                  //> greeting1  : String = josyula Srinath
 def greet2(prefix: String ="",name:String)=s"$prefix$name"
                                                  //> greet2: (prefix: String, name: String)String
 def max1(x:Int)(y:Int):Int={
 if(x>y)x
 else y
 }                                                //> max1: (x: Int)(y: Int)Int
 val larger = max1(20)(40)                        //> larger  : Int = 40
 def identity[A](a:A):A=a                         //> identity: [A](a: A)A
 val s : String = identity[String]("Hello")       //> s  : String = Hello
 val d : Double = identity[Double](2.17)          //> d  : Double = 2.17
 
 val s1= "vacation.jpg"                           //> s1  : String = vacation.jpg
 val isJPEG= s1.endsWith(".jpg")                  //> isJPEG  : Boolean = true
 // chapter 5
 def double(x:Int):Int=x*2                        //> double: (x: Int)Int
 double(5)                                        //> res5: Int = 10
  val myDouble: (Int) => Int= double              //> myDouble  : Int => Int = <function1>
  myDouble(5)                                     //> res6: Int = 10
  val myDoubleCopy=myDouble                       //> myDoubleCopy  : Int => Int = <function1>
  myDoubleCopy(10)                                //> res7: Int = 20

def max3(a:Int,b:Int)= if (a>b)a
else b                                            //> max3: (a: Int, b: Int)Int
val maximize:(Int,Int)=> Int =max3                //> maximize  : (Int, Int) => Int = <function2>
maximize(3,20)                                    //> res8: Int = 20
def safeStringOp(s:String , f:String => String)={
if(s!= null) f(s) else s
}                                                 //> safeStringOp: (s: String, f: String => String)String
def reverse (s:String)= s.reverse                 //> reverse: (s: String)String

safeStringOp(null,reverse)                        //> res9: String = null
safeStringOp("srinath",reverse)                   //> res10: String = htanirs

val greeter =(name:String)=>s"Hello $name"        //> greeter  : String => String = <function1>

val h = greeter("srinath")                        //> h  : String = Hello srinath

def combination(x:Int,y:Int,f:(Int,Int)=>Int)=f(x,y)
                                                  //> combination: (x: Int, y: Int, f: (Int, Int) => Int)Int
combination(10,20,_*_)                            //> res11: Int = 200

def factor(x:Int)(y:Int)=y%x==0                   //> factor: (x: Int)(y: Int)Boolean
val isEven= factor(2)_                            //> isEven  : Int => Boolean = <function1>
val z= isEven(32)                                 //> z  : Boolean = true

val statusHandler:Int => String ={
case 200=> "okay"
case 400=>"your error"
case 500=> "our error"
}                                                 //> statusHandler  : Int => String = <function1>
// chapter 6
val numbers=List(32,95,24,21,17)                  //> numbers  : List[Int] = List(32, 95, 24, 21, 17)

val colors= List("red","green","blue")            //> colors  : List[String] = List(red, green, blue)
println(s"I have ${colors.size} colors:$colors")  //> I have 3 colors:List(red, green, blue)
var total =0; for(i<- numbers){total+=i}          //> total  : Int = 189


colors.foreach((c:String)=> println(c))           //> red
                                                  //| green
                                                  //| blue
val sizes=colors.map((c:String)=>c.size)          //> sizes  : List[Int] = List(3, 5, 4)
val colmap= Map("red"-> 0xFF0000,"green"-> 0xFF00,"blue"->0xFF)
                                                  //> colmap  : scala.collection.immutable.Map[String,Int] = Map(red -> 16711680,
                                                  //|  green -> 65280, blue -> 255)
val primes= List(2,3,5,7,11,13)                   //> primes  : List[Int] = List(2, 3, 5, 7, 11, 13)
val nbers= 1::2::3::Nil                           //> nbers  : List[Int] = List(1, 2, 3)

List(1,2):::List(2,3)                             //> res12: List[Int] = List(1, 2, 2, 3)
List(1,2)++List(5,7,8)                            //> res13: List[Int] = List(1, 2, 5, 7, 8)
List('a','s','d','g','h')drop 2                   //> res14: List[Char] = List(d, g, h)
List(1,2,5,7,8) partition(_<3)                    //> res15: (List[Int], List[Int]) = (List(1, 2),List(5, 7, 8))
List(2,3,5,7)slice (1,3)                          //> res16: List[Int] = List(3, 5)
List("apple","to").sorted                         //> res17: List[String] = List(apple, to)
List(1,2,5,7,8) splitAt 2                         //> res18: (List[Int], List[Int]) = (List(1, 2),List(5, 7, 8))
List(1,2) zip List ("a","b")                      //> res19: List[(Int, String)] = List((1,a), (2,b))
List(0,1,0) collect {
case 1=>"ok"
}                                                 //> res20: List[String] = List(ok)
List(12,25,30) forall(_<18)                       //> res21: Boolean = false
List(4,5,6).fold(0)(_+_)                          //> res22: Int = 15
List(4,5,6).foldLeft(0)(_-_)                      //> res23: Int = -15
List(6,8,9,88).mkString(",")                      //> res24: String = 6,8,9,88
List('a','b').toBuffer                            //> res25: scala.collection.mutable.Buffer[Char] = ArrayBuffer(a, b)
Map("a"->1,"b"->2).toList                         //> res26: List[(String, Int)] = List((a,1), (b,2))
Set(1-> true,3-> true).toMap                      //> res27: scala.collection.immutable.Map[Int,Boolean] = Map(1 -> true, 3 -> tr
                                                  //| ue)
val m= Map("AAPL" ->597,"MSFT"->40)               //> m  : scala.collection.immutable.Map[String,Int] = Map(AAPL -> 597, MSFT -> 
                                                  //| 40)
val n=m-"AAPL"+("GGOG" ->521)                     //> n  : scala.collection.immutable.Map[String,Int] = Map(MSFT -> 40, GGOG -> 5
                                                  //| 21)
val nums = collection.mutable.Buffer(1)           //> nums  : scala.collection.mutable.Buffer[Int] = ArrayBuffer(1)
for(i<- 2 to 10) nums+=i
println(nums)                                     //> ArrayBuffer(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
def inc(i:Int): Stream[Int]=Stream.cons(i,inc(i+1))
 //                                               //> inc: (i: Int)Stream[Int]
 
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
}