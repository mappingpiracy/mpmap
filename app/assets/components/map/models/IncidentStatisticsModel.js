mpmap.service('IncidentStatisticsModel', function() {
	var model = {
		incidentCount: 0,
		closestCountryCount: 0,
		waterCountryCount: 0,
		vesselCountryCount: 0
	};

	function getData(data) {
		model.incidentCount = 50;
	}

	return function(data) {
		return model;
	};
});