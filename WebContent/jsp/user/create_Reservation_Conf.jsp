<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>schedule meeting</title>
	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/reset.css?ver=${version}"/>
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css?ver=${version}"/>
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery.uniform/themes/default/css/uniform.custom.css?ver=${version}">
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/common.css?ver=${version}"/>
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/popupbox.css?ver=${version}"/>
	<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/user/box.css?ver=${version}"/>	
	<style>
	.cycleEnable, .extraEnable{
		display: none;
	}
	.extra_panel {
		display: none;
	}
	.warning {
	    display: block;
	    margin-left: 1em;
	    width: auto;
	    color: red;
	}
	</style>
	<!-- Javascript -->
	<script type="text/javascript">
		var confID = "${conf.id}";
		var confHwid = "${conf.confHwid}";
		var timeZone = "${conf.timeZone}";
		var cycleID = "${conf.cycleId}";
		var validString  = {
			pageRequired:{
				confName: "${LANG['bizconf.jsp.create_Reservation_Conf.res1']}",
				confNameLength: "${LANG['bizconf.jsp.create_Reservation_Conf.res2000']}",
				hostKey: "${LANG['bizconf.jsp.create_Reservation_Conf.res4']}",
				hostKeyType: "${LANG['bizconf.jsp.create_Reservation_Conf.res5']}",
				confDescLength: "${LANG['bizconf.jsp.create_Reservation_Conf.res6000']}",
				confPass: "${LANG['bizconf.jsp.create_Reservation_Conf.res7']}",
				confPass2: "${LANG['bizconf.jsp.create_Reservation_Conf.res8']}",
				confirmPass: "${LANG['bizconf.jsp.create_Reservation_Conf.res9']}",
				passLength: "${LANG['bizconf.jsp.create_Reservation_Conf.res1000']}",
				startDate: "${LANG['bizconf.jsp.create_Reservation_Conf.res11']}",
				aheadTime: "${LANG['bizconf.jsp.create_Reservation_Conf.res1200']}",
				aheadTimeInt: "${LANG['bizconf.jsp.create_Reservation_Conf.res13']}",
				maxUser: "${LANG['bizconf.jsp.conf_default_setup.res10']}",
				maxUserNumber: "${LANG['bizconf.jsp.create_Reservation_Conf.res17']}",
				dayInterval: "${LANG['bizconf.jsp.create_Reservation_Conf.res18']}",
				maxInterval: "${LANG['bizconf.jsp.create_Reservation_Conf.res1900']}",
				weekInterval: "${LANG['bizconf.jsp.create_Reservation_Conf.res21']}",
				weekFlag: "${LANG['bizconf.jsp.create_Reservation_Conf.res22']}",
				weekStartDate: "${LANG['bizconf.jsp.create_Reservation_Conf.res23']}",
				weekEndDate: "${LANG['bizconf.jsp.create_Reservation_Conf.res24']}",
				cycleError: "${LANG['bizconf.jsp.create_Reservation_Conf.res25']}",
				timeError: "${LANG['bizconf.jsp.create_Reservation_Conf.res26']}",
				hour: "${LANG['bizconf.jsp.conf_list_index.res16']}",
				hours: "${LANG['bizconf.jsp.conf_list_index.res16000']}",
				minute: "${LANG['bizconf.jsp.attended_conf_list.res10']}",
				minutes: "${LANG['bizconf.jsp.attended_conf_list.res10000']}",
				firConfType: "${LANG['bizconf.jsp.create_Reservation_Conf.res27']}",
				secConfType: "${LANG['bizconf.jsp.conf_default_setup.res5']} ",
				thirdConfType: "${LANG['bizconf.jsp.create_Reservation_Conf.res28']} ",
				fourConfType: "${LANG['bizconf.jsp.create_Reservation_Conf.res29']}",
				firCycleType: "${LANG['bizconf.jsp.create_Reservation_Conf.res30']}",
				secCycleType: "${LANG['bizconf.jsp.create_Reservation_Conf.res31']}",
				thirdCycleType: "${LANG['bizconf.jsp.create_Reservation_Conf.res32']}",
				fourCycleType: "${LANG['bizconf.jsp.create_Reservation_Conf.res33']}",
				infiniteType: "${LANG['bizconf.jsp.create_Reservation_Conf.res34']}",
				repeatCountLeft:"${LANG['bizconf.jsp.create_Reservation_Conf.res35']}",
				repeatCountRight:"${LANG['bizconf.jsp.create_Reservation_Conf.res36']}",
				sendInventors: "${LANG['bizconf.jsp.create_Reservation_Conf.inventor']}",
				afterStart: "${LANG['bizconf.jsp.create_Reservation_Conf.afterStart']}",
				addCanlendar: "${LANG['bizconf.jsp.create_Reservation_Conf.calendar']}",
				recreateConf: "${LANG['bizconf.jsp.create_Reservation_Conf.recreate']}",
				updateConf: "${LANG['bizconf.jsp.create_Reservation_Conf.update']}",
				createConf: "${LANG['bizconf.jsp.create_Reservation_Conf.create']}",
				updateConfSuccess: "${LANG['bizconf.jsp.create_Reservation_Conf.update.success']}",
				recreateConfSuccess: "${LANG['bizconf.jsp.create_Reservation_Conf.recreate.success']}"
			},
			maxlength: {
				confName: "${LANG['bizconf.jsp.create_Reservation_Conf.res3700']}",
				confDesc: "${LANG['bizconf.jsp.create_Reservation_Conf.res3900']}",
				duration: "${LANG['bizconf.jsp.create_Reservation_Conf.res40']}"
			},
			digits: {
				masterPass: "${LANG['bizconf.jsp.create_Reservation_Conf.res41']}"			
			}
		};
		function setDefaultTime(defaultDate) {
			var tempDate = new Date();
			tempDate.setTime(defaultDate.getTime()+10*60*1000);
			var tempYear = tempDate.getFullYear();
			var tempMonth = tempDate.getMonth()+1;
			tempMonth = tempMonth<10?"0"+tempMonth:tempMonth;
			var tempDay = tempDate.getDate();
			tempDay = tempDay<10?"0"+tempDay:tempDay;
			var tempHours = tempDate.getHours();
			var tempMins = tempDate.getMinutes();
			if(tempMins>55){
				tempDate.setTime(tempDate.getTime()+60*60*1000);
				tempYear = tempDate.getFullYear();
				tempMonth = tempDate.getMonth()+1;
				tempMonth = tempMonth<10?"0"+tempMonth:tempMonth;
				tempDay = tempDate.getDate();
				tempDay = tempDay<10?"0"+tempDay:tempDay;
				tempHours = tempDate.getHours();
				tempMins = 0;
			}
			var tempTimeStart = tempYear+"-"+tempMonth+"-"+tempDay;
			tempMonth = parseInt(tempMonth, 10)+1;
			tempMonth = tempMonth<10?"0"+tempMonth:tempMonth;
			var tempTimeEnd = tempYear+"-"+ tempMonth +"-"+tempDay;
			//${LANG['bizconf.jsp.create_Reservation_Conf.res42']}
			$("#startTimeM option").each(function() {
 				var cValue = $(this).val();
 				if(tempMins<=cValue) {
 					$(this).attr("selected", true);
 					return false;
 				}
			});
			$("#endTimeM option").each(function() {
 				var cValue = $(this).val();
 				if(tempMins<=cValue) {
 					$(this).attr("selected", true);
 					return false;
 				}
			});
			//${LANG['bizconf.jsp.create_Reservation_Conf.res43']}
			$("#startTimeH option").each(function() {
 				var cValue = $(this).val();
 				if(cValue==tempHours) {
 					$(this).attr("selected", true);
 					return false;
 				}
			});
			$("#endTimeH option").each(function() {
 				var cValue = $(this).val();
 				if(cValue==tempHours) {
 					$(this).attr("selected", true);
 					return false;
 				}
			});
			$("#startDate").val(tempTimeStart);
			$("#startTime").val(tempTimeStart);
			$("#endTime").val(tempTimeEnd);
			$("#endDate").val(tempTimeEnd);
		}
	</script>
	<%@ include file="/jsp/common/cookie_util.jsp"%>
	<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery-1.8.3.min.js?ver=${version}"></script>
	<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery-ui-1.9.2.custom.min.js?ver=${version}"></script>
	<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery-ui-i18n.js?ver=${version}"></script>
	<script type="text/javascript" src="${baseUrlStatic}/js/min/jquery.uniform.min.js?ver=${version}"></script>
	<script type="text/javascript" src="${baseUrlStatic}/js/date.js?ver=${version}"></script>
	<script type="text/javascript" src="${baseUrlStatic}/js/json2.js?ver=${version}"></script>
	<script type="text/javascript" src="${baseUrlStatic}/js/widgets.js?ver=${version}"></script>
	<script type="text/javascript" src="${baseUrlStatic}/js/util.js?ver=${version}"></script>
	<fmt:formatDate var="serverDate" value="${defaultDate}" type="date" pattern="yyyy/MM/dd HH:mm:ss"/>
	<fmt:formatDate var="beijingDate" value="${defaultDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
	<fmt:formatDate var="serverTime" value="${defaultDate}" type="date" pattern="yyyy-MM-dd"/>
	<c:set var="cycleConfType" value="${cycleConfType}"></c:set>
    <cc:confList var="CONF_VIDEO_TYPE_WEBBAND"/>
	<cc:confList var="CONF_VIDEO_TYPE_FLUENCY"/>
    <cc:confList var="CONF_VIDEO_TYPE_DISTINCT"/>
    <cc:confList var="CONF_VIDEO_TYPE_BEST"/>
    <c:set var="CONF_VIDEO_TYPE_FLUENCY" value="${CONF_VIDEO_TYPE_FLUENCY }"></c:set>
    <c:set var="CONF_VIDEO_TYPE_DISTINCT" value="${CONF_VIDEO_TYPE_DISTINCT }"></c:set>
    <c:set var="CONF_VIDEO_TYPE_WEBBAND" value="${CONF_VIDEO_TYPE_WEBBAND }"></c:set>
	<script type="text/javascript">   //该脚本为创建会议时，加载用户默认会议设置信息
		var cycleConfType = "${cycleConfType}";
		var isOutCallFlag = "${isOutCallFlag}";
		var defaultDate = new Date("${serverDate}"); //2013/04/07 11:57:06 
		$(function() {
			var maxLicense = "${defaultLicence}";
			$("#maxLicense").text(maxLicense);
			if(defaultDate){
				//${LANG['bizconf.jsp.create_Reservation_Conf.res45']}
				var weekday = defaultDate.getDay()+1;
				//${LANG['bizconf.jsp.create_Reservation_Conf.res46']}
				$("#weekDay").attr("value",weekday);
				$("input[name=cycleWeekFlag]").each(function(n){
					var typeValue = $(this).val();
					if(typeValue==weekday){
						$(this).attr("checked",'checked');
					} else {
						$(this).removeAttr("checked");
					}
				});
				//${LANG['bizconf.jsp.create_Reservation_Conf.res47']}
				setDefaultTime(defaultDate);
			}
			
			var videoType = "${defaultConfig.videoType}";
			if(videoType=="${CONF_VIDEO_TYPE_WEBBAND}"){
				$("input:radio[name=videoType]:eq(0)").attr("checked",'checked');
			} else if(videoType=="${CONF_VIDEO_TYPE_FLUENCY}"){
				$("input:radio[name=videoType]:eq(1)").attr("checked",'checked');
			} else if(videoType=="${CONF_VIDEO_TYPE_DISTINCT}"){
				$("input:radio[name=videoType]:eq(2)").attr("checked",'checked');
			} else if(videoType=="${CONF_VIDEO_TYPE_BEST}"){
				$("input:radio[name=videoType]:eq(3)").attr("checked",'checked');
			}
			
			//${LANG['bizconf.jsp.create_Reservation_Conf.res48']}
			var isAudioFlag = "${isAudioFlag}"; 
			if(!isAudioFlag){
				$("#audioTR").hide();
			}
			var isVideoFlag = "${isVideoFlag}"; 
			if(!isVideoFlag){
				$("#videoFunc").hide();
				$("#videoFuncM").hide();
				$("#videoTR").hide();
				$("input[name=confType]:eq(0)").removeAttr("checked");
			}
			
			//${LANG['bizconf.jsp.create_Reservation_Conf.res49']}
			var phoneFunc = "${defaultConfig.confType}";    //会议缺省设置中电话功能开启状态：1开启；2关闭
			var allowCall = "${defaultConfig.allowCall}";    //会议缺省设置中外呼开启状态：1开启；2关闭
			var isPhoneFlag = "${isPhoneFlag}";
			if(!isPhoneFlag) {
				$("#phoneFunc").hide();
				$("#phoneFuncM").hide();
				$("input[name=confType]:eq(1)").attr("disabled",'disabled');
				$("input[name=confType]:eq(1)").removeAttr("checked");
			} else {
				$("input[name=confType]:eq(1)").removeAttr("disabled");
				if(phoneFunc == 1){
					$("input[name=confType]:eq(1)").attr("checked",'checked');
					if(isOutCallFlag){
						$("#allowCallTr").attr("style","");
					}
					if(isOutCallFlag && allowCall == 1){
						$("input:radio[name=allowCall]:eq(0)").attr("checked",'checked');
					}
				}
			}
			if(!isVideoFlag && !isPhoneFlag){
				$(".confFuncTR").hide();
				$("#videoTR").hide();
			}
			$("#maxUser").attr("value","${defaultConfig.maxUser}");
	
			var videoNumber = parseInt("${videoNumber}", 10);
			var audioNumber = parseInt("${audioNumber}", 10);
			var defaultMaxVideo = parseInt("${defaultConfig.maxVideo}", 10);
			var defaultMaxAudio = parseInt("${defaultConfig.maxAudio}", 10);
			if(defaultMaxVideo>videoNumber){
				defaultMaxVideo = videoNumber;
			}
			if(defaultMaxAudio>audioNumber){
				defaultMaxAudio = audioNumber;
			}
			$("#maxVideo").attr("value",defaultMaxVideo);
			$("#maxAudio").attr("value",defaultMaxAudio);
			$("#aheadTime").attr("value","${defaultConfig.aheadTimes}");
			
			//修复bug808
			var confModel = "${confModel}";
			if(confModel==1){
				$("input:radio[name=confModel]:eq(0)").attr("checked",'checked');
			} else {
				$("input:radio[name=confModel]:eq(1)").attr("checked",'checked');
			}
			var micStatus = "${micStatus}";
			if(micStatus==1){
				$("input:radio[name=micStatus]:eq(0)").attr("checked",'checked');
			} else {
				$("input:radio[name=micStatus]:eq(1)").attr("checked",'checked');
			}
			
			
			
			//permission
			if("${changePage}"==1){
				$("input:checkbox[name=changePage]:eq(0)").attr("checked",'checked');
			}
			if("${annotate}"==1){
				$("input:checkbox[name=annotate]:eq(0)").attr("checked",'checked');
			}
			if("${chatAnyOne}"==1){
				$("input:checkbox[name=chatAnyOne]:eq(0)").attr("checked",'checked');
			}
			if("${chatCompere}"==1){
				$("input:checkbox[name=chatCompere]:eq(0)").attr("checked",'checked');
			}
			if("${chatParticipants}"==1){
				$("input:checkbox[name=chatParticipants]:eq(0)").attr("checked",'checked');
			}
			
			//${LANG['bizconf.jsp.conf_default_setup.res9']}
			if("${shareDocs}"==1){
				$("input:checkbox[name=shareDocs]:eq(0)").attr("checked",'checked');
			}
			if("${shareScreen}"==1){
				$("input:checkbox[name=shareScreen]:eq(0)").attr("checked",'checked');
			}
			if("${shareMedia}"==1){
				$("input:checkbox[name=shareMedia]:eq(0)").attr("checked",'checked');
			}
			var isShareMediaFlag = "${isShareMediaFlag}";
			if(!isShareMediaFlag) {
				$("#shareMedia").hide();
				$("#shareMediaM").hide();
				$("input:checkbox[name=shareMedia]:eq(0)").attr("disabled",'disabled');
				$("input:checkbox[name=shareMedia]:eq(0)").removeAttr("checked");
			}
			
			if("${whiteBoard}"==1){
				$("input:checkbox[name=whiteBoard]:eq(0)").attr("checked",'checked');
			}
			if("${fileTrans}"==1){
				$("input:checkbox[name=fileTrans]:eq(0)").attr("checked",'checked');
			}
			if("${record}"==1){
				$("input:checkbox[name=record]:eq(0)").attr("checked",'checked');
			}
			var isRecordFlag = "${isRecordFlag}";
			if(!isRecordFlag) {
				$("#record").hide();
				$("#recordM").hide();
				$("input:checkbox[name=record]:eq(0)").attr("disabled",'disabled');
				$("input:checkbox[name=record]:eq(0)").removeAttr("checked");
			}
		});
	</script>
