<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<script type="text/javascript">
	var statu = "${statu}"
	var showinfo = "<div style=\"text-align:left;margin-left:30px;\">${LANG['site.user.import.cbinfo1']} ${itemnum} ${LANG['site.user.import.cbinfo2']} ${iptitemnum} ${LANG['site.user.import.cbinfo3']}";
	<c:if test="${itemnum eq iptitemnum}">
		showinfo += "</div>"
	</c:if>
	<c:if test="${itemnum gt iptitemnum}">
		showinfo +=	"${LANG['admin.site.userimport.notimport']} ${itemnum-iptitemnum}${LANG['site.user.import.cbinfo3']}</div><br/>";
		//showinfo += "  <br/><br/> ";
		showinfo += "  <div style=\"text-align:left;margin-left:30px;\">${LANG['admin.site.userimport.infotail']} ${LANG['admin.site.userimport.accdetail']}</div> ";
	</c:if>
	//showinfo += "<br/>"
	//${LANG['bizconf.jsp.admin.importCallback.res1']}";
	<c:if test="${fn:length(repeated)>0}">
		showinfo += " <div style=\"text-align:left;margin-left:30px;\">${LANG['site.user.import.cbinfo4']}</div> <div style=\"text-align:left;margin-left:50px;\">";
		<c:forEach var="rpt" items="${repeated}" varStatus="s0">
			<c:choose>
				<c:when test="${not empty rpt.loginName }">
					showinfo += " ${rpt.loginName} ";
				</c:when>
				<c:when test="${not empty rpt.trueName }">
					showinfo += " ${rpt.trueName} ";
				</c:when>
				<c:when test="${not empty rpt.userEmail }">
					showinfo += " ${rpt.userEmail} ";
				</c:when>
				<c:otherwise>
					showinfo += " ${LANG['admin.site.userimport.emptydata']}";
				</c:otherwise>
			</c:choose>
		</c:forEach>
				showinfo += " </div>";
	</c:if>
	showinfo += " <br/>";
	
	<c:if test="${fn:length(dissaveable)>0}">
		showinfo += " <div style=\"text-align:left;margin-left:30px;\">${LANG['site.user.import.cbinfo5']}</div> <div style=\"text-align:left;margin-left:50px;\">";
		<c:forEach var="unsave" items="${dissaveable}" varStatus="s1">
			<c:choose>
				<c:when test="${not empty unsave.loginName }">
					showinfo += " ${unsave.loginName} ";
				</c:when>
				<c:when test="${not empty unsave.trueName }">
					showinfo += " ${unsave.trueName} ";
				</c:when>
				<c:when test="${not empty unsave.userEmail }">
					showinfo += " ${unsave.userEmail} ";
				</c:when>
				<c:otherwise>
					showinfo += " ${LANG['admin.site.userimport.emptydata']}";
				</c:otherwise>
			</c:choose>
		</c:forEach>
				showinfo += " </div>";
	</c:if>
	var info = "";
	if(statu=="1"){
		info = showinfo;
	}else{
		info = "${LANG['site.user.import.cbinfo6']}${LANG['bizconf.jsp.admin.importCallback.res2']}";
	}
	window.parent.showInfo(info,statu);
	//alert("yes!");
</script>
