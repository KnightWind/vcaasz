<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<div class="sidebar">
    <ul class="nav-menu"><%--
        <li>
            <a href="javascript:;" class="isParent ico-conf nav-ul-on" >
                <span class="icon-conf-me-img"></span>
                <span class="icon-conf-me-span">${LANG['website.admin.left.menu.user.manage'] }</span>
            </a>
            <ul>
                <li class="active">
                    <a href="/admin/entUser/listAll" class="ico-conf-actor">    
                        <span class="icon-conf-actor-span">${LANG['website.admin.left.menu.user.manage.user'] }</span>
                    </a>
                </li>
<!--                 <c:if test="${isSuperSiteAdmin}"> -->
<!--                 <li> -->
<!--                     <a href="/admin/org/orgListIndex" class="ico-conf-actor">     -->
<!--                         <span class="icon-conf-actor-span">${LANG['website.admin.left.menu.user.manage.org'] }</span> -->
<!--                     </a> -->
<!--                 </li> -->
<!--                 </c:if> -->
            </ul>
        </li>
        --%>
        <c:if test="${isSuperSiteAdmin}">
        <li>
            <a href="javascript:;"  class="isParent ico-settings nav-ul-on" id="contactsHref">
                <span class="icon-settings-img"></span>
                <span class="icon-settings-span">${LANG['website.admin.left.menu.config'] }</span>
            </a>
            <ul>
                <li class="active">
		            <a href="/admin/site/info" class="ico-conf-host">
		                <span class="icon-conf-host-span">${LANG['website.admin.left.menu.site.info'] }</span>
		            </a>
		        </li>
                <li> 
                    <a href="/admin/site/listHosts" class="ico-conf-actor">    
                        <span class="icon-conf-actor-span">${LANG['bizconf.jsp.system.hostlist.res1'] }</span> 
                    </a> 
                </li>
<%--                <li> --%>
<%--                    <a href="/admin/entUser/listAll" class="ico-conf-actor">    --%>
<%--                        <span class="icon-conf-actor-span">主持人列表</span> --%>
<%--                    </a> --%>
<%--                </li>--%>
                <li>
                    <a href="/admin/email/showhost" class="ico-conf-host">
                        <span class="icon-conf-host-span">${LANG['website.admin.left.menu.config.email.host'] }</span>
                    </a>
                </li>
<%--                <li> --%>
<%--                    <a href="/admin/email/goTemplateEdit" class="ico-conf-actor">    --%>
<%--                        <span class="icon-conf-actor-span">${LANG['website.admin.left.menu.config.email.template'] }</span> --%>
<%--                    </a> --%>
<%--                </li>--%>
            </ul>
        </li>
        <li class="active" style="display: none;">
            <a href="/admin/site/info" class="ico-company">
                <span class="icon-company-img"></span>
                <span class="icon-company-span">${LANG['website.admin.left.menu.site.info'] }</span>
            </a>
        </li>
    	<li>
            <a href="/admin/contact/goContacts"  class="ico-contact" id="contactsHref">
                <span class="icon-contact-img"></span>
                <span class="icon-contact-span">${LANG['website.user.leftmenu.contacts'] }</span>
            </a>
        </li>
     <%--
        <li>
            <a href="/admin/notice/list" class="ico-notice">
                <span class="icon-notice-img"></span>
                <span class="icon-notice-span">${LANG['website.admin.left.menu.notice'] }</span>
            </a>
        </li>
         --%>
         </c:if>
        <li>
            <a href="javascript:;" class="isParent ico-report nav-ul-off">
                <span class="icon-report-img"></span>
                <span class="icon-report-span">${LANG['website.admin.left.menu.find.info'] }</span>
            </a>
            <ul style="display:none;">
                <li>
                    <a href="/admin/conf/list" class="ico-conf-host">
                        <span class="icon-conf-host-span">${LANG['website.admin.left.menu.find.info.conf'] }</span>
                    </a>
                </li>
                <li>
                    <a href="/admin/statistical/overview" class="ico-conf-host">
                        <span class="icon-conf-host-span">${LANG['website.site.admin.meetingdata.statis'] }</span>
                    </a>
                </li>
                <%--<li>
                    <a href="/admin/siteAdminLogs/list" class="ico-conf-actor">
                        <span class="icon-conf-actor-span">${LANG['website.admin.left.menu.find.info.mgrlog'] }</span>
                    </a>
                </li>
                --%><li>
                    <a href="/admin/siteUserLogs/userLoginList" class="ico-conf-actor">
                        <span class="icon-conf-actor-span">${LANG['bizconf.jsp.system.user.login.log'] }</span>
                    </a>
                </li>
            </ul>
        </li>
        
        <!-- 账单查询	-->
        <c:if test="${myfn:billingAvailable(siteBase.siteSign)}">
        <li> 
         	<a href="javascript:;" class="isParent ico-report nav-ul-off" style="">
                <span class="icon-blling-img"></span>
                <span class="icon-report-span">${LANG['website.admin.bill.left.menu.name.1'] }</span>
            </a>
            <ul style="display:none;">
             	<li>
	             	<a href="/admin/billing/listTotalBill" class="ico-billing" id="billList">
		               <span class="icon-conf-actor-span">${LANG['website.admin.bill.left.menu.name.2'] }</span>
		            </a>
	             </li>
	             <li>
	             	<a href="/admin/billing/listDetailBills" class="ico-billing" id="billDetail" style="display: none;">
		               <span class="icon-conf-actor-span">${LANG['website.admin.bill.left.menu.name.3'] }</span>
		            </a>
	             </li>
            </ul>
         </li>  
         </c:if>
        <li>
            <a href="/admin/profile/toFavorSetup" class="ico-favor">
                <span class="icon-favor-img"></span>
                <span class="icon-favor-span">${LANG['website.admin.left.menu.prefer.setting'] }</span>
            </a>
        </li>
        <li>
            <a href="javascript:;" class="isParent ico-person nav-ul-off">
                <span class="icon-person-img"></span>
                <span class="icon-person-span">${LANG['website.admin.left.menu.user.setting'] }</span>
            </a>
            <ul style="display:none;">
                <li>
                    <a href="/admin/profile" class="ico-conf-host">
                        <span class="icon-conf-host-span">${LANG['website.admin.left.menu.user.setting.base'] }</span>
                    </a>
                </li>
                <li>
                    <a href="/admin/profile/toChangePwd" class="ico-conf-actor">    
                        <span class="icon-conf-actor-span">${LANG['website.admin.left.menu.user.setting.password'] }</span>
                    </a>
                </li>
            </ul>
        </li>
    </ul>
</div> 
