<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
<!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
<!--[if lt IE 7]><script src="/assets/js/lib/belatedpng.js"></script><![endif]-->
<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.adm.validate.css" />
<link type="text/css" rel="stylesheet" href="/assets/css/apps/company-manage.css" />

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
<div class="box">
	<div class="head">
		<span class="title">${LANG['website.admin.sitebase.setting.headinfo0']}</span>
		<span class="desc">${LANG['website.admin.sitebase.setting.headinfo1']}</span>
	</div>
	<div class="body">
		<div class="fieldset">
			<form name="profileForm" id="profileForm" action="/admin/site/update" method="post">
			<div class="legend"><span> ${LANG['website.admin.sitebase.setting.siteinfo']}</span></div>
			<div class="form-item">
				<label class="title">${LANG['website.admin.sitebase.setting.sitename']}</label>
				<div class="widget">
					<input type="hidden" id="siteId" name="id" value="${siteBase.id }"/>
		    		<input id="siteName" name="siteName" class="input-text" type="text" value="${siteBase.siteName }"/>
					<span class="form-required"></span>
					<span class="form-tip"><span id="siteNameTip"></span></span>
				</div>
			</div>
			<div class="form-item">
				<label class="title">${LANG['website.admin.sitebase.setting.siteenname']}</label>
				<div class="widget">
					<input id="enName" name="enName" class="input-text" type="text" value="${siteBase.enName }"/>
					<span class="form-tip"><span id="enNameTip"></span></span>
				</div>
			</div>
			<div class="form-item" style="display:${(!empty siteBase.siteDiy && siteBase.siteDiy==1) ? '':'none'}">
				<label class="title">${LANG['website.admin.sitebase.setting.sitelogo']}</label>
				<div class="widget">
					<p class="">
						<c:choose>
						   <c:when test="${!empty siteBase.siteLogo}">
							   	<div style="background: #ccc;width:130px;height:60px;text-align: center;">
							   		<img id="previewImg" src="${siteBase.siteLogo}" alt="" onerror="this.src='/assets/images/icons/logo.png';" onload="javascript:DrawImage(this,120,60)"/>
							   	</div>
						   </c:when>
						   <c:otherwise>
							   	<div style="background: #ccc;width:120px;height:60px;text-align: center;">
									<img id="previewImg" src="/assets/images/icons/logo.png" alt="" onload="javascript:DrawImage(this,120,60)"/>
								</div>
						   </c:otherwise>
						</c:choose>
						<img id="previewLoadImg" src="/static/images/loading.gif" style="display: none;" />
						<input id="siteLogo" name="siteLogo" type="hidden" value="${siteBase.siteLogo}"/>
					</p>
					<p>
						<span class="gray">${LANG['website.admin.sitebase.setting.sitelogoproposal']}</span>
					</p>
					<p>
						<input id="backLogo" name="backLogo" type="checkbox" value=""/>
						<label for="resetLogo">${LANG['website.admin.sitebase.setting.sitelogorerecover']}</label>
					<p>
					<p>
						<iframe id="frameImg" src="/common/upload/preUpload" width="102" height="30" frameborder="0" scrolling="no"></iframe>
					<p>
				</div>
			</div>
