<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
<link type="text/css" rel="stylesheet" href="/assets/css/apps/global-settings.css" />

<cc:siteList var="EMPOWER_DISABLED"/>
<cc:siteList var="EMPOWER_ENABLED"/>
<cc:siteList var="EMPOWER_CODE_FIELD_ARRAY"/>

</head>
<body>
<div class="box">
	<div class="head">
		<span class="title">${LANG['website.admin.globalfun.setting.headinfo0']}</span>
		<span class="desc">${LANG['website.admin.globalfun.setting.headinfo1']}</span>
	</div>
	<div class="body">
		<form name="configForm" id="configForm" action="/admin/config/telconfig/update" method="post">
			<c:set var="flagCount" value="0"/>   <!-- flagCount是否允许企业管理员设置全局变量的个数 -->
		    <c:forEach var="eachField" items="${EMPOWER_CODE_FIELD_ARRAY}" varStatus="fieldSatus">
				<c:if test="${fn:indexOf(eachField[1],'Flag') > -1 && eachField[2] == 1}">
					<c:set var="flagCount" value="${flagCount + 1}"/>
				</c:if>
			</c:forEach>
			<c:if test="${powerCount > 0}">     <!-- powerCount代表已授权功能的个数 -->
				<div class="fieldset">
					<div class="legend"><span>${LANG['website.admin.globalfun.setting.havefunc']}</span></div>
					<c:forEach var="eachField" items="${EMPOWER_CODE_FIELD_ARRAY}" varStatus="fieldSatus">
					<c:set var="eachFieldName" value="${eachField[1]}"/>
	    				<c:if test="${sitePower[eachFieldName]==EMPOWER_ENABLED  && eachField[2]==1}">
							<c:set var="langName" value="system.site.empower.item.${eachField[0]}"/>
							<div class="form-item">
								<label class="title">${LANG[langName]}：</label>
								<div class="widget">
									<label for="openRecord${eachFieldName}">
										<input type="radio" class="allowTelFlag" id="openRecord${eachFieldName}" name="${eachFieldName}"  value="${EMPOWER_ENABLED}" 
		        						<c:if test="${globalConfig[eachFieldName]==EMPOWER_ENABLED }">checked</c:if>/>${LANG['website.common.option.open'] }
									</label>
									<label for="closeRecord${eachFieldName}" class="rlabel">
										<input type="radio" class="allowTelFlag" id="closeRecord${eachFieldName}" name="${eachFieldName}"  value="${EMPOWER_DISABLED}" 
		        						<c:if test="${globalConfig[eachFieldName]==EMPOWER_DISABLED }">checked</c:if>/>${LANG['website.common.option.close'] }
									</label>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</div>
			</c:if>
			<c:if test="${flagCount > powerCount}">  <!-- 该条件满足则有未授权的功能 -->
				<div class="fieldset disableset">
					<div class="legend"><span>${LANG['website.admin.globalfun.setting.funcno']}</span></div>
					<div class="form-item">
						<ul class="clearfix">
							<c:forEach var="eachField" items="${EMPOWER_CODE_FIELD_ARRAY}" varStatus="fieldSatus">
							<c:set var="eachFieldName" value="${eachField[1]}"/>
								<c:if test="${sitePower[eachFieldName]==EMPOWER_DISABLED && eachField[2]==1}">
									<c:set var="langName" value="system.site.empower.item.${eachField[0]}"/>
									<li>${LANG[langName]}</li>
								</c:if>
							</c:forEach>
						</ul>
					</div>
				</div>
			</c:if>
			<div class="form-item submit-item">
				<button type="submit" class="input-gray">${LANG['website.common.option.submit']}</button>
				<span class="success-item">
					<span class="success">
						<c:if test="${!empty infoMessage}">
							<i class="icon fix-png"></i> ${infoMessage}
						</c:if>
						<c:if test="${!empty errorMessage}">
					   		<i class="icon fix-png"></i> ${errorMessage}
					   	</c:if>
					</span>
				</span>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript">
		var outCallFlag = "${sitePower.outCallFlag}";
		$(document).ready(function(){
			$("input[name='outCallFlag']").attr("disabled", "disabled");
			var checked = $("input[name='phoneFlag']").attr("checked");
			if(checked=="checked" && outCallFlag != 0){
				$("input[name='outCallFlag']").removeAttr("disabled");
			}
		});
		$(function() {
			$("input:radio[name=phoneFlag]").change(function() {
				var value = $(this).val();
				if(value==1  && outCallFlag != 0){
					$("input:radio[name=outCallFlag]").removeAttr("disabled");
				} else {
					$("input:radio[name=outCallFlag]").attr("disabled",'disabled');
					$("input:radio[name=outCallFlag]:eq(1)").attr("checked",'checked');
				}
			});
		});
</script>
</body>
</html>