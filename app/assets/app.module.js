var mpmap = angular.module('mpmap',
  ['ngRoute',
  'appControllers',
  'ui.layout',                //  angular-ui, used for resizing divs
  'leaflet-directive',        //  leaflet-directive, used for adding leaflet to angular
  'ui.bootstrap',             //  for bootstrap js functionality
  'ngModal',                  //  angular modal popups
  'nvd3'						//  nvd3 for data visualization
  ]);

var appControllers = angular.module('appControllers',[]);