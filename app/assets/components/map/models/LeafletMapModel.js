/******************************************

LeafletMapModel

Alex Klibisz, 2/21/15

This service handles all options and data 
manipulation for the leaflet map on the main 
map view.

It is initialized via the final return 
function with a passed geojson object.

******************************************/

mpmap.service('LeafletMapModel', function() {

	var model = {
		defaults: {
			tileLayer: "http://{s}.tiles.mapbox.com/v3/utkpiracyscience.k1ei0a8m/{z}/{x}/{y}.png",
			maxZoom: 14
		},
		center: {
			lat: 0,
			lng: 0,
			zoom: 2
		},
		createMarker: function(feature, latlng) {
			return L.circleMarker(latlng, {
				radius: 7,
				fillColor: "#ff7800",
				color: "#000",
				weight: 1,
				opacity: 1,
				fillOpacity: 0.8
			});
		},
		createPopup: function(feature, layer) {
			popupContent = '<div class="popup-content"><ul>';
			popupContent += '<li>Id: ' + feature.properties.id + '</li>';
			popupContent += '<li>Date: ' + feature.properties.date + '</li>';
			popupContent += '<li>Time of Day: ' + feature.properties.timeOfDay + '</li>';
			popupContent += '<li>Latitude: ' + feature.properties.latitude + '</li>';
			popupContent += '<li>Longitude: ' + feature.properties.longitude + '</li>';
			popupContent += '<li>Closest Country: ' + feature.properties.closestCountry + '</li>';
			popupContent += '<li>Water Country: ' + feature.properties.waterCountry + '</li>';
			popupContent += '<li>Vessel Name: ' + feature.properties.vesselName + '</li>';
			popupContent += '<li>Vessel Country: ' + feature.properties.vesselCountry + '</li>';
			popupContent += '<li>Vessel Status: ' + feature.properties.vesselStatus + '</li>';
			popupContent += '<li>Violence Dummy: ' + feature.properties.violenceDummy + '</li>';
			popupContent += '</ul></div>';
			layer.bindPopup(popupContent, {
				maxWidth: 300
			});
		}
	};

	return function(data) {
		if (arguments.length === 1) {
			model.geojson = {
				data: data,
				//pointToLayer: model.createMarker,
				onEachFeature: model.createPopup
			};
		}
		return model;
	};

});