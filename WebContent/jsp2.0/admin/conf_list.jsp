<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>confInfo</title>
<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
<!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
<!--[if lt IE 7]><script src="/assets/js/lib/belatedpng.js"></script><![endif]-->
<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
<link rel="stylesheet" type="text/css" href="/assets/css/module/jquery-ui-1.9.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.adm.meeting-info.css" />
<link type="text/css" rel="stylesheet" href="/assets/css/module/tipsy.css" />
<%@ include file="/jsp/common/cookie_util.jsp"%>
<cc:confList var="CONF_STATUS_OPENING"/>
<cc:confList var="CONF_STATUS_FINISHED"/>
<cc:sort var="SORT_ASC"/>
<cc:sort var="SORT_DESC"/>
<cc:sort var="CONFBASE_SORT_STARTTIME"/>
<cc:sort var="CONFBASE_SORT_ENDTIME"/>
<cc:sort var="CONFBASE_SORT_STATUS"/>
<cc:sort var="CONFBASE_SORT_CONFTYPE"/>
<cc:confList var="CONF_TYPES"/>
<cc:confList var="CONF_STATUS"/>
</head>
<body>
<c:set var="formAction" value="/admin/conf/list"/>
<c:if test="${confName ne null  || effeDateStart ne null || effeDateEnd ne null || eachType ne null || confStatus ne null}">
<c:set var="formAction" value="/admin/conf/listWithCondition"/>
</c:if>
<form id="confForm" name="confForm" action="${formAction}" method="post">
<input type="hidden" class="skipThese" name="sortField"   id="sortField" value="${sortField}"/>
<input type="hidden" class="skipThese" name="sortord" id="sortord" value="${sortord}"/>
<input type="hidden" class="skipThese" name="advanced" id="advanced-search" value="${advanced}"/>
<input type="hidden" class="skipThese" name="baseurl" id="baseurl"/>
	<div class="advantage-search" style="display: none;">
		<div class="bg-header"></div>
		<!-- 会议信息头字段  -->
		<div class="search-fieldset">
				<div class="form-item">
					<label class="title">${LANG['website.admin.conf.list.page.search.confname'] }${LANG['website.common.symbol.colon'] }</label>
					<div class="widget">
						<input name="confName" value="${confName}" type="text" class="input-text" onkeydown='enterSumbit("/admin/conf/listWithCondition",true)'/>
					</div>
				</div>
				<div class="form-item">
					<label class="title">${LANG['website.admin.conf.list.page.search.starttime'] }${LANG['website.common.symbol.colon'] }</label>
					<div class="widget">
						<input name="effeDateStart" id="effeDateStart" value="${effeDateStart}" type="text" class="input-text time-text" />
						<span class="divider">---</span>
						<input name="effeDateEnd" id="effeDateEnd" value="${effeDateEnd}" type="text" class="input-text time-text" />
					</div>
				</div>
				<div class="form-item">
					<label class="title">${LANG['website.admin.conf.list.page.search.confstatus'] }${LANG['website.common.symbol.colon'] }</label>
					<div class="widget" >
						<select name="confStatus" id="confStatus">
					   		<c:forEach var="eachType" items="${CONF_STATUS}"  varStatus="itemStatus">
					   			<c:set var ="typeName" value="conf.status.${eachType }"/>
					   			<option value="${eachType}" <c:if test="${confStatus==eachType}">selected</c:if>>${LANG[typeName]}</option>
					   		</c:forEach>
				   		</select>
					</div>
				</div>
				<div class="form-item">
					<label class="title"></label>
					<div class="widget">
						<button class="input-gray" type="button" onclick='btnSearch("/admin/conf/listWithCondition",true)'>${LANG['website.admin.conf.list.page.search.btn'] }</button>
					</div>
				</div>
		</div>
		<div class="bg-footer"></div>
	</div>
	<div class="box">
		<div class="head">
			<span class="title">${LANG['website.admin.conf.list.page.confinfo']}</span>
			<span class="desc">${LANG['website.admin.conf.list.page.confdesc']}</span>
		</div>
		<div class="body">
			<div class="summary">
				<span class="date">
					<a class="input-gray" onclick="return exportConflogs()">${LANG['website.admin.conf.list.page.export.btn'] }</a>
				</span>
				<div class="search" style="*padding-top:10px;">
					<a class="advantage">${LANG['website.admin.conf.list.page.adsearch'] }</a>
					<button style="float: right;" class="submit-search" type="button" onclick="btnSearch('/admin/conf/list')">${LANG['website.admin.conf.list.page.search.btn'] }</button>
					<input id="conf-search-topic" style="float: right;" name="title" type="text" class="input-text" value="${title }" placeholder="${LANG['website.admin.conf.list.page.search.tip'] }" onkeydown='enterSumbit("/admin/conf/list")'/>
				</div>
			</div>
			<!-- 会议信息条目信息 -->
			<c:if test="${fn:length(confList)>0}">
				<div class="infomation">
					<table class="common-table">
						<tbody>
							<tr><!-- 会议主题和主持人 -->
								<th class="">${LANG['website.admin.conf.list.page.title.confname'] }</th>
								<th class="">${LANG['website.admin.conf.list.page.title.compere'] }</th>
								<!-- 会议状态 -->
								<th class="">
									<div class="sort-th" onclick="return sort('${CONFBASE_SORT_STATUS}')">
										${LANG['website.admin.conf.list.page.title.confstatus'] }
										<c:if test="${CONFBASE_SORT_STATUS!=sortField}"><img  style="vertical-align: middle;" src="/static/images/paixu_button.png"/></c:if>
										<c:if test="${CONFBASE_SORT_STATUS==sortField && SORT_ASC==sortord}"><img  style="vertical-align: middle;" src="/static/images/paixu02.png"/></c:if>
										<c:if test="${CONFBASE_SORT_STATUS==sortField  && SORT_DESC==sortord}"><img  style="vertical-align: middle;" src="/static/images/paixu01.png"/></c:if>
									</div>
								</th>
								<th class="">
									<div class="sort-th" onclick="return sort('${CONFBASE_SORT_STARTTIME}')">
										${LANG['website.admin.conf.list.page.title.starttime'] }
										<c:if test="${CONFBASE_SORT_STARTTIME!=sortField}"><img  style="vertical-align: middle;" src="/static/images/paixu_button.png"/></c:if>
										<c:if test="${CONFBASE_SORT_STARTTIME==sortField && SORT_ASC==sortord}"><img  style="vertical-align: middle;" src="/static/images/paixu02.png"/></c:if>
										<c:if test="${CONFBASE_SORT_STARTTIME==sortField  && SORT_DESC==sortord}"><img  style="vertical-align: middle;" src="/static/images/paixu01.png"/></c:if>
									</div>
								</th>
								<th class="">
									<div class="sort-th" onclick="return sort('${CONFBASE_SORT_ENDTIME}')">
										${LANG['website.admin.conf.list.page.title.endtime'] }
										<c:if test="${CONFBASE_SORT_ENDTIME!=sortField}"><img  style="vertical-align: middle;" src="/static/images/paixu_button.png"/></c:if>
										<c:if test="${CONFBASE_SORT_ENDTIME==sortField && SORT_ASC==sortord}"><img  style="vertical-align: middle;" src="/static/images/paixu02.png"/></c:if>
										<c:if test="${CONFBASE_SORT_ENDTIME==sortField  && SORT_DESC==sortord}"><img  style="vertical-align: middle;" src="/static/images/paixu01.png"/></c:if>
									</div>
								</th>
							</tr>
							<c:forEach var="confInfo" items="${confList}" varStatus="status">
								<tr>
									<td class="first-child">
	<%-- 									<c:if test="${confInfo.cycleId!=null && confInfo.cycleId>0 }"><span id="cycleId_${confInfo.cycleId}">（日）</span></c:if> --%>
										<a onclick="return parent.viewConf(${confInfo.id});" title="${confInfo.confName }">${confInfo.confName }</a>
									</td>
									<td class="" title="${confInfo.compereName}">${confInfo.compereName}</td>
									<td class="">
