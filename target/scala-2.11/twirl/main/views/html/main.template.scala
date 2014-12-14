
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._

import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._

/**/
object main extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.32*/("""
"""),format.raw/*2.1*/("""<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>"""),_display_(/*13.13*/title),format.raw/*13.18*/(""" """),format.raw/*13.19*/("""| MPMAP</title>

    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" media="screen" href=""""),_display_(/*16.50*/routes/*16.56*/.Assets.at("stylesheets/bootstrap.min.css")),format.raw/*16.99*/("""">
    <link rel="stylesheet" media="screen" href=""""),_display_(/*17.50*/routes/*17.56*/.Assets.at("stylesheets/global.css")),format.raw/*17.92*/("""">
    <link rel="stylesheet" media="screen" href=""""),_display_(/*18.50*/routes/*18.56*/.Assets.at("stylesheets/leaflet.css")),format.raw/*18.93*/("""">

    <link rel="shortcut icon" type="image/png" href=""""),_display_(/*20.55*/routes/*20.61*/.Assets.at("images/favicon.ico")),format.raw/*20.93*/(""""/>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<!-- Navigation -->
<nav id="top-nav" class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">MPMAP | Maritime Piracy Mapping Analysis Portal</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="about">About</a></li>
                <li><a href="help">Help</a></li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
</nav>

"""),_display_(/*62.2*/content),format.raw/*62.9*/("""

"""),format.raw/*64.1*/("""<!-- JavaScript dependencies -- jQuery, leaflet, mapbox, bootstrap, date, main -->
<script src=""""),_display_(/*65.15*/routes/*65.21*/.Assets.at("javascripts/jquery.min.js")),format.raw/*65.60*/("""" type="text/javascript"></script>
<script src=""""),_display_(/*66.15*/routes/*66.21*/.Assets.at("javascripts/leaflet.js")),format.raw/*66.57*/("""" type="text/javascript"></script>
<script src=""""),_display_(/*67.15*/routes/*67.21*/.Assets.at("javascripts/mapbox.js")),format.raw/*67.56*/("""" type="text/javascript"></script>
<script src=""""),_display_(/*68.15*/routes/*68.21*/.Assets.at("javascripts/bootstrap.min.js")),format.raw/*68.63*/("""" type="text/javascript"></script>
<script src=""""),_display_(/*69.15*/routes/*69.21*/.Assets.at("javascripts/date.js")),format.raw/*69.54*/("""" type="text/javascript"></script>
<script src=""""),_display_(/*70.15*/routes/*70.21*/.Assets.at("javascripts/index.js")),format.raw/*70.55*/("""" type="text/javascript"></script>

</body>

</html>"""))}
  }

  def render(title:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title)(content)

  def f:((String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Wed Dec 10 01:00:35 EST 2014
                  SOURCE: /media/data/Users/Alex/Documents/Development/Web/mpmap/www/mpmap-play/app/views/main.scala.html
                  HASH: f7069dbf14702edfa1b61f0c90d6fc378d36dab4
                  MATRIX: 727->1|845->31|872->32|1189->322|1215->327|1244->328|1369->426|1384->432|1448->475|1527->527|1542->533|1599->569|1678->621|1693->627|1751->664|1836->722|1851->728|1904->760|3523->2353|3550->2360|3579->2362|3703->2459|3718->2465|3778->2504|3854->2553|3869->2559|3926->2595|4002->2644|4017->2650|4073->2685|4149->2734|4164->2740|4227->2782|4303->2831|4318->2837|4372->2870|4448->2919|4463->2925|4518->2959
                  LINES: 26->1|29->1|30->2|41->13|41->13|41->13|44->16|44->16|44->16|45->17|45->17|45->17|46->18|46->18|46->18|48->20|48->20|48->20|90->62|90->62|92->64|93->65|93->65|93->65|94->66|94->66|94->66|95->67|95->67|95->67|96->68|96->68|96->68|97->69|97->69|97->69|98->70|98->70|98->70
                  -- GENERATED --
              */
          