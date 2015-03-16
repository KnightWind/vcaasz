<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!--${LANG['bizconf.jsp.conf_list_index_more.res1']}7${LANG['bizconf.jsp.conf_list_index_more.res2']}-->
<cc:confList var="CONF_STATUS_OPENING"/>
<cc:confList var="CONF_STATUS_SUCCESS"/>
<cc:confList var="CONF_STATUS_FINISHED"/>
<cc:confList var="CONF_PERMANENT_ENABLED_MAIN"/>
<c:forEach var="newConfBase" items="${confList}">
<div class="extras-container">
		<div class="extras-tr" style="border-bottom:${empty newConfBase.confDesc?'1px solid #D2D8DB':'1px dashed #D2D8DB'}">
			<div class="date_part">
				<fmt:formatDate var="comingStartTime" value="${newConfBase.startTime}" pattern="yyyy-MM-dd HH:mm:ss" />
				<fmt:formatDate var="comingEndTime" value="${newConfBase.endTime}" pattern="yyyy-MM-dd HH:mm:ss" />
	          	<section class="timeago date-holder" title="${comingStartTime}" startTime="${comingStartTime}" endTime="${comingEndTime}" status="come"> 
			        	<span class="date">
	          			<fmt:formatDate value="${newConfBase.startTime}" pattern="yyyy-MM-dd" />
	          		</span> 
	          		<span class="time">
	          			<fmt:formatDate value="${newConfBase.startTime}" pattern="HH:mm"/>
	          		</span> 
	          	</section>
	        </div>
	        <div class="text_part" align="left">
	        	<section class="date-holder_01"> 
	        	
	        		<span class="title"><a href="javascript:;" onclick="viewConf(${newConfBase.id})">${newConfBase.confName}
	        			<c:forEach var="confCyc" items="${cycs}">
			        			<c:if test="${confCyc.id eq newConfBase.cycleId}">
			        				<c:choose>
			        					<c:when test="${confCyc.cycleType eq '1'}">
			        						<span style="color:#47ADE9;">(${LANG['bizconf.jsp.conf_list_index.res43']})</span>
			        					</c:when>
			        					<c:when test="${confCyc.cycleType eq '2'}">
			        						<span style="color:#47ADE9;">(${LANG['bizconf.jsp.conf_list_index.res44']})</span>
			        					</c:when>
			        					<c:when test="${confCyc.cycleType eq '3'}">
			        						<span style="color:#47ADE9;">(${LANG['bizconf.jsp.conf_list_index.res45']})</span>
			        					</c:when>
			        				</c:choose>
			        			</c:if>
		        		</c:forEach>		
	        		</a></span> 
