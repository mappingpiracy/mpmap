/******************************************

EventsPerYearModel

Alex Klibisz, 2/18/15

This service handles all options and data manipulation for the events per year nvD3 line chart.

It is initialized via the final return function with a passed list of events in geojson form.

******************************************/

mpmap.service('EventsPerYearModel', function($rootScope) {
    var model = {
        options: {
            chart: {
                type: 'lineChart',
                height: 275,
                margin: {
                    top: 20,
                    right: 60,
                    bottom: 40,
                    left: 55
                },
                x: function(d) {
                    return d.x;
                },
                y: function(d) {
                    return d.y;
                },
                useInteractiveGuideline: true,
                dispatch: {
                    stateChange: function(e) { /* console.log("stateChange"); */ },
                    changeState: function(e) { /* console.log("changeState"); */ },
                    tooltipShow: function(e) { /* console.log("tooltipShow"); */ },
                    tooltipHide: function(e) { /* console.log("tooltipHide"); */ }
                },
                xAxis: {
                    axisLabel: 'Years',
                    tickValues: []
                },
                yAxis: {
                    axisLabel: 'Events',
                    axisLabelDistance: 30
                },
                callback: function(chart) { /* console.log("!!! lineChart callback !!!"); */ }
            }
        }
    };

    /*
    Create an nvD3 line chart-friendly representation of the passed mapData
    2-step process:
    - Create an object functioning as a 2-dimensional map with
        - key: country name
        - val:
            - key: year
            - val: number of events
    - Transcribe this map object to the chartData array matching the form:
        Array: [
            Object: {
                key: country name,
                values: Array: {
                    Object: {
                        x: year,
                        y: number of events for that year
                    }
                }   
            }
        ]
     */

    function getData(features, countries, beginYear, endYear) {
        var years = {},
            uniqueCountries = {},
            chartData = [],
            values = [],
            country, year, i, j;


        //create an object with a key for every country and the years object for the value
        for (i = 0; i < countries.length; i++) {
            country = countries[i].name;
            uniqueCountries[country] = {};
            for (j = beginYear; j <= endYear; j++) {
                uniqueCountries[country][j] = 0;
            }
        }

        //iterate over the features array to set the count for every year
        for (i = 0; i < features.length; i++) {
            country = features[i].properties.closestCountry;
            year = new Date(features[i].properties.occurredOnDate).getFullYear();
            if (country in uniqueCountries) {
                uniqueCountries[country][year]++;
            }
        }

        //iterate of the uniqueCountries to create the final nvd3 linechart-friendly data object
        for (country in uniqueCountries) {
            values = [];
            for (year in uniqueCountries[country]) {
                values.push({
                    x: year,
                    y: uniqueCountries[country][year]
                });
            }
            chartData.push({
                values: values,
                key: country
            });
        }

        return chartData;
    }

    /*
        generate an array with years starting at beginYear, ending at endYear
    */
    function getTickValues(beginYear, endYear) {
        var i, tickValues = [];
        for (i = beginYear; i <= endYear; i++) {
            tickValues.push(i);
        }
        return tickValues;
    }

    /*
        model constructor
        - check if arguments are passed
        - try/catch extract geojson features from passed mapData object
        - try/catch extract the year from passed beginDate and endDate
        - call getData to set the model data
        - set the xaxis tick values according to the passed begin and end years
        - return the model
    */
    return function(mapData, countries, beginDate, endDate) {
        var features, beginYear, endYear;

        //not guaranteed to have all four arguments; exit gracefully
        if (arguments.length < 4) {
            return model;
        }

        //not guaranteed to have feature list in mapData object; exit gracefully
        try {
            features = mapData.data.features;
        } catch (err) {
            console.log(err);
            return model;
        }

        //not guarateed to have begin and endDates in explicit date format; exit gracefully
        try {
            beginYear = beginDate.getFullYear();
            endYear = endDate.getFullYear();
        } catch (err) {
            console.log(err);
            return model;
        }

        model.data = getData(features, countries, beginYear, endYear);  
        model.options.chart.xAxis.tickValues = getTickValues(beginYear, endYear);
        return model;
    };

});