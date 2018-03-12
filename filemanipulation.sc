
object filemanipulation
{
  println("Newton approximation")                 //> Newton approximation
 def check1(b: Double) = if (b > 0) b else -b     //> check1: (b: Double)Double

def check2(a: Double, b: Double) = check1(a* a - b)/b < 0.0000001
                                                  //> check2: (a: Double, b: Double)Boolean

def check3(a: Double, b: Double) = (a + b/a)/2    //> check3: (a: Double, b: Double)Double

def newtonsMethod(a: Double, b: Double): Double =  {
    if (check2(a,b)) a
    else newtonsMethod(check3(a, b), b)
}                                                 //> newtonsMethod: (a: Double, b: Double)Double

def squareRoot(b: Double): Double  =  newtonsMethod(1, b)
                                                  //> squareRoot: (b: Double)Double

//tests
print(squareRoot(19) + "\n")                      //> 4.358898943541577
print(squareRoot(5558) + "\n")                    //> 74.55199527863795
print(squareRoot(121)  + "\n")                    //> 11.000000001611474
}