/**
 * 
 */

	
function onCategoryButtonsClick(event) {
	var categoryList = $("#categoryList");
	var confirmDialog = $("#confirmDialog");
	var addupdateDialog = $("#addupdateDialog");
	var selectedCategory = $("#selectedCategory");
	var growlMessages = $("#growlmessages");
	var buttonId = event.currentTarget.id;
	var categoryId = categoryList.val();
	var categoryName = categoryList.children().filter("[value="+categoryId+"]").text();
	
	console.log(buttonId);
	
	switch(buttonId) {
	case "categoryUpdate":
		selectedCategory.val(categoryName);
		addupdateDialog.dialog("open");
		
		break;
	case "categoryAdd":
		selectedCategory.val("");
		addupdateDialog.dialog("open");
		break;
		
	case "categoryDelete":
		confirmDialog.children("label").text(categoryName + " will be deleted,");
		confirmDialog.dialog("open");
		break;
	}
	
}

function categoryCrud(oper, id, name) {
	console.log(oper+">"+id+">"+name);
	$.ajax({
		type: "POST"
		, url: "/administration/category/" + oper
		, data: '{"versionNumber": "1.0.0", "model": [{"categoryName": "' + name + '", "id": ' + id + '}]}'
		, dataType: "json"
		, contentType: "application/json"
		, success: function(response) {
			if (response.responseCode == 0) {

			}
				
		}
	});
	
} 

