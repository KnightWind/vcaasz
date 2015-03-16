<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${LANG['bizconf.jsp.conf_list_main.res1']}</title>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/common.css?ver=${version}"/>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js?ver=${version}"></SCRIPT>
<script type="text/javascript">
	/*
	 * ${LANG['bizconf.jsp.conf_list_index.res48']}
	 */
	function joinMeeting(joinType,cId){//cId,cPass,code){
		parent.joinMeeting(joinType,cId);
	} 
	/*
	 * ${LANG['bizconf.jsp.conf_list_index.res3']}
	 */
	function viewConf(id){
		parent.viewConf(id);
	}
	/*
	 * ${LANG['bizconf.jsp.conf_list_main.res2']}
	 */
	function inventContact(confId){
		parent.inventContact(confId);
	}
	
	/*
	 * ${LANG['bizconf.jsp.conf_list_main.res3']}
	 */
	function updateBookMeeting(id) {
		parent.createReservationConf(id);
	}
	/*
	 * ${LANG['bizconf.jsp.conf_list_main.res4']}
	 */
	function updateAllBookMeeting(id) {
		parent.updateReservationConf(id);
	}
	/*
	 * ${LANG['bizconf.jsp.conf_list_index.res5']}
	 * updateCycleBookMeetingInfo()
	 */
	function updateCycleBookMeetingInfo(id) {
		parent.updateCycleMeetingInfo(id);
	}
	/*
	 * ${LANG['bizconf.jsp.attended_conf_list.res2']}
	 */
	function reCreateReservationConf(id) {
		parent.reCreateReservationConf(id);
	}
	function delConf(confId){
		parent.confirmDialog("${LANG['bizconf.jsp.attended_conf_list.res3']}",function(){
			$.ajax({
		      	type: "POST",
		      	url:"/user/conf/delete/"+confId,
		      	dataType:"json",
		      	success:function(data){
					if(data){
						 window.location.reload(true);
					}else{
						parent.errorDialog('${LANG['bizconf.jsp.attended_conf_list.res4']}');
					}
		      	},
		        error:function(XMLHttpRequest, textStatus, errorThrown) {
		        	alert(XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
		        }
			});  
		});	
	}
	jQuery(function($) {
		var links = [
		             "/user/conf/listWithDuringConf",
		             "/user/conf/listWithUpcomingConf",
		             "/user/conf/listWithMissConf",
		             "/user/conf/listWithAttendedConf"];
		$(".switchConf").click(function() {
			var index = $(this).index();
			var href = links[index];
			$("#contentFrame").attr("src",href);
			$(".meau_btn a").removeClass("activeMeau-left activeMeau-right");
			$(this).find("a").addClass("activeMeau-left activeMeau-right");
		});
		
		$("#contentFrame").load(function(){         
	        $(this).height($(this).contents().find("body").height() + 40);  
	    });
	});
</script>

</head>
<body>
<div class="main_content">
	<div class="meeting_top">
		<ul>
		<li class="meau_01 switchConf">
			<span class="meau_btn"><a href="javascript:;" class="activeMeau-left activeMeau-right"><img src="/static/images/icon_a.png" width="25" height="23" align="absmiddle" style=" margin-right:10px; margin-left:20px;"  />${LANG['bizconf.jsp.conf_list_main.res5']}</a></span></li>
	    <li class="meau_02 switchConf">
	    	<span class="meau_btn"><a href="javascript:;"><img src="/static/images/icon_b.png" width="25" height="23" align="absmiddle" style=" margin-right:10px; margin-left:20px;"  />${LANG['bizconf.jsp.conf_list_main.res6']}</a></span></li>
	    <li class="meau_03 switchConf">
	    	<span class="meau_btn"><a href="javascript:;"><img src="/static/images/icon_c.png" width="25" height="23" align="absmiddle" style=" margin-right:10px; margin-left:20px;"  />${LANG['bizconf.jsp.conf_list_main.res7']}</a></span></li>
	    <li class="meau_04 switchConf">
	    	<span class="meau_btn"><a href="javascript:;"><img src="/static/images/icon_d.png" width="25" height="23" align="absmiddle" style=" margin-right:10px; margin-left:20px;"  />${LANG['bizconf.jsp.conf_list_main.res8']}</a></span></li>
	    </ul>
	</div>
	<div style="clear: both;"></div>
	<iframe frameborder="0" width="100%" height="550px;" id="contentFrame" name="contentFrame" scrolling="no" src="/user/conf/listWithDuringConf"></iframe>
</div>
</body>
</html>
