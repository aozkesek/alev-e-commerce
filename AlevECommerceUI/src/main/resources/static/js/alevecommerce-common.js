/**
 * 
 */

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	
	var mainMenuBar = $('#mainMenuBar').puimenubar();
	
	function categoryListInit(f) {
		$.ajax({
			type: "GET"
			, url: "/category/list"
			, contentType: "application/json"
			, success: function(response) {
				if (response.responseCode == 0) 
					if (response.model != null)  
						response.model.forEach(function(item, index) {
							try { f(item); } catch(e) { console.log(e); }
						});
			}	
		});
	}

	function productListInit(f) {
		$.ajax({
			type: "POST"
			, url: "/product/listTotalRecord"
			, data: '{"versionNumber":"1.0.0","firstRecordNumber":0,"maxRecordNumber":0}'
			, dataType: "json"
			, contentType: "application/json"
			, success: function(response) {
				if (response.responseCode == 0 && response.totalRecordNumber > 0)
					try { f(response.totalRecordNumber); } catch(e) { console.log(e); }
			}	
		});
	}
	
	function productListPage(o, i, m, f) {
		$.ajax({
			type: "POST"
			, url: "/product/list"
			, data: '{"versionNumber":"1.0.0","firstRecordNumber":' + i + ',"maxRecordNumber":' + m + '}'
			, dataType: "json"
			, contentType: "application/json"
			, context: o
			, success: function(response) {
				if (response.responseCode > -1)
					if (response.model != null && response.model.length > 0) 
						try { f.call(o, response.model); } catch(e) { console.log(e); }
			}
		});
		
	}
	