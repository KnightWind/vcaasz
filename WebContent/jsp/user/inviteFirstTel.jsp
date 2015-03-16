<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/popupbox.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/box.css?ver=${version}"/>
<title>${LANG['bizconf.jsp.conf_list_main.res2']}</title>
<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery-1.8.3.min.js?ver=${version}"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/date.js?ver=${version}"></script>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/json2.js?ver=${version}"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/util.js?ver=${version}"></script>
<script type="text/javascript">
var confId = "${confId}";
function removeItem(obj,email){
	//unRegsit(email);
	$(obj).parent().parent().remove();
	refreshItemNo();
}

function saveContact() {
	if(hasRepeatEmail()){
		return;
	}
	var data = {};
	data.confId = confId;
	data.users = [];
	$.each($(".yq_ta"), function(i, elem) {
		var userbase = {};
		userbase.userName = $(elem).find(".username").val();
		userbase.userEmail = $(elem).find(".email").val();
		userbase.telephone = $(elem).find(".phone").val();
		userbase.userId = $(elem).find("input[name=userId]").val();
		if(userbase.userEmail || userbase.telephone){
			data.users.push(userbase);
		}
	});
	if(data.users.length==0){
		$(".yq_ta").each(function(){
			$(this).remove();
		});		 
		parent.errorDialog("${LANG['bizconf.jsp.inviteFirst.res1']}");
		return;
	}
	app.inventContact(data, function(result) {
		if(result) {
			if(result.status==1){
				var url = parent.$("#eidtInventContact").find("iframe").attr("src");
				if(url){
					url = addUrlParam(url, "t", Math.random());
					parent.$("#eidtInventContact").find("iframe").attr("src", url);	
				}
				closeDialog(result);
			} else {
				parent.errorDialog(result.message);
			}
		}
	}, {message:"${LANG['bizconf.jsp.inviteFirst.res2']}...", ui:parent});
}
function invent(){
	parent.importContact();
}
	
//${LANG['bizconf.jsp.inviteFirst.res3']} ${LANG['bizconf.jsp.inviteFirst.res4']}DOM${LANG['bizconf.jsp.attended_conf_list.res9']}  
var emailContainer = new Object();
function regsiterEmail(email){
	if(!emailContainer[email]){
		emailContainer[email] = true;
	}
}
function unRegsit(email){
	if(emailContainer[email]){
		emailContainer[email] = false;
	}
}	

function isRepeatedEmail(email){
	if(!email){
		return false;
	}
	//alert($("input[name=cemail]").filter("[value='"+email+"']").length);
	if($("input[name=cemail]").filter("[value='"+email+"']").length >0){
		return true;
	}
	return false;
}

//${LANG['bizconf.jsp.inviteFirst.res5']}
function hasRepeatEmail(){
	var regsiter = new Object();
	for(var i=0; i<$("input[name=cemail]").length;i++){
		var ipt = $("input[name=cemail]").get(i);
		if(!($(ipt).val())){
			continue;
		}
		if(regsiter[$(ipt).val()]){
			$(ipt).focus();
			parent.errorDialog("${LANG['bizconf.jsp.inviteFirst.res6']}");
			return true;
		}
		regsiter[$(ipt).val()]=true;
	}
	return false;
}


function addItem(){
		 var name =$("#itemName").val();
		 var email =$("#itemEmail").val();
		 var phone =$("#itemPhone").val();
		 var reg_email =/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		 var reg_tel = /(^((\+86)?|\(\+86\)|\+86\s)0?1[358]\d{9}$)|(^((\+86)?|\(\+86\)|\+86\s)0?([1-9]\d-?\d{6,8}|[3-9][13579]\d-?\d{6,7}|[3-9][24680]\d{2}-?\d{6})(-\d{4})?$)/;
		 //isRepeatedEmail(email);
// 		 if(!email && !phone){
// 			parent.errorDialog("${LANG['bizconf.jsp.inviteFirst.res7']}");
			//$("#itemName").focus();
// 			return;
// 		}else if(email && !reg_email.test(email)){
// 			parent.errorDialog("${LANG['bizconf.jsp.inviteFirst.res8']}");
// 			$("#itemEmail").focus();
// 			return;
// 		}else if(phone && !reg_tel.test(phone)){
// 			parent.errorDialog("${LANG['bizconf.jsp.inviteFirst.res9']}");
// 			$("#itemPhone").focus();
// 			return;
// 		}else if(isRepeatedEmail(email)){
// 			parent.errorDialog("${LANG['bizconf.jsp.inviteFirst.res10']}");
// 			$("#itemEmail").focus();
// 			return;
// 		}
		if(!phone){
			parent.errorDialog("${LANG['bizconf.jsp.inviteFirst.resTel']}");
			$("#itemPhone").focus();
			return;
		}else if(phone && !reg_tel.test(phone)){
				parent.errorDialog("${LANG['bizconf.jsp.inviteFirst.res9']}");
				$("#itemPhone").focus();
				return;
		}
		var data = new Object();
		data.name = name;
		data.email = email;
		data.phone = phone;
		appendRow(data);
		
		$("#itemName").val("");
		$("#itemEmail").val("");
		$("#itemPhone").val("");
}

