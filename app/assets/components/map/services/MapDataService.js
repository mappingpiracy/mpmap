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
      }
    };

    return mapData;

  }
);