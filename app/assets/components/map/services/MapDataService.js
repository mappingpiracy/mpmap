mpmap.service('MapDataService',
  function($rootScope, $location, $http) {

    var mapData = {

      getIncidents: function(eventFilter, format) {
        eventFilter.format = format;
        return $http.get('/mapdata/incidents', {
          params: eventFilter
        });
      },

      /*
          return the list of countries
      */
      getCountries: function() {
        return $http.get('/mapdata/countries');
      },

      /*
          return the years from this year back to 1992 - TODO: abstract this to an API call
      */
      getYears: function() {
        var years = [];
        for (var i = new Date().getFullYear(); i > 1992; i--) years.push(i);
        return years;
      },

      /*
          return the list of vessel statuses - TODO: abstract this to an API call
      */
      getVesselStatus: function() {
        return [{"id": "Anchored", "name": "Anchored"}, {"id": "Berthed", "name": "Berthed"}, {"id": "Moored", "name": "Moored"}, {"id": "Stationary", "name": "Stationary"}, {"id": "Berthed", "name": "Berthed"}, {"id": "Unspecified", "name": "Unspecified"}];
      },

      /*
          mapdata-specific conversion from geoJson features to CSV

          - works by loading data into row array, then pushing the row array as a
          comma-separated list to the body array.
          - initialize the column headings as the first row
          - then iterate over the geojson features list to get the corresponding 
          column values
          - return the body list as a string with fields separated by new line characters

          TODO: this is pretty brittle. If the data schema changes, this will have to change.
          See about making this more generic by just looking at the object and its properties.
      */
      convert: {
        geoJsonFeaturesToCSV: function(data) {
          var row, body = [];
            
          //initialize the column headings and push them to the body
          row = ['id', //0
            'date', //1
            'time', //2
            'closest_country', //3
            'territorial_water_status', //4
            'vessel_flag_country', //5
            'vessel_status', //6
            'longitude', //7
            'latitude' //8
          ];
          body.push(row.join(','));
          
          //load the remaining data into rows and into the body
          data = data.data.features; //get the feature list
          for (var i = 0; i < data.length; i++) {
            row = [
              data[i].properties.id, //0
              data[i].properties.occurredOnDate, //1
              data[i].properties.occurredOnTime, //2
              data[i].properties.closestCountry, //3
              data[i].properties.territorialWaterStatus, //4
              data[i].properties.vesselFlagCountry, //5
              data[i].properties.vesselStatus, //6
              data[i].geometry.coordinates[0], //7
              data[i].geometry.coordinates[1] //8
            ];
            body.push(row.join(','));
          }

          //return the body list
          return body.join('\n');
        }
      }

    };

    return mapData;

  }
);