<!-- 若会议信息不为空，则为修改会议，加载会议信息 -->
	<c:if test="${conf != null}">    
	<fmt:formatDate var="confStartTime" value="${conf.startTime}" type="date" pattern="yyyy/MM/dd HH:mm:ss"/>
	<fmt:formatDate var="confEndTime" value="${conf.endTime}" type="date" pattern="yyyy/MM/dd HH:mm:ss"/>
	<fmt:formatDate var="confStartDate" value="${conf.startTime}" type="date" pattern="yyyy-MM-dd"/>
	<fmt:formatDate var="confEndDate" value="${conf.endTime}" type="date" pattern="yyyy-MM-dd"/>
	<c:set var="timeZoneDesc" value="website.timezone.city.${conf.timeZoneId}"/>
	<script type="text/javascript">
		$(function() {
			$("#confName").val("${conf.confName}");
			//var confDesc = $("#confDescValue").html();
			//$("#confDesc").val(confDesc);
			var isPublic = "${conf.publicFlag}";
			if(isPublic=="1"){
				$("input:radio[name=allowPublic]:eq(0)").attr("checked",'checked');
				$(".publicPassSet").show();
				var publicPass = "${conf.publicConfPass}";
				if(publicPass){
					$("input:radio[name=passSetRadio]:eq(0)").attr("checked",'checked');
					$("#confPass").val(publicPass);
					$("#confirmPass").val(publicPass);
					$("#confPassTR").show();
					$("#confirmPassTR").show();
				}
				$(".confInviterTR").hide();
			} else if(isPublic=="3"){
				$("input:radio[name=confInviterRadio]:eq(0)").attr("checked",'checked');
			}
			var hostKey = "${conf.hostKey}";
			if(hostKey){
				$("#hostKey").val(hostKey);
			}
			var startTime = new Date("${confStartTime}");
			if(startTime && cycleConfType != "2"){
				$("#startDate").val("${confStartDate}");
				var hour = startTime.getHours();
				$("#startTimeH").attr("value",hour);
				var mis = startTime.getMinutes(); 
				$("#startTimeM").attr("value",mis);
			}
			var endTime = "${confEndTime}";
			if(endTime){
				endTime = new Date(endTime);
				$("#endDate").val("${confEndDate}");
				var ehour = endTime.getHours();
				$("#endTimeH").attr("value",ehour);
				var emis = endTime.getMinutes(); 
				$("#endTimeM").attr("value",emis);
			}
			//${LANG['bizconf.jsp.create_Reservation_Conf.res51']}
			$(".endTypeTR").hide();
			if(cycleConfType == "1"){        //${LANG['bizconf.jsp.create_Reservation_Conf.res52']}cycleConfType${LANG['bizconf.jsp.create_Reservation_Conf.res53']}1
				$(".startDateDiv").hide();	 //${LANG['bizconf.jsp.create_Reservation_Conf.res54']}!
				$("input[name=cycleOption]:eq(0)").attr("checked",'checked');
				$(".cycleTypeTR").show();
				$(".cycleType").attr("disabled",'disabled');
				var cycleType = "${cycleType}";//"${cycleType}";
				if(cycleType=="1"){
					$("input[name=cycleType]:eq(0)").attr("checked",'checked');
					$("#cycleDayFlag").attr("disabled",'disabled');
					var cycleDayValue = "${cycleDayValue}";
					$("#cycleDayFlag").val(cycleDayValue);//"${cycleDayValue}"
					$(".cycleDay").show();
				} else if (cycleType=="2") {
					$("input[name=cycleType]:eq(1)").attr("checked",'checked');
					$("input[name=cycleWeekFlag]").attr("disabled",'disabled');
					var cycleWeekValue = "${cycleWeekValue}";
					cycleWeekValue = cycleWeekValue.split(",");
					$("input[name=cycleWeekFlag]").removeAttr("checked");
					for ( var i = 0; i < cycleWeekValue.length; i++) {
						var indexWeek = cycleWeekValue[i]-1;
						$("input[name=cycleWeekFlag]:eq("+indexWeek+")").attr("checked",'checked');	
					}
// 					$("input[name=cycleWeekFlag]").each(function(n){
// 						var typeValue = $(this).val();//"${cycleWeekValue}"
// 						if(typeValue==1){
// 							$(this).attr("checked",'checked');
// 						} else {
// 							$(this).removeAttr("checked");
// 						}
// 					});
					$(".cycleWeek").show();
				} else if (cycleType=="3"){
					$("input[name=cycleMonthRadio]").attr("disabled",'disabled');
					$("#eachMonthDay").attr("disabled",'disabled');
					$("#weekFlag").attr("disabled",'disabled');
					$("#weekDay").attr("disabled",'disabled');
					var monthCycleOption = "${monthCycleOption}";//"${monthCycleOption}"
					if(monthCycleOption=="1"){
						$("input[name=cycleMonthRadio]:eq(0)").attr("checked",'checked');
						$("#eachMonthDay").val("${eachMonthDay}");//"${eachMonthDay}"
					} else if(monthCycleOption=="2"){
						$("input[name=cycleMonthRadio]:eq(1)").attr("checked",'checked');
						$("#weekFlag").attr("value","${weekFlag}");//"${weekFlag}"
						$("#weekDay").attr("value","${weekDay}");//"${weekDay}"
					}
					$("input[name=cycleType]:eq(2)").attr("checked",'checked');
					$(".cycleMonth").show();
				}
			}
			var durationValue = "${conf.duration}";
			if(durationValue){
				var duration = parseInt("${conf.duration}", 10);
				var duraHour = duration/60>>0;
				var duraMis = duration%60;
				$("#meetingTimeH").attr("value",duraHour);
				$("#meetingTimeM").attr("value",duraMis);
				if(duraMis%5!=0){
					$("#meetingTimeM").attr("value","10");
				}

				if(durationValue>1440){
					$(".endDateTr").show();
					$(".durationTR").hide();	
				} else {
					$(".endDateTr").hide();
					$(".durationTR").show();
				}
			}
			var confType = "${conf.confType}";
			if(confType) {
				if(confType == 0){
					$("input[name=confType]:eq(0)").removeAttr("checked");
					$("#videoTR").hide();
				}
				if(confType==2 || confType==3){
					$("#videoTR").attr("style","");
					$("input[name=confType]:eq(0)").attr("checked",'checked');
				}
				
				if(confType==1 || confType==3){
					var isPhoneFlag = "${isPhoneFlag}";
					if(!isPhoneFlag) {
						$("#phoneFunc").hide();
						$("#phoneFuncM").hide();
						$("input[name=confType]:eq(1)").attr("disabled",'disabled');
						$("input[name=confType]:eq(1)").attr("checked",'');	
					} else {
						$("input[name=confType]:eq(1)").attr("checked",'checked');
					}
				}
			}
			//${LANG['bizconf.jsp.create_Reservation_Conf.res48']}
			var isVideoFlag = "${isVideoFlag}"; 
			if(!isVideoFlag){
				$("#videoFunc").hide();
				$("#videoFuncM").hide();
				$("#videoTR").hide();
				$("input[name=confType]:eq(0)").removeAttr("checked");
			}
			
			if(!isOutCallFlag){
				$("#allowCallTr").attr("style","display:none");
			} else {
				if(confType==1 || confType==3){
					$("#allowCallTr").attr("style","");
				}
			} 
			$("#maxUser").val("${conf.maxUser}");
			//${LANG['bizconf.jsp.create_Reservation_Conf.res55']}
			var confModel = "${confModel}";
			if(confModel==1){
				$("input:radio[name=confModel]:eq(0)").attr("checked",'checked');
			} else {
				$("input:radio[name=confModel]:eq(1)").attr("checked",'checked');
			}
			var micStatus = "${micStatus}";
			if(micStatus==1){
				$("input:radio[name=micStatus]:eq(0)").attr("checked",'checked');
			} else {
				$("input:radio[name=micStatus]:eq(1)").attr("checked",'checked');
			}
			var allowCall = "${conf.allowCall}";
			if(allowCall==1){
				$("input:radio[name=allowCall]:eq(0)").attr("checked",'checked');
			} else {
				$("input:radio[name=allowCall]:eq(1)").attr("checked",'checked');
			}
			//config
			
			var videoNumber = parseInt("${videoNumber}", 10);
			var audioNumber = parseInt("${audioNumber}", 10);
			var confMaxVideo = parseInt("${conf.maxVideo}", 10);
			var confMaxAudio = parseInt("${conf.maxAudio}", 10);
			if(confMaxVideo>videoNumber){
				confMaxVideo = videoNumber;
			}
			if(confMaxAudio>audioNumber){
				confMaxAudio = audioNumber;
			}
			$("#maxAudio").attr("value",confMaxAudio);
			$("#maxVideo").attr("value",confMaxVideo);
			var videoType = "${conf.videoType}";
			if(videoType=="${CONF_VIDEO_TYPE_WEBBAND}"){
				$("input:radio[name=videoType]:eq(0)").attr("checked",'checked');
			} else if(videoType=="${CONF_VIDEO_TYPE_FLUENCY}"){
				$("input:radio[name=videoType]:eq(1)").attr("checked",'checked');
			} else if(videoType=="${CONF_VIDEO_TYPE_DISTINCT}"){
				$("input:radio[name=videoType]:eq(2)").attr("checked",'checked');
			} else if(videoType=="${CONF_VIDEO_TYPE_BEST}"){
				$("input:radio[name=videoType]:eq(3)").attr("checked",'checked');
			}
// 			if(videoType==1){
// 				$("input:radio[name=videoType]:eq(0)").attr("checked",'checked');
// 			} else if(videoType==2){
// 				$("input:radio[name=videoType]:eq(1)").attr("checked",'checked');
// 			} else {
// 				$("input:radio[name=videoType]:eq(2)").attr("checked",'checked');
// 			}
			$("#aheadTime").val("${conf.aheadTime}");
			//permission
			if("${changePage}"==1){
				$("input:checkbox[name=changePage]:eq(0)").attr("checked",'checked');
			}
			if("${annotate}"==1){
				$("input:checkbox[name=annotate]:eq(0)").attr("checked",'checked');
			}
			if("${chatAnyOne}"==1){
				$("input:checkbox[name=chatAnyOne]:eq(0)").attr("checked",'checked');
			}
			if("${chatCompere}"==1){
				$("input:checkbox[name=chatCompere]:eq(0)").attr("checked",'checked');
			}
			if("${chatParticipants}"==1){
				$("input:checkbox[name=chatParticipants]:eq(0)").attr("checked",'checked');
			}
			
			//${LANG['bizconf.jsp.conf_default_setup.res9']}
			if("${shareDocs}"==1){
				$("input:checkbox[name=shareDocs]:eq(0)").attr("checked",'checked');
			}
			if("${shareScreen}"==1){
				$("input:checkbox[name=shareScreen]:eq(0)").attr("checked",'checked');
			}
			if("${shareMedia}"==1){
				$("input:checkbox[name=shareMedia]:eq(0)").attr("checked",'checked');
			}
			var isShareMediaFlag = "${isShareMediaFlag}";
			if(!isShareMediaFlag) {
				$("#shareMedia").hide();
				$("#shareMediaM").hide();
				$("input:checkbox[name=shareMedia]:eq(0)").attr("disabled",'disabled');
				$("input:checkbox[name=shareMedia]:eq(0)").removeAttr("checked");
			}
			if("${whiteBoard}"==1){
				$("input:checkbox[name=whiteBoard]:eq(0)").attr("checked",'checked');
			}
			if("${fileTrans}"==1){
				$("input:checkbox[name=fileTrans]:eq(0)").attr("checked",'checked');
			}
			if("${record}"==1){
				$("input:checkbox[name=record]:eq(0)").attr("checked",'checked');
			}
			var isRecordFlag = "${isRecordFlag}";
			if(!isRecordFlag) {
				$("#record").hide();
				$("#recordM").hide();
				$("input:checkbox[name=record]:eq(0)").attr("disabled",'disabled');
				$("input:checkbox[name=record]:eq(0)").removeAttr("checked");
			}	
		});
	</script>	
