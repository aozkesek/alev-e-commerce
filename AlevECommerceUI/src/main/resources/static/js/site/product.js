/**
 * 
 */
var adminTaskTab = $("#adminTaskTab"),
	productDialog = $("#productDialog"),
	productList = $("#productList");

function productDialog_Save() {
	productDialog.puidialog("hide");
}

function productDialog_Delete() {
	productDialog.puidialog("hide");
}

function onProductTableRowSelect(event, row) {
	$("#name").val(row.name);
	$("#title").val(row.title);
	$("#description").val(row.description);
	$("#price").val(row.price);
	$("#actualPrice").val(row.actualPrice);
	
	$("#productDialog").dialog("open");
}

function productRowProcess(data, type, full, meta) {
	console.log(data);
	return '<a href="#"><img src="/images/pencil.png"></img></a><a href="#"><img src="/images/minus.png"></img></a>';
}

function productListInit(f) {
	$.ajax({
		type: "POST"
		, url: "/product/listTotalRecord"
		, data: '{"versionNumber":"1.0.0","firstRecordNumber":0,"maxRecordNumber":0}'
		, dataType: "json"
		, contentType: "application/json"
		, success: function(response) {
			if (response.responseCode == 0 && response.totalRecordNumber > 0)
				try { f(response.totalRecordNumber); } catch(e) { console.log(e); }
		}	
	});
}

function productListPage(o, i, m, f) {
	$.ajax({
		type: "POST"
		, url: "/product/list"
		, data: '{"versionNumber":"1.0.0","firstRecordNumber":' + i + ',"maxRecordNumber":' + m + '}'
		, dataType: "json"
		, contentType: "application/json"
		, context: o
		, success: function(response) {
			if (response.responseCode > -1)
				if (response.model != null && response.model.length > 0) 
					try { f.call(o, response.model); } catch(e) { console.log(e); }
		}
	});
	
}

function dialogClose(dialog) {
	$(dialog).dialog("close");
	//do not call this every time to pretend network load, try to sense if there is change
	//adminTaskTab.tabs("load", adminTaskTab.tabs("option", "active"));
	
}

function getProducts(data, callback, settings) {
	
	$.ajax({
		type: "POST"
		, url: "/product/list"
		, data: '{"versionNumber":"1.0.0","firstRecordNumber":' + settings._iDisplayStart + ',"maxRecordNumber":' + settings._iDisplayLength + '}'
		, dataType: "json"
		, contentType: "application/json"
		, success: function(response) {
			if (response.responseCode > -1)
				if (response.model != null && response.model.length > 0) 
					callback({data: response.model});
		}
	});
	
}




		