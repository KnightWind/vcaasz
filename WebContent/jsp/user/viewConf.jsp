<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>${LANG['bizconf.jsp.conf_list_index.res3']}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- Css -->	
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/popupbox.css?ver=${version}"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/box.css?ver=${version}"/>

<!-- Javascript -->
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js?ver=${version}"></SCRIPT>
<cc:confList var="CONF_CYCLE_INFINITE_TRUE"/>
<cc:confList var="CONF_CYCLE_INFINITE_FALSE"/>
<cc:confList var="CONF_PERMANENT_ENABLED_CHILD"/>

<c:if test="${conf.permanentConf == CONF_PERMANENT_ENABLED_CHILD}">
	<c:set var="confId" value="${conf.belongConfId}"></c:set>
</c:if>

<c:set var="time" value="${LANG['system.cycle.end.time']}"></c:set>
<c:set var="count" value="${ confCycle.repeatCount }"></c:set>
<c:set var="repeatInfo" value="${fn:replace(time, '%s', count)}" />

<script type="text/javascript">
	$(function() {
		var confType = "${confType}";
		if(confType == "") {
			$(".confTypeTR").hide();
		}	
		
		var confDesc = "${conf.confDesc}";
		if(confDesc == ""){
			$(".confDescTR").hide();
		}
		
	});


</script>


<script type="text/javascript">
function joinMeeting(joinType,cId){
	parent.joinMeeting(joinType,cId);
	closeDialog();
} 

function editInventContact(id) {
	parent.editInventContact(id);
}

function copyConfLink(meintext) {
	if (window.clipboardData){
		// the IE-manier
		window.clipboardData.setData("Text", meintext);
	} else {
		parent.errorDialog("${LANG['bizconf.jsp.viewConf.res1']}");
	}

}
</script>

</head>
<body onload="loaded()">
<table class="overlay-panel" border="0" cellpadding="0" cellspacing="0" >
    <tbody>
      <tr class="no-header">
        <td class="overlay-hdL"></td>
        <td class="overlay-hdC"></td>
        <td class="overlay-hdR"></td>
      </tr>
      <tr>
        <td class="overlay-bdL"></td>
        
             <!--${LANG['bizconf.jsp.add_contacts.res2']}========================================================================-->
      
      <td class="overlay-content">
        <div class="First_Steps_quick_meeting02" style=" background:#FFF">
          <div class="First_Steps_title_meeting02"> <a href="javascript:closeDialog();"></a>
            <h3 class="tit_a_meeting">${LANG['system.menu.info.meeting']}</h3>
          </div>
          <div style=" background:#fff"><img class="toa_quick_meeting02" src="/static/images/min.jpg" width="620" height="1" /></div>
          <div class="mmm">
          <div class="First_Steps_main_quick_meeting">
            <table width="320" cellpadding="0" cellspacing="0" border="0" id="xx_meeting02" style=" float:left;">
              <tr>
                <td align="right" class="xx_07">${LANG['system.list.meeting.title']}${LANG['bizconf.jsp.viewConf.res3']}</td>
                <td align="left"><span class="xx_08" title="${conf.confName }">${conf.confName }</span></td>
              </tr>
            
             <c:if test="${!empty confCycle}">
               <tr>
                <td align="right" class="xx_07">${LANG['user.menu.conf.mode.regular']}${LANG['bizconf.jsp.viewConf.res3']}</td>
                <td align="left"><span class="xx_08">${cycleMode }</span></td>
              </tr> 
             <tr>
                <td align="right" class="xx_07">${LANG['user.menu.conf.duplicate']}${LANG['bizconf.jsp.viewConf.res3']}</td>
                <td align="left">
                <c:if test="${confCycle.infiniteFlag == CONF_CYCLE_INFINITE_FALSE  and confCycle.repeatCount == 0}">
                <span class="xx_08">
	                <fmt:formatDate value="${confCycle.beginDate}" pattern="yyyy-MM-dd" />----<fmt:formatDate value="${confCycle.endDate}" pattern="yyyy-MM-dd" />
                </span>
                </c:if>
                <c:if test="${confCycle.infiniteFlag == CONF_CYCLE_INFINITE_FALSE  and confCycle.repeatCount > 0}">
                <span class="xx_08" title="${repeatInfo }">
	                ${repeatInfo }
                </span>
                </c:if>
                <c:if test="${confCycle.infiniteFlag == CONF_CYCLE_INFINITE_TRUE}">
                <span class="xx_08" title="${LANG['bizconf.jsp.create_Reservation_Conf.res34']}">
	                	${LANG['bizconf.jsp.create_Reservation_Conf.res34']}
                </span>
                </c:if>
                </td>
              </tr> 
             </c:if> 
              
                <tr>
                <td align="right" class="xx_07">${LANG['user.menu.conf.starttime']}${LANG['bizconf.jsp.viewConf.res3']}</td>
                <td align="left">
                	<span class="xx_08">
	                	<fmt:formatDate value="${conf.startTime}" pattern="yyyy-MM-dd HH:mm" />
			            <c:if test="${!empty user }">
			            	(${user.timeZoneDesc })
			            </c:if>
			            <c:if test="${empty user }">
		                	(${site.timeZoneDesc })
			            </c:if>
		            </span>
                </td>
              </tr>
              <tr>
                <td align="right" class="xx_07">
                <c:if test="${conf.permanentConf eq 1 or conf.permanentConf eq 2}">
                	${LANG['user.menu.conf.endtime']}：
                </c:if>
                <c:if test="${conf.permanentConf eq 0}">
                	${LANG['user.menu.conf.duration']}：
                </c:if>
                </td>
                <td align="left"><span class="xx_08">
                <c:if test="${conf.permanentConf eq 1 or conf.permanentConf eq 2}">${LANG['bizconf.jsp.viewConf.res4']}
                	<fmt:formatDate value="${conf.endTime}" pattern="yyyy-MM-dd HH:mm" />
			            <c:if test="${!empty user }">
			            	(${user.timeZoneDesc })
			            </c:if>
			            <c:if test="${empty user }">
		                	(${site.timeZoneDesc })
			            </c:if>
                </c:if>
                <c:if test="${conf.permanentConf eq 0}">
               	 ${duration}
                </c:if>
                </span></td>
              </tr>
             
