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
<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css" />
</head>
<body style="padding: 0px;">
<form id="query" name="query" action="/user/conflog/attendConflist" method="post">
<input type="hidden" value="${userId}" name="userId" />
<input type="hidden" value="${confId}" name="confId" />
<div class="party-dialog">
	<div class="wrapper">
		<section>
			<div class="party-panel biz-attend-conf-detail">
				<div class="details">
					<c:if test="${fn:length(pageModel.datas)>0}">
						<table class="common-table">
							<tbody>
								<tr>
									<th class="conf-name" title="${LANG['website.user.conflogs.title.conftheme']}">${LANG['website.user.conflogs.title.conftheme']}</th>
									<th class="conf-person" title="${LANG['website.user.conflogs.title.attendees']}">${LANG['website.user.conflogs.title.attendees']}</th>
									<th class="conf-host" title="${LANG['website.user.conflogs.title.host']}">${LANG['website.user.conflogs.title.host']}</th>
									<th class="conf-time" title="${LANG['website.user.conflogs.title.confstarttime']}">${LANG['website.user.conflogs.title.confstarttime']}</th>
									<th class="conf-time" title="${LANG['website.user.conflogs.title.confendtime']}">${LANG['website.user.conflogs.title.confendtime']}</th>
									<th class="conf-time" title="${LANG['website.user.conflogs.title.jointime']}">${LANG['website.user.conflogs.title.jointime']}</th>
									<th class="conf-time-exit" title="${LANG['website.user.conflogs.title.leavetime']}">${LANG['website.user.conflogs.title.leavetime']}</th>
								</tr>
							</tbody>
						</table>
						<div class="adm-conf-details-panel">
							<table class="common-table">
								<tbody>
									<c:forEach var="conf" items="${pageModel.datas}" varStatus="status">
										<tr>
											<td class="first-child conf-name" title="${conf.confName}">
												<div class="nobr">${conf.confName}</div>
											</td>
											<td class="conf-person">
												${numMap[conf.id]}
											</td>
											<td class="conf-host" title="${conf.compereName}">
												${conf.compereName}
											</td>
											<td class="conf-time" title="${myfn:fmtDate('yyyy-MM-dd HH:mm',conf.startTime,timezone)}">
												<div class="nobr">${myfn:fmtDate('yyyy-MM-dd HH:mm',conf.startTime,timezone)}</div>
											</td>
											<td class="conf-time" title="${myfn:fmtDate('yyyy-MM-dd HH:mm',conf.endTime,timezone)} ">
												<div class="nobr">${myfn:fmtDate('yyyy-MM-dd HH:mm',conf.endTime,timezone)}</div> 
											</td>
											<td class="conf-time" title="${myfn:fmtDate('yyyy-MM-dd HH:mm',cls[conf.id].joinTime,timezone)}">
												<div class="nobr">${myfn:fmtDate('yyyy-MM-dd HH:mm',cls[conf.id].joinTime,timezone)}</div>
											</td>
											<td class="last-child conf-time-exit" title="${myfn:fmtDate('yyyy-MM-dd HH:mm',cls[conf.id].exitTime,timezone)} ">
												<div class="nobr">${myfn:fmtDate('yyyy-MM-dd HH:mm',cls[conf.id].exitTime,timezone)}</div> 
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="pagebar clearfix">
							<jsp:include page="page.jsp" />
						</div>
					</c:if>
					<c:if test="${fn:length(pageModel.datas)<=0}">
						<div style="margin-top: 50px;" class="common-empty">
							${LANG['website.user.notice.nodata'] }<!-- 此栏目暂无数据 -->
						</div>
					</c:if>
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
		var dialog = parent.parent.$("#attendconfs");
		if(result){
			dialog.trigger("closeDialog", [result]);
		} else {
			dialog.trigger("closeDialog");	
		}
	}
</script>  
</body>
</html>