</c:if>
<script type="text/javascript" src="${baseUrlStatic}/js/valid.js?ver=${version}"></script>

</head>
<body onload="loaded()">
<form id="meetingForm" action="">
<div id="step1" class="validation_group" style="">
<table class="overlay-panel" border="0" cellpadding="0" cellspacing="0">
    <tbody>
      <tr class="no-header">
        <td class="overlay-hdL"></td>
        <td class="overlay-hdC"></td>
        <td class="overlay-hdR"></td>
      </tr>
      <tr>
        <td class="overlay-bdL"></td>
        <td class="overlay-content">
        <!--${LANG['bizconf.jsp.add_contacts.res2']}========================================================================-->
			<div class="First_Steps">
			    <div class="First_Steps_title"> 
			    	<a href="javascript:;" class="closeButton"></a> 
			    	<img class="nar" src="/static/images/nar.jpg" width="261" height="18"/>
			    	<c:if test="${!empty conf }">
			      		<h3 class="tit">${LANG['user.menu.conf.update']}</h3>
			    	</c:if>
			    	<c:if test="${empty conf }">
			      		<h3 class="tit">${LANG['user.menu.conf.schedule']}</h3>
			    	</c:if>
			      <ul class="nar_text">
			        <li style=" margin-left:">${LANG['system.menu.info.meeting']}</li>
			        <li style=" margin-left:30px;">${LANG['user.menu.conf.time.info']}</li>
			        <li style=" margin-left:35px;">${LANG['user.menu.conf.parameter.info']}</li>
			        <li style=" margin-left:50px;">${LANG['system.complete']}</li>
			      </ul>
			    </div>
			    <div class="toa"></div>
			    <div class="First_Steps_top">
			      <h3>${LANG['bizconf.jsp.create_Reservation_Conf.res56']}</h3>
			      <p>${LANG['bizconf.jsp.create_Reservation_Conf.res57']}</p>
			    </div>
			    <div class="First_Steps_main" style="min-height: 298px;_height:298px;">
			      <table class="box_a">
			        <tr class="box01">
			          <td align="right" class="left_text"><span class="red_star">*</span>${LANG['bizconf.jsp.attendConfloglist.res3']}</td>
			          <td align="left" class="td_space"><input class="right_text skipThese"  id="confName" name="confName" type="text" value=""/></td>
			        </tr>
			        <tr class="box01">
						<td align="right" class="left_text"><span class="red_star">*</span>${LANG['bizconf.jsp.create_Reservation_Conf.res58']}</td>
						<td align="left"  class="td_space">
							<input id="hostKey" class="right_text skipThese" name="hostKey" type="text" value=""/>
						</td>
					</tr>
					<tr class="box01">
						<td align="right" class="left_text">${LANG['bizconf.jsp.create_Reservation_Conf.res59']}</td>
						<td align="left"  class="td_space">
							<input id="allowPublicYes" class="right_radio01 allowPublic" name="allowPublic" type="radio" value="1" /> 
							${LANG['bizconf.jsp.conf_default_setup.res29']}
							<input id="allowPublicNo" class="right_radio01 allowPublic" name="allowPublic" type="radio" value="2" checked="checked"/>
							${LANG['bizconf.jsp.conf_default_setup.res30']}
						</td>
					</tr>
					<tr class="box01 confInviterTR">
						<td align="right" class="left_text">${LANG['bizconf.jsp.conf.schedule.attendee']}</td>
						<td align="left"  class="td_space">
							<input class="right_radio01 " name="confInviterRadio" type="radio" value="0" /> 
							${LANG['bizconf.jsp.conf.schedule.registers']}
							<input class="right_radio01 " name="confInviterRadio" type="radio" value="1" checked="checked"/>
							${LANG['bizconf.jsp.conf.schedule.invitors']}  <span style="color:#EB6D00">${LANG['bizconf.jsp.conf.schedule.notice']}</span>
						</td>
					</tr>
					<tr class="box01 publicPassSet" style="display: none;">
						<td align="right" class="left_text">${LANG['bizconf.jsp.create_Reservation_Conf.res60']}</td>
						<td align="left"  class="td_space">
							<input class="right_radio01 passSetRadio" name="passSetRadio" type="radio" value="1" /> ${LANG['bizconf.jsp.conf_default_setup.res29']} 
							<input class="right_radio01 passSetRadio" name="passSetRadio" type="radio" value="2" checked="checked"/> ${LANG['bizconf.jsp.conf_default_setup.res30']}
						</td>
					</tr>
					<tr class="box01" style="display: none;" id="confPassTR">
			          <td  align="right" class="left_text"><span class="red_star">*</span>${LANG['bizconf.jsp.create_Reservation_Conf.res61']}</td>
			          <td align="left" class="td_space"><input class="right_text skipThese" id="confPass" name="confPass" type="password"  value=""/></td>
			        </tr>
					<tr class="box01" style="display: none;" id="confirmPassTR">
			          <td  align="right" class="left_text"><span class="red_star">*</span>${LANG['bizconf.jsp.create_Reservation_Conf.res62']}</td>
			          <td align="left" class="td_space"><input class="right_text skipThese" id="confirmPass" name="confirmPass" type="password"  value=""/></td>
			        </tr>        
			        <tr class="box02">
			          <td  align="right" valign="top" style="padding-top:5px">${LANG['bizconf.jsp.create_Reservation_Conf.res63']}</td>
			          <td align="left" class="td_space" style="padding-top:5px">
			          	<textarea class="textarea_step1 skipThese"  id="confDesc" name="confDesc" cols="" rows="" style="text-indent: 5px;">${conf.confDesc}</textarea>
			          	<div id="confDescValue" style="display: none;">${conf.confDesc}</div>
			          </td>
			        </tr>
			      </table>
			    </div>
			    <div class="First_Steps_bottom">
			      <div class="but01"><a href="javascript:;" class="closeButton"><img src="/static/images/quxiao.png" width="11" height="10" align="absmiddle" style=" margin-right:8px; margin-left:10px"/>${LANG['bizconf.jsp.create_Reservation_Conf.res6400']}</a></div>
			      <div class="but02"><a href="javascript:;" class="next" pageIndex="1"><img src="/static/images/next_bg.png" width="9" height="11" align="absmiddle" style=" margin-right:5px; margin-left:10px;" />${LANG['bizconf.jsp.create_Reservation_Conf.res66']}</a></div>
			    </div>
			</div>
        <!--${LANG['bizconf.jsp.add_contacts.res2']}========================================================================-->
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
</div>
<div id="step2" class="validation_group"  style="display: none;">
<table class="overlay-panel" border="0" cellpadding="0" cellspacing="0" >
    <tbody>
      <tr class="no-header">
        <td class="overlay-hdL"></td>
        <td class="overlay-hdC"></td>
        <td class="overlay-hdR"></td>
      </tr>
      <tr>
        <td class="overlay-bdL"></td>
        <td class="overlay-content">
        <!--${LANG['bizconf.jsp.add_contacts.res2']}========================================================================-->
			<div class="First_Steps">
			   	  <div class="First_Steps_title"> <a href="javascript:;" class="closeButton"></a>
			       <img class="nar" src="/static/images/nar02.jpg" width="261" height="18" />
			    	<c:if test="${!empty conf }">
			      		<h3 class="tit">${LANG['bizconf.jsp.create_Reservation_Conf.res67']}</h3>
			    	</c:if>
			    	<c:if test="${empty conf }">
			      		<h3 class="tit">${LANG['bizconf.jsp.create_Reservation_Conf.res68']}</h3>
			    	</c:if>
			        <ul class="nar_text">
			        	<li style=" margin-left:">${LANG['bizconf.jsp.create_Reservation_Conf.res69']}</li>
			            <li style=" margin-left:30px;">${LANG['bizconf.jsp.create_Reservation_Conf.res70']}</li>
			            <li style=" margin-left:35px;">${LANG['bizconf.jsp.create_Reservation_Conf.res71']}</li>
			            <li style=" margin-left:50px;">${LANG['bizconf.jsp.add_contacts.res14']}</li>
			        </ul>  
			        </div>
			        <div class="toa"></div>
			        <div class="First_Steps_top">
			        	<h3>${LANG['bizconf.jsp.create_Reservation_Conf.res72']}</h3>
			            <p>${LANG['bizconf.jsp.create_Reservation_Conf.res73']}</p>
			          
			        </div>
			        <div class="First_Steps_main" style="min-height: 298px;_height:298px;">
			          <table class="box_a" style="">
			          		<tr class="box01" style="display: none;">
			                	<td align="right" class="left_text">${LANG['bizconf.jsp.conf_list_pad.res4']}</td>
			                    <td align="left" class="td_space">
			                    	<input class="permanentOption" name="permanentConf" type="radio" value="1" /><span>${LANG['bizconf.jsp.conf_default_setup.res29']}</span>
			                    	<input class="permanentOption"  name="permanentConf" type="radio" value="0" checked="checked"/><span>${LANG['bizconf.jsp.conf_default_setup.res30']}</span>
			                    </td>
			                </tr>
			            	<tr class="box01 cycleOptionTR">
			                	<td align="right" class="left_text">${LANG['bizconf.jsp.create_Reservation_Conf.res74']}</td>
			                    <td align="left" class="td_space">
			                    	<input class="cycleOption" name="cycleOption" type="radio" value="1" <c:if test="${!empty conf}">disabled</c:if>/><span>${LANG['bizconf.jsp.conf_default_setup.res29']}</span>
			                    	<input class="cycleOption"  name="cycleOption" type="radio" value="2" checked="checked" <c:if test="${!empty conf}">disabled</c:if>/><span>${LANG['bizconf.jsp.conf_default_setup.res30']}</span>
			                    </td>
			                </tr>
							<cc:confList var="WEEK_DAYS"/>
							<cc:confList var="CONF_CYCLE_DAILY"/>
							<cc:confList var="CONF_CYCLE_WEEKLY"/>
							<cc:confList var="CONF_CYCLE_MONTHLY"/>                
			                <tr class="box01 cycleEnable cycleTypeTR">
			                	<td align="right" class="left_text">${LANG['bizconf.jsp.create_Reservation_Conf.res75']}</td>
			                    <td align="left" class="td_space">
				                    <input class="cycleType" name="cycleType" type="radio" value="${CONF_CYCLE_DAILY}" <c:if test="${confCycle.cycleType== CONF_CYCLE_DAILY}"> checked </c:if> /><span>${LANG['bizconf.jsp.create_Reservation_Conf.res76']}</span>
				                    <input class="cycleType"  name="cycleType" type="radio" value="${CONF_CYCLE_WEEKLY}" <c:if test="${empty confCycle || confCycle.cycleType== CONF_CYCLE_WEEKLY}"> checked </c:if> /><span>${LANG['bizconf.jsp.create_Reservation_Conf.res77']}</span>
				                    <input class="cycleType"  name="cycleType" type="radio" value="${CONF_CYCLE_MONTHLY}" <c:if test="${confCycle.cycleType== CONF_CYCLE_MONTHLY}"> checked </c:if> /><span>${LANG['bizconf.jsp.create_Reservation_Conf.res78']}</span>
			                    </td>
			                </tr>
			                <!--${LANG['bizconf.jsp.create_Reservation_Conf.res79']}-->
			                <tr class="box01 cycleDay" style=" margin-top:14px; display:none" >
			                	<td  align="right" class="left_text">${LANG['bizconf.jsp.create_Reservation_Conf.res80']}</td>
			                    <td align="left" class="td_space">
			                    	${LANG['bizconf.jsp.create_Reservation_Conf.res81']}<input class="right_text06 skipThese" id="cycleDayFlag" name="cycleDayFlag" type="text" value="1"/>${LANG['bizconf.jsp.conf_list_index.days']}
			                    </td>
			                </tr>
			                <!--${LANG['bizconf.jsp.create_Reservation_Conf.res82']}-->
			                
			                <!--${LANG['bizconf.jsp.create_Reservation_Conf.res83']}-->
			                <tr class="box01 cycleEnable cycleWeek" style=" margin-top:14px; display:none" >
			                	<td  align="right" class="left_text">${LANG['bizconf.jsp.create_Reservation_Conf.res84']}</td>
			                    <td align="left" class="wert td_space">
				                    <c:forEach var="eachDay" items="${WEEK_DAYS}">
										<c:set var="week_day_lang" value="conf.cycle.weekly.${eachDay}"/>
										<input name="cycleWeekFlag" type="checkbox" value="${eachDay}"/>
										<label class="radio-label">${LANG[week_day_lang]}</label>
						 			</c:forEach>
			                    </td>
			                </tr>
			                 <!--${LANG['bizconf.jsp.create_Reservation_Conf.res85']}-->
			                 
			                <!--${LANG['bizconf.jsp.create_Reservation_Conf.res86']}-->
			                <tr class="cycleMonth" style=" margin-top:14px;display: none;">
			                	<td  align="right" valign="top" class="left_text">${LANG['bizconf.jsp.create_Reservation_Conf.res87']}</td>
			                    <td align="left" class="td_space">
			                    	<p style="margin-bottom: 5px;" class="monthCycleDayTR">
			                    		<input name="cycleMonthRadio" type="radio" value="1"/>
										${LANG['bizconf.jsp.create_Reservation_Conf.res88']}<input class="right_text03 skipThese" type="text" id="eachMonthDay" name="eachMonthDay" value="1"/>${LANG['bizconf.jsp.conf_list_index.res17']}
			                    	</p>
			                    	<p class="monthCycleWeekTR">
										<input name="cycleMonthRadio" type="radio" value="2"  checked="checked"/>
										${LANG['bizconf.jsp.create_Reservation_Conf.res88']}
										<select class="right_text03" id="weekFlag" name="weekFlag">
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option value="5">5</option>
										</select>${LANG['bizconf.jsp.create_Reservation_Conf.res89']}
										<select name="weekDay" id="weekDay">
											<c:forEach var="eachDay" items="${WEEK_DAYS}">
												<c:set var="week_day_lang" value="conf.cycle.weekly.${eachDay}"/>
												<option value="${eachDay}">${LANG[week_day_lang]}</option>
											</c:forEach>
										</select>
										${LANG['bizconf.jsp.conf_list_index.res17']}                    	
			                    	</p>
			                    </td>
			                </tr>
			                
