/**
 * 
 */

function pictureRowProcess(data, type, full, meta) {
	return '<a class="ui-button ui-icon ui-icon-minus" href="javascript:void(0)" onclick="removePicture(this);" title="Remove this picture"></a>';
}

function removePicture(picture) {
	return true;
}

function addPicture() {
	return true;
}

function destroyPictures() {
	$("#pictureTable").DataTable().destroy();
}

function getPictures(event, ui) {
	
	var product = productDialog.dialog("option","product");
	
	$("#pictureTable").DataTable({
		jQueryUI: true,
		data: product.pictures,
		columns: [
			//{data: null, render: function(r){return '<input type="file" accept="image/*" formenctype="multipart/form-data" class="ui-widget ui-button"">'+r.name+'</input>';} },
			{data: null, render: function(r){return r.path+r.name;} },
			{data: null, render: pictureRowProcess, "className": "dt-body-center", "orderable": false, "searchable": false}
		]
	});
	
}




		