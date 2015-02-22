/******************************************

MapController

Alex Klibisz, 1/16/15

 ******************************************/

mpmap.controller('MapController',
  function($scope, $location, $document, $modal,
    MapDataService, ExportDataService,
    LeafletMapModel, EventsPerYearModel) {


    $scope.initialize = function() {
      
      if($scope.filterForm.loaded === false) {
        $scope.filterForm.getData();
        $scope.filterForm.loaded = true;
      }

      //open a generic modal
      $scope.modals.generic.open($scope.messages.events.loading, "");
      MapDataService.getEvents($scope.filterForm.getFilter(), 'geojson')
        .success(function(data, status) {
          //call the map model constructor with the returned data
          $scope.map = LeafletMapModel(data);
        })
        .error(function(data, status) {
          $scope.modals.generic.open($scope.messages.events.error, "");
        })
        .then(function() {
          //reload the analysis data
          $scope.analysis.getData();
          $scope.modals.generic.close();
        });
    };

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
      loaded: false,
      fields: {
        dateRange: {
          years: [ /*getData*/ ],
          selectedYear: new Date().getFullYear(),
          beginDate: {
            value: new Date(new Date().getFullYear(), 0, 1),
            isOpen: false
          },
          endDate: {
            value: new Date(new Date().getFullYear(), 11, 31),
            isOpen: false
          },
          calendarOptions: {
            format: 'yyyy-MM-dd',
            minDate: '1993-01-01',
            maxDate: new Date(),
            toggleOpen: function($event, dateObject) {
              $event.preventDefault();
              $event.stopPropagation();
              dateObject.isOpen = !dateObject.isOpen;
            }
          },
          update: function() {
            $scope.filterForm.fields.dateRange.beginDate.value = new Date($scope.filterForm.fields.dateRange.selectedYear, 0, 1);
            $scope.filterForm.fields.dateRange.endDate.value = new Date($scope.filterForm.fields.dateRange.selectedYear, 11, 31);
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
        conflictInformation: {}
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
        var finalFilter = {},
            beginDate = $scope.filterForm.fields.dateRange.beginDate.value,
            endDate = $scope.filterForm.fields.dateRange.endDate.value,
            tempDate,
            buffer = [];

        tempDate = beginDate.getFullYear() + '-' + (beginDate.getMonth() + 1) + '-' + beginDate.getDate();
        finalFilter.beginDate = tempDate.toString();

        tempDate = endDate.getFullYear() + '-' + (endDate.getMonth() + 1) + '-' + endDate.getDate();
        finalFilter.endDate = tempDate.toString();


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

        return finalFilter;
      },
      getData: function() {
        console.log("get filter form data");
        /*
        $scope.filterForm.fields.dateRange.years
         */
        $scope.filterForm.fields.dateRange.years = MapDataService.getYears();
        /*
        $scope.vesselStatus.all
         */
        $scope.filterForm.fields.vesselInformation.vesselStatus.all = MapDataService.getVesselStatus();
        /*
        $scope.filterForm.fields.locationInformation.territorialWaterStatus.all
        $scope.filterForm.fields.locationInformation.closestCountry.all
        $scope.filterForm.fields.vesselInformation.vesselCountry.all
         */
        MapDataService.getCountries()
          .success(function(data, status) {
            $scope.filterForm.fields.locationInformation.territorialWaterStatus.all = data;
          });
        MapDataService.getCountries()
          .success(function(data, status) {
            $scope.filterForm.fields.locationInformation.closestCountry.all = data;
          });
        MapDataService.getCountries()
          .success(function(data, status) {
            $scope.filterForm.fields.vesselInformation.vesselCountry.all = data;
          });
      }

    };

    /******************************************

    Map object - contains all data and functions for
    the main map (div id "map")

     ******************************************/

    $scope.map = LeafletMapModel();

    /******************************************

    Export object - calls export functionality
    for filters and events.

     ******************************************/
    $scope.export = {

      events: function(format) {
        //export the geojson as is
        if (format == 'geojson') {
          ExportDataService.export($scope.map.geojson, format);
        }
        //get the feature list from the geojson object, convert it to csv, then export
        else if (format == 'csv') {
          ExportDataService.export(MapDataService.convert.geoJsonFeaturesToCSV($scope.map.geojson), format);
        }
      },
      filters: function(format) {
        ExportDataService.export($scope.filterForm.getFilter(), format);
      }
    };

    /******************************************

    Analysis object - contains all models
    for the d3 and nv.d3 data visualizations.

    ******************************************/

    $scope.analysis = {
      models: {
        eventsPerYear: EventsPerYearModel()
      },
      getData: function() {
        EventsPerYearModel($scope.map.geojson,
          $scope.filterForm.fields.locationInformation.closestCountry.selected,
          $scope.filterForm.fields.dateRange.beginDate.value,
          $scope.filterForm.fields.dateRange.endDate.value);
      }
    };

    /******************************************

    Data Loading - call all data loading
    functions.

     ******************************************/
    //$scope.filterForm.getData();
    $scope.initialize();
  }
); //  MapController