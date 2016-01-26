/**
 * 
 */

function productRowProcess(data, type, full, meta) {
	return '<a class="ui-button ui-icon ui-icon-pencil" href="javascript:void(0)" onclick="editProduct('+data.id+');" title="Update this product"></a>' +
		'<a class="ui-button ui-icon ui-icon-minus" href="javascript:void(0)" onclick="deleteProduct('+data.id+');" title="Delete this product"></a>';
}

function editProduct(id) {
	var product = productTable.data().filter(function(product){return product.id===id;})[0];
	$("#productId").val(product.id);
	$("#name").val(product.name);
	$("#title").val(product.title);
	$("#price").val(product.price);
	$("#actualPrice").val(product.actualPrice);
	$("#description").text(product.description);
	productDialog.dialog("option", "product", product);
	productDialog.dialog("open");
	return true;
}

function deleteProduct(id) {
	var product = productTable.data().filter(function(product){return product.id===id;})[0];
	confirmDialog.dialog("option", "title", "Product - Delete");
	confirmDialog.children("label").text("[" + product.name + "] " + product.title + " will be deleted,");
	buttons = confirmDialog.dialog("option", "buttons");
	buttons.filter(function(b){return b.text==="Yes"})[0].click = function() {
		adminAjaxCall({
			type: "POST",
			url: "/product/delete",
			dataModel: '[{"id": ' + id + '}]',
			success: function(){
				confirmDialog.dialog("close");
				adminTaskTabs.tabs("load", adminTaskTabs.tabs("option", "active"));
				},
			error: function(){
				confirmDialog.dialog("close");
				}
		});
		};
	confirmDialog.dialog("option","buttons",buttons);
	confirmDialog.dialog("open");
	return true;
}

function addProduct() {
	$("#name,#title,#price,#actualPrice,#productId").val("");
	$("#description").text("");
	productDialog.dialog("open");
	return true;
}

function dialogClose(dialog) {
	$(dialog).dialog("close");
	//do not call this every time to pretend network load, try to sense if there is change
	//or move this into the only button event that made change something
	adminTaskTabs.tabs("load", adminTaskTabs.tabs("option", "active"));
	
}

function getProducts(data, callback, settings) {
	var first = settings._iDisplayStart,
		max = settings._iDisplayLength;
	
	$.ajax({
		type: "POST"
		, url: "/product/list"
		, data: '{"versionNumber":"1.0.0","firstRecordNumber":' + first + ',"maxRecordNumber":' + max + '}'
		, dataType: "json"
		, contentType: "application/json"
		, success: function(response) {
			if (response.responseCode > -1)
				if (response.model != null && response.model.length > 0) 
					callback({data: response.model});
		}
	});
	
}

function hasEmptyFileRow() {
	var emptyrows = $("input[name=rawpictures]").filter(function(i,o){return $(o).val().length === 0;});

	if (emptyrows.length > 0)
		return true;
	
	return false;
}

function validateProductForm() {
	if (hasEmptyFileRow()) {
		growlMessages.puigrowl("show", [{severity: "warn", summary: "Form validation", detail: "You can not submit unselected or empty picture file."}]);
		return false;
	}
	
	
	return true;
}

/*
 * cleanup previous remained div into the body
 */
function detachProductDialog() {
	var ariaProductDialog = $("div[aria-describedby=productDialog]");
	if (ariaProductDialog.length>0)
		ariaProductDialog.detach();	
}


		