/******************************************

EventsPerYearModel

Alex Klibisz, 2/18/15

This service handles all options and data manipulation for the events per year nvD3 line chart.

It is initialized via the final return function with a passed list of events in geojson form.

 ******************************************/

mpmap.service('EventsPerYearModel', function ($rootScope)
{
    var model = {
        options : {
            chart : {
                type : 'lineChart',
                height : 275,
                margin : {
                    top : 20,
                    right : 60,
                    bottom : 40,
                    left : 55
                },
                x: function(d){ return d.x; },
                y: function(d){ return d.y; },
                useInteractiveGuideline: true,
                dispatch: {
                    stateChange: function(e){ /* console.log("stateChange"); */ },
                    changeState: function(e){ /* console.log("changeState"); */ },
                    tooltipShow: function(e){ /* console.log("tooltipShow"); */ },
                    tooltipHide: function(e){ /* console.log("tooltipHide"); */ }
                },
                xAxis : {
                    axisLabel : 'Years',
                    tickValues : []
                },
                yAxis : {
                    axisLabel : 'Events',
                    axisLabelDistance : 30
                },
                callback: function(chart){
                    console.log("!!! lineChart callback !!!");
                }
            }
        }
    };

    /*
    Create an nvD3 line chart-friendly representation of the passed mapData
    Final data model is:
        List: [
            Object: {
                key: country name,
                values: List: {
                    Object: {
                        x: year,
                        y: number of events for that year
                    }
                }   
            }
        ]

    2-step process:
    1. Create a 2-dimensional map object keyed on country name and year with value yearly number of events for each country.
    2. Transcribe this map object to the chartData array so that it works with nvd3.js
     */
    function getData(mapData) {
        var features,
        countries = {},
        chartData = [],
        yearlyValues = [],
        country,
        year;

        try {
            features = mapData.data.features; //extract features array from the passed mapdata
        }
        catch (err) {
            return [];
        }

        //iterate over the features array to create the 2-dimensional map object
        for (var i = 0; i < features.length; i++) {
            //extract the closest country name and year
            country = features[i].properties.closestCountry;
            year = features[i].properties.occurredOnDate.substring(0, 4);

            if (country in countries) { //check if this country has already been stored
                if (year in countries[country]) { //country has been stored -- check if this year has been stored
                    countries[country][year]++; //year has been stored -- increment its count
                }
                else {
                    countries[country][year] = 1; //year has not been stored -- initialize it
                }
            }
            else {
                countries[country] = {}; //country has not been stored -- initialize its map object
                countries[country][year] = 1; //initialize the country counter
            }
        }

        //iterate over the countries again to create the final nvd3.js-friendly object
        for (country in countries) {
            yearlyValues = []; //yearly values for this country
            for (year in countries[country]) {
                yearlyValues.push({ //push a new (x,y) pair for this year
                    x : year,
                    y : countries[country][year]
                }
                );
            }
            chartData.push({ //push a new (key, value) pair for this country
                values : yearlyValues,
                key : country
            });
        }
        return chartData;
    }

    function getTickValues(beginDate, endDate) {
        var i, beginYear, endYear, tickValues = [];
        try {
            beginYear = new Date(beginDate).getFullYear();
            endYear = new Date(endDate).getFullYear();    
        } catch(err) {
            return tickValues;
        }

        if(beginYear == endYear) {
            tickValues.push(beginYear);
        } else {
            for(i = beginYear; i <= endYear; i++) {
                tickValues.push(i);
            }    
        }
        return tickValues;
    }
    
    return function (mapData, beginDate, endDate) {
        model.data = getData(mapData);
        model.options.chart.xAxis.tickValues = getTickValues(beginDate, endDate);
        return model;
    };

}
);
