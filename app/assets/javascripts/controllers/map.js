/******************************************

MapController

Alex Klibisz, 1/16/14

******************************************/

mpmap.controller('MapController', ['$scope', '$location', '$document', '$http', 'MapData',
  function($scope, $location, $document, $http, MapData) {

    /******************************************

      Messages object - contains all help, error,
      etc. messages

    ******************************************/

    $scope.messages = {
      help: {
        multiSelect: "Use the search field to filter the \"All\" column. Transfer items from the \"All\" column to the selected column by clicking on them."
      },
      placeHolder: {
        multiSelectSearch: "Search and select by country name."
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
      }
    };

    MapData.getEvents().success(function(data, status) {
      $scope.map.geojson = {
        data: data,
        pointToLayer: $scope.map.createMarker,
        onEachFeature: $scope.map.createPopup
      };
    });


    /******************************************

      Filter Form object - contains all data and
      functionality for the event filter form

    ******************************************/

    $scope.filterForm = {

      fields: {
        dateRange: {
          years: ["2015", "2014", "2013", "2012", "2011", "2010"],
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
            $scope.filter.dateRange.beginDate.value = $scope.filter.dateRange.selectedYear + "-01-01";
            $scope.filter.dateRange.endDate.value = $scope.filter.dateRange.selectedYear + "-12-31";
          }
        },
        locationInformation: {
          territorialWaterStatus: {
            searchTerm: "",
            all: MapData.getCountries(),
            selected: []
          },
          closestCountry: {
            searchTerm: "",
            all: MapData.getCountries(),
            selected: []
          }
        },
        vesselInformation: {
          vesselCountry: {
            searchTerm: "",
            all: MapData.getCountries(),
            selected: []
          },
          vesselStatus: {
            all: ["Anchored", "Berthed", "Moored", "Stationalry", "Steaming", "Unspecified"],
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
      updateFilter: function() {
        var finalFilter = {
          beginDate: $scope.filter.dateRange.beginDate.value,
          endDate: $scope.filter.dateRange.endDate.value,
          territorialWaterStatus: [],
          closestCountry: [],
          vesselCountry: []
        };

        angular.forEach($scope.filter.locationInformation.territorialWaterStatus.selected, function(value, key) {
          finalFilter.territorialWaterStatus.push(value.id);
        });

        angular.forEach($scope.filter.locationInformation.closestCountry.selected, function(value, key) {
          finalFilter.closestCountry.push(value.id);
        });

        angular.forEach($scope.filter.vesselInformation.vesselCountry.selected, function(value, key) {
          finalFilter.vesselCountry.push(value.id);
        });

        alert(JSON.stringify(finalFilter));
      }

    };


  }
]); //  MapController
