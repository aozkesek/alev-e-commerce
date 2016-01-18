/**
 * 
 */
var adminTaskTab = $("#adminTaskTab");

function onCategoryButtonsClick(event) {
	var categoryList = $("#categoryList"),
		id = categoryList.val(),
		name = categoryList.children().filter("[value="+id+"]").text(),
		confirmDialog = $("#confirmDialog"),
		addupdateDialog = $("#addupdateDialog"),
		selectedCategory = $("#selectedCategory"),
		buttons;
	
	switch(event.currentTarget.id) {
	case "categoryUpdate":
		selectedCategory.val(name);
		buttons = addupdateDialog.dialog("option", "buttons");
		buttons.filter(function(b){return b.text==="Ok"})[0].click = function() {categoryCrud("update",id,selectedCategory.val());};
		addupdateDialog.dialog("option","buttons",buttons);
		addupdateDialog.dialog("open");
		break;
		
	case "categoryAdd":
		selectedCategory.val("");
		buttons = addupdateDialog.dialog("option", "buttons");
		buttons.filter(function(b){return b.text==="Ok"})[0].click = function() {
			categoryCrud("create",null,selectedCategory.val(), function(){
				selectedCategory.val("");
				});
			};
		addupdateDialog.dialog("option","buttons",buttons);
		addupdateDialog.dialog("open");
		break;
		
	case "categoryDelete":
		confirmDialog.children("label").text(name + " will be deleted,");
		buttons = confirmDialog.dialog("option", "buttons");
		buttons.filter(function(b){return b.text==="Yes"})[0].click = function() {
			categoryCrud("delete",id,null, function(){
				confirmDialog.dialog("close");
				adminTaskTab.tabs("load", adminTaskTab.tabs("option", "active"));
				});
			};
		confirmDialog.dialog("option","buttons",buttons);
		confirmDialog.dialog("open");
		break;
	}
	
}

function categoryCrud(oper, id, name, fns, fne) {
	var isFns = fns !== undefined && fns !== null,
		idFne = fne !== undefined && fne !== null,
		growlMessages = $("#growlmessages");
	
	$.ajax({
		type: "POST"
		, url: "/administration/category/" + oper
		, data: '{"versionNumber": "1.0.0", "model": [{"categoryName": "' + name + '", "id": ' + id + '}]}'
		, dataType: "json"
		, contentType: "application/json"
		, success: function(response) {
			if (response.responseCode == 0) {
				growlMessages.puigrowl("show", [{severity: "info", summary: oper, detail: "Done."}]);
				try{isFns ? fns() : null;}catch(e){console.log(e);}
			} else {
				growlMessages.puigrowl("show", [{severity: "error", summary: oper, detail: "Failed."}]);
				try{isFne ? fne() : null;}catch(e){console.log(e);}
			}	
		}
	});
	
} 

function dialogClose(dialog) {
	$(dialog).dialog("close");
	//do not call every time to pretend network load, try to sense if there is change
	adminTaskTab.tabs("load", adminTaskTab.tabs("option", "active"));
	
}
