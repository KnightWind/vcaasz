<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-CN">
<head>
<meta charset="utf-8" />
<title>${siteBase.siteName} - ${LANG['website.common.index.title.base.1'] }</title>
<!--[if lt IE 9]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
<!--[if lt IE 9]><script src="/assets/js/lib/html5.js"></script><![endif]-->
<!--[if lt IE 7]><script type="text/javascript" src="/assets/js/lib/DD_belatedPNG_0.0.8a.js"></script><![endif]-->
<link type="image/x-icon" rel="shortcut icon" href="/assets/favicon.ico" />
<link rel="stylesheet" type="text/css" href="/assets/css/module/jquery-ui-1.9.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="/assets/css/base.css" />
<link type="text/css" rel="stylesheet" href="/assets/css/apps/dialog.css?va=00" />
<link type="text/css" rel="stylesheet" href="/assets/css/apps/biz.validate.css" />
</head>
<body>
<div id="showStepDiv">
</div>
	<div class="meeting-dialog">
		<!-- 预约会议第一步   -->  
		<div class="step1-panel valid-group">
			<div class="step1-header">
				<h3 class="title">
				<i class="icon icon-meeting"></i><span class="confOperate">${LANG['website.user.conf.personal.meetingroom.information']}</span><!-- <span class="step fix-png"> -->
				</h3>
				<a href="javascript:closeDialog();" title="${LANG['website.common.option.close']}" class="close"></a>
			</div>
			<form id="scheduleForm1" method="post">
			<div class="step-content">
				<h4 class="title">${LANG['website.user.create.reser.page.step.one.title']}</h4>
				<div class="form-panel">
						<div class="form-item">
							<label class="title">${LANG['website.user.create.reser.page.confname']}${LANG['website.common.symbol.colon']}</label>
							<div class="widget">
								<div style="display:none" id="confNameDiv">${conf.confName}</div>
								<input id="confName" name="confName" type="text" class="input-text" placeholder="${LANG['website.user.create.reser.page.confname']}"  value="${conf.confName}"/> 
								<span class="form-required"></span> 
<%-- 								<span class="tips">1-32个任意字符</span> --%>
								<span class="form-tip"><span id="confNameTip"></span></span>
							</div>
						</div>
						<div class="form-item">
							<label class="title">${LANG['website.user.create.reser.page.public.password.set']}${LANG['website.common.symbol.colon']}</label>
							<div class="widget">
								<input value="${conf.hostKey}" id="hostKey" name="hostKey" type="text" class="input-text" placeholder="${LANG['website.user.create.reser.page.public.password']}" />
<%--								<span class="form-required"></span> --%>
 									<span class="tips">${LANG['website.user.create.reser.js.message.number.length12']}
