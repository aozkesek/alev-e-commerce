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
	var ptrows = pictureTable.rows(function(i,d) {
			if (!d.path.startsWith("<input "))
				return false;
			
			if ($("#"+$(d.path).attr("id")).val().length > 0)
				return false;
			
			return true;
		});
	
	if (ptrows[0].length > 0)
		return true;
	
	pictureTable
		.row
			.add({path: '<input id="pic_'+pictureTable.rows()[0].length+'" type="file" accept="image/*" formenctype="multipart/form-data" class="ui-widget ui-button"></input>',
			      name: ''
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
			{data: null, render: function(r){return r.path+r.name;} },
			{data: null, render: pictureRowProcess, "className": "dt-body-center", "orderable": false, "searchable": false}
		]
	});

	
}




		