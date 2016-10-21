$(function() {	
	$('form').keypress(function(e) {
        if (e.keyCode == 13) {
            return false;
         }
	})
	
	$('.search-input').keyup(function(e){
		e.preventDefault();
		var code = e.which;
	    if(code==13) {
	    	onEnter();
		} else {
			productSearch();
		}
	})
});

function productSearch() {
    $(PF('productTable').jqId + "\\:globalFilter").val(PF('searchText').jq.val());
    $(PF('productTable').jqId + "\\:globalFilter").trigger("keyup");
}

function onEnter() {
	var products = $('.product-name').length;
	if (products == 1) {
		var product = $('.product-name');
		PF('productTable').clearFilters();
		product.click();
		$('.search-input').val('');
	}
}