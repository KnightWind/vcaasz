<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>base info</title>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/enterprise/reset.css"/>
<link rel="stylesheet" type="text/css" href="/static/js/tipsy-master/src/stylesheets/tipsy.css" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery.uniform/themes/default/css/uniform.custom.css">
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/enterprise/rightbox.css"/>
<SCRIPT type="text/javascript" src="/static/js/jquery-1.8.3.js"></SCRIPT>
<script type="text/javascript" src="/static/js/jquery-validation-1.10.0/dist/jquery.validate.js"></script>
<script type="text/javascript" src="/static/js/tipsy-master/src/javascripts/jquery.tipsy.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery.uniform/jquery.uniform.js"></script>
<script type="text/javascript">
$(function() {
	$("#profileForm").find("input, select").not(".skipThese").uniform();
	$("#profileForm :input").tipsy({ trigger: 'manual', fade: false, gravity: 'sw', opacity: 1 });
	var ruleString = {
		required: {
			"siteName": "${LANG['system.site.list.CompanyName.input']}",
			"enName": "${LANG['system.site.list.EnglishName.input']}"
		},
		rangelength: {
			"siteName": "${LANG['system.site.list.siteName.rangelength']}",
			"enName": "${LANG['system.site.list.enName.rangelength']}"
		},
		custom: {
			"checkSiteName": "${LANG['system.site.list.checkSiteName.custom']}",
			"checkChName": "${LANG['system.site.list.checkChName.custom']}",
			"checkEnName": "${LANG['system.site.list.checkEnName.custom']}"
		}
	};		
	$.validator.addMethod("checkSiteName", function(value, element) {       
    	return this.optional(element) || /^[a-zA-Z0-9_\-&\u4e00-\u9fa5]{4,32}$/.test(value);
 	}, ruleString.custom.checkSiteName);
	
	$.validator.addMethod("checkEnName", function(value, element) {       
    	return this.optional(element) || /^[a-zA-Z0-9_\s\-&,.]{1,64}$/.test(value);
 	}, ruleString.custom.checkEnName);
	
	$.validator.addMethod("notChName", function(value, element) {       
    	return this.optional(element) || /^[^\u4e00-\u9fa5]+$/.test(value);
 	}, ruleString.custom.checkChName);
	
	var v = $("#profileForm").validate({
		onkeyup: false,
		errorClass: "warning",
		rules: {
            'siteName' : {required:true, rangelength: [1, 32]},
            'enName' : {required:true, rangelength: [1, 64], notChName:true}
        },
        messages: {
            'siteName' : {required:ruleString.required.siteName, rangelength: ruleString.rangelength.siteName},
            'enName' : {required:ruleString.required.siteName, rangelength: ruleString.rangelength.enName}
        },
        success: function (label) {
            $(label).each(function () {
                $('#' + this.htmlFor).tipsy('hide').removeAttr('original-title');
            });
        },
        errorPlacement: function (error, element) {
        	var errorEl = $(".tipsy");
        	var errorText = error.text();
        	if (!errorEl || errorEl.length==0) {
	            element.attr('original-title', errorText);
                element.tipsy('show');	
        	} else {
        		//for update first tip div title
	        	var elTitle = element.attr('original-title');
	        	if (elTitle && elTitle.length>0 && elTitle!=errorText) {
	        		element.attr('original-title', error.text());
	                element.tipsy('show');	
	        	}
        	}
        }
	});
	
	$("input[name='backBanner']").change(function() {
		var value = $(this).val();
		var checked = $(this).attr("checked");
		if(checked=="checked"){
			$("#siteBanner").val("");
		} else {
			$("#siteBanner").val(value);
		}
		$.uniform.update();
	});
	
	$("input[name='backLogo']").change(function() {
		var value = $(this).val();
		var checked = $(this).attr("checked");
		if(checked=="checked"){
			$("#siteLogo").val("");
		} else {
			$("#siteLogo").val(value);
		}
		$.uniform.update();
	});
});
</script>
<script type="text/javascript">
function uploadCallback(url){
	$("#previewLoadImg").hide();
	$("#frameImg").attr("src", "/jsp/common/upload_common.jsp");
	if(url && url.length>0){
		$("#previewImg").attr("src", "/uploadfiles/site_logo/" + url);
		$("#siteLogo").val("/uploadfiles/site_logo/" + url);
		$("#backLogo").val("/uploadfiles/site_logo/" + url);
	}
}

