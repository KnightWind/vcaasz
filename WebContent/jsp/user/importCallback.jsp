<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<script type="text/javascript">
	var statu = "${statu}"
	<c:if test="${info!=null}">
		window.parent.showInfo('${info}',statu);
	</c:if>
	
	<c:if test="${info == null}">
		var showinfo = "${LANG['site.user.import.cbinfo1']} ${itemnum} ${LANG['site.user.import.cbinfo2']} ${iptitemnum} ${LANG['site.user.import.cbinfo3']}"
		//${LANG['bizconf.jsp.importCallback.res1']}";
		<c:if test="${repeated != null}">
			showinfo += " ${LANG['site.user.import.cbinfo4']} ";
			<c:forEach var="rpt" items="${repeated}" varStatus="status">
				showinfo += "  ${rpt.contactName} ";
			</c:forEach>
		</c:if>
		<c:if test="${dissaveable != null}">
			showinfo += " ${LANG['site.user.import.cbinfo5']} ";
			<c:forEach var="unsave" items="${dissaveable}" varStatus="status">
			showinfo += "  ${unsave.contactName} ";
			</c:forEach>
		</c:if>
		var info = "";
		if(statu=="1"){
			info = showinfo;
		}else{
			info = "${LANG['site.user.import.cbinfo6']}";
		}
		window.parent.showInfo(showinfo,statu);
	</c:if>
	history.go(-1);
</script>
