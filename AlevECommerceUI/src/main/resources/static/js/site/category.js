/**
 * 
 */

var categoryList = $("#categoryList");
var selectedCategory = null;
var confirmDialogHandle = $("#confirmDialog");
var growlMessages = $("#growlmessages");
	
function categoryDialog(message, confirmCallback) {
	
	$(confirmDialogHandle).children(".pui-dialog-content").children("label").html(message);
	confirmDialogHandle.puidialog("option", "confirmCallback", confirmCallback);
	confirmDialogHandle.puidialog("show");

	
}

function categoryContent(category) {
	$("#categoryList").puidropdown("addOption", {value: category.id, label: category.categoryName}); 
}

function categoryCrud(oper, id, name) {
	
	$.ajax({
		type: "POST"
		, url: "/administration/category/" + oper
		, data: '{"versionNumber": "1.0.0", "model": [{"categoryName": "' + name + '", "id": ' + id + '}]}'
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

