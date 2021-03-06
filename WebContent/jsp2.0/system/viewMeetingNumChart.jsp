<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/reset.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/style.css"/>

<link rel="stylesheet" type="text/css" href="/static/js/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/js/jquery.uniform/themes/default/css/uniform.custom.css">
<link rel="stylesheet" type="text/css" href="${baseUrlStatic}/css/common.css"/>

<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-1.8.3.js"></SCRIPT>
<SCRIPT type="text/javascript" src="/static/js/jquery-ui-1.9.2.custom.js"></SCRIPT>
<!--[if lte IE 6]>  
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/jquery-ui-1.9.2.custom/development-bundle/external/jquery.bgiframe-2.1.2.js"></SCRIPT>  
<![endif]-->
<script type="text/javascript" src="${baseUrlStatic}/js/jquery.uniform/jquery.uniform.js"></script>
<SCRIPT type="text/javascript" src="${baseUrlStatic}/js/json2.js"></SCRIPT>
<script type="text/javascript" src="${baseUrlStatic}/js/util.js"></script>
<script type="text/javascript" src="${baseUrlStatic}/js/widgets.js"></script>
<script src="${baseUrlStatic}/js/chart/highcharts.js"></script>
<script src="${baseUrlStatic}/js/chart/exporting.js"></script>
<script type="text/javascript"> 
var dates  = [];
var nums = [];

var rotationValue = 0;
var serisname = "本周";
var freq = '${freq}';
var subtitle = "近一周统计";
if(freq == 2){
	rotationValue = -45;
	serisname = "本月";
	subtitle = "近一月统计";
}
serisname += "会议场次";

<c:forEach var="date" items ="${dateList}"  varStatus="status">
	dates.push("${myfn:fmtDate('yyyy-MM-dd',date,28800000) }")
</c:forEach>
	
<c:forEach var="num" items ="${numList}"  varStatus="status">
	nums.push(${num})
</c:forEach>
$(function () {
    $('#container').highcharts({
        title: {
            text: '会议场数统计',
            x: -20 //center
        },
        subtitle: {
            text: subtitle,
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
                text: '会议 (场)'
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
                '<td style="padding:0;"><b>{point.y}场会议</b></td></tr>',
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
            name: serisname,
            data: nums
        }]
    });
});

function viewDetail(x,y){
	if(y>0){
		//var date = $(obj).parent().parent().parent().parent().find("span[name=date]").attr("data");
		var date = x;
		date += " 00:00:00"
		$("#detaildata").show();
		var url = '/system/statistical/viewDetailMeetingNum?date='+date;
		$("#detailFrame").attr("src",url);
	}else{
		$("#detaildata").hide();
	}
}

function viewSite(id) {
	$("<div id=\"siteDiv\"/>").showDialog({
		"title" : "${LANG['system.site.meaasge.lookup']}",
		"dialogClass" : "ui-dialog-smile",
		"url" : "/system/site/view/" + id,
		"type" : VIEW_TYPE.site,
		"action" : ACTION.view,
		"width" : 420,
		"height" : 460
	});
}
</script>
<title>会议统计</title>
</head>
<body style="min-width:1002px;">
<%-- <jsp:include page="header.jsp" /> --%>
	<div class="">
	 	<div id="container" style="min-width: 310px; height: 300px; margin: 0 auto"></div>
	 	<div id="detaildata" style="margin-top: 20px; width:1260px; overflow: hidden;" align="center;">
	 		<iframe frameborder="0" height="100%"  id="detailFrame" name="mainFrame" style="height:500px;" width="1060px;" scrolling="no" src=""></iframe>
	 	</div>
	</div>
</body>
</html>