<%-- 	        		<span class="content"><a href="#">${newConfBase.confDesc}</a></span> --%>
	        	</section>
	        </div>
			<div class="action_part" style="float: right;">
				<table  border="0" align="right" cellpadding="0" cellspacing="0">
	          		<tr>
	          			<td class="" ><!-- ${LANG['bizconf.jsp.conf_list_index.res53']} -->
	          				<c:if test="${user.id eq newConfBase.compereUser && (newConfBase.confStatus==CONF_STATUS_SUCCESS || newConfBase.confStatus==CONF_STATUS_OPENING)}">
	          					<div class="k_f" onclick="inventContact(${newConfBase.id})"><img src="/static/images/yaoqing.jpg" width="21" height="16" align="absmiddle" style=" padding-right:5px;" />${LANG['bizconf.jsp.conf_list_index.res13']}</div>
          					</c:if>
          				</td>
          				<c:if test="${newConfBase.permanentConf eq 0}">
	            			<td><div class="k02 viewtip durationTime" title="${LANG['bizconf.jsp.conf_list_index.res23']}:${newConfBase.duration}${LANG['bizconf.jsp.attended_conf_list.res10']}" duration="${newConfBase.duration}"><img src="${baseUrlStatic}/images/clockbtn.jpg" width="20" height="20" align="absmiddle" /><div class="k02_span durationMin">${newConfBase.duration}${LANG['bizconf.jsp.attended_conf_list.res10']}</div></div></td>
	            		</c:if>
	            		<c:if test="${newConfBase.permanentConf eq 1}">
	            			<td><div class="k02 viewtip durationTime" title="${LANG['bizconf.jsp.conf_list_pad.res4']}" duration="${LANG['bizconf.jsp.conf_list_pad.res4']}" endTime="<fmt:formatDate value="${newConfBase.endTime}" pattern="yyyy-MM-dd HH:mm" />" ><img src="${baseUrlStatic}/images/clockbtn.jpg" width="20" height="20" align="absmiddle" /><div class="k02_span durationMin" style="text-align: center;white-space:normal;line-height:normal;">${LANG['bizconf.jsp.conf_list_pad.res4']}</div></div></td>
	            		</c:if>
	            		<td><div class="k02  k09 viewtip" title="${LANG['bizconf.jsp.attendConfloglist.res5']}${newConfBase.compereName}"><img src="${baseUrlStatic}/images/zhuchuren.jpg" width="20" height="20" align="absmiddle" />${newConfBase.compereName}</div></td>
	            		<td>
	            			<c:if test="${newConfBase.confStatus==CONF_STATUS_SUCCESS}">
	            				<div name="attendeer" confId="${newConfBase.id}" onclick="<c:if test="${!empty user && (newConfBase.confStatus==CONF_STATUS_SUCCESS || newConfBase.confStatus==CONF_STATUS_OPENING)}">editInventContact('${newConfBase.id}')</c:if>" class="k04 attendee" title="${LANG['bizconf.jsp.conf_list_index.res35']}"><img src="${baseUrlStatic}/images/ico24.jpg" width="20" height="20" align="absmiddle" /><span class='attendCount'>0</span></div>
	            			</c:if>
	            			<c:if test="${newConfBase.confStatus==CONF_STATUS_OPENING}">
	            				<div class="k04 attendee" title="${LANG['bizconf.jsp.conf_list_index.res46']}${newConfBase.pcNum+newConfBase.phoneNum}"><img src="${baseUrlStatic}/images/renshu.png" width="20" height="20" align="absmiddle" />${newConfBase.pcNum+newConfBase.phoneNum}</div>
	            			</c:if>
	            			<c:if test="${newConfBase.confStatus==CONF_STATUS_FINISHED}">
	            				<div class="k04 attendee" title=""><img src="${baseUrlStatic}/images/renshu.png" width="20" height="20" align="absmiddle" />${newConfBase.pcNum+newConfBase.phoneNum}</div>
	            			</c:if>
	            		</td>
	            		<td><div title="${LANG['bizconf.jsp.conf_list_index.res47']}" class="viewtip extras-default" onclick="toggleExtra(this)"><a href="javascript:;"><img src="${baseUrlStatic}/images/caozuo.jpg" width="20" height="20" /></a></div></td>
	            		<td>
	            			<c:if test="${newConfBase.joinTimeFlag}">
		            			<div class="k06">
		            				<c:if test="${newConfBase.confStatus==CONF_STATUS_FINISHED}">
		            					<c:if test="${user.id eq newConfBase.createUser}">
								              <a href="javascript:reCreateReservationConf(${newConfBase.id});">${LANG['bizconf.jsp.attended_conf_list.res11']}</a>
							            </c:if>
		            				</c:if>
		            				<c:if test="${newConfBase.confStatus!=CONF_STATUS_FINISHED}">
		            					<c:if test="${user.id != newConfBase.compereUser}">
						            		<span class="button_common">
								          		<a href="javascript:joinMeeting(1,'${newConfBase.id}');"><img width="16" align="absmiddle" height="16" style=" margin-right:5px; margin-left:5px" src="/static/images/bullet_go.gif">${LANG['bizconf.jsp.conf_list_index.res48']}</a>
								       		</span>
								       	</c:if>
								       	<c:if test="${user.id eq newConfBase.compereUser}">
								       		<span class="button_common">
								          		<a href="javascript:joinMeeting(1,'${newConfBase.id}');"><img width="16" align="absmiddle" height="16" style=" margin-right:5px; margin-left:5px" src="/static/images/bullet_go.gif">${LANG['bizconf.jsp.conf_list_index.res54']}</a>
								       		</span>
								       	</c:if>           				
		            				</c:if>
			            		</div>
			            	</c:if>
			            	<c:if test="${!newConfBase.joinTimeFlag}">
		            			<div class="k_n_06" >
		            				<a href="javascript:;">${LANG['bizconf.jsp.conf_list_index.notres48']}</a>
		            			</div>
	            			</c:if>
            			</td>
	          		</tr>
        		</table>
			</div>
			<span class="fader" onclick="faderClick(this)"></span>		
		</div>
   		<div class="extras-action" style="border-bottom:${empty newConfBase.confDesc?'1px solid #D2D8DB':'1px dashed #D2D8DB'}">
				<div align="left" style="padding-top:10px">
					<c:if test="${newConfBase.confStatus==CONF_STATUS_SUCCESS}">
						<a class="email01" href="javascript:sendNoticeEmail(${newConfBase.id});"><img src="${baseUrlStatic}/images/email05.png" width="16" height="12" align="absmiddle" style=" padding-right:5px;"/>Outlook</a>
						<a class="email02" href="javascript:sendNoticeEmail(${newConfBase.id});" style="display: none;"> <img src="${baseUrlStatic}/images/ico002.png" width="16" height="11" align="absmiddle" style=" padding-right:5px;" />Gmail</a>
						<a class="email03" href="javascript:sendNoticeEmail(${newConfBase.id});" style="display: none;"><img src="${baseUrlStatic}/images/ico003.png" width="14" height="17" align="absmiddle" style=" padding-right:5px;" />Foxmail</a>
					</c:if>
				</div>
				<div align="right">
				<c:if test="${newConfBase.confStatus!=CONF_STATUS_FINISHED}">
					<c:if test="${user.id eq newConfBase.createUser}">
						<c:choose>
						 	<c:when test="${newConfBase.cycleId!=0}">
						 		<c:if test="${(newConfBase.permanentConf eq 1 || newConfBase.confStatus==CONF_STATUS_SUCCESS)}">
									<a class="email04" href="javascript:;" onclick="delAllConf(${newConfBase.cycleId},${newConfBase.id});">
										<img src="${baseUrlStatic}/images/ico005.png" width="12" height="17" align="absmiddle" style=" padding-right:5px;"/>${LANG['bizconf.jsp.conf_list_index.res55']}</a>
									<a class="email04" href="javascript:;" onclick="delConf(${newConfBase.id});">
									<img src="${baseUrlStatic}/images/ico005.png" width="12" height="17" align="absmiddle" style=" padding-right:5px;"/>${LANG['bizconf.jsp.add_contacts.res13']}</a>
								</c:if>
								<c:if test="${newConfBase.confStatus==CONF_STATUS_SUCCESS}">
								 	<a class="email05" href="javascript:;" onclick="updateCycleBookMeetingInfo(${newConfBase.id})">
										<img src="${baseUrlStatic}/images/ico004.png" width="16" height="16" align="absmiddle" style=" padding-right:5px;"/>${LANG['bizconf.jsp.conf_list_index.res56']}</a>	
									<a class="email05" href="javascript:;" onclick="updateBookMeeting(${newConfBase.id})">
										<img src="${baseUrlStatic}/images/ico004.png" width="16" height="16" align="absmiddle" style=" padding-right:5px;"/>${LANG['bizconf.jsp.conf_list_index.res57']}</a>
								</c:if>
								<c:if test="${newConfBase.confStatus==CONF_STATUS_OPENING }">
									<a class="email05" href="javascript:;" onclick="viewConf(${newConfBase.id});">
										<img src="${baseUrlStatic}/images/xiangq.png" width="16" height="16" align="absmiddle" style=" padding-right:5px;" />${LANG['bizconf.jsp.conf_list_index.res50']}</a>
								</c:if>
						 	</c:when>
						 	<c:otherwise>
						 		<c:if test="${(newConfBase.permanentConf eq 1 || newConfBase.confStatus==CONF_STATUS_SUCCESS)}">
						 			<c:if test="${CONF_PERMANENT_ENABLED_MAIN eq newConfBase.permanentConf}">
							 			<a class="email04" href="javascript:;" onclick="delConfForPerConf(${newConfBase.id});">
											<img src="${baseUrlStatic}/images/ico005.png" width="12" height="17" align="absmiddle" style=" padding-right:5px;"/>${LANG['bizconf.jsp.add_contacts.res13']}</a>
							 		</c:if>
							 		<c:if test="${CONF_PERMANENT_ENABLED_MAIN != newConfBase.permanentConf}">
								 		<a class="email04" href="javascript:;" onclick="delConf(${newConfBase.id});">
											<img src="${baseUrlStatic}/images/ico005.png" width="12" height="17" align="absmiddle" style=" padding-right:5px;"/>${LANG['bizconf.jsp.add_contacts.res13']}</a>
							 		</c:if>
						 		</c:if>
						 		<c:if test="${newConfBase.confStatus==CONF_STATUS_SUCCESS}">
							 		<a class="email05" href="javascript:;" onclick="updateBookMeeting(${newConfBase.id})">
									<img src="${baseUrlStatic}/images/ico004.png" width="16" height="16" align="absmiddle" style=" padding-right:5px;"/>${LANG['bizconf.jsp.conf_list_index.res57']}</a>
								</c:if>
								<c:if test="${newConfBase.confStatus==CONF_STATUS_OPENING }">
									<a class="email05" href="javascript:;" onclick="viewConf(${newConfBase.id});">
										<img src="${baseUrlStatic}/images/xiangq.png" width="16" height="16" align="absmiddle" style=" padding-right:5px;" />${LANG['bizconf.jsp.conf_list_index.res50']}</a>
								</c:if>	
						 	</c:otherwise>
						</c:choose>
					</c:if>
				</c:if>
				<c:if test="${newConfBase.confStatus==CONF_STATUS_FINISHED}">
					<c:if test="${user.id eq newConfBase.createUser && (upcomingConf.permanentConf eq 1 || newConfBase.confStatus==CONF_STATUS_SUCCESS)}">
<%-- 	   					<a class="email04" href="#" onclick="delConf(${newConfBase.id});"><img src="${baseUrlStatic}/images/ico005.png" width="12" height="17" align="absmiddle" style=" padding-right:5px;" />${LANG['bizconf.jsp.attended_conf_list.res12']}</a> --%>
	   				</c:if>
					<a class="email05" href="javascript:;" onclick="viewConf(${newConfBase.id});">
						<img src="${baseUrlStatic}/images/xiangq.png" width="16" height="16" align="absmiddle" style=" padding-right:5px;" />${LANG['bizconf.jsp.conf_list_index.res50']}</a>
				</c:if>
				</div>
		</div>
		<c:if test="${!empty newConfBase.confDesc}">
			<div class="extras-desc">
				<div style="width: 60%;line-height: 18px;text-indent: 2em">
				<c:set var="newConfDesc" value="${fn:replace(newConfBase.confDesc,' ','&nbsp;')}" />
				${fn:replace(newConfDesc,vEnter,"<br>")}
				</div>
			</div>
		</c:if>	
</div>
</c:forEach>
