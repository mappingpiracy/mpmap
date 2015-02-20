mpmap.service('ExportData',
function($rootScope, $location, $http) {

  var exportData = {

    /*
      Generic export function creates a file of the given 
      format containing the given data.
    */
    export: function(data, format) {
      var fileName, fileContents, fileType, blob;
      fileName = 'mpmamp_export_events_' + new Date().toString('yyyy-MM-dd-HH:mm:ss') + '.' + format;
      if (format.indexOf('json') > -1) {
        fileContents = JSON.stringify(data);
        fileType = 'application/json;';
      } else if (format.indexOf('csv') > -1) {
        fileContents = data;
        fileType = 'text/csv;';
      }

      blob = new Blob([fileContents], {
        type: fileType + 'charset=utf-8;'
      });

      saveAs(blob, fileName);
  }

};

return exportData;

});