mpmap.factory('MapData',
['$rootScope', '$location', '$http',
 function($rootScope, $location, $http) {

  var mapData = {

    loadMap : function() {
      alert('load map called!');
    },

    getEvents : function() {
      return $http.get("assets/data/events.json");
    },

    getEvents2 : function() {
      return $http.get("assets/data/events2.json");
    },

    getCountries : function() {
      return [{id:"700",name:"Afghanistan (AFG)"},{id:"615",name:"Algeria (ALG)"},{id:"232",name:"Andorra (AND)"},{id:"339",name:"Albania (ALB)"},{id:"007",name:"Testing a country with a really long name that will take multiple lines"}];
    },

    getVesselStatus : function() {
      return ["Anchored", "Berthed", "Moored", "Stationalry", "Steaming", "Unspecified"];
    }

  };

  return mapData;

}]);