<!--         			        <tr class="box01 cycleEnable">-->
<!--			                	<td align="right" class="left_text">${LANG['bizconf.jsp.create_Reservation_Conf.res90']}</td>-->
<!--			                    <td align="left" class="td_space">-->
<!--			                    	<input class="infiniteFlag" name="infiniteFlag" type="radio" value="1" <c:if test="${!empty conf}">disabled</c:if>/><span>${LANG['bizconf.jsp.conf_default_setup.res29']}</span>-->
<!--			                    	<input class="infiniteFlag"  name="infiniteFlag" type="radio" value="0" checked="checked" <c:if test="${!empty conf}">disabled</c:if>/><span>${LANG['bizconf.jsp.conf_default_setup.res30']}</span>-->
<!--			                    </td>-->
<!--			                </tr>-->
			                
			                <!--${LANG['bizconf.jsp.create_Reservation_Conf.res91']}-->
			                 <tr class="box01 cycleEnable cycleTimeTr" style=" margin-top:14px;" >
			                	<td align="right" class="" valign="top">
			                		<span style="position: relative;top: 7px;">
			                			${LANG['bizconf.jsp.create_Reservation_Conf.res92']}
			                		</span>
			                	</td>
			                    <td align="left" class="td_space">
			                    	<table style="" >
			                    		<tr>
			                    			<td style="width: 30px;" align="center">
			                    				${LANG['bizconf.jsp.create_Reservation_Conf.res93']}
			                    			</td>
			                    			<td style="width: 120px">
			                    				<input class="right_text05 skipThese" id="startTime" name="startTime" type="text" readonly="readonly" value=""  style="width:100px;"/>
			                    			</td>
			                    			<td align="left">
			                    				<input class="infiniteFlag" name="infiniteFlag" type="radio" value="1" <c:if test="${!empty conf}">disabled</c:if>/>${LANG['bizconf.jsp.create_Reservation_Conf.res94']}
			                    			</td>
			                    			<td></td>
			                    		</tr>
			                    		<tr>
			                    			<td></td>
			                    			<td></td>
			                    			<td align="left">
			                    				<input class="infiniteFlag" name="infiniteFlag" type="radio" value="2" checked="checked"/>${LANG['bizconf.jsp.create_Reservation_Conf.res35']} 
			                    			</td>
			                    			<td style="position: relative;">
			                    				<input class="right_text05 skipThese" id="repeatCount" type="text" value="30" style="width:50px;"/> ${LANG['bizconf.jsp.create_Reservation_Conf.res36']}
			                    			</td>
			                    		</tr>
			                    		<tr>
			                    			<td></td>
			                    			<td></td>
			                    			<td align="left" style="">
			                    				<input class="infiniteFlag" name="infiniteFlag" type="radio" value="3"/>${LANG['bizconf.jsp.create_Reservation_Conf.res95']}
			                    			</td>
			                    			<td>
			                    				<input class="right_text07 skipThese" id="endTime" name="endTime" type="text"  readonly="readonly" value=""  style="width:100px;margin-left: 0px"/>
			                    			</td>
			                    		</tr>
			                    	</table>
			                    </td>
			                </tr>       
