<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>index</title>
	<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
	<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.adm.settings.css" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.adm.validate.css" />
	<cc:base var="NOTICE_FLAG_ANYONE"/>
	<cc:base var="NOTICE_FLAG_LOGINED"/>
</head>
<body style="padding: 0px;">
	<div class="manager-body">
		<form id="noticeForm" action="" method="post" class="noticeForm">
			<div class="form-body">
				<div class="form-content notice-content">
					<div class="form-item">
						<label class="title">${LANG['website.admin.notice.common.noticetheme'] }：</label>
						<div class="widget">
							<input id="title" name="title" type="text" class="input-text" value="${notice.title }" />
							<span class="form-required"></span>
							<span class="form-tip"><span id="titleTip"></span></span>
						</div>
					</div>
					<div class="form-item">
						<label class="title">${LANG['website.admin.notice.edit.readauthority']}：</label>
						<div class="widget">
							<input type="radio" name="userRole" id="allUserRole" value="0" checked="checked" />
							<label for="allUserRole">${LANG['website.admin.notice.edit.readauthority.all']}</label>
							<input type="radio" name="userRole" id="loginUserRole" value="1" style="margin-left: 15px;" />
							<label for="loginUserRole">${LANG['website.admin.notice.edit.readauthority.loginuserolny']}</label>
						</div>
					</div>
					<div class="form-item remark-widget">
						<label class="title">${LANG['website.admin.notice.common.noticecontent']}：</label>
						<div class="widget notice-area-widget">
							<textarea class="input-area"  id="content" style="width: 360px;margin-right: 10px;">${notice.content }</textarea>
							<span class="form-required"></span>
							<span class="form-tip count">
								<span id='nowCountSpan'>0</span>/256
							</span>						
						</div>
					</div>			
				</div>
			</div>
			<div class="form-buttons">
				<input type="submit" class="button input-gray" value="${LANG['website.common.option.confirm']}"> 
				<a class="button anchor-cancel">${LANG['website.common.option.cancel']}</a>
			</div>
		</form>
	</div>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/formValidator-4.1.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/formValidatorRegex.js"></script>
<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>
<script type="text/javascript">
var validString = {
		title: {
			onShow: "${LANG['website.admin.notice.edit.warninfo0']}",
			onFocus: "${LANG['website.admin.notice.edit.warninfo0']}",
			onCorrect: "",
			emptyError: "${LANG['website.admin.notice.edit.warninfo1']}",
			onInputError: "${LANG['website.admin.notice.edit.warninfo0']}"
		},
		remark: {
			onInputError: "${LANG['website.admin.notice.edit.warninfo2']}"
		}
		
};
$(document).ready(function(){
		var loginFlag = "${notice.loginedFlag}";
		var NOTICE_FLAG_ANYONE = "${NOTICE_FLAG_ANYONE}";
		var NOTICE_FLAG_LOGINED = "${NOTICE_FLAG_LOGINED}";
		if(NOTICE_FLAG_ANYONE == loginFlag){
			$("#allUserRole").attr("checked",'checked');
		}else if (NOTICE_FLAG_LOGINED == loginFlag){
			$("#loginUserRole").attr("checked",'checked');
		}
	
		$.formValidator.initConfig({
			oneByOneVerify:true,
			wideWord: true,
			formID : "noticeForm",
			onSuccess : function() {
				saveNotice();
			},
			debug : false,
			onError : function(msg,obj,errorlist) {
				if(msg && msg==validString.remark.onInputError) {
					$(".count").css("color", "#C91600");
				}
			}
		});
		$("#title").formValidator({
			onShow : validString.title.onShow,
			onFocus : validString.title.onFocus,
			onCorrect : validString.title.onCorrect
		}).inputValidator({
			min : 2,
			max : 32,
			empty : {
				leftEmpty : false,
				rightEmpty : false,
				emptyError : validString.title.emptyError
			},
			onError : validString.title.onInputError
		});
		
		$("#content").formValidator({
			onShow : "",
			onFocus : "",
			onCorrect : ""
		}).inputValidator({
			min : 1,
			max : 256,
			onError : validString.remark.onInputError
		});
		setCursor("title", $("#title").val().length);
		var content = "${notice.content }";
		$("#nowCountSpan").text(content.Lenb());     //计算公告内容字长
		
		$("#content").keyup(function() {
			var nowLength=$(this).val().Lenb();
			if (nowLength>256) {
				$(".count").css("color", "#C91600");
			} else {
				$(".count").css("color", "#B0B0B0");
			}
			$("#nowCountSpan").text(nowLength);
		}).focusin(function() {
			$(".count").css("color", "#B0B0B0");
		}).focusout(function() {
			var nowLength=$(this).val().Lenb();
			if (nowLength<=0 || nowLength>256) {
				$(".count").css("color", "#C91600");				
			}
		}).bind('paste', function(){
			$(this).prev('label').hide();
		});
});
function saveNotice() {
	var frame = parent.$("#noticeDiv");
	var noticeID = "${notice.id}";
	var notice = {};
	notice.title = $("#title").val();
	notice.content = $("#content").val();
	notice.loginedFlag = $('input:radio[name="userRole"]:checked').val();
	if (noticeID && noticeID.length>0) {
		notice.id = noticeID;
		app.updateSiteNotice(notice, function(result) {
			frame.trigger("closeDialog", [result]);
		},{message:"${LANG['system.site.change']}", ui:parent});
	} else {
		app.createSiteNotice(notice, function(result) {
			frame.trigger("closeDialog", [result]);
		},{message:"${LANG['system.site.save']}", ui:parent});	
	}
}
</script>
</body>
</html>