<%--			<div class="form-item" style="display:${(!empty siteBase.siteDiy && siteBase.siteDiy==1) ? '':'none'}">--%>
			<div class="form-item" style="display:none">
				<label class="title">${LANG['website.admin.sitebase.setting.sitetopimage']}</label>
				<div class="widget">
					<p class="figure clearfix">
						<c:choose>
						   <c:when test="${!empty siteBase.siteBanner}">
						    <img id="bannerImg" src="${siteBase.siteBanner}" alt="${siteBase.siteBanner}" onerror="this.src='/assets/images/concat-phone.png';" onload="javascript:DrawImage(this,300,60)"/>
						   </c:when>
						   <c:otherwise>
							<img id="bannerImg" src="/assets/images/concat-phone.png" alt="" onload="javascript:DrawImage(this,300,60)"/>
						   </c:otherwise>
						</c:choose>
						<img id="loadImgBanner" src="/static/images/loading.gif" style="display: none;height: 32px;left: 20px;position: absolute;top: 5px;width: 32px;">
						<input id="siteBanner" name="siteBanner" type="hidden" value="${siteBase.siteBanner}"/>
					</p>
					<p>
						<span class="gray">${LANG['website.admin.sitebase.setting.sitetopimageproposal']}</span>
					</p>
					<p>
						<input id="backBanner" name="backBanner" type="checkbox" value=""/>
						<label for="resetImage">${LANG['website.admin.sitebase.setting.sitetopimagerecover']}</label>
					<p>
					<p>
						<iframe id="frameImg" src="/common/upload/preUpload?type=Banner" width="102" height="30" frameborder="0" scrolling="no"></iframe>
					<p>
				</div>
			</div>
			<div class="form-item" style="height: 35px;line-height: 28px;">
				<label class="title"></label>
				<div class="widget">
					<button class="input-gray" type="submit">${LANG['website.common.option.submit']}</button>
	           		<c:if test="${msgCode!=null && msgCode!='' && msgCode=='1'}">
		           		<img src="/static/images/ys_r_bg.jpg" width="16" height="14" style="margin-left:15px;margin-top:5px;margin-right: 5px" />
		           		<label style='color:#258021'>${LANG['bizconf.jsp.admin.siteBase_info.res5']}</label>
		           		<script type="text/javascript">
							function refreshLogo() {
								var url = $("#previewImg").attr("src");
								parent.$("#site-logo").attr("src", url);
							}
							refreshLogo();
						</script>
		           	</c:if>
		           	<c:if test="${msgCode!=null && msgCode!='' && msgCode=='2'}">
		           		<img src="/static/images/ys_w_bg.jpg" width="16" height="14" style="margin-left:15px;margin-top:5px;margin-right: 5px" />
		           		<label style='color:#258021'>${LANG['bizconf.jsp.admin.siteBase_info.res6']}</label>
		           	</c:if>
				</div>
			</div>
			</form>
		</div>
		<div class="fieldset lecense-info">
			<div class="legend"><span>${LANG['website.admin.sitebase.setting.sitebusinessinfo']}</span></div>
			<div class="form-item">
				<label class="title">${LANG['website.admin.sitebase.setting.siterentmodel']}</label>
				<c:choose>
			         <c:when test="${SITE_HIREMODE_MONTH == siteBase.hireMode }">
			         	<div class="widget">${LANG['website.admin.sitebase.setting.rentmodelmothly']}</div>
			      	 </c:when>
			         <c:when test="${SITE_HIREMODE_TIME == siteBase.hireMode }">
						<div class="widget">${LANG['website.admin.sitebase.setting.rentmodeltime']}</div>
			      	 </c:when>
			       	 <c:otherwise>
			            <div class="widget">--</div>
			       	 </c:otherwise>
				</c:choose>
			</div>
			<div class="form-item">
				<label class="title">${LANG['website.admin.sitebase.setting.sitetype']}</label>
				<c:choose>
		             <c:when test="${SITEFLAG_FORMAL == siteBase.siteFlag}">
		             	<div class="widget">${LANG['website.admin.sitebase.setting.sitetypeformal']}</div>
		             </c:when>
		             <c:when test="${SITEFLAG_TRY == siteBase.siteFlag}">
		             	<div class="widget">${LANG['website.admin.sitebase.setting.sitetypetry']}</div>
		             </c:when>
		     	     <c:otherwise>
			            <div class="widget">--</div>
			       	 </c:otherwise>
	            </c:choose>
			</div>
			<div class="form-item">
				<label class="title">${LANG['website.admin.sitebase.setting.siteexpiredate']}</label>
				<div class="widget"><fmt:formatDate  value="${siteBase.expireDate}" type="date" pattern="yyyy-MM-dd"/></div>
			</div>
			<!-- 主持人信息 -->
			<c:if test="${SITE_CHARGEMODE_NAMEHOST == siteBase.chargeMode }">
			<div class="form-item" style="display: none;">
                <label class="title">${LANG['website.admin.sitebase.setting.sitehostinfo']}</label>
                <div class="widget" style="padding-top:15px">
	                <table class="common-table" style="line-height:17px;">
		                <tbody>
							<tr height="30" class="host01">
						        <th class="email">${LANG['website.admin.sitebase.setting.sitehost.email']}</th>
						        <th class="name"> ${LANG['website.admin.sitebase.setting.sitehost.createcomperetime']}</th>
						        <th class="mobile">${LANG['website.user.billing.datadetail.licensenum']}</th>
 						        <th class="name">${LANG['website.admin.adminlist.page.title.option']}</th> 
<!-- 						        <th class="name">${LANG['website.admin.sitebase.setting.sitehost.expiretime']}</th> -->
						    </tr>
						    <c:if test="${fn:length(pageModel.datas)<=0 }">
						         <tr  height="36" class="host02">
						           <td colspan="4" width="540" align="center">
						        		${LANG['website.common.msg.list.nodata']}
						           </td>
						         </tr>
						    </c:if>
						    <c:forEach var="host" items = "${pageModel.datas}"  varStatus="status">
							    <tr>
							    	<td class="email">${host.userEmail}</td>
							    	<td class="name first-child">${host.createTime}</td>
							        <td class="mobile">${host.numPartis}</td>
 							        <td class="name"><a href="javascript:goModifyHost('${host.id}','${siteBase.id}');" title="${LANG['website.common.option.modify']}">${LANG['website.common.option.modify']}</a></td> 
<!-- 							        <td class="name last-child"><fmt:formatDate  value="${siteBase.expireDate}" type="date" pattern="yyyy-MM-dd"/></td> -->
							    </tr>
						    </c:forEach>
					    </tbody>
					</table>
				</div>
			 </div>
			 </c:if>
		</div>
	</div>
</div>
<script type="text/javascript" src="/assets/js/lib/formValidator-4.1.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/formValidatorRegex.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
<script type="text/javascript">
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
	setCursor("siteName", $("#siteName").val().length);
});
</script>
</body>
</html>