<!--			                <tr class="box01 cycleEnable cycleTimeTr" style=" margin-top:14px;" >-->
<!--			                	<td align="right" class="left_text">${LANG['bizconf.jsp.create_Reservation_Conf.res92']}</td>-->
<!--			                    <td align="left" class="td_space">-->
<!--			                    	<div style="position: relative;float: left;">-->
<!--			                    		<input class="right_text05 skipThese" id="startTime" name="startTime" type="text" readonly="readonly" value=""/>-->
<!--			                    	</div>-->
<!--			                    	<span style="margin-left: 10px;float: left;margin-top: 5px;">${LANG['bizconf.jsp.conf_list.res6']}</span>-->
<!--			                    	<div style="position: relative;float: left;">-->
<!--			                    		<input class="right_text07 skipThese" id="endTime" name="endTime" type="text"  readonly="readonly" value="" />-->
<!--			                    	</div>-->
<!--			                    	<div style="clear:both"></div> -->
<!--			                    </td>-->
<!--			                </tr>            -->
			                <tr class="startDateTr">
			                <td align="right" class="left_text"></td>
			                <td class="td_space">
			                <span style="color:#EB6D00"  class="li01" title="${beijingDate}">${LANG['bizconf.jsp.create_Reservation_Conf.res96']}:
			                <c:if test="${empty timeZoneDesc }">
			                	<c:set var="timeZone" value="website.timezone.city.${currentUser.timeZoneId}"/>
			                	${LANG[timeZone]}${LANG['bizconf.jsp.create_Reservation_Conf.res97']}&nbsp;
			                </c:if> 
			                <c:if test="${!empty timeZoneDesc }">
			                	${LANG[timeZoneDesc]}${LANG['bizconf.jsp.create_Reservation_Conf.res97']}&nbsp;
			                </c:if>
			                 <span id="site_time">${beijingDate}</span>  
			                </span></td>
			                </tr>
			               	<tr class="startDateTr">
								<td align="right" class="left_text"><span class="red_star">*</span>${LANG['bizconf.jsp.attendConfloglist.res6']}</td>
								<td class="td_space">
									<div class="startDateDiv" style="float: left;margin-right:10px;position: relative;">
										<c:if test="${!empty conf && !empty conf.startTime}">
											<fmt:formatDate var="defaultDate" value="${conf.startTime}" />
										</c:if>
										<input class="right_text05 skipThese" id="startDate" name="startDate" type="text" value="" style="width:100px;"/>
									</div>
									<select name="startTimeH" id="startTimeH">
										<c:forEach var="h" begin="00" end="23" step="1">
											<option value="${h}" >${h} ${LANG['bizconf.jsp.create_Reservation_Conf.res98']}</option>
										</c:forEach>
									</select>
									<span style="margin-left: 5px;"></span>
									<select name="startTimeM" id="startTimeM">
										<c:forEach var="m" begin="00" end="55" step="5">
											<option value="${m}">${m} ${LANG['bizconf.jsp.create_Reservation_Conf.res99']}</option>
										</c:forEach>
									</select>
									
								</td>
							</tr>
							<tr  class="endTypeTR">
								<td style="height: 35px;"></td>
								<td class="td_space">
									<input class="endTypeRadio" name="endType" type="radio" value="1"/>${LANG['bizconf.jsp.create_Reservation_Conf.res100']}
									<span>
										<input class="endTypeRadio" name="endType" type="radio" value="2" checked="checked"/>${LANG['bizconf.jsp.create_Reservation_Conf.res101']}
									</span>
								</td>
							</tr>
							<tr class="endDateTr" style="display: none;">
								<input type="hidden" id="permanentConf_id" value="${conf.permanentConf}"/>
								<td align="right" class="left_text"><span class="red_star">*</span>${LANG['bizconf.jsp.attendConfloglist.res7']}</td>
								<td class="td_space">
									<div class="endDateDiv" style="float: left;margin-right:10px;position: relative;">
										<input class="right_text05 skipThese" id="endDate" name="endDate" type="text" value="" style="width:100px;"/>
									</div>
									<select name="endTimeH" id="endTimeH">
										<c:forEach var="h" begin="00" end="23" step="1">
											<option value="${h}" >${h} ${LANG['bizconf.jsp.create_Reservation_Conf.res98']}</option>
										</c:forEach>
									</select>
									<span style="margin-left: 5px;"></span>
									<select name="endTimeM" id="endTimeM">
										<c:forEach var="m" begin="00" end="55" step="5">
											<option value="${m}">${m} ${LANG['bizconf.jsp.create_Reservation_Conf.res99']}</option>
										</c:forEach>
									</select>
									
								</td>
							</tr>
			                 <tr style=" margin-top:14px;" class="durationTR">
			                	<td align="right" class="left_text"> <span class="red_star">*</span>${LANG['bizconf.jsp.create_Reservation_Conf.res102']}</td>
			                    <td align="left" class="td_space">
			  						<select name="meetingTimeH" id="meetingTimeH">
										<c:forEach var="h" begin="00" end="23" step="1">
											<option value="${h}" <c:if test="${h==1}">selected="selected"</c:if>>${h}
											<c:if test="${h==1 || h==0}">
											${LANG['bizconf.jsp.conf_list_index.hr']}
											</c:if>
											<c:if test="${h!=1 && h!=0}">
											${LANG['bizconf.jsp.conf_list_index.hrs']}
											</c:if> 
											</option>
										</c:forEach>
									</select>                  
									<select name="meetingTimeM" id="meetingTimeM">
										<c:forEach var="m" begin="00" end="55" step="5">
											<option value="${m}">${m} 
											<c:if test="${m==0}">
											${LANG['bizconf.jsp.conf_list_index.min']}
											</c:if>
											<c:if test="${m!=0}">
											${LANG['bizconf.jsp.conf_list_index.mins']}
											</c:if>
											</option>
										</c:forEach>
									</select>
			                    </td>
			                </tr>
			                 
			            </table>
			            </div>
						<div class="First_Steps_bottom">
			      <div class="but01"><a href="javascript:;" class="closeButton"><img src="/static/images/quxiao.png" width="11" height="10" align="absmiddle" style=" margin-right:8px; margin-left:10px"/>${LANG['bizconf.jsp.create_Reservation_Conf.res6400']}</a></div>
			      <div class="but02"><a href="javascript:;" class="prev"  pageIndex="2"><img src="/static/images/prev_bg.png" width="9" height="11" align="absmiddle" style=" margin-right:5px; margin-left:10px;" />${LANG['bizconf.jsp.create_Reservation_Conf.res103']}</a></div>
			      <div class="but02"><a href="javascript:;" class="next"  pageIndex="2"><img src="/static/images/next_bg.png" width="9" height="11" align="absmiddle" style=" margin-right:5px; margin-left:10px;" />${LANG['bizconf.jsp.create_Reservation_Conf.res66']}</a></div>
			    </div>
			</div>			
        <!--${LANG['bizconf.jsp.add_contacts.res2']}========================================================================-->
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
</div>
<div id="step3" class="validation_group" style="display: none;">
<table class="overlay-panel" border="0" cellpadding="0" cellspacing="0" >
    <tbody>
      <tr class="no-header">
        <td class="overlay-hdL"></td>
        <td class="overlay-hdC"></td>
        <td class="overlay-hdR"></td>
      </tr>
      <tr>
        <td class="overlay-bdL"></td>
        <td class="overlay-content">
        <!--${LANG['bizconf.jsp.add_contacts.res2']}========================================================================-->
			<div class="First_Steps">
			    <div class="First_Steps_title" style=" background:#FFF"> <a href="javascript:;" class="closeButton"></a> <img class="nar" src="/static/images/nar03.jpg" width="261" height="18" />
			    	<c:if test="${!empty conf }">
			      		<h3 class="tit">${LANG['bizconf.jsp.create_Reservation_Conf.res67']}</h3>
			    	</c:if>
			    	<c:if test="${empty conf }">
			      		<h3 class="tit">${LANG['bizconf.jsp.create_Reservation_Conf.res68']}</h3>
			    	</c:if>
			      <ul class="nar_text">
			        <li style=" margin-left:">${LANG['bizconf.jsp.create_Reservation_Conf.res69']}</li>
			        <li style=" margin-left:30px;">${LANG['bizconf.jsp.create_Reservation_Conf.res70']}</li>
			        <li style=" margin-left:35px;">${LANG['bizconf.jsp.create_Reservation_Conf.res71']}</li>
			        <li style=" margin-left:50px;">${LANG['bizconf.jsp.add_contacts.res14']}</li>
			      </ul>
			    </div>
			    <div style=" background:#fff"><img class="toa" src="/static/images/min.jpg" width="621" height="1" /></div>
			    <div class="First_Steps_top" style=" background:#FFF">
			      <h3>${LANG['bizconf.jsp.create_Reservation_Conf.res104']}</h3>
			      <p>${LANG['bizconf.jsp.create_Reservation_Conf.res105']}</p>
			    </div>
			    <div class="step3_content" style="overflow: hidden; min-height: 298px;_height:298px;">
				    <div class="First_Steps_main extend_option">
				      <table class="box_a">
				        <tr class="box01 confFuncTR" >
				          <td align="right" class="left_text">${LANG['bizconf.jsp.create_Reservation_Conf.res106']}</td>
				          <td align="left" class="td_space">
				          	<input class="confType" name="confType" type="checkbox" id="videoFunc" value="2" checked="checked"/><span id="videoFuncM">${LANG['bizconf.jsp.create_Reservation_Conf.res28']}</span>
				          	<input class="confType" name="confType" type="checkbox" id="phoneFunc" value="1"/><span id="phoneFuncM">${LANG['bizconf.jsp.conf_default_setup.res5']}</span>
			          	  </td>
				        </tr>
				        <tr class="box01" id="allowCallTr" style="display: none;">
				          <td  align="right" class="left_text">${LANG['bizconf.jsp.conf_default_setup.res32']}</td>
				          <td align="left" class="td_space">
				          	<input class=""  name="allowCall" type="radio" value="1" />${LANG['bizconf.jsp.conf_default_setup.res31']}
				          	<input class=""  name="allowCall" type="radio" value="0" checked="checked"/>${LANG['bizconf.jsp.attendConfloglist.res10']}
				          </td>
				        </tr>	        
				        <tr class="box01">
				          <td  align="right" class="left_text">${LANG['bizconf.jsp.conf_default_setup.res3']}</td>
				          <td align="left" class="td_space">
				          	<input class=""  name="confModel" type="radio" value="1" checked="checked"/>${LANG['bizconf.jsp.conf_default_setup.res26']}
				          	<input class=""  name="confModel" type="radio" value="0" />${LANG['bizconf.jsp.conf_default_setup.res27']}</td>
				        </tr>
				         <tr class="box01">
				          <td  align="right" class="left_text">${LANG['bizconf.jsp.conf_default_setup.res4']}</td>
				          <td align="left" class="td_space">
				          	<input class=""  name="micStatus" type="radio" value="1" />${LANG['bizconf.jsp.conf_default_setup.res29']}
				          	<input class=""  name="micStatus" type="radio" value="0" checked="checked"/>${LANG['bizconf.jsp.conf_default_setup.res30']}
				          </td>
				        </tr>
				      </table>
				    </div>
				    <div style="padding-top: 10px;">
				          <span class="gaoji" style="font-size: 14px;font-weight: bold;cursor: pointer;padding-right: 5px;">
				          	${LANG['bizconf.jsp.create_Reservation_Conf.res107']}<img src="/static/images/y_gaoji.jpg" style="width:7px;height:11px;margin-left:5px" alt="" id="gaojiicon"/></span>
				          
				    </div>
				    <div class="extra_panel">
				       <table class="box_a">
				        <tr class="box01" id="audioTR">
				          <td align="right" class="left_text">${LANG['bizconf.jsp.create_Reservation_Conf.res108']}</td>
				          <td align="left" class="td_space">
					          <select name="maxAudio" id="maxAudio">
								<c:forEach var="h" begin="0" end="${audioNumber }" step="1">
									<option value="${h}">${h} ${LANG['bizconf.jsp.conf_default_setup.res19']}</option>
								</c:forEach>
							  </select>
							</td>
				        </tr>
				        <tr class="box01" id="videoTR">
				          <td  align="right" class="left_text">${LANG['bizconf.jsp.create_Reservation_Conf.res109']}</td>
				          <td align="left" class="td_space">
				          	<select name="maxVideo" id="maxVideo">
								<c:forEach var="h" begin="0" end="${videoNumber }" step="1">
					        		<c:if test="${h<7 || h==16}">
					        			<option value="${h}">${h} ${LANG['bizconf.jsp.conf_default_setup.res19']}</option>
					        		</c:if>
								</c:forEach>
							</select>
				         </td>
				        </tr>
				        <tr class="box01">
				          <td  align="right" class="left_text">${LANG['bizconf.jsp.conf_default_setup.res21']}</td>
				          <td align="left" class="td_space">
				                <cc:confList var="CONF_VIDEO_TYPE_FLUENCY"/>
					            <cc:confList var="CONF_VIDEO_TYPE_DISTINCT"/>
					            <cc:confList var="CONF_VIDEO_TYPE_WEBBAND"/>
					            <cc:confList var="CONF_VIDEO_TYPE_BEST"/>
					            
					            <cc:confList var="CONF_VIDEO_TYPE_FLUENCY_CODE"/>
					            <cc:confList var="CONF_VIDEO_TYPE_DISTINCT_CODE"/>
					            <cc:confList var="CONF_VIDEO_TYPE_WEBBAND_CODE"/>
					            <cc:confList var="CONF_VIDEO_TYPE_BEST_CODE"/>
				            <input class="leixing"  name="videoType" type="radio" value="${CONF_VIDEO_TYPE_WEBBAND }" <c:if test="${userEmpower.dpiNumber==CONF_VIDEO_TYPE_WEBBAND_CODE }"> checked="checked" </c:if> />${LANG['bizconf.jsp.conf_default_setup.res24']}
				            <c:if test="${userEmpower.dpiNumber>=CONF_VIDEO_TYPE_FLUENCY_CODE }">
				          	<input class="leixing"  name="videoType" type="radio" value="${CONF_VIDEO_TYPE_FLUENCY }" checked="checked"/>${LANG['bizconf.jsp.conf_default_setup.res22']}
				          	</c:if>
				          	<c:if test="${userEmpower.dpiNumber>=CONF_VIDEO_TYPE_DISTINCT_CODE }">
				          	<input class="leixing"  name="videoType" type="radio" value="${CONF_VIDEO_TYPE_DISTINCT }" />${LANG['bizconf.jsp.conf_default_setup.res23']}
				          	</c:if>
				          	<c:if test="${userEmpower.dpiNumber>=CONF_VIDEO_TYPE_BEST_CODE }">
				          	<input class="leixing"  name="videoType" type="radio" value="${CONF_VIDEO_TYPE_BEST }" />${LANG['system.quality.level.best']}
				          	</c:if>
				          </td>
				        </tr>
				         <tr class="box01" >
				          <td  align="right" class="left_text">${LANG['bizconf.jsp.conf_default_setup.res25']}</td>
				          <td align="left" class="td_space">
				          	<input class="right_text skipThese" id="aheadTime" name="aheadTime" type="text" value="10" style="width: 100px"/> ${LANG['bizconf.jsp.attended_conf_list.res10']}
				          </td>
				        </tr>
				         <tr class="box01">
				          <td  align="right" class="left_text">${LANG['bizconf.jsp.conf_default_setup.res33']}</td>
				          <td align="left" class="td_space">
				          	<input class="extraPermission" name="changePage" type="checkbox" value="1" />${LANG['bizconf.jsp.conf_default_setup.res34']}
				        	<input class="extraPermission" name="annotate" type="checkbox" value="1" />${LANG['bizconf.jsp.conf_default_setup.res35']}
				            <input class="extraPermission" name="chatAnyOne" type="checkbox" value="1" />${LANG['bizconf.jsp.conf_default_setup.res36']}
				            <input class="extraPermission" name="chatCompere" type="checkbox" value="1" />${LANG['bizconf.jsp.conf_default_setup.res37']}
				            <input class="extraPermission" name="chatParticipants" type="checkbox" value="1" />${LANG['bizconf.jsp.conf_default_setup.res38']}
				          </td>
				        </tr>
				         <tr class="box01">
				          <td  align="right" class="left_text">${LANG['bizconf.jsp.conf_default_setup.res9']}</td>
				          <td align="left" class="td_space">
					        <input class="clientOption" name="shareDocs" type="checkbox" value="1" />${LANG['bizconf.jsp.conf_default_setup.res39']}
					        <input class="clientOption" name="shareScreen" type="checkbox" value="1" />${LANG['bizconf.jsp.conf_default_setup.res40']}
					        <input class="clientOption" name="shareMedia" type="checkbox" id="shareMedia" value="1" /><span id="shareMediaM">${LANG['bizconf.jsp.conf_default_setup.res41']}</span>
					        <input class="clientOption" name="whiteBoard" type="checkbox" value="1" />${LANG['bizconf.jsp.conf_default_setup.res42']}
					        <input class="clientOption" name="fileTrans" type="checkbox" value="1" />${LANG['bizconf.jsp.conf_default_setup.res43']}
					        <input class="clientOption" name="record" type="checkbox" id="record" value="1" /><span id="recordM">${LANG['bizconf.jsp.conf_default_setup.res44']}</span>
				          </td>
				        </tr>
				        
				      </table>    
				    </div>    
			    </div>
			    <div class="First_Steps_bottom">
			      <div class="but01"><a href="javascript:;" class="closeButton"><img src="/static/images/quxiao.png" width="11" height="10" align="absmiddle" style=" margin-right:8px; margin-left:10px"/>${LANG['bizconf.jsp.create_Reservation_Conf.res6400']}</a></div>
				  <div class="but02"><a href="javascript:;" class="prev"  pageIndex="2"><img src="/static/images/prev_bg.png" width="9" height="11" align="absmiddle" style=" margin-right:5px; margin-left:10px;" />${LANG['bizconf.jsp.create_Reservation_Conf.res103']}</a></div>
			      <div class="but02"><a href="javascript:;" class="next"  pageIndex="3"><img src="/static/images/next_bg.png" width="9" height="11" align="absmiddle" style=" margin-right:5px; margin-left:10px;" />${LANG['bizconf.jsp.create_Reservation_Conf.res1101']}</a></div>
			    </div>
			</div>			
        <!--${LANG['bizconf.jsp.add_contacts.res2']}========================================================================-->
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
</div>

