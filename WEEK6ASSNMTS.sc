object WEEK6ASSNMTS {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  //1-a
  val funlit = ( a:Int, b:Int)=> if (a>b)a
  else b                                          //> funlit  : (Int, Int) => Int = <function2>
  /** 1-b**/
 
   def functcp(t: (Int, Int, Int), cmp: (Int, Int) => Int): Int = {
       cmp(t._1, cmp(t._2, t._3))
      }                                           //> functcp: (t: (Int, Int, Int), cmp: (Int, Int) => Int)Int
      
  
functcp( (100, 200, 300), funlit)                 //> res0: Int = 300
      /**2 a **/
    
      funlit(util.Random.nextInt  ,util.Random.nextInt  )
                                                  //> res1: Int = 68417627
 
    val t = (util.Random.nextInt  ,util.Random.nextInt,util.Random.nextInt )
                                                  //> t  : (Int, Int, Int) = (1562093600,-992326315,962891527)
    functcp(t,funlit)                             //> res2: Int = 1562093600
    
    /** 3**/
    def higher1(x: Int) = (y: Int) => x * y       //> higher1: (x: Int)Int => Int
    val higher2 = higher1(9)                      //> higher2  : Int => Int = <function1>
    higher2(10)                                   //> res3: Int = 90
    /**4 **/
     def fzero(x: String, f: String => Unit): String = { f(x); x }
                                                  //> fzero: (x: String, f: String => Unit)String
    fzero("scala", s => println(s.reverse))       //> alacs
                                                  //| res4: String = scala
    
     /** question 5 **/
     def square(m: Double) = m * m                //> square: (m: Double)Double
   val sq: Double => Double = square              //> sq  : Double => Double = <function1>
   sq(5)                                          //> res5: Double = 25.0
   /** question 6**/
   def conditional[A](x: A, p: A => Boolean, f: A => A): A = {
        if (p(x)) f(x) else x
      }                                           //> conditional: [A](x: A, p: A => Boolean, f: A => A)A
      val a = conditional[String]("sr", _.size > 4, _.reverse)
                                                  //> a  : String = sr
        
        /** chapter 6 question 1 **/
        for (i <- 0 to 9; j = i * 2 + 1) yield j  //> res6: scala.collection.immutable.IndexedSeq[Int] = Vector(1, 3, 5, 7, 9, 11
                                                  //| , 13, 15, 17, 19)
           0 to 20 filter (_ % 2 == 1)            //> res7: scala.collection.immutable.IndexedSeq[Int] = Vector(1, 3, 5, 7, 9, 11
                                                  //| , 13, 15, 17, 19)
          0 to 9 map (_ * 2 + 1)                  //> res8: scala.collection.immutable.IndexedSeq[Int] = Vector(1, 3, 5, 7, 9, 11
                                                  //| , 13, 15, 17, 19)
            /** question 2 **/
          def factors(x: Int) = { 2 to (x-1) filter (x % _ == 0) }
                                                  //> factors: (x: Int)scala.collection.immutable.IndexedSeq[Int]
    factors(10)                                   //> res9: scala.collection.immutable.IndexedSeq[Int] = Vector(2, 5)
    /** question 7 **/
    
   }