function uploadCallbackBanner(url) {
	$("#loadImgBanner").hide();
	$("#frameImgBanner").attr("src", "/jsp/common/upload_common.jsp?type=Banner");
	if(url && url.length>0){
		$("#bannerImg").attr("src", "/uploadfiles/site_logo/" + url);
		$("#siteBanner").val("/uploadfiles/site_logo/" + url);
		$("#backBanner").val("/uploadfiles/site_logo/" + url);
	}
}
</script>
</head>
<body>
<div class="y_emile">
<div class="y_emile_01">
   	<div class="y_emile_01_top"><span>${LANG['system.site.list.CompanyInfo']}：</span></div>
   	<cc:siteList var="TIMEZONE_WITH_CITY_LIST"/>
   	<form name="profileForm" id="profileForm" action="/admin/site/update" method="post">
		<table class="form-table" style="margin-left:60px">
		  <tr>
		    <td align="right">
		    	${LANG['system.site.list.CompanyName']}
		    </td>
		    <td class="form-table-td">
		    	<input type="hidden" id="siteId" name="id" value="${siteBase.id }"/>
		    	<input id="siteName" name="siteName" class="text-input" type="text" value="${siteBase.siteName }"/>
		    </td>
		  </tr>
		  <tr>
		    <td align="right">
		    	${LANG['system.site.list.EnglishName']}
		    </td>
		    <td class="form-table-td">
		    	<input id="enName" name="enName" class="text-input" type="text" value="${siteBase.enName }"/>
		    </td>
		  </tr>
		  <tr>
		    <td align="right">
		    	${LANG['system.site.list.timeZoneSet']}
		    </td>
		    <td class="form-table-td">
		    	<select name="timeZoneId"  class="" >
	        		<c:forEach var="timeZoneInfo"  items="${TIMEZONE_WITH_CITY_LIST}"  varStatus="status">
	        		<c:set  var="tzlang" value="website.timezone.city.${timeZoneInfo.timeZoneId}"/>
	        		<c:set var="statusStr" value=""/>
	        		<c:if test="${timeZoneInfo.timeZoneId==siteBase.timeZoneId}"> <c:set var="statusStr" value=" selected "/></c:if>
	        			<option value="${timeZoneInfo.timeZoneId}" ${statusStr}>${LANG[tzlang]}</option>
	        		</c:forEach>
        		</select>
		    </td>
		  </tr>
		  <tr style="display:${(!empty siteBase.siteDiy && siteBase.siteDiy==1) ? '':'none'}">
		    <td align="right">
		    	${LANG['system.site.list.CompanyLogo']}
		    </td>
		    <td class="form-table-td">
		    	<div style="position: relative;">
		    		<iframe style="float: left;" id="frameImg" src="/jsp/common/upload_common.jsp" width="230" height="25" frameborder="0" scrolling="no"></iframe>
	            	<div style="color: red; float: left; height: 25px; line-height: 25px;">${LANG['system.site.list.imageLimit']}</div>
	            	<div style="clear: both;"></div>
		    	</div>
		    </td>
		  </tr>
		  <tr style="display:${(!empty siteBase.siteDiy && siteBase.siteDiy==1) ? '':'none'}">
		    <td align="right">
		    	
		    </td>
		    <td class="form-table-td">
		    	<div style="height: 60px;position: relative;">
		    		<c:choose>
					   <c:when test="${!empty siteBase.siteLogo}">
					   <img id="previewImg" src="${siteBase.siteLogo}" style="height:48px;"  alt="${siteBase.siteLogo}"/>
					   </c:when>
					   <c:otherwise>
						<img id="previewImg" src="/static/images/logo.png" style="height:48px;"  alt=""/>		
					   </c:otherwise>
					</c:choose>      
					<img id="previewLoadImg" src="/static/images/loading.gif" style="display: none;">
					<input id="siteLogo" name="siteLogo" type="hidden" value="${siteBase.siteLogo}"/>
		    	</div>
		    	<div style="height: 25px; line-height: 25px;"><input id="backLogo" name="backLogo" type="checkbox" value=""/>${LANG['bizconf.jsp.system.logo.reset']}</div>
		    </td>
		  </tr>
		  
		  <tr style="display:${(!empty siteBase.siteDiy && siteBase.siteDiy==1) ? '':'none'}">
		    <td align="right">
		    	${LANG['bizconf.jsp.system.banner.image']}
		    </td>
		    <td class="form-table-td">
		    	<div style="position: relative;">
		    		<iframe style="float: left;" id="frameImgBanner" src="/jsp/common/upload_common.jsp?type=Banner" width="230" height="25" frameborder="0" scrolling="no"></iframe>
	            	<div style="color:red;float: left; height: 25px; line-height: 25px;">${LANG['bizconf.jsp.system.banner.support']}</div>
	            	<div style="clear: both;"></div>
		    	</div>
		    </td>
		  </tr>
		  <tr style="display:${(!empty siteBase.siteDiy && siteBase.siteDiy==1) ? '':'none'}">
		    <td align="right">
		    	
		    </td>
		    <td class="form-table-td">
		    	<div style="height: 60px;position: relative;width: 590px;overflow: hidden;">
		    		<c:choose>
					   <c:when test="${!empty siteBase.siteBanner}">
					   <img id="bannerImg" src="${siteBase.siteBanner}" style="height:48px;"  alt="${siteBase.siteBanner}"/>
					   </c:when>
					   <c:otherwise>
						<img id="bannerImg" src="/static/images/logo_d_s.png" style="height:48px;"  alt=""/>		
					   </c:otherwise>
					</c:choose>
					<img id="loadImgBanner" src="/static/images/loading.gif" style="display: none;height: 32px;left: 20px;position: absolute;top: 5px;width: 32px;">      
					<input id="siteBanner" name="siteBanner" type="hidden" value="${siteBase.siteBanner}"/>
		    	</div>
		    	<div style="height: 25px; line-height: 25px;"><input id="backBanner" name="backBanner" type="checkbox" value=""/>${LANG['bizconf.jsp.system.banner.reset']}</div>
		    </td>
		  </tr>
		</table>    	
   		<div>
   			<input  class="skipThese" name="emile_button" id="y_emile_button" type="submit"  value="${LANG['system.submit']}" />
           	<c:if test="${msgCode!=null && msgCode!='' && msgCode=='1'}">
           		<img src="/static/images/ys_r_bg.jpg" width="16" height="14" /><label style='color:#258021'>修改成功</label>
           		<script type="text/javascript">
					function refreshLogo() {
						var url = $("#previewImg").attr("src");
						parent.$("#logo_icon").attr("src", url);
					}
					refreshLogo();
				</script>
           	</c:if>
           	<c:if test="${msgCode!=null && msgCode!='' && msgCode=='2'}">
           		<img src="/static/images/ys_w_bg.jpg" width="16" height="14" /><label style='color:#258021'>修改失败</label>
           	</c:if>
   		</div>
	</form>
	</div>
    	
     <div class="y_emile_02">     
     	<div class="y_emile_02_top"><span>${LANG['system.site.list.AuthorizationInfo']}：</span></div>
        <ul class="y_emile_02_bottom">
        	<cc:siteList var="SITE_HIREMODE_MONTH"/>
        	<cc:siteList var="SITE_HIREMODE_TIME"/>
        	<c:choose>
	         <c:when test="${SITE_HIREMODE_MONTH == siteBase.hireMode }">
				<li><span>${LANG['system.site.list.hireMode']}：</span>${LANG['system.site.list.hireMode.month']}</li>
	      	 </c:when>
	         <c:when test="${SITE_HIREMODE_TIME == siteBase.hireMode }">
				<li><span>${LANG['system.site.list.hireMode']}：</span>${LANG['system.site.list.hireMode.minutes']}</li>
	      	 </c:when>
	       	 <c:otherwise>
	            <li><span>${LANG['system.site.list.hireMode']}：</span>--</li>
	       	 </c:otherwise>
			</c:choose>
        	<cc:siteList var="SITE_CHARGEMODE_NAMEHOST"/>
        	<cc:siteList var="SITE_CHARGEMODE_ACTIVEUSER"/>
        	<cc:siteList var="SITE_CHARGEMODE_SEATES"/>
        	<cc:siteList var="SITE_CHARGEMODE_TIME"/>
        	<cc:siteList var="SITE_CHARGEMODE_USERTIME"/>
			<c:choose>
			 <c:when test="${SITE_CHARGEMODE_NAMEHOST == siteBase.chargeMode }">
                <li><span>${LANG['system.site.list.chargeMode']}：</span>${LANG['system.site.list.chargeMode.Name Host']}</li>
			 </c:when>
			 <c:when test="${SITE_CHARGEMODE_ACTIVEUSER == siteBase.chargeMode }">
                <li><span>${LANG['system.site.list.chargeMode']}：</span>${LANG['system.site.list.chargeMode.Active User']}</li>
			 </c:when>
			 <c:when test="${SITE_CHARGEMODE_SEATES == siteBase.chargeMode }">
                <li><span>${LANG['system.site.list.chargeMode']}：</span>${LANG['system.site.list.chargeMode.Seats']}</li>
			 </c:when>
			 <c:when test="${SITE_CHARGEMODE_TIME == siteBase.chargeMode }">
                <li><span>${LANG['system.site.list.chargeMode']}：</span>${LANG['system.site.list.chargeMode.time']}</li>
			 </c:when>
			 <c:when test="${SITE_CHARGEMODE_USERTIME == siteBase.chargeMode }">
                <li><span>${LANG['system.site.list.chargeMode']}：</span>${LANG['system.site.list.chargeMode.usertime']}</li>
			 </c:when>
 	       	 <c:otherwise>
	            <li><span>${LANG['system.site.list.chargeMode']}：</span>--</li>
	       	 </c:otherwise>
			</c:choose>