<div id="step4" class="" style="display: none;">
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
        
        <td class="overlay-content" style="width:660px; height: 440px;background:#FFF;" > 
          <div class="First_Steps_title" style=" background:#FFF"> 
          		<a href="javascript:createSuccess();"></a>
          		<img class="nar" src="/static/images/nar04.jpg" width="261" height="18" />
              <h3 class="tit">${LANG['bizconf.jsp.create_Reservation_Conf.res68']}</h3>
              <ul class="nar_text">
                <li style=" margin-left:">${LANG['bizconf.jsp.create_Reservation_Conf.res69']}</li>
                <li style=" margin-left:30px;">${LANG['bizconf.jsp.create_Reservation_Conf.res70']}</li>
                <li style=" margin-left:35px;">${LANG['bizconf.jsp.create_Reservation_Conf.res71']}</li>
                <li style=" margin-left:50px;">${LANG['bizconf.jsp.add_contacts.res14']}</li>
              </ul>
          </div>
          <div style=" background:#fff"><img class="toa" src="/static/images/min.jpg" width="621" height="1" /></div>
          <div class="overlay-top">
          	<img src="/static/images/agree.png" width="52" height="41" />
          	<h3>${LANG['bizconf.jsp.create_Reservation_Conf.res112']}</h3>
          </div>
          <div class="overlay-main">
          	<div class="overlay-main_left">
            	<table border="0" cellpadding="0" cellspacing="0" id="overlay-main_txt">
