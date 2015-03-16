<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery.uniform/themes/default/css/uniform.custom.css">
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/style.css"/>

<%@ include file="/jsp/common/cookie_util.jsp"%>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom.js"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/development-bundle/ui/minified/i18n/jquery-ui-i18n.min.js"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery.uniform/jquery.uniform.js"></script>
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
	//for search input style
	$("#query").find("input, select").not(".skipThese").uniform();
	
	//table tr background highlight
	$('#site-list tr').hover(function() {
			$(this).addClass('tr-hover');
		}, function() {
			$(this).removeClass('tr-hover');
	});
	
	//show or hide search input
	$(".gaoji").toggle(function () {
	    $("#search-condition").slideDown(function() {
		    parent.resizeHeight();//${LANG['bizconf.jsp.admin.conf_list.res1']}
	    });
	}, function () {
		$("#search-condition").slideUp(function() {
			parent.resizeHeight();//${LANG['bizconf.jsp.admin.conf_list.res2']}
		});
	});
	var lang = getBrowserLang();
	if (lang=="zh-cn") {
		$.datepicker.setDefaults( $.datepicker.regional[ "zh-CN" ] );
	} else {
		$.datepicker.setDefaults( $.datepicker.regional[ "en-GB" ] );
	}
	$( ".expireDate" ).datepicker({
		changeMonth: true,
		changeYear: true,
		dateFormat: "yy-mm-dd",
		showOn: "both",
		buttonImage: "/static/images/calendar.jpg",
		buttonImageOnly: true
	});
	
	$("#createSite").click(function() {
		parent.createOrUpdateSite();
	});
	
	$("#advanceSearch").click(function(){
		var userType = "${user.sysType}";//2017-07-08 add
		
		var dateStart = $("#expireDateStart").val();
		var dateEnd =  $("#expireDateEnd").val();
		if(dateStart && dateEnd && !compareDate(dateStart, dateEnd)){
			parent.errorDialog("${LANG['system.conf.sitelist.starttime.than.endtime']}");
			return;
		}
		//2014-07-08 add
		if(userType == '6'){  
			var siteName = $("#siteName").val();
			var siteSign = $("#siteSign").val();
			if(siteName=="" && siteSign =="" ){
				parent.errorDialog("${LANG['system.conf.sitelist.nameandsite.notnull.simultaneously']}");
				return;
			}
			//查询条件存在
			if(siteName!=""){
				$("#textfield01").val(siteName);
			}else if(siteSign!=""){
				$("#textfield01").val(siteSign);
			}
		}
		querySite("/system/site/listWithCondition");
		
	});
});

function countManager(id) {
	parent.countManager(id);
}
function hostManager(id) {
	parent.hostManger(id);
}
function updateSite(id) {
	parent.createOrUpdateSite(id);
}

