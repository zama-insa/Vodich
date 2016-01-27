/**
 * 
 */
$(document).ready(function() {              
	$('#btnSubmitFrench').click(function(event) {   	
		location.href="changelangage?language=fr&redirectURL=" + window.location.href
	});
	$('#btnSubmitEnglish').click(function(event) { 
		location.href="changelangage?language=en&redirectURL=" + window.location.href
	});
});


var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};

$(document).ready(function() {              
    $('#btnExport').click(function(event) { 
    	var filePath=$('#file').val(); 
        });
    });
