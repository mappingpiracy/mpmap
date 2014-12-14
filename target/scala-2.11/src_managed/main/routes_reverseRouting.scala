// @SOURCE:/media/data/Users/Alex/Documents/Development/Web/mpmap/www/mpmap-play/conf/routes
// @HASH:3e32cd8384af3fb2a6c007eb8ad4b1d0007f17e7
// @DATE:Sun Dec 14 18:11:29 EST 2014

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset
import _root_.play.libs.F

import Router.queryString


// @LINE:20
// @LINE:16
// @LINE:15
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers {

// @LINE:11
// @LINE:10
// @LINE:9
class ReverseMapData {


// @LINE:9
def index(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "mapdata")
}
                        

// @LINE:11
// @LINE:10
def events(): Call = {
   () match {
// @LINE:10
case ()  =>
  import ReverseRouteContext.empty
  Call("POST", _prefix + { _defaultPrefix } + "mapdata/events")
                                         
   }
}
                                                

}
                          

// @LINE:20
class ReverseAssets {


// @LINE:20
def at(file:String): Call = {
   implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                        

}
                          

// @LINE:16
// @LINE:15
// @LINE:6
class ReverseApplication {


// @LINE:15
def about(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "about")
}
                        

// @LINE:6
def index(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix)
}
                        

// @LINE:16
def help(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "help")
}
                        

}
                          
}
                  


// @LINE:20
// @LINE:16
// @LINE:15
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers.javascript {
import ReverseRouteContext.empty

// @LINE:11
// @LINE:10
// @LINE:9
class ReverseMapData {


// @LINE:9
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.MapData.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mapdata"})
      }
   """
)
                        

// @LINE:11
// @LINE:10
def events : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.MapData.events",
   """
      function() {
      if (true) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mapdata/events"})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mapdata/events"})
      }
      }
   """
)
                        

}
              

// @LINE:20
class ReverseAssets {


// @LINE:20
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        

}
              

// @LINE:16
// @LINE:15
// @LINE:6
class ReverseApplication {


// @LINE:15
def about : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.about",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "about"})
      }
   """
)
                        

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

// @LINE:16
def help : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.help",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "help"})
      }
   """
)
                        

}
              
}
        


// @LINE:20
// @LINE:16
// @LINE:15
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers.ref {


// @LINE:11
// @LINE:10
// @LINE:9
class ReverseMapData {


// @LINE:9
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.MapData.index(), HandlerDef(this.getClass.getClassLoader, "", "controllers.MapData", "index", Seq(), "GET", """ Mapdata index method -- doesn't really return anything yet.""", _prefix + """mapdata""")
)
                      

// @LINE:10
def events(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.MapData.events(), HandlerDef(this.getClass.getClassLoader, "", "controllers.MapData", "events", Seq(), "POST", """""", _prefix + """mapdata/events""")
)
                      

}
                          

// @LINE:20
class ReverseAssets {


// @LINE:20
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      

}
                          

// @LINE:16
// @LINE:15
// @LINE:6
class ReverseApplication {


// @LINE:15
def about(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.about(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "about", Seq(), "GET", """ Static pages""", _prefix + """about""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      

// @LINE:16
def help(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.help(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "help", Seq(), "GET", """""", _prefix + """help""")
)
                      

}
                          
}
        
    