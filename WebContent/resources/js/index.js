function globalSearch(widget) {
    $(PF(widget).jqId + "\\:globalFilter").val(PF('searchText').jq.val());
    $(PF(widget).jqId + "\\:globalFilter").trigger("keyup");
}