<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<TITLE>Insert title here</TITLE>
<SCRIPT type="text/javascript" src="${ctx}/static/js/jquery-1.8.3.js"></SCRIPT>
<SCRIPT type="text/javascript" src="${ctx}/static/js/jquery.layout-latest.js"></SCRIPT>
<script type="text/javascript">
function showURL(url){
    document.getElementById('rFrame').src=url;
} 
</script>
<SCRIPT type="text/javascript">
$(document).ready(function () {
	$('body').layout({ 
		north: {
		    enableCursorHotkey: false,
		    closable: false,
		    resizable: false,
		    spacing_open: 6,
		    spacing_closed: 6,
		    size: 80
		},
		south: {
		    enableCursorHotkey: false,
		    closable: false,
		    resizable: false,
		    spacing_open: 0,
		    spacing_closed: 0
		},
		west: {
		    enableCursorHotkey: false,
		    closable: false,
		    resizable: false,
		    spacing_open: 6,
		    spacing_closed: 6,
            size:220
		}
	});
});
</SCRIPT>

<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/layout.css"/>
 
</HEAD>
<BODY>
<!--页面右部内容区-->
<DIV class="ui-layout-center">

<script>
var  highlightcolor='#EDF4FA';
//此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
var  clickcolor='#51b2f6';
function  changeto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=highlightcolor;
}
}

function  changeback(){
if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
return
if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
//source.style.backgroundColor=originalcolor
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}

function  clickto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=clickcolor;
}
else
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}
</script>
<div class="main_right">
 <div class="m_top">
    <div class="text_box">
    	<input type="text" name="textfield" id="textfield01" value="请输入企业名称，企业标识进行查询..." onfocus="this.value=' '; this.onfocus=null;" />
    	<a class="sousuo" title="搜索" href="#"><img src="images/sousuo.jpg" width="29" height="24" /></a><a class="gaoji" title="高级搜索" href="#">高级搜索</a>
    </div>
    <div class="make_new"><a href="#">创建站点</a></div>
  </div>


