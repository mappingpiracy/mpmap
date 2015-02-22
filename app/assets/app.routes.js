mpmap.config(['$routeProvider', function($routeProvider){
  $routeProvider.
  when('/map', {
    templateUrl: 'assets/components/map/MapView.html',
    controller: 'MapController'
  }).
  when('/about', {
    templateUrl: 'assets/components/about/AboutView.html',
    controller: 'StaticPageController'
  }).
  when('/help', {
    templateUrl: 'assets/components/help/HelpView.html',
    controller: 'StaticPageController'
  }).
  when('/status', {
    templateUrl: 'assets/components/status/StatusView.html',
    controller: 'StaticPageController'
  }).
  otherwise({
    redirectTo: '/map'
  });
}]);