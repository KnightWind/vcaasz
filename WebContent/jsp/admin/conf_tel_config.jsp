<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${LANG['bizconf.jsp.admin.conf_tel_config.res1']}</title>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/enterprise/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/enterprise/rightbox.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery.uniform/themes/default/css/uniform.custom.css">

<script type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery.uniform/jquery.uniform.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-validation-1.10.0/dist/jquery.validate.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/tipsy-master/src/javascripts/jquery.tipsy.js"></script>

<%-- <cc:confList var="CONF_CONFIG_ALLOW_PHONE_ENABLED"/><cc:confList var="CONF_CONFIG_ALLOW_PHONE_DISABLED"/><cc:confList var="CONF_CONFIG_ALLOW_PHONE_AUTOABLED"/> --%>
<%-- <cc:confList var="CONF_CONFIG_AUTO_CALL_ENABLED"/><cc:confList var="CONF_CONFIG_AUTO_CALL_DISABLED"/><cc:confList var="CONF_CONFIG_AUTO_CALL_AUTOABLED"/> --%>
<%-- <cc:confList var="CONF_CONFIG_VOIP_ENABLED"/><cc:confList var="CONF_CONFIG_VOIP_DISABLED"/><cc:confList var="CONF_CONFIG_VOIP_AUTOABLED"/> --%>
<cc:siteList var="EMPOWER_DISABLED"/>
<cc:siteList var="EMPOWER_ENABLED"/>
<cc:siteList var="EMPOWER_CODE_FIELD_ARRAY"/>

	<script type="text/javascript">
		var outCallFlag = "${sitePower.outCallFlag}";
		$(document).ready(function(){
			$("input[name='outCallFlag']").attr("disabled", "disabled");
			var checked = $("input[name='phoneFlag']").attr("checked");
			if(checked=="checked" && outCallFlag != 0){
				$("input[name='outCallFlag']").removeAttr("disabled");
			}
		});
		$(function() {
			$("input:radio[name=phoneFlag]").change(function() {
				var value = $(this).val();
				if(value==1  && outCallFlag != 0){
					$("input:radio[name=outCallFlag]").removeAttr("disabled");
				} else {
					$("input:radio[name=outCallFlag]").attr("disabled",'disabled');
					$("input:radio[name=outCallFlag]:eq(1)").attr("checked",'checked');
				}
			});
		});
	</script>	  
<!-- <script type="text/javascript"> -->
<!-- 	$(document).ready(function() { -->
<!-- 		$("input").not(".skipThese").uniform(); -->
<!-- 		$(".allowTelFlag").change(function() { -->
<!-- 			var value = $(this).val(); -->
<%-- 			if (value==${CONF_CONFIG_ALLOW_PHONE_DISABLED}) { --%>
<!-- 				$(".needEnable").rules("remove"); -->
<!-- 				$(".needDisable").rules("remove"); -->
<!-- 				$(".error-container").empty(); -->
<!-- 				$(".needEnable").attr("disabled", true); -->
<!-- 				$(".needDisable").attr("disabled", true); -->
<!-- 			} else { -->
<!-- 				$(".needEnable").rules("add", {required:true}); -->
<!-- 				$(".needDisable").rules("add", {required:true}); -->
				
<!-- 				$(".needEnable").attr("disabled", false); -->
<!-- 				$(".needDisable").attr("disabled", false); -->
<!-- 			} -->
<!-- 			$("input").not(".skipThese").uniform(); -->
<!-- 		}); -->
<!-- 		var v = $("#configForm").validate({ -->
<!-- 			errorClass: "warning", -->
<!-- 			rules: { -->
<!-- 				'allowTelFlag' : {required:true}, -->
<!-- 	            'autoCallFlag' : {required:true}, -->
<!-- 	            'voipFlag' : {required:true} -->
<!-- 	        }, -->
<!-- 	        messages: { -->
<!-- 	        	'allowTelFlag' : {required:"${LANG['bizconf.jsp.admin.conf_tel_config.res2']}"}, -->
<!-- 	            'autoCallFlag' : {required:"${LANG['bizconf.jsp.admin.conf_tel_config.res3']}"}, -->
<!-- 	            'voipFlag' : {required:"${LANG['bizconf.jsp.admin.conf_tel_config.res4']}VOIP"} -->
<!-- 	        }, -->
<!-- 	        success: function (label) { -->

<!-- 	        }, -->
<!-- 	        errorPlacement: function (error, element) { -->
<!-- 	        	  if ( element.is(":radio") ) { -->
<!-- 	        	        error.appendTo( element.closest("td").next().next() );   -->
<!-- 	        	  } -->
<!-- 	        } -->
<!-- 		});	 -->
<!-- 	}); -->
<!-- </script> -->
</head>
<body>
<div class="s_emile">
<div class="s_emile_01">
  <div class="s_emile_01_top"><span>${LANG['site.admin.conf.config.phone.title'] }</span></div>
  <form name="configForm" id="configForm" action="/admin/config/telconfig/update" method="post">
  <c:set var="flagCount" value="0"/>
  <c:forEach var="eachField" items="${EMPOWER_CODE_FIELD_ARRAY}" varStatus="fieldSatus">
   <c:if test="${fn:indexOf(eachField[1],'Flag') > -1 && eachField[2]==1}"><c:set var="flagCount" value="${flagCount+1 }"/></c:if>
     </c:forEach>
