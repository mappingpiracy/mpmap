mpmap.factory('MapData',
['$rootScope', '$location', '$http',
 function($rootScope, $location, $http) {

  var mapData = {

    loadMap : function() {
      alert('load map called!');
    },

    getEvents : function(eventFilter) {
      return $http.post('/mapdata/events', eventFilter);
    },

    /*
        return the list of countries - TODO: abstract this to an API call
    */
    getCountries : function() {
      //return $http.get('/mapdata/countries');
      return [{"id":172,"cowId":700,"name":"Afghanistan","abbreviation":"AFG"},{"id":74,"cowId":339,"name":"Albania","abbreviation":"ALB"},{"id":150,"cowId":615,"name":"Algeria","abbreviation":"ALG"},{"id":46,"cowId":232,"name":"Andorra","abbreviation":"AND"},{"id":135,"cowId":540,"name":"Angola","abbreviation":"ANG"},{"id":14,"cowId":58,"name":"Antigua & Barbuda","abbreviation":"AAB"},{"id":34,"cowId":160,"name":"Argentina","abbreviation":"ARG"},{"id":93,"cowId":371,"name":"Armenia","abbreviation":"ARM"},{"id":204,"cowId":900,"name":"Australia","abbreviation":"AUL"},{"id":61,"cowId":305,"name":"Austria","abbreviation":"AUS"},{"id":60,"cowId":300,"name":"Austria-Hungary","abbreviation":"AUH"},{"id":95,"cowId":373,"name":"Azerbaijan","abbreviation":"AZE"},{"id":53,"cowId":267,"name":"Baden","abbreviation":"BAD"},{"id":3,"cowId":31,"name":"Bahamas","abbreviation":"BHM"},{"id":168,"cowId":692,"name":"Bahrain","abbreviation":"BAH"},{"id":188,"cowId":771,"name":"Bangladesh","abbreviation":"BNG"},{"id":9,"cowId":53,"name":"Barbados","abbreviation":"BAR"},{"id":49,"cowId":245,"name":"Bavaria","abbreviation":"BAV"},{"id":92,"cowId":370,"name":"Belarus","abbreviation":"BLR"},{"id":39,"cowId":211,"name":"Belgium","abbreviation":"BEL"},{"id":17,"cowId":80,"name":"Belize","abbreviation":"BLZ"},{"id":108,"cowId":434,"name":"Benin","abbreviation":"BEN"},{"id":186,"cowId":760,"name":"Bhutan","abbreviation":"BHU"},{"id":31,"cowId":145,"name":"Bolivia","abbreviation":"BOL"},{"id":79,"cowId":346,"name":"Bosnia and Herzegovina","abbreviation":"BOS"},{"id":143,"cowId":571,"name":"Botswana","abbreviation":"BOT"},{"id":30,"cowId":140,"name":"Brazil","abbreviation":"BRA"},{"id":200,"cowId":835,"name":"Brunei","abbreviation":"BRU"},{"id":84,"cowId":355,"name":"Bulgaria","abbreviation":"BUL"},{"id":113,"cowId":439,"name":"Burkina Faso","abbreviation":"BFO"},{"id":129,"cowId":516,"name":"Burundi","abbreviation":"BUI"},{"id":194,"cowId":811,"name":"Cambodia","abbreviation":"CAM"},{"id":118,"cowId":471,"name":"Cameroon","abbreviation":"CAO"},{"id":2,"cowId":20,"name":"Canada","abbreviation":"CAN"},{"id":101,"cowId":402,"name":"Cape Verde","abbreviation":"CAP"},{"id":121,"cowId":482,"name":"Central African Republic","abbreviation":"CEN"},{"id":122,"cowId":483,"name":"Chad","abbreviation":"CHA"},{"id":33,"cowId":155,"name":"Chile","abbreviation":"CHL"},{"id":178,"cowId":710,"name":"China","abbreviation":"CHN"},{"id":24,"cowId":100,"name":"Colombia","abbreviation":"COL"},{"id":146,"cowId":581,"name":"Comoros","abbreviation":"COM"},{"id":123,"cowId":484,"name":"Congo","abbreviation":"CON"},{"id":22,"cowId":94,"name":"Costa Rica","abbreviation":"COS"},{"id":77,"cowId":344,"name":"Croatia","abbreviation":"CRO"},{"id":4,"cowId":40,"name":"Cuba","abbreviation":"CUB"},{"id":83,"cowId":352,"name":"Cyprus","abbreviation":"CYP"},{"id":63,"cowId":315,"name":"Czechoslovakia","abbreviation":"CZE"},{"id":64,"cowId":316,"name":"Czech Republic","abbreviation":"CZR"},{"id":124,"cowId":490,"name":"Democratic Republic of the Congo","abbreviation":"DRC"},{"id":99,"cowId":390,"name":"Denmark","abbreviation":"DEN"},{"id":132,"cowId":522,"name":"Djibouti","abbreviation":"DJI"},{"id":10,"cowId":54,"name":"Dominica","abbreviation":"DMA"},{"id":6,"cowId":42,"name":"Dominican Republic","abbreviation":"DOM"},{"id":203,"cowId":860,"name":"East Timor","abbreviation":"ETM"},{"id":28,"cowId":130,"name":"Ecuador","abbreviation":"ECU"},{"id":158,"cowId":651,"name":"Egypt","abbreviation":"EGY"},{"id":20,"cowId":92,"name":"El Salvador","abbreviation":"SAL"},{"id":104,"cowId":411,"name":"Equatorial Guinea","abbreviation":"EQG"},{"id":134,"cowId":531,"name":"Eritrea","abbreviation":"ERI"},{"id":88,"cowId":366,"name":"Estonia","abbreviation":"EST"},{"id":133,"cowId":530,"name":"Ethiopia","abbreviation":"ETH"},{"id":216,"cowId":987,"name":"Federated States of Micronesia","abbreviation":"FSM"},{"id":211,"cowId":950,"name":"Fiji","abbreviation":"FIJ"},{"id":96,"cowId":375,"name":"Finland","abbreviation":"FIN"},{"id":41,"cowId":220,"name":"France","abbreviation":"FRN"},{"id":120,"cowId":481,"name":"Gabon","abbreviation":"GAB"},{"id":105,"cowId":420,"name":"Gambia","abbreviation":"GAM"},{"id":94,"cowId":372,"name":"Georgia","abbreviation":"GRG"},{"id":52,"cowId":265,"name":"German Democratic Republic","abbreviation":"GDR"},{"id":51,"cowId":260,"name":"German Federal Republic","abbreviation":"GFR"},{"id":50,"cowId":255,"name":"Germany","abbreviation":"GMY"},{"id":116,"cowId":452,"name":"Ghana","abbreviation":"GHA"},{"id":82,"cowId":350,"name":"Greece","abbreviation":"GRC"},{"id":11,"cowId":55,"name":"Grenada","abbreviation":"GRN"},{"id":18,"cowId":90,"name":"Guatemala","abbreviation":"GUA"},{"id":112,"cowId":438,"name":"Guinea","abbreviation":"GUI"},{"id":103,"cowId":404,"name":"Guinea-Bissau","abbreviation":"GNB"},{"id":26,"cowId":110,"name":"Guyana","abbreviation":"GUY"},{"id":5,"cowId":41,"name":"Haiti","abbreviation":"HAI"},{"id":48,"cowId":240,"name":"Hanover","abbreviation":"HAN"},{"id":56,"cowId":273,"name":"Hesse Electoral","abbreviation":"HSE"},{"id":57,"cowId":275,"name":"Hesse Grand Ducal","abbreviation":"HSG"},{"id":19,"cowId":91,"name":"Honduras","abbreviation":"HON"},{"id":62,"cowId":310,"name":"Hungary","abbreviation":"HUN"},{"id":100,"cowId":395,"name":"Iceland","abbreviation":"ICE"},{"id":185,"cowId":750,"name":"India","abbreviation":"IND"},{"id":202,"cowId":850,"name":"Indonesia","abbreviation":"INS"},{"id":155,"cowId":630,"name":"Iran","abbreviation":"IRN"},{"id":157,"cowId":645,"name":"Iraq","abbreviation":"IRQ"},{"id":37,"cowId":205,"name":"Ireland","abbreviation":"IRE"},{"id":162,"cowId":666,"name":"Israel","abbreviation":"ISR"},{"id":66,"cowId":325,"name":"Italy","abbreviation":"ITA"},{"id":111,"cowId":437,"name":"Ivory Coast","abbreviation":"CDI"},{"id":7,"cowId":51,"name":"Jamaica","abbreviation":"JAM"},{"id":184,"cowId":740,"name":"Japan","abbreviation":"JPN"},{"id":161,"cowId":663,"name":"Jordan","abbreviation":"JOR"},{"id":177,"cowId":705,"name":"Kazakhstan","abbreviation":"KZK"},{"id":126,"cowId":501,"name":"Kenya","abbreviation":"KEN"},{"id":209,"cowId":946,"name":"Kiribati","abbreviation":"KIR"},{"id":181,"cowId":730,"name":"Korea","abbreviation":"KOR"},{"id":80,"cowId":347,"name":"Kosovo","abbreviation":"KOS"},{"id":167,"cowId":690,"name":"Kuwait","abbreviation":"KUW"},{"id":175,"cowId":703,"name":"Kyrgyzstan","abbreviation":"KYR"},{"id":195,"cowId":812,"name":"Laos","abbreviation":"LAO"},{"id":89,"cowId":367,"name":"Latvia","abbreviation":"LAT"},{"id":160,"cowId":660,"name":"Lebanon","abbreviation":"LEB"},{"id":142,"cowId":570,"name":"Lesotho","abbreviation":"LES"},{"id":114,"cowId":450,"name":"Liberia","abbreviation":"LBR"},{"id":152,"cowId":620,"name":"Libya","abbreviation":"LIB"},{"id":43,"cowId":223,"name":"Liechtenstein","abbreviation":"LIE"},{"id":90,"cowId":368,"name":"Lithuania","abbreviation":"LIT"},{"id":40,"cowId":212,"name":"Luxembourg","abbreviation":"LUX"},{"id":76,"cowId":343,"name":"Macedonia","abbreviation":"MAC"},{"id":145,"cowId":580,"name":"Madagascar","abbreviation":"MAG"},{"id":139,"cowId":553,"name":"Malawi","abbreviation":"MAW"},{"id":198,"cowId":820,"name":"Malaysia","abbreviation":"MAL"},{"id":191,"cowId":781,"name":"Maldives","abbreviation":"MAD"},{"id":106,"cowId":432,"name":"Mali","abbreviation":"MLI"},{"id":73,"cowId":338,"name":"Malta","abbreviation":"MLT"},{"id":214,"cowId":983,"name":"Marshall Islands","abbreviation":"MSI"},{"id":109,"cowId":435,"name":"Mauritania","abbreviation":"MAA"},{"id":147,"cowId":590,"name":"Mauritius","abbreviation":"MAS"},{"id":58,"cowId":280,"name":"Mecklenburg Schwerin","abbreviation":"MEC"},{"id":16,"cowId":70,"name":"Mexico","abbreviation":"MEX"},{"id":70,"cowId":332,"name":"Modena","abbreviation":"MOD"},{"id":85,"cowId":359,"name":"Moldova","abbreviation":"MLD"},{"id":42,"cowId":221,"name":"Monaco","abbreviation":"MNC"},{"id":179,"cowId":712,"name":"Mongolia","abbreviation":"MON"},{"id":75,"cowId":341,"name":"Montenegro","abbreviation":"MNG"},{"id":149,"cowId":600,"name":"Morocco","abbreviation":"MOR"},{"id":136,"cowId":541,"name":"Mozambique","abbreviation":"MZM"},{"id":189,"cowId":775,"name":"Myanmar","abbreviation":"MYA"},{"id":141,"cowId":565,"name":"Namibia","abbreviation":"NAM"},{"id":213,"cowId":970,"name":"Nauru","abbreviation":"NAU"},{"id":192,"cowId":790,"name":"Nepal","abbreviation":"NEP"},{"id":38,"cowId":210,"name":"Netherlands","abbreviation":"NTH"},{"id":206,"cowId":920,"name":"New Zealand","abbreviation":"NEW"},{"id":21,"cowId":93,"name":"Nicaragua","abbreviation":"NIC"},{"id":110,"cowId":436,"name":"Niger","abbreviation":"NIR"},{"id":119,"cowId":475,"name":"Nigeria","abbreviation":"NIG"},{"id":182,"cowId":731,"name":"North Korea","abbreviation":"PRK"},{"id":98,"cowId":385,"name":"Norway","abbreviation":"NOR"},{"id":171,"cowId":698,"name":"Oman","abbreviation":"OMA"},{"id":187,"cowId":770,"name":"Pakistan","abbreviation":"PAK"},{"id":215,"cowId":986,"name":"Palau","abbreviation":"PAL"},{"id":23,"cowId":95,"name":"Panama","abbreviation":"PAN"},{"id":67,"cowId":327,"name":"Papal States","abbreviation":"PAP"},{"id":205,"cowId":910,"name":"Papua New Guinea","abbreviation":"PNG"},{"id":32,"cowId":150,"name":"Paraguay","abbreviation":"PAR"},{"id":71,"cowId":335,"name":"Parma","abbreviation":"PMA"},{"id":29,"cowId":135,"name":"Peru","abbreviation":"PER"},{"id":201,"cowId":840,"name":"Philippines","abbreviation":"PHI"},{"id":59,"cowId":290,"name":"Poland","abbreviation":"POL"},{"id":47,"cowId":235,"name":"Portugal","abbreviation":"POR"},{"id":169,"cowId":694,"name":"Qatar","abbreviation":"QAT"},{"id":197,"cowId":817,"name":"Republic of Vietnam","abbreviation":"RVN"},{"id":86,"cowId":360,"name":"Romania","abbreviation":"ROM"},{"id":87,"cowId":365,"name":"Russia","abbreviation":"RUS"},{"id":130,"cowId":517,"name":"Rwanda","abbreviation":"RWA"},{"id":217,"cowId":990,"name":"Samoa","abbreviation":"WSM"},{"id":69,"cowId":331,"name":"San Marino","abbreviation":"SNM"},{"id":102,"cowId":403,"name":"Sao Tome and Principe","abbreviation":"STP"},{"id":163,"cowId":670,"name":"Saudi Arabia","abbreviation":"SAU"},{"id":54,"cowId":269,"name":"Saxony","abbreviation":"SAX"},{"id":107,"cowId":433,"name":"Senegal","abbreviation":"SEN"},{"id":148,"cowId":591,"name":"Seychelles","abbreviation":"SEY"},{"id":115,"cowId":451,"name":"Sierra Leone","abbreviation":"SIE"},{"id":199,"cowId":830,"name":"Singapore","abbreviation":"SIN"},{"id":65,"cowId":317,"name":"Slovakia","abbreviation":"SLO"},{"id":81,"cowId":349,"name":"Slovenia","abbreviation":"SLV"},{"id":208,"cowId":940,"name":"Solomon Islands","abbreviation":"SOL"},{"id":131,"cowId":520,"name":"Somalia","abbreviation":"SOM"},{"id":140,"cowId":560,"name":"South Africa","abbreviation":"SAF"},{"id":183,"cowId":732,"name":"South Korea","abbreviation":"ROK"},{"id":154,"cowId":626,"name":"South Sudan","abbreviation":"SSD"},{"id":45,"cowId":230,"name":"Spain","abbreviation":"SPN"},{"id":190,"cowId":780,"name":"Sri Lanka","abbreviation":"SRI"},{"id":15,"cowId":60,"name":"St. Kitts and Nevis","abbreviation":"SKN"},{"id":12,"cowId":56,"name":"St. Lucia","abbreviation":"SLU"},{"id":13,"cowId":57,"name":"St. Vincent and the Grenadines","abbreviation":"SVG"},{"id":153,"cowId":625,"name":"Sudan","abbreviation":"SUD"},{"id":27,"cowId":115,"name":"Suriname","abbreviation":"SUR"},{"id":144,"cowId":572,"name":"Swaziland","abbreviation":"SWA"},{"id":97,"cowId":380,"name":"Sweden","abbreviation":"SWD"},{"id":44,"cowId":225,"name":"Switzerland","abbreviation":"SWZ"},{"id":159,"cowId":652,"name":"Syria","abbreviation":"SYR"},{"id":180,"cowId":713,"name":"Taiwan","abbreviation":"TAW"},{"id":174,"cowId":702,"name":"Tajikistan","abbreviation":"TAJ"},{"id":127,"cowId":510,"name":"Tanzania","abbreviation":"TAZ"},{"id":193,"cowId":800,"name":"Thailand","abbreviation":"THI"},{"id":117,"cowId":461,"name":"Togo","abbreviation":"TOG"},{"id":212,"cowId":955,"name":"Tonga","abbreviation":"TON"},{"id":8,"cowId":52,"name":"Trinidad and Tobago","abbreviation":"TRI"},{"id":151,"cowId":616,"name":"Tunisia","abbreviation":"TUN"},{"id":156,"cowId":640,"name":"Turkey","abbreviation":"TUR"},{"id":173,"cowId":701,"name":"Turkmenistan","abbreviation":"TKM"},{"id":72,"cowId":337,"name":"Tuscany","abbreviation":"TUS"},{"id":210,"cowId":947,"name":"Tuvalu","abbreviation":"TUV"},{"id":68,"cowId":329,"name":"Two Sicilies","abbreviation":"SIC"},{"id":125,"cowId":500,"name":"Uganda","abbreviation":"UGA"},{"id":91,"cowId":369,"name":"Ukraine","abbreviation":"UKR"},{"id":170,"cowId":696,"name":"United Arab Emirates","abbreviation":"UAE"},{"id":36,"cowId":200,"name":"United Kingdom","abbreviation":"UKG"},{"id":1,"cowId":2,"name":"United States of America","abbreviation":"USA"},{"id":35,"cowId":165,"name":"Uruguay","abbreviation":"URU"},{"id":176,"cowId":704,"name":"Uzbekistan","abbreviation":"UZB"},{"id":207,"cowId":935,"name":"Vanuatu","abbreviation":"VAN"},{"id":25,"cowId":101,"name":"Venezuela","abbreviation":"VEN"},{"id":196,"cowId":816,"name":"Vietnam","abbreviation":"DRV"},{"id":55,"cowId":271,"name":"Wuerttemburg","abbreviation":"WRT"},{"id":165,"cowId":679,"name":"Yemen","abbreviation":"YEM"},{"id":164,"cowId":678,"name":"Yemen Arab Republic","abbreviation":"YAR"},{"id":166,"cowId":680,"name":"Yemen People's Republic","abbreviation":"YPR"},{"id":78,"cowId":345,"name":"Yugoslavia","abbreviation":"YUG"},{"id":137,"cowId":551,"name":"Zambia","abbreviation":"ZAM"},{"id":128,"cowId":511,"name":"Zanzibar","abbreviation":"ZAN"},{"id":138,"cowId":552,"name":"Zimbabwe","abbreviation":"ZIM"}];
    },

    /*
        return the years from this year back to 1992 - TODO: abstract this to an API call
    */
    getYears : function() {
        var years = [];
        for(var i = Date.today().getFullYear(); i > 1992; i--) years.push(i);
        return years;
    },

    /*
        return the list of vessel statuses - TODO: abstract this to an API call
    */
    getVesselStatus : function() {
      return ["Anchored", "Berthed", "Moored", "Stationary", "Steaming", "Unspecified"];
    }

  };

  return mapData;

}]);