//设置Download地址
function downloadSetting(id) {
	parent.downloadSetting(id);
}
function viewSite(id) {
	parent.viewSite(id);
}
function checkForm(){
	//var userType = "${user.sysType}";
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
	var userType = "${user.sysType}";
	
	var searchResource= "${LANG['system.site.search.info']}";
	var searchValue = $("#textfield01").val();
	if(userType != '6'){
		if (searchValue==searchResource) {
			$("#textfield01").val("");
		}
		resetPageNo();
		query.action=url;
		query.submit();	
	}else{//客服系统 2017-07-08 add
		if(searchValue.indexOf("企业名称")>-1 || searchValue.indexOf("企业标识")>-1 || searchValue.indexOf("Enterprise Name")>-1 || searchValue.indexOf("Enterprise identify")>-1 || $("#textfield01").val()== ""){
			parent.errorDialog("${LANG['system.conf.sitelist.nameandsite.notnull']}");
			return;
		}else{
			resetPageNo();
			query.action=url;
			query.submit();
		}
	}
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

function sysAdminManage(id){
	var url = "/admin/sysAdminManage/"+id;
	window.open(url,"_bank");//
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
	
	//非客服 add
	var userType = "${user.sysType}";
	if(userType != '6'){
		querySite(url);
	}
	
}

function formBind(){
	$("#textfield01").val("${nameOrSign}");
	$("#sortField").val("${sortField}");
	$("#sortord").val("${sortord}");
	$("#siteName").val("${siteName}");
	$("#siteSign").val("${siteSign}");
	$("#siteFlag").val("${siteFlag}");
	$("#lockFlag").val("${lockFlag}");
	$("#hireMode").val("${hireMode}");
	$("#expireDateStart").val("${expireDateStart}");
	$("#expireDateEnd").val("${expireDateEnd}");
}

function enterSumbit(url){   
    var event=arguments.callee.caller.arguments[0]||window.event;//${LANG['bizconf.jsp.admin.conf_list.res3']}   
    if (event.keyCode == 13){       //${LANG['bizconf.jsp.admin.conf_list.res4']}
    	querySite(url);   
    }   
}  

$(document).ready(function(){
	if (!$.browser.msie || $.browser.version>7) {
		$("#textfield01").watermark('${LANG['bizconf.jsp.system.site_list.res1']}');
	}
	$("#search-condition").find("input[type=text]").each(function(){
		if($(this).val()){
			$("#search-condition").show();
			return false;
		}
	});
	$("#search-condition").find("select").each(function(){
		if($(this).val()!=0){
			$("#search-condition").show();
			return false;
		}		
	});
});

function resetPwd(siteId,obj) {       
	$.ajax({
      	type: "POST",
      	url:"/system/site/resetEmail",
      	data:{'siteId':siteId},
      	dataType:"json",
      	success:function(data){
      		//alert('aa=='+data.status);
			 if(data.status == 1){
				top.successDialog("${LANG['website.system.userlist.alreadysendmail']}${LANG['website.common.symbol.exmark']}");
				$(obj).html("${LANG['website.system.userlist.alreadysendmail.2']}");
				//$(obj).css({color:"red"}); 
				obj.onclick=function(){};
			 }else{
				top.errorDialog("${LANG['website.system.userlist.failuresendmail']}${LANG['website.common.symbol.exmark']}");
			 }
      	},
        error:function(XMLHttpRequest, textStatus, errorThrown) {
        	alert(XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
        }
	}); 
}

function exportSite(){
		formBind();
		query.action="/system/site/exportSite";
		query.submit();
}
</script>
<title>${LANG['system.menu.site.list']}</title>
</head>
<body>
<form id="query" name="query" action="/system/site/list/" method="post" onsubmit="checkForm();">
<div style="margin: 0 10px">
 <div class="m_top">
    <div class="text_box">
    	<input  type="hidden" name="daochu" value="${daochu}" />
    	<input class="search_input" type="text" name="nameOrSign" id="textfield01" value="${nameOrSign}" onkeydown='enterSumbit("/system/site/listWithSignOrName")'/>
    	<input type="button" name="search" id="search" class="sousuo searchs_button skipThese"  onclick='querySite("/system/site/listWithSignOrName")'/>
    	<a style="" class="gaoji" title="${LANG['system.advancedSearch']}" href="javascript:;">${LANG['system.advancedSearch']}</a>
    </div>
  </div>
    	<div id="search-condition" style="display: none; width:100%; height: auto;margin-left: 20px;">
    		<div style="height:30px;">
	    		<label>${LANG['system.site.list.CompanyName']} </label>
	    		<input type="text" name="siteName" id="siteName" value="${siteName}" style="width:262px;" onkeydown='enterSumbit("/system/site/listWithCondition")'/>
    		</div>
    		<div style="height:30px;clear: left">
	    		<label>${LANG['system.site.list.SiteSign']} </label>
	    		<input type="text" name="siteSign" id="siteSign" value="${siteSign}" style="width:262px;" onkeydown='enterSumbit("/system/site/listWithCondition")'/>
    		</div>
    		<div style="height:30px;clear: left">
	    		<label>${LANG['system.site.list.SiteFlag']} </label>
	    		<select name="siteFlag" id="siteFlag">
					<cc:siteList var="SITE_TYPES"/>
			   		<c:forEach var="eachType" items="${SITE_TYPES}"  varStatus="itemStatus">
			   			<c:set var ="typeName" value="site.type.${eachType }"/>
			   			<option value="${eachType}" <c:if test="${siteFlag==eachType}">selected</c:if>>${LANG[typeName]}</option>
			   		</c:forEach>
    			</select>
	    		<label style="margin:4px 11px">${LANG['system.site.list.Status']} </label>
	    		<select name="lockFlag" id="lockFlag">
					<cc:siteList var="SITE_STATUS"/>
			   		<c:forEach var="eachType" items="${SITE_STATUS}"  varStatus="itemStatus">
			   			<c:set var ="typeName" value="site.status.${eachType }"/>
			   			<option value="${eachType}" <c:if test="${lockFlag==eachType}">selected</c:if>>${LANG[typeName]}</option>
			   		</c:forEach>
    			</select>
    		</div>
    		<div style="height:30px;clear: left">
	    		<label>${LANG['system.site.list.SiteHire']}  </label>
	    		<select name="hireMode" id="hireMode">
					<cc:siteList var="SITE_HIREMODE"/>
			   		<c:forEach var="eachType" items="${SITE_HIREMODE}"  varStatus="itemStatus">
			   			<c:set var ="typeName" value="site.hire.${eachType }"/>
			   			<option value="${eachType}" <c:if test="${hireMode==eachType}">selected</c:if>>${LANG[typeName]}</option>
			   		</c:forEach>
    			</select>
    		</div>
    		<div style="height:30px;clear: left">
	    		<label>${LANG['system.site.list.ExpireDate']} </label>
	    		<input type="text" name="expireDateStart" id="expireDateStart"  value="${expireDateStart}" style="width:100px;" class="expireDate"/>
	    		<label style="margin-top:4px;margin-left: 15px;margin-right: 18px;">---</label>
	    		<input type="text" name="expireDateEnd" id="expireDateEnd" value="${expireDateEnd}"  style="width:100px;" class="expireDate"/>
    		</div>
    		<div style="height:30px;clear: left">
    			<input type="button" id="advanceSearch" class='button-small skipThese' value="${LANG['system.search']}"/>
    		</div>
    	</div>

<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_box" style=" border:#B5D7E6 1px solid; border-top:none; border-bottom:none;">
  <tr>
  <c:if test="${user.sysType gt 6 }">
  	<td colspan="9" >
	  		<div style="position: absolute; right: 20px; margin-top: -40px;">
	  			<div class="make_new"><a href="javascript:;" id="createSite"><b>${LANG['system.site.list.create']}</b></a></div>
	  		</div>
  	</td>
  	<td >
	  	<div style="position: absolute; right: 130px; margin-top: -40px; ">
	  		<div class="make_new" ><a href="javascript:;" id="exportSite" onclick="exportSite()"><b>${LANG['bizconf.jsp.conflogs.res1']}</b></a></div>
	  	</div>
  	</td>
  	</c:if>
  </tr>
  <tr class="table002" height="32" >
    <td>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" id="site-list">
    	<tr>
	      	<td colspan="7">
		      	<input type="hidden" name="sortField" id="sortField"/>
		      	<input type="hidden" name="sortord" id="sortord"/>
	      	</td>
    	</tr>
      <tr class="table003" height="38" >
        <td width="16%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['system.site.list.CompanyName']}</b></span></div></td>
        <td width="10%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center">
	        <c:if test="${1!=sortField}">
	        	<a class="paixu01" href="javascript:sortQuery('1','0');"><span style="text-decoration: underline;"><b>${LANG['system.site.list.SiteSign']}</b></span><img src="${baseUrlStatic}/images/paixuzong.png" width="6" height="13" /></a>
	        </c:if>
	        <c:if test="${1==sortField && 0==sortord}">
	        	<a class="paixu01" href="javascript:sortQuery('1','1');" ><span style="text-decoration: underline;"><b>${LANG['system.site.list.SiteSign']}</b></span><img src="${baseUrlStatic}/images/paixu02.png" width="6" height="13" /></a>
	        </c:if>
	        <c:if test="${1==sortField  && 1==sortord}">
	        	<a class="paixu01" href="javascript:sortQuery('1','0');"><span style="text-decoration: underline;"><b>${LANG['system.site.list.SiteSign']}</b></span><img src="${baseUrlStatic}/images/paixu01.png" width="6" height="13" /></a>
	        </c:if>
        </div></td>
        <%--
       <td width="10%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center">
        	<c:if test="${2!=sortField}">
	        	<a class="paixu01" href="javascript:sortQuery('2','0');" ><span style="text-decoration: underline;"><b>${LANG['system.site.list.SiteFlag']}</b></span><img src="${baseUrlStatic}/images/paixuzong.png" width="6" height="13" /></a>
	        </c:if>
	        <c:if test="${2==sortField && 0==sortord}">
	        	<a class="paixu01" href="javascript:sortQuery('2','1');" ><span style="text-decoration: underline;"><b>${LANG['system.site.list.SiteFlag']}</b></span><img src="${baseUrlStatic}/images/paixu02.png" width="6" height="13" /></a>
	        </c:if>
	        <c:if test="${2==sortField  && 1==sortord}">
	        	<a class="paixu01" href="javascript:sortQuery('2','0');" ><span style="text-decoration: underline;"><b>${LANG['system.site.list.SiteFlag']}</b></span><img src="${baseUrlStatic}/images/paixu01.png" width="6" height="13" /></a>
	        </c:if>
        </div></td>--%>
        <%--<td width="8%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['system.site.list.chargeMode']}</b></span></div></td> --%>
        <td width="12%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center">
        	<c:if test="${5!=sortField}">
	        	<a class="paixu01" href="javascript:sortQuery('5','0');" ><span style="text-decoration: underline;"><b>${LANG['system.site.list.effedate']}</b></span><img src="${baseUrlStatic}/images/paixuzong.png" width="6" height="13" /></a>
	        </c:if>
	        <c:if test="${5==sortField && 0==sortord}">
	        	<a class="paixu01" href="javascript:sortQuery('5','1');"><span style="text-decoration: underline;"><b>${LANG['system.site.list.effedate']}</b></span><img src="${baseUrlStatic}/images/paixu02.png" width="6" height="13" /></a>
	        </c:if>
	        <c:if test="${5==sortField  && 1==sortord}">
	        	<a class="paixu01" href="javascript:sortQuery('5','0');" ><span style="text-decoration: underline;"><b>${LANG['system.site.list.effedate']}</b></span><img src="${baseUrlStatic}/images/paixu01.png" width="6" height="13" /></a>
	        </c:if>
       		</div>
       	</td>
        <td width="12%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center">
        	<c:if test="${3!=sortField}">
	        	<a class="paixu01" href="javascript:sortQuery('3','0');" ><span style="text-decoration: underline;"><b>${LANG['system.site.list.ExpireDate']}</b></span><img src="${baseUrlStatic}/images/paixuzong.png" width="6" height="13" /></a>
	        </c:if>
	        <c:if test="${3==sortField && 0==sortord}">
	        	<a class="paixu01" href="javascript:sortQuery('3','1');" ><span style="text-decoration: underline;"><b>${LANG['system.site.list.ExpireDate']}</b></span><img src="${baseUrlStatic}/images/paixu02.png" width="6" height="13" /></a>
	        </c:if>
	        <c:if test="${3==sortField  && 1==sortord}">
	        	<a class="paixu01" href="javascript:sortQuery('3','0');" ><span style="text-decoration: underline;"><b>${LANG['system.site.list.ExpireDate']}</b></span><img src="${baseUrlStatic}/images/paixu01.png" width="6" height="13" /></a>
	        </c:if>
       		</div>
       	</td>
        <td width="9%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['system.site.list.UserName']}</b></span></div></td>
<%--<td width="10%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span>${LANG['system.site.list.Status']}</span></div></td> --%><%--
        <td width="7%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center">
        	<c:if test="${4!=sortField}">
	        	<a class="paixu01" href="javascript:sortQuery('4','0');"><span style="text-decoration: underline;"><b>${LANG['system.site.list.Status']}</b></span><img src="${baseUrlStatic}/images/paixuzong.png" width="6" height="13" /></a>
	        </c:if>
	        <c:if test="${4==sortField && 0==sortord}">
	        	<a class="paixu01" href="javascript:sortQuery('4','1');" ><span style="text-decoration: underline;"><b>${LANG['system.site.list.Status']}</b></span><img src="${baseUrlStatic}/images/paixu02.png" width="6" height="13" /></a>
	        </c:if>
	        <c:if test="${4==sortField  && 1==sortord}">
	        	<a class="paixu01" href="javascript:sortQuery('4','0');" ><span style="text-decoration: underline;"><b>${LANG['system.site.list.Status']}</b></span><img src="${baseUrlStatic}/images/paixu01.png" width="6" height="13" /></a>
	        </c:if>
       		</div>
       	</td>--%>
	    <td width="16%" height="38" bgcolor="d3eaef" class="STYLE10" class="STYLE_none" ><div align="center"><span><b>${LANG['system.Operate']}</b></span></div></td>
	       <c:if test="${user.sysType != 6 }">
     	<td width="10%" height="38" bgcolor="d3eaef" class="STYLE10" style="border-right:none"><div align="center"><span><b>${LANG['website.system.host.number']}</b></span></div></td>
     	</c:if>
     	   <c:if test="${user.sysType eq 6 }">
     	   <td width="13%" height="38" bgcolor="d3eaef" class="STYLE10" style="border-right:none"><div align="center"><span><b>${LANG['website.system.userlist.createtime']}</b></span></div></td>
     	   </c:if>
      </tr>
      
      <!-- 非客服 -->
     <c:if test="${user.sysType != 6 }">
       	 <c:if test="${fn:length(siteList)<=0 }">
         <tr>
        <td height="32" class="STYLE19" colspan="9"  align="center">
        	${LANG['website.common.msg.list.nodata']}
        </td>
      </tr>
        </c:if>
      <c:if test="${fn:length(siteList)>0 }">
     <c:forEach var= "siteBase" items = "${siteList}"  varStatus="status">
      <tr>
        <td height="32" class="STYLE19" align="left">
        	<c:choose>
	      			<c:when test="${myfn:siteExpired(siteBase.expireDate) }">
	      				<div style="margin-left: 10px;" title="${LANG['bizconf.jsp.system.site_list.res3']}" >
	      					<img src="/static/images/overdue.png" />
	      					<a onclick="viewSite(${siteBase.id})" href="javascript:;"><span>${siteBase.siteName}</span></a>
	      				</div>	
	      			</c:when>
	      			<c:when test="${myfn:expiredInWe(siteBase.expireDate) }">
	      				<div style="margin-left: 10px;"  title="${LANG['bizconf.jsp.system.site_list.res2']}" >
	      					<img src="/static/images/caution.png"/>
			        		<a onclick="viewSite(${siteBase.id})" href="javascript:;"><span>${siteBase.siteName}</span></a>
			        	</div>
	      			</c:when>
	      			<c:otherwise>
	      				<div style="margin-left: 10px;">
	      					<a onclick="viewSite(${siteBase.id})" href="javascript:;"><span>${siteBase.siteName}</span></a>
	      				</div>
	      			</c:otherwise>
		      	</c:choose>
        </td>
        <td height="32" class="STYLE19" b><div align="center">${siteBase.siteSign }</div></td>
        <%--<c:if test="${siteBase.siteFlag == 1 }">
			<td height="32" class="STYLE19"><div align="center">${LANG['system.site.list.SiteFlag.official']}</div></td>
		</c:if>
		<c:if test="${siteBase.siteFlag == 2 }">
			<td height="32" class="STYLE19"><div align="center">${LANG['system.site.list.SiteFlag.trial']}</div></td>
		</c:if>--%>
		<%--<c:set var="hireMode" value="site.hire.${siteBase.hireMode }"></c:set>
		<td height="32" class="STYLE19"><div align="center">${LANG[hireMode]}</div></td>
--%><!--		<c:if test="${siteBase.hireMode == 1 }">-->
<!--			<td height="32" class="STYLE19"><div align="center">${LANG['system.site.list.hireMode.month']}</div></td>-->
<!--		</c:if>-->
<!--		<c:if test="${siteBase.hireMode == 2 }">-->
<!--			<td height="32" class="STYLE19"><div align="center">${LANG['system.site.list.hireMode.minutes']}</div></td>-->
<!--		</c:if>-->
<!--		<c:if test="${siteBase.hireMode == null || siteBase.hireMode == 0}">-->
<!--			<td height="32" class="STYLE19"><div align="center">--</div></td>-->
<!--		</c:if>-->
        <td height="32" class="STYLE19"><div align="center">${myfn:fmtDate('yyyy-MM-dd',siteBase.effeDate,28800000)}</div></td>
        <td height="32" class="STYLE19"><div align="center">${myfn:fmtDate('yyyy-MM-dd',siteBase.expireDate,28800000)}</div></td>
        <td height="32" class="STYLE19"><div align="center" >${nameMap[siteBase.id]}</div></td>
	    <%--<c:if test="${siteBase.lockFlag == 1}"> 
			<td height="32" class="STYLE19"><div align="center" class="STYLE21">${LANG['system.site.list.Status.normal']}</a></span></div></td>
		</c:if>
		<c:if test="${siteBase.lockFlag == 2}"> 
			<td height="32" class="STYLE19"><div align="center" class="STYLE21">${LANG['system.site.list.Status.lock']}</a></span></div></td>
		</c:if>
        
--%><%--        <a href="javascript:hostManager(${siteBase.id});">host=${siteBase.chargeMode}</a>--%>
				<c:choose>
					<c:when test="${user.sysType gt 6 }">
						<td height="32" class="STYLE19">
	        				<div align="center" class="STYLE21" style="line-height: 20px">
	        					<a onclick="updateSite(${siteBase.id})" href="javascript:;">${LANG['system.change']}</a>
	        					<c:if test="${siteBase.expireDate > nowDate}">
	        							${LANG['system.delete']}
	        					</c:if>
	        					<c:if test="${siteBase.expireDate <= nowDate}">
	        							<a href="javascript:;" onclick='del("${siteBase.id}")'>${LANG['system.delete']}</a>
	        					</c:if>
								
								<c:if test="${siteBase.lockFlag == 2}"> 
									<a href="javascript:;" onclick='unlock("${siteBase.id}")'>${LANG['system.site.list.Status.unlock']}</a>
								</c:if>
								<c:if test="${siteBase.lockFlag == 1}"> 
									<a href="javascript:;" onclick='lock("${siteBase.id}")'>${LANG['system.site.list.Status.lock']}</a>
								</c:if>
				        		<a href="javascript:hostManager(${siteBase.id});">${LANG['website.admin.left.menu.user.manage'] }</a>
				        		<a href="javascript:void(0);" onclick="resetPwd(${siteBase.id},this);">${LANG['website.user.password.reset.password'] }</a>
<!-- 								<a href="javascript:;" onclick='sysAdminManage("${siteBase.id}")'>${LANG['bizconf.jsp.system.index.res4']}</a>&nbsp;&nbsp; -->
<!-- 								<a href="javascript:;" onclick='downloadSetting("${siteBase.id}")'>分配下载地址</a> -->
							</div>
       					</td>
					</c:when>
					<c:otherwise>
<%--						<a  href="javascript:;">${LANG['bizconf.jsp.system.site_list.res5']}</a>--%>
					</c:otherwise>
				</c:choose>
<%-- 				<a href="javascript:;"  >${LANG['system.manager']}</a> --%>
				<td height="32" class="STYLE19"><div align="center">${map[siteBase.id]}</div></td>
		
      </tr>
     </c:forEach>
     </c:if>
       </c:if>
    <!-- 客服 -->
     <c:if test="${user.sysType eq 6 }">
     	<c:if test="${fn:length(siteList)<=0 }">
         <tr>
	       <!-- 
	       	 <td height="32" class="STYLE19" colspan="9"  align="center">
	        	${LANG['website.common.msg.list.nodata']}
	        </td>
	        -->
	         <td height="32" class="STYLE19" colspan="7"  align="center">
	        	${LANG['website.system.title.tip11']}
	        </td>
	      </tr>
        </c:if>
      <c:if test="${fn:length(siteList)>0 }">
     <c:forEach var= "siteBase" items = "${siteList}"  varStatus="status">
      <tr>
        <td height="32" class="STYLE19" align="left">
        	<c:choose>
	      			<c:when test="${myfn:expiredInWe(siteBase.expireDate) }">
	      				<div style="margin-left: 10px;"  title="${LANG['bizconf.jsp.system.site_list.res2']}" >
	      					<img src="/static/images/caution.png"/>
			        		<a onclick="viewSite(${siteBase.id})" href="javascript:;"><span>${siteBase.siteName}</span></a>
			        	</div>
	      			</c:when>
	      			<c:when test="${myfn:siteExpired(siteBase.expireDate) }">
	      				<div style="margin-left: 10px;" title="${LANG['bizconf.jsp.system.site_list.res3']}" >
	      					<img src="/static/images/overdue.png" />
	      					<a onclick="viewSite(${siteBase.id})" href="javascript:;"><span>${siteBase.siteName}</span></a>
	      				</div>	
	      			</c:when>
	      			<c:otherwise>
	      				<div style="margin-left: 10px;">
	      					<a onclick="viewSite(${siteBase.id})" href="javascript:;"><span>${siteBase.siteName}</span></a>
	      				</div>
	      			</c:otherwise>
		      	</c:choose>
        </td>
        <td height="32" class="STYLE19" b><div align="center">${siteBase.siteSign }</div></td>
<!--		<c:if test="${siteBase.hireMode == 1 }">-->
<!--			<td height="32" class="STYLE19"><div align="center">${LANG['system.site.list.hireMode.month']}</div></td>-->
<!--		</c:if>-->
<!--		<c:if test="${siteBase.hireMode == 2 }">-->
<!--			<td height="32" class="STYLE19"><div align="center">${LANG['system.site.list.hireMode.minutes']}</div></td>-->
<!--		</c:if>-->
<!--		<c:if test="${siteBase.hireMode == null || siteBase.hireMode == 0}">-->
<!--			<td height="32" class="STYLE19"><div align="center">--</div></td>-->
<!--		</c:if>-->
        <td height="32" class="STYLE19"><div align="center">${myfn:fmtDate('yyyy-MM-dd',siteBase.effeDate,28800000)}</div></td>
        <td height="32" class="STYLE19"><div align="center">${myfn:fmtDate('yyyy-MM-dd',siteBase.expireDate,28800000)}</div></td>
        <td height="32" class="STYLE19"><div align="center" >${nameMap[siteBase.id]}</div></td>   
<%--        <a href="javascript:hostManager(${siteBase.id});">host=${siteBase.chargeMode}</a>--%>
		
						<td height="32" class="STYLE19">
	        				<div align="center" class="STYLE21" style="line-height: 20px">
				        		<a href="javascript:void(0);" onclick="resetPwd(${siteBase.id},this);">${LANG['website.user.password.reset.password'] }</a>
							</div>
       					</td>
<%-- 				<a href="javascript:;"  >${LANG['system.manager']}</a> --%>
						 <td height="32" class="STYLE19"><div align="center">${myfn:fmtDate('yyyy-MM-dd',siteBase.createTime,28800000)}</div></td>
      </tr>
     </c:forEach>
     </c:if>
     
     </c:if>
     
    </table>
  </tr>
  <tr>
    <td class="table_bottom" height="38">
    <jsp:include page="/jsp/common/page_info.jsp" />
<!--     <table width="100%" border="0" cellspacing="0" cellpadding="0"> -->
<!--       <tr> -->
<%--         <td><div><span width="33%" class="STYLE22" align="left">&nbsp;&nbsp;&nbsp;&nbsp;${LANG['bizconf.jsp.system.site_info.res8']} <strong>${pageModel.pageCount}</strong> ${LANG['bizconf.jsp.system.site_list.res6']},${LANG['bizconf.jsp.system.site_info.res8']}有<strong>${pageModel.rowsCount}</strong> ${LANG['bizconf.jsp.system.site_list.res7']}</span></div></td> --%>
<!--         <td width="67%"><table width="420" border="0" align="right" cellpadding="0" cellspacing="0" > -->
<!--           <tr> -->
<!--             <td><div align="center" class="page_shouye"><a href="#">${LANG['bizconf.jsp.system.site_info.res10']}</a></div></td> -->
<!--             <td><div align="center" class="page_next"><a href="#">${LANG['bizconf.jsp.system.site_info.res13']}</a></div></td> -->
<!--             <td><div align="center" class="page_shuzi"><a href="#">1</a>&nbsp;&nbsp;<a href="#">2</a>&nbsp;&nbsp;<a href="#">3</a>&nbsp;&nbsp;<a href="#">4</a>&nbsp;&nbsp;<a href="#">5</a></div></td> -->
            
<!--             <td><div class="page_next"><a href="#">${LANG['bizconf.jsp.system.site_info.res13']}</a></div></td> -->
<!--             <td><div class="page_shouye"><a href="#">${LANG['bizconf.jsp.system.site_info.res11']}</a></div></td> -->
<!--             <td class="STYLE22"><div class="page_shouye"><a href="#">${LANG['bizconf.jsp.system.site_list.res8']}</a></div></td> -->
<!--             <td><div align="center"> -->
<!--             <input type="text" name="textfield" id="textfield"  style="width:22px; height:16px; font-size:12px; border:solid 1px #CACACA; line-height:16px;"/> -->
<!--             </div></td> -->
<!--             <td class="STYLE22">${LANG['bizconf.jsp.system.site_list.res6']}</div></td> -->
<!--             <td class="STYLE22"><div class="page_shouye01">${LANG['bizconf.jsp.system.site_list.res9']}</div></td> -->
<!--            </tr>   -->
<!--            </table>   -->
<!--         </td>  -->
<!--        </td> -->
<!--       </tr> -->
<!--     </table> -->
    </td>
  </tr>
</table>
</div>
</form>
</body>
</html>


