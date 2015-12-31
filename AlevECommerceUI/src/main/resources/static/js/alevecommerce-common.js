/**
 * 
 */

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	
	var mainMenuBar = $('#mainMenuBar').puimenubar();
	
	function categoryListInit(f) {
		$.ajax({
			type: "GET"
			, url: "/category/list"
			, contentType: "application/json"
			, success: function(response) {
				if (response.responseCode == 0) 
					if (response.model != null) { 
						try {
							response.model.forEach(function(item, index) {
								f(item);
							});
						} catch(e) {console.log(e);}
					}
			}	
		});
	}
