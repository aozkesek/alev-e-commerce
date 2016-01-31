/**
 * 
 */


function getPicturesTemplate(pictures) {
	var picTemplate = '<div>';
	pictures.forEach(
			function(current,index,array) {
				picTemplate += '<img src="' + current.path + current.name + '"></img>';
				});
	return picTemplate;
}

function productRowProcess(data, type, full, meta) {
	var prodTemplate = 
			'<div class="ui-widget ui-widget-header">'+data.title+'</div>' +
			'<div class="ui-widget ui-widget-content">' +
			'<div class="ui-widget ui-site-left">' +
				'<div style="display:inline-block;">Name: ' + data.name + '</div>' +
				'<div style="display:inline-block;">Price : ' + data.price + '</div>' +
				'<div style="display:inline-block;">Now: ' + data.actualPrice + '</div>' +
			'</div>' +
			'<div class="ui-widget ui-site-center">' +
				'<div style="display:inline-block;">Description : ' + data.description + '</div>' +
			'</div>' +
			'<div class="ui-widget ui-site-right">' +
				'<div style="display:inline-block;">' +
				getPicturesTemplate(data.pictures) +
				'</div>' +
			'</div>' +
			'</div>';
	
	return prodTemplate;
}

function getProducts(data, callback, settings) {
	var categoryId = $(this).attr("categoryId"),
		jData = '{"firstRecordNumber":' + settings._iDisplayStart + 
			',"maxRecordNumber":' + settings._iDisplayLength + 
			',"model":[{"category":{"id":'+categoryId+'}}]}';
	
	$.ajax({
		type: "POST", 
		url: "/product/list", 
		data: jData, 
		dataType: "json", 
		contentType: "application/json", 
		success: function(response) {
			if (response.responseCode > -1)
				if (response.model != null && response.model.length > 0) 
					callback({data: response.model});
		}
	});
	
}