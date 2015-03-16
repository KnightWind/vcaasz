<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<div class="sidebar">
    <ul class="nav-menu">
    	<c:if test="${!isLogined}">
            <li class="active">
	            <a href="/user/conf/getPublicControlPadIndex"  class="ico-public" >
	                <span class="icon-conf-pub-img"></span>
	                <span class="icon-conf-pub-span">${LANG['website.user.leftmenu.meeting.public'] }</span>
	            </a>
            </li>
        </c:if>
        <c:if test="${isLogined}">

        <li class="active">
         	<cc:base var="USERROLE_HOST"/>
            <cc:base var="USERROLE_PARTICIPANT"/>
            <c:if test="${user.userRole==USERROLE_HOST }">
	             <a href="/user/conf/getControlPadIndex?userRole=1" class="ico-conf-host" >
	                <span class="icon-conf-me-img"></span>
	                <span class="icon-conf-me-span">${LANG['website.user.leftmenu.meeting'] }</span>
	            </a>
            </c:if>
            <!-- 
            	<a href="javascript:;" class="isParent ico-conf nav-ul-on" >
                <span class="icon-conf-me-img"></span>
                <span class="icon-conf-me-span">${LANG['website.user.leftmenu.meeting'] }</span>
            </a>
            <ul>
            	<c:if test="${user.userRole==USERROLE_HOST }">
                <li class="active">
                    <a href="/user/conf/getControlPadIndex?userRole=1" class="ico-conf-host">
                        <span class="icon-conf-host-img"></span>
                        <span class="icon-conf-host-span">${LANG['website.user.leftmenu.meeting.host'] }</span>
                    </a>
                </li>
                </c:if>
                <li <c:if test="${user.userRole==USERROLE_PARTICIPANT }">class="active"</c:if> >
                    <a href="/user/conf/getControlPadIndex?userRole=2" class="ico-conf-actor">    
                        <span class="icon-conf-actor-img"></span>
                        <span class="icon-conf-actor-span">${LANG['website.user.leftmenu.meeting.participation'] }</span>
                    </a>
                </li>
            </ul>
             -->
        </li>
        </c:if>
        <li>
            <a href="/user/contact/goContacts"  class="ico-contact" id="contactsHref">
                <span class="icon-contact-img"></span>
                <span class="icon-contact-span">${LANG['website.user.leftmenu.contacts'] }</span>
            </a>
        </li>
      
        <li>
            <a href="/user/notice/list" class="ico-notice">
                <span class="icon-notice-img"></span>
                <span class="icon-notice-span">${LANG['website.user.leftmenu.notice'] }</span>
            </a>
        </li>
       <c:if test="${isLogined}">
       <!-- 账单 -->
<!--         <li> -->
<!--             <a href="/common/billing/userBillListIndex" class="ico-billing"> -->
<!--                 <span class="icon-blling-img"></span> -->
<!--                 <span class="icon-blling-span">${LANG['website.user.leftmenu.bill'] }</span> -->
<!--             </a> -->
<!--         </li> -->
        <!-- 会议报告 -->
        <li>
            <a class="isParent ico-person nav-ul-off"  href="javascript;">
                <span class="icon-report-img"></span>
                <span class="icon-report-span">${LANG['website.user.leftmenu.report'] }</span>
            </a>
             <ul style="display:none;"> 
	             <c:if test="${user.userRole==USERROLE_HOST }"> 
	                 <li>
	                     <a href="/user/conflog/logsList" class="ico-report-host"> 
	                         <span class="icon-report-host-img"></span> 
	                        <span class="icon-report-host-span">${LANG['bizconf.jsp.conf.bizconf.allmeetingreport'] }</span> 
	                     </a> 
	               	</li> 
	               </c:if> 
	                 <li>
	                     <a href="/user/conflog/monthlyconf" class="ico-report-host"> 
	                         <span class="icon-report-host-img"></span> 
	                        <span class="icon-report-host-span">${LANG['bizconf.jsp.conf.bizconf.monthlymeetingreport'] }</span> 
	                     </a> 
	               	</li>
             </ul> 
        </li>
        <c:if test="${user.userRole==USERROLE_HOST }">
        <li>
            <a href="/user/confConfig/getConfConfig" class="icon-default">
                <span class="icon-default-config-img"></span>
                <span class="icon-default-config-span">${LANG['website.user.leftmenu.config.default'] }</span>
            </a>
        </li>
        </c:if>
        <li>
            <a href="/user/favor/getTimeZone" class="ico-favor">
                <span class="icon-favor-img"></span>
                <span class="icon-favor-span">${LANG['website.user.leftmenu.preferences'] }</span>
            </a>
        </li>
        <li>
            <a href="javascript:;" class="isParent ico-person nav-ul-off">
                <span class="icon-person-img"></span>
                <span class="icon-person-span">${LANG['website.user.leftmenu.setting'] }</span>
                <span class="icon-arrow"></span>
            </a>
            <ul style="display:none;">
                <li>
                    <a href="/user/profile" class="ico-person-base">
                        <span class="icon-person-base-img"></span>
                        <span class="icon-person-base-span">${LANG['website.user.leftmenu.setting.baseinfo'] }</span>
                    </a>
                </li>
                <li>
                    <a href="/user/profile/passPage" class="ico-person-pass">    
                        <span class="icon-person-pass-img"></span>
                        <span class="icon-person-pass-span">${LANG['website.user.leftmenu.setting.password'] }</span>
                    </a>
                </li>
            </ul>
        </li>
        </c:if>
        <li>
            <a href="javascript:;" class="isParent ico-support nav-ul-off">
                <span class="icon-support-img"></span>
                <span class="icon-support-span">${LANG['website.user.leftmenu.support'] }</span>
            </a>
            <ul style="display:none;">
                <li>
                    <a href="/help" target="_blank" class="ignore ico-help">
                        <span class="icon-help-img"></span>
                        <span class="icon-help-span">${LANG['website.user.leftmenu.support.help'] }</span>
                    </a>
                </li>
                <li>
                    <a href="/downCenter/downClient" class="ico-download">    
                        <i class="icon-download-img"></i>
                        <span class="icon-download-span">${LANG['website.user.leftmenu.support.download'] }</span>
                    </a>
                </li>
            </ul>
        </li>
    </ul>
</div> 