<!-- 										<c:set var="statusLang" value="website.common.conf.status.${confInfo.confStatus}"/> -->
<!-- 										${LANG[statusLang]} -->
									<c:choose>
											<c:when test="${2 eq confInfo.confStatus}">
												${LANG['com.vcaas.portal.confstatus.living']}
											</c:when>
											<c:when test="${0 eq confInfo.confStatus}">
												 ${LANG['com.vcaas.portal.confstatus.scheduled']}
											</c:when>
											<c:when test="${3 eq confInfo.confStatus}">
												 ${LANG['com.vcaas.portal.confstatus.finish']}
											</c:when>
											<c:when test="${9 eq confInfo.confStatus}">
												 ${LANG['com.vcaas.portal.confstatus.cancel']}
											</c:when>
											<c:when test="${99 eq confInfo.confStatus}">
												 ${LANG['com.vcaas.portal.confstatus.exception']}
											</c:when>
									</c:choose>
									</td>
									<c:choose>
										<c:when test="${empty  confInfo.actulStartTime }">
											<td class="" title='${myfn:fmtDate("yyyy-MM-dd HH:mm",confInfo.startTime,0) }'>
												${myfn:fmtDate("yyyy-MM-dd HH:mm",confInfo.startTime,0) }
											</td>
										</c:when>
										<c:otherwise>
											<td class="" title='${myfn:fmtDate("yyyy-MM-dd HH:mm",confInfo.actulStartTime,site.timeZone) }'>
													${myfn:fmtDate("yyyy-MM-dd HH:mm",confInfo.actulStartTime,site.timeZone) }

											</td>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${CONF_STATUS_OPENING == confInfo.confStatus}">
											<td class="">
											--
											</td>
										</c:when>
										<c:when test="${confInfo.clientCycleConf or confInfo.pmi}">
											<td class="">
											--
											</td>
										</c:when>
										<c:when test="${CONF_STATUS_FINISHED == confInfo.confStatus}">
											<td class="" title='${myfn:fmtDate("yyyy-MM-dd HH:mm",confInfo.endTime,0) }'>
												${myfn:fmtDate("yyyy-MM-dd HH:mm",confInfo.endTime,0) }
											</td>
										</c:when>
										<c:otherwise>
											<td class=""  title='${myfn:fmtDate("yyyy-MM-dd HH:mm",confInfo.endTime,0) }'>
												 ${myfn:fmtDate("yyyy-MM-dd HH:mm",confInfo.endTime,0) }
											</td>
										</c:otherwise>
									</c:choose>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="pagebar clearfix">
						<jsp:include page="page.jsp" />
					</div>
				</div>
			</c:if>	
			<c:if test="${fn:length(confList)<=0}">
				<div class="common-empty">
					${LANG['website.user.notice.nodata'] }<!-- 此栏目暂无数据 -->
				</div>
			</c:if>	
		</div>
	</div>