<!--             	 <tr class="cycleTypeTR"> -->
<!--             	<td height="30">${LANG['bizconf.jsp.create_Reservation_Conf.res113']}</td> -->
<!--             	<td><span id="cycleTypeSpan"></span></td> -->
<!--             </tr> -->
<!--             <tr class="cycleTimeTRD"> -->
<!--             	<td height="30">${LANG['bizconf.jsp.create_Reservation_Conf.res114']}</td> -->
<!--             	<td><span id="cycleTimeSpan"></span></td> -->
<!--             </tr> -->
                	<tr>
                    	<td class="overlay_title">${LANG['bizconf.jsp.attendConfloglist.res3']}:</td>
                    	<td class="overlay_content"><span id="confNameSpan"></span></td>
                    </tr>
                   
                    <tr class="cycleTypeTR">
                    	<td class="overlay_title">${LANG['bizconf.jsp.create_Reservation_Conf.res75']}:</td>
                    	<td class="overlay_content"><span id="cycleTypeSpan"></span></td>
                    </tr>
                    <tr class="cycleTimeTRD">
                    	<td class="overlay_title">${LANG['bizconf.jsp.create_Reservation_Conf.res92']}:</td>
                    	<td class="overlay_content"><span id="cycleTimeSpan"></span></td>
                    </tr>
                    <tr>
                    	<td class="overlay_title">${LANG['bizconf.jsp.create_Reservation_Conf.res102']}:</td>
                    	<td class="overlay_content"><span id="durationSpan"></span></td>
                    </tr>
                     <tr class="confTypeTR">
                    	<td class="overlay_title">${LANG['bizconf.jsp.create_Reservation_Conf.res106']}:</td>
                    	<td class="overlay_content"><span id="confTypeSpan"></span></td>
                    </tr>
