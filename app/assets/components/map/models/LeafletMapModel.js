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
			popupContent += '<li>Id:                        <strong>' + feature.properties.id + '</strong></li>';
			popupContent += '<li>Date:                      <strong>' + feature.properties.date + '</strong></li>';
			popupContent += '<li>Time of Day:                      <strong>' + feature.properties.timeOfDay + '</strong></li>';
			popupContent += '<li>Closest Country:           <strong>' + feature.properties.closestCountry + '</strong></li>';
			popupContent += '<li>Water Country:  <strong>' + feature.properties.waterCountry + '</strong></li>';
			popupContent += '<li>Vessel Country:       <strong>' + feature.properties.vesselCountry + '</strong></li>';
			popupContent += '<li>Vessel Status:             <strong>' + feature.properties.vesselStatus + '</strong></li>';
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