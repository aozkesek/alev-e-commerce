/**
 * 
 */
var productDialog = $("#productDialog");

function productDialog_Save() {
	productDialog.puidialog("hide");
	
}

function productDialog_Delete() {
	productDialog.puidialog("hide");
	
}

function productListInit() {
	$.ajax({
		type: "POST"
		, url: "/product/listTotalRecord"
		, data: '{"versionNumber":"1.0.0","firstRecordNumber":0,"maxRecordNumber":0}'
		, dataType: "json"
		, contentType: "application/json"
		, success: function(response) {
			if (response.responseCode == 0 && response.totalRecordNumber > 0)
				productList(response.totalRecordNumber);
		}	
	});
}

function productList(listTotalRecord) {

	$("#productList").puidatatable({
		lazy: true
		, caption: "Product List"
		, selectionMode: "single"
		, paginator: { rows: 5, totalRecords: listTotalRecord }
		, rowSelect: function(event, data) {
			
			$("#name").attr("value", data.name);
			$("#title").attr("value", data.title);
			$("#description").attr("value", data.description);
			$("#price").attr("value", data.price);
			$("#actualPrice").attr("value", data.actualPrice);
			
			$("#productDialog").puidialog("show");
		}
		, columns: [ 
		   {
			   field: "categoryName"
			   , headerText: "Category"
			   , content: function(model) {
					return model.category.categoryName;
				}
		   }
			,{field: "name", headerText: "Name" }
			,{field: "title", headerText: "Title" }
			
		]
		, datasource: function(callback, ui) {
			
			$.ajax({
				type: "POST"
				, url: "/product/list"
				, data: '{"versionNumber":"1.0.0","firstRecordNumber":' + ui.first + ',"maxRecordNumber":15}'
				, dataType: "json"
				, contentType: "application/json"
				, context: this
				, success: function(response) {
					if (response.responseCode > -1)
						if (response.model != null && response.model.length > 0) {
							callback.call(this, response.model);
						}
				}
			});
			
		}
	});
	
}

$("#name, #title, #price, #actualPrice").puiinputtext();
$("#sizes, #colors").puilistbox({scrollHeight: 100});
$("#description").puiinputtextarea();

$("#productDialog").puidialog({
	modal: true
	, responsive: true
	, width: 600
	, buttons: [
	  { text: "Save", click: function() { productDialog_Save(); }}
	  , { text: "Delete", click: function() { productDialog_Delete(); }}
	  , { text: "Cancel", click: function() { $("#productDialog").puidialog("hide"); }}
	  ]
});

productListInit();



		