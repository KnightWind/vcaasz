<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${LANG['bizconf.jsp.attendConfloglist.res1']}</title>
<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
<!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
<!--[if lt IE 7]><script src="/assets/js/lib/belatedpng.js"></script><![endif]-->
<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
<!-- <link type="text/css" rel="stylesheet" href="/assets/css/apps/meeting-info.css" /> -->
<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css" />
</head>
<body style="padding: 0px;">
<form id="query" name="query" action="/user/conflog/loglist" method="post">
<input type="hidden" value="${confId}" name="confId" />
<cc:logs var="EVENTLOG_SECCEED"></cc:logs>
<cc:logs var="EVENTLOG_FAIL"></cc:logs>
<div class="party-dialog">
	<div class="wrapper">
		<section>
			<div class="party-panel biz-log-operation">
				<div class="details">
					<!-- 
						<c:if test="${fn:length(optDescList)>0}">
						<table class="common-table">
							<tbody>
								<tr>
									<th class="conf-type">
										${LANG['event.log.detail.update.type']}
									</th>
									<th class="conf-before">
										${LANG['event.log.detail.update.before']}
									</th>
									<th class="conf-after">
										${LANG['event.log.detail.update.after']}
									</th>
								</tr>
							</tbody>
						</table>
						<div class="adm-conf-log-panel">
							<table class="common-table">
								<tbody>
									<c:forEach var="optDesc" items="${optDescList}">
										<tr>
											<c:set var="fieldName" value="${optDesc[0]}.${optDesc[1]}"></c:set>
											<td class="first-child conf-type">
												${LANG[fieldName]}
											</td>
											<td class="conf-before" title="${optDesc[2]}">
												<c:if test="${!empty  optDesc[2]}">${optDesc[2]}</c:if>
												<c:if test="${empty  optDesc[2]}">&nbsp;</c:if>
											</td>
											<td class="last-child conf-after" title="${optDesc[3]}">
												<c:if test="${!empty  optDesc[3]}">${optDesc[3]}</c:if>
												<c:if test="${empty  optDesc[3]}">&nbsp;</c:if>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</c:if>
					<c:if test="${fn:length(optDescList)<=0}">
						<div class="common-empty">
<%--							${LANG['website.user.notice.nodata']}<!-- 此栏目暂无数据 -->--%>
						</div>
					</c:if>						
					 -->
					 <table class="common-table">
					 	<tbody>
					 		<tr>
					 			<th class="conf-type">操作对象</th> 
								<th class="conf-before">详情</th> 
					 			<th class="conf-after">状态</th> 
					 		</tr>
					 	</tbody>
					 </table>
					 <div>
						<table class="common-table">
							<tbody>
								<tr>
					 			<td title="${eventLog.optName}">
					 				<c:choose>
					 					<c:when test="${not empty eventLog.optName}">${eventLog.optName}</c:when>
					 					<c:otherwise>- -</c:otherwise>
					 				</c:choose>
					 			</td> 
								<td title="${eventLog.optDesc}">${eventLog.optDesc}</td> 
					 			<td>
									<c:if test="${eventLog.logStatus==EVENTLOG_SECCEED}">${LANG['website.message.succeed']}</c:if>
        							<c:if test="${eventLog.logStatus==EVENTLOG_FAIL}">${LANG['website.message.fail']}</c:if>
								</td> 
					 		</tr>
							</tbody>
						</table>
					 </div>
				</div>
			</div>		
		</section>
		<div class="form-buttons">
	  		<input type="button" class="button input-gray" onclick="closeDialog();" value="${LANG['website.common.option.confirm']}" />
		</div>
	</div>
</div>
</form>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript">
	function sortQuery(sortField,sortord){
		if(!sortord){
			if($("#sortRule").val()=='0'){
				sortord = "1";
			}else{
				sortord = "0";
			}
		}
		$("#sortField").val(sortField);
		$("#sortRule").val(sortord);
		$("#query").attr("action","/user/conflog/loglist");
		$("#query").submit();
	}
	function closeDialog(result) {
		var dialog = parent.parent.$("#optDescDiv");
		if(result){
			dialog.trigger("closeDialog", [result]);
		} else {
			dialog.trigger("closeDialog");	
		}
	}
</script>  
</body>
</html>