/**
 * 
 */
var categoryList = $("#categoryList");
var selectedCategory = null;

$("#categoryFieldset").puifieldset();
$("#categoryUpdate, #categoryAdd").puibutton();

categoryList.puidropdown({
	data: null
	, filter: true
	, editable: true
	, change: function(event) {
		selectedCategory = $(this).puidropdown('getSelectedValue');
		
	}
});

categoryListInit(
		function(category) { 
			console.log(category);
			console.log(category.id + ">" + category.categoryName);
			categoryList.puidropdown("addOption", {value: ""+category.id, label: category.categoryName}); 
			}
		);
