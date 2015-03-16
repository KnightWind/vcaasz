<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${LANG['bizconf.jsp.conf_default_setup.res1']}</title>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/enterprise/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/common.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery.uniform/themes/default/css/uniform.custom.css?ver=${version}">

<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js?ver=${version}"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-validation-1.10.0/dist/jquery.validate.js?ver=${version}"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/tipsy-master/src/javascripts/jquery.tipsy.js?ver=${version}"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery.uniform.min.js?ver=${version}"></script>
<cc:confList var="CONF_VIDEO_TYPE_WEBBAND"/>
<cc:confList var="CONF_VIDEO_TYPE_FLUENCY"/>
<cc:confList var="CONF_VIDEO_TYPE_DISTINCT"/>
<cc:confList var="CONF_VIDEO_TYPE_BEST"/>
<c:set var="CONF_VIDEO_TYPE_FLUENCY" value="${CONF_VIDEO_TYPE_FLUENCY }"></c:set>
<c:set var="CONF_VIDEO_TYPE_DISTINCT" value="${CONF_VIDEO_TYPE_DISTINCT }"></c:set>
<c:set var="CONF_VIDEO_TYPE_WEBBAND" value="${CONF_VIDEO_TYPE_WEBBAND }"></c:set>
<script type="text/javascript">
$(function() {
	
	var maxVideoNum = parseInt("${confConfig.maxVideo}", 10);
	var maxAudioNum = parseInt("${confConfig.maxAudio}", 10);
	var allowCall = parseInt("${confConfig.allowCall}", 10);
	var empowerVideoNum = parseInt("${userEmpower.videoNumber}", 10);
	var empowerAudioNum = parseInt("${userEmpower.audioNumber}", 10);
	if(maxVideoNum>empowerVideoNum){
		maxVideoNum = empowerVideoNum;
	}
	if(maxAudioNum>empowerAudioNum){
		maxAudioNum = empowerAudioNum;
	}
	$("#maxVideo").attr("value",maxVideoNum);
	$("#maxAudio").attr("value",maxAudioNum);
	var videoType = "${confConfig.videoType}";
	if(videoType=="${CONF_VIDEO_TYPE_WEBBAND}"){
		$("input:radio[name=videoType]:eq(0)").attr("checked",'checked');
	} else if(videoType=="${CONF_VIDEO_TYPE_FLUENCY}"){
		$("input:radio[name=videoType]:eq(1)").attr("checked",'checked');
	} else if(videoType=="${CONF_VIDEO_TYPE_DISTINCT}"){
		$("input:radio[name=videoType]:eq(2)").attr("checked",'checked');
	} else if(videoType=="${CONF_VIDEO_TYPE_BEST}"){
		$("input:radio[name=videoType]:eq(3)").attr("checked",'checked');
	} 
	$("#aheadTimes").val("${confConfig.aheadTimes}");
	//${LANG['bizconf.jsp.conf_default_setup.res2']}
	
	var isAudioFlag = "${isAudioFlag}";
	if(isAudioFlag){
		$(".audioTR").show();
	}else{
		$(".audioTR").hide();
	}
	
	var isVideoFlag = "${isVideoFlag}"; 
	if(isVideoFlag){
		$(".videoTR").show();
	} else {
		$(".videoTR").hide();
	}
	
	//${LANG['bizconf.jsp.conf_default_setup.res3']}
	if("${confModel}" == 1){
		$("input:radio[name=confModel]:eq(0)").attr("checked",'checked');
	}else if("${confModel}" == 0){
		$("input:radio[name=confModel]:eq(1)").attr("checked",'checked');
	}
	
	//${LANG['bizconf.jsp.conf_default_setup.res4']}
	if("${micStatus}" == 1){
		$("input:radio[name=micStatus]:eq(0)").attr("checked",'checked');
	}

	//${LANG['bizconf.jsp.conf_default_setup.res5']}
	var isPhoneFlag = "${isPhoneFlag}";
	if(isPhoneFlag){
		var phoheFuc = "${confConfig.confType}"; 
		if( phoheFuc == "1"){
			$("input:radio[name=confType]:eq(0)").attr("checked",'checked');
		} else {
			$(".callFunc").hide();
		}
	}else{
		$(".phoneFuc").hide();
		$(".callFunc").hide();
	}
/*
	var isAutoFlag = "${isAutoFlag}";
	$('input:radio[name="confType"]').change(function() {
		var confTypeValue = $(this).val();
		if (isAutoFlag && confTypeValue=="1") {
			$(".callFunc").show();
		} else {
			$(".callFunc").hide();
			$("input:radio[name=allowCall]:eq(1)").attr("checked",'checked');
		}
	});
	*/
	var isOutCallFlag = "${isOutCallFlag}";
	$('input:radio[name="confType"]').change(function() {
		var confTypeValue = $(this).val();
		if (isOutCallFlag && confTypeValue=="1") {
			$(".callFunc").show();
		} else {
			$(".callFunc").hide();
			$("input:radio[name=allowCall]:eq(1)").attr("checked",'checked');
		}
	});
	
	//外呼
	if(isOutCallFlag){
		var callFunc = allowCall; 
		if(callFunc=="1"){
			$("input:radio[name=allowCall]:eq(0)").attr("checked",'checked');
		}
	}else{
		$(".callFunc").hide();
	}	
	
	//${LANG['bizconf.jsp.conf_default_setup.res8']}
	if("${changePage}"==1){
		$("input:checkbox[name=changePage]:eq(0)").attr("checked",'checked');
	}
	if("${annotate}"==1){
		$("input:checkbox[name=annotate]:eq(0)").attr("checked",'checked');
	}
	if("${chatAnyOne}"==1){
		$("input:checkbox[name=chatAnyOne]:eq(0)").attr("checked",'checked');
	}
	if("${chatCompere}"==1){
		$("input:checkbox[name=chatCompere]:eq(0)").attr("checked",'checked');
	}
	if("${chatParticipants}"==1){
		$("input:checkbox[name=chatParticipants]:eq(0)").attr("checked",'checked');
	}
	
	//${LANG['bizconf.jsp.conf_default_setup.res9']}
	if("${shareDocs}"==1){
		$("input:checkbox[name=shareDocs]:eq(0)").attr("checked",'checked');
	}
	if("${shareScreen}"==1){
		$("input:checkbox[name=shareScreen]:eq(0)").attr("checked",'checked');
	}
	var isShareMediaFlag = "${isShareMediaFlag}";
	if(isShareMediaFlag){
		if("${shareMedia}"==1){
			$("input:checkbox[name=shareMedia]:eq(0)").attr("checked",'checked');
		}	
	} else {
		$("#shareMedia").hide();
		$("#shareMediaM").hide();
		$("input:checkbox[name=shareMedia]:eq(0)").attr("disabled",'disabled');
	}
	if("${whiteBoard}"==1){
		$("input:checkbox[name=whiteBoard]:eq(0)").attr("checked",'checked');
	}
	if("${fileTrans}"==1){
		$("input:checkbox[name=fileTrans]:eq(0)").attr("checked",'checked');
	}
	var isRecordFlag = "${isRecordFlag}";
	if(isRecordFlag) {
		if("${record}"==1){
			$("input:checkbox[name=record]:eq(0)").attr("checked",'checked');
		}	
	} else {
		$("#record").hide();
		$("#recordM").hide();
		$("input:checkbox[name=record]:eq(0)").attr("disabled",'disabled');
	}
});
</script>
<script type="text/javascript">
var confConfigForm = null;
var maxUser = parseInt("${defaultLicence}", 10);
$(function() {
	var ruleString = {
		required: {
			"maxUser": "${LANG['bizconf.jsp.conf_default_setup.res10']}"
		},
		rangelength: {
			"maxUser": "${LANG['bizconf.jsp.conf_default_setup.res11']}2${LANG['bizconf.jsp.conf_default_setup.res12']}, ${LANG['bizconf.jsp.conf_default_setup.res13']}"+maxUser+"${LANG['bizconf.jsp.conf_default_setup.res12']}",
			"aheadTimes": "${LANG['valid.input.aheadTimes']}"
		}
	};
	$("#confConfigForm").find("input, select").not(".skipThese").uniform();
	$("#confConfigForm :input").tipsy({ trigger: 'manual', fade: false, gravity: 'sw', opacity: 0.65 });
	
	confConfigForm = $("#confConfigForm").validate({
		onkeyup: false,
		onblur: true,
		errorClass: "warning",
		rules: {
            'maxUser' : {required: true, min:2, max:maxUser},
            'aheadTimes': {min:0, max:60}
        },
        messages: {
            'maxUser' : {required: ruleString.required.maxUser, min: ruleString.rangelength.maxUser, max: ruleString.rangelength.maxUser},
            'aheadTimes': {min: ruleString.rangelength.aheadTimes, max: ruleString.rangelength.aheadTimes}
        },
        success: function (label) {
            $(label).each(function () {
                $('#' + this.htmlFor).tipsy('hide').removeAttr('original-title');
            });
        },
        errorPlacement: function (error, element) {
        	var errorEl = $(".tipsy");
        	var errorText = error.text();
        	if (!errorEl || errorEl.length==0) {
	            element.attr('original-title', errorText);
                element.tipsy('show');	
        	} else {
        		//for update first tip div title
	        	var elTitle = element.attr('original-title');
	        	if (elTitle && elTitle.length>0 && elTitle!=errorText) {
	        		element.attr('original-title', error.text());
	                element.tipsy('show');	
	        	}
        	}
        }
	});	
});

