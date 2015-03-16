<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery.uniform/themes/default/css/uniform.custom.css">
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/style.css"/>

<%@ include file="/jsp/common/cookie_util.jsp"%>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom.js"></SCRIPT>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/development-bundle/ui/minified/i18n/jquery-ui-i18n.min.js"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery.uniform/jquery.uniform.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/jquery-validation-1.10.0/dist/jquery.validate.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/tipsy-master/src/javascripts/jquery.tipsy.js"></script>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/util.js"></SCRIPT>
<c:choose>
	<c:when test="${result eq 'success'}">
		<script type="text/javascript">
			$(function() {
				parent.successDialog("${LANG['system.user.unlock.1']}");
			});	
	</script>
	</c:when>
	<c:when test="${lockresult eq 'error'}">
		<script type="text/javascript">
		$(function() {
			parent.errorDialog("${LANG['system.user.unlock.2']}");
		});
	</script>
	</c:when>
	<c:when test="${lockresult eq 'success'}">
		<script type="text/javascript">
			$(function() {
				parent.successDialog("${LANG['system.user.lock.1']}");
			});	
	</script>
	</c:when>
	<c:when test="${lockresult eq 'error'}">
		<script type="text/javascript">
		$(function() {
			parent.errorDialog("${LANG['system.user.lock.2']}");
		});
	</script>
	</c:when>
	<c:when test="${delresult eq 'success'}">
		<script type="text/javascript">
			$(function() {
				parent.successDialog("${LANG['system.user.del.1']}");
			});	
	</script>
	</c:when>
	<c:when test="${delresult eq 'error'}">
		<script type="text/javascript">
		$(function() {
			parent.errorDialog("${LANG['system.user.del.2']}");
		});
	</script>
	</c:when>
</c:choose>
<script type="text/javascript">
$(function() {
	//for search input style
	$("#query").find("input, select").not(".skipThese").uniform();
	
	
	//show or hide search input
	$(".gaoji").toggle(function () {
	    $("#search-condition").slideDown(function() {
		    parent.resizeHeight();//${LANG['bizconf.jsp.admin.conf_list.res1']}
	    });
	}, function () {
		$("#search-condition").slideUp(function() {
			parent.resizeHeight();//${LANG['bizconf.jsp.admin.conf_list.res2']}
		});
	});
	
	
	$("#advanceSearch").click(function(){
		var reg = /^\+?[1-9][0-9]*$/;
		var num = $("#numPartis").val();
		
		if(num && !reg.test(num)){
			$("#numtip").show();
		}else{
			if(${!isCS}){
				$("#query").submit();
			}else{//客服系统 2017-07-08 add
				if($("#numPartis").val()== ""){
					parent.errorDialog("${LANG['system.conf.userlist.already.number.notnull']}");
					return;
				}else{
					$("#numtip").hide();
					$("#query").submit();
				}
			}
		}
	});
	
	
	$("#search").click(function(){
		var searchValue = $("#textfield01").val();
		if(${!isCS}){
			$("#query").submit();
		}else{//客服系统 2017-07-08 add
			if(searchValue.indexOf("企业名称")>-1 || searchValue.indexOf("username")>-1 || searchValue.indexOf("email")>-1 || searchValue.indexOf("site name")>-1 || searchValue.indexOf("site brand")>-1 || searchValue.indexOf("站点标识")>-1 || searchValue.indexOf("姓名")>-1 || searchValue.indexOf("登录邮箱")>-1 || $("#textfield01").val()== ""){
				parent.errorDialog("${LANG['system.conf.userlist.please.number.input.nens']}");
				return;
			}else{
				$("#query").submit();
			}
		}
		
		
	});
});

function countManager(id) {
	parent.countManager(id);
}
function hostManager(id) {
	parent.hostManger(id);
}
function updateSite(id) {
	parent.createOrUpdateSite(id);
}

 
function viewSite(id) {
	parent.viewSite(id);
}
 
 

 

