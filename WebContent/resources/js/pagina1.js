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
	var searchText = removeAccents(PF('searchText').jq.val());
    $(PF('productTable').jqId + "\\:globalFilter").val(searchText);
    $(PF('productTable').jqId + "\\:globalFilter").trigger("keyup");
}

function removeAccents(word) { 
    var withAccents = 'áàãâäéèêëíìîïóòõôöúùûüçÁÀÃÂÄÉÈÊËÍÌÎÏÓÒÕÖÔÚÙÛÜÇ'; 
    var noAccents = 'aaaaaeeeeiiiiooooouuuucAAAAAEEEEIIIIOOOOOUUUUC'; 
    var newWord = ''; 
    for (i=0;i<word.length;i++) { 
        if (withAccents.search(word.substr(i,1))>=0) { 
        	newWord+=noAccents.substr(withAccents.search(word.substr(i,1)),1); 
        } else { 
        	newWord+=word.substr(i,1); 
        } 
    } 
    return newWord; 
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