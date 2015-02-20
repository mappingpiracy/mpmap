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
                text: 'Events Per Year (based on closest country)'
            },
            subtitle: {
                enable: false,
                text: '',
                css: {}
            },
            caption: {
                enable: true,
                html: '<p>This chart displays the events per year by closest country for the currently filtered events.',
                css: {
                    'text-align': 'justify',
                    'margin': '10px 13px 0px 7px'
                }
            }

        },
        data: [],
        /*
            final return object should be essentially a list with country
            names, arrays of their yearly event counts
        */
        getData: function(mapData) {
            var features = mapData.data.features;
            var countries = { };
            var key, val, year, i, j;
            var temp = [ { x: 2000, y: 25 }, { x: 2002, y: 30 }, { x: 2003, y: 40 } ];

            for(i = 0; i < features.length; i++) {
                key = features[i].properties.closestCountry;
                year = new Date(features[i].properties.occurredOnDate).getFullYear();
                if(key in countries) {
                    for(j = 0; j < countries[key].length; j++) {
                        if(countries[key][j].x == year) {
                            countries[key][j].y++;
                        }
                    }
                } else {
                    val = {
                        x: year,
                        y: 1
                    };
                    countries[key] = [];
                    countries[key].push(val);
                }
            }

            console.log(countries);

            return null;

        },
        getData2: function(mapData) {
            console.log("getData", mapData);
            var a = [];
            var b = [];
            var c = [];

            for (var i = 1993; i < 2016; i++) {
                a.push({
                    x: i,
                    y: Math.floor((Math.random() * 50) + 1)
                });
                b.push({
                    x: i,
                    y: Math.floor((Math.random() * 50) + 1)
                });
                c.push({
                    x: i,
                    y: Math.floor((Math.random() * 50) + 1)
                });
            }

            var r = [{
                values: a,
                key: 'Somalia'
            }, {
                values: b,
                key: 'Kenya'
            }, {
                values: c,
                key: 'Yemen'
            }];
            return r;
        }
    };

    return model;


});