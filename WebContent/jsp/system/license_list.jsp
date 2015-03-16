<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/Popup.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/reset.css"/>
<link rel="stylesheet" type="text/css" href="/static/js/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/common.css"/>
<title></title>
<%@ include file="/jsp/common/cookie_util.jsp"%>
<SCRIPT type="text/javascript" src="/static/js/jquery-1.8.3.js"></SCRIPT>
<SCRIPT type="text/javascript" src="/static/js/jquery-ui-1.9.2.custom.js"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/development-bundle/ui/minified/i18n/jquery-ui-i18n.min.js"></SCRIPT>	
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/json2.js"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/date.js"></SCRIPT>
<script type="text/javascript" src="/static/js/widgets.js"></script>
<style type="text/css">
	.showborder{
		border:#ccc 1px solid;
	}
	.fh_btn{ margin:20px 0px 20px 30px; padding-bottom:20px;display:inline-block;}
	.b_c{ display:inline-block; vertical-align:middle; margin-left:30px; margin-right:5px;}
	.b_p{ display:inline-block;}
</style>
</head>
<body onload="loaded();">
<div class="number_body">
<form id="save" name="save" action="/system/lic/save" method="post">
	<input name="siteId" type="hidden" value="${siteId}"/>
	<input name="userId" type="hidden" value="${userId}"/>
	<table border="0" cellpadding="0" cellspacing="0" class="site_top">
		<tr height="22">
	    	<td width="113" align="center">${LANG['bizconf.jsp.system.hostlist.res2']}</td>
	        <td width="170" align="center">${LANG['bizconf.jsp.system.license_list.res1']}</td>
	        <td width="170" align="center">${LANG['bizconf.jsp.system.license_list.res2']}</td>
	        <td width="200"></td> 
	    </tr>
	    <tr height="36">
	    	<td align="center"><input id="licenseCount"  name="licNum" type="text" class="text01" /></td>
	        <td align="center"><input id="effeDate" name="effeDate" type="text" class="text02" /></td>
	        <td align="center"><input id="expireDate" name="expireDate" type="text" class="text03" /></td>
	        <td align="center"><a onclick="addLic();" href="#" class="tijiao_btn Public_button">${LANG['bizconf.jsp.admin.arrange_org_user.res10']}</a></td>  
	    </tr>
	</table>
</form>

<form id="hidsave" name="hidsave" action="/system/lic/save" method="post">
	<input name="siteId" type="hidden" value="${siteId}"/>
	<input name="userId" type="hidden" value="${userId}"/>
	<input id="h_id" name="id" type="hidden" value=""/>
	<input id="h_licNum" name="licNum" type="hidden" />
	<input id="h_effeDate" name="effeDate" type="hidden" />
	<input id="h_expireDate" name="expireDate" type="hidden" />
</form>
<div class="si_m">                
<table border="0" cellpadding="0" cellspacing="0" class="site_main">
	<c:if test="${fn:length(pageModel.datas)<=0 }">
         <tr>
           <td colspan="4" width="653" align="center">
        	${LANG['website.common.msg.list.nodata']}
           </td>
         </tr>
      </c:if>
     <c:if test="${fn:length(pageModel.datas)>0 }">
     <c:forEach var="lic" items = "${pageModel.datas}"  varStatus="status">
     	<tr height="36">
	    	<td width="113" align="center">
	    		<input readonly="readonly" class="text_01" id="licNum${lic.id}" type="text" value="${lic.licNum}" bak="${lic.licNum}"/>
	    	</td>
	        <td width="190" align="center">
	        	<fmt:formatDate var="licEffeDate"  value="${lic.effeDate}" type="date" pattern="yyyy-MM-dd"/>
	        	<input readonly="readonly" class="text_02" id="effeDate${lic.id}" type="text" value="${licEffeDate }" bak="${licEffeDate}"/>
	        </td>
	        <td width="160" align="center">
	        	<fmt:formatDate var="licExpireDate"  value="${lic.expireDate}" pattern="yyyy-MM-dd"/>
	        	<input readonly="readonly" class="text_03" id="expireDate${lic.id}" type="text" value="${licExpireDate }" bak="${licExpireDate}"/>
	        </td>
        <td width="210" align="center"><%--	生效标志：${lic.effeFlag} 失效标志：${lic.expireFlag}--%>
	         <c:choose>
					<c:when  test="${lic.effeFlag eq '0' and lic.expireFlag eq '0'}">
						<a id="modify${lic.id}" href="javascript:;" class="change_btn" onclick="toUpdate(${lic.id});" >${LANG['bizconf.jsp.system.email_template_list.res7']}</a> 
						<a id="del${lic.id}" href="javascript:;" class="cc_btn" onclick="del(${lic.id});" >${LANG['bizconf.jsp.system.email_template_list.res8']}</a>
						<a id="stroe${lic.id}" style="display: none;" href="javascript:;" class="change_btn" onclick="stroeData(${lic.id});" >${LANG['bizconf.jsp.system.email_template.res5']}</a>&nbsp;
						<a id="cancel${lic.id}" style="display: none;" href="javascript:;"  class="change_btn" onclick="cancelUpdate(${lic.id})">${LANG['bizconf.jsp.admin.createOrg.res4']}</a>						
					</c:when>
					<c:when test="${lic.effeFlag eq '1' and lic.expireFlag eq '0'}">
						&nbsp;<a href="javascript:;" class="cc_btn" style="color: #666" >(${LANG['bizconf.jsp.system.license_list.res3']})</a>
					</c:when>
					<c:when test="${lic.expireFlag != '1'}">
						&nbsp;<a href="javascript:;" class="cc_btn" style="color: #666" >(${LANG['bizconf.jsp.system.license_list.res4']})</a>
					</c:when>
					<c:otherwise>
						&nbsp;<a href="javascript:;" class="cc_btn" style="color: #666" >(${LANG['bizconf.jsp.system.license_list.res5']})</a>
					</c:otherwise>
				</c:choose>
