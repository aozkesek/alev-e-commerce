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
	
	console.log(pt.DataTable().rows()[0]);
	
	pt.DataTable()
		.row
			.add({path: '<input id="pic_'+pt.DataTable().rows()[0].length+'" type="file" accept="image/*" formenctype="multipart/form-data" class="ui-widget ui-button"></input>',
			      name: ''
				})
			.draw();
	return true;
}

function destroyPictures() {
	var pt = $("#pictureTable");
	pt.DataTable().destroy();
	pt.children("tbody").html("");
	productDialog.dialog("option", "product", []);
}

function getPictures(event, ui) {
	
	var product = productDialog.dialog("option","product");

	if (product === null || product === undefined)
		product = [];
	
	$("#pictureTable").DataTable({
		jQueryUI: true,
		data: product.pictures,
		columns: [
			{data: null, render: function(r){return r.path+r.name;} },
			{data: null, render: pictureRowProcess, "className": "dt-body-center", "orderable": false, "searchable": false}
		]
	});
	
}




		