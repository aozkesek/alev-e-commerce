/**
 * 
 */

function onCategoryButtonsClick(target) {
	var categoryList = $("#categoryList"),
		id = categoryList.val(),
		name = categoryList.children().filter("[value="+id+"]").text();
	
	switch(target) {
	case "update":
		UIkit.modal.prompt(
				"<h2 class='uk-text-bold'>Update category,</h2><p>Category name:</p>",
				name,
				function(newName) {
					adminAjaxCall({
						type: "POST",
						url: "/category/update",
						dataModel: '[{"categoryName": "'+newName+'", "id":'+id+'}]',
						success: function() {
							growlMessages.puigrowl("show", [{severity: "info", summary: "Update category,", detail: "Done."}]);
							onAdminNavChange('/admincategory');	
						},
						error:  function() {
							growlMessages.puigrowl("show", [{severity: "error", summary: "Update category,", detail: "Failed."}]);
						} 
					});
				}
		);
		break;
		
	case "add":
		UIkit.modal.prompt(
				"<h2 class='uk-text-bold'>Add category,</h2><p>Category name:</p>",
				"",
				function(newName) {
					adminAjaxCall({
						type: "POST",
						url: "/category/create",
						dataModel: '[{"categoryName": "'+newName+'"}]',
						success: function() {
							growlMessages.puigrowl("show", [{severity: "info", summary: "Add category,", detail: "Done."}]);
							onAdminNavChange('/admincategory');	
						},
						error:  function() {
							growlMessages.puigrowl("show", [{severity: "error", summary: "Add category,", detail: "Failed."}]);
						}
					});
				}
		);
		break;
		
	case "delete":
		UIkit.modal.confirm(
				"<h2 class='uk-text-danger uk-text-bold'>Delete category,</h2><p>" + name + " will be deleted,</p><p class='uk-text-danger'>Are you sure?</p>",
				function() {
					adminAjaxCall({
						type: "POST",
						url: "/category/delete",
						dataModel: '[{"id": '+id+'}]',
						success: function() {
							growlMessages.puigrowl("show", [{severity: "info", summary: "Delete category,", detail: "Done."}]);
							onAdminNavChange('/admincategory');
						},
						error:  function() {
							growlMessages.puigrowl("show", [{severity: "error", summary: "Delete category,", detail: "Failed."}]);
						}
					});
				}
		);
		break;
	}
	
}


