/**
 * 
 */

function pictureRowProcess(data, type, full, meta) {
	return '<a class="ui-button ui-icon ui-icon-minus" href="javascript:void(0)" onclick="removePicture(this);" title="Remove this picture"></a>';
}

function removePicture(picture) {
	var pt = $("#pictureTable"),
		tr = $(picture).parents("tr");
	
	console.log(tr);
	
	pt.DataTable()
		.row(tr)
		.remove()
		.draw();
}

function addPicture() {
	var pt = $("#pictureTable"),
		ptrows = pt.DataTable().rows(function(i,d) {
			if (!d.path.startsWith("<input "))
				return false;
			
			if ($("#"+$(d.path).attr("id")).val().length > 0)
				return false;
			
			return true;
		});
	
	if (ptrows[0].length > 0)
		return true;
	
	pt.DataTable()
		.row
			.add({path: '<input id="pic_'+pt.DataTable().rows()[0].length+'" type="file" accept="image/*" formenctype="multipart/form-data" class="ui-widget ui-button"></input>',
			      name: ''
				})
			.draw();
	return true;
}

function destroyPictures() {
	console.log("clearing & destroying...");
	var pt = $("#pictureTable");
	pt.DataTable().destroy();
	productDialog.dialog("option", "product", []);
}

function getPictures(event, ui) {
	console.log("building picture table...");
	var product = productDialog.dialog("option","product"),
		tdPictures = $("#pictures");
	
	if (product === null || product === undefined)
		product = [];
	
	console.log(tdPictures.html());
	
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
	
	console.log(tdPictures.html());
	
	$("#pictureTable").DataTable({
		jQueryUI: true,
		data: product,
		columns: [
			{data: null, render: function(r){return r.path+r.name;} },
			{data: null, render: pictureRowProcess, "className": "dt-body-center", "orderable": false, "searchable": false}
		]
	});

	
}




		