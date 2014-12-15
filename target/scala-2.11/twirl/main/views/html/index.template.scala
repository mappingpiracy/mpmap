
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
object index extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](_display_(/*1.2*/main(title = "Home")/*1.22*/ {_display_(Seq[Any](format.raw/*1.24*/("""
"""),format.raw/*2.1*/("""<div id="left-wrapper">
    <div id="filter-analyze-data">
        <div id="content">
            <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
                <li class="active"><a href="#filter-events-tab" data-toggle="tab">Filter events</a></li>
                <li><a href="#analyze-data-tab" data-toggle="tab">Analyze Data</a></li>
            </ul>

            <div id="tab-content" class="tab-content" style="padding: 10px">
                <div class="tab-pane active" id="filter-events-tab">
                    <div class="alert alert-info">Select from the options below to filter the event data presented on
                        the map.
                    </div>
                    <form role="form" class="form-horizontal" id="filter-events">
                        <!-- Date Range -->
                        <fieldset>
                            <div class="form-group">
                                <label class="col-md-4 control-label">Begin Date:</label>

                                <div class="col-md-8">
                                    <input type='date' class="form-control" id="begin-date"/>
                                </div>
                            </div>
                        </fieldset>
                        <fieldset>
                            <div class="form-group">
                                <label class="col-md-4 control-label">End Date:</label>

                                <div class="col-md-8">
                                    <input type='date' class="form-control" id="end-date"/>
                                </div>
                            </div>
                        </fieldset>

                        <!-- Territorial Water Status -->
                        <fieldset>
                            <div class="form-group">
                                <label class="col-md-4 control-label">Territorial Water Status:</label>

                                <div class="col-md-8">
                                    <select class="form-control" id="territorial-water-status" multiple>
                                        <option value="1">Country 1</option>
                                        <option value="2">Country 2</option>
                                        <option value="3">Country 3</option>
                                    </select>
                                </div>
                            </div>
                        </fieldset>

                        <!-- Closest Coastal State -->
                        <fieldset>
                            <div class="form-group">
                                <label class="col-md-4 control-label">Closest Coastal State:</label>

                                <div class="col-md-8">
                                    <select class="form-control" id="closest-coastal-state" multiple>
                                        <option value="1">Country 1</option>
                                        <option value="2">Country 2</option>
                                        <option value="3">Country 3</option>
                                    </select>
                                </div>
                            </div>
                        </fieldset>

                        <!-- Vessel Country -->
                        <fieldset>
                            <div class="form-group">
                                <label class="col-md-4 control-label">Vessel Country:</label>

                                <div class="col-md-8">
                                    <select class="form-control" id="vessel-country" multiple>
                                        <option value="1">Country 1</option>
                                        <option value="2">Country 2</option>
                                        <option value="3">Country 3</option>
                                    </select>
                                </div>
                            </div>
                        </fieldset>

                        <!-- Advanced Filtering Options -->
                        <fieldset>
                            <a class="btn btn-sm btn-info" id="filter-events-advanced-toggle">
                                Advanced event Filters
                            </a>
                            <a class="btn btn-sm btn-info" id="export-events">
                                Export events
                            </a>

                        </fieldset>

                        <div id="filter-events-advanced">
                            <p>Advanced event Filters</p>
                        </div>

                        <p></p>

                        <fieldset>
                            <a href="#" class="btn btn-md btn-primary" id="filter-events-update">
                                Filter Events
                            </a>
                        </fieldset>

                    </form>


                </div>
                <div class="tab-pane" id="analyze-data-tab">
                    <div class="alert alert-info">Select from the options below to present a relational analysis of the
                        filtered data.
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="map"></div>

""")))}),format.raw/*120.2*/("""
"""))}
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Sun Dec 14 18:39:20 EST 2014
                  SOURCE: /media/data/Users/Alex/Documents/Development/Web/mpmap/www/mpmap-play/app/views/index.scala.html
                  HASH: 84eb3055aac4bce3c17712cf58b0d2b67f423da0
                  MATRIX: 798->1|826->21|865->23|892->24|6270->5371
                  LINES: 29->1|29->1|29->1|30->2|148->120
                  -- GENERATED --
              */
          