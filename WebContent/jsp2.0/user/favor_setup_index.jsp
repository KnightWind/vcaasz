<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<cc:siteList var="TIMEZONE_WITH_CITY_LIST"/>
<cc:siteList var="USER_FAVOR_PAGE_SIZE"/>
<cc:siteList var="USER_FAVOR_LANGUAGE"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>index</title>
	<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
	<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.validate.css" />
</head>
<body style="padding: 0px;">
<div class="modifypwd-dialog">
	<div class="wrapper">
		<form name="resetPassForm" id="resetPassForm" action="" method="post">
			<div class="modifypwd-section" style="width: 480px;padding-left: 15px;">
				<div class="form-item">
					<label class="title" style="width: 90px;">${LANG['website.user.favor.timezone'] } ${LANG['website.common.symbol.colon'] }</label>
					<div class="widget">
						 <select id="timeZoneId" name="timeZoneId">
							<c:forEach var="eachTimeZone" items="${TIMEZONE_WITH_CITY_LIST}">
								<c:set var="eachLang" value="website.timezone.city.zone.${eachTimeZone.timeZoneId}"/>
								<option value="${eachTimeZone.timeZoneId}" timeZone="${eachTimeZone.offset}" <c:if test="${timezoneId eq eachTimeZone.timeZoneId}">selected="selected"</c:if>>${LANG[eachLang]} </option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-item">
					<label class="title" style="width: 90px;">${LANG['website.user.favor.lang'] } ${LANG['website.common.symbol.colon'] }</label>
					<div class="widget">
						<select class="selectOption" id="favorLanguage" name="favorLanguage">
							<c:forEach var="eachLanguage" items="${USER_FAVOR_LANGUAGE}">
								<c:set var="language" value="system.language.${eachLanguage}"/>
								<option value="${eachLanguage}"<c:if test="${eachLanguage eq lang}">selected="selected"</c:if> >${LANG[language]}</option>
						    </c:forEach>
						</select>
					</div>
				</div>
				<div class="form-item confirm-item">
					<label class="title" style="width: 90px;">${LANG['website.user.favor.page.data'] } ${LANG['website.common.symbol.colon'] }</label>
					<div class="widget" style="position: relative;">
						<select class="selectOption" id="pageSize" name="pageSize">
							<c:forEach var="eachPageSize" items="${USER_FAVOR_PAGE_SIZE}">
								<c:set var="eachPage" value="${eachPageSize}"/>
								<option value="${eachPageSize}" <c:if test="${eachPageSize eq 15 }">selected="selected"</c:if>>${eachPageSize} ${LANG['user.favor.page.records']}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="form-buttons">
				<input type="button" class="button input-gray" onclick="resetFavoerSetting();" value="${LANG['website.common.option.confirm'] }">
				<a class="button anchor-cancel" onclick="javascript:closeDialog();">${LANG['website.common.option.cancel'] }</a>
			</div>
		</form>
	</div>
	<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
	<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
	<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
	<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
	<script type="text/javascript">
		function closeDialog(){
			var frame = parent.$("#faverSetting");
			frame.trigger("closeDialog");
		}
		
		function resetFavoerSetting(){
			var parms = {};
			parms.timeZoneId = $("#timeZoneId").val();
			parms.pageSize = $("#pageSize").val();
			parms.favorLanguage = $("#favorLanguage").val();
			$.ajax({
		      	type: "POST",
		      	url:"/user/favor/indexUpdateFavor",
		      	data:parms,
		      	dataType:"json",
		      	success:function(data){
		      		if(data.message == 'changelan'){
		      			top.location.reload(true);
		      		}else{
		      			closeDialog();
		      		}
		      	},
		        error:function(XMLHttpRequest, textStatus, errorThrown) {
		        	alert(XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
		        }
			}); 
		}
	</script>
</body>
</html>