function saveInfo() {
	if(confConfigForm.form()) {
		document.getElementsByName("confConfigForm")[0].submit();	
	}	
}

</script>
</head>
<body>
<div class="main_content">
<form name="confConfigForm" id="confConfigForm" action="/user/confConfig/updateConfConfig" method="post">
  <div class="intercalate_main_top_01">
	<h3>${LANG['bizconf.jsp.conf_default_setup.res1']}</h3>
    <p>${LANG['bizconf.jsp.conf_default_setup.res14']}, ${LANG['bizconf.jsp.conf_default_setup.res15']}</p>
  </div>
  <table class="Personal_settings_main_01" cellpadding="0" cellspacing="0" border="0" >
<!--   	<tr height="40"> -->
<!--     	<td align="right" width="160">${LANG['bizconf.jsp.conf_default_setup.res16']}</td> -->
<!--         <td align="left" class="confsetTD"> -->
<%--         	<input class=" skipThese" id="maxUser" name="maxUser" type="text"  value="${confConfig.maxUser}"/> --%>
<%--         	&nbsp;<span style="color: red">${LANG['bizconf.jsp.conf_default_setup.res17']}: ${defaultLicence }</span> --%>
<!--         </td> -->
<!--     </tr> -->  
    <tr height="40" class="audioTR">
    	<td align="right" width="160">${LANG['bizconf.jsp.conf_default_setup.res18']}</td>
        <td align="left" class="confsetTD">
        	<select name="maxAudio" id="maxAudio" class="video_channels">
				<c:forEach var="h" begin="0" end="${audioNumber }" step="1">
					<option value="${h}">${h} ${LANG['bizconf.jsp.conf_default_setup.res19']}</option>
				</c:forEach>
		 	</select>
	 	</td>
    </tr>
    
    <tr height="40" class="videoTR">
    	<td align="right" width="160">${LANG['bizconf.jsp.conf_default_setup.res20']}</td>
        <td align="left" class="confsetTD">
	        <select name="maxVideo" id="maxVideo" class="video_channels">
	        	<c:forEach var="h" begin="0" end="${videoNumber }" step="1">
	        		<c:if test="${h<7 || h==16}">
	        			<option value="${h}">${h} ${LANG['bizconf.jsp.conf_default_setup.res19']}</option>
	        		</c:if>
				</c:forEach>
		    </select>
        </td>
    </tr>
    <tr height="40">
    	<td align="right" width="160">${LANG['bizconf.jsp.conf_default_setup.res21']}</td>
        <td align="left" class="confsetTD">
        	<ul class="video_set">
        	    <cc:confList var="CONF_VIDEO_TYPE_FLUENCY"/>
	            <cc:confList var="CONF_VIDEO_TYPE_DISTINCT"/>
	            <cc:confList var="CONF_VIDEO_TYPE_WEBBAND"/>
	            <cc:confList var="CONF_VIDEO_TYPE_BEST"/>
	            
	            <cc:confList var="CONF_VIDEO_TYPE_FLUENCY_CODE"/>
	            <cc:confList var="CONF_VIDEO_TYPE_DISTINCT_CODE"/>
	            <cc:confList var="CONF_VIDEO_TYPE_WEBBAND_CODE"/>
	            <cc:confList var="CONF_VIDEO_TYPE_BEST_CODE"/>
	            <li><input name="videoType" type="radio" value="${CONF_VIDEO_TYPE_WEBBAND }" <c:if test="${userEmpower.dpiNumber==CONF_VIDEO_TYPE_WEBBAND_CODE }"> checked="checked" </c:if>/>${LANG['bizconf.jsp.conf_default_setup.res24']}</li>
	            <c:if test="${userEmpower.dpiNumber>=CONF_VIDEO_TYPE_FLUENCY_CODE }">
        		<li><input name="videoType" type="radio" value="${CONF_VIDEO_TYPE_FLUENCY }" checked="checked"/>${LANG['bizconf.jsp.conf_default_setup.res22']}</li>
        		</c:if>
        		<c:if test="${userEmpower.dpiNumber>=CONF_VIDEO_TYPE_DISTINCT_CODE }">
                <li><input name="videoType" type="radio" value="${CONF_VIDEO_TYPE_DISTINCT }" />${LANG['bizconf.jsp.conf_default_setup.res23']}</li>
        		</c:if>
        		<c:if test="${userEmpower.dpiNumber>=CONF_VIDEO_TYPE_BEST_CODE }">
                <li><input name="videoType" type="radio" value="${CONF_VIDEO_TYPE_BEST }" />${LANG['system.quality.level.best']}</li>
        		</c:if>
                
           </ul>
       </td>
    </tr>
    
    <tr height="40">
    	<td align="right" width="160">${LANG['bizconf.jsp.conf_default_setup.res25']}</td>
        <td align="left" class="confsetTD">
        	<input class="Personal_settings_01 skipThese" id="aheadTimes" name="aheadTimes" type="text"  value="${confConfig.aheadTimes}" />${LANG['bizconf.jsp.attended_conf_list.res10']}</td>
    </tr>
    
     	<tr height="40">
    	<td align="right" width="160">${LANG['bizconf.jsp.conf_default_setup.res3']}</td>
        <td align="left" class="confsetTD">
        	<input class="" name="confModel" type="radio" value="1" checked="checked"/>${LANG['bizconf.jsp.conf_default_setup.res26']}
          	<input class="" name="confModel" type="radio" value="0" />${LANG['bizconf.jsp.conf_default_setup.res27']}</td>
        </td>
    </tr>        
 	<tr height="40">
    	<td align="right" width="160">${LANG['bizconf.jsp.conf_default_setup.res4']}</td>   <!-- ${LANG['bizconf.jsp.conf_default_setup.res28']} -->
        <td align="left" class="confsetTD">
          	<input class="" name="micStatus" type="radio" value="1" />${LANG['bizconf.jsp.conf_default_setup.res29']}
          	<input class="" name="micStatus" type="radio" value="0" checked="checked"/>${LANG['bizconf.jsp.conf_default_setup.res30']}
        </td>
    </tr>        
 	<tr height="40" class="phoneFuc">
    	<td align="right" width="160">${LANG['bizconf.jsp.conf_default_setup.res5']}</td>
        <td align="left" class="confsetTD">
          	<input class="" name="confType" type="radio" value="1" />${LANG['bizconf.jsp.conf_default_setup.res31']}
          	<input class="" name="confType" type="radio" value="0" checked="checked"/>${LANG['bizconf.jsp.attendConfloglist.res10']}
        </td>
    </tr>        
 	<tr height="40" class="callFunc">
    	<td align="right" width="160">${LANG['bizconf.jsp.conf_default_setup.res32']}</td>
        <td align="left" class="confsetTD">
          	<input class="" name="allowCall" type="radio" value="1" />${LANG['bizconf.jsp.conf_default_setup.res31']}
          	<input class="" name="allowCall" type="radio" value="0" checked="checked"/>${LANG['bizconf.jsp.attendConfloglist.res10']}
        </td>
    </tr>  
    
    <tr height="40">
    	<td align="right" width="160">${LANG['bizconf.jsp.conf_default_setup.res33']}</td>
        <td align="left" class="authority_set confsetTD">
        	<input name="changePage" type="checkbox" value="1" />${LANG['bizconf.jsp.conf_default_setup.res34']}
        	<input name="annotate" type="checkbox" value="1" />${LANG['bizconf.jsp.conf_default_setup.res35']}
            <input name="chatAnyOne" type="checkbox" value="1" />${LANG['bizconf.jsp.conf_default_setup.res36']}
            <input name="chatCompere" type="checkbox" value="1" />${LANG['bizconf.jsp.conf_default_setup.res37']}
            <input name="chatParticipants" type="checkbox" value="1" />${LANG['bizconf.jsp.conf_default_setup.res38']}</td>
    </tr>
    <tr height="40">
    	<td align="right" width="160">${LANG['bizconf.jsp.conf_default_setup.res9']}</td>
        <td align="left"  class="authority_set01 confsetTD">
	        <input name="shareDocs" type="checkbox" value="1" />${LANG['bizconf.jsp.conf_default_setup.res39']}
	        <input name="shareScreen" type="checkbox" value="1" />${LANG['bizconf.jsp.conf_default_setup.res40']}
	        <input name="shareMedia" id="shareMedia" type="checkbox" value="1" /><label id="shareMediaM">${LANG['bizconf.jsp.conf_default_setup.res41']}</label>
	        <input name="whiteBoard" type="checkbox" value="1" />${LANG['bizconf.jsp.conf_default_setup.res42']}
	        <input name="fileTrans" type="checkbox" value="1" />${LANG['bizconf.jsp.conf_default_setup.res43']}
	        <input name="record" id="record" type="checkbox" value="1" /><label id="recordM">${LANG['bizconf.jsp.conf_default_setup.res44']}</label>
        </td>
    </tr>
    <tr>
    	<td></td>
        <td>
        <div style="margin-top: 20px;">
        <a class="Personal_settings_main_hold" style="float:left;margin-top:0px;" href="javascript:;" onclick="saveInfo()">
        	<img src="/static/images/ys_r_bg.png" width="16" height="14" align="absmiddle" style=" margin-right:5px;" />${LANG['bizconf.jsp.conf_default_setup.res45']}</a>
	        	<c:if test="${!empty infoMessage}">
		 				<img src="/static/images/ys_r_bg.jpg" width="16" height="14" style="margin-left:15px;margin-top:5px;margin-right: 5px"/><label style='color:#258021'>${infoMessage}</label>
		 			</c:if>
					<c:if test="${!empty errorMessage}">
		           		<img src="/static/images/ys_w_bg.jpg" width="16" height="14" style="margin-left:15px;margin-top:5px;margin-right: 5px;"/><label style='color:red'>${errorMessage}</label>
	           	</c:if>
        </div>
        
        	</td>
        <td></td>
    </tr>
    <tr height="40">
    	<td></td>
        <td>
			&nbsp;
        </td>
        <td></td>
    </tr>
  </table>
</form>
</body>
</html>
