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
      },
      events: {
        loading : "Events loading. Please wait.",
        error: "Error loading events."
      },
      modal: {
        show: false,
        title: "",
        value: "",
        display: function(title, value) {
            $scope.messages.modal.title = title;
            $scope.messages.modal.value = value;
            $scope.messages.modal.show = true;
        },
        hide: function() {
            $scope.messages.modal.title = "";
            $scope.messages.modal.value = "";
            $scope.messages.modal.show = false;
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
          years: MapData.getYears(),
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
            all: MapData.getVesselStatus(),
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
          endDate: $scope.filterForm.fields.dateRange.endDate.value,
          territorialWaterStatus: [],
          closestCountry: [],
          vesselCountry: [],
          vesselStatus: []
        };

        angular.forEach($scope.filterForm.fields.locationInformation.territorialWaterStatus.selected, function(value, key) {
          value.id = value.cowId;
          finalFilter.territorialWaterStatus.push(value.id);
        });

        angular.forEach($scope.filterForm.fields.locationInformation.closestCountry.selected, function(value, key) {
          value.id = value.cowId;
          finalFilter.closestCountry.push(value.id);
        });

        angular.forEach($scope.filterForm.fields.vesselInformation.vesselCountry.selected, function(value, key) {
          value.id = value.cowId;
          finalFilter.vesselCountry.push(value.id);
        });

        angular.forEach($scope.filterForm.fields.vesselInformation.vesselStatus.selected, function(value, key) {
          finalFilter.vesselStatus.push(value);
        });

        return finalFilter;
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
     updateEvents: function() {
       $scope.messages.modal.display($scope.messages.events.loading, "");
       MapData.getEvents($scope.filterForm.getFilter())
       .success(function(data, status) {
           $scope.map.geojson = {
               data: data,
               pointToLayer: $scope.map.createMarker,
               onEachFeature: $scope.map.createPopup
           };
           $scope.messages.modal.hide();
           console.log('Events updated.');
       })
       .error(function(data, status) {
           $scope.messages.modal.hide();
           $scope.messages.modal.display($scope.messages.events.error, "");
       });
     }
    };

    /*
    - Initial event loading, executes when the controller is called.
    */
    $scope.map.updateEvents();

  }
]); //  MapController
