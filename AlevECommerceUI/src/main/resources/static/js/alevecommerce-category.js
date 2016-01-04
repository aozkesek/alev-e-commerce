/**
 * 
 */

var categoryList = $("#categoryList");
var selectedCategory = null;

function categoryContent(category) {
	categoryList.puidropdown("addOption", {value: ""+category.id, label: category.categoryName}); 
}

function categoryAddUpdate(isUpdate, id, name) {
	$.ajax({
		type: "POST"
		, url: "/administration/category/" + (isUpdate ? "update" : "create")
		, data: '{"versionNumber": "1.0.0", "model": [{"categoryName": "' + name + (isUpdate ? ('", "id": ' + id) : '"') + '}]}'
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

$("#categoryUpdate, #categoryAdd").puibutton({
	click: function(event) {
		console.log(categoryList);
		var newLabel = $(categoryList[0].parentElement.parentElement).children("input").val();
		var selLabel = $(categoryList).puidropdown("getSelectedLabel");
		
		if (newLabel === selLabel)
			return;
			
		var selValue = $(categoryList).puidropdown("getSelectedValue");
		
		if (event.target.id == "categoryAdd") {
			categoryAddUpdate(false, null, newLabel);
		} else {
			categoryAddUpdate(true, selValue, newLabel);
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

