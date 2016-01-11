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
	categoryList.puidropdown("addOption", {value: ""+category.id, label: category.categoryName}); 
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

confirmDialogHandle.puidialog({
	responsive: true
	, confirmCallback: null
	, modal: true
	, buttons: [{
        text: 'Yes',
        icon: 'fa-check',
        click: function() {
        	try { confirmDialogHandle.puidialog("option", "confirmCallback")(); } catch(e) { console.log(e); }
            confirmDialogHandle.puidialog('hide');
        }
    },
    {
        text: 'No',
        icon: 'fa-close',
        click: function() {
        	confirmDialogHandle.puidialog('hide');
        }
    }
]
});

$("#categoryUpdate, #categoryAdd, #categoryDelete").puibutton({
	click: function(event) {
		var newLabel = $(categoryList[0].parentElement.parentElement).children("input").val();
		var selLabel = $(categoryList).puidropdown("getSelectedLabel");
		var buttonId = event.target.id;
		var isDelete = buttonId === "categoryDelete";
		var isUpdate = buttonId === "categoryUpdate"
		
		if (!isDelete && newLabel === selLabel) {
			growlMessages.puigrowl("show", [{severity: "error", summary: "Invalid operation", detail: "Nothing changed!"}]);
			return;
		}
		if (isDelete && newLabel != selLabel) {
			growlMessages.puigrowl("show", [{severity: "error", summary: "Invalid operation", detail: "Category changed!"}]);
			return;
		}
			
		var selValue = $(categoryList).puidropdown("getSelectedValue");
	
		var message = isDelete ? selLabel + " will deleted,"
				: isUpdate ? selLabel + " will updated with " + newLabel + ", "
						: newLabel + " will added,";
		
		categoryDialog(message
				, isUpdate ? function() { categoryCrud("update", selValue, newLabel); }
						: isDelete ? function() { categoryCrud("delete", selValue, newLabel); } 
								: function() { categoryCrud("create", null, newLabel); });
		
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

