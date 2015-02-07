mpmap.factory('MapData', ['$rootScope', '$location', '$http',
  function($rootScope, $location, $http) {

    var mapData = {

      loadMap: function() {
        alert('load map called!');
      },

      getEvents: function(eventFilter) {
        return $http.post('/mapdata/events', eventFilter);
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
        for (var i = Date.today().getFullYear(); i > 1992; i--) years.push(i);
        return years;
      },

      /*
          return the list of vessel statuses - TODO: abstract this to an API call
      */
      getVesselStatus: function() {
        return ["Anchored", "Berthed", "Moored", "Stationary", "Steaming", "Unspecified"];
      }

    };

    return mapData;

  }
]);