<%--									<span class="form-tip"><span id="hostKeyTip"></span></span>--%>
									</span>
							</div>
						</div>
						 
						<!-- 开始时间 -->
						<!-- 持续时间 -->
						<!-- 会议缺省设置 -->
			<div class="form-item">
				<label class="title">${LANG['website.user.view.conf.conf.preHostor'] }${LANG['website.common.symbol.colon'] }</label>
				<div class="widget">
					<input id="compereType" type="radio" name="optionJbh" value="0" <c:if test="${conf.optionJbh eq 0 }">checked="checked"</c:if> />
					<label for="compereType" class="for-label">${LANG['website.user.view.conf.conf.preHostor.no'] }</label>

					<input id="freeType" type="radio" name="optionJbh" value="1" <c:if test="${conf.optionJbh eq 1 }">checked="checked"</c:if> />
					<label for="freeType" class="for-label">${LANG['website.user.view.conf.conf.preHostor.yes'] }</label>
				</div>
			</div>
			<div class="form-item">
				<label class="title">${LANG['website.user.view.conf.confModel'] }${LANG['website.common.symbol.colon'] }</label>
				<div class="widget">
					 <select id="optionStartType" name="optionStartType">
					 	<option  value="1" <c:if test="${conf.optionStartType eq 1 }">selected="selected"</c:if> >${LANG['website.user.create.reser.param.advanced.func.videohare'] }</option>
					 	<option value="2" <c:if test="${conf.optionStartType eq 2 }">selected="selected"</c:if>>${LANG['com.bizconf.vcaasz.entity.ConfBase.clientConfig.2'] }</option>
					 </select>
				</div>
			</div>
			<!-- 会议描述 -->
			<div class="form-item describe-widget">
				<label class="title">${LANG['website.user.create.reser.conf.desc']}${LANG['website.common.symbol.colon']}</label>
				<div class="widget">
					<textarea id="confDesc" class="input-area" placeholder="${LANG['website.user.create.reser.conf.desc']}">${conf.confDesc}</textarea>
					<span class="count"><span id="nowCountSpan">0</span>/256</span>
				</div>
			</div>
				</div>
			</div>
				<div class="form-buttons">
					<input id="submit1" page-index="1" type="submit" class="button input-gray next-button" value="${LANG['system.ok']}">
					<a class="button anchor-cancel">${LANG['website.common.option.cancel']}</a>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="/assets/js/lib/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="/assets/js/lib/jquery-ui-1.9.2.custom.js"></script>
	<script type="text/javascript" src="/assets/js/lib/jquery-ui-i18n.min.js"></script>
	<script type="text/javascript" src="/assets/js/lib/formValidator-4.1.3.js"></script>
	<script type="text/javascript" src="/assets/js/lib/formValidatorRegex.js"></script>
	<script type="text/javascript">
	var SCHEDULE_CONSTANT={
			confname_empty:"${LANG['website.user.create.reser.js.message.confname.empty']}",//请输入会议主题！
			confname_length:"${LANG['website.user.create.reser.js.message.confname.length']}",//主题长度为1~32个任意字符！
			hostpass_empty:"${LANG['website.user.create.reser.js.message.hostpass']}",//请输入主持人密码！
			hostpass_number:"${LANG['website.user.create.reser.js.message.hostpass.neednumber']}",//主持人密码必须是数字！
			hostpass_length:"${LANG['website.user.create.reser.js.message.hostpass.length']}",//主持人密码长度为6~12个字符！
			confpass_empty:"${LANG['website.user.create.reser.js.message.confpass.empty']}",//请输入会议密码！
			confpass_length:"${LANG['website.user.create.reser.js.message.confpass.length']}",//会议密码长度为6~16个字符！
			confpass_confirm_empty:"${LANG['website.user.create.reser.js.message.confpass.confirm.empty']}",//请输入确认密码！
			confpass_confirm_length:"${LANG['website.user.create.reser.js.message.confpass.confirm.length']}",//确认密码的长度为6~16个字符！
			confpass_confirm_diff:"${LANG['website.user.create.reser.js.message.confpass.confirm.diff']}",//两次密码不一致！
			confdesc_length:"${LANG['website.user.create.reser.js.message.confdesc.length']}",//会议描述的长度为1~150个字符！
			interval_days:"${LANG['website.user.create.reser.js.message.interval.days']}",//请输入间隔天数！
			interval_days_integer:"${LANG['website.user.create.reser.js.message.interval.days.integer']}",//间隔天数必须是整数
			interval_days_soap:"${LANG['website.user.create.reser.js.message.interval.days.soap']}",//间隔天数范围最少为一天, 最多31天
			least_week:"${LANG['website.user.create.reser.js.message.least.oneweek']}",//请至少选择一个星期
			loop_date_error:"${LANG['website.user.create.reser.js.message.loop.date.error']}",//循环的日期不正确
			start_cantless_now:"${LANG['website.user.create.reser.js.message.startdate.cantless.now']}",//开始时间不能早于当前时间
			end_cantless_start:"${LANG['website.user.create.reser.js.message.enddate.cantless.startdate']}",//结束时间不能早于开始时间
			conf_least_five:"${LANG['website.user.create.reser.js.message.conf.least.five']}",//会议时长至少为5分钟
			duration_least_five:"${LANG['website.user.create.reser.js.message.duration.least.five']}",//持续时间最少5分钟
			aheadtime:"${LANG['website.user.create.reser.js.message.aheadtime']}",//请输入会议提前时间
			aheadtime_integer:"${LANG['website.user.create.reser.js.message.aheadtime.integer']}",//会议提前时间必须是整数
			aheadtime_soap:"${LANG['website.user.create.reser.js.message.aheadtime.soap']}",//会议提前时间范围为5~60分钟
			aheadtime_scope:"${LANG['website.user.create.reser.js.message.aheadtime.scope']}",
			minute_name:"${LANG['website.common.minute.name']}",//分钟
			hour_name:"${LANG['website.common.hour.name']}",//小时
			cycle_date_error:"${LANG['website.user.create.reser.js.message.cycle.date.error']}",//日循环会议
			cycle_daily:"${LANG['website.user.create.reser.js.message.cycle.daily']}",//日循环会议
			cycle_weekly:"${LANG['website.user.create.reser.js.message.cycle.weekly']}",//周循环会议
			cycle_monthly:"${LANG['website.user.create.reser.js.message.cycle.monthly']}",//月循环会议
			cycle_none:"${LANG['website.user.create.reser.js.message.cycle.none']}",//非周期循环会议
			repeat_desc:"${LANG['website.user.create.reser.js.message.repeat.desc']}",//重复%s次后结束
			repeat_range_noenddate:"${LANG['website.user.create.reser.cycle.repeat.range.no.enddate']}",//无结束日期
			end_time:"${LANG['website.user.create.reser.conf.end.time']}",//结束日期
			funcs_phone:"${LANG['website.user.create.reser.js.message.funcs.phone']}",//电话功能
			funcs_video:"${LANG['website.user.create.reser.js.message.funcs.video']}",//视频功能
			funcs_phoneandvideo:"${LANG['website.user.create.reser.js.message.funcs.phoneandvideo']}",//视频功能、电话功能
			audio_net:"${LANG['website.user.create.reser.js.message.audio.net']}",//网络语音
			audio_netandphone:"${LANG['website.user.create.reser.js.message.audio.netandphone']}",//网络语音+电话语音
			modify_succeed:"${LANG['website.user.create.reser.js.message.modify.succeed']}",//修改会议成功
			create_info:"${LANG['website.user.create.reser.js.message.create.info']}",//正在创建会议, 请稍等...
			recreate_succeed:"${LANG['website.user.create.reser.js.message.recreate.succeed']}",//重新创建会议成功
			recreate_info:"${LANG['website.user.create.reser.js.message.recreate.info']}",//正在重新创建会议, 请稍等...
			modify_info:"${LANG['website.user.create.reser.js.message.modify.info']}",//正在修改会议, 请稍等...
			everychar_length32:"${LANG['website.user.create.reser.js.message.everychar.length32']}",//1-32个任意字符
			confname_unblank:"${LANG['website.user.create.reser.js.message.confname.unblank']}",//会议主题两边不能有空符号
			number_length12:"${LANG['website.user.create.reser.js.message.number.length12']}",//6-12个数字
			hostpass_unblank:"${LANG['website.user.create.reser.js.message.hostpass.unblank']}",//主持人密码两边不能有空符号
			everychar_length16:"${LANG['website.user.create.reser.js.message.everychar.length16']}",//1-16个任意字符
			pass_confirm_diff:"${LANG['website.user.create.reser.js.message.pass.confirm.diff']}",//2次密码不一致,请确认
			duration_zero:"${LANG['website.user.create.reser.js.message.duration.cant.zero']}",//会议时长不能为0
			interval_unblank:"${LANG['website.user.create.reser.js.message.interval.unblank']}",//间隔天数两边不能有空符号
			interval_error:"${LANG['website.user.create.reser.js.message.interval.inputerror']}",//请输入正确的间隔天数
			repeat_times:"${LANG['website.user.create.reser.js.message.repeat.times']}",//请输入正确的重复次数间
			ahead_time_error:"${LANG['website.user.create.reser.js.message.aheat.times.error']}"	//请输入正确的会议提前时间
		};
	
	jQuery(function($) {
		//原来的第一步的验证
		 $.formValidator.initConfig({
			formID : "scheduleForm1",
			debug : false,
			onSuccess : function() {
				updatePMI();
			},
			onError : function() {

			}
		}); 
		
		$("#confName").formValidator({
			onShow : SCHEDULE_CONSTANT.everychar_length32,
			onFocus : SCHEDULE_CONSTANT.everychar_length32,
			onCorrect : ""
		}).inputValidator({
			min : 1,
			max : 128,
			empty : {
				leftEmpty : false,
				rightEmpty : false,
				emptyError : SCHEDULE_CONSTANT.confname_unblank
			},
			onError : SCHEDULE_CONSTANT.everychar_length32
		});
		
		$("#hostKey").formValidator({		
			empty:true,
			onShow : SCHEDULE_CONSTANT.number_length12,
			onFocus : SCHEDULE_CONSTANT.number_length12,
			onCorrect : ""
		}).inputValidator({
			min : 6,
			max : 12,
			onError : SCHEDULE_CONSTANT.number_length12
		}).regexValidator({
			regExp : "hostkey",
			dataType : "enum",
			onError : SCHEDULE_CONSTANT.number_length12
		});
		
		$("#confDesc").formValidator({
			empty : true,
			onShow : "",
			onFocus : "",
			onCorrect : "",
			onEmpty : ""
		}).inputValidator({
			min : 1,
			max : 256,
			onError : ""
		});
	});
	
	function updatePMI(){
		var conf = new Object();
		conf.id = "${conf.id}";
		conf.confName = $("#confName").val();
		conf.hostKey = $("#hostKey").val();
		conf.confDesc = $("#confDesc").val();
		conf.optionJbh = $("input[name=optionJbh]:checked").val();
		conf.optionStartType = $("#optionStartType").val();
		$.ajax({
	      	type: "POST",
	      	url:"/user/conf/updatePmi",
	      	data:conf,
	      	dataType:"json",
	      	async:false,
	      	success:function(data){
	      		// parent.success
	      		parent.successDialog("${LANG['bizconf.jsp.admin.siteBase_info.res5']}");
	      		closeDialog();
	      	},
	        error:function(XMLHttpRequest, textStatus, errorThrown) {
	        	parent.errorDialog("${LANG['bizconf.jsp.conf.personal.updmeetingroom.failure']}${LANG['website.common.symbol.exmark']}");
	        }
		});  
	}
	
	
	function closeDialog() {
		parent.$("#bookMeeting").trigger("closeDialog");
	}
	
	
	document.ready(function(){
			$("#confName").val("${conf.confName}")
			$("#hostKey").val("${conf.hostKey}")	
			
	})
	</script>
</body>
</html>