<%--				<c:if test="${lic.effeFlag eq '1'}">--%>
<%--					&nbsp;<a href="javascript:;" class="cc_btn" style="color: #666" >(${LANG['bizconf.jsp.system.license_list.res3']})</a>--%>
<%--				</c:if>--%>
	        </td> 
   		 </tr>
    </c:forEach>
   </c:if>
</table> </div>
	<div style="position: relative;margin: 0px 20px;">
		<a href="javascript:closeDialog();" style="margin:15px;display:inline-block;" class="Public_button" onclick="closeDialog();">${LANG['bizconf.jsp.admin.createOrg.ok']}</a>
		<c:if test="${userId != '' && userId > 0}">
			<input class="b_c" name="sendEmail" type="checkbox" value=""/><span class="b_p">${LANG['bizconf.jsp.system.license_list.res6']}</span>
		</c:if>	 
		<span style="position: absolute;right: 20px;top:20px;">当前可使用最大方数：${licNum}</span> 
	</div>
</div>
</body>
</html>
<fmt:formatDate var="effeDateTime" value="${site.expireDate}" type="date" pattern="yy-MM-dd"/>
<script type="text/javascript">
$(document).ready(function(){
	var lang = getBrowserLang(); 
	if (lang=="zh-cn") {
		$.datepicker.setDefaults( $.datepicker.regional[ "zh-CN" ] );
	} else {
		$.datepicker.setDefaults( $.datepicker.regional[ "en-GB" ] );
	}
	var effeDate = "${effeDateTime}".parseDate();
	$("#effeDate" ).datepicker({
		minDate: +0,
		maxDate: effeDate,
		changeMonth: true,
		changeYear: true,
		dateFormat: "yy-mm-dd",
		showOn: "both",
		buttonImage: "/static/images/calendar.jpg",
		buttonImageOnly: true
	});
	
	$("#expireDate" ).datepicker({
		minDate: +0,
		maxDate: effeDate,
		changeMonth: true,
		changeYear: true,
		dateFormat: "yy-mm-dd",
		showOn: "both",
		buttonImage: "/static/images/calendar.jpg",
		buttonImageOnly: true
	});
	
});

function cancelUpdate(licId) {
	$("#del"+licId).show();
	$("#modify"+licId).show();
	$("#stroe"+licId).hide();
	$("#cancel"+licId).hide();
	$("#licNum"+licId).removeClass("showborder").attr("readonly",true);
	$("#effeDate"+licId).datepicker("destroy").removeClass("showborder").attr("readonly",true);
	$("#expireDate"+licId).datepicker("destroy").removeClass("showborder").attr("readonly",true);
	var licNum = $("#licNum"+licId).attr("bak");
	$("#licNum"+licId).val(licNum);
	var effeDate = $("#effeDate"+licId).attr("bak");
	$("#effeDate"+licId).val(effeDate);
	var expireDate = $("#expireDate"+licId).attr("bak");
	$("#expireDate"+licId).val(expireDate);
}

