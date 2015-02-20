/******************************************

MapController

Alex Klibisz, 1/16/14

******************************************/

mpmap.controller('MapController',
  function($scope, $location, $document, $modal, MapData, ExportData) {


    /******************************************

      Messages object - contains all help, error,
      etc. messages

    ******************************************/

    $scope.messages = {
      help: {
        multiSelect: "Use the search field to filter the \"All\" column. Transfer items from the \"All\" column to the selected column by clicking on them.",
      },
      placeHolder: {
        multiSelectSearch: "Search and select by country name."
      },
      events: {
        loading: 'Events loading. Please wait.',
        error: "Error loading events."
      }
    };

    /******************************************

      Modals object - handles all modals for the
      map view.

    ******************************************/

    $scope.modals = {
      generic: {
        show: false,
        title: null,
        value: null,
        open: function(title, value) {
          $scope.modals.generic.title = title;
          $scope.modals.generic.value = value;
          $scope.modals.generic.show = true;
        },
        close: function() {
          $scope.modals.generic.title = null;
          $scope.modals.generic.value = null;
          $scope.modals.generic.show = false;
        }
      },
      help: {
        show: false,
        open: function() {
          $scope.modals.help.show = true;
        }
      }
    };


    /******************************************

      Filter Form object - contains all data and
      functionality for the event filter form

    ******************************************/

    $scope.filterForm = {

      fields: {
        dateRange: {
          years: [ /*getData*/ ],
          selectedYear: Date.today().getFullYear(),
          beginDate: {
            value: new Date(Date.today().getFullYear(), 0, 1).toString("yyyy-MM-dd"),
            isOpen: false
          },
          endDate: {
            value: new Date(Date.today().getFullYear(), 11, 31).toString("yyyy-MM-dd"),
            isOpen: false
          },
          calendarOptions: {
            format: 'yyyy-MM-dd',
            minDate: '1993-01-01',
            maxDate: Date.today().toString("yyyy-MM-dd"),
            toggleOpen: function($event, dateObject) {
              $event.preventDefault();
              $event.stopPropagation();
              dateObject.isOpen = !dateObject.isOpen;
            }
          },
          update: function() {
            $scope.filterForm.fields.dateRange.beginDate.value = $scope.filterForm.fields.dateRange.selectedYear + "-01-01";
            $scope.filterForm.fields.dateRange.endDate.value = $scope.filterForm.fields.dateRange.selectedYear + "-12-31";
          }
        },
        locationInformation: {
          territorialWaterStatus: {
            searchTerm: "",
            all: [ /*getData*/ ],
            selected: []
          },
          closestCountry: {
            searchTerm: "",
            all: [ /*getData*/ ],
            selected: []
          }
        },
        vesselInformation: {
          vesselCountry: {
            searchTerm: "",
            all: [ /*getData*/ ],
            selected: []
          },
          vesselStatus: {
            all: [ /*getData*/ ],
            selected: []
          }
        },
        conflictInformation: {

        }
      },
      /*
      - pushes the item at from[index] onto to
      - used for filtering and selecting from large lists
      */
      multiSelectTransfer: function(from, to, index) {
        to.push(from[index]);
        from.splice(index, 1);
      },
      /*
      - constructs the filter that will be passed to the API
      */
      getFilter: function() {
        var finalFilter = {
          beginDate: $scope.filterForm.fields.dateRange.beginDate.value,
          endDate: $scope.filterForm.fields.dateRange.endDate.value
        };

        var buffer = [];
        angular.forEach($scope.filterForm.fields.locationInformation.territorialWaterStatus.selected, function(value, key) {
          buffer.push(value.cowId);
        });
        finalFilter.territorialWaterStatus = buffer.join();

        buffer = [];
        angular.forEach($scope.filterForm.fields.locationInformation.closestCountry.selected, function(value, key) {
          buffer.push(value.cowId);
        });
        finalFilter.closestCountry = buffer.join();

        buffer = [];
        angular.forEach($scope.filterForm.fields.vesselInformation.vesselCountry.selected, function(value, key) {
          buffer.push(value.cowId);
        });
        finalFilter.vesselCountry = buffer.join();

        buffer = [];
        angular.forEach($scope.filterForm.fields.vesselInformation.vesselStatus.selected, function(value, key) {
          buffer.push(value);
        });
        finalFilter.vesselStatus = buffer.join();

        console.log(finalFilter);

        return finalFilter;
      },
      getData: function() {
        /*
          $scope.filterForm.fields.dateRange.years
        */
        $scope.filterForm.fields.dateRange.years = MapData.getYears();
        /*
          $scope.vesselStatus.all
        */
        $scope.filterForm.fields.vesselInformation.vesselStatus.all = MapData.getVesselStatus();
        /*
          $scope.filterForm.fields.locationInformation.territorialWaterStatus.all
          $scope.filterForm.fields.locationInformation.closestCountry.all
          $scope.filterForm.fields.vesselInformation.vesselCountry.all
        */
        MapData.getCountries()
          .success(function(data, status) {
            $scope.filterForm.fields.locationInformation.territorialWaterStatus.all = data;
            $scope.filterForm.fields.locationInformation.closestCountry.all = data;
            $scope.filterForm.fields.vesselInformation.vesselCountry.all = data;
          });
      }

    };

    /******************************************

      Map object - contains all data and functions for
      the main map (div id "map")

    ******************************************/

    $scope.map = {
      defaults: {
        tileLayer: "http://{s}.tiles.mapbox.com/v3/utkpiracyscience.k1ei0a8m/{z}/{x}/{y}.png",
        maxZoom: 14
      },
      geojson: [],
      center: {
        lat: 0,
        lng: 0,
        zoom: 2
      },
      createMarker: function(feature, latlng) {
        return L.circleMarker(latlng, $scope.map.markerOptions);
      },
      createPopup: function(feature, layer) {
        popupOptions = $scope.map.popupOptions;
        popupContent = '<div class="popup-content"><ul>';
        popupContent += '<li>Id:                        <strong>' + feature.properties.id + '</strong></li>';
        popupContent += '<li>Date:                      <strong>' + feature.properties.occurredOnDate + '</strong></li>';
        popupContent += '<li>Time:                      <strong>' + feature.properties.occurredOnTime + '</strong></li>';
        popupContent += '<li>Closest Country:           <strong>' + feature.properties.closestCountry + '</strong></li>';
        popupContent += '<li>Territorial Water Status:  <strong>' + feature.properties.territorialWaterStatus + '</strong></li>';
        popupContent += '<li>Vessel Flag Country:       <strong>' + feature.properties.vesselFlagCountry + '</strong></li>';
        popupContent += '<li>Vessel Status:             <strong>' + feature.properties.vesselStatus + '</strong></li>';
        popupContent += '</ul></div>';
        layer.bindPopup(popupContent, popupOptions);
      },
      markerOptions: {
        radius: 7,
        fillColor: "#ff7800",
        color: "#000",
        weight: 1,
        opacity: 1,
        fillOpacity: 0.8
      },
      popupOptions: {
        maxWidth: 300
      },
      /*
      - get the current filters
      - retrieve the events via the mapdata service with the given filters
      - replace the map's geojson with the new events
      */
      getData: function() {
        $scope.modals.generic.open($scope.messages.events.loading, "");
        MapData.getEvents($scope.filterForm.getFilter(), 'geojson')
          .success(function(data, status) {
            $scope.map.geojson = {
              data: data,
              // pointToLayer: $scope.map.createMarker,
              onEachFeature: $scope.map.createPopup
            };
            $scope.modals.generic.close();
            console.log('Events updated.');
            console.log(data);
          })
          .error(function(data, status) {
            $scope.modals.generic.close();
            $scope.modals.generic.open($scope.messages.events.error, "");
          });
      }
    };

    /******************************************

      Export object - calls export functionality
      for filters and events.

    ******************************************/
    $scope.export = {

      events: function(format) {
        ExportData.export($scope.map.geojson, format);
      },
      filters: function(format) {
        ExportData.export($scope.filterForm.getFilter(), format);
      }
    };

    /******************************************

      Analysis object - contains all models
      for the d3 and nv.d3 data visualizations.

      TODO: Abstract this to another file or 
      a service -- http://joelhooks.com/blog/2013/04/24/modeling-data-and-state-in-your-angularjs-application/

    ******************************************/

    $scope.analysis = {
      models: {
        eventsPerYear: {
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
              text: 'Events Per Year (Note: we are currently testing, and this is randomly generated data.)'
            },
            subtitle: {
              enable: false,
              text: '',
              css: {}
            },
            caption: {
              enable: true,
              html: '<p>This chart displays the events per year for the currently filtered events.',
              css: {
                'text-align': 'justify',
                'margin': '10px 13px 0px 7px'
              }
            }
          },
          data: [],
          getData: function() {
            console.log("randData called");
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
              key: 'Somalia',
              color: '#ff7f0e'
            }, {
              values: b,
              key: 'Kenya',
              color: '#2ca02c'
            }, {
              values: c,
              key: 'Yemen',
              color: '#7777ff'
            }];
            return r;
          }

        }
      },
      getData: function() {
        $scope.analysis.models.eventsPerYear.data = $scope.analysis.models.eventsPerYear.getData();
      }
    };

    /******************************************

      Data Loading - call all data loading
      functions.

    ******************************************/
    $scope.filterForm.getData();
    $scope.map.getData();
    $scope.analysis.getData();
  }
); //  MapController