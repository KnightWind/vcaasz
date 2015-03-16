<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>index</title>
	<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
	<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/concat.css" />
	<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
	<script type="text/javascript">
	function DrawImage(ImgD,iwidth,iheight){
	    var image=new Image();
	    image.src=ImgD.src;
	    if(image.width>0 && image.height>0){
	      if(image.width/image.height>= iwidth/iheight){
	          if(image.width>iwidth){
	              ImgD.width=iwidth;
	              ImgD.height=(image.height*iwidth)/image.width;
	          }else{
	              ImgD.width=image.width;
	              ImgD.height=image.height;
	          }
	      }else{
	          if(image.height>iheight){
	              ImgD.height=iheight;
	              ImgD.width=(image.width*iheight)/image.height;
	          }else{
	              ImgD.width=image.width;
	              ImgD.height=image.height;
	          }
	      }
	    }
	}
	
	function goModifyHost(hostId,siteId) {
	    parent.goModifyHost(hostId,siteId);
	}
	function submitForm(){
		$("#pageNo").val("");
		query.submit();
	}
	</script>
<cc:siteList var="SITE_HIREMODE_MONTH"/>
<cc:siteList var="SITE_HIREMODE_TIME"/>
<cc:siteList var="SITE_CHARGEMODE_NAMEHOST"/>
<cc:siteList var="SITE_CHARGEMODE_ACTIVEUSER"/>
<cc:siteList var="SITE_CHARGEMODE_SEATES"/>
<cc:siteList var="SITE_CHARGEMODE_TIME"/>
<cc:siteList var="SITE_CHARGEMODE_USERTIME"/>
<cc:siteList var="SITEFLAG_FORMAL"/>
<cc:siteList var="SITEFLAG_TRY"/>
</head>
<body>
<div class="tabview">
	<div class="views" style="width: 99%;">
		<form id="query" name="query" action="/admin/site/listHosts" method="post">
		<input type="hidden"  name="group_id" value="${group_id}"/>
		<div class="summary">
			<ul class="actions">
<%--				<li class=""><a onclick="return exportExcel();"><i class="icon icon-exp"></i>${LANG['website.admin.user.list.page.btn.export'] }</a></li>--%>
			</ul>
			<div class="search">
				<input type="text" name="keyword"  value="${keyword}" class="input-text" placeholder="${LANG['website.user.contact.list.placeholder1']}" />
				<button class="submit-search" type="submit" onclick="submitForm();" >${LANG['swebsite.common.search.name']}</button>
			</div>
		</div>
		<div class="view active" data-view="users" >
			<c:if test="${fn:length(pageModel.datas) gt 0}">
				<table class="common-table contact-list-table" style="width: 100%;">
					<tbody>
						<tr>
<%--							<th class="check"><input type="checkbox"  id="checkAll" /></th>--%>
							<th class="name"> ${LANG['website.user.org.regsiter.list.name']}</th>
							<th class="email">${LANG['website.admin.sitebase.setting.sitehost.email']}</th>
					        <th class="mobile"> ${LANG['website.user.contact.edit.telphone']}</th>
					        <th class="mobile">${LANG['website.user.billing.datadetail.licensenum']}</th>
						    <th class="name">${LANG['website.admin.adminlist.page.title.option']}</th> 
						</tr>
						
					    <c:forEach var="host" items = "${pageModel.datas}"  varStatus="status">
						    <tr>
<%--						    	<td class="first-child check">--%>
<%--									<input name="id" type="checkbox" value="${host.id}" />--%>
<%--									<input name="userId_${host.id}" type="hidden" value="${host.id}" />--%>
<%--								</td>--%>
						    	<td class="name">${host.trueName}</td>
						    	<td class="email">${host.userEmail}</td>
						    	<td class="mobile">${host.mobile}</td>
						        <td class="mobile">${host.numPartis}</td>
							    <td class="name"><a href="javascript:goModifyHost('${host.id}','${siteBase.id}');" title="${LANG['website.common.option.modify']}">${LANG['website.common.option.modify']}</a></td> 
						    </tr>
					    </c:forEach>
					</tbody>
				</table>
				<div class="pagebar clearfix">
					<jsp:include page="page.jsp" />
				</div>
			</c:if>
			
			<c:if test="${fn:length(pageModel.datas) lt 1}">
				<div class="module">
					<div class="nodata">${LANG['website.user.contact.list.nodata']}</div>
				</div>
			</c:if>
		</div>
	</form>
	</div>
</div>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery.watermark.js"></script>
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript" src="/assets/js/lib/formValidator-4.1.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/formValidatorRegex.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
<script type="text/javascript">

//批量导出用户
function exportExcel(){
	$("#query").attr("action","/admin/site/exportHosts");
	$("#query").submit();
	$("#query").attr("action","/admin/site/listHosts");
}
function uploadCallback(url){
	$("#previewLoadImg").hide();
	$("#frameImg").attr("src", "/common/upload/preUpload");
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
var validString = {
		siteName: {
			onShow: "${LANG['website.admin.profile.val.truename'] }",
			onFocus: "${LANG['website.admin.profile.val.truename'] }",
			onCorrect: "",
			emptyError: "${LANG['website.user.profile.val.truename.space'] }",
			onInputError: "${LANG['website.admin.profile.val.truename'] }",
			onRegExpError: "${LANG['website.admin.profile.val.truename'] }"
		},
		enName: {
			onShow: "${LANG['website.admin.profile.val.enname'] }",
			onFocus: "${LANG['website.admin.profile.val.enname'] }",
			onCorrect: "",
			onEmpty: "",
			emptyError: "${LANG['website.user.profile.val.enname.space'] }",
			onInputError: "${LANG['website.admin.profile.val.enname'] }",
			onRegExpError: "${LANG['website.admin.profile.val.enname'] }"
		},
		host:{
			title:"${LANG['bizconf.jsp.system.index.res1']}"
		}
};
$(document).ready(function(){
	
	//parent.refreshIHeight();
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
			$("#query").attr("action","/admin/site/listHosts");
			$("#query").submit();
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
	});
	$("input[name='backLogo']").change(function() {
		var value = $(this).val();
		var checked = $(this).attr("checked");
		if(checked=="checked"){
			$("#siteLogo").val("");
		} else {
			$("#siteLogo").val(value);
		}
	});
	$.formValidator.initConfig({
		oneByOneVerify:true,
		formID : "profileForm",
		debug : false,
		onError : function() {
			//alert("具体错误，请看网页上的提示");
		}
	});
	$("#siteName").formValidator({
		onShow : validString.siteName.onShow,
		onFocus : validString.siteName.onFocus,
		onCorrect : validString.siteName.onCorrect
	}).inputValidator({
		min : 1,
		max : 20,
		empty : {
			leftEmpty : false,
			rightEmpty : false,
			emptyError : validString.siteName.emptyError
		},
		onError : validString.siteName.onInputError
	});
	$("#enName").formValidator({
		empty:true,
		onShow : validString.enName.onShow,
		onFocus : validString.enName.onFocus,
		onCorrect : validString.enName.onCorrect,
		onEmpty:validString.enName.onEmpty
	}).inputValidator({
		max: 70,
		onError : validString.enName.onInputError
	}).regexValidator({
			regExp : "siteEnName",
			dataType : "enum",
			onError : validString.enName.onRegExpError
	});
	//setCursor("siteName", $("#siteName").val().length);
});
</script>
</body>
</html>