function toUpdate(licId){
	$("#del"+licId).hide();
	$("#modify"+licId).hide();
	$("#stroe"+licId).show();
	$("#cancel"+licId).show();
	$("#licNum"+licId).addClass("showborder").attr("readonly",false);
	var effeDate = "${effeDateTime}".parseDate();
	$("#effeDate"+licId).addClass("showborder").datepicker({
		minDate: +0,
		maxDate: effeDate,
		changeMonth: true,
		changeYear: true,
		dateFormat: "yy-mm-dd",
		//showOn: "both",
		buttonImage: "/static/images/calendar.jpg",
		buttonImageOnly: true
	});
	
	$("#expireDate"+licId).addClass("showborder").datepicker({
		minDate: +0,
		maxDate: effeDate,
		changeMonth: true,
		changeYear: true,
		dateFormat: "yy-mm-dd",
		//showOn: "both",
		buttonImage: "/static/images/calendar.jpg",
		buttonImageOnly: true
	});
}

function checkForm(licid){
	var count = $("#licenseCount").val();
	if(licid){
		count = $("#licNum"+licid).val();
	}
	var licNum = 0 + ${licNum}+count;
	if(!count){
		parent.errorDialog("${LANG['bizconf.jsp.system.license_list.res7']}");
		return false;
	}
	
	if(count && (count==0 || count<-5000 || count>5000) || licNum<2){
		parent.errorDialog("${LANG['bizconf.jsp.system.license_list.res8']}");
		return false;
	}
	if(count && !count.isFullInteger()){
		parent.errorDialog("${LANG['bizconf.jsp.system.license_list.res9']}");
		return false;
	}
	var effeDate = $("#effeDate").val();
	if(licid){
		effeDate = $("#effeDate"+licid).val();
	}
	if(!effeDate){
		parent.errorDialog("${LANG['bizconf.jsp.system.license_list.res10']}");
		return false;
	}
	var expireDate = $("#expireDate").val();
	if(licid){
		expireDate = $("#expireDate"+licid).val();
	}	
	if(!expireDate){
		parent.errorDialog("${LANG['bizconf.jsp.system.license_list.res11']}");
		return false;
	}
	var effeDateTemp = effeDate.parseDate();
	var expireDateTemp = expireDate.parseDate();
	if(effeDateTemp.after(expireDateTemp)){
		parent.errorDialog("${LANG['bizconf.jsp.system.license_list.res12']}");
		return false;
	}
	return true;
}

function stroeData(licid){
	if(checkForm(licid)){
		$("#h_id").val(licid);
		$("#h_licNum").val($("#licNum"+licid).val());
		$("#h_effeDate").val($("#effeDate"+licid).val()+" 00:00:10");
		$("#h_expireDate").val($("#expireDate"+licid).val()+" 23:59:59");
	 	hidsave.submit();	
	}
}

function addLic(){
	if(checkForm()){
		$("input[name=effeDate]").val($("input[name=effeDate]").val()+" 00:00:10");
		$("input[name=expireDate]").val($("input[name=expireDate]").val()+" 23:59:59");
		save.submit();	
	}
}

function loaded() {
	
	var frame = parent.$("#countManagerDiv");
	frame.trigger("loaded");
}

function closeDialog() {
	//alert('hi');
	if($("input[name=sendEmail]:checked").length>0){
		$.ajax({
	      	type: "POST",
	      	url:"/system/lic/sendMail?userId=${userId}",
	      	dataType:"json",
	      	async:false,
	      	success:function(data){
				if(data.status == 1){
					parent.successDialog("${LANG['bizconf.jsp.system.license_list.res13']}");
				}else{
					parent.errorDialog("${LANG['bizconf.jsp.system.license_list.res14']}");
				} 
	      	},
	        error:function(XMLHttpRequest, textStatus, errorThrown) {
	        	alert(XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
	       		//html = "${LANG['bizconf.jsp.system.license_list.res15']}";
	       		//$(self).attr("title",html);
	        }
		}); 
		closePup();	
	}else{
		closePup();	
	}
}

function closePup(){
	if(parent.$("#hostManagerDiv").length>0){
		parent.$("#hostManagerDiv").find("iframe")[0].src = "/system/lic/listHost?siteId=${siteId}&userId=${userId}";	
	}
	var frame = parent.$("#countManagerDiv");
	frame.trigger("closeDialog");
}

function del(id){
	parent.confirmDialog("${LANG['bizconf.jsp.system.license_list.res16']}",function(){
		window.location = "/system/lic/del?id="+id;	
	});
}


</script>
