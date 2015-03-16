<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${LANG['bizconf.jsp.add_contacts.res1']}</title>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/common.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/popupbox.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/box.css?ver=${version}"/>
<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery-1.8.3.min.js?ver=${version}"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-validation-1.10.0/dist/jquery.validate.js?ver=${version}"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/tipsy-master/src/javascripts/jquery.tipsy.js?ver=${version}"></script>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/json2.js?ver=${version}"></SCRIPT>	
<script type="text/javascript" src="${baseUrlStatic}/js/util.js?ver=${version}"></script>
<script type="text/javascript">
	var contactForm = null;
	$(document).ready(function(){
		$('#contactForm :input').tipsy({ trigger: 'manual', fade: false, gravity: 'sw', opacity: 0.65 });
		var ruleString = {
				required: {
					"contactName": "${LANG['valid.input.userName']}",
					"email": "${LANG['valid.input.email']}",
					"contactPhone": "${LANG['valid.input.phone']}"
				},
				maxlength: {
					"remark": "${LANG['valid.rangelength256']}"
				},
				rangelength: {
					"contactName": "${LANG['valid.rangelength132']}",
					"contactNameEn": "${LANG['valid.rangelength132']}",
					"email": "${LANG['valid.rangelength664']}",
					"phone": "${LANG['valid.rangelength432']}",
					"mobile": "${LANG['valid.rangelength432']}"
				},
				custom: {
					"email": "${LANG['valid.checkEmail']}",
					"checkPhone": "${LANG['valid.checkPhone']}",
					"checkMobile": "${LANG['valid.checkMobile']}",
					"checkUserName": "${LANG['valid.checkUserName']}",
					"checkEnName":"${LANG['valid.checkEnName']}"
				}
		};
		
		$.validator.addMethod("notRequired", function(value, element) {
			if(value==null || value=="" || value.length==0){
				$(element).tipsy('hide').removeAttr('original-title');
			}
	    	return true;
	 	}, "");
		$.validator.addMethod("checkUserName", function(value, element) {  
	    	return this.optional(element) || /^[a-zA-Z0-9\u4e00-\u9fa5_\-&]{1,32}$/.test(value);
	 	}, ruleString.custom.checkUserName);	
		$.validator.addMethod("checkEnName", function(value, element) {       
	    	return this.optional(element) || /^[a-zA-Z0-9_\-&\s]{1,32}$/.test(value);
	 	}, ruleString.custom.checkEnName);
		$.validator.addMethod("checkTelphone", function(value, element) {       
	    	return this.optional(element) || /^((\+86)?|\(\+86\)|\+86\s|\+86-)0?([1-9]\d{1,2}-?\d{6,8}|[3-9][13579]\d-?\d{6,7}|[3-9][24680]\d{2}-?\d{6})(-\d{4})?$/.test(value);
	 	}, ruleString.custom.checkPhone);
		$.validator.addMethod("checkMobile", function(value, element) {       
			return this.optional(element) || /^((\+86)?|\(\+86\)|\+86\s|\+86-)0?1[358]\d{9}$/.test(value);
	 	}, ruleString.custom.checkMobile);
		
		contactForm = $("#contactForm").validate({
			onkeyup: false,
			errorClass: "warning",
			submitHandler: function(form) {
				saveSysUser();
			},
			rules: {
	            'contactName' : {required:true, rangelength: [1, 32], checkUserName:true},
	            'contactNameEn' : {notRequired:true, rangelength: [1, 32], checkEnName:true},
	            'contactEmail' : {required:true, rangelength:[6, 64], email: true},
	            'contactPhone' : {notRequired:true, rangelength: [4, 32], checkTelphone:true},
	            'contactMobile' : {notRequired:true, rangelength: [4, 32], checkMobile:true},
	            'remark' : {notRequired:true, maxlength: 256}
	        },
	        messages: {
	            'contactName' : {required:ruleString.required.contactName, rangelength: ruleString.rangelength.contactName},
	            'contactNameEn' : {rangelength: ruleString.rangelength.contactNameEn},
	            'contactEmail' : {required:ruleString.required.email, rangelength: ruleString.rangelength.email, email: ruleString.custom.email},
	            'contactPhone': {required:ruleString.required.contactPhone, rangelength: ruleString.rangelength.phone},
	            'contactMobile': {rangelength: ruleString.rangelength.mobile},
	            'remark': {maxlength: ruleString.maxlength.remark}
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
	function saveContact() {
		var data = {};
		data.contactName = $("#contactName").val();
		data.contactNameEn = $("#contactNameEn").val();
		data.contactEmail = $("#contactEmail").val();
		data.contactPhone = $("#contactPhone").val();
		data.contactMobile = $("#contactMobile").val();
		data.contactDesc = $("#remark").val();
		data.id = "${contact.id}";
		app.addContact(data, function(result) {
			if(result){
				if(result.status==1){
					closeDialog(result);		
				} else {
					parent.errorDialog(result.message);
				}
			}
		});
	}
	function submitForm() {
		if(contactForm.form()){
			saveContact();
		}
	}
	</script>
</head>
<body onload="loaded()">
<form action="" id="contactForm">
 <table class="overlay-panel" border="0" cellpadding="0" cellspacing="0" >
    <tbody>
      <tr class="no-header">
        <td class="overlay-hdL"></td>
        <td class="overlay-hdC"></td>
        <td class="overlay-hdR"></td>
      </tr>
      <tr>
        <td class="overlay-bdL"></td>
        <td class="overlay-content"> 
        <!--${LANG['bizconf.jsp.add_contacts.res2']}========================================================================-->
      	<div class="First_Steps_quick_a" style=" background:#FFF;height: 464px;">
	        <div class="First_Steps_title_a"> <a href="javascript:closeDialog();"></a>
	          <h3 class="tit_a">${LANG['bizconf.jsp.add_contacts.res1']}</h3>
	          <p class="tit_b">${LANG['bizconf.jsp.add_contacts.res3']}</p>
	        </div>
        	<div style=" background:#fff">
        		<img class="toa_quick" src="/static/images/min.jpg" width="410" height="1" />
        	</div>
        	<div class="First_Steps_top" style=" background:#FFF"> </div>
        	<p class="annotation_text">
        		${LANG['bizconf.jsp.add_contacts.res5']}<br />
        		${LANG['bizconf.jsp.add_contacts.res6']}
        	</p>
	        <div class="First_Steps_main_quick">
	          <table class="box_a_quick" cellpadding="0" cellspacing="0" border="0" >
	            <tr class="box01">
	              <td align="right" class="left_text_a"><label class='red_star'>*</label>${LANG['bizconf.jsp.add_contacts.res7']}</td>
	              <td align="left"><input class="right_text_a" id="contactName" name="contactName" type="text" value="${contact.contactName}" /></td>
	            </tr>
	            <tr class="box01">
	              <td align="right" class="left_text_a">${LANG['bizconf.jsp.add_contacts.res8']}</td>
	              <td align="left"><input class="right_text_a" id="contactNameEn" name="contactNameEn" type="text" value="${contact.contactNameEn}"/></td>
	            </tr>
	            <tr class="box01">
	              <td align="right" class="left_text_a"><label class='red_star'>*</label>${LANG['bizconf.jsp.add_contacts.res9']}</td>
	              <td align="left"><input class="right_text_a" id="contactEmail" name="contactEmail" type="text" value="${contact.contactEmail}" /></td>
	            </tr>
	            <tr class="box01">
	              <td align="right" class="left_text_a">${LANG['bizconf.jsp.add_contacts.res10']}</td>
	              <td align="left"><input class="right_text_a" id="contactPhone" name="contactPhone" type="text" value="${contact.contactPhone}"  /></td>
	            </tr>
	             <tr class="box01">
	              <td align="right" class="left_text_a">${LANG['bizconf.jsp.add_contacts.res11']}</td>
	              <td align="left"><input class="right_text_a" id="contactMobile" name="contactMobile" type="text" value="${contact.contactMobile}"/></td>
	            </tr>
	            <tr class="box01">
	              <td align="right" valign="top" class="left_text_a">${LANG['bizconf.jsp.add_contacts.res12']}</td>
	              <td align="left"><textarea  class="right_text_b" id="remark" name="remark"  value="${contact.contactDesc}">${contact.contactDesc}</textarea></td>
	            </tr>
	          </table>
	        </div>
       
          	<div class="btn_a">
          		<span class="button_common">
          			<a href="javascript:closeDialog();"><img src="/static/images/quxiao.png" width="14" height="14" align="absmiddle" style=" margin-right:5px; margin-left:5px;" />${LANG['bizconf.jsp.add_contacts.res13']}</a>
          		</span>
          	</div>
          	<div class="btn_q">
          		<span class="button_common">
          			<a href="javascript:submitForm();"><img src="/static/images/right.png" width="16" height="14" align="absmiddle" style=" margin-right:5px; margin-left:5px;"/>
          			<c:choose>
	          			<c:when test="${contact !=null}">
	          				${LANG['bizconf.jsp.add_contacts.res14']}
	          			</c:when>
	          			<c:otherwise>
	          				${LANG['bizconf.jsp.add_contacts.res15']}
	          			</c:otherwise>
          			</c:choose>
					</a>
				</span>
			</div>
      	</div>
        <!--${LANG['bizconf.jsp.add_contacts.res2']}========================================================================-->
   		</td>
        <td class="overlay-bdR"></td>
      </tr>
      <tr>
        <td class="overlay-ftL"></td>
        <td class="overlay-ftC"></td>
        <td class="overlay-ftR"></td>
      </tr>
    </tbody>
  </table>
</form>
</body>
</html>
<script type="text/javascript">
function loaded() {
	var frame = parent.$("#addContact");
	frame.trigger("loaded");
}
function closeDialog(result) {
	var dialog = parent.$("#addContact");
	if(result){
		dialog.trigger("closeDialog", [result]);
	} else {
		dialog.trigger("closeDialog");	
	}
}
</script>
