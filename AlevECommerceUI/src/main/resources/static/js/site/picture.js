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


function getPictures(event, ui) {
	
	console.log(ui);
	
	$("#pictureTable").DataTable({
		jQueryUI: true,
		columns: [
			{data: null, render: '<input type="file" class="ui-widget ui-button"></input>'},
			{data: null, render: pictureRowProcess, "className": "dt-body-center", "orderable": false, "searchable": false}
		]
	});
	
}




		