<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css"/>
<link rel="stylesheet" type="text/css" href="/static/js/jqtransform/jqtransformplugin/jqtransform.css"/>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom.js"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/development-bundle/ui/minified/i18n/jquery-ui-i18n.min.js"></SCRIPT>
<script type="text/javascript" src="/static/js/jqtransform/jqtransformplugin/jquery.jqtransform.js"></script>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery.cookie.js"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/util.js"></SCRIPT>



<c:if test="${!empty errorMessage}">
	<script type="text/javascript">
		$(function() {
			parent.errorDialog("${errorMessage}");
		});
	</script>
</c:if>
<c:if test="${!empty infoMessage}">
	<script type="text/javascript">
	$(function() {
		parent.successDialog("${infoMessage}");
	});	
	</script>
</c:if>

<script type="text/javascript"> 
$(function() {
	$('#query').jqTransform({imgPath:'/static/js/jqtransform/jqtransformplugin/img/'});
	$('#site-list tr').hover(function() {
			$(this).addClass('tr-hover');
		}, function() {
			$(this).removeClass('tr-hover');
	});
	
	$(".gaoji").toggle(function () {
	    $("#search-condition").slideDown();
	    parent.resizeHeight(150, true);//${LANG['bizconf.jsp.admin.conf_list.res1']}
	}, function () {
		$("#search-condition").slideUp();
		var height = $("#search-condition").height();
		parent.resizeHeight(150, false);//${LANG['bizconf.jsp.admin.conf_list.res2']}
	});
	var lang = getBrowserLang();
	if (lang=="zh-cn") {
		$.datepicker.setDefaults( $.datepicker.regional[ "zh-CN" ] );
	} else {
		$.datepicker.setDefaults( $.datepicker.regional[ "en-GB" ] );
	}
	$( ".expireDate" ).datepicker({
		dateFormat: "yy-mm-dd",
		showOn: "both",
		buttonImage: "/static/images/calendar.jpg",
		buttonImageOnly: true
	});
	$("#createSite").click(function() {
		parent.editSite();
	});
});

function checkForm(){
	$("#textfield01").val("${nameOrSign}");
	var nameOrSign = $("#textfield01").val();
	if(nameOrSign != null && nameOrSign != ""){
		query.action="/system/site/listWithSignOrName";
	}else{
		formBind();
		query.action="/system/site/listWithCondition";
	}
}

function querySite(url){
	resetPageNo();
	query.action=url;
	query.submit();
}

function unlock(id){
	parent.confirmDialog("${LANG['system.site.unlock']}",function(){
		formBind();
		query.action="/system/site/unlock/"+id;
		query.submit();
	});
	
}

function lock(id){
	parent.confirmDialog("${LANG['system.site.lock']}",function(){
		formBind();
		query.action="/system/site/lock/"+id;
		query.submit();
	});
}

function del(id){
	parent.confirmDialog("${LANG['system.site.delete']}", function() {
		formBind();
		query.action="/system/site/delete/"+id;
		query.submit();
	});
}

function updateSite(id) {
	parent.editSite(id);
}

function sortQuery(sortField,sortord){
	formBind();
	var nameOrSign = $("#textfield01").val();
	var url = "/system/site/listWithCondition";
	if(nameOrSign != null && nameOrSign != ""){
		url = "/system/site/listWithSignOrName";
	}
	$("#sortField").val(sortField);
	$("#sortord").val(sortord);
	querySite(url);
}

function formBind(){
	$("#textfield01").val("${nameOrSign}");
	$("#sortField").val("${sortField}");
	$("#sortord").val("${sortord}");
	$("#siteName").val("${siteName}");
	$("#siteSign").val("${siteSign}");
	$("#siteFlag").val("${site_flag}");
	$("#lockFlag").val("${lock_flag}");
	$("#expireDateStart").val("${expireDateStart}");
	$("#expireDateEnd").val("${expireDateEnd}");
}

function enterSumbit(){   
    var event=arguments.callee.caller.arguments[0]||window.event;//${LANG['bizconf.jsp.admin.conf_list.res3']}   
    if (event.keyCode == 13){       //${LANG['bizconf.jsp.admin.conf_list.res4']}
    	querySite("/system/site/listWithSignOrName");   
    }   
}  

