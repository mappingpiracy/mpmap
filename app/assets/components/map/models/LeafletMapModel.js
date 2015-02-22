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
			popupOptions = {
				maxWidth: 300
			};
			popupContent = '<div class="popup-content"><ul>';
			popupContent += '<li>Id:                        <strong>' + feature.properties.id + '</strong></li>';
			popupContent += '<li>Date:                      <strong>' + feature.properties.occurredOnDate + '</strong></li>';
			popupContent += '<li>Time:                      <strong>' + feature.properties.occurredOnTime + '</strong></li>';
			popupContent += '<li>Closest Country:           <strong>' + feature.properties.closestCountry + '</strong></li>';
			popupContent += '<li>Territorial Water Status:  <strong>' + feature.properties.territorialWaterStatus + '</strong></li>';
			popupContent += '<li>Vessel Flag Country:       <strong>' + feature.properties.vesselFlagCountry + '</strong></li>';
			popupContent += '<li>Vessel Status:             <strong>' + feature.properties.vesselStatus + '</strong></li>';
			popupContent += '</ul></div>';
			layer.bindPopup(popupContent, popupOptions);
		}
	};

	return function(data) {
		if (arguments.length === 1) {
			console.log(data);
			model.geojson = {
				data: data,
				//pointToLayer: model.createMarker,
				onEachFeature: model.createPopup
			};
		}
		return model;
	};

});