<table width="98.5%" border="0" align="center" cellpadding="0" cellspacing="0" id="table_box" style=" margin-left:10px; margin-right:10px; border:#D6D6D6 1px solid; border-top:none; border-bottom:none;">
  
  <tr class="table002" height="32" >
    <td><table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#a8c7ce" onmouseover="changeto()"  onmouseout="changeback()">
      <tr class="table003" height="38" >
        <td width="20%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span>企业名称</span></div></td>
        <td width="10%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span>企业标识&nbsp;</span><a class="paixu01" href="#"><img src="images/paixuzong.jpg" width="6" height="13" /></a></div></td>
        <td width="10%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span>站点类型&nbsp;</span><a class="paixu02" href="#" title="点击进行排序"  ><img src="images/paixuzong.jpg" width="6" height="13" /></a></div></td>
        <td width="10%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span>最大参会方数</span></div></td>
        <td width="15%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span>到期时间&nbsp;</span><a class="paixu03" href="#" title="点击进行排序"  ><img src="images/paixuzong.jpg" width="6" height="13" /></a></div></td>
        <td width="10%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span>用户名</span></div></td>
        <td width="10%" height="38" bgcolor="d3eaef" class="STYLE10"><div align="center"><span>状态</span></div></td>
        <td width="15%" height="38" bgcolor="d3eaef" class="STYLE10" class="STYLE_none" style="border-right:none"><div align="center"><span>操作</span></div></td>
      </tr>
      <tr class="table001" height="32" >
        <td height="32" bgcolor="#FFFFFF"><div align="center"><span class="STYLE19">上海会畅通讯股份有限公司</span></div></td> 
        <td height="32" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">Bizconf</span></div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">正式</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">100</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">2013-02-26</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">robert</div></td>
        <td height="32" bgcolor="#FFFFFF"><div align="center" class="STYLE21"><a href="#">锁定</a></div></td>
        <td height="32" bgcolor="#FFFFFF"><div align="center" class="STYLE21"><a href="#">解锁</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">删除</a></div></td>
       </tr>
      <tr class="table001" height="32">  
        <td height="32" bgcolor="#FFFFFF"><div align="center"><span class="STYLE19">上海会畅通讯股份有限公司</span></div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">Bizconf</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">正式</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">100</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">2013-02-26</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">robert</div></td>
        <td height="32" bgcolor="#FFFFFF"><div align="center" class="STYLE21"><a href="#">正常</a></span></div></td>
        <td height="32" bgcolor="#FFFFFF"><div align="center" class="STYLE21"><a href="#">锁定</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">删除</a></div></td>
      </tr>
      <tr class="table001" height="32">
        <td height="32" bgcolor="#FFFFFF"><div align="center"><span class="STYLE19">上海会畅通讯股份有限公司</span></div>
        </td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">Bizconf</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">正式</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">100</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">2013-02-26</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">robert</div></td>
        <td height="32" bgcolor="#FFFFFF"><div align="center" class="STYLE21"><a href="#">正常</a></span></div></td>
        <td height="32" bgcolor="#FFFFFF"><div align="center" class="STYLE21"><a href="#">锁定</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">删除</a></div></td>
      </tr>
      <tr class="table001" height="32">   
        <td height="32" bgcolor="#FFFFFF"><div align="center"><span class="STYLE19">上海会畅通讯股份有限公司</span></div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">Bizconf</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">正式</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">100</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">2013-02-26</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">robert</div></td>
       <td height="32" bgcolor="#FFFFFF"><div align="center" class="STYLE21"><a href="#">正常</a></span></div></td>
        <td height="32" bgcolor="#FFFFFF"><div align="center" class="STYLE21"><a href="#">锁定</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">删除</a></div></td>
      </tr>
      <tr class="table001" height="32">
        <td height="32" bgcolor="#FFFFFF"><div align="center"><span class="STYLE19">上海会畅通讯股份有限公司</span></div> </td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">Bizconf</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">正式</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">100</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">2013-02-26</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">robert</div></td>
       <td height="32" bgcolor="#FFFFFF"><div align="center" class="STYLE21"><a href="#">正常</a></span></div></td>
        <td height="32" bgcolor="#FFFFFF"><div align="center" class="STYLE21"><a href="#">锁定</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">删除</a></div></td>
      </tr>
      <tr class="table001" height="32">
        <td height="32" bgcolor="#FFFFFF"><div align="center"><span class="STYLE19">上海会畅通讯股份有限公司</span></div>  
        </td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">Bizconf</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">正式</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">100</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">2013-02-26</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">robert</div></td>
       <td height="32" bgcolor="#FFFFFF"><div align="center" class="STYLE21"><a href="#">正常</a></span></div></td>
        <td height="32" bgcolor="#FFFFFF"><div align="center" class="STYLE21"><a href="#">锁定</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">删除</a></div></td>
      </tr>
      <tr class="table001" height="32">
        <td height="32" bgcolor="#FFFFFF"><div align="center"><span class="STYLE19">上海会畅通讯股份有限公司</span></div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">Bizconf</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">正式</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">100</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">2013-02-26</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">robert</div></td>
        <td height="32" bgcolor="#FFFFFF"><div align="center" class="STYLE21"><a href="#">正常</a></span></div></td>
        <td height="32" bgcolor="#FFFFFF"><div align="center" class="STYLE21"><a href="#">锁定</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">删除</a></div></td>
      </tr>
      <tr class="table001" height="32">
        <td height="32" bgcolor="#FFFFFF"><div align="center"><span class="STYLE19">上海会畅通讯股份有限公司</span></div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">Bizconf</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">正式</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">100</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">2013-02-26</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">robert</div></td>
        <td height="32" bgcolor="#FFFFFF"><div align="center" class="STYLE21"><a href="#">正常</a></span></div></td>
        <td height="32" bgcolor="#FFFFFF"><div align="center" class="STYLE21"><a href="#">锁定</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">删除</a></div></td>
      </tr>
      <tr class="table001" height="32">
        <td height="32" bgcolor="#FFFFFF"><div align="center"><span class="STYLE19">上海会畅通讯股份有限公司</span></div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">Bizconf</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">正式</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">100</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">2013-02-26</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">robert</div></td>
       <td height="32" bgcolor="#FFFFFF"><div align="center" class="STYLE21"><a href="#">正常</a></span></div></td>
        <td height="32" bgcolor="#FFFFFF"><div align="center" class="STYLE21"><a href="#">锁定</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">删除</a></div></td>
      </tr>
       <tr>
        <td height="32" bgcolor="#FFFFFF"><div align="center"><span class="STYLE19">上海会畅通讯股份有限公司</span></div>
        </td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19" b><div align="center">Bizconf</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">正式</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">100</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">2013-02-26</div></td>
        <td height="32" bgcolor="#FFFFFF" class="STYLE19"><div align="center">robert</div></td>
        <td height="32" bgcolor="#FFFFFF"><div align="center" class="STYLE21"><a href="#">正常</a></span></div></td>
        <td height="32" bgcolor="#FFFFFF"><div align="center" class="STYLE21"><a href="#">锁定</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">删除</a></div></td>
      </tr>
    </table>
  </tr>
  <tr>
    <td class="table_bottom" height="38"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td ><div><span width="33%" class="STYLE22" align="left">&nbsp;&nbsp;&nbsp;&nbsp;共 <strong>10</strong> 页,共有<strong> 92</strong> 条记录</span></div></td>
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
    </table></td>
  </tr>
</table>

</div>
</DIV>
</BODY>
</HTML>
