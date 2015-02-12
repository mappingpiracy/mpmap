var mpmap = angular.module('mpmap',
  ['ngRoute',
  'appControllers',
  'ui.layout',                //  angular-ui, used for resizing divs
  'leaflet-directive',        //  leaflet-directive, used for adding leaflet to angular
  'ui.bootstrap',             //  for bootstrap js functionality
  'ngModal',                  //  angular modal popups
  'nvd3'
  ]);

var appControllers = angular.module('appControllers',[]);

mpmap.config(['$routeProvider', function($routeProvider){
  $routeProvider.
  when('/map', {
    templateUrl: 'assets/views/map.html',
    controller: 'MapController'
  }).
  when('/about', {
    templateUrl: 'assets/views/about.html',
    controller: 'StaticPageController'
  }).
  when('/help', {
    templateUrl: 'assets/views/help.html',
    controller: 'StaticPageController'
  }).
  when('/status', {
    templateUrl: 'assets/views/status.html',
    controller: 'StaticPageController'
  }).
  otherwise({
    redirectTo: '/map'
  });
}]);
