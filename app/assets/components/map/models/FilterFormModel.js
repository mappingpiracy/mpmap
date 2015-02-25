/******************************************

FilterFormModel

Alex Klibisz, 2/21/15

This service handles all options and data 
manipulation for the event filter form.

******************************************/

mpmap.service('FilterFormModel', function(MapDataService) {

	var model = {
		loaded: false,
		fields: {
			dateRange: {
				years: [],
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
					minDate: new Date(1993, 0, 1),
					maxDate: new Date(),
					toggleOpen: function($event, dateObject) {
						$event.preventDefault();
						$event.stopPropagation();
						dateObject.isOpen = !dateObject.isOpen;
					}
				},
				update: function() {
					updateSelectedYear(year);
				}
			},
			locationInformation: {
				closestCountry: {
					title: 'Closest Country',
					filterPlaceHolder: 'Start typing to filter the lists below.',
					labelAll: 'All Items',
					labelSelected: 'Selected Items',
					helpMessage: '',
					orderProperty: 'name',
					items: [],
					selected: [] 
				},
				territorialWaterStatus: {
					title: 'Territorial Water Status',
					filterPlaceHolder: 'Start typing to filter the lists below.',
					labelAll: 'All Items',
					labelSelected: 'Selected Items',
					helpMessage: '',
					orderProperty: 'name',
					items: [],
					selected: [] 
				}
			},
			vesselInformation: {
				vesselCountry: {
					title: 'Vessel Country',
					filterPlaceHolder: 'Start typing to filter the lists below.',
					labelAll: 'All Items',
					labelSelected: 'Selected Items',
					helpMessage: '',
					orderProperty: 'name',
					items: [],
					selected: [] 
				},
				vesselStatus: {
					title: 'Vessel Status',
					filterPlaceHolder: 'Start typing to filter the lists below.',
					labelAll: 'All Items',
					labelSelected: 'Selected Items',
					helpMessage: '',
					orderProperty: 'name',
					items: [],
					selected: [] 
				}
			},
			conflictInformation: {

			}
		},
		getFilter: function() {
			return getFilter();
		},
		getData: function() {
			return getData();
		}
	};

	/*
		Set the begin and end dates to the first and last day of the 
		selected year.
	*/
	function updateSelectedYear() {
		var year = model.fields.dateRange.selectedYear;
		model.fields.dateRange.beginDate.value = new Date(year, 0, 1);
		model.fields.dateRange.endDate.value = new Date(year, 11, 31);
	}

	/*
		Store the selected values from the filter form in a single object
		that will be passed to the MapData API.
	*/
	function getFilter() {
		console.log("getFilter");
		var finalFilter = {},
		dateRange = model.fields.dateRange,
		locationInformation = model.fields.locationInformation,
		vesselInformation = model.fields.vesselInformation,
		buffer = [];

		//concatenate begin and end date strings
		finalFilter.beginDate = (dateRange.beginDate.value.getFullYear() + '-' + (dateRange.beginDate.value.getMonth() + 1) + '-' + dateRange.beginDate.value.getDate()).toString();
		finalFilter.endDate = (dateRange.endDate.value.getFullYear() + '-' + (dateRange.endDate.value.getMonth() + 1) + '-' + dateRange.endDate.value.getDate()).toString();

		//push all of the selected cowIds for the four country fields to a buffer array.
		//then join the array to a string to be included with the final filter.
		buffer.length = 0;
		angular.forEach(locationInformation.closestCountry.selected, function(value, key) {
			buffer.push(value.cowId);
		});
		finalFilter.closestCountry = buffer.join();

		buffer.length = 0;
		angular.forEach(locationInformation.territorialWaterStatus.selected, function(value, key) {
			buffer.push(value.cowId);
		});
		finalFilter.territorialWaterStatus = buffer.join();
		
		buffer.length = 0;
		angular.forEach(vesselInformation.vesselCountry.selected, function(value, key) {
			buffer.push(value.cowId);
		});
		finalFilter.vesselCountry = buffer.join();

		buffer.length = 0;
		angular.forEach(vesselInformation.vesselStatus.selected, function(value, key) {
			buffer.push(value.id);
		});
		finalFilter.vesselStatus = buffer.join();

		return finalFilter;
	}

	/*
		Call the MapDataService to populate the form fields.
	*/
	function getData() {
		console.log("getData");

		if(model.loaded) {
			return;
		}

		//get years
		model.fields.dateRange.years = MapDataService.getYears();

		//get vessel status options
		model.fields.vesselInformation.vesselStatus.items = MapDataService.getVesselStatus();

		//get countries (3 separate copies so they are changed individually)
		MapDataService.getCountries()
			.success(function(data) {
				model.fields.locationInformation.closestCountry.items = data;
			});
		MapDataService.getCountries()
			.success(function(data) {
				model.fields.locationInformation.territorialWaterStatus.items = data;
			});
		MapDataService.getCountries()
			.success(function(data) {
				model.fields.vesselInformation.vesselCountry.items = data;
			});

		model.loaded = 1;
	}

	return function() {
		getData();
		return model;
	};

});