<!--            <li><span>${LANG['system.site.list.LoginName']}：</span>${siteAdmin.loginName }</li>-->
<!--            <li><span>${LANG['system.site.list.Email']}：</span>${siteAdmin.userEmail }</li>-->
<!--            <li><span>${LANG['system.site.list.License']}：</span>${siteBase.license }</li>-->
<!--            <li><span>${LANG['system.site.list.effedate']}：</span><fmt:formatDate  value="${siteBase.effeDate}" type="date" pattern="yyyy-MM-dd"/></li>-->
           
            <cc:siteList var="SITEFLAG_FORMAL"/>
            <cc:siteList var="SITEFLAG_TRY"/>
            <c:choose>
             <c:when test="${SITEFLAG_FORMAL == siteBase.siteFlag}">
             	<li><span>${LANG['system.site.list.SiteFlag']}：</span>${LANG['system.site.list.SiteFlag.official']}</li>
             </c:when>
             <c:when test="${SITEFLAG_TRY == siteBase.siteFlag}">
             	<li><span>${LANG['system.site.list.SiteFlag']}：</span>${LANG['system.site.list.SiteFlag.trial']}</li>
             </c:when>
     	     <c:otherwise>
	            <li><span>${LANG['system.site.list.SiteFlag']}：</span>--</li>
	       	 </c:otherwise>
            </c:choose>
             <li><span>${LANG['system.site.list.siteExpireDate']}：</span><fmt:formatDate  value="${siteBase.expireDate}" type="date" pattern="yyyy-MM-dd"/></li>
            
            
            
            
            
			 <c:if test="${SITE_CHARGEMODE_NAMEHOST == siteBase.chargeMode }">
                <li><span>${LANG['system.site.list.hostInfo']}：</span>
                <table border="0" cellpadding="0" cellspacing="0" class="host_top" style="position:relative; top: 15px;">
					<tr height="30" class="host01">
				    	<td width="100" align="center">${LANG['system.site.list.LoginName']}</td>
				        <td width="150" align="center">${LANG['system.site.list.Email']}</td>
				        <td width="80" align="center">${LANG['system.site.list.siteInfo.license']}</td>
				        <td width="80" align="center">${LANG['system.site.list.effedate']}</td>
				        <td width="80" align="center">${LANG['system.site.list.ExpireDate']}</td>
				    </tr>
				    <c:if test="${fn:length(pageModel.datas)<=0 }">
				         <tr  height="36" class="host02">
				           <td colspan="4" width="540" align="center">
				        		${LANG['website.common.msg.list.nodata']}
				           </td>
				         </tr>
				      </c:if>
				    <c:forEach var="host" items = "${pageModel.datas}"  varStatus="status">
					    <tr height="36" class="host02">
					    	<td width="100" align="center" >${host.loginName}</td>
					        <td width="150" align="center">${host.userEmail}</td>
					        <td width="80" align="center" cla>${licnums[host.id]}</td>
					        <td width="80" align="center" cla><fmt:formatDate  value="${effeDates[host.id]}" type="date" pattern="yyyy-MM-dd"/></td>
					        <td width="80" align="center" cla><fmt:formatDate  value="${siteBase.expireDate}" type="date" pattern="yyyy-MM-dd"/></td>
					    </tr>
				    </c:forEach>
				</table>
                </li>
				  
			 </c:if>
            
            
            
        </ul>
	</div>      
</div>
</body>
</html>
