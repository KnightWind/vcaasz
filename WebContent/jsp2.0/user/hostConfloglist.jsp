<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-CN">
	<head>
		<meta charset="utf-8" />
		<title>${LANG['bizconf.jsp.download_center.bizconf.downloadtitle'] }</title>
		<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
		<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
		<link rel="stylesheet" type="text/css" href="/assets/css/module/jquery-ui-1.9.2.custom.css"/>
		<link type="text/css" rel="stylesheet" href="/assets/css/apps/report.css" />
	</head>
	<body>
		<form id="query" name="query" action="/user/conflog/logsList" method="post" class="filter-form" >	
		<div class="box">
			<div class="head">
				<span class="title">${LANG['website.user.conf.report.title'] }</span>
				<span class="desc">${LANG['website.user.conf.report.host.desc'] }</span>
			</div>
			<div class="body" style="background: transparent;">
				<input type="hidden" name="isCreator" value="true" />
				<table class="search-table">
					<tr>
						<td class="conf-topic" style="text-align: right;"><label class="name">${LANG['website.user.conf.report.conf.title'] }:</label></td>
						<td class="conf-input"><input type="text" class="input-text" name="theme" value="${theme }" onkeypress="enterKey();"/></td>
						<td class="conf-time" style="text-align: right;"><label class="name">${LANG['website.user.conf.report.conf.time'] }:</label></td>
						<td class="conf-input-start"><input id="confStart" class="input-text" name="startTime" type="text" readonly="readonly" 
									 value="<fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd"/>"/></td>
						<td class="time-divider">—</td>
						<td class="conf-input-end"><input id="confEnd" class="input-text" name="endTime" type="text" readonly="readonly" 
									value="<fmt:formatDate value="${endTime}" pattern="yyyy-MM-dd"/>" /></td>
						<td class="conf-search">
							<a id="searchbtn" class="input-gray report-search">${LANG['website.common.option.lookup'] }</a>
						</td>
						<td style="text-align: right;">
							<a href="javascript:window.print();" class="input-gray report-print">${LANG['website.common.option.print'] }</a>
						</td>
					</tr>
				</table>
				<c:if test="${fn:length(pageModel.datas)>0}">	
					<table class="common-table host-conf-table">
						<tbody>
							<tr>
								<th  width="150"  title="${LANG['website.user.conf.report.conf.title'] }">${LANG['website.user.conf.report.conf.title'] }</th>  <!-- 会议主题 -->
								<th width="40" title="${LANG['website.user.conf.report.conf.join.number'] }">${LANG['website.user.conf.report.conf.join.number'] }</th> <!-- 参会人次 -->
								<th width="80"  title="${LANG['website.user.conf.report.conf.starttime'] }">${LANG['website.user.conf.report.conf.starttime'] }</th><!-- 开始时间 -->
								<th width="80"   title="${LANG['website.user.conf.report.conf.endtime'] }">${LANG['website.user.conf.report.conf.endtime'] }</th><!-- 结束时间 -->
								<th width="50"  title="${LANG['website.user.conf.report.operator'] }">${LANG['website.user.conf.report.operator'] }</th><!-- 操作 -->
							</tr>
							<c:forEach var="record" items="${pageModel.datas}" varStatus="status">
							      <tr>
<%--							        <td class="first-child"  title="${conflog.confTopic}"><a onclick="return parent.viewConf('${conflog.id}');">${conflog.confTopic}</a></td>--%>
							        <td class=""  title="${record.topic}">${record.topic}</td>
									<td class=""  title="${numMap[record.id]}">${numMap[record.id]}</td>
									<td class="conf-time"  title="${myfn:fmtDate('yyyy-MM-dd HH:mm:ss',record.startTime,currUser.timeZone)}">${myfn:fmtDate('yyyy-MM-dd HH:mm:ss',record.startTime,currUser.timeZone)}</td>
									<td class="conf-time"  title="${myfn:fmtDate('yyyy-MM-dd HH:mm:ss',record.endTime,currUser.timeZone)}">${myfn:fmtDate('yyyy-MM-dd HH:mm:ss',record.endTime,currUser.timeZone)}</td>
									<td class="last-child conf-action">
										<a onclick="return showPartConfDetail('${record.id}');">
										<i class="icon icon-details"></i>${LANG['website.user.conf.report.detail'] }</a> <!-- 参会详情 -->
									</td>
							      </tr>
						    </c:forEach>
						</tbody>
					</table>
					<div class="pagebar clearfix" style="">
						<jsp:include page="page.jsp" />
					</div>
				</c:if>
				<c:if test="${fn:length(pageModel.datas)<=0}">
					<div class="module">
						<div class="nodata">${LANG['website.user.notice.nodata'] }</div><!-- 此栏目暂无数据 -->
					</div>
				</c:if>
			</div>
		</div>
		</form>		
		<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
		<script type="text/javascript" src="/assets/js/lib/jquery-ui-i18n.min.js"></script>
		<%@ include file="/jsp/common/cookie_util.jsp"%>
		<script type="text/javascript">
			function showPartConfDetail(id) {
				parent.showPartConfDetail(id);
			}
			$(document).ready(function(){
				$("#searchbtn").click(function(){
					$("#pageNo").val("");
					$("#query").submit();
				});
				var lang = getBrowserLang(); 
				if (lang=="zh-cn") {
					$.datepicker.setDefaults( $.datepicker.regional[ "zh-CN" ] );
				} else {
					$.datepicker.setDefaults( $.datepicker.regional[ "en-GB" ] );
				}
				
				$( "#confStart, #confEnd" ).datepicker({
					changeMonth: true,
					changeYear: true,			
					dateFormat: "yy-mm-dd",
					onClose: function() {

					}
				});
			});
			
			function enterKey(){
			    var event=arguments.callee.caller.arguments[0]||window.event;//消除浏览器差异   
			    if (event.keyCode == 13){       //监听回车键
			    	$("#pageNo").val("");
					$("#query").submit();
			    } 
			}
		</script>
	</body>
</html>