<!--              <tr>-->
<!--                <td align="right" class="xx_07">${LANG['user.menu.conf.invite.number']}${LANG['bizconf.jsp.viewConf.res3']}</td>-->
<!--                <td align="left"><span class="xx_08">${inviteUserCount}</span></td>-->
<!--              </tr>-->
              
            <c:if test="${!empty user}">   
              <tr>
                <td align="right" class="xx_07">${LANG['user.menu.conf.participant.confid']}：</td>
                <td align="left"><span class="xx_08">${conf.userSecure }</span></td>
              </tr>
              <c:if test="${user.id eq conf.compereUser}">
	              <tr>
	                <td align="right" class="xx_07">${LANG['user.menu.conf.host.confid']}：</td>
	                <td align="left">
	                  <p class="xx_08">${conf.compereSecure } </p>
	                </td>
	              </tr>
	              
	              <c:if test="${conf.hostKey != ''}">
		              <tr>
		                <td align="right" class="xx_07">${LANG['bizconf.jsp.create_Reservation_Conf.res58']}：</td>
		                <td align="left">
		                  <p class="xx_08">${conf.hostKey}</p>
		                </td>
		              </tr>
	              </c:if>
              </c:if>
            </c:if>  
              
            </table>
          </div>
          <img src="/static/images/add_li_bg.png" width="5" height="200" style=" float:left;" />
          <div class="First_Steps_main_quick_meeting" style=" float:left">
            <table width="320" cellpadding="0" cellspacing="0" border="0" id="xx_meeting02">
              
               <tr class="confTypeTR">
                <td align="right" class="xx_07">${LANG['user.menu.conf.conftype']}${LANG['bizconf.jsp.viewConf.res3']}</td>
                <td align="left">
                	<span class="xx_08" title="${confType }">${confType}</span>
               	</td>
              </tr>
            
              <tr>
                <td align="right" class="xx_07">${LANG['user.menu.conf.aheadtime']}${LANG['bizconf.jsp.viewConf.res3']}</td>
                <td align="left"><span class="xx_08">${ aheadTime}</span></td>
              </tr>
              <tr>
                <td align="right" class="xx_07">${LANG['user.menu.conf.invite.number']}${LANG['bizconf.jsp.viewConf.res3']}</td>
                <td align="left"><span onclick="<c:if test="${!empty user}">editInventContact('${confId}')</c:if>" style="color:blue;text-decoration: underline;cursor:pointer;">${inviteUserCount}</span></td>
              </tr>
              <tr>
                <td align="right" class="xx_07">${LANG['user.menu.conf.func']}${LANG['bizconf.jsp.viewConf.res3']}</td>
                <td align="left"><span class="xx_08" title="${clientFunc}">${clientFunc} </span></td>
              </tr>
              <cc:confList var="CONF_STATUS_FINISHED"/>
              <c:if test="${conf.confStatus != CONF_STATUS_FINISHED }">
	              <c:if test="${!empty user}"> 
	              	<c:if test="${user.id eq conf.compereUser}">
		              <tr>
		                <td align="right" class="xx_07">${LANG['user.menu.conf.host.url']}${LANG['bizconf.jsp.viewConf.res3']}</td>
		                <td align="left"><span class="" style="margin-left: 5px;"><input readonly="readonly" onmouseover="this.select()" type="text" value="${hostUrl}" style="width: 150px;height:20px;line-height:20px;border:1px solid #666666"/></span></td>
		              </tr>
		            </c:if>
		              
		              <tr>
		                <td align="right" class="xx_07">${LANG['user.menu.conf.act.url']}${LANG['bizconf.jsp.viewConf.res3']}</td>
		                <td align="left"><span class="" style="margin-left: 5px;"><input readonly="readonly" onmouseover="this.select()" type="text" value="${userUrl }" style="width: 150px;height:20px;line-height:20px;border:1px solid #666666"/></span></td>
		              </tr>
	             </c:if>
	             
	             <c:if test="${empty user && conf.publicFlag == 1}">
	           		  <tr>
		                <td align="right" class="xx_07">${LANG['user.menu.conf.act.url']}${LANG['bizconf.jsp.viewConf.res3']}</td>
		                <td align="left"><span class="" style="margin-left: 5px;"><input readonly="readonly" onmouseover="this.select()" type="text" value="${userUrl }" style="width: 150px;height:20px;line-height:20px;border:1px solid #666666"/></span></td>
	              	  </tr>
	             </c:if>
             </c:if>
              <tr class="confDescTR">
                <td align="right" class="xx_07">${LANG['user.menu.conf.desc']}${LANG['bizconf.jsp.viewConf.res3']}</td>
                <td align="left">
                  <p class="xx_08" title="${conf.confDesc }">${conf.confDesc }</p>
                </td>
              </tr>
            </table>
           
          </div></div>
          
          
          
          
          <div class="bb" style="clear: both;">
              <cc:confList var="CONF_STATUS_OPENING"/>
		      <cc:confList var="CONF_STATUS_SUCCESS"/>
		      <cc:confList var="JOIN_TYPE_CONFID"/>
	          <div class="btn_message01">
				  <span class="button_common">
				      <a href="javascript:closeDialog();"><img src="/static/images/back.png" width="17" height="14" align="absmiddle" style=" margin-right:5px; margin-left:5px" />${LANG['website.message.back']}</a>
				  </span>
	       	  </div>
	       	  
	       	  <c:if test="${conf.confStatus == CONF_STATUS_SUCCESS ||  conf.confStatus == CONF_STATUS_OPENING}">
		          <div class="btn_message02">
		          	<c:if test="${conf.joinTimeFlag}">
		          		<span class="button_common">
			          		<a href="javascript:;" onclick="javascript:joinMeeting(${JOIN_TYPE_CONFID},'${conf.id}');">
			          			<img src="/static/images/bullet_go.png" width="16" height="16" align="absmiddle" style=" margin-right:5px; margin-left:5px" />
			          				<c:if test="${user.id != conf.compereUser}">
					           			${LANG['user.menu.conf.join']}
					           		</c:if>
					           		<c:if test="${user.id eq conf.compereUser}">
					           			${LANG['user.menu.conf.begin']}
					           		</c:if>
			       			</a>
			       		</span>
		          	</c:if>
		          	<c:if test="${!conf.joinTimeFlag}">
            			<span class="k_nv_06">
            				${LANG['bizconf.jsp.conf_list_index.notres48']}
            			</span>
           			</c:if>
		   		  </div>
	          </c:if>
       	</div>
        </div>
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
	var frame = parent.$("#viewMeeting");
	frame.trigger("loaded");
}
function closeDialog(){
	var frame = parent.$("#viewMeeting");
	frame.trigger("closeDialog");
}
</script>
