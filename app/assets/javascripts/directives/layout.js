mpmap.directive('filter-form', function() {
    return function (scope, element, attrs) {
        //element.height($(window).height() - $('.header').outerHeight());
        console.log("i'm here");
        element.height(50);
    };
});