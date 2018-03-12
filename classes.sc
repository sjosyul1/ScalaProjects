object classes
 {
 
 class user(val name:String){

 def greet: String =s"hello from $name"
 override def toString= s"user($name)"
  }
  
  val users= List(new user("shoot"),new user("srinath5"))
                                                  //> users  : List[classes.user] = List(user(shoot), user(srinath5))
                                        
 val sizes= users map (_.name.size)               //> sizes  : List[Int] = List(5, 8)


class A
{
def hi= "Hello from A"
override def toString=getClass.getName
}
class B extends A

class c extends B
{
override def hi ="hi C"+super.hi
}

val hiA=new A().hi                                //> hiA  : String = Hello from A
val hiB=new B().hi                                //> hiB  : String = Hello from A
val hiC= new c().hi                               //> hiC  : String = hi CHello from A
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
}