function del(id){
	parent.confirmDialog("${LANG['system.site.delete']}", function() {
		formBind();
		query.action="/system/site/delete/"+id;
		query.submit();
	});
}


 
$(document).ready(function(){
	if (!$.browser.msie || $.browser.version>7) {
		$("#textfield01").watermark('${LANG['bizconf.jsp.system.user_list.res1']}');
	}
	
	$("#search-condition").find("input[type=text]").each(function(){
		if($(this).val()){
			$("#search-condition").show();
			return false;
		}
	});
	
	$("#textfield01").keydown(function(event){
		if(event.keyCode == 13){
			$("#search-condition").find("input[type=text]").each(function(){
				$(this).val("");
			});
			$("#query").submit();
		}
	});
	
	$("#numPartis").keydown(function(event){
		if(event.keyCode == 13){
			$("#textfield01").val("");
			var reg = /^\+?[1-9][0-9]*$/;
			var num = $("#numPartis").val();
			
			if(num && !reg.test(num)){
				$("#numtip").show();
			}else{
				$("#numtip").hide();
				$("#query").submit();
			}
		}
	});
});
</script>
<title>${LANG['system.menu.site.list']}</title>
</head>
<body>
<form id="query" name="query" action="/system/user/siteUserBaseAll/" method="post"  >
<input type="hidden" name="sortField"  value="${sortField }"/>
<input type="hidden" name="sortRule"  value="${sortRule }" />
<div style="margin: 0 10px">
 <div class="m_top">
    <div class="text_box">
    	<input class="search_input" type="text"  id="textfield01"  name="keyword" value="${keyword}"  />
    	<button type="button"  name="search" id="search" class="sousuo searchs_button skipThese" ></button>
    	<a style="" class="gaoji" title="${LANG['system.advancedSearch']}" href="javascript:;">${LANG['system.advancedSearch']}</a>
    </div>
  </div>
  <div id="search-condition" style="display: none; width:100%; height: auto;margin-left: 20px;">
  	<div style="height:30px;">
   		<label>${LANG['bizconf.jsp.system.site_info.res19']} </label>
   		<input type="text" name="numPartis" id="numPartis" value="${numPartis}" style="width:262px;"  /> <label id="numtip" style="color: red; display: none;">${LANG['website.system.userlist.numformat.error']}</label>
  	</div>
  	<div style="height:30px;clear: left">
  		<input type="button" id="advanceSearch" class='button-small skipThese' value="${LANG['system.search']}"/>
  	</div>
   </div>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_box" style=" border:#B5D7E6 1px solid; border-top:none; border-bottom:none;">
	<tr>
  	<td colspan="6">
  		<div style="position: absolute; right: 20px; margin-top: -40px;">
  		<c:if test="${!isCS}">
  			<div class="make_new"><a href="/system/user/exportUsers?keyword=${keyword}&numPartis=${numPartis}" ><b>${LANG['bizconf.jsp.conflogs.res1']}</b></a></div>
  		</c:if>
  		</div>
  	</td>
  </tr>
  <tr class="table002" height="32" >
    <td>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" id="site-list">
      <tr class="table003" height="38" >
       	<td width="8%" height="38" bgcolor="d3eaef" class="STYLE10">
       		<div align="center">
	       		<span><b>${LANG['website.admin.adminlist.page.title.truename']}</b></span>
        	</div>
        </td>
        <td width="6%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['bizconf.jsp.admin.arrange_org_user.res9']}</b></span></div></td>
        <td width="8%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>到期时间</b></span></div></td>
        <td width="10%" height="38" bgcolor="d3eaef" class="STYLE10">
       		<div align="center">
	        	<c:if test="${firstIsCS}">
	        		<a class="paixu01" href="javascript:();" ><span style="text-decoration: underline;"><b>${LANG['bizconf.jsp.conf_default_setup.res16']}</b></span><img src="${baseUrlStatic}/images/paixuzong.png" width="6" height="13" /></a>
	        	</c:if>
        	<c:if test="${!firstIsCS}">
	        	<c:if test="${2!=sortField}">
		        	<a class="paixu01" href="javascript:sortQuery('2','0');" ><span style="text-decoration: underline;"><b>${LANG['bizconf.jsp.conf_default_setup.res16']}</b></span><img src="${baseUrlStatic}/images/paixuzong.png" width="6" height="13" /></a>
		        </c:if>
		        <c:if test="${2==sortField && 0==sortRule}">
		        	<a class="paixu01" href="javascript:sortQuery('2','1');" ><span style="text-decoration: underline;"><b>${LANG['bizconf.jsp.conf_default_setup.res16']}</b></span><img src="${baseUrlStatic}/images/paixu01.png"" width="6" height="13" /></a>
		        </c:if>
		        <c:if test="${2==sortField  && 1==sortRule}">
		        	<a class="paixu01" href="javascript:sortQuery('2','0');" ><span style="text-decoration: underline;"><b>${LANG['bizconf.jsp.conf_default_setup.res16']}</b></span><img src="${baseUrlStatic}/images/paixu02.png"" width="6" height="13" /></a>
		        </c:if>
	        </c:if>
      		</div>
       	</td>
        <td width="12%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['system.site.list.CompanyName']}</b></span></div></td>
        <td width="10%" height="38" bgcolor="d3eaef" class="STYLE10">
        	<div align="center">
	        	<c:if test="${firstIsCS}">
	        		<a class="paixu01" href="javascript:();" ><span style="text-decoration: underline;"><b>${LANG['website.admin.adminlog.page.title.site.sign']}</b></span><img src="${baseUrlStatic}/images/paixuzong.png" width="6" height="13" /></a>
	        	</c:if>
	        	<c:if test="${!firstIsCS}">
			            <c:if test="${1!=sortField}">
				        	<a class="paixu01" href="javascript:sortQuery('1','0');"><span style="text-decoration: underline;"><b>${LANG['website.admin.adminlog.page.title.site.sign']}</b></span><img src="${baseUrlStatic}/images/paixuzong.png" width="6" height="13" /></a>
				       	</c:if>
				       	<c:if test="${1==sortField && 0==sortRule}">
				        	<a class="paixu01" href="javascript:sortQuery('1','1');"><span style="text-decoration: underline;"><b>${LANG['website.admin.adminlog.page.title.site.sign']}</b></span><img src="${baseUrlStatic}/images/paixu01.png" width="6" height="13" /></a>
				       	</c:if>
				       	<c:if test="${1==sortField  && 1==sortRule}">
				        	<a class="paixu01" href="javascript:sortQuery('1','0');"><span style="text-decoration: underline;"><b>${LANG['website.admin.adminlog.page.title.site.sign']}</b></span><img src="${baseUrlStatic}/images/paixu02.png"" width="6" height="13" /></a>
				       	</c:if>
			      </c:if>
        	</div>
        </td>
	<td width="16%" height="38" bgcolor="d3eaef" class="STYLE10" class="STYLE_none" ><div align="center"><span><b>${LANG['system.Operate']}</b></span></div></td>
	<c:if test="${!isCS}">
		<td width="10%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>${LANG['website.system.userlist.createtime']}</b></span></div></td>
	</c:if>
	<c:if test="${!isCS}">
		<td width="10%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span><b>数据中心名称</b></span></div></td>
	</c:if>
   </tr>
    <c:choose>
      	<c:when test="${firstIsCS}">
         <tr>
	        <td height="32" class="STYLE19" colspan="6"  align="center">
	        	${LANG['website.system.title.tip11']}
	        </td>
	      </tr>
        </c:when>
	  <c:otherwise>
		<c:forEach var="user" items="${pageModel.datas}" varStatus="status">
	      <tr>
			<td height="32" class="STYLE19"><div align="center">${user.trueName}</div></td>
			<td height="32" class="STYLE19"><div align="center">${user.userEmail}</div></td>
			<td height="32" class="STYLE19"><div align="center"><fmt:formatDate value="${user.exprieDate}" pattern="yyyy-MM-dd" /></div></td>
			<td height="32" class="STYLE19"><div align="center">${user.numPartis}</div></td>
			<td height="32" class="STYLE19" align="center">
     			<div style="margin-left: 10px;"  title="${LANG['bizconf.jsp.system.site_list.res2']}" >
	        		<a onclick="viewSite(${user.siteId})" href="javascript:;"><span>${myfn:getSiteName(user.siteId) } </span></a>
	        	</div>
	        </td>
	        <td height="32" class="STYLE19" b><div align="center">${myfn:getSiteSign(user.siteId) } </div></td>
			<td height="32" class="STYLE19">
     			<div align="center" class="STYLE21" style="line-height: 20px">
      				<c:if test="${user.delFlag!=2}">
      					<c:if test="${!isCS}">
							<a href="#" onclick="popUpHost(${user.siteId},${user.id})">${LANG['bizconf.jsp.system.email_template_list.res7']}</a>
							<c:choose>
								<c:when test="${!user.expried}">
									<a href="#" onclick="top.licenseManage('${user.id}','${user.siteId}');">端口管理</a>
									<a href="#" onclick="resetPwd(${user.id},this);">${LANG['website.user.password.reset.password'] }</a>
									<c:if test="${user.userStatus == 1}"> 
										<a href="javascript:void(0);" onclick='lock("${user.id}")'>${LANG['website.admin.user.list.page.btn.lock']}</a>
									</c:if>
									<c:if test="${user.userStatus == 0}"> 
										<a href="javascript:void(0);" onclick='unlock("${user.id}")'>${LANG['website.admin.user.list.page.btn.unlock']}</a>
									</c:if>
								</c:when>
								<c:otherwise>
									端口管理
									${LANG['website.user.password.reset.password'] }
									${LANG['website.admin.user.list.page.btn.lock']}
								</c:otherwise>
							</c:choose>
							<a href="javascript:void(0);" onclick='del("${user.id}")'>${LANG['bizconf.jsp.system.email_template_list.res8']}</a>
					    </c:if>
					    <c:if test="${isCS}">
						 	<a href="#" onclick="resetPwd(${user.id},this);">${LANG['website.user.password.reset.password'] }</a>
						</c:if>
				 	</c:if>
					<c:if test="${user.delFlag!=1}">
						${LANG['website.system.userlist.deleteduser']}
					</c:if>
				</div>
   			</td>
     			<!-- 创建时间 -->
    		<c:if test="${!isCS}">
				<td  height="32" class="STYLE19"><div align="center"><fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd" /></div></td>
			</c:if>
				<!-- 数据中心名字 -->
    		<c:if test="${!isCS}">
				<td  height="32" class="STYLE19"><div align="center">${user.dataCenterName }</div></td>
			</c:if>
		</tr>
  	</c:forEach>
 </c:otherwise>