<!--                      <tr> -->
<!--                     	<td class="overlay_title">${LANG['bizconf.jsp.conf_default_setup.res16']}:</td> -->
<!--                     	<td class="overlay_content"><span id="maxUserSpan"></span></td> -->
<!--                     </tr> -->
                     <tr>
                    	<td class="overlay_title">${LANG['bizconf.jsp.create_Reservation_Conf.res115']}:</td>
                    	<td class="overlay_content"><span id="userSecureSpan"></span></td>
                    </tr>
                </table>
            </div>
            <img class="add_li" src="/static/images/add_li_bg.png" width="5" height="265" />
            <div class="overlay-main_right">
           	 	<input type="hidden" id="confIdForEmail" value=""/>
            	<h3>${LANG['bizconf.jsp.conf_invite_recv.res13']}:</h3>
                <p>${LANG['bizconf.jsp.create_Reservation_Conf.res1160']}</p>
                <a class="send_outlook" href="javascript:sendNoticeEmail();"><img src="/static/images/email05.png" width="16" height="12" align="absmiddle" style=" padding-right:5px;"/>Outlook</a>
                <a class="send_gmail" href="javascript:sendNoticeEmail();" style=""> <img src="/static/images/ico002.png" width="16" height="11" align="absmiddle" style=" padding-right:5px;" />Gmail</a>
                <a class="overlay_email03" href="javascript:sendNoticeEmail();" style="display: none;"><img src="/static/images/ico003.png" width="14" height="17" align="absmiddle" style=" padding-right:5px;" />Foxmail</a>
	            <div style="clear: left;width:100%;height:40px;"></div>
				<h3 style="clear: left;">${LANG['bizconf.jsp.create_Reservation_Conf.res118']}:</h3>
				<p>${LANG['bizconf.jsp.create_Reservation_Conf.res119']}</p>
				<a class="email_outlook" href="javascript:inventContact();"><img src="/static/images/yaoqing.jpg" width="16" height="12" align="absmiddle" style=" padding-right:5px;"/>${LANG['bizconf.jsp.conf_list_index.res13']}</a>
                <a class="meet_ok" href="javascript:createSuccess();"><img src="/static/images/ys_r_bg.png" width="16" height="14" align="absmiddle" style=" padding-right:5px;"/>${LANG['bizconf.jsp.create_Reservation_Conf.res120']}</a>

            </div>
          </div>
          <div class="overlay_bottom" style=" height:40px;"></div>
          
        </td>
        
        <!--${LANG['bizconf.jsp.add_contacts.res2']}========================================================================-->
        
        <td class="overlay-bdR"></td>
      </tr>
      <tr>
        <td class="overlay-ftL"></td>
        <td class="overlay-ftC"></td>
        <td class="overlay-ftR"></td>
      </tr>
    </tbody>
  </table>
</div>
</form>
</body>
</html>
<script type="text/javascript">
function loaded() {
	var frame = parent.$("#bookMeeting");
	frame.trigger("loaded");
}

function closeDialog(result) {
	var dialog = parent.$("#bookMeeting");
	if(result){
		dialog.trigger("closeDialog", [result]);
	} else {
		dialog.trigger("closeDialog");	
	}
}
</script>
