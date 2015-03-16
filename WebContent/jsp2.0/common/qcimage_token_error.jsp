<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css" />
<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script> 
<link type="text/css" rel="stylesheet" href="/assets/css/login.css" />
<title>${LANG['outer.conf.join.qccode.error.title']}</title>

<style>
.biz-titleBar { 
	position: relative; 
/* 	width: 100%; */
	background: #F9F9F9;
	background: linear-gradient(top,#F9F9F9,#EBEBEB);
	background: -moz-linear-gradient(top,#F9F9F9,#EBEBEB);
	background: -webkit-linear-gradient(top,#F9F9F9,#EBEBEB);
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr=#F9F9F9,endColorstr=#EBEBEB);
    padding: 0 15px;
	border-bottom: 1px solid #CCCCCC;
	height: 36px;
	line-height: 36px;   	
}
.biz-title {
    cursor: default;
    overflow: hidden;
    text-overflow: ellipsis;
    color: #333333;
/*     height: 33px; */
/*     line-height: 33px; */
    padding: 0 16px 0 0;
	font-family: "Microsoft Yahei","微软雅黑","宋体";
	font-size: 15px;
 
}
.icon-join {
    background: url("/assets/images/sprite/icons-png8.png") no-repeat scroll -154px -188px transparent;
    height: 18px;
    margin: 7px 5px 7px 0;
    width: 18px;
}
.biz-icon {
    display: inline-block;
    vertical-align: middle;
}
.biz-close {
    display: block;
    outline: none;
    position: absolute;
    text-decoration: none;
    background: url("/assets/images/buttons/close.png") no-repeat scroll center center transparent;
    height: 13px;
    right: 10px;
    text-indent: -9999em;
    top: 10px;
    width: 13px;
}
.biz-close:hover{
    background-image: url(/assets/images/buttons/close-hover.png);
}
.input-select option {
	padding: 0 5px;
}
.input-text, .input-area {
	border: 1px solid #DADADA;
	border-radius: 3px;
	padding: 5px;
	height: 16px;
	box-shadow: 0 1px #f7f7f7 inset;
}
.input-text:focus, .input-area:focus {
	border: 1px solid #8caac8;
	box-shadow: 0 0 5px #d6e2ed;
}
.input-button,.input-submit,.input-green,.input-gray,.input-cancel,.input-disable,.input-warn {
	border-radius: 3px;
	border-style: solid;
	border-width: 1px;
	cursor: pointer;
	height: 30px;
	padding: 0 20px;
	text-align: center;
	overflow: visible;
	vertical-align: middle;
	margin: 0;
}
:root .input-button,
:root .input-submit,
:root .input-green,
:root .input-gray,
:root .input-cancel,
:root .input-disable,
:root .input-warn {
	min-width: 80px;
}
button.input-disable, .input-disable[type=button], .input-disable[type=submit] {
	padding: 0 20px;
	height: 30px;
	line-height: 100%;
}
.input-button,.input-submit {
	background: #44ADEF;
	background: linear-gradient(top,#44ADEF,#2B94D6);
	background: -moz-linear-gradient(top,#44ADEF,#2B94D6);
	background: -webkit-linear-gradient(top,#44ADEF,#2B94D6);
	border-color: #44ADEF;
	color: #FFFFFF;
	box-shadow: 0 1px #5EA2EC inset;
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr=#44ADEF,endColorstr=#2B94D6);
}
.input-button:hover,.input-submit:hover {
	background: #39AFED;
	background: linear-gradient(top,#5EC5FF,#39AFED);
	background: -moz-linear-gradient(top,#5EC5FF,#39AFED);
	background: -webkit-linear-gradient(top,#5EC5FF,#39AFED);
	text-decoration: none;
	border-color: #44ADEF;
	box-shadow: 0 0.5px #5EA2EC inset;
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr=#5EC5FF,endColorstr=#39AFED);
}
.input-button:active,.input-submit:active {
	background: #2B94D6;
	background: linear-gradient(bottom,#44ADEF,#2B94D6);
	background: -moz-linear-gradient(bottom,#44ADEF,#2B94D6);
	background: -webkit-linear-gradient(bottom,#44ADEF,#2B94D6);
	text-decoration: none;
	box-shadow: 0 1px #5EA2EC inset;
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr=#2B94D6,endColorstr=#44ADEF);
}
.input-gray,.input-cancel {
	background: #FFFFFF;
	background: linear-gradient(top,#FFFFFF,#ECECEC);
	background: -moz-linear-gradient(top,#FFFFFF,#ECECEC);
	background: -webkit-linear-gradient(top,#FFFFFF,#ECECEC);
	border-color: #CCCCCC;
	color: #666666;
	box-shadow: 0 1px #F7F7F7 inset;
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr=#FFFFFF,endColorstr=#ECECEC);
}
.input-gray:hover,.input-cancel:hover {
	background: #FDFDFD;
	background: linear-gradient(top,#FFFFFF,#F7F7F7);
	background: -moz-linear-gradient(top,#FFFFFF,#F7F7F7);
	background: -webkit-linear-gradient(top,#FFFFFF,#F7F7F7);
	text-decoration: none;
	box-shadow: 0 -1px #F0F0F0 inset;
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr=#FFFFFF,endColorstr=#F7F7F7);
}
.input-gray:active,.input-cancel:active {
	background: #DADADA;
	background: linear-gradient(bottom,#FEFEFE,#DADADA);
	background: -moz-linear-gradient(bottom,#FEFEFE,#DADADA);
	background: -webkit-linear-gradient(bottom,#FEFEFE,#DADADA);
	text-decoration: none;
	box-shadow: 0 1px #D9D8D8 inset;
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr=#DADADA,endColorstr=#FEFEFE);
}
.input-warn {
	background: #F26866;
	background: linear-gradient(top,#F26866,#B60307);
	background: -moz-linear-gradient(top,#F26866,#B60307);
	background: -webkit-linear-gradient(top,#F26866,#B60307);
	border-color: #B81F24;
	color: #FFFFFF;
	box-shadow: 0 1px #E34A4D inset;
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr=#F26866,endColorstr=#B60307);
}
.input-warn:hover {
	background: #EE6764;
	background: linear-gradient(top,#F39492,#EE6764);
	background: -moz-linear-gradient(top,#F39492,#EE6764);
	background: -webkit-linear-gradient(top,#F39492,#EE6764);
	text-decoration: none;
	border-color: #ED605C;
	box-shadow: 0 0.5px #E34A4D inset;
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr=#F39492,endColorstr=#EE6764);
}
.input-warn:active {
	background: #B60307;
	background: linear-gradient(bottom,#F26866,#B60307);
	background: -moz-linear-gradient(bottom,#F26866,#B60307);
	background: -webkit-linear-gradient(bottom,#F26866,#B60307);
	text-decoration: none;
	box-shadow: 0 1px #E34A4D inset;
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr=#B60307,endColorstr=#F26866);
}
.input-green {
	background: #83c74c;
	background: linear-gradient(top,#83c74c,#6eb337);
	background: -moz-linear-gradient(top,#83c74c,#6eb337);
	background: -webkit-linear-gradient(top,#83c74c,#6eb337);
	border-color: #5da127;
	color: #FFFFFF!important;
	box-shadow: 0 1px #83c84c inset;
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0, startColorstr=#83c74c, endColorstr=#6eb337);
}
.input-green:hover {
	background: #8cce58;
	background: linear-gradient(top,#a0e26c,#8cce58);
	background: -moz-linear-gradient(top,#a0e26c,#8cce58);
	background: -webkit-linear-gradient(top,#a0e26c,#8cce58);
	text-decoration: none;
	border-color: #71A745;
	box-shadow: 0 0.5px #94da5d inset;
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0, startColorstr=#a0e26c, endColorstr=#8cce58);
}
.input-green:active {
	background: #6EB337;
	background: linear-gradient(bottom,#83C74C,#6EB337);
	background: -moz-linear-gradient(bottom,#83C74C,#6EB337);
	background: -webkit-linear-gradient(bottom,#83C74C,#6EB337);
	text-decoration: none;
	box-shadow: 0 1px #6DA442 inset;
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0, startColorstr=#6EB337, endColorstr=#83C74C);
}
a.input-button,a.input-submit,a.input-green,a.input-gray,a.input-cancel,a.input-disable,a.input-warn {
	display: inline-block;
	min-width: 38px!important;
	height: 28px;
	line-height: 28px;
	text-decoration: none;
	color: #333333;
}
a.input-button,a.input-submit,a.input-green,
.input-button:link, .input-submit:link, .input-green:link,
.input-button:visited, .input-submit:visited, .input-green:visited {
	color: #FFFFFF;
}
.input-disable,.input-disable:hover,.input-disable:active,a.input-disable,a.input-disable:hover,a.input-disable:active {
	background: #F2F0F0;
	border-color: #CCCCCC;
	color: #B5B5B5!important;
	cursor: default;
	box-shadow: none;
	background: linear-gradient(top, #F0F0F0, #EBEBEB);
	background: -moz-linear-gradient(top, #F0F0F0, #EBEBEB);
	background: -webkit-linear-gradient(top, #F0F0F0, #EBEBEB);
	text-decoration: none;
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0, startColorstr=#F0F0F0, endColorstr=#EBEBEB);
}
input[readonly], input[readonly]:focus, input[readonly]:hover {
	background-color: #F9F9F9;
	cursor: default;
	box-shadow: none;
}
input[type=radio],input[type=checkbox] {
	vertical-align: middle;
}
</style>
</head>
<body>
<jsp:include page="/jsp2.0/user/loginHead.jsp" />
<div style="width: 1360px;height: 480px; margin-top: 20px;margin-bottom: -40px">
		<div style="width: 1000px;height: 480px;border: solid 1px #CCCCCC;margin-left: 180px;">
	<input type="hidden" id="blank" name="blank" value="${blank}"/><!-- 标记是新打开的标签页面 -->
	<div class="prompt-dialog" style="margin-top:97px;">
		<div class="wrapper">
			<div class="prompt-section">
				<div class="prompt-error">
					<div class="face-failure">
						${LANG['outer.conf.join.qccode.error.url'] }
					</div>
				</div>		
			</div>
		</div>
	</div>
 </div>
</div>
<div id="footer">
		<div class="wrapper">
			<p>${LANG['website.user.footer.info.rightreserved']}</p>
       	 	<p>${LANG['website.user.footer.info.hotline']}</p>
		</div>
	</div>

</body>
</html>
