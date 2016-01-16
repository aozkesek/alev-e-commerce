/**
 * 
 */

function productDataSource(caller, callback, requestData) {

	$.ajax("/product/list", {
		type: "POST",
		contentType: "application/json",
		context: caller,
		dataType: "json",
		data: requestData,
		success: function(response) {
			if (response.responseCode === 0 && response.model.length)
				callback.call(caller, response.model);
		}
		
	});
}
		