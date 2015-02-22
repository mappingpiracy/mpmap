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

     function getData(features, countries, beginYear, endYear) {
        var years = {},
            uniqueCountries = {},
            chartData = [],
            values = [],
            country, year, value, i, j;

            //create an object with a key for every year for the time span and a value of 0
            for(i = beginYear; i <= endYear; i++) {
                years[i] = 0;
            }

            console.log("years", years);

            //create an object with a key for every country and the years object for the value
            for(i = 0; i < countries.length; i++) {
                uniqueCountries[countries[i].name] = years;     //<-- need to make a copy of the years object
            }

            //iterate over the features array to set the count for every year
            for(i = 0; i < features.length; i++) {
                country = features[i].properties.closestCountry;
                year = Date.parse(features[i].properties.occurredOnDate).getFullYear();
                console.log(country, year, uniqueCountries[country]);
                uniqueCountries[country][year]++;
            }

            console.log("uniqueCountries", uniqueCountries);

            //iterate of the uniqueCountries to create the final nvd3 linechart-friendly data object
            for(country in uniqueCountries) {
                values = [];
                for(year in uniqueCountries[country]) {
                    value = {
                        x: year,
                        y: uniqueCountries[country][year]
                    };
                    //console.log(country, value);
                }

            }


            // for (country in uniqueCountries) {
            //     yearlyValues = []; //yearly values for this country
            //     for (year in uniqueCountries[country]) {
            //         yearlyValues.push({ //push a new (x,y) pair for this year
            //             x : year,
            //             y : uniqueCountries[country][year]
            //         });
            //     }
            //     chartData.push({ //push a new (key, value) pair for this country
            //         values : yearlyValues,
            //         key : country
            //     });
            // }

            console.log("chartData:", chartData);

            return chartData;
     }

    function getTickValues(beginYear, endYear) {
        var i, tickValues = [];
        if(beginYear == endYear) {
            tickValues.push(beginYear);
        } else {
            for(i = beginYear; i <= endYear; i++) {
                tickValues.push(i);
            }    
        }
        console.log("tickValues:", tickValues);
        return tickValues;
    }
    
    return function (mapData, countries, beginDate, endDate) {
        var features, beginYear, endYear;
        
        try {
            features = mapData.data.features;
        } catch(err){
            console.log(err);
            return model;
        }

        try {
            beginYear = beginDate.getFullYear();
            endYear = endDate.getFullYear();
        } catch(err) {
            console.log(err);
            return model;
        }

        model.data = getData(mapData, countries, beginYear, endYear);
        model.options.chart.xAxis.tickValues = getTickValues(beginYear, endYear);
        return model;
    };

}
);
