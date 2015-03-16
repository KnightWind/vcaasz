<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>${LANG['system.site.list.create']}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- Css -->	
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/reset.css" />	
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/tipsy-master/src/stylesheets/tipsy.css" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery.uniform/themes/default/css/uniform.custom.css">
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/common.css"/>
<style type="text/css">
.table-step {
	margin:15px auto;
}
.table-step td{
	height: 33px;
}
.step-head-img {
	text-align: center; 
	height: 32px; 
	margin-top: 2px;
}
.step-head-txt {
	height: 20px
}
.step-input {
	width: 290px;
	height: 18px;
}
.step-td{
	padding-left: 10px;
}
.step-bottom {
	bottom: 0;
    position: absolute;
    width: 100%;
    height: 45px;
}
.step-buttons {
	width: 400px;
	margin: 0px auto;
    text-align: right;
}
#previewImg {
	height:48px;position: absolute;
}
#previewLoadImg {
	width:32px;height:32px;position: absolute; left: 20px; top:5px;display: none;
}
.submitbutton, .nextbutton, .prevbutton, .closeButton, .updateButton {
	background: url("/static/images/button01.jpg") no-repeat center center;
    background-color: #663399;
    border: 0px;
    color: #333333;
    cursor: pointer;
    height: 24px;
    line-height:24px;
    margin: 7px;
    text-align: center;
    width: 72px;
}
.site_nav {margin: 0 15px;}
.nav_1{  background:url(/static/images/nav_bg.png) left center no-repeat;}
.nav_5{  background:url(/static/images/nav_bg.png) right center no-repeat;}
.nav_3{ background:url(/static/images/nav_center_bg.png) left center repeat-x;}
.nav_2{ background:url(/static/images/nav_center_bg.png) left center repeat-x;}
.nav_4{ background:url(/static/images/nav_center_bg.png) left center repeat-x;}
.menu_text{ color:#858585;}
.menu_text td{ padding-top:3px;}
</style>

<%@ include file="/jsp/common/cookie_util.jsp"%>
<!-- Javascript -->
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom.js"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/development-bundle/ui/minified/i18n/jquery-ui-i18n.min.js"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-validation-1.10.0/dist/jquery.validate.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/tipsy-master/src/javascripts/jquery.tipsy.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery.uniform/jquery.uniform.js"></script>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/json2.js"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/widgets.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/util.js"></script>
<SCRIPT type="text/javascript" src="/assets/js/apps/biz.ValidatePass.js"></SCRIPT>
</head>
<body onload="loaded()">
<cc:siteList var="TIMEZONE_WITH_CITY_LIST"/>
<cc:siteList var="EMPOWER_CODE_FIELD_ARRAY"/>
<cc:siteList var="EMPOWER_CODE_VIDEO"/>
<cc:siteList var="EMPOWER_CODE_AUDIO"/>
<cc:siteList var="EMPOWER_CODE_DPI"/>
<cc:siteList var="EMPOWER_ENABLED"/>
<cc:siteList var="SITE_CREATE_MIN_LICENSE"/>
<cc:siteList var="SITE_CREATE_MAX_LICENSE"/>
<cc:siteList var="SITE_CREATE_MIN_SYNCNUM"/>
<cc:siteList var="SITE_CREATE_MAX_SYNCNUM"/>

<div style="margin: 0px auto;">
<form name="createSiteForm" id="createSiteForm" action="" method="post">
<input type="hidden" name="recordRequestPrimaryServiceID" id="recordRequestPrimaryServiceID" value="100" />
<input type="hidden" name="recordClientServices" id="recordClientServices" value="1,3" />
<ul id="stepForm" class="ui-accordion-container">
	<!-- 站点基本信息 -->
	<li id="sf1" class='step'>
		<table cellpadding="0" cellspacing="0" border="0" class="site_nav" >
		  <tr height="40" align="center">
		    <td width="120" class="nav_1"><img src="/static/images/ico01_hover.png" width="34" height="32" /></td>
		    <td width="120" class="nav_2"><img src="/static/images/ico02.png" width="34" height="32" /></td>
		    <td width="120" class="nav_3"><img src="/static/images/ico03.png" width="34" height="32" /></td>
		    <td width="120" class="nav_4"><img src="/static/images/ico04.png" width="34" height="32" /></td>
<!-- 		    <td width="120" class="nav_5"><img src="/static/images/ico05.png" width="34" height="32" /></td> -->
		  </tr>
		  <tr height="30" align="center" valign="top" class="menu_text">
		    <td>${LANG['system.site.list.CompanyInfo']}</td>
<!-- 		    <td>${LANG['system.site.list.AuthorizInfo']}</td> -->
		    <td>${LANG['system.site.list.BusinessInfo']}</td>
		    <td>${LANG['system.site.list.AdminInfo']}</td>
		    <td>${LANG['system.site.list.Complete']}</td>
		  </tr>
		</table>
		<div class="clearfix"></div>
		<table class="table-step">
			<tr>
				<td align="right">
					<span class='red_star'>*</span>${LANG['system.site.list.CompanyName']}
				</td>
				<td class="step-td">
					<input class="step-input" type="text" name="siteName" id="siteName"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					${LANG['system.site.list.EnglishName']}			
				</td>
				<td class="step-td">
					<input class="step-input" type="text" name="enName" id="enName"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					<span class='red_star'>*</span>${LANG['system.site.list.SiteSign']}
				</td>
				<td class="step-td">
					<input class="step-input" type="text" name="siteSign" id="siteSign"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					<span class='red_star'>*</span>${LANG['bizconf.jsp.system.createSite.res2']}
				</td>
				<td class="step-td">
					<select id="timeZoneId" name="timeZoneId">
						<c:forEach var="eachTimeZone" items="${TIMEZONE_WITH_CITY_LIST}">
						<c:set var="eachLang" value="website.timezone.city.${eachTimeZone.timeZoneId}"/>
						<option value="${eachTimeZone.timeZoneId}" timeZone="${eachTimeZone.offset}" <c:if test="${eachTimeZone.timeZoneId==44 }">selected="selected"</c:if> >${LANG[eachLang]} </option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right">
					${LANG['system.site.list.CompanyLogo']}
				</td>
				<td class="step-td">
					<iframe id="logoIframe" src="/jsp/common/upload_common.jsp" width="230" height="25" frameborder="0" scrolling="no"></iframe>
				</td>
			</tr>
			<tr>
				<td></td>
				<td class="step-td">
					<div style="position: relative; display: inline-block; height: 60px;">
						<c:if test="${!empty siteBase}">
							<c:if test="${!empty siteBase.siteLogo}">
					   		<img id="previewImg" src="${siteBase.siteLogo}" alt="${siteBase.siteLogo}"/>
					   		</c:if>
					   		<c:if test="${empty siteBase.siteLogo}">
					   			<img id="previewImg" src="/static/images/logo.png" alt=""/>
					   		</c:if>
					   	</c:if>
					   	<c:if test="${empty siteBase}">
							<img id="previewImg" src="/static/images/logo.png" alt=""/>			   
					   	</c:if>
					   	<img id="previewLoadImg" src="/static/images/loading.gif">
					   	<div style="color: red;width: 200px; margin-left: 110px;">${LANG['bizconf.jsp.system.createSite.res3000']}</div>
					</div>
				</td>
			</tr>
			<tr>
				<td align="right">
					${LANG['system.site.list.SiteFlag']}
				</td>
				<td class="step-td">
					<input name="siteFlag" type="radio" value="1" checked="checked"/>
					<label>${LANG['system.site.list.SiteFlag.official']}</label>
					<input name="siteFlag" type="radio" value="2" />
					<label>${LANG['system.site.list.SiteFlag.trial']}</label>
				</td>
			</tr>
		</table>
		<div class="step-bottom">
			<div class="step-buttons">
				<c:if test="${!empty siteBase}">
					<input type="button" class="updateButton skipThese" value="${LANG['system.complete']}" />
				</c:if>	
				<input type="button" class="nextbutton skipThese" value="${LANG['system.next']}" pageIndex="1"/>
				<input type="button" class="closeButton skipThese" value="${LANG['system.cancel']}" />
			</div>
		</div>
	</li>
	<!-- 授权信息 -->
	
	<!-- 商务信息 -->
	<li id="sf2" style="display: none" class='step'>
		<table cellpadding="0" cellspacing="0" border="0" class="site_nav" >
		  <tr height="40" align="center">
		  
		  	<td width="120" class="nav_1"><img src="/static/images/ico01_hover.png" width="34" height="32" /></td>
		    <td width="120" class="nav_2"><img src="/static/images/ico02_hover.png" width="34" height="32" /></td>
		    <td width="120" class="nav_3"><img src="/static/images/ico03.png" width="34" height="32" /></td>
		    <td width="120" class="nav_4"><img src="/static/images/ico04.png" width="34" height="32" /></td>
<!-- 		    <td width="120" class="nav_5"><img src="/static/images/ico05.png" width="34" height="32" /></td> -->
		  </tr>
		  <tr height="30" align="center" valign="top" class="menu_text">
		    <td>${LANG['system.site.list.CompanyInfo']}</td>
<!-- 		    <td>${LANG['system.site.list.AuthorizInfo']}</td> -->
		    <td>${LANG['system.site.list.BusinessInfo']}</td>
		    <td>${LANG['system.site.list.AdminInfo']}</td>
		    <td>${LANG['system.site.list.Complete']}</td>
		  </tr>
		</table>	
		<table class="table-step">
			<tr>
				<td align="right">${LANG['bizconf.jsp.conf_default_setup.banner']}</td>
				<td class="step-td">
					<input type="checkbox" value="0" name="siteDiy" id="siteDiy"  <c:if test="${siteBase.siteDiy==1}"> checked </c:if> />
					<label for="">${LANG['bizconf.jsp.conf_default_setup.bannerlogo']}</label>
				</td>
			</tr>
			<tr>
				<td align="right">${LANG['system.site.list.hireMode']}</td>
				<td class="step-td">
					<div style="width: 310px;">
						<input name="hireMode" type="radio" value="1" checked="checked"/>
							<label>${LANG['system.site.list.hireMode.month']}</label>
						<input name="hireMode" type="radio" value="2"/>
							<label>${LANG['bizconf.jsp.system.createSite.res8']}</label>
					</div>						
				</td>
			</tr>
			<input id="chargeMode" type="hidden" value="1"  checked="checked"/>
			<!-- <tr class="licenseTR">
				<td align="right">
					<span class='red_star'>*</span>${LANG['system.site.list.License']}
				</td>
				<td class="step-td">
					<input class="" style="width: 100px" type="text" name="license" id="license"/>
				</td>
			</tr> -->
			
			<!-- <tr class="confNumTR">
				<td align="right">${LANG['system.site.empower.item.18']}</td>
				<td class="step-td">
					<input type="text" style="width:100px;" value="0" name="syncConfNum" id="syncConfNum" class="text" />
					<label for="">${LANG['bizconf.jsp.create_Reservation_Conf.res89']}&nbsp;${LANG['system.site.empower.item.sync.max']}</label>
				</td>
			</tr> -->
			<tr>
				<td align="right">
					<span class='red_star'>*</span>${LANG['system.site.list.effedate']}
				</td>
				<td class="step-td">
					<div style="position: relative; width: 110px">
						<input style="width: 100px" type="text" name="effeDate" id="effeDate"/>
					</div>
				</td>
			</tr>
			<tr>
				<td align="right">
					<span class='red_star'>*</span>${LANG['system.site.list.ExpireDate']}
				</td>
				<td class="step-td">
					<div style="position: relative; width: 110px">
						<input style="width: 100px" type="text" name="expireDate" id="expireDate"/>
					</div>
				</td>
			</tr>
			<tr>
				<td align="right">
					${LANG['website.system.title.tip3']}
				</td>
				<td class="step-td">
					<input class="step-input" type="text" name="marketorEmail" id="marketorEmail"/>
				</td>
			</tr>	
		</table>
		<div class="step-bottom">
			<div class="step-buttons">
				<input type="button" class="prevbutton skipThese" value="${LANG['system.back']}" pageIndex="0"/>
				<c:if test="${!empty siteBase}">
					<input type="button" class="updateButton skipThese" value="${LANG['system.complete']}" />
				</c:if>
				<input type="button" class="nextbutton skipThese" value="${LANG['system.next']}" pageIndex="2"/>
				<input type="button" class="closeButton skipThese" value="${LANG['system.cancel']}"/>
			</div>		
		</div>		
	</li>
	<!-- 站点管理员信息 -->
	<li id="sf3" style="display: none" class='step'>
		<table cellpadding="0" cellspacing="0" border="0" class="site_nav">
		  <tr height="40" align="center">
		  
		  	<td width="120" class="nav_1"><img src="/static/images/ico01_hover.png" width="34" height="32" /></td>
		    <td width="120" class="nav_2"><img src="/static/images/ico02_hover.png" width="34" height="32" /></td>
		    <td width="120" class="nav_3"><img src="/static/images/ico03_hover.png" width="34" height="32" /></td>
		    <td width="120" class="nav_4"><img src="/static/images/ico04.png" width="34" height="32" /></td>
<!-- 		    <td width="120" class="nav_5"><img src="/static/images/ico05.png" width="34" height="32" /></td> -->
		  </tr>
		  <tr height="30" align="center" valign="top" class="menu_text">
		    <td>${LANG['system.site.list.CompanyInfo']}</td>
<!-- 		    <td>${LANG['system.site.list.AuthorizInfo']}</td> -->
		    <td>${LANG['system.site.list.BusinessInfo']}</td>
		    <td>${LANG['system.site.list.AdminInfo']}</td>
		    <td>${LANG['system.site.list.Complete']}</td>
		  </tr>
		</table>
		<table class="table-step">
			<tr>
				<td align="right">
					<span class='red_star'>*</span>${LANG['system.site.list.UserName']}
				</td>
				<td class="step-td">
					<input class="step-input" type="text" name="trueName" id="trueName"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					${LANG['system.site.list.EnName']}
				</td>
				<td class="step-td">
					<input class="step-input" type="text" name="userEnName" id="userEnName"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					<span class='red_star'>*</span>${LANG['system.site.list.LoginName']}
				</td>
				<td class="step-td">
					<input class="step-input" type="text" name="loginName" id="loginName"/>
				</td>
			</tr>
			<c:if test="${empty siteBase}">
			<tr>
				<td align="right">
					生成密码
				</td>
				<td class="step-td">
					<input class="step-input" type="checkbox" name="randomPwd" id="randomPwd"/>
				</td>
			</tr>
			</c:if>
			<!-- 密码和确认密码 -->
			<tr>
				<td align="right">
					<span class='red_star'>*</span>${LANG['system.site.list.Password']}
				</td>
				<td class="step-td">
					<input class="step-input" type="password" name="loginPass" id="loginPass"/>
					<input class="step-input" type="text" name="loginPassT" id="loginPassT" style="display: none;"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					<span class='red_star'>*</span>${LANG['system.user.confirmPass']}
				</td>
				<td class="step-td">
					<input class="step-input" type="password" name="confirmPass" id="confirmPass" />
					<input class="step-input" type="text" name="confirmPassT" id="confirmPassT" style="display: none;"/>
				</td>
			</tr>
			
			<tr>
				<td align="right">
					<span class='red_star'>*</span>${LANG['system.site.list.Email']}
				</td>
				<td class="step-td">
					<input class="step-input" type="text" name="userEmail" id="userEmail"/>
				</td>
			</tr>			
			<tr>
				<td align="right">
					${LANG['system.site.list.Phone']}
				</td>
				<td class="step-td">
					<input class="step-input" type="text" name="mobile" id="mobile"/>
				</td>
			</tr>
			<tr>
				<td align="right" valign="top">
					${LANG['system.site.list.Remark']}
				</td>
				<td class="step-td" style="height: 65px;">
					<textarea name="remark" id="remark" style="width: 290px; height:50px;resize:none;"></textarea>
				</td>
				<div id="remarkFlag" style="display: none;">${siteAdmin.remark}</div>
			</tr>
		</table>
		<div class="step-bottom">
			<div class="step-buttons">
				<input type="button" class="prevbutton skipThese" value="${LANG['system.back']}" pageIndex="1"/>
				<input type="submit" class="submitbutton skipThese" value="${LANG['system.complete']}"/>
				<input type="button" class="closeButton skipThese" value="${LANG['system.cancel']}"/>
			</div>
		</div>
	</li>
</ul>
</form>
</div>
</body>
</html>
<fmt:formatDate var="effeDate" value="${siteBase.effeDate}" type="date" pattern="yyyy-MM-dd"/>
<fmt:formatDate var="expireDate" value="${siteBase.expireDate}" type="date" pattern="yyyy-MM-dd"/>
<c:if test="${!empty siteBase}">   
	<script type="text/javascript">
		$(function() {
			$("#siteName").val("${siteBase.siteName}");
			$("#siteDiy").val("${siteBase.siteDiy}");
			$("#enName").val("${siteBase.enName}");
			$("#siteSign").val("${siteBase.siteSign}").attr("disabled",'disabled').css("color","#888");
			$("#license").val("${siteBase.license}");
			$("#syncConfNum").val("${siteBase.syncConfNum}");
			$("#timeZoneId").val("${siteBase.timeZoneId}");
			var siteFlag = "${siteBase.siteFlag}";
			if (siteFlag=="1") {
				$("input:radio[name=siteFlag]:eq(0)").attr("checked",'checked');
			} else {
				$("input:radio[name=siteFlag]:eq(1)").attr("checked",'checked');
			}
			
			var effeDate = '${effeDate}';
			if (effeDate && effeDate.length>0) {
				effeDate = effeDate.substring(0, 10);
			}
			$("#effeDate").val(effeDate);
			var expireDate = '${expireDate}';
			if (expireDate && expireDate.length>0) {
				expireDate = expireDate.substring(0, 10);
			}
			$("#expireDate").val(expireDate);

			$("#trueName").val("${siteAdmin.trueName}");
			$("#loginName").val("${siteAdmin.loginName}");
			$("#userEnName").val("${siteAdmin.enName}");
			$("#loginPass").val("");
			$("#userEmail").val("${siteAdmin.userEmail}");
			$("#mobile").val("${siteAdmin.mobile}");
			var remark = $("#remarkFlag").text();
			$("#remark").val(remark);
			var hireMode = "${siteBase.hireMode}";
			if (hireMode=="1") {
				$("input:radio[name=hireMode]:eq(0)").attr("checked",'checked');
				$("input:radio[name=hireMode]").attr("disabled",'disabled');
			} else {
				$("input:radio[name=hireMode]:eq(1)").attr("checked",'checked');
				$("input:radio[name=hireMode]").attr("disabled",'disabled');
			}
			var chargeMode = "${siteBase.chargeMode}";
			$("#chargeMode").val(chargeMode);
			 
			var siteModel = "${siteBase.siteModel}";
			if(siteModel=="0") {
				$("input:radio[name=siteModel]:eq(0)").attr("checked",'checked');
			} else {
				$("input:radio[name=siteModel]:eq(1)").attr("checked",'checked');
			}
			$("#marketorEmail").val("${siteBase.marketorEmail}");
			//$("#license").attr("disabled",'disabled').css("color", "#cccccc");
		});
	</script>	
</c:if>
<script type="text/javascript">
$(document).ready(function(){
	var lang = getBrowserLang(); 
	if (lang=="zh-cn") {
		$.datepicker.setDefaults( $.datepicker.regional[ "zh-CN" ] );
	} else {
		$.datepicker.setDefaults( $.datepicker.regional[ "en-GB" ] );
	}
	$("#effeDate, #expireDate").datepicker({
		minDate: +0,
		changeMonth: true,
		changeYear: true,
		dateFormat: "yy-mm-dd",
		showOn: "both",
		buttonImage: "/static/images/calendar.jpg",
		buttonImageOnly: true
	});
	$("#createSiteForm").find("input, select, textarea").not(".skipThese").uniform();
	$('#createSiteForm :input').tipsy({ trigger: 'manual', fade: false, gravity: 'sw', opacity: 1 });
	var current = 0;
	var ruleString = {
			pageRequired: {
				"siteName": "${LANG['system.site.list.CompanyName.input']}",
				"enName": "${LANG['system.site.list.EnglishName.input']}",
				"siteSign": "${LANG['system.site.list.SiteSign.input']}",
				"license": "${LANG['system.site.list.License.input']}",
				"effeDate": "${LANG['system.site.list.Effedate.input']}",
				"expireDate": "${LANG['system.site.list.ExpireDate.input']}",
				"trueName": "${LANG['system.site.list.UserName.input']}",
				"loginName": "${LANG['system.site.list.LoginName.input']}",
				"loginPass": "${LANG['system.site.list.Password.input']}",
				"confirmPass": "${LANG['system.user.confirmPass.input']}",
				"userEmail": "${LANG['system.site.list.Email.input']}",
				"marketorEmail": "${LANG['website.system.title.tip4']}",
				"syncConfNum": "${LANG['website.system.title.tip5']}"
				
			},
			remote: {
				"siteSign": "${LANG['system.site.list.siteSign.remote']}",
				"loginName": "${LANG['system.site.list.loginName.remote']}",
				"userEmail": "${LANG['system.site.list.userEmail.remote']}"
			},
			minlength: {
				"license": "${LANG['system.site.list.license.minlength']}",
				"syncConfNum": "${LANG['website.system.title.tip6']}"
			},
			maxlength: {
				"remark": "${LANG['system.site.list.remark.maxlength']}",
				"license": "${LANG['system.site.list.license.maxlength']}",
				"syncConfNum": "${LANG['website.system.title.tip7']}"
			},
			rangelength: {
				"siteName": "${LANG['system.site.list.siteName.rangelength']}",
				"enName": "${LANG['system.site.list.enName.rangelength']}",
				"siteSign": "${LANG['system.site.list.siteSign.rangelength']}",
				"trueName": "${LANG['system.site.list.clientName.rangelength']}",
				"loginName": "${LANG['system.site.list.loginName.rangelength']}",
				"loginPass": "${LANG['system.site.list.loginPass.rangelength']}",
				"userEmail": "${LANG['system.site.list.userEmail.rangelength']}",
				"marketorEmail": "${LANG['system.site.list.userEmail.rangelength']}",
				"mobile": "${LANG['system.site.list.mobile.rangelength']}"
			},
			custom: {
				"equalTo": "${LANG['system.user.confirmpass.custom']}",
				"checkSiteName": "${LANG['system.site.list.checkSiteName.custom']}",
				"checkEnName": "${LANG['system.site.list.checkEnName.custom']}",
				"checkChName": "${LANG['system.site.list.checkChName.custom']}",
				"checkSiteSign": "${LANG['system.site.list.checkSiteSign.custom']}",
				"checkUserName": "${LANG['system.site.list.checkUserName.custom']}",
				"checkLoginName": "${LANG['system.site.list.checkLoginName.custom']}",
				"checkMobile": "${LANG['system.site.list.checkMobile.custom']}",
				"email": "${LANG['system.site.list.CorrectEmail.input']}",
				"mobile": "${LANG['system.site.list.mobile.custom']}",
				"endDate": "${LANG['system.site.list.endDate.custom']}",
				"digits": "${LANG['system.site.list.Integer.input']}",
				"dateISO": "${LANG['system.site.list.CorrectDate.input']}"
			}
	};
	$.validator.addMethod("pageRequired", function(value, element) {
		var $element = $(element);
		function match(index) {
			return current == index && $(element).parents("#sf" + (index + 1)).length;
		}
		if (match(0) || match(1) || match(2) || match(3)) {
			return !this.optional(element);
		}
		return "dependency-mismatch";
	}, $.validator.messages.required);
	$.validator.addMethod("notRequired", function(value, element) {
		if(value==null || value=="" || value.length==0){
			$(element).tipsy('hide').removeAttr('original-title');
		}
    	return true;
 	}, "");
	
	$.validator.addMethod("checkSiteName", function(value, element) {   
    	return this.optional(element) || /^[a-zA-Z0-9_\s\-&\u4e00-\u9fa5]{1,32}$/.test(value);
 	}, ruleString.custom.checkSiteName);
	
	$.validator.addMethod("checkEnName", function(value, element) {       
    	return this.optional(element) || /^[a-zA-Z0-9_\s\-&,.]{1,64}$/.test(value);
 	}, ruleString.custom.checkEnName);
	
	$.validator.addMethod("notChName", function(value, element) {       
    	return this.optional(element) || /^[^\u4e00-\u9fa5]+$/.test(value);
 	}, ruleString.custom.checkChName);
	
	$.validator.addMethod("checkSiteSign", function(value, element) {       
    	return this.optional(element) || /^[a-zA-Z0-9_\-&]{1,16}$/.test(value);
 	}, ruleString.custom.checkSiteSign);	
	
	$.validator.addMethod("checkUserName", function(value, element) {       
    	return this.optional(element) || /^[a-zA-Z0-9_\-&\u4e00-\u9fa5]{1,32}$/.test(value);
 	}, ruleString.custom.checkUserName);	
	
	$.validator.addMethod("checkLoginName", function(value, element) {       
    	return this.optional(element) || /^[a-zA-Z0-9_]{4,16}$/.test(value);
 	}, ruleString.custom.checkLoginName);

	$.validator.addMethod("endDate", function(value, element) {
		var startDate = $("#effeDate").val();
		var d1Arr=startDate.split('-');
		var d2Arr=value.split('-');
		var v1=new Date(d1Arr[0],d1Arr[1],d1Arr[2]);
		var v2=new Date(d2Arr[0],d2Arr[1],d2Arr[2]);
    	return v1<=v2;
 	}, ruleString.custom.endDate);
	/**
	${LANG['bizconf.jsp.admin.add_site_user.res3']}  ${LANG['bizconf.jsp.admin.add_site_user.res4']} 1 ${LANG['bizconf.jsp.admin.add_site_user.res5']} 3${LANG['bizconf.jsp.admin.add_site_user.res6']}5 ${LANG['bizconf.jsp.admin.add_site_user.res7']} 8
	13211111111, 015111111111, +8615811111111, +86015811111111, (+86)13111111111
	${LANG['bizconf.jsp.admin.add_site_user.res8']} ${LANG['bizconf.jsp.admin.add_site_user.res9']}-${LANG['bizconf.jsp.admin.add_site_user.res10']}-${LANG['bizconf.jsp.admin.add_site_user.res11']}
	1334567890, 031-3145678-123, 010-11111111, (+86)010-13901691-123
	*/
	$.validator.addMethod("checkMobile", function(value, element) {       
		return this.optional(element) || /(^((\+86)?|\(\+86\)|\+86\s|\+86-)0?1[358]\d{9}$)|(^((\+86)?|\(\+86\)|\+86\s|\+86-)0?([1-9]\d{1,2}-?\d{6,8}|[3-9][13579]\d-?\d{6,7}|[3-9][24680]\d{2}-?\d{6})(-\d{4})?$)/.test(value);
	}, ruleString.custom.checkMobile);	
	
	$.validator.addMethod("checkPass", function(value, element) {       
		var flag = ValidatorPass(value);
    	if(flag){
    		return true;
    	}
		return false;
 	}, "${LANG['website.user.profile.val.newpass.tip1'] }");
	
	var v = $("#createSiteForm").validate({
		onkeyup: false,
		onblur: true,
		errorClass: "warning",
		submitHandler: function(form) {
			saveOrCreateSite();
		},
		rules: {
            'siteName' : {pageRequired:true, rangelength: [1, 32]},
            'enName' : {notRequired:true,  rangelength: [1, 64], notChName:true},
            'siteSign' : {pageRequired:true, rangelength: [1, 16], checkSiteSign:true, remote: {
            	url: '/system/site/siteSignValidate',
            	type: 'post',
            	data: {
            		siteSign: function() {
            			return $("#siteSign").val();
            		},
                    siteId: function() {
                    	siteId = "${siteBase.id}";
                    	if (siteId && siteId.length>0) {
                        	return siteId;	
                    	} else {
                        	return null;
                    	}
                    }
            	}
            }},
            'license' : {pageRequired:true, digits: true, min:"${SITE_CREATE_MIN_LICENSE}", max:"${SITE_CREATE_MAX_LICENSE}"},
            'syncConfNum' : {pageRequired:true, digits: true, min:"${SITE_CREATE_MIN_SYNCNUM}", max:"${SITE_CREATE_MAX_SYNCNUM}"},
            'effeDate' : {pageRequired:true, dateISO:true},
            'expireDate' : {pageRequired:true, dateISO:true, endDate: true},
            'trueName' : {pageRequired:true, rangelength: [1, 32],checkUserName:true},
            'loginName' : {pageRequired:true,  rangelength: [4, 16],checkLoginName:true, remote: {
            	url: '/system/site/loginNameValidate',
            	type: 'post',
            	data: {
            		loginName: function() {
            			return $("#loginName").val();
            		},
                    siteId: function() {
                    	siteId = "${siteBase.id}";
                    	if (siteId && siteId.length>0) {
                        	return siteId;	
                    	} else {
                        	return null;
                    	}
                    },
                    userId: function() {
                    	userId = "${siteAdmin.id}";
                    	if (userId && userId.length>0) {
                        	return userId;	
                    	} else {
                        	return null;
                    	}
                    }
            	}
            }},
            'userEnName' : {notRequired:true,  rangelength: [1, 32], checkEnName:true},
            'userEmail' : {pageRequired:true, rangelength: [6, 32], email: true, remote: {
            	url: '/system/site/emailValidate',
            	type: 'post',
            	data: {
            		userEmail: function() {
            			return $("#userEmail").val();
            		},
                    siteId: function() {
                    	siteId = "${siteBase.id}";
                    	if (siteId && siteId.length>0) {
                        	return siteId;	
                    	} else {
                        	return null;
                    	}
                    },
                    userId: function() {
                    	userId = "${siteAdmin.id}";
                    	if (userId && userId.length>0) {
                        	return userId;	
                    	} else {
                        	return null;
                    	}
                    }
            	}
            }},
            "marketorEmail": {notRequired:true, rangelength: [6, 32], email:true},
            "mobile": {checkMobile:true},
            "remark": {maxlength: 256}
        },
        messages: {
            'siteName' : {pageRequired:ruleString.pageRequired.siteName, rangelength: ruleString.rangelength.siteName},
            'enName' : {pageRequired:ruleString.pageRequired.enName, rangelength: ruleString.rangelength.enName},
            'siteSign' : {pageRequired:ruleString.pageRequired.siteSign,checkSiteSign:ruleString.custom.checkSiteSign, remote: ruleString.remote.siteSign, rangelength: ruleString.rangelength.siteSign},
            'license' : {pageRequired:ruleString.pageRequired.license, digits: ruleString.custom.digits, min:ruleString.minlength.license, max:ruleString.maxlength.license},
            'effeDate' : {pageRequired:ruleString.pageRequired.effeDate, dateISO:ruleString.custom.dateISO},
            'expireDate' : {pageRequired:ruleString.pageRequired.expireDate, dateISO:ruleString.custom.dateISO, endDate: ruleString.custom.endDate},
            'trueName' : {pageRequired:ruleString.pageRequired.clientName, checkUserName:ruleString.custom.checkUserName, rangelength: ruleString.rangelength.clientName},
            'loginName' : {pageRequired:ruleString.pageRequired.loginName,checkLoginName:ruleString.custom.checkLoginName, remote: ruleString.remote.loginName, rangelength: ruleString.rangelength.loginName},
            'userEnName' : {pageRequired:ruleString.pageRequired.enName, checkEnName: ruleString.custom.checkEnName,  rangelength: ruleString.rangelength.enName},
            'loginPass' : {pageRequired:ruleString.pageRequired.loginPass, rangelength: ruleString.rangelength.loginPass},
            'confirmPass' : {pageRequired:ruleString.pageRequired.confirmPass, rangelength: ruleString.rangelength.loginPass, equalTo:ruleString.custom.equalTo},
            'userEmail' : {pageRequired:ruleString.pageRequired.userEmail, rangelength: ruleString.rangelength.userEmail, email: ruleString.custom.email, remote: ruleString.remote.userEmail},
            'marketorEmail' : {pageRequired:ruleString.pageRequired.marketorEmail, rangelength: ruleString.rangelength.marketorEmail, email: ruleString.custom.email},
            'mobile': {rangelength: ruleString.rangelength.mobile, checkMobile: ruleString.custom.mobile},
            'remark': {maxlength: ruleString.maxlength.remark},
            'syncConfNum' : {pageRequired:ruleString.pageRequired.license, digits: ruleString.custom.digits, min:ruleString.minlength.syncConfNum, max:ruleString.maxlength.syncConfNum}
        
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
	
	/** 点击随机生成密码 DARREN ADD */
	$("input[name='randomPwd']").change(function(){
		//每次点击checkout都对password进行清空处理
		$("#loginPass").val("");
		$("#confirmPass").val("");
		
		var checked = $(this).attr("checked");
		if(checked == "checked"){
			$("#loginPass").hide();
			$("#confirmPass").hide();
			$("#loginPassT").show();
			$("#confirmPassT").show();
			
			$("#loginPassT").val("${randomPwd}");
			$("#confirmPassT").val("${randomPwd}");
			$("#loginPass").val("${randomPwd}");
			$("#confirmPass").val("${randomPwd}");
			
			//去除掉提示信息
			$("#loginPass").tipsy('hide').removeAttr('original-title');
			$("#confirmPass").tipsy('hide').removeAttr('original-title');
		}else{
			$("#loginPass").show();
			$("#confirmPass").show();
			$("#loginPassT").hide();
			$("#confirmPassT").hide();
			
			$("#loginPassT").val("");
			$("#confirmPassT").val("");
			$("#loginPass").val("");
			$("#confirmPass").val("");
		}
	 });
	
	//update site should remove passworld valid
	var siteId = "${siteBase.id}";
	if (siteId) {
		$("#loginPass").rules("remove");
		$("#confirmPass").rules("remove");
	} else {
		$("#loginPass").rules("add", {pageRequired:true, rangelength:[6, 16],checkPass:true});
		$("#confirmPass").rules("add", {pageRequired:true, rangelength:[6, 16], equalTo: '#loginPass'});
	}
	
	$("#loginPass").change(function() {
		if (siteId) {
			if($(this).val() && $(this).val().length>0) {
				$(this).rules("add", {pageRequired: true, rangelength: [6, 16],checkPass:true});
				$("#confirmPass").rules("add", {pageRequired:true, rangelength:[6, 16], equalTo: '#loginPass'});
			} else {
				$(this).tipsy('hide').removeAttr('original-title');
				$(this).rules("remove");
				$("#confirmPass").rules("remove");
			}	
		}
	});
	$("#confirmPass").change(function() {
		if (siteId) {
			if($(this).val() && $(this).val().length>0) {
				$(this).rules("add", {pageRequired: true, rangelength: [6, 16],checkPass:true});
				$("#confirmPass").rules("add", {pageRequired:true, rangelength:[6, 16], equalTo: '#loginPass'});
			} else {
				$(this).tipsy('hide').removeAttr('original-title');
				$(this).rules("remove");
				$("#confirmPass").rules("remove");
			}	
		}
	});
	
	// back buttons do not need to run validation
	$(".prevbutton").click(function(){
		$(".tipsy").remove();
		var pageIndex = $(this).attr("pageIndex");
		current = pageIndex;
		$("#createSiteForm").find("li.step").eq(current).show().siblings().hide();
	}); 
	
	$(".nextbutton").click(function() {
		var pageIndex = $(this).attr("pageIndex");
	  	if (v.form()) {
	    	current = pageIndex;
			$("#createSiteForm").find("li.step").eq(current).show().siblings().hide();
	  	}else{
	  		$("#createSiteForm").find("li.step").eq(current).find("input.warning:first").focus();
	  	}
	});
	
	$(".closeButton").click(function() {
		var frame = parent.$("#siteDiv");
		frame.trigger("closeDialog");
	});
	
	$(".updateButton").click(function() {
		if (v.form()) {
			saveOrCreateSite();	
		} else {
			
		}
	});
	
	//${LANG['bizconf.jsp.system.createSite.res13']}
	$("input[name='hireMode']").change(function() {
		
	});
	//${LANG['bizconf.jsp.system.createSite.res14']}
	
	$("input[name='time']").change(function() {
		var value = $(this).val();
		if (value==5) {
			$("#license").val(2000);
		} else {
			$("#license").val(20);
		}
	});
	//${LANG['bizconf.jsp.system.createSite.res17']}
	var licenseNum = "${siteBase.license}";
	if(licenseNum == "" || licenseNum == 0){
		$("#license").val(20);
	}
//	$("input[name='autoFlag']").attr("disabled", "disabled");
	$("input[name='outCallFlag']").attr("disabled", "disabled");
	var checked = $("input[name='phoneFlag']").attr("checked");
	if(checked=="checked"){
//		$("input[name='autoFlag']").removeAttr("disabled");
		$("input[name='outCallFlag']").removeAttr("disabled");
	}
	
	$("input[name='phoneFlag']").change(function() {
		var value = $(this).val();
		var checked = $(this).attr("checked");
		if(checked=="checked"){
			document.getElementById("phoneFlag").value = 1;
//			$("input[name='autoFlag']").removeAttr("disabled");
			$("input[name='outCallFlag']").removeAttr("disabled");
		} else {
			document.getElementById("phoneFlag").value = 0;
//			$("input[name='autoFlag']").attr("disabled", "disabled");
//			$("input[name='autoFlag']").removeAttr("checked", "checked");
			$("input[name='outCallFlag']").attr("disabled", "disabled");
			$("input[name='outCallFlag']").removeAttr("checked", "checked");
		}
		$.uniform.update();
	});
	/*
	$("input[name='autoFlag']").change(function() {
		var value = $(this).val();
		var checked = $(this).attr("checked");
		if(checked=="checked"){
			document.getElementById("autoFlag").value = 1;
		} else {
			document.getElementById("autoFlag").value = 0;
		}
		$.uniform.update();
	});
	*/
	$("input[name='outCallFlag']").change(function() {
		var value = $(this).val();
		var checked = $(this).attr("checked");
		if(checked=="checked"){
			document.getElementById("outCallFlag").value = 1;
		} else {
			document.getElementById("outCallFlag").value = 0;
		}
		$.uniform.update();
	});
	
	$("input[name='shareMediaFlag']").change(function() {
		var value = $(this).val();
		var checked = $(this).attr("checked");
		if(checked=="checked"){
			document.getElementById("shareMediaFlag").value = 1;
		} else {
			document.getElementById("shareMediaFlag").value = 0;
		}
		$.uniform.update();
	});
	
	$("input[name='recordFlag']").change(function() {
		var value = $(this).val();
		var checked = $(this).attr("checked");
		if(checked=="checked"){
			document.getElementById("recordFlag").value = 1;
		} else {
			document.getElementById("recordFlag").value = 0;
		}
		$.uniform.update();
	});
});

function saveOrCreateSite() {
	var siteID = "${siteBase.id}";
	var userID = "${siteAdmin.id}";
	//siteinfo
	var siteInfo = {};
	siteInfo.siteName = $("#siteName").val();
	siteInfo.siteDiy = $("#siteDiy").attr("checked") ? 1 : 0;
	siteInfo.enName = $("#enName").val();
	siteInfo.syncConfNum = $("#syncConfNum").val();
	siteInfo.siteSign = $("#siteSign").val();
	siteInfo.timeZoneId = $("#timeZoneId").val();
	var logo = $("#previewImg").attr("alt");
	siteInfo.siteLogo = logo;
	siteInfo.siteFlag = $('input:radio[name="siteFlag"]:checked').val();
	siteInfo.hireMode = $('input:radio[name="hireMode"]:checked').val();
	siteInfo.chargeMode = 1;
	
	siteInfo.siteModel = $('input:radio[name="siteModel"]:checked').val();
	siteInfo.effeDate = $("#effeDate").val();
	siteInfo.expireDate = $("#expireDate").val();
	siteInfo.marketorEmail = $("#marketorEmail").val();
	siteInfo.msGroupId = $("#msGroupSelect").val();
	//userinfo
	var userInfo = {};
	userInfo.trueName = $("#trueName").val();
	userInfo.enName = $("#userEnName").val();
	userInfo.loginName = $("#loginName").val();
	var password = $("#loginPass").val();
	if(password && password.length>0) {
		userInfo.loginPass = password;
		if(siteID && siteID.length>0) {
			parent.$("#mainFrame").data("PWD", password);	
		}
	}
	userInfo.userEmail = $("#userEmail").val();
	var mobile = $("#mobile").val();
	if(mobile && mobile.length>0) {
		userInfo.mobile = mobile;	
	}
	var remark = $("#remark").val();
	if (remark && remark.length>0) {
		userInfo.remark = remark;	
	}
	//config
	var config = {};
	$('.extraConfig').each(function(){
		var index = $(this).val();
		var name = $(this).attr("name");
		config[name] = 0;
		var checked = $(this).attr("checked");
		if(checked=="checked"){
			config[name] = index;
		}
	});
	config.videoNumber = $("#videoNumber").val();
	config.audioNumber = $("#audioNumber").val();
	config.dpiNumber = $("#dpiNumber").val();
	if (siteID && siteID.length>0) {
		siteInfo.id = siteID;
		userInfo.id = userID;
		siteInfo.license = "${siteBase.license}";    //修改站点时，不可以修改license
		app.updateSite(siteInfo, userInfo, config, function(result) {
			var frame = parent.$("#siteDiv");
			frame.trigger("closeDialog", [result]);
		}, {message:"${LANG['system.site.change']}", ui:parent});
	} else {
		siteInfo.license = $("#license").val();
		app.createSite(siteInfo, userInfo, config,function(result) {
			var frame = parent.$("#siteDiv");
			frame.trigger("closeDialog", [result]);
		}, {message:"${LANG['system.site.save']}", ui:parent});	
	}
}
function uploadCallback(url){
	$("#previewLoadImg").hide();
	$("#logoIframe").attr("src", "/jsp/common/upload_common.jsp");
	if(url && url.length>0){
		$("#previewImg").attr("src", "/uploadfiles/site_logo/" + url);
		$("#previewImg").attr("alt", "/uploadfiles/site_logo/" + url);
		
	}
}

function loaded() {
	var frame = parent.$("#siteDiv");
	frame.trigger("loaded");
}


/**
 * ${LANG['bizconf.jsp.system.createSite.res18']}
 * @returns {Number}
 */

Date.prototype.getTimeZone=function(){
    if (this == null) {
        return 0;
    }
	return this.getTimezoneOffset()*-1*60*1000;
}
 
function initPage(){
	 var nowDate=new Date();
	 var timeZone=nowDate.getTimeZone();
	 $("#timeZoneId option[timeZone='"+timeZone+"']").attr("selected", true);
}

 //initPage();
</script>
