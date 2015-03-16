<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/popupbox.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/box.css?ver=${version}"/>
<title>${LANG['bizconf.jsp.contacts_import_main.res1']}</title>
<style></style>
<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery-1.8.3.min.js?ver=${version}"></script>
<script type="text/javascript">
	function selectGroup(obj){
			$("#contactFrame").attr("src","/user/group/invitelist");	
			$(".cluster").find("a").css("color","#333333");
			$(obj).css("color","#ED6F00");
	}
	
	function selectContact(obj){
			$("#contactFrame").attr("src","/user/contact/invitelist");
			$(".cluster").find("a").css("color","#333333");
			$(obj).css("color","#ED6F00");
	}
	
	function selectEnContacts(obj){
			$("#contactFrame").attr("src","/user/contact/showEnterpriseContacts?showAll=1");	
			$(".cluster").find("a").css("color","#333333");
			$(obj).css("color","#ED6F00");
	}
	
	function selectOrg(obj){
			$("#contactFrame").attr("src","/jsp/user/org_user_list.jsp");	
			$(".cluster").find("a").css("color","#333333");
			$(obj).css("color","#ED6F00");
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
		<div class="First_Steps_invite_m" style=" background:#FFF; border-radius:3px;">
        <div class="First_Steps_title_invite"> <a href="javascript:;" onclick="closeDialog()"></a>
          <h3 class="tit_a_invite">${LANG['bizconf.jsp.inviteContactsSelect.res1']}</h3>
          <p class="tit_b_invite">${LANG['bizconf.jsp.inviteContactsSelect.res2']}</p>
        </div>
        <div style=" background:#fff"><img class="toa_quick_invite" src="/static/images/min.jpg" width="730" height="1" /></div>
        <div class="First_Steps_top_invite" style=" background:#FFF;clear: both;height: 35px;">
          <div class="cluster"><a href="#" style="margin-left:10px; color: #ED6F00;" onclick="selectContact(this);"><img src="/static/images/lianxiren.png" width="18" height="20" align="absmiddle" style=" margin-right:5px;" />${LANG['bizconf.jsp.inviteContactsSelect.res5']}&nbsp;&nbsp;</a></div>
          <div class="cluster"><a href="#" style="margin-left:10px; " onclick="selectGroup(this);"><img src="/static/images/qunzu.png" width="20" height="18" align="absmiddle" style=" margin-right:5px;" />${LANG['bizconf.jsp.inviteContactsSelect.res6']}&nbsp;&nbsp;</a></div>
          <div class="cluster"><a href="#" style="margin-left:10px;" onclick="selectEnContacts(this);"><img src="/static/images/tongxl.png" width="18" height="20" align="absmiddle" style=" margin-right:5px;" />${LANG['bizconf.jsp.inviteContactsSelect.res7']}&nbsp;&nbsp;</a></div>
        </div>
        <!--${LANG['bizconf.jsp.contacts_import_main.res7']}-->
        <div class="First_Steps_main_invite" style="width: 100%;height: 495px;">
        	<iframe frameborder="0" width="100%" style="height: 440px;overflow-x: hidden;overflow-y: auto;" id="contactFrame" name="contactFrame" scrolling="yes" src="/user/contact/invitelist"></iframe>
        	<div class="First_Steps_bottom_s">
	          <div class="but44"><span class="button_common"><a href="javascript:;" onclick="closeDialog()"><img src="/static/images/quxiao.png" width="11" height="10" align="absmiddle" style=" margin-right:8px; margin-left:10px;"/>${LANG['bizconf.jsp.add_contacts.res13']}</a></span></div>
	          <div class="but09"><span class="button_common"><a href="javascript:;" onclick="selectContacts()"><img src="/static/images/right.png" width="16" height="14" align="absmiddle" style=" margin-right:8px; margin-left:10px;" />${LANG['bizconf.jsp.inviteContactsSelect.res4']}</a></span></div>
	        </div>
        </div>
        <!--${LANG['bizconf.jsp.contacts_import_main.res8']}-->
        
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
function selectContacts() {
	//var data = {"name": "alan", "email": "asd@qq.com", "phone": "12312311"};
	var datas = window.frames["contactFrame"].getContactsData();
	//var dialog = parent.$("#inventContact").find("iframe")[0].contentWindow.appendRow(data);
	if(datas && datas.ids){
		$.ajax({ 
	      	type: "POST",
	      	url:"/user/group/getContactsByGroups",
	      	data:{ids:datas.ids},
	      	dataType:"json",
	      	async:false,
	      	success:function(data){
	      		var contacts = new Array();
				if(data){
					for(var i=0;i<data.length;i++){
						var item = new Object();
						item.name = data[i].contactName;
						item.email = data[i].contactEmail;
						item.phone = data[i].contactPhone || data[i].contactMobile;
						item.userId = data[i].id;
						contacts.push(item);
					}
					parent.$("#inventContact").find("iframe")[0].contentWindow.addByExtral(contacts);
				}else{
					alert('Server inner error!');
				}
	      	},
	        error:function(XMLHttpRequest, textStatus, errorThrown) {
	        	alert(XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
	        }
		});  
	}else if(datas){
		parent.$("#inventContact").find("iframe")[0].contentWindow.addByExtral(datas);
		//alert(datas);
	}
	closeDialog();
} 
function loaded() {
	var dialog = parent.$("#importContact");
	dialog.trigger("loaded");
}
function closeDialog() {
	var dialog = parent.$("#importContact");
	dialog.trigger("closeDialog");
}
</script>
