/**
 * 
 */

function onCategoryButtonsClick(event) {
	var categoryList = $("#categoryList"),
		id = categoryList.val(),
		name = categoryList.children().filter("[value="+id+"]").text(),
		selectedCategory = $("#selectedCategory"),
		buttons;
	
	switch(event.currentTarget.id) {
	case "categoryUpdate":
		selectedCategory.val(name);
		buttons = categoryDialog.dialog("option", "buttons");
		buttons.filter(function(b){return b.text==="Ok"})[0].click = function() {
			adminAjaxCall({
				type: "POST",
				url: "/category/update",
				dataModel: '[{"categoryName": "'+selectedCategory.val()+'", "id":'+id+'}]'
			});
		};
		categoryDialog.dialog("option","buttons",buttons);
		categoryDialog.dialog("open");
		break;
		
	case "categoryAdd":
		selectedCategory.val("");
		buttons = categoryDialog.dialog("option", "buttons");
		buttons.filter(function(b){return b.text==="Ok"})[0].click = function() {
			adminAjaxCall({
				type: "POST",
				url: "/category/create",
				dataModel: '[{"categoryName": "'+selectedCategory.val()+'"}]',
				success: function() {
					selectedCategory.val("");	
				}
			});
		};
		categoryDialog.dialog("option","buttons",buttons);
		categoryDialog.dialog("open");
		break;
		
	case "categoryDelete":
		confirmDialog.dialog("option", "title", "Category - Delete");
		confirmDialog.children("label").text(name + " will be deleted,");
		buttons = confirmDialog.dialog("option", "buttons");
		buttons.filter(function(b){return b.text==="Yes"})[0].click = function() {
			adminAjaxCall({
				type: "POST",
				url: "/category/delete",
				dataModel: '[{"id": '+id+'}]',
				success: function() {
					confirmDialog.dialog("close");
					adminTaskTabs.tabs("load", adminTaskTabs.tabs("option", "active"));	
				}
			});
		};
		confirmDialog.dialog("option","buttons",buttons);
		confirmDialog.dialog("open");
		break;
	}
	
}

function dialogClose(dialog) {
	$(dialog).dialog("close");
	//do not call this every time to pretend network load, try to sense if there is change
	adminTaskTabs.tabs("load", adminTaskTabs.tabs("option", "active"));
	
}

function detachCategoryDialog() {
	var ariaCategoryDialog = $("div[aria-describedby=categoryDialog]");
	if (ariaCategoryDialog.length>0)
		ariaCategoryDialog.detach();		
}
