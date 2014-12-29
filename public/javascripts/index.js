/**
 * Created by alex on 10/23/14.
 */

// Global variables
var map;                //  Leaflet map
var events;          //  Events currently displayed on the map in GeoJSON format.
var eventFilters;    //  Filters currently applied to the map via the filter data form
var eventLayer;      //  Map layer that holds all events.

/*
 * Inital page load
 */
$(document).ready(function() {
    initializeForms();
    initializeMap();
    populateEvents();

});

/*
 * Event handlers
 */
$('#filter-events-update').click(function(){
    populateEvents();
});

// Show advanced filtering options
$('#filter-incidents-advanced-toggle').click(function(){
    $('#filter-events-advanced').toggle(0, function(){
        return false;
    });
});

$('#export-events').click(function(){
    exportEvents();
});

$('#overlay-message > .message-dismiss').click(function() {
    hideOverlayMessage();
});





/*
 * udpateEventFilters() 
 * stores values from the event filter form in the eventFilters global variable.
 */
function updateEventFilters(){
    
    eventFilters = null;
    eventFilters = new Object();
    
    eventFilters.beginDate = $('#begin-date').val();
    eventFilters.endDate = $('#end-date').val();
    
    eventFilters.territorialWaterStatus = [];
    
    $('#territorial-water-status :selected').each(function(i, selected){
       eventFilters.territorialWaterStatus[i] = $(selected).val(); 
    });
    
    eventFilters.closestCountry = [];
    
    $('#closest-country :selected').each(function(i, selected){
       eventFilters.closestCountry[i] = $(selected).val();
    });
    
    eventFilters.vesselCountry = [];
    
    $('#vessel-country :selected').each(function(i, selected){
       eventFilters.vesselCountry[i] = $(selected).val(); 
    });
    
}

function initializeForms() {
    //  Set begin and end dates
    var beginDate = $("#begin-date");
    var endDate = $("#end-date");
    var today = new Date();
    endDate.val(today.toString("yyyy-MM-dd"));
    beginDate.val(today.last().year().toString("yyyy-MM-dd"));
}

/*
 * initializeMap()
 * initializes the map using Leaflet
 */
function initializeMap() {

    // Initialize Leaflet map using mapbox tiles from project under utkpiracyscience account
    L.mapbox.accessToken = 'pk.eyJ1IjoidXRrcGlyYWN5c2NpZW5jZSIsImEiOiJZWEZXOHMwIn0.Q6rdOkInfnpwiA2H0wlIcQ';
    map = L.mapbox.map('map', 'utkpiracyscience.k1ei0a8m').setView([0, 0], 2);

}

/*
 * populateEvents()
 * call updateEventFilters(), removes all of the current events from the map,
 * populates the map with events returned from api based on filters.
 */
function populateEvents() {
    //  Display loading message
    displayOverlayMessage("Loading events, please wait.");

    //  Update event filters
    updateEventFilters();
    
    //  Clear the current events
    if(map.hasLayer(eventLayer)) {
        map.removeLayer(eventLayer);
    }

    //  Post filters to mapdata api; if successfull, populate map with responding data
    $.ajax({
        type:  'POST',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(eventFilters),
        url: '/mapdata/events',
        success: function(response) {
            console.log('/mapdata/events POST successful.');
            console.log(response);
            events = response;
            eventLayer = L.geoJson(events, {
                 onEachFeature: createPopup,
                 pointToLayer: createCustomMarker,
            }).addTo(map);
            hideOverlayMessage();
        }
    });

}

/*
    createPopup
    initializes the leaflet popup for the passed feature
*/
function createPopup(feature, layer) {
    popupOptions = {maxWidth: 300};

    var occurredOnDate = feature.properties["occurredOnDate"];
    //var occurredOnTime = feature.properties["occurredOnTime"];
    var popupContent;
    popupContent = '<strong>Event Information</strong>';
    popupContent += '<p>Id: ' + feature.properties['id'] + '</p>';
    popupContent += '<p>Date: ' + feature.properties['occurredOnDate'] + '</p>';
    popupContent += '<p>Time: ' + feature.properties['occurredOnTime'] + '</p>';
    popupContent += '<p>Closest Country: ' + feature.properties['closestCountry'] + '</p>';
    popupContent += '<p>Territorial Water Status: ' + feature.properties['territorialWaterStatus'] + '</p>';
    popupContent += '<p>Vessel Flag Country: ' + feature.properties['vesselFlagCountry'] + '</p>';
    layer.bindPopup(popupContent, popupOptions);
}

/*
    createCustomMarker
    creates a marker for the passed feature
*/
function createCustomMarker(feature, coordinates) {
    return L.circleMarker(coordinates, {
        radius: 8,
        fillColor: "#ff7800",
        color: "#000",
        weight: 1,
        opacity: 1,
        fillOpacity: 0.8
    });
}

function displayOverlayMessage(message) {
    var overlayMessage = $("#overlay-message");
    var overlayMessageContent = $("#overlay-message > .message-content");
    overlayMessageContent.append("<p>" + message + "</p>");
    overlayMessage.fadeIn();
}

function hideOverlayMessage() {
    var overlayMessage = $("#overlay-message");
    var overlayMessageContent = $("#overlay-message > .message-content");
    overlayMessage.fadeOut();
    overlayMessageContent.empty();
}


/*
 * exportEvents()
 */
function exportEvents(){
    //  Post filters to mapdata api; if successfull, populate map with responding data
    $.ajax({
        type:  'POST',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(events),
        url: '/mapdata/export',
        success: function(response) {
            console.log('/mapdata/export POST successful.');
            console.log(response);
        }
    });
}
/*
 * ajax post that returns the data posted
 */
function postEcho(echo) {
    $.ajax({
        type: 'POST',
        url: '/mapdata/echo',
        datatype: 'json',
        data: {
            echo: echo
        },
        success: function(data, textStatus, jqXHR) {
            console.log('Echo Successful.');
            console.log(data);
        }

    });
}
