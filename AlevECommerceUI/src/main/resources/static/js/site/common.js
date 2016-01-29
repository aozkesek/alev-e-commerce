/**
 * 
 */

	var token = $("meta[name='_csrf']").attr("content"),
		header = $("meta[name='_csrf_header']").attr("content");
	
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	


	/*
	 * settigns: 
	 * 		url			: 
	 * 		type		: get/post
	 * 		dataModel	: json string
	 * 		firstRecord	: default 0
	 * 		maxRecord	: default 10
	 * 		success		: callback function() on successful finish
	 * 		error		: callback function() on error halt
	 * 		title		:
	 *  
	 */
	function adminAjaxCall(settings) {
		
		if ((settings === undefined) || (settings === null) || ((typeof settings) !== "object"))
			return;
		
		var dataj = '{"versionNumber": "1.0.0", "firstRecordNumber": ' + (settings.firstRecord === undefined ? 0 : settings.firstRecord)
					+ ', "maxRecordNumber": ' + (settings.maxRecord === undefined ? 10 : settings.maxRecord)
					+ ', "model": ' + settings.dataModel + '}';

		$.ajax({
			type: settings.type,
			url: "/administration" + settings.url,
			data: dataj,
			dataType: "json",
			contentType: "application/json",
			success: function(response) {
				if (response.responseCode == 0) {
					growlMessages.puigrowl("show", [{severity: "info", summary: settings.title, detail: "Done."}]);
					try{settings.success(response);}catch(e){console.log(e);}
				} else {
					growlMessages.puigrowl("show", [{severity: "error", summary: settings.title, detail: "Failed."}]);
					try{settings.error(response);}catch(e){console.log(e);}
				}
			},
			error: function(e) {
				console.log(e);
				growlMessages.puigrowl("show", [{severity: "error", summary: settings.title, detail: "Failed."}]);
				try{settings.error(response);}catch(e){console.log(e);}
			}
		});
		
	}