</c:choose>
</table>
</tr>
  <tr>
    <td class="table_bottom" height="38">
    <jsp:include page="/jsp/common/page_info.jsp" />
    </td>
  </tr>
</table>
</div>
</form>
</body>
</html>
<script type="text/javascript"> 
function popUpHost(siteId,userId) {
	parent.createOrUpdateHost(siteId,userId);
}

function submitForm(){
	$("#query").submit();
}

function resetPwd(id,obj) {       
	$.ajax({
      	type: "POST",
      	url:"/system/user/resetEmail",
      	data:{'id':id},
      	dataType:"json",
      	success:function(data){
      		//alert('aa=='+data.status);
			 if(data.status == 1){
				top.successDialog("${LANG['website.system.userlist.alreadysendmail']}${LANG['website.common.symbol.exmark']}");
				$(obj).html("${LANG['website.system.userlist.alreadysendmail.2']}");
				//$(obj).css({color:"red"}); 
				obj.onclick=function(){};
			 }else{
				top.errorDialog("${LANG['website.system.userlist.failuresendmail']}${LANG['website.common.symbol.exmark']}");
			 }
      	},
        error:function(XMLHttpRequest, textStatus, errorThrown) {
        	alert(XMLHttpRequest+"\n"+textStatus+"	n"+errorThrown);
        }
	}); 
}