</form>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-i18n.min.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery.tipsy.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery.watermark.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
<script type="text/javascript">
	jQuery('.advantage-search').click(function(e) {
		e.stopPropagation();
	});
	jQuery(document).on('click', '.advantage', function(e) {
		jQuery('.advantage-search').toggle();
		e.stopPropagation();
	});
	jQuery(document).on('click', function(e) {
	    e = window.event || e;
	    var obj = $(e.srcElement || e.target);
	    var pElem = $(obj).parent(".ui-datepicker-div");
	    if (!pElem) {
	    	jQuery('.advantage-search').hide();
	    }
	});

	jQuery(function($) {
		if ($.browser.msie && $.browser.version<10) {
			$("input[name=title]").watermark("${LANG['website.admin.conf.list.page.search.tip'] }");
		}
		$('.attend_user').tipsy({ html: true, trigger: 'hover', fade: false, gravity: 'e', opacity: 0.65 });
		var lang = getBrowserLang(); 
		if (lang=="zh-cn") {
			$.datepicker.setDefaults( $.datepicker.regional[ "zh-CN" ] );
		} else {
			$.datepicker.setDefaults( $.datepicker.regional[ "en-GB" ] );
		}
		
		$( "#effeDateStart, #effeDateEnd" ).datepicker({
			changeMonth: true,
			changeYear: true,			
			dateFormat: "yy-mm-dd",
			onClose: function() {

			}
		});
		setCursor("conf-search-topic", $("#conf-search-topic").val().length);
	});
	function enterSumbit(url){  
	    var event=arguments.callee.caller.arguments[0]||window.event;  
	    if (event.keyCode == 13){
	    	btnSearch(url);
	    }   
	} 
	function btnSearch(url,isAdvce) {
		if (typeof(resetPageNo) !== "undefined") {
			resetPageNo();	
		}
		if(isAdvce){
			$("#advanced-search").val("1");
		}else{
			$("#advanced-search").val("0");
		}
    	$("#confForm").attr("action", url);
    	$("#confForm").submit();
	}
	function sort(sortField){						//排序
		var formId=($("#sortField").closest("form").attr("id"));
		var oldSortField=$("#sortField").val();
		var oldSortType=$("#sortord").val();
		if(oldSortField==sortField){
			if(oldSortType=="${SORT_DESC}"){
				$("#sortord").val("${SORT_ASC}");
			}else{
				if(oldSortType=="${SORT_ASC}"){
					$("#sortord").val("${SORT_DESC}");
				}
			}
		}else{
			$("#sortField").val(sortField);
			$("#sortord").val("${SORT_ASC}");
		}
		if (typeof(resetPageNo) !== "undefined") {
			resetPageNo();	
		}
		$("#"+formId).submit();
	}
	function exportConflogs() {
		var title = $("#title").val();
		$("#title").val("${title}");
		var act = confForm.action;
		confForm.action="/admin/conf/exportConfdetails";
    	$("#baseurl").val(act);
		confForm.submit();
    	confForm.action = act;
    	$("#title").val(title);
	}
</script>
</body>
</html>