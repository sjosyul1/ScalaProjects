
import scala.xml._
import scala.io.Source
object xmlquestion {


 println("Welcome to the Scala worksheet")        //> Welcome to the Scala worksheet
 
 val urlgeneric= "http://api.openweathermap.org/data/2.5/weather?mode=xml&q=London,uk&APPID=2b60bbdfbcd49b68e44733bab1384882"
                                                  //> urlgeneric  : String = http://api.openweathermap.org/data/2.5/weather?mode=x
                                                  //| ml&q=London,uk&APPID=2b60bbdfbcd49b68e44733bab1384882
  
   val listXML = Source.fromURL(urlgeneric).getLines.toList
                                                  //> listXML  : List[String] = List(<current><city id="2643743" name="London"><co
                                                  //| ord lon="-0.13" lat="51.51"></coord><country>GB</country><sun rise="2017-02-
                                                  //| 24T06:55:32" set="2017-02-24T17:32:36"></sun></city><temperature value="276.
                                                  //| 54" min="275.15" max="278.15" unit="kelvin"></temperature><humidity value="7
                                                  //| 5" unit="%"></humidity><pressure value="1012" unit="hPa"></pressure><wind><s
                                                  //| peed value="6.7" name="Moderate breeze"></speed><gusts></gusts><direction va
                                                  //| lue="280" code="W" name="West"></direction></wind><clouds value="20" name="f
                                                  //| ew clouds"></clouds><visibility value="10000"></visibility><precipitation mo
                                                  //| de="no"></precipitation><weather number="801" value="few clouds" icon="02n">
                                                  //| </weather><lastupdate value="2017-02-24T05:20:00"></lastupdate></current>)
   val listXMLToString = listXML(0)               //> listXMLToString  : String = <current><city id="2643743" name="London"><coord
                                                  //|  lon="-0.13" lat="51.51"></coord><country>GB</country><sun rise="2017-02-24T
                                                  //| 06:55:32" set="2017-02-24T17:32:36"></sun></city><temperature value="276.54"
                                                  //|  min="275.15" max="278.15" unit="kelvin"></temperature><humidity value="75" 
                                                  //| unit="%"></humidity><pressure value="1012" unit="hPa"></pressure><wind><spee
                                                  //| d value="6.7" name="Moderate breeze"></speed><gusts></gusts><direction value
                                                  //| ="280" code="W" name="West"></direction></wind><clouds value="20" name="few 
                                                  //| clouds"></clouds><visibility value="10000"></visibility><precipitation mode=
                                                  //| "no"></precipitation><weather number="801" value="few clouds" icon="02n"></w
                                                  //| eather><lastupdate value="2017-02-24T05:20:00"></lastupdate></current>
   val pattern = """.*<city id="\d+" name="([a-zA-Z]+)">.*""".r
                                                  //> pattern  : scala.util.matching.Regex = .*<city id="\d+" name="([a-zA-Z]+)">.
                                                  //| *
   val pattern(name) = listXMLToString            //> name  : String = London
   val xml = XML.loadString(listXMLToString )     //> xml  : scala.xml.Elem = <current><city name="London" id="2643743"><coord lat
                                                  //| ="51.51" lon="-0.13"/><country>GB</country><sun set="2017-02-24T17:32:36" ri
                                                  //| se="2017-02-24T06:55:32"/></city><temperature unit="kelvin" max="278.15" min
                                                  //| ="275.15" value="276.54"/><humidity unit="%" value="75"/><pressure unit="hPa
                                                  //| " value="1012"/><wind><speed name="Moderate breeze" value="6.7"/><gusts/><di
                                                  //| rection name="West" code="W" value="280"/></wind><clouds name="few clouds" v
                                                  //| alue="20"/><visibility value="10000"/><precipitation mode="no"/><weather ico
                                                  //| n="02n" value="few clouds" number="801"/><lastupdate value="2017-02-24T05:20
                                                  //| :00"/></current>
   xml \\ "city"  \ "@name"                       //> res0: scala.xml.NodeSeq = London

    val forecast = "http://api.openweathermap.org/data/2.5/forecast?mode=xml&q=London,uk&APPID=2b60bbdfbcd49b68e44733bab1384882"
                                                  //> forecast  : String = http://api.openweathermap.org/data/2.5/forecast?mode=xm
                                                  //| l&q=London,uk&APPID=2b60bbdfbcd49b68e44733bab1384882
    
    val forecastListXML = Source.fromURL(forecast).getLines.toList
                                                  //> forecastListXML  : List[String] = List(<weatherdata><location><name>London</
                                                  //| name><type></type><country>GB</country><timezone></timezone><location altitu
                                                  //| de="0" latitude="51.50853" longitude="-0.12574" geobase="geonames" geobaseid
                                                  //| ="0"></location></location><credit></credit><meta><lastupdate></lastupdate><
                                                  //| calctime>0.0928</calctime><nextupdate></nextupdate></meta><sun rise="2017-02
                                                  //| -24T06:55:31" set="2017-02-24T17:32:36"></sun><forecast><time from="2017-02-
                                                  //| 24T03:00:00" to="2017-02-24T06:00:00"><symbol number="500" name="light rain"
                                                  //|  var="10n"></symbol><precipitation unit="3h" value="0.9275" type="rain"></pr
                                                  //| ecipitation><windDirection deg="310.504" code="NW" name="Northwest"></windDi
                                                  //| rection><windSpeed mps="5.65" name="Moderate breeze"></windSpeed><temperatur
                                                  //| e unit="celsius" value="3.26" min="3.26" max="4.88"></temperature><pressure 
                                                  //| unit="hPa" value="1017.93"></pressure><humidity value="81" unit="%"></humidi
                                                  //| ty><clouds value="overca
                                                  //| Output exceeds cutoff limit.
    
   val timeSeg = forecastListXML.filter(_ contains "</time>").size
                                                  //> timeSeg  : Int = 1


 
 def retrieve(tag: String, attr: String) = {
       forecastListXML.filter(_ contains s"<$tag")
        .filter(_ contains s"$attr=")
         .map { s => s.replaceAll(s""".*$attr="([^"]+)".*""", "$1") }
     }                                            //> retrieve: (tag: String, attr: String)List[String]

  val names = retrieve("symbol", "name")          //> names  : List[String] = List(Moderate breeze)
 
 

val sortedList = retrieve("symbol", "name").distinct.sorted
                                                  //> sortedList  : List[String] = List(Moderate breeze)

  
   val symToDesc= retrieve("symbol", "number") zip retrieve("symbol", "name")
                                                  //> symToDesc  : List[(String, String)] = List((500,Moderate breeze))
   val symbolMap = symToDesc.distinct.map(t => t._1.toInt -> t._2).toMap
                                                  //> symbolMap  : scala.collection.immutable.Map[Int,String] = Map(500 -> Modera
                                                  //| te breeze)

 
  val maximumTemp = retrieve("temperature", "max").map(_.toDouble).max
                                                  //> maximumTemp  : Double = 6.17
  val minimumTemp = retrieve("temperature", "min").map(_.toDouble).min
                                                  //> minimumTemp  : Double = 6.17




}