/**
 * 
 */

var categoryList = $("#categoryList");
var selectedCategory = null;

function categoryContent(category) {
	categoryList.puidropdown("addOption", {value: ""+category.id, label: category.categoryName}); 
}

function categoryCrud(oper, id, name) {
	var isUpdate = oper === "Upd";
	var isDelete = oper === "Del";
	
	$.ajax({
		type: "POST"
		, url: "/administration/category/" + (isUpdate ? "update" : isDelete ? "delete" : "create")
		, data: '{"versionNumber": "1.0.0", "model": [{"categoryName": "' + name + (isUpdate || isDelete ? ('", "id": ' + id) : '"') + '}]}'
		, dataType: "json"
		, contentType: "application/json"
		, success: function(response) {
			if (response.responseCode == 0) {
				categoryList.puidropdown("removeAllOptions");
				categoryListInit(categoryContent);
			}
				
		}
	});
} 

$("#categoryUpdate, #categoryAdd, #categoryDelete").puibutton({
	click: function(event) {
		console.log(categoryList);
		var newLabel = $(categoryList[0].parentElement.parentElement).children("input").val();
		var selLabel = $(categoryList).puidropdown("getSelectedLabel");
		var buttonId = event.target.id;
		
		if ((buttonId != "categoryDelete") && (newLabel === selLabel))
			return;
			
		var selValue = $(categoryList).puidropdown("getSelectedValue");
		
		if (buttonId == "categoryAdd") {
			categoryCrud('Add', null, newLabel);
		} else if (buttonId == "categoryUpdate") {
			categoryCrud('Upd', selValue, newLabel);
		} else if (buttonId == "categoryDelete") {
			categoryCrud('Del', selValue, null);
		}
		
	}
});

categoryList.puidropdown({
	data: null
	, filter: true
	, editable: true
	, change: function(event) {
		
	}
});

categoryListInit(categoryContent);

