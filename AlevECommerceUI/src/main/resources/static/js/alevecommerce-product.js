/**
 * 
 */
var productDialog = $("#productDialog");
var productList = $("#productList");

function productDialog_Save() {
	productDialog.puidialog("hide");
	
}

function productDialog_Delete() {
	productDialog.puidialog("hide");
	
}

productListInit(function(m){

	productList.puidatatable({
		lazy: true
		, caption: "Product List"
		, selectionMode: "single"
		, paginator: { rows: 5, totalRecords: m }
		, rowSelect: function(event, data) {
			
			$("#name").val(data.name);
			$("#title").val(data.title);
			$("#description").val(data.description);
			$("#price").val(data.price);
			$("#actualPrice").val(data.actualPrice);
			
			$("#productDialog").puidialog("show");
		}
		, columns: [
		   {field: "categoryName", headerText: "Category", content: function(p) { return p.category.categoryName; }}
		   ,{field: "name", headerText: "Name" }
		   ,{field: "title", headerText: "Title" }
		   ,{field: "rowupdate", headerText: "Update", content: function(p) { return '<a href="#'+p.id+'">'+p.name+'</a>'; } }
		   ,{field: "rowdelete", headerText: "Delete", content: function(p) { return '<a href="#'+p.id+'">'+p.name+'</a>'; } }
		]
		, datasource: function(callback, ui) {
			productListPage(this, ui.first, 5, callback);
		}
	});
	
});


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




		