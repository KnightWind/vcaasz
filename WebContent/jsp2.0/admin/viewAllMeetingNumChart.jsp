<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ include file="/jsp/common/cookie_util.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
<!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
<!--[if lt IE 7]><script src="/assets/js/lib/belatedpng.js"></script><![endif]-->
<link href="/assets/favicon.ico" rel="shortcut icon" type="image/x-icon">
<link href="/assets/css/base.css" rel="stylesheet" type="text/css">
<link href="/assets/css/apps/orders.css" rel="stylesheet" type="text/css">

<link rel="stylesheet" type="text/css" href="/static/js/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css"/>
<link rel="stylesheet" type="text/css" href="/assets/css/module/jquery-ui-1.9.2.custom.css"/>

<!-- <link rel="stylesheet" type="text/css" href="/assets/css/module/jquery.ui.base.css" /> -->
<link rel="stylesheet" type="text/css" href="/assets/css/apps/biz.adm.index.css" />
 <style type="text/css"> 
	.ui-datepicker select.ui-datepicker-month{
		width: 30%;
	}
	.ui-datepicker select.ui-datepicker-year {
		width: 35%;
	}
 </style> 
 
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/assets/js/lib/json2.js"></script>
<!--[if IE 6]>
<script type="text/javascript" src="/assets/js/lib/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="/assets/js/lib/DD_belatedPNG_0.0.8a.js"></script>  
<![endif]-->
<script type="text/javascript" src="/assets/js/module/biz.plugins.js"></script>
<script type="text/javascript" src="/assets/js/module/stats.widgets.js"></script>
<script type="text/javascript" src="/assets/js/apps/biz.ajax.js"></script>


<script type="text/javascript" src="/assets/js/lib/jquery-ui-i18n.min.js"></script>
<script src="${baseUrlStatic}/js/chart/highcharts.js"></script>
<!-- <script src="${baseUrlStatic}/js/chart/exporting.js"></script> -->
<script type="text/javascript"> 
var dates  = [];
var nums = [];

var rotationValue = 0;

var title = "";
var subTitle = "";
var freq = '${statType}';
var totalnum = 0;
if(freq == 1){
	subTitle = "${LANG['website.site.admin.chart.statis.timebymonth']}";
	title = '${year}'+"/"+'${month}'+' ${LANG['website.user.billling.list.to']} '+'${eyear}'+"/"+'${emonth}'+" ${LANG['website.user.conf.confnumber']}";
}else{
	subTitle = "${LANG['website.site.admin.chart.statis.timebydate']}";
	title = '${startDate}'+' ${LANG['website.user.billling.list.to']} '+'${endDate}'+" ${LANG['website.user.conf.confnumber']} ";
}

<c:forEach var="date" items ="${dateList}"  varStatus="status">
	<c:choose>
		<c:when test="${statType eq 1}">
			dates.push("${myfn:fmtDate('yyyy/MM',date,28800000) }")
		</c:when>
		<c:otherwise>
			dates.push("${myfn:fmtDate('yyyy-MM-dd',date,28800000) }")
		</c:otherwise>
	</c:choose>
</c:forEach>
	
<c:forEach var="num" items ="${numList}"  varStatus="status">
	nums.push(${num});
	totalnum += parseInt("${num}");	
</c:forEach>
	
if(dates.length>20){
	rotationValue = -45;
}

