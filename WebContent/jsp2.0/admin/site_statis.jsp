<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8" />
    <title>会畅通讯 - 会议系统</title>
    <!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
    <!--[if lt IE 9]><script src="/Formal/js/lib/html5.js"></script><![endif]-->
    <!--[if lt IE 7]><script src="/Formal/js/lib/belatedpng.js"></script><![endif]-->
    <link type="image/x-icon" rel="shortcut icon" href="/Formal/favicon.ico" />
    <link type="text/css" rel="stylesheet" href="/Formal/css/base.css" />
    <link type="text/css" rel="stylesheet" href="/Formal/css/app/statis.css" />
  </head>
  <body>
    <div class="breadcrumb">
      <span>${LANG['admin.conf.statistics.information.query']}</span>
      <span class="divider">&gt;</span>
      <span>${LANG['admin.conf.statistics.confstatistical']}</span>
    </div>
    <div class="statis">
      <div class="item current">
        <div class="title">
          <i class="icon"></i>
          <span>${LANG['admin.conf.statistics.the.ongoing']}</span>
        </div>
        <div class="details">
         <a href="/admin/statistical/viewRunningMeeting"  target="_blank"> <i class="count text-success">${tadayMeetNum}</i></a>${LANG['website.site.admin.chart.meetingstips.unit']}
        </div>
      </div>
      <div class="item week">
        <div class="title">
          <i class="icon"></i>
          <span>${LANG['admin.conf.statistics.the.nearly.week']}</span>
        </div>
        <div class="details">
          <ul>
            <li class="first-child">
              <div class="desc text-muted"><i class="text-lg">${weekMeetingTime}</i>${LANG['user.menu.conf.time.minute']}</div>
              <span class="text-muted">${LANG['bizconf.jsp.attended_conf_list.res7']}</span>
            </li>
            <li>
              <div class="desc text-primary"><a target="_blank" href="/admin/statistical/viewMeetingNumChar?freq=1">
              <i class="text-lg">${weekMeetNum }</i></a>${LANG['website.user.conf.confScreening']}</div>
              <span class="text-muted">${LANG['com.vcaas.portal.confstatus.finish.4staticis']}</span>
            </li>
            <li class="last-child">
              <div class="desc text-primary"><a target="_blank" href="/admin/statistical/viewParticipantNumChar?freq=1">
               <i class="text-lg">${weekParticiPantNum}</i></a>${LANG['website.user.view.conf.people']}</div>
              <span class="text-muted">${LANG['admin.conf.statistics.already.participate.meeting']}</span>
            </li>
          </ul>
        </div>
      </div>
      <div class="item month">
        <div class="title">
          <i class="icon"></i>
          <span>${LANG['admin.conf.statistics.the.nearly.month']}</span>
        </div>
        <div class="details">
          <ul>
            <li class="first-child">
              <div class="desc text-muted"><i class="text-lg">${monMeetingTime}</i>${LANG['user.menu.conf.time.minute']}</div>
              <span class="text-muted">${LANG['bizconf.jsp.attended_conf_list.res7']}</span>
            </li>
            <li>
              <div class="desc text-primary"><a  target="_blank" href="/admin/statistical/viewMeetingNumChar?freq=2">
              <i class="text-lg">${monMeetNum }</i></a>${LANG['website.user.conf.confScreening']}</div>
              <span class="text-muted">${LANG['com.vcaas.portal.confstatus.finish.4staticis']}</span>
            </li>
            <li class="last-child">
              <div class="desc text-primary"><a target="_blank" href="/admin/statistical/viewParticipantNumChar?freq=2">
              <i class="text-lg">${monParticiPantNum }</i></a>${LANG['website.user.view.conf.people']}</div>
              <span class="text-muted">${LANG['admin.conf.statistics.already.participate.meeting']}</span>
            </li>
          </ul>
        </div>
      </div>
      <div class="item total">
        <div class="title">
          <i class="icon"></i>
          <span>${LANG['conf.type.0']}</span>
        </div>
        <div class="details">
          <ul>
            <li class="first-child">
              <div class="desc text-muted"><i class="text-lg">${totalMeetingTime }</i>${LANG['user.menu.conf.time.minute']}</div>
              <span class="text-muted">${LANG['bizconf.jsp.attended_conf_list.res7']}</span>
            </li>
            <li>
              <div class="desc text-primary"><a target="_blank" href="/admin/statistical/viewAllMeetingNumChar?statType=1">
              <i class="text-lg">${totalMeetingNum }</i></a>${LANG['website.user.conf.confScreening']}</div>
              <span class="text-muted">${LANG['com.vcaas.portal.confstatus.finish.4staticis']}</span>
            </li>
            <li class="last-child">
              <div class="desc text-primary"><a target="_blank" href="/admin/statistical/viewAllParticipantNumChar?statType=1"><i class="text-lg">${totalParticipantNum }</i></a>${LANG['website.user.view.conf.people']}</div>
              <span class="text-muted">${LANG['admin.conf.statistics.already.participate.meeting']} </span>
            </li>
          </ul>
        </div>
      </div>
      <div style="height: 20px;"></div>
    </div>

    <script type="text/javascript" src="/assets/js/lib/jquery.js"></script>
    <script type="text/javascript" src="/assets/js/lib/base.js"></script>
  </body>
</html>