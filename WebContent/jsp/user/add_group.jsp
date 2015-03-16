<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>add group</title>
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
var groupForm = null;
$(document).ready(function(){
	$('#groupForm :input').tipsy({ trigger: 'manual', fade: false, gravity: 'sw', opacity: 0.65 });
	var ruleString = {
			required: {
				"groupName": "${LANG['valid.input.group']}"
			},
			maxlength: {
				"groupDesc": "${LANG['valid.rangelength256']}"
			},
			rangelength: {
				"groupName": "${LANG['valid.rangelength132']}"
			}
	};
	
	$.validator.addMethod("notRequired", function(value, element) {
		if(value==null || value=="" || value.length==0){
			$(element).tipsy('hide').removeAttr('original-title');
		}
    	return true;
 	}, "");
	
	groupForm = $("#groupForm").validate({
		onkeyup: false,
		errorClass: "warning",
		rules: {
            'groupName' : {required:true, rangelength: [1, 32]},
            'groupDesc' : {notRequired:true, maxlength: 256}
        },
        messages: {
            'groupName' : {required:ruleString.required.groupName, rangelength: ruleString.rangelength.groupName},
            'groupDesc' : {maxlength: ruleString.maxlength.groupDesc}
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

function submitForm() {
	if(groupForm.form()){
		saveGroup();
	}
}

function saveGroup() {
	var data = {};
	data.id = "${group.id}";
	data.groupName = $("#groupName").val();
	data.groupDesc = $("#groupDesc").val();
	app.addGroup(data, function(result) {
		if(result){
			if(result.status==1){
				closeDialog(result);		
			} else {
				parent.errorDialog(result.message);
			}
		}
	});		
}

</script>
</head>
<body onload="loaded()">
<form action="" id="groupForm">
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
        <!--==================================${LANG['bizconf.jsp.add_contacts.res2']}======================================-->
        
        <div class="First_Steps_quick_a" style=" background:#FFF">
	        <div class="First_Steps_title_a"> <a href="javascript:;" onclick="closeDialog()"></a>
	          <h3 class="tit_a">${LANG['bizconf.jsp.add_group.res1']}</h3>
	          <p class="tit_b">${LANG['bizconf.jsp.add_group.res2']}</p>
	        </div>
        	<div style=" background:#fff"><img class="toa_quick" src="/static/images/min.jpg" width="410" height="1" /></div>
        	<div class="First_Steps_top" style=" background:#FFF"> </div>
        	<div class="First_Steps_main_quick">
          		<table class="box_a_quick">
		            <tr class="box01">
		              <td align="right" class="left_text_a"><label class='red_star'>*</label>${LANG['bizconf.jsp.add_group.res3']}</td>
		              <td align="left"><input class="right_text_a" id="groupName" name="groupName" class="text04" type="text" value="${group.groupName}" /></td>
		            </tr>
		            <tr class="box01">
		              <td align="right" class="left_text_a" valign="top">${LANG['bizconf.jsp.add_group.res4']}</td>
		              <td align="left"><textarea  class="right_text_b" id="groupDesc" name="groupDesc" cols="" rows="">${group.groupDesc}</textarea></td>
		            </tr>
          		</table>
        	</div>
          	<div class="but15">
          		<span class="button_common" style=" margin-bottom:20px; margin-right:30px;">
          			<a href="javascript:closeDialog();"><img src="/static/images/quxiao.png" width="11" height="10" align="absmiddle" style=" margin-right:5px; margin-left:5px"/>${LANG['bizconf.jsp.add_contacts.res13']}</a>
          		</span>
          	</div>
          	<div class="but16">
          		<span class="button_common">
          			<a href="javascript:submitForm();"><img src="/static/images/right.png" width="16" height="14" align="absmiddle" style=" margin-right:5px; margin-left:5px;"/>${LANG['bizconf.jsp.add_group.res5']}</a>
          		</span>
          	</div>
      	</div>
        
		<!--==================================${LANG['bizconf.jsp.add_contacts.res2']}======================================-->
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
	var frame = parent.$("#addGroup");
	frame.trigger("loaded");
}

function closeDialog(result) {
	var dialog = parent.$("#addGroup");
	if(result){
		dialog.trigger("closeDialog", [result]);
	} else {
		dialog.trigger("closeDialog");	
	}
}
</script>