<c:if test="${powerCount > 0}">
	<table cellpadding="0" cellspacing="0" border="0" class="site_01">
  	<tr height="36">
  		<td colspan="4" align="left" class="operate_big" style=" border-bottom:#ADADAD 1px solid;"><span>${LANG['bizconf.jsp.admin.conf_tel_config.res5']}</span></td>
  	</tr>
    <c:forEach var="eachField" items="${EMPOWER_CODE_FIELD_ARRAY}" varStatus="fieldSatus">
    <c:set var="eachFieldName" value="${eachField[1]}"/>
   
    <c:if test="${sitePower[eachFieldName]==EMPOWER_ENABLED  && eachField[2]==1}">
    
     <tr height="30" align="center">
     		<td class="operate" width="200"><c:set var="langName" value="system.site.empower.item.${eachField[0]}"/>${LANG[langName]}</td>
<!--	        <td><div align="center">-->
<!--	        	<input type="radio" class="allowTelFlag" name="${eachFieldName}"  value="${EMPOWER_ENABLED}" -->
<!--	        	<c:if test="${globalConfig[eachFieldName]==EMPOWER_ENABLED }">checked</c:if>/>-->
<!--	        	</div>-->
<!--	       	</td>-->
	       	<td class="operate" width="200">
	       		<input type="radio" class="allowTelFlag" name="${eachFieldName}"  value="${EMPOWER_ENABLED}" 
	        	<c:if test="${globalConfig[eachFieldName]==EMPOWER_ENABLED }">checked</c:if>/>${LANG['bizconf.jsp.radio.status.opened']}
	       	</td>
	       	<td class="operate" width="200">
	       		<input type="radio" class="allowTelFlag" name="${eachFieldName}"  value="${EMPOWER_DISABLED}" 
	        	<c:if test="${globalConfig[eachFieldName]==EMPOWER_DISABLED }">checked</c:if>/>${LANG['bizconf.jsp.radio.status.closed']}
	       	</td>
	        <td width="auto" height="38px" class="error-container" style="border:0px;background:#FFFFFF;color:red;padding-left:10px;"></td>
	    </tr>
	   
	    </c:if>
    </c:forEach>
 
  </table>
  </c:if>
  
  <c:if test="${flagCount > powerCount}">
  <table cellpadding="0" cellspacing="0" border="0" class="site_02">
  	<tr height="36">
  		<td colspan="4" align="left" class="operate_big" style=" border-bottom:#ADADAD 1px solid;"><span>${LANG['bizconf.jsp.admin.conf_tel_config.res7']}</span></td>
  	</tr>
    <c:forEach var="eachField" items="${EMPOWER_CODE_FIELD_ARRAY}" varStatus="fieldSatus">
    <c:set var="eachFieldName" value="${eachField[1]}"/>
    <c:if test="${sitePower[eachFieldName]==EMPOWER_DISABLED && eachField[2]==1}">
    <tr height="30" align="center">
    	<td class="operate" width="150"><c:set var="langName" value="system.site.empower.item.${eachField[0]}"/>${LANG[langName]}</td>
        <td class="operate">
        	<input type="radio" class="allowTelFlag" name="${eachFieldName}"  value="${EMPOWER_ENABLED}" 
        	<c:if test="${globalConfig[eachFieldName]==EMPOWER_ENABLED }">checked</c:if> disabled/>${LANG['bizconf.jsp.radio.status.opened']}
        </td>
        <td class="operate">
        	<input type="radio" class="allowTelFlag" name="${eachFieldName }" value="${EMPOWER_DISABLED}" 
        	<c:if test="${globalConfig[eachFieldName]==EMPOWER_DISABLED }">checked</c:if> disabled/>${LANG['bizconf.jsp.radio.status.closed']}
        </td>
        <td width="auto" height="38px" class="error-container" style="border:0px;background:#FFFFFF;color:red;padding-left:10px;"></td>
    </tr>
    </c:if>
    </c:forEach>
    
   
    
  </table>
  </c:if>
  <div>
  	<input name="button_a" class="button_a_wasd skipThese" type="submit" value="${LANG['system.submit']}"/>
  	<c:if test="${!empty infoMessage}">
		<img src="${baseUrlStatic}/images/ys_r_bg.jpg" width="16" height="14" style="margin-left:15px;margin-top:5px;margin-right: 5px"/><label style='color:#258021'>${infoMessage}</label>
	</c:if>
	<c:if test="${!empty errorMessage}">
   		<img src="${baseUrlStatic}/images/ys_w_bg.jpg" width="16" height="14" style="margin-left:15px;margin-top:5px;margin-right: 5px;"/><label style='color:red'>${errorMessage}</label>
   	</c:if>
  </div>
 
    </form>
  </div>
</div>
</body>
</html>
