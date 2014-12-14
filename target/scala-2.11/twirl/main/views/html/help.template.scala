
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
object help extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](_display_(/*1.2*/main(title = "Help")/*1.22*/ {_display_(Seq[Any](format.raw/*1.24*/("""

"""),format.raw/*3.1*/("""<!-- Page Content -->
<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <h1>Help</h1>
        </div>
    </div>
</div>

        """)))}))}
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Wed Dec 10 01:00:35 EST 2014
                  SOURCE: /media/data/Users/Alex/Documents/Development/Web/mpmap/www/mpmap-play/app/views/help.scala.html
                  HASH: 703208328b151c061669c0309658cd644ca10bed
                  MATRIX: 797->1|825->21|864->23|892->25
                  LINES: 29->1|29->1|29->1|31->3
                  -- GENERATED --
              */
          