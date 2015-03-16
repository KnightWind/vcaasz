/*=======================================*/
/*	Alan Liu                             */
/*	email:mingliu@bizconf.cn	         */
/*	2013-07-23                           */
/*=======================================*/

function initTab() {
	$(".tabs").slideBar({
        clickCallback: function(elem) {
        	var href = $(elem).attr("href");
        	$("#contentFrame").attr("src",href);
        }
    });
}
function refreshIHeight() {
	var height = $("#contentFrame").contents().find("body").outerHeight();
	$("#contentFrame").height(height); 
	parent.resizeHeight();
}
jQuery(function($) {
	initTab();
});