<%@page import="com.bizconf.vcaasz.constant.ConstantUtil"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.bizconf.vcaasz.entity.SiteBase"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>站点列表展示</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${ctx}/script/jquery-1.8.2.js"></script> 
</head>
<script>
		function saveSiteInfo(){
			
			document.getElementsByName("loginform")[0].submit();
		}
		
		function simpleQuery(){
			query.action="/system/site/listWithSignOrName";
			query.submit();
		}
		
		function advancedQuery(){
			query.action="/system/site/listWithCondition";
			query.submit();
		}
</script>

<body>

<form name="query" action="/system/site/list/" method="post">
	<table align = "center" style="width:1000px">
		<tr>
			<td colspan="2" align="center"  >站点列表</td>
		</tr>
		<tr>
			<td align="left">
				<input type="text" name="nameOrSign" value="请输入企业名称或标识查询" style="width:200px">
				<input type="button" value="查询" onclick="simpleQuery();">
			</td>
		</tr>

		<tr>
			<td align="right">
				<a href="${ctx}/system/site/new">创建站点</a>
			</td>
		</tr>
	</table>
	<table align = "center" style="width:900px">
			<tr>
				<td align="left">企业名称
				<input name="siteName" type="text"></td>
				<input name="id" type="hidden">
			</tr>
			
			<tr>
				<td align="left">企业标识
				<input name="siteSign" type="text"></td>
			</tr>
			<tr>
				<td align="left">站点类型
					<input name="siteFlag" type="radio" value="1" checked="checked">正式
					<input name="siteFlag" type="radio" value="2">试用
				</td>
			</tr>
			<tr>
				<td align="left">站点状态
					<input name="lockFlag" type="radio" value="1" checked="checked">正常
					<input name="lockFlag" type="radio" value="2">锁定
				</td>
			</tr>
			<tr>
				<td align="left">到期时间
				<input name="expireDateStart" type="text" value="${siteBase.expireDate}">---
				<input name="expireDateEnd" type="text" value="${siteBase.expireDate}"></td>
			</tr>
			<tr>
				<td>
					<input type="button" value="高级搜索" onclick="advancedQuery();">
				</td>
			</tr>
	</table>
	
	<table align="center" cellpadding="4" cellspacing="0" border="1" style="width:1000px">
		<tr>
			<td>企业名称</td>
			<td>企业标识</td>
			<td>站点类型</td>
			<td>最大参会方数</td>
			<td>到期时间</td>
			<td>用户名</td>
			<td>状态</td>
			<td style="width: 130px">操作</td>
		</tr>
		<c:forEach items = "${siteList}" var = "siteList" varStatus="status">
			<tr>
				<td>${siteList.siteName }</td>
				<td>${siteList.siteSign }</td>
				<c:if test="${siteList.siteFlag == 1 }">
					<td>正式</td>
				</c:if>
				<c:if test="${siteList.siteFlag == 2 }">
					<td>试用</td>
				</c:if>
				<td>${siteList.license }</td>
				<td>${siteList.expireDate }</td>
				<td>${siteList.createUser }</td>
				<c:if test="${siteList.lockFlag == 1}"> 
					<td>正常</td>
				</c:if>
				<c:if test="${siteList.lockFlag == 2}"> 
					<td>锁定</td>
				</c:if>
				<td align="left">
					<c:if test="${siteList.lockFlag == 2}"> 
						<a href="${ctx}/system/site/unlock/${siteList.id}">解锁</a>
					</c:if>
					<c:if test="${siteList.lockFlag == 1}"> 
						<a href="${ctx}/system/site/lock/${siteList.id}">锁定</a>
					</c:if>
					<a href="${ctx}/system/site/update/${siteList.id}">修改</a>
					<a href="${ctx}/system/site/delete/${siteList.id}">删除</a>
				</td>
			</tr>
		</c:forEach>
		
	<tr>
    <td class="table_bottom" height="38" colspan="8">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td ><div><span width="33%" class="STYLE22" align="left">&nbsp;&nbsp;&nbsp;&nbsp;共 <strong>${pageModel.pageCount}</strong> 页,共有<strong>${pageModel.rowsCount}</strong> 条记录</span></div></td>
        <td width="67%"><table width="420" border="0" align="right" cellpadding="0" cellspacing="0" >
          <tr>
            <td><div align="center" class="page_shouye"><a href="#">首页</a></div></td>
            <td><div align="center" class="page_next"><a href="#">下一页</a></div></td>
            <td><div align="center" class="page_shuzi"><a href="#">1</a>&nbsp;&nbsp;<a href="#">2</a>&nbsp;&nbsp;<a href="#">3</a>&nbsp;&nbsp;<a href="#">4</a>&nbsp;&nbsp;<a href="#">5</a></div></td>
            
            <td><div class="page_next"><a href="#">下一页</a></div></td>
            <td><div class="page_shouye"><a href="#">尾页</a></div></td>
            <td class="STYLE22"><div class="page_shouye"><a href="#">转到</a></div></td>
            <td><div align="center">
            <input type="text" name="textfield" id="textfield"  style="width:22px; height:16px; font-size:12px; border:solid 1px #CACACA; line-height:16px;"/>
            </div></td>
            <td class="STYLE22">页</div></td>
            <td class="STYLE22"><div class="page_shouye01">跳转</div></td>
           </tr>  
           </table>  
        </td> 
       </td>
      </tr>
    </table>
    </td>
  </tr>
  
	</table>
	</form>
</body>
</html>