function appendRow(data){
	//isRepeatedEmail(data.email);
	//regsiterEmail(data.email);
	var userId = data.userId || "";
	var len = $("#contactsTab tr").length-1;
	var html = "<tr class=\"yq_ta\"><td width=\"20\" name=\"contactItem\">"+len+"</td><td align=\"center\" width=\"160\">"
				+"<input name=\"userId\" value=\""+userId+"\" type=\"hidden\"><input class=\"yq_t01 username\" name=\"cname\" type=\"text\" value=\""+data.name+"\" /></td>"
				//+" <td align=\"center\"  width=\"185\"><input class=\"yq_t02 email\" name=\"cemail\" type=\"text\" value=\""+data.email+"\" /></td>"
				+" <td width=\"160\" align=\"center\" ><input class=\"yq_t03 phone\" name=\"cphone\" type=\"text\" value=\""+data.phone+"\" /></td>" //电话
				+"  <td width=\"90\" align=\"center\" ><a href=\"#\" onclick=\"removeItem(this,'"+data.email+"');\" >${LANG['bizconf.jsp.attended_conf_list.res12']}</a></td>"
				+" <td width=\"160\" align=\"center\" ></td></tr>";
	$("#contactsTab").append(html);
}

function refreshItemNo(){
	var trs = $("td[name=contactItem]");
	for(var i=0;i<trs.length;i++){
		$(trs[i]).html(i+1);
	}
	//$.children("tr[name=contactItem]")
	//alert(trs.length);
}

function addByExtral(datas){
	var repated = "";
	for(var i=0;i<datas.length;i++){
		if(isRepeatedEmail(datas[i].email)){
			repated += datas[i].name;
			repated += ",";
			continue;
		}
		appendRow(datas[i]);
	}
	var text = "${LANG['bizconf.jsp.inviteFirst.res11']}";
	if(repated){
		text += "${LANG['bizconf.jsp.inviteFirst.res12']} "+repated+ " ${LANG['bizconf.jsp.inviteFirst.res13']}";
		alert(text);
	}
}
</script>
</head>

<body onload="loaded()">
<table class="overlay-panel" border="0" cellpadding="0" cellspacing="0" id="joinPublicDiv">
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
<div class="First_Steps_invite_first" style="height: 450px;width:730px">
        <div class="First_Steps_title_invite" style="width: 730px;"> 
        	<a href="javascript:;" onclick="closeDialog()"></a>
          <h3 class="tit_a_invite">${LANG['bizconf.jsp.inviteFirst.res14']}</h3>
          <p class="tit_b_invite">${LANG['bizconf.jsp.inviteFirst.res15']}</p>
        </div>
        <div style=" background:#fff;font-size: 1px;"><img class="toa_quick_invite" src="/static/images/min.jpg" width="650" height="1" /></div>
        <div class="First_Steps_main_invite" style="height:300px; overflow-y:auto;overflow-x:hidden;width:730px;position: relative;">
        	<div style="position: absolute;top: 0px;">
        		<table id="contactsTab" width="100%" align="center" cellpadding="0" cellspacing="0" border="0" id="t_box_01" style="margin-top:5px;">
            <tr>
              <td width="20" height="24"></td>
              <td align="center" width="160">${LANG['bizconf.jsp.add_contacts.res7']}</td>
       <%--      <td align="center" width="185">${LANG['bizconf.jsp.add_contacts.res9']}</td>--%>
             <td align="center" width="160">${LANG['bizconf.jsp.conflogs.res4']}</td>
              <td align="center" width="90"></td>
              <td align="center" width="160"></td>
            </tr>
            <tr>
              <td width="20"></td>
              <td align="center" width="160" height="36"><input class="yq_t01" id="itemName" type="text" /></td>
             <%--<td align="center" width="185"><input class="yq_t02" id="itemEmail" type="text" /></td>--%>
             <td align="center" width="160"><input class="yq_t03" id="itemPhone" type="text" /></td>
              <td align="center" width="90">
              	<span class="button_common">
              		<a href="javascript:;" onclick="addItem()">
              			<img width="16" height="16" align="absmiddle" style=" margin-left:5px; margin-right:5px;" src="/static/images/add_16.png" />${LANG['bizconf.jsp.inviteFirst.res16']}
              		</a>
              	</span>
              </td>
              <td align="center" width="160">
              	<span class="button_common">
              		<a href="javascript:;" onclick="invent()">
              			<img width="14" height="9" align="absmiddle" style=" margin-left:5px; margin-right:5px;" src="/static/images/import.png" />${LANG['bizconf.jsp.inviteContactsSelect.res1']}
              		</a>
              	</span>
              </td>
            </tr>
          </table>
        	</div>
        </div>
        <div class="First_Steps_bottom_a" style="margin-top: 0px;">
          <div class="but10">
          	<span class="button_common">
          	<a href="javascript:;" onclick="closeDialog()"><img src="/static/images/quxiao.png" width="11" height="10" align="absmiddle" style=" margin-right:8px; margin-left:10px"/>${LANG['bizconf.jsp.create_Reservation_Conf.res6400']}</a>
          	</span></div>
          <div class="but11">
          	<span class="button_common">
          		<a href="javascript:;" onclick="saveContact()"><img src="/static/images/fasong.png" width="16" height="12" align="absmiddle" style=" margin-right:8px; margin-left:10px" />${LANG['bizconf.jsp.user.telinviteuser.call']}</a>
          	</span>
          	</div>
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
</body>
</html>
<script type="text/javascript">
function loaded() {
	var dialog = parent.$("#inventContact");
	dialog.trigger("loaded");
}
function closeDialog(result) {
	var dialog = parent.$("#inventContact");
	if(result){
		dialog.trigger("closeDialog", [result]);
	} else {
		dialog.trigger("closeDialog");	
	}
}
</script>
