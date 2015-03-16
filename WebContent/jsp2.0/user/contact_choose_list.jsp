<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>index</title>
	<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
	<!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
	<!--[if lt IE 7]><script src="/assets/js/lib/belatedpng.js"></script><![endif]-->
	<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
	<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.adm.meeting-info.css" />
	<cc:sort var="SORT_ASC"/>
	<cc:sort var="SORT_DESC"/>
	<cc:sort var="EVENTLOG_SORT_STATUS"/>
</head>
<body style="padding: 0px;background: none repeat scroll 0 0 #ffffff">
<form id="query" name="query" action="/user/contact/invitelist" method="post">
<input class="" type="hidden" name="sortord" id="sortord" value="${sortord}"/>
	<div class="choose-dialog">
		<div class="wrapper">
			<div class="choose-panel biz-contact-address-invite">
				<div class="tabview">
					<div class="view active">
						<div class="filter clearfix">
							<div class="search">
									<input type="text" class="input-text" name="keyword" value="${keyword}" placeholder="${LANG['website.user.contact.list.placeholder1']}">
									<button class="submit-search" type="button" onclick="javscript:submitForm();" >${LANG['swebsite.common.search.name']}</button>
								
							</div>
						</div>
						<div class="render">
							<c:if test="${fn:length(pageModel.datas)>0}">
									<table class="common-table">
										<tbody>
											<tr>
												<th class="check"><input type="checkbox"  id="checkAll" /></th>
												<th class="sort-th" onclick="sort('${EVENTLOG_SORT_STATUS}')">
													${LANG['website.user.contact.list.name']}
													<c:choose>
														<c:when test="${SORT_ASC==sortord}"><img src="/static/images/paixu02.png" style="vertical-align: middle;"/></c:when>
														<c:when test="${SORT_DESC==sortord}"><img src="/static/images/paixu01.png" style="vertical-align: middle;"/></c:when>
														<c:otherwise><img src="/static/images/paixu_button.png" style="vertical-align: middle;"/></c:otherwise>
													</c:choose>
												</th>
												<th class="user-enname">${LANG['website.user.contact.list.titleenname']}</th>
												<th class="user-email">${LANG['website.user.contact.list.titleemail']}</th>
												<th class="user-tel">${LANG['website.user.contact.list.titlephone']}</th>
												<th class="user-call">${LANG['website.user.contact.list.titlemobile']}</th>
											</tr>
										</tbody>
									</table>
									<div class="panel">
										<table class="common-table">
											<tbody>
												<c:forEach var="contact" items="${pageModel.datas}" varStatus="status">
												<tr>
													<td class="first-child check">
														<input name="id" type="checkbox" value="${contact.id}" />
														<input name="userId_${contact.id}" type="hidden" value="${contact.id}" />
													</td>
													<td class="user-name" name="contactName${contact.id}" title="${contact.contactName}">${contact.contactName}</td>
													<td class="user-enname" title="${contact.contactNameEn}">${contact.contactNameEn}</td>
													<td class="user-email" name="contactEmail${contact.id}" title="${contact.contactEmail}">${contact.contactEmail}</td>
													<td class="user-tel" name="contactPhone${contact.id}" title="${contact.contactPhone}">${contact.contactPhone}</td>
													<td class="last-child user-call" name="contactMobile${contact.id}" title="${contact.contactMobile}">${contact.contactMobile}</td>
												</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
									<div class="pagebar clearfix">
										 <jsp:include page="page.jsp" />
									</div>
							</c:if>
							<c:if test="${fn:length(pageModel.datas)<1}">
								<div class="common-empty">${LANG['website.user.contact.list.nodata']}</div>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery.watermark.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript">
	function getContactsData(){
		var datas = new Array();
		$("input[name=id]:checked").each(function(){
			var item = new Object();
			var id = $(this).val();
			var name= $("td[name=contactName"+id+"]").html();
			var email= $("td[name=contactEmail"+id+"]").html();
			var phone= $("td[name=contactPhone"+id+"]").html();
			var mobile= $("td[name=contactMobile"+id+"]").html();
			var userId = $("input[name=userId_"+id+"]").val();
			item.name = name;
			item.email = email;
			/*item.phone = phone || mobile;*/
			item.phone = mobile;
			item.userId = userId;
			datas.push(item);
		});
		return datas;
	}
	
	$(document).ready(function(){
		if ($.browser.msie && $.browser.version<10) {
			$("input[name=keyword]").watermark("${LANG['website.user.contact.list.placeholder1']}");
		}
		$("#checkAll").click(function(){
			if($(this).attr("checked")){
				$("input[name=id]").attr("checked",true);				
			}else{
				$("input[name=id]").attr("checked",false);
			}
		});
		$("input[name=id]").click(function(){
			if($("input[name=id]").length == $("input[name=id]:checked").length){
				$("#checkAll").attr("checked",true);
			}else{
				$("#checkAll").attr("checked",false);
			}
		});
		
		$("input[name=keyword]").keyup(function(event){
			if(event.keyCode=='13'){
				$("#pageNo").val("");
				$("#query").submit();
			}
		});
	});
	
	//排序
	function sort(sort){
		//alert("${SORT_DESC}");//1
		//alert("${SORT_ASC}");//0
		//alert("${EVENTLOG_SORT_STATUS}");//1
		var formId=($("#sortord").closest("form").attr("id"));
		var oldSortType=$("#sortord").val();
		if(oldSortType=="${SORT_DESC}"){
			$("#sortord").val("${SORT_ASC}");
		}else{
			$("#sortord").val("${SORT_DESC}");
		}
		$("#"+formId).submit();
	}
	
	function submitForm(){
		$("#pageNo").val("");
		query.submit();
	}
	function closeDialog() {
		var dialog = parent.$("#importContact");
		dialog.trigger("closeDialog");
	}
</script>
</body>
</html>