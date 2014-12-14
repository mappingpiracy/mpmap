// @SOURCE:/media/data/Users/Alex/Documents/Development/Web/mpmap/www/mpmap-play/conf/routes
// @HASH:3e32cd8384af3fb2a6c007eb8ad4b1d0007f17e7
// @DATE:Sun Dec 14 18:11:29 EST 2014


import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset
import _root_.play.libs.F

import Router.queryString

object Routes extends Router.Routes {

import ReverseRouteContext.empty

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_Application_index0_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
private[this] lazy val controllers_Application_index0_invoker = createInvoker(
controllers.Application.index(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
        

// @LINE:9
private[this] lazy val controllers_MapData_index1_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("mapdata"))))
private[this] lazy val controllers_MapData_index1_invoker = createInvoker(
controllers.MapData.index(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.MapData", "index", Nil,"GET", """ Mapdata index method -- doesn't really return anything yet.""", Routes.prefix + """mapdata"""))
        

// @LINE:10
private[this] lazy val controllers_MapData_events2_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("mapdata/events"))))
private[this] lazy val controllers_MapData_events2_invoker = createInvoker(
controllers.MapData.events(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.MapData", "events", Nil,"POST", """""", Routes.prefix + """mapdata/events"""))
        

// @LINE:11
private[this] lazy val controllers_MapData_events3_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("mapdata/events"))))
private[this] lazy val controllers_MapData_events3_invoker = createInvoker(
controllers.MapData.events(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.MapData", "events", Nil,"GET", """""", Routes.prefix + """mapdata/events"""))
        

// @LINE:15
private[this] lazy val controllers_Application_about4_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("about"))))
private[this] lazy val controllers_Application_about4_invoker = createInvoker(
controllers.Application.about(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "about", Nil,"GET", """ Static pages""", Routes.prefix + """about"""))
        

// @LINE:16
private[this] lazy val controllers_Application_help5_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("help"))))
private[this] lazy val controllers_Application_help5_invoker = createInvoker(
controllers.Application.help(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "help", Nil,"GET", """""", Routes.prefix + """help"""))
        

// @LINE:20
private[this] lazy val controllers_Assets_at6_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_Assets_at6_invoker = createInvoker(
controllers.Assets.at(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """mapdata""","""controllers.MapData.index()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """mapdata/events""","""controllers.MapData.events()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """mapdata/events""","""controllers.MapData.events()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """about""","""controllers.Application.about()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """help""","""controllers.Application.help()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]]
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Application_index0_route(params) => {
   call { 
        controllers_Application_index0_invoker.call(controllers.Application.index())
   }
}
        

// @LINE:9
case controllers_MapData_index1_route(params) => {
   call { 
        controllers_MapData_index1_invoker.call(controllers.MapData.index())
   }
}
        

// @LINE:10
case controllers_MapData_events2_route(params) => {
   call { 
        controllers_MapData_events2_invoker.call(controllers.MapData.events())
   }
}
        

// @LINE:11
case controllers_MapData_events3_route(params) => {
   call { 
        controllers_MapData_events3_invoker.call(controllers.MapData.events())
   }
}
        

// @LINE:15
case controllers_Application_about4_route(params) => {
   call { 
        controllers_Application_about4_invoker.call(controllers.Application.about())
   }
}
        

// @LINE:16
case controllers_Application_help5_route(params) => {
   call { 
        controllers_Application_help5_invoker.call(controllers.Application.help())
   }
}
        

// @LINE:20
case controllers_Assets_at6_route(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at6_invoker.call(controllers.Assets.at(path, file))
   }
}
        
}

}
     