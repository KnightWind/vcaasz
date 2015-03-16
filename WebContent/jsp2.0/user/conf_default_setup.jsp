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
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/settings.css?v=09" />
	<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.validate.css" />
	<cc:siteList var="TIMEZONE_WITH_CITY_LIST"/>
	<cc:siteList var="USER_FAVOR_PAGE_SIZE"/>
	<cc:siteList var="USER_FAVOR_LANGUAGE"/>
</head>
<body>
<div class="box">

	<div class="head">
<!-- 会议缺省设置 -->
		<span class="title">${LANG['website.user.leftmenu.config.default'] }</span>
		<span class="desc">${LANG['website.user.default.config.desc'] }</span>
	</div>
	<div class="body">
		<form action="/user/confConfig/updateConfConfig" method="post" id="config-form">
			<input type="hidden" value="${currentUser.id}" name="userId" id="userId"/>
			<input type="hidden" value="${config.id}" name="id" id="id"/>
			<div class="form-item">
				<!-- 会议缺省设置 -->
<!-- 				<div class="form-item"> -->
<!-- 				<label class="title">默认时区 ${LANG['website.common.symbol.colon'] }</label> -->
<!-- 				<div class="widget"> -->
<!-- 					<select id="timeZone" name="timeZone"> -->
<!-- 						<c:forEach var="eachTimeZone" items="${TIMEZONE_WITH_CITY_LIST}"> -->
<!-- 							<c:set var="eachLang" value="website.timezone.city.zone.${eachTimeZone.timeZoneId}"/> -->
<!-- 							<option value="${eachTimeZone.timeZoneId}" timeZone="${eachTimeZone.offset}" <c:if test="${eachTimeZone.timeZoneId == currentUser.timeZoneId}">selected="selected"</c:if> >${LANG[eachLang]} </option> -->
<!-- 						</c:forEach> -->
<!-- 					</select> -->
<!-- 				</div> -->
<!-- 			</div> -->

			<div class="form-item">
				<label class="title">${LANG['website.user.view.conf.conf.preHostor'] }${LANG['website.common.symbol.colon'] }</label>
				<div class="widget">
					<input id="compereType" type="radio" name="optionJbh" value="0" checked="checked"/>
					<label for="compereType" class="for-label">${LANG['website.user.view.conf.conf.preHostor.no'] }</label>

					<input id="freeType" type="radio" name="optionJbh" value="1"/>
					<label for="freeType" class="for-label">${LANG['website.user.view.conf.conf.preHostor.yes'] }</label>
				</div>
			</div>


			<div class="form-item">
				<label class="title">${LANG['user.menu.conf.pad.videooption'] }${LANG['website.common.symbol.colon'] }</label>
				<div class="widget">
					 <select id="optionStartType" name="optionStartType">
					 	<option value="1">${LANG['user.menu.conf.pad.videooption.on'] }</option>
					 	<option value="2">${LANG['user.menu.conf.pad.videooption.off'] }</option>
					 </select>
				</div>
			</div>
			
				<div class="form-item submit-item">
				<div class="widget">
					<button type="submit" class="input-gray">${LANG['website.common.option.save'] }</button>
					<c:if test="${!empty infoMessage}">
	        			<span class="message_span">
	        			<img src="/static/images/ys_r_bg.jpg" width="16" height="14" style="margin-left:15px;margin-top:5px;margin-right: 5px"/><label style='color:#258021'>${infoMessage}</label>
	        			</span>
		 			</c:if>
		 			<c:if test="${!empty errorMessage}">
						<span class="message_span">
		           		<img src="/static/images/ys_w_bg.jpg" width="16" height="14" style="margin-left:15px;margin-top:5px;margin-right: 5px;"/><label style='color:red'>${errorMessage}</label>
		           		</span>
	           		</c:if>
				</div>
			</div>
		</form>

	</div>

</div>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript">
	 
	 $(document).ready(function(){
		
		 $("#optionStartType option[value='${config.optionStartType}']").attr("selected",true);
		 $("input[name=optionJbh]").each(function(){
			 if($(this).val()=='${config.optionJbh}')$(this).attr("checked",true);
		 });
	 })
	 //保存
	 function save(){
		 var data = {};
		 data.id = $("#id").val();
		 data.userId = $("#userId").val();
		// data.timeZone = $("#timeZone").val();
		// data.timeZoneId = $("#timeZone option:selected").attr("timeZone");
		 var optionJbh = $('input:radio[name="optionJbh"]:checked').val();//周期选项0是不允许,1是允许
		 data.optionJbh = optionJbh;
		 data.optionStartType = $("#optionStartType").val();
		 $.ajax({
		      	type: "POST",	
		      	url:"/user/confConfig/updateConfConfig",
		      	data:data,
		      	//dataType:"json",
		      	success:function(data) {
		      		
		      	},
		        error:function(XMLHttpRequest, textStatus, errorThrown) {
		        	alert("error");
		        }
			}); 
		 
	 }
	 
</script>
</body>
</html>