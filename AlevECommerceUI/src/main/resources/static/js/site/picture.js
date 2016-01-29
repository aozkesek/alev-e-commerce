/**
 * 
 */

function pictureRowProcess(data, type, full, meta) {
	return '<a class="ui-button ui-icon ui-icon-minus" href="javascript:void(0)" onclick="removePicture(this);" title="Remove this picture"></a>';
}

function removePicture(picture) {
	var tr = $(picture).parents("tr");
	pictureTable
		.row(tr)
		.remove()
		.draw();
}

function addPicture() {
	
	if (hasEmptyFileRow())
		return true;
	
	pictureTable
		.row
			.add({path: '<input name="rawpictures" type="file" accept="image/*" formenctype="multipart/form-data" class="ui-widget ui-button" />',
			      name: '', id: ''
				})
			.draw();
	
	return true;
}

function destroyPictures() {
	pictureTable.destroy();
	tinymce.activeEditor.remove();
	productDialog.dialog("option", "product", []);
}

function getPictures(event, ui) {
	var product = productDialog.dialog("option","product"),
		tdPictures = $("#pictures");
	
	tinymce.init({
		selector: "textarea#description",
		menubar: false	
	});
	
	if (product === null || product === undefined)
		product = [];
	
	tdPictures.append(
	'<table id="pictureTable" class="display compact"> \
		<thead> \
		<tr> \
			<th>Pictures</th> \
			<th> <a class="ui-button ui-icon ui-icon-plus" href="javascript:void(0)" onclick="addPicture();" title="Add new picture"></a> </th> \
		</tr> \
		</thead> \
		<tbody> \
		</tbody> \
	</table>');
	
	pictureTable = $("#pictureTable").DataTable({
		jQueryUI: true,
		data: product.pictures,
		columns: [
			{data: null, render: function(r){return (r.path.startsWith('<input')) ? r.path : r.path+r.name+'<input type="hidden" name="pictureIds" value="'+r.id+'"/><input type="hidden" name="pictureNames" value="'+r.name+'"/>';} },
			{data: null, render: pictureRowProcess, "className": "dt-body-center", "orderable": false, "searchable": false}
		]
	});

	
}




		