<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>
<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
<!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
<!--[if lt IE 7]><script src="/assets/js/lib/belatedpng.js"></script><![endif]-->
<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css" />
</head>
<body>
	<div class="details-dialog view-conf">
		<div class="details-container" style="height: 465px;">
			<div style="margin-top:0px;">
				<pre style="color: #ef8201;font-size:13px;margin-bottom: 2px">${LANG['bizconf.jsp.conf.copy.confinformation']}${LANG['website.common.symbol.colon']}</pre>
				<%--	<textarea id="copyText" onmouseover="this.select()" readonly="readonly" style="overflow-y:scroll;height: 150px;width: 635px; padding: 10px; background-color: #EEE; color: #666666;" >--%>
				<textarea id="copyText" onmouseover="this.focus()" onfocus="this.select()" readonly="readonly" style="overflow-y:scroll;height: 320px;width: 635px; padding: 10px; background-color: #EEE; color: #666666;">
${LANG['website.user.view.conf.title']}${LANG['website.common.symbol.colon']}${conf.confName}
<c:if test="${conf.permanentConf eq 0 and !conf.clientCycleConf and !conf.portalCycleConf }">${LANG['website.user.conf.report.conf.time']}${LANG['website.common.symbol.colon']}<fmt:formatDate value="${conf.startTime}" pattern="yyyy-MM-dd HH:mm" /> (${conf.timeZoneDesc})
</c:if><c:choose><c:when test="${conf.portalCycleConf}">${LANG['website.user.conf.report.conf.time']}${LANG['website.common.symbol.colon']}<fmt:formatDate value="${conf.startTime}" pattern="yyyy-MM-dd HH:mm" />(${conf.timeZoneDesc}) 
${LANG['website.user.view.conf.cycle.period']}${LANG['website.common.symbol.colon']}${cycleMode}
${LANG['bizconf.jsp.create_Reservation_Conf.res92']}${LANG['website.common.symbol.colon']}${repeatScope}
</c:when><c:otherwise></c:otherwise>
</c:choose>
${LANG['bizconf.jsp.conf.copy.join.way']}${LANG['website.common.symbol.colon']}
 ------------------------------------------------------------
${LANG['bizconf.jsp.conf.copy.through.equipment']}${LANG['website.common.symbol.colon']}
${LANG['bizconf.jsp.conf.copy.click.url']}${LANG['website.common.symbol.colon']}${userUrl }
${LANG['bizconf.jsp.conf.copy.or.visit']} http://${siteUrl} ${LANG['bizconf.jsp.conf.copy.join']}${LANG['website.common.symbol.comma']}${LANG['bizconf.jsp.conf.copy.input.confid']}${LANG['website.common.symbol.colon']}${conf.confZoomId }      ${LANG['website.user.join.page.cpass']}${LANG['website.common.symbol.colon']}${conf.hostKey }<c:if
 test="${conf.hostKey == '' }">${LANG['com.bizconf.vcaasz.entity.ConfBase.confType.0']}</c:if>
 ------------------------------------------------------------
${LANG['bizconf.jsp.conf.copy.scanning.join']}${LANG['website.common.symbol.colon']} 
${LANG['bizconf.jsp.conf.copy.click.qrcode']}${LANG['website.common.symbol.colon']}${tdcUrl}
 ------------------------------------------------------------
${LANG['bizconf.jsp.conf.copy.through.h323sip']}${LANG['website.common.symbol.colon']}
${LANG['bizconf.jsp.conf.copy.conf.address']}${LANG['website.common.symbol.colon']}${rcips}
${LANG['website.user.view.conf.conf.confCode']}${LANG['website.common.symbol.colon']}${conf.confZoomId } 
<c:if test="${!empty conf.phonePass }">${LANG['website.user.join.page.cpass.h323orsip']}${LANG['website.common.symbol.colon']}${conf.phonePass}</c:if>
 ------------------------------------------------------------
	 </textarea>
			</div>
			<c:if test="${conf.confStatus != CONF_STATUS_FINISHED }">
				<div class="addresses" style="margin-top: 10px">
					<div class="participants">
						${LANG['website.user.view.conf.join.url'] }： <input type="text"
							class="input-text" onmouseover="this.focus()"
							onfocus="this.select()" readonly="readonly" value="${userUrl }" />
					</div>
				</div>
				<div class="form-buttons"
					style="width:673px; margin-left: -20px;margin-top: 8px">
					<a class="button anchor-cancel" onclick="javascript:closeDialog();">${LANG['website.common.option.cancel']}</a>
				</div>
			</c:if>
		</div>
	</div>
	<input type="hidden" id="ForIE6Select" />
	<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
	<script type="text/javascript">
		function setCursor(id, position) {
			var txtFocus = document.getElementById(id);
			if ($.browser.msie) {
				var range = txtFocus.createTextRange();
				range.move("character", position);
				range.select();
			} else {
				txtFocus.setSelectionRange(position, position);
				txtFocus.focus();
			}
		}
		function joinMeeting(joinType, cId) {//cId,cPass,code){
			top.joinMeeting(joinType, cId);
		}
		function closeDialog(result) {
			var dialog = parent.$("#viewMeeting");
			if (result) {
				dialog.trigger("closeDialog", [ result ]);
			} else {
				dialog.trigger("closeDialog");
			}
		}
		function viewInviteUser(id) {
			parent.editInventContact(id);
		}
		function showCompereSecure() {
			$(".compereUser").show();
		}
		$(document)
				.ready(
						function() {
							$("#compereUserBut")
									.click(
											function(e) {
												if ($(".compereUser").css(
														"display") == "none") {
													$(".compereUser").show();
													$("#compereUserBut")
															.attr("value",
																	"${LANG['website.common.option.hide'] }");
												} else {
													$(".compereUser").hide();
													$("#compereUserBut")
															.attr("value",
																	"${LANG['website.common.option.look'] }");
												}
											});
							$("#compereBut")
									.click(
											function(e) {
												if ($(".compereHostUrl").css(
														"display") == "none") {
													$(".compereHostUrl").show();
													$("#compereBut")
															.attr("value",
																	"${LANG['website.common.option.hide'] }");
												} else {
													$(".compereHostUrl").hide();
													$("#compereBut")
															.attr("value",
																	"${LANG['website.common.option.look'] }");
												}
											});
							setCursor("ForIE6Select",
									$("#ForIE6Select").val().length);
						});
	</script>
</body>
</html>