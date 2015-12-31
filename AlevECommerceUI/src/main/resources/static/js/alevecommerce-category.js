/**
 * 
 */
var categoryList = $("#categoryList");
	
categoryList.puidropdown({
	data: null
	, filter: true
});

categoryListInit(
		function(category) { 
			categoryList.puidropdown("addOption", {label: category.categoryName, value: category.id }); 
			}
		);
