
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/popupbox.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/box.css"/>
<script type="text/javascript" src="${ctx}/static/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.plugin.js"></script>  
<title>快速入会</title>
</head>
<body>


<table class="overlay-panel" border="0" id="joinSecureDiv" cellpadding="0" cellspacing="0" >
  <tbody>
    <tr class="no-header">
      <td class="overlay-hdL"></td>
      <td class="overlay-hdC"></td>
      <td class="overlay-hdR"></td>
    </tr>
    <tr>
      <td class="overlay-bdL"></td>
      
      <!--弹出层主题内容区域开始========================================================================-->
      
      <td class="overlay-content">
        <div class="First_Steps_quick_a" style=" background:#FFF;">
          <div class="First_Steps_title_a"> <a href="javascript:closeDialog();"></a>
            <h3 class="tit_a">加入会议</h3>
            <p class="tit_b">输入您的会议安全号，快速加入您的会议。</p>
          </div>
          <div style=" background:#fff"><img class="toa_quick" src="/static/images/min.jpg" width="410" height="1" /></div>
          <div class="First_Steps_top" style=" background:#FFF"> </div>
          <div class="First_Steps_main_quick">
            <table class="box_a_quick02">
             <form name="joinForm" id="joinForm" method="post" action="/join">
      <input type="hidden" name="joinType" id="joinType" value="${joinType}"/>
              <tr class="box01">
                <td align="right" class="left_text_a">姓名</td>
                <td align="left">
<%--                  <c:set var="userName" value="请输入用户名"/> --%>
              <c:if test="${currentUser!=null }">
              	<c:set var="userName" value="${currentUser.trueName }"/>
              </c:if>
              <input class="right_text_a" name="userName" id="userName" type="text" value="${userName}"/>
                </td>
              </tr>
              <%--
              <tr class="box01">
                <td align="right" class="left_text_a">会议密码</td>
                <td align="left">
                 <input class="right_text_a" name="" type="text"  onfocus="this.value=' '; this.onfocus=null;" />
                </td>
              </tr>
              --%>
              <tr class="box01">
                <td align="right" class="left_text_a">安全会议号</td>
                <td align="left">
                  <input class="right_text_a" name="code" id="code"  type="text" />
                </td>
              </tr>
              <tr align="left">
            <td align="left" colspan="3">  
            <div class="but150" style=" margin-bottom:70px;"><span class="button_common"><a  href="javascript:" onclick="join();"><img src="/static/images/join.png" width="16" height="15" align="absmiddle" style=" margin-right:5px; margin-left:5px"/>加入会议</a></span></div>
            </td>
            </tr>
               </form>
            </table>
          </div>
          </div> 
            
        	
       
        
      </td>
      
      <!--弹出层主题内容区域开始========================================================================-->
      
      <td class="overlay-bdR"></td>
    </tr>
    <tr>
      <td class="overlay-ftL"></td>
      <td class="overlay-ftC"></td>
      <td class="overlay-ftR"></td>
    </tr>
  </tbody>
</table>




<table class="overlay-panel" border="0" cellpadding="0" cellspacing="0" id="joinMtgErrorDiv" style="display:none">
  <tbody>
    <tr class="no-header">
      <td class="overlay-hdL"></td>
      <td class="overlay-hdC"></td>
      <td class="overlay-hdR"></td>
    </tr>
    <tr>
      <td class="overlay-bdL"></td>
      <td class="overlay-content">
      <!--弹出层主题内容区域开始========================================================================-->  
		<form name="joinForm" id="joinForm" method="post" action="/join">
	      	<input type="hidden" name="cId" id="cId" value="${cId}"/>
	      	<input type="hidden" name="joinType" id="joinType" value="${joinType}"/>
	      	<div class="First_Steps_quick_a" style=" background:#FFF">
		        <div class="First_Steps_title_a"> <a href="javascript:"  onclick="javascript:closeDialog();" ></a>
		          <h3 class="tit_a">信息提示</h3>
		          <p class="tit_b">&nbsp;</p>
		        </div>
	        	<div style=" background:#fff"><img class="toa_quick" src="/static/images/min.jpg" width="410" height="1" /></div>
	        	<div class="First_Steps_top" style=" background:#FFF"> </div>
		        <div class="First_Steps_main_quick">
		          <table class="box_a_quick02" width="100%">
		            <tr class="box01">
		              <td align="center" colspan="2">
		              
		              <c:set var="msgName" value="website.conf.join.type.${msgFlag}"/>
						${LANG[msgName]}
		              </td>
		           
		          </table>
		        </div>
		        <div class="First_Steps_bottom01"  style="height: 30px;">
		        	<div class="but150">
		        		<span class="button_common">
		        			<a href="javascript:" onclick="closeDialog();"><img src="/static/images/cross.png" width="16" height="15" align="absmiddle" style=" margin-right:5px; margin-left:5px"/>关闭</a>
		        		</span>
		        	</div>
		        </div>
	      </div>
      </form>
      <!--弹出层主题内容区域开始========================================================================-->      
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

