<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript">
var html = "";
	html += "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";
	html += "<html xmlns=\"http://www.w3.org/1999/xhtml\">";
	html += "<head>";
	html += "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />";
	html += "<title>index</title>";
	html += "<!--[if lt IE 9]><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\" /><![endif]-->";
	html += "<!--[if lt IE 9]><script src=\"/assets/js/lib/html5.js\"><\/script><![endif]-->";
	html += "	<!--[if lt IE 7]><script src=\"/assets/js/lib/belatedpng.js\"><\/script><![endif]-->";
	html += "<link type=\"text/css\" rel=\"stylesheet\" href=\"/assets/css/base.css\" />";
	html += "<link type=\"text/css\" rel=\"stylesheet\" href=\"/assets/css/apps/dialog.css\" />";
	html += "<link type=\"text/css\" rel=\"stylesheet\" href=\"/assets/css/apps/concat.css\" />";
	html += "</head>";
	html += "<body style=\"padding: 0px; \">";
	//导入成功显示的内容
	<c:if test="${statu eq 1 }">
	html += "<div class=\"import-dialog\" style=\"height: 482px;overflow: hidden;\">";
	html += "<div class=\"face-success\"  style=\"margin-top:30px;\">${LANG['website.user.contact.importcallback.successinfo']}</div>";
	html += "<div class=\"import-success\" style=\"padding: 0 105px 20px; overflow-y: auto; height: 182px;\">";
		<c:if test="${excelimp}">
		html += "<div class=\"box\">";
		html += "<div class=\"head\">";
		html += "				${LANG['website.user.contact.importcallback.total']}${itemnum }${LANG['website.user.contact.importcallback.items']}${LANG['website.user.contact.importcallback.imported']}${iptitemnum}${LANG['website.user.contact.importcallback.item']}";
		html += "			</div>";
				/* */
				<c:if test="${fn:length(repeated)>0 }">
				html += "			<div class=\"body\">";
				html += "<p style=\"padding-left: 30px;\">${LANG['website.user.contact.importcallback.repeatitem']}</p>";
				html += "	<ul class=\"items clearfix\">";
						<c:forEach var="rpt" items ="${repeated}"  varStatus="status">	
						html += "		<li>${rpt.contactName }</li>";
						</c:forEach>
						html += "</ul>";
						html += "</div>";
				</c:if>
				<c:if test="${fn:length(dissaveable)>0 }">
				html += "<div class=\"body\">";
				html += "<p style=\"padding-left: 30px;\">${LANG['website.user.contact.importcallback.unvildate']}</p>";
				html += "	<ul class=\"items clearfix\">";
							<c:forEach var="unsave" items ="${dissaveable}"  varStatus="status">	
							html += "				<li>${unsave.contactName}</li>";
							</c:forEach>
							html += "		</ul>";
							html += "	</div>";
				</c:if>
				
				html += "</div>";
		</c:if>
		html += "</div>";
	html += "<div class=\"form-buttons\">";
	html += "	<input type=\"button\" value=\"${LANG['website.common.option.confirm']}\" onclick=\"closeDialog();\" class=\"button input-gray\">";
	html += "</div>";
	html += "</div>";
	html += "</div>";
	</c:if>
	//导入失败显示的内容
	<c:if test="${statu != 1 }">
		html += "<div class=\"invite-dialog\">";
		html += "<div class=\"wrapper\">";
		html += "	<div style=\"height: 429px;overflow: hidden;\">";
		html += "	 <div class=\"face-failure\">${LANG['website.user.contact.importcallback.failinfo']}";
		//html += "			 <button class=\"input-gray\" onclick=\"closeDialog();\">重新发送邀请</button>";
		html += "	 </div> ";	
		html += "	</div>";
		html += "<div class=\"form-buttons\">";
		html += "	<input type=\"button\" class=\"button input-gray\" value=\"${LANG['website.common.option.confirm']}\" onclick=\"closeDialog();\">";
		html += "	</input>";
		html += " </div>";
		html += " </div>";
	</c:if>
	
	html += "<script type=\"text/javascript\" src=\"/assets/js/lib/jquery-1.8.3.js\"><\/script>";
	html += "<script type=\"text/javascript\" src=\"/assets/js/lib/jquery-ui-1.9.2.custom.js\"><\/script>";
	html += "<script type=\"text/javascript\" src=\"/assets/js/module/biz.plugins.js\"><\/script>";
	html += "<script type=\"text/javascript\">";
	html += "function closeDialog() {";
		html += "	var dialog = top.$(\"#importContact\");";
		html += "	dialog.trigger(\"closeDialog\");";
		html += "}";
		html += "<\/script>";
	html += "</body>";
	html += "</html>";
	//刷新列表
	top.refreshChildIframe("/admin/contact/list","contentFrame"); 
	var win = top.$("#importContact").find("iframe")[0].contentWindow || parent.$("#viewGroup").find("iframe")[0].window;
	win.document.write(html);
	
	top.$("#importContact").height(528);
	if($.browser.msie){
		top.$("#importContact").parent().height(480);
		top.$("#importContact").height(470);
		top.$("#importContact").find("iframe").height(470);
	}
</script>