$(function () {
    $('#container').highcharts({
        title: {
            text: title,
            x: -20 //center
        },
        subtitle: {
            text: subTitle,
            x: -20
        },
        xAxis: {
        	//gridLineWidth: 1, 
        	labels:{
        		rotation: rotationValue 
        	},
            categories: dates
        },
        
        yAxis: {
        	allowDecimals:false,
        	min:0,
            title: {
                text: '${LANG['website.site.admin.chart.meetings.unit']}'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        credits: {
            enabled: false
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px;cursor: pointer;" name="date" data="{point.key}">{point.key}:</span><table style="cursor: pointer;">',
            pointFormat: '<tr><td style="color:{series.color};padding:0"></td>' +
                '<td style="padding:0;cursor: pointer;"><b>{point.y} ${LANG['website.site.admin.chart.meetingstips.unit']}</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {//折点的点击事件--查看详情
            series: {
                cursor: 'pointer',
                point: {
                    events: {
                        click: function () {
                            //alert('Category: ' + this.category + ', value: ' + this.y);
                            viewDetail(this.category,this.y);
                        }
                    }
                }
            }
        },
        series: [{
            name: title,
            data: nums
        }]
    });
});


//function viewDetail(obj,num){
function viewDetail(x,num){
	if(num > 0){
		$("#detaildata").show();
		//var date = $(obj).parent().parent().parent().parent().find("span[name=date]").attr("data");
		var date = x;
		
		if(date.indexOf("/")>0){
			var year = date.substring(0,4);
			var month = date.substring(5);
			var url = '/admin/statistical/viewDetailMeetingNum?year='+year+'&month='+month;
		}else{
			date += " 00:00:00"
			var url = '/admin/statistical/viewDetailMeetingNum?date='+date;
		}
		$("#detailFrame").attr("src",url);
	}else{
		$("#detaildata").hide();
	}
}



var currMonthStr = "<option value=\"1\" >${LANG['system.billing.res.moth1']}</option>";
currMonthStr += "<option value=\"2\" >${LANG['system.billing.res.moth2']}</option>";
currMonthStr += "<option value=\"3\" >${LANG['system.billing.res.moth3']}</option>";
currMonthStr += "<option value=\"4\" >${LANG['system.billing.res.moth4']}</option>";
currMonthStr += "<option value=\"5\" >${LANG['system.billing.res.moth5']}</option>";
currMonthStr += "<option value=\"6\" >${LANG['system.billing.res.moth6']}</option>";
currMonthStr += "<option value=\"7\" >${LANG['system.billing.res.moth7']}</option>";
currMonthStr += "<option value=\"8\" >${LANG['system.billing.res.moth8']}</option>";
currMonthStr += "<option value=\"9\" >${LANG['system.billing.res.moth9']}</option>";
currMonthStr += "<option value=\"10\" >${LANG['system.billing.res.moth10']}</option>";
currMonthStr += "<option value=\"11\" >${LANG['system.billing.res.moth11']}</option>";
currMonthStr += "<option value=\"12\" >${LANG['system.billing.res.moth12']}</option>";


var selMonthStr = "";
<c:forEach begin="1" var="index" end="${currmonth}">
	<c:if test="${index eq 1}">selMonthStr += "<option value=\"1\">${LANG['system.billing.res.moth1']}</option>";</c:if>
	<c:if test="${index eq 1}">selMonthStr += "<option value=\"2\">${LANG['system.billing.res.moth2']}</option>";</c:if>
	<c:if test="${index eq 3}">
		selMonthStr += "<option value=\"3\">${LANG['system.billing.res.moth3']}</option>";
	</c:if>
	<c:if test="${index eq 4}">
		selMonthStr += "<option value=\"4\" >${LANG['system.billing.res.moth4']}</option>";
	</c:if>
	<c:if test="${index eq 5}">
		selMonthStr += "<option value=\"5\" >${LANG['system.billing.res.moth5']}</option>";
	</c:if>
	<c:if test="${index eq 6}">
		selMonthStr += "<option value=\"6\" >${LANG['system.billing.res.moth6']}</option>";
	</c:if>
	<c:if test="${index eq 7}">
		selMonthStr += "<option value=\"7\" >${LANG['system.billing.res.moth7']}</option>";
	</c:if>
	<c:if test="${index eq 8}">
		selMonthStr += "<option value=\"8\" >${LANG['system.billing.res.moth8']}</option>";
	</c:if>
	<c:if test="${index eq 9}">
		selMonthStr += "<option value=\"9\" >${LANG['system.billing.res.moth9']}</option>";
	</c:if>
	<c:if test="${index eq 10}">
		selMonthStr += "<option value=\"10\" >${LANG['system.billing.res.moth10']}</option>";
	</c:if>
	<c:if test="${index eq 11}">
		selMonthStr += "<option value=\"11\" >${LANG['system.billing.res.moth11']}</option>";
	</c:if>
	<c:if test="${index eq 12}">
		selMonthStr += "<option value=\"12\">${LANG['system.billing.res.moth12']}</option>";
	</c:if>
</c:forEach>


$(document).ready(function(){
	if($("#yearSelect").val()=='${curryear}'){
		$("#monthSelect").html(selMonthStr);
	}else{
		$("#monthSelect").html(currMonthStr);
	}	
	
	if($("#yearSelecte").val()=='${curryear}'){
		$("#monthSelecte").html(selMonthStr);
	}else{
		$("#monthSelecte").html(currMonthStr);
	}	
	
	$("#yearSelect").change(function(){
		if($(this).val()=='${curryear}'){
			$("#monthSelect").html(selMonthStr);
		}else{
			$("#monthSelect").html(currMonthStr);
		}			
	});
	 
	
	$("#statbymonth").click(function(){
		$("#showMonthly").show();
		$("#showDaily").hide();
	});
	
	$("#statbydate").click(function(){
		$("#showMonthly").hide();
		$("#showDaily").show();
	});
	
	var lang = getBrowserLang(); 
	if (lang=="zh-cn") {
		$.datepicker.setDefaults( $.datepicker.regional[ "zh-CN" ] );
	} else {
		$.datepicker.setDefaults( $.datepicker.regional[ "en-GB" ] );
	}
	$("#startDate, #endDate").datepicker({
		minDate: new Date(2014,05,01),
		maxDate:new Date(),
		changeMonth: true,
		changeYear: true,
		dateFormat: "yy-mm-dd",
		showOn: "both",
		buttonImage: "/static/images/calendar.jpg",
		buttonImageOnly: true
	});
	
	var statType = '${statType}';
	
	if(statType == 1){
		$("#statbymonth").attr("checked",true);
		$("#showMonthly").show();
		$("#showDaily").hide();
	}else{
		$("#statbydate").attr("checked",true);
		$("#showMonthly").hide();
		$("#showDaily").show();
	}
	
	var startDate = '${startDate }';
	$("#startDate").val(startDate);
	var endDate = '${endDate }';
	$("#endDate").val(endDate);
	
	
	$("#monthSelect").val("${month}");
	$("#monthSelecte").val("${emonth}");
	
	$("#totalnum").html(totalnum);
});

function submitForm(){
	if($("#statbydate").attr("checked") == 'checked'){
		var sDate = $("#startDate").val();
		var eDate = $("#endDate").val();
		if(!sDate){
			errorDialog('${LANG['website.site.admin.chart.statis.warntip01']}');
		}
		if(!eDate){
			errorDialog('${LANG['website.site.admin.chart.statis.warntip02']}');
		}
		var syear = sDate.substring(0,4);
		var smonth = sDate.substring(5,7);
		var sdate = sDate.substring(8,10);
		
		var eyear = eDate.substring(0,4);
		var emonth = eDate.substring(5,7);
		var edate = eDate.substring(8,10);
		
		var startDate = new Date(syear,smonth-1,sdate);
		var endDate = new Date(eyear,emonth-1,edate);
		var gap =  Math.abs(endDate.getTime()-startDate.getTime());
		
		if(gap>60*24*3600*1000){
			errorDialog("${LANG['website.site.admin.chart.statis.warntip03']}");
		}else{
			$("#query").submit();
		}
	}else{
		$("#query").submit();
	}
}

function errorDialog(message,callback) {
	/**if(!callback){
		callback = function(){
			alert($(".ui-dialog-alert").html());
			$(".ui-dialog-alert").css("top",280);
			$(".ui-dialog-alert").css("left",460);
		}
	}*/
	$("<div>").alertDialog({
		"title": "${LANG['website.user.index.js.message.title.remind.common']}",//"提醒",
		"dialogClass" : "ui-dialog-alert",
		"message" : message,
		"type": "error",
		"buttonOk": "${LANG['website.common.option.confirm']}",//"确定",
		"successCallback": callback
	});
}
</script>
<title>${LANG['website.site.admin.meetingdata.statis']}</title>
</head>
<body style="min-width:1002px;">
	<div class="">
			<div  style="padding: 12px;line-height: 28px;">
			<div style="width: 70%; float: left;">
				<form id="query" action="/admin/statistical/viewAllMeetingNumChar" method="post">
					 <div>
					 	${LANG['website.site.admin.chart.statis.method']}：<input  type="radio" value="1" name="statType" checked="checked"  id="statbymonth"/><label>${LANG['website.site.admin.chart.statis.timebymonth']}</label> &nbsp; <input type="radio"  value="2" name="statType" id="statbydate"/><label>${LANG['website.site.admin.chart.statis.timebydate']}</label>
					 </div>
					 <div id="showMonthly">
					 	${LANG['website.site.admin.chart.statis.time']}： <select  name="year" id="yearSelect" >
					            <option value="${curryear}" <c:if test="${year eq curryear}">selected="selected"</c:if>>${curryear} ${LANG['bizconf.jsp.admin.mybilling_list.res1']}</option>
					            <option value="${curryear-1}" <c:if test="${year eq (curryear-1)}">selected="selected"</c:if>>${curryear-1} ${LANG['bizconf.jsp.admin.mybilling_list.res1']}</option>
					          </select>
					          <select  name="month" id="monthSelect">
					          </select>
					          --
					           <select  name="eyear" id="yearSelecte" >
					            <option value="${curryear}" <c:if test="${eyear eq curryear}">selected="selected"</c:if>>${curryear} ${LANG['bizconf.jsp.admin.mybilling_list.res1']}</option>
					            <option value="${curryear-1}" <c:if test="${eyear eq (curryear-1)}">selected="selected"</c:if>>${curryear-1} ${LANG['bizconf.jsp.admin.mybilling_list.res1']}</option>
					          </select>
					          <select  name="emonth" id="monthSelecte">
					          </select>
					 </div>
					 <div id="showDaily">
					 	${LANG['website.site.admin.chart.statis.time']}： <input style="width: 100px" readonly="readonly" type="text"  name="startDate" id="startDate"/>
					          --
					          <input style="width: 100px" readonly="readonly" type="text"  name="endDate" id="endDate"/>
					 </div>
					 <div>
					 	<input class="input-gray"  type="button" onclick="submitForm();" value="${LANG['website.common.option.lookup']}"/>
					 </div>
				</form>
				</div>
				<div style="width: 25%;float: left;overflow: hidden;">
					<div style="height: 50px;">
						<div style="border: 0 none; width: 280px;font-size: 16px;">${LANG['website.site.admin.chart.statis.allmeetings']}：<span id="totalnum" style="font-size: 16px; font-weight: bold;"></span></div>
					</div>
				</div>
			</div>
	 	<div id="container" style="min-width: 310px; height: 300px; margin: 0 auto;clear: both;"></div>
	 	<div id="detaildata" style="margin-top: 20px; width:1260px; overflow: hidden;" align="center">
	 		<iframe frameborder="0" height="100%"  id="detailFrame" name="mainFrame" style="height:100px;" width="1060px;" scrolling="no" src=""></iframe>
	 	</div>
	</div>
</body>
</html>