</script>
<title>${LANG['system.menu.site.list']}</title>
</head>
<body>
<form id="query" name="query" action="/system/site/list/" method="post" onsubmit="checkForm();">
<div class="">
 <div class="m_top">
    <div class="text_box">
    	<input type="text" name="nameOrSign" id="textfield01" value="${LANG['bizconf.jsp.system.meeting_list.res1']}..." onkeydown="enterSumbit()"  onfocus="this.value=''; this.onfocus=null;" />
    	<a class="sousuo" title="${LANG['system.search']}" href="javascript:;" onclick='querySite("/system/site/listWithSignOrName")'><img src="${baseUrlStatic}/images/sousuo.jpg" width="29" height="24" /></a>
    	<a class="gaoji" title="${LANG['system.advancedSearch']}" href="#">${LANG['system.advancedSearch']}</a>
    </div>
    <div class="make_new"><a href="javascript:;" id="createSite">${LANG['system.site.list.create']}</a></div>
  </div>
    	<div id="search-condition" style="display: none; width:100%; height: auto;margin-left: 20px;">
    		<div style="height:30px;">
	    		<label>${LANG['system.site.list.CompanyName']} </label>
	    		<input type="text" name="siteName" id="siteName" style="width:262px;"/>
    		</div>
    		<div style="height:30px;clear: left">
	    		<label>${LANG['system.site.list.SiteSign']} </label>
	    		<input type="text" name="siteSign" id="siteSign" style="width:262px;"/>
    		</div>
    		<div style="height:30px;clear: left">
	    		<label>${LANG['system.site.list.SiteFlag']} </label>
	    		<select name="siteFlag" id="siteFlag" style="width: 83px;">
	    			<option value="0">${LANG['system.all']}</option>
	    			<option value="1">${LANG['system.site.list.SiteFlag.official']}</option>
	    			<option value="2">${LANG['system.site.list.SiteFlag.trial']}</option>
	    		</select>
	    		<label style="margin:4px 11px">${LANG['system.site.list.Status']} </label>
	    		<select name="lockFlag" id="lockFlag" style="width: 83px;">
	    			<option value="0">${LANG['system.all']}</option>
	    			<option value="1">${LANG['system.site.list.Status.normal']}</option>
	    			<option value="2">${LANG['system.site.list.Status.lock']}</option>
	    		</select>
    		</div>
    		<div style="height:30px;clear: left">
	    		<label>${LANG['system.site.list.ExpireDate']} </label>
	    		<input type="text" name="expireDateStart" id="expireDateStart" style="width:100px;" class="expireDate"/>
	    		<label style="margin-top:4px;margin-left: 15px;margin-right: 18px;">---</label>
	    		<input type="text" name="expireDateEnd" id="expireDateEnd" style="width:100px;" class="expireDate"/>
    		</div>
    		<div style="height:30px;clear: left">
    			<input type="button" class='button-small' onclick='querySite("/system/site/listWithCondition")' value="${LANG['system.search']}"/>
    		</div>
    	</div>