function sortQuery(sortAttr,sortRule){
		$("input[name=sortField]").val(sortAttr);
		$("input[name=sortRule]").val(sortRule);
		$("input[name=numPartis]").val("${numPartis}");
		$("input[name=keyword]").val("${keyword}");
		$("#query").submit();
}
function unlock(id){
	parent.confirmDialog("${LANG['bizconf.jsp.system.hostlist.unlock']}",function(){
		$("input[name=sortField]").val("${sortField }");
		$("input[name=sortRule]").val("${sortRule}");
		$("input[name=numPartis]").val("${numPartis}");
		$("input[name=keyword]").val("${keyword}");
		var url  = "/system/user/unlock?id="+id;
		$("#query").attr("action",url);
		$("#query").submit();
	});
}
function lock(id){
	parent.confirmDialog("${LANG['bizconf.jsp.system.hostlist.lock']}",function(){
		$("input[name=sortField]").val("${sortField }");
		$("input[name=sortRule]").val("${sortRule}");
		$("input[name=numPartis]").val("${numPartis}");
		$("input[name=keyword]").val("${keyword}");
		var url  = "/system/user/lock?id="+id;
		$("#query").attr("action",url);
		$("#query").submit();
	});
}
function del(id){
	parent.confirmDialog("${LANG['bizconf.jsp.system.hostlist.res4']}",function(){
		$("input[name=sortField]").val("${sortField }");
		$("input[name=sortRule]").val("${sortRule}");
		$("input[name=numPartis]").val("${numPartis}");
		$("input[name=keyword]").val("${keyword}");
		var url  = "/system/user/delHostUser?id="+id;
		$("#query").attr("action",url);
		$("#query").submit();
	});
}


</script>
</script>