<%--<div class="add17_main">
  <div class="add17_top">
    <div class="add17_top_left"></div>
    <div class="add17_top_center"></div>
    <div class="add17_top_right"></div>
  </div>
  <div class="add17_center">
    <div class="add17_center_left"></div>
    <div class="add17_center_center">
      <form name="joinForm" id="joinForm" method="post" action="/join">
      <input type="hidden" name="joinType" id="joinType" value="${joinType}"/>
      <div class="First_Steps_quick_a" style=" background:#FFF">
        <div class="First_Steps_title_a"> <a href="javascript:"  onclick="javascript:closeDialog();" ></a>
          <h3 class="tit_a">快速入会</h3>
          <p class="tit_b">请输入你的账号后进入该会议。</p>
        </div>
        <div style=" background:#fff"><img class="toa_quick" src="/static/images/min.jpg" width="410" height="1" /></div>
        <div class="First_Steps_top" style=" background:#FFF"> </div>
        <div class="First_Steps_main_quick">
          <table class="box_a_quick02">
            <tr class="box01">
              <td align="right" class="left_text_a">用户名</td>
              <td align="left">
 	<c:set var="userName" value="请输入用户名"/>
              <c:if test="${currentUser!=null }"><c:set var="userName" value="${currentUser.trueName }"/></c:if>
              <input class="right_text_a" name="userName" id="userName" type="text" value="${userName}"/>
              
              </td>
            </tr>
            
            <tr class="box01">
              <td align="right" class="left_text_a">会议密码</td>
              <td align="left"><input class="right_text_a" name="" type="text"  onfocus="this.value=' '; this.onfocus=null;" /></td>
            </tr>
            
              <tr class="box01">
              <td align="right" class="left_text_a">安全会议号</td>
              <td align="left">
              <input class="right_text_a" name="code" id="code"  type="text" value="请输入会议安全号" />
            </tr>
          </table>
        </div>
        <div class="First_Steps_bottom01">
          <div class="but150"><a href="javascript:" onclick="join();"><img src="/static/images/right.png" width="16" height="14" align="absmiddle" style=" margin-right:8px;"/>快速加入</a></div>
        </div>
        
        
      </div>
      
      </form>
    </div>
    <div class="add17_center_right"></div>
  </div>
  <div class="add17_bottom">
    <div class="add17_bottom_left"></div>
    <div class="add17_bottom_center"></div>
    <div class="add17_bottom_right"></div>
  </div>
</div>
--%></body>
</html>

<script type="text/javascript">
$(document).ready(function(){
	$("#userName").watermark('请输入姓名');
	$("#code").watermark('请输入会议安全号');
});
function closeDialog() {
	var dialog = parent.$("#joinMeeting");
	dialog.trigger("closeDialog");
}
window.onload=function(){
	var frame = parent.$("#joinMeeting");
	frame.trigger("loaded");
}

function join(){
	var userName=$("#userName").val();
	var code=$("#code").val();
	if(userName==null || userName=="" || $.trim(userName)=="请输入用户名"){
		alert("请输入用户名");
		return false;
	}
	if(code==null || $.trim(code)=="" || $.trim(code)=="请输入会议安全号"){
		alert("请输入会议安全号");
		return false;
	}
	$("#joinForm").submit();
	
}
function initPage(){
// 	$("#userName").focus(function(){
// 		var userName=$("#userName").val();
// 		if($.trim(userName)=="" || $.trim(userName)=="请输入用户名"){
// 			$("#userName").val("");
// 		}
// 	});
// 	$("#userName").blur(function(){
// 		var userName=$("#userName").val();
// 		if($.trim(userName)==""){
// 			$("#userName").val("请输入用户名");
// 		}
// 	});
// 	$("#code").focus(function(){
// 		var code=$("#code").val();
// 		if($.trim(code)=="" || $.trim(code)=="请输入会议安全号"){
// 			$("#code").val("");
// 		}
// 	});
// 	$("#code").blur(function(){
// 		var code=$("#code").val();
// 		if($.trim(code)==""){
// 			$("#code").val("请输入会议安全号");
// 		}
// 	});
	

	/*
	$("#userName").att("onfocus",function(){
		var userNameVal=$("#userName").val();
		if($.trim(userName)=="" || $.trim(userName)=="请输入用户名"){
			$("#userName").val("");
		}
	});
	*/
<c:if test="${msgFlag!=null && msgFlag!=''}">
	$("#joinSecureDiv").hide();
	$("#joinMtgErrorDiv").show();
</c:if>
}

initPage();
</script>