<table width="98.5%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_box" style=" margin-left:10px; margin-right:10px; border:#B5D7E6 1px solid; border-top:none; border-bottom:none;">
  
  <tr class="table002" height="32" >
    <td>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" id="site-list">
      <tr class="table003" height="38" >
      	<input type="hidden" name="sortField" id="sortField"/>
      	<input type="hidden" name="sortord" id="sortord"/>
        <td width="20%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span>${LANG['system.site.list.CompanyName']}</span></div></td>
        <td width="10%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center">
	        <c:if test="${1!=sortField}">
	        	<a class="paixu01" href="javascript:;" onclick="sortQuery('1','0')"><span style="text-decoration: underline;">${LANG['system.site.list.SiteSign']}&nbsp;</span><img src="${baseUrlStatic}/images/paixuzong.png" width="6" height="13" /></a>
	        </c:if>
	        <c:if test="${1==sortField && 0==sortord}">
	        	<a class="paixu01" href="javascript:;" onclick="sortQuery('1','1')"><span style="text-decoration: underline;">${LANG['system.site.list.SiteSign']}&nbsp;</span><img src="${baseUrlStatic}/images/paixu02.png" width="6" height="13" /></a>
	        </c:if>
	        <c:if test="${1==sortField  && 1==sortord}">
	        	<a class="paixu01" href="javascript:;" onclick="sortQuery('1','0')"><span style="text-decoration: underline;">${LANG['system.site.list.SiteSign']}&nbsp;</span><img src="${baseUrlStatic}/images/paixu01.png" width="6" height="13" /></a>
	        </c:if>
        </div></td>
       <td width="10%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center">
        	<c:if test="${2!=sortField}">
	        	<a class="paixu01" href="javascript:;" onclick="sortQuery('2','0')"><span style="text-decoration: underline;">${LANG['system.site.list.SiteFlag']}&nbsp;</span><img src="${baseUrlStatic}/images/paixuzong.png" width="6" height="13" /></a>
	        </c:if>
	        <c:if test="${2==sortField && 0==sortord}">
	        	<a class="paixu01" href="javascript:;" onclick="sortQuery('2','1')"><span style="text-decoration: underline;">${LANG['system.site.list.SiteFlag']}&nbsp;</span><img src="${baseUrlStatic}/images/paixu02.png" width="6" height="13" /></a>
	        </c:if>
	        <c:if test="${2==sortField  && 1==sortord}">
	        	<a class="paixu01" href="javascript:;" onclick="sortQuery('2','0')"><span style="text-decoration: underline;">${LANG['system.site.list.SiteFlag']}&nbsp;</span><img src="${baseUrlStatic}/images/paixu01.png" width="6" height="13" /></a>
	        </c:if>
        </div></td>
        <td width="10%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span>${LANG['system.site.list.License']}</span></div></td>
        <td width="15%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center">
        	<c:if test="${3!=sortField}">
	        	<a class="paixu01" href="javascript:;" onclick="sortQuery('3','0')"><span style="text-decoration: underline;">${LANG['system.site.list.ExpireDate']}&nbsp;</span><img src="${baseUrlStatic}/images/paixuzong.png" width="6" height="13" /></a>
	        </c:if>
	        <c:if test="${3==sortField && 0==sortord}">
	        	<a class="paixu01" href="javascript:;" onclick="sortQuery('3','1')"><span style="text-decoration: underline;">${LANG['system.site.list.ExpireDate']}&nbsp;</span><img src="${baseUrlStatic}/images/paixu02.png" width="6" height="13" /></a>
	        </c:if>
	        <c:if test="${3==sortField  && 1==sortord}">
	        	<a class="paixu01" href="javascript:;" onclick="sortQuery('3','0')"><span style="text-decoration: underline;">${LANG['system.site.list.ExpireDate']}&nbsp;</span><img src="${baseUrlStatic}/images/paixu01.png" width="6" height="13" /></a>
	        </c:if>
        </div></td>
        <td width="10%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span>${LANG['system.site.list.UserName']}</span></div></td>
        <td width="10%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span>${LANG['system.site.list.Status']}</span></div></td>
        <td width="15%" height="38" bgcolor="d3eaef" class="STYLE10" class="STYLE_none" style="border-right:none"><div align="center"><span>${LANG['system.Operate']}</span></div></td>
      </tr>
     <c:forEach var= "confBase" items = "${confList}"  varStatus="status">
      <tr>
        <td height="32" class="STYLE19">
        	<div align="center"><span>${confBase.confHwid}</span></div>
        </td>
        <td height="32" class="STYLE19" b><div align="center">${siteBase.siteSign }</div></td>
        <c:if test="${siteBase.siteFlag == 1 }">
			<td height="32" class="STYLE19"><div align="center">${LANG['system.site.list.SiteFlag.official']}</div></td>
		</c:if>
		<c:if test="${siteBase.siteFlag == 2 }">
			<td height="32" class="STYLE19"><div align="center">${LANG['system.site.list.SiteFlag.trial']}</div></td>
		</c:if>
        <td height="32" class="STYLE19"><div align="center">${confBase.confName}</div></td>
        <td height="32" class="STYLE19"><div align="center"><fmt:formatDate  value="${confBase.startTime}" type="date" pattern="yyyy-MM-dd"/></div></td>
        <td height="32" class="STYLE19"><div align="center" id="siteIdDiv_${siteBase.id}">--</div></td>
	    <c:if test="${siteBase.lockFlag == 1}"> 
			<td height="32" class="STYLE19"><div align="center" class="STYLE21">${LANG['system.site.list.Status.normal']}</a></span></div></td>
		</c:if>
		<c:if test="${siteBase.lockFlag == 2}"> 
			<td height="32" class="STYLE19"><div align="center" class="STYLE21">${LANG['system.site.list.Status.lock']}</a></span></div></td>
		</c:if>
        <td height="32" class="STYLE19">
        	<div align="center" class="STYLE21">
	     		<c:if test="${siteBase.lockFlag == 2}"> 
					<a href="javascript:;" onclick='unlock("${siteBase.id}")'>${LANG['system.site.list.Status.unlock']}</a>&nbsp;&nbsp;&nbsp;&nbsp;
				</c:if>
				<c:if test="${siteBase.lockFlag == 1}"> 
					<a href="javascript:;" onclick='lock("${siteBase.id}")'>${LANG['system.site.list.Status.lock']}</a>&nbsp;&nbsp;&nbsp;&nbsp;
				</c:if>
				<a onclick="updateSite(${siteBase.id})" href="javascript:;">${LANG['system.change']}</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:;" onclick='del("${siteBase.id}")'>${LANG['system.delete']}</a>
			</div>
        </td>
      </tr>
     </c:forEach>
    </table>
  </tr>
  <tr>
    <td class="table_bottom" height="38">
    	<jsp:include page="/jsp/common/page_info.jsp" />
    </td>
  </tr>
</table>

</div>
</form>
</body>
</html>



<script type="text/javascript"> 
function initPage(){
	var supperAdminArray=new Array();
	var eachSupperAdmin=null;
	<c:forEach var="supAdmin" items="${supperAdminList}" varStatus="status">
	eachSupperAdmin=new Array();
	eachSupperAdmin.push("${supAdmin.id}");
	eachSupperAdmin.push("${supAdmin.siteId}");
	eachSupperAdmin.push("${supAdmin.trueName}");
	supperAdminArray.push(eachSupperAdmin);
	</c:forEach>
	if(supperAdminArray!=null && supperAdminArray.length>0){
		for(var ii=0;ii<supperAdminArray.length;ii++){
			$("#siteIdDiv_"+supperAdminArray[ii][1]).html(supperAdminArray[ii][2]);
		}
		
	}
}
initPage();
</script>
