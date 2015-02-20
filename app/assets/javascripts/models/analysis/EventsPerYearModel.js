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
                    stateChange: function(e) {
                        console.log("stateChange");
                    },
                    changeState: function(e) {
                        console.log("changeState");
                    },
                    tooltipShow: function(e) {
                        console.log("tooltipShow");
                    },
                    tooltipHide: function(e) {
                        console.log("tooltipHide");
                    }
                },
                xAxis: {
                    axisLabel: 'Years'
                },
                yAxis: {
                    axisLabel: 'Events',
                    tickFormat: d3.format(''),
                    axisLabelDistance: 30
                },
                callback: function(chart) {
                    console.log("!!! lineChart callback !!!");
                }
            },
            title: {
                enable: true,
                text: 'Events Per Year (By Closest Country)'
            },
            subtitle: {
                enable: false,
                text: '',
                css: {}
            },
            caption: {
                enable: true,
                html: '<p>This chart displays the events per year by closest country for the currently filtered events.</p>',
                css: {
                    'text-align': 'justify',
                    'margin': '10px 13px 0px 7px'
                }
            }

        },
        data: [],
        /*
            Extract the chart data for this analysis model.
            2-step process:
            1. Create a 2-dimensional map object keyed on country name and year with value yearly number of events for each country.
            2. Transcribe this map object to the chartData array so that it works with nvd3.js

        */
        getData: function(mapData) {
            var features = mapData.data.features,   //extract features array from the passed mapdata
                countries = {},
                chartData = [],
                yearlyValues = [],
                country, year;

            //iterate over the features array to create the 2-dimensional map object
            for (var i = 0; i < features.length; i++) {
                
                //extract the closest country name and year
                country = features[i].properties.closestCountry;
                year = new Date(features[i].properties.occurredOnDate).getFullYear();

                if (country in countries) {  //check if this country has already been stored

                    if (year in countries[country]) {  //country has been stored -- check if this year has been stored
                        countries[country][year] ++;  //year has been stored -- increment its count
                    } else {
                        countries[country][year] = 1;  //year has not been stored -- initialize it
                    }
                } else {
                    countries[country] = {};  //country has not been stored -- initialize its map object
                    countries[country][year] = 1;  //initialize the country counter
                }
            }

            //iterate over the countries again to create the final nvd3.js-friendly object
            for(country in countries) {
                yearlyValues = [];  //yearly values for this country
                for(year in countries[country]) {
                    yearlyValues.push({  //push a new (x,y) pair for this year
                        x: year,
                        y: countries[country][year]
                    });
                }
                chartData.push({  //push a new (key, value) pair for this country
                    values: yearlyValues,
                    key: country
                });
            }

            return chartData;
        }
    };

    return model;


});