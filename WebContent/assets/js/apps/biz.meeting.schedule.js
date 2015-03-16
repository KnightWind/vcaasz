/*=======================================*/
/*	Alan Liu                             */
/*	email:mingliu@bizconf.cn	         */
/*	2013-07-23                           */
/*=======================================*/

function closeDialog() {
	parent.$("#bookMeeting").trigger("closeDialog");
}
function checkTimeInfo() {
	var repeat = $('input:radio[name="repeat"]:checked').val();
	if(repeat=="1") {
		//定期模式
		var cycleType = $('input:radio[name="ontime"]:checked').val();
		var cycleValue = 0;
		switch (cycleType) {
			case "1":
				//定期模式  按天
				var cycleDayValue = $("#cycleDayFlag").val();
				cycleValue = cycleDayValue;
				break;
			case "2":
				//定期模式  按周
				var chk_value =[];    
				$('input[name="weekday"]:checked').each(function(){    
				   chk_value.push($(this).val());    
				});
				cycleValue = chk_value.toString();
				break;
			default:
				//定期模式  按月
				var monthFlag = $('input:radio[name="month-type"]:checked').val();
				if(monthFlag=="1") {
					var cycleDayValue = $("#eachMonthDay").val();
					cycleValue = cycleDayValue;
				} else {
					var week = $("#weekFlag").val();
					var weekDay = $("#weekDay").val();
					cycleValue = week+";"+weekDay;
				}
				break;
		}
		
		//重复范围
		var repeatScope = $('input:radio[name="repeatScope"]:checked').val();
		if (repeatScope=="3") {
			var startTime = $("#startTime").val();
			var endTime = $("#endTimeForEver").val();
			var startTimeH = $("#startTimeH").val();
			var startTimeM = $("#startTimeM").val();
			var startDateStr = startTime + " " + startTimeH + ":" + startTimeM + ":00";
			var endDateStr = endTime + " " + startTimeH + ":" + startTimeM + ":00";;
			var dateList = getDateListFromCycleScope(startDateStr, endDateStr, cycleType, cycleValue, false);
			if(dateList==null || dateList.length==0){
				///*"循环的日期不正确!"*/
				parent.errorDialog(SCHEDULE_CONSTANT.cycle_date_error, function() {
					
				});
				return false;								
			}
		}
		
	} 
	return true;
}
//创建会议完成后返回会议信息到成功页面
function createMeetSuccess(result) {
	var confBase = result.confBase[0];
	var frame = parent.$("#bookMeeting");
	frame.data("data", result);
	var confCycle = null;
	if(result.confCycle){
		confCycle = result.confCycle[0];	
	}
	$("#confIdForEmail").val(confBase.id);
	
	if(confBase.confName.length > 20){
		confBase.confName = confBase.confName.substr(0,19)+"....";
	}
	$("#confTitle").text(confBase.confName);   
	$("#confTitle").attr("title",confBase.confName);//创建完成返回页面
	$("#confStartTime").text(confBase.startTime);   
	
	//创建会议返回时区
	$("#confTimeZoneDesc").text("("+result.timeZoneDesc+SCHEDULE_CONSTANT.zone_time+")");   
	
	$("#confZoomId").text(confBase.confZoomId);
	if(confBase.hostKey && confBase.hostKey!=""){
		$("#confpwd").text(confBase.hostKey);
	}else{
		$("#confpwd").text(SCHEDULE_CONSTANT.meeting_pass_no);
	}
	
	//H323/SIP匹配码
	if(confBase.phonePass && confBase.phonePass!=""){
		$("#pairingCodeSIP").text(confBase.phonePass);
	}else{
		$("#pairingCodeSIP").text(SCHEDULE_CONSTANT.meeting_pass_no);
	}
	
	if(confBase.optionJbh == 1){
		$("#optionjbh").text(SCHEDULE_CONSTANT.meeting_jbh_yes);
	}else{
		$("#optionjbh").text(SCHEDULE_CONSTANT.meeting_jbh_no);
	}
	if(confBase.optionStartType == 1){
		$("#conffuntype").text(SCHEDULE_CONSTANT.video_option_on);
	}else{
		$("#conffuntype").text(SCHEDULE_CONSTANT.video_option_off);
	}
	
	var duraH = parseInt(confBase.duration/60);
	var duraM = confBase.duration%60;
	var duraString = "";
	if(duraM > 1){
		duraString = duraM + " " + SCHEDULE_CONSTANT.minute_name;
	}else if(duraM == 1){
		duraString = duraM + " " + SCHEDULE_CONSTANT.minute_name;
	}
	
	if(duraH > 1){
		duraString = duraH + " " + SCHEDULE_CONSTANT.hour_name+ " " +duraString; 
	}else if(duraH == 1){
		duraString = duraH + " " + SCHEDULE_CONSTANT.hour_name + " " +duraString; 
	}
	$("#confDuration").text(duraString);    //会议时长 ：1小时30分
	if(confBase.permanentConf && confBase.permanentConf=="1"){
		$("#durationConsole").hide();
		$("#endTimeConsole").show();
		$("#confEndTimeConsole").text(confBase.endTime);
	}
	
	//定期模式
	var cycleType = confCycle.cycleType;
	var infiniteFlag = confCycle.infiniteFlag;
	var repeatCount = confCycle.repeatCount;
	var cycleEndDate = confCycle.endDate;
	//重复范围
	var repeatScope = "";
	var cycleOption = 1;
	if(cycleType == 1){
		cycleType = SCHEDULE_CONSTANT.cycle_daily;    //定期模式:日循环会议
	}else if(cycleType == 2){
		cycleType = SCHEDULE_CONSTANT.cycle_weekly;    //定期模式:周循环会议
	}else if(cycleType == 3){
		cycleType =  SCHEDULE_CONSTANT.cycle_monthly;   //定期模式:月循环会议
	}else{
		cycleOption = 0;
		cycleType = SCHEDULE_CONSTANT.cycle_none;	   //定期模式:非周期循环会议
	}
	if(repeatCount > 0){
		repeatScope = SCHEDULE_CONSTANT.repeat_desc.replace("%s",repeatCount);
	}else if(infiniteFlag == 1){
		repeatScope = SCHEDULE_CONSTANT.repeat_range_noenddate;
	}else if(cycleEndDate != ""){
		repeatScope = SCHEDULE_CONSTANT.end_time + cycleEndDate.parseDate().format("yyyy-MM-dd");
	}
	if(cycleOption == 0){
		$("#confPatternTR").hide();     //隐藏定期模式
		$("#confRepeatTR").hide();     //隐藏重复范围
	}else{
		$("#confPattern").text(cycleType);     //定期模式
		$("#confRepeatScope").text(repeatScope);     //重复范围
	}
	//语音模式
	
	//会议类型    
	var confType = confBase.confType;
	if(confBase.confType == 1){
		confType = SCHEDULE_CONSTANT.funcs_phone;       //会议类型 :电话功能    
	}
	if(confBase.confType == 2){
		confType = SCHEDULE_CONSTANT.funcs_video;  	//会议类型 :视频功能   
	}
	if(confBase.confType == 3){
		confType = SCHEDULE_CONSTANT.funcs_phoneandvideo;         //会议类型 :视频功能、电话功能  
	}
	$("#confType").text(confType);  
	if(confBase.confType == 0){
		$("#confTypeTR").hide();	 //隐藏会议类型
	}
	$("#confVoicePattern").text(SCHEDULE_CONSTANT.audio_net);
	if(confBase.confType == 1 || confBase.confType == 3){   //语音模式为：网络语音+电话语音
		$("#confVoicePattern").text(SCHEDULE_CONSTANT.audio_netandphone);
	}
	$(".valid-group").eq(3).show().siblings().hide();
}


//修改会议完成后返回会议信息到成功页面
function updateMeetSuccess(result) {
	$(".face-success").text(SCHEDULE_CONSTANT.modify_succeed);
	var confBase = result.confBase[0];
	var frame = parent.$("#bookMeeting");
	frame.data("data", result);
	var confCycle = null;
	if(result.confCycle){
		confCycle = result.confCycle[0];	
	}
	$("#confIdForEmail").val(confBase.id);
	if(confBase.confName.length > 20){
		confBase.confName = confBase.confName.substr(0,19);
	}
	$("#confTitle").text(confBase.confName+"...."); 
	$("#confTitle").attr("title",confBase.confName);//修改完成返回页面
	var confStartTime = confBase.startTime;
	$("#confStartTime").text(confStartTime.parseDate().format("yyyy-MM-dd hh:mm:ss"));
	
	//创建会议返回时区
	$("#confTimeZoneDesc").text("("+result.timeZoneDesc+SCHEDULE_CONSTANT.zone_time+")"); 
	
	$("#confZoomId").text(confBase.confZoomId);
	if(confBase.hostKey){
		$("#confpwd").text(confBase.hostKey);
	}else{
		$("#confpwd").text(SCHEDULE_CONSTANT.meeting_pass_no);
	}
	//H323 匹配码
	if(confBase.phonePass){
		$("#pairingCodeSIP").text(confBase.phonePass);
	}else{
		$("#pairingCodeSIP").text(SCHEDULE_CONSTANT.meeting_pass_no);
	}
	
	if(confBase.optionJbh == 1){
		$("#optionjbh").text(SCHEDULE_CONSTANT.meeting_jbh_yes);
	}else{
		$("#optionjbh").text(SCHEDULE_CONSTANT.meeting_jbh_no);
	}
	if(confBase.optionStartType == 1){
		$("#conffuntype").text(SCHEDULE_CONSTANT.video_option_on);
	}else{
		$("#conffuntype").text(SCHEDULE_CONSTANT.video_option_off);
	}
	if(confCycle != null){
		//定期模式
		var cycleType = confCycle.cycleType;
		var infiniteFlag = confCycle.infiniteFlag;
		var repeatCount = confCycle.repeatCount;
		var cycleEndDate = confCycle.endDate;
		//重复范围
		var repeatScope = "";
		var cycleOption = 1;
		if(cycleType == 1){
			cycleType = SCHEDULE_CONSTANT.cycle_daily;    //定期模式:日循环会议
		}else if(cycleType == 2){
			cycleType = SCHEDULE_CONSTANT.cycle_weekly;    //定期模式:周循环会议
		}else if(cycleType == 3){
			cycleType =  SCHEDULE_CONSTANT.cycle_monthly;   //定期模式:月循环会议
		}else{
			cycleOption = 0;
			cycleType = SCHEDULE_CONSTANT.cycle_none;	   //定期模式:非周期循环会议
		}
		if(repeatCount > 0){
			repeatScope = SCHEDULE_CONSTANT.repeat_desc.replace("%s",repeatCount);
		}else if(infiniteFlag == 1){
			repeatScope = SCHEDULE_CONSTANT.repeat_range_noenddate;
		}else if(cycleEndDate != ""){
			repeatScope = SCHEDULE_CONSTANT.end_time + cycleEndDate.parseDate().format("yyyy-MM-dd");
		}
		if(cycleOption == 0){
			$("#confPatternTR").hide();     //隐藏定期模式
			$("#confRepeatTR").hide();     //隐藏重复范围
		}else{
			$("#confPattern").text(cycleType);     //定期模式
			$("#confRepeatScope").text(repeatScope);     //重复范围
		}
	}else{
		$("#confPatternTR").hide();     //隐藏定期模式
		$("#confRepeatTR").hide();     //隐藏重复范围
	}
	
	var confType = confBase.confType;
	if(confBase.confType == 1){
		confType = SCHEDULE_CONSTANT.funcs_phone;       //会议类型 :电话功能    
	}
	if(confBase.confType == 2){
		confType = SCHEDULE_CONSTANT.funcs_video;  	//会议类型 :视频功能   
	}
	if(confBase.confType == 3){
		confType = SCHEDULE_CONSTANT.funcs_phoneandvideo;         //会议类型 :视频功能、电话功能  
	}
	$("#confType").text(confType);         //会议功能    
	if(confBase.confType == 0){
		$("#confTypeTR").hide();	 //隐藏会议类型
//		confType = validString.pageRequired.firConfType;       //会议功能 :数据会议功能   
	}
	$("#confVoicePattern").text(SCHEDULE_CONSTANT.audio_net);
	if(confBase.confType == 1 || confBase.confType == 3){   //语音模式为：网络语音+电话语音
		$("#confVoicePattern").text(SCHEDULE_CONSTANT.audio_netandphone);
	}
	$("#maxUserSpan").text(confBase.maxUser);         //最大参会人数
	$("#userSecureSpan").text(confBase.userSecure); 		//与会者安全会议号
	$("#confDescSpan").text(confBase.confDesc); 		//会议描述
	var duraH = parseInt(confBase.duration/60);
	var duraM = confBase.duration%60;
	var duraString = "";
	if(duraM > 1){
		duraString = duraM + " "  + SCHEDULE_CONSTANT.minute_name;
	}else if(duraM == 1){
		duraString = duraM + " "  + SCHEDULE_CONSTANT.minute_name ;
	}
	
	if(duraH > 1){
		duraString = duraH + " " + SCHEDULE_CONSTANT.hour_name + " " +duraString; 
	}else if(duraH == 1){
		duraString = duraH + " " + SCHEDULE_CONSTANT.hour_name  + " " +duraString; 
	}
	$("#confDuration").text(duraString);    //会议时长 ：1小时30分
	if(confBase.permanentConf && confBase.permanentConf=="1"){
		$("#durationConsole").hide();
		$("#endTimeConsole").show();
		$("#confEndTimeConsole").text(confBase.endTime);
	}
	$(".valid-group").eq(3).show().siblings().hide();
}
//重新创建会议完成后返回会议信息到成功页面
function reCreateConfSuccess(result) {
	$(".face-success").text(SCHEDULE_CONSTANT.recreate_succeed);
	var confBase = result.confBase[0];
	var frame = parent.$("#bookMeeting");
	frame.data("data", result);
	$("#confIdForEmail").val(confBase.id);
	$("#confTitle").text(confBase.confName);   //会议主题
	$("#confStartTime").text(confBase.startTime);
	//创建会议返回时区
	$("#confTimeZoneDesc").text("("+result.timeZoneDesc+SCHEDULE_CONSTANT.zone_time+")");
	
	$("#confZoomId").text(confBase.confZoomId);
	if(confBase.hostKey){
		$("#confpwd").text(confBase.hostKey);
	}else{
		$("#confpwd").text(SCHEDULE_CONSTANT.meeting_pass_no);
	}
	if(confBase.optionJbh == 1){
		$("#optionjbh").text(SCHEDULE_CONSTANT.meeting_type_yes);
	}else{
		$("#optionjbh").text(SCHEDULE_CONSTANT.meeting_type_no);
	}
	if(confBase.optionStartType == 1){
		$("#conffuntype").text(SCHEDULE_CONSTANT.meeting_type_video);
	}else{
		$("#conffuntype").text(SCHEDULE_CONSTANT.meeting_type_share);
	}
	$(".cycleTypeTR").hide();     //周期类型
	$(".cycleTimeTRD").hide();	 //周期时间
	$("#confPatternTR").hide();
	$("#confRepeatTR").hide();
	var confType = confBase.confType;
	if(confBase.confType == 1){
		confType = SCHEDULE_CONSTANT.funcs_phone;       //会议类型 :电话功能    
	}
	if(confBase.confType == 2){
		confType = SCHEDULE_CONSTANT.funcs_video;  	//会议类型 :视频功能   
	}
	if(confBase.confType == 3){
		confType = SCHEDULE_CONSTANT.funcs_phoneandvideo;         //会议类型 :视频功能、电话功能  
	}
	$("#confType").text(confType);         //会议功能    
	if(confBase.confType == 0){
		$(".confTypeTR").hide();	 //隐藏会议类型
//		confType = validString.pageRequired.firConfType;       //会议功能 :数据会议功能   
	}
	$("#confVoicePattern").text(SCHEDULE_CONSTANT.audio_net);
	if(confType==1 || confType==3){   //语音模式为：网络语音+电话语音
		$("#confVoicePattern").text(SCHEDULE_CONSTANT.audio_netandphone);
	}
	$("#maxUserSpan").text(confBase.maxUser);         //最大参会人数
	$("#userSecureSpan").text(confBase.userSecure); 		//与会者安全会议号
	$("#confDescSpan").text(confBase.confDesc); 		//会议描述
	var duraH = parseInt(confBase.duration/60);
	var duraM = confBase.duration%60;
	var duraString = "";
	if(duraM > 1){
		duraString = duraM + " "  + SCHEDULE_CONSTANT.minute_name;
	}else if(duraM == 1){
		duraString = duraM + " "  + SCHEDULE_CONSTANT.minute_name;
	}
	
	if(duraH > 1){
		duraString = duraH + " " + SCHEDULE_CONSTANT.hour_name + " " +duraString; 
	}else if(duraH == 1){
		duraString = duraH + " " + SCHEDULE_CONSTANT.hour_name + " " +duraString; 
	}
	$("#confDuration").text(duraString);    //会议时长 ：1小时30分
	$(".valid-group").eq(3).show().siblings().hide();
}

/**添加和修改会议*/
function saveOrUpdateMeeting() {
	
	var data = {};
	//基本信息
	data.confName = $("#confName").val();
	//主持人密码
	data.hostKey = $("#hostKey").val();
	
	/*****************************************/
	data.timeZoneSecond =  $("#timeZoneSecond").val();
	var optionJbh = $('input:radio[name="optionJbh"]:checked').val();//周期选项0是不允许,1是允许
	data.optionJbh = optionJbh;
	data.optionStartType = $("#optionStartType").val();
	//2014-07-01 add
	data.timeZoneId = $("#timeZoneId").val();//选择时区id
	data.timeZone = $("#timeZoneId option:selected").attr("timeZone");//选择时区时间
	/*****************************************/
	//语音模式
	data.publicFlag = 2;
	var allowPublic = $('input:radio[name="allowPublic"]:checked').val();
	if(allowPublic=="1"){
		data.publicFlag = 1;
		var publicPass = $('input:radio[name="publicPass"]:checked').val();//$(".passSetCheck").attr("checked");
		if(publicPass && publicPass=="1"){
			data.publicConfPass = $("#confPass").val();	
		}
	} else {
		var assign = $('input:radio[name="confInviterRadio"]:checked').val();
		if(assign && assign=="0"){
			data.publicFlag = 3;
		}
	}
	data.confDesc = $("#confDesc").val();
	
	//时间信息
	var cycleOption = $('input:radio[name="repeat"]:checked').val();//周期选项1是开启,2是关闭
	data.cycleOption = cycleOption;
	var startDateStr=$("#startTime").val()+" "+$("#startTimeH").val()+":"+$("#startTimeM").val()+":00";
	data.startTime = startDateStr.parseDate().format("yyyy-MM-dd hh:mm:ss");
	//持续时间
	var bytype = $('input:radio[name="bytype"]:checked').val();
	if (bytype && bytype=="1") {
		var endDateStr = $("#endTime").val()+" "+$("#endTimeH").val()+":"+$("#endTimeM").val()+":00";
		data.endTime = endDateStr.parseDate().format("yyyy-MM-dd hh:mm:ss"); 
	} else {
		var durationH = parseInt($("#durationH").val(), 10);
		var durationM = parseInt($("#durationM").val(), 10);
		var duration = durationH*60 + durationM; 
		if(duration>0){
			data.duration = duration;
		}
	}
	if(cycleOption=="1"){
		var cycleType = $('input:radio[name="ontime"]:checked').val();
		data.cycleType = cycleType;
		if(cycleType==1){
			//按天
			data.cycleValue = $("#cycleDayFlag").val();
		} else if (cycleType==2) {
			//按周
			var chk_value =[];    
			$('input[name="weekday"]:checked').each(function(){    
			   chk_value.push($(this).val());    
			});
			data.cycleValue = chk_value.toString();
		} else {
			//按月
			var monthFlag = $('input:radio[name="month-type"]:checked').val();
			if(monthFlag==1) {
				data.cycleValue = $("#eachMonthDay").val();
			} else {
				var week = $("#weekFlag").val();
				var weekDay = $("#weekDay").val();
				data.cycleValue = week+";"+weekDay;
			}
		}
		data.beginDate = startDateStr.parseDate().format("yyyy-MM-dd hh:mm:ss");// $("#startTime").val()+" "+$("#startTimeH").val()>0+":"+$("#startTimeM").val()+":00";

		var endDate = $("#endTimeForEver").val();
		var infiniteFlag = $('input:radio[name="repeatScope"]:checked').val();
		//	无限循环会议
		if(infiniteFlag==1){
			data.infiniteFlag = 1;
			endDate = "2100-01-01";
		}
		//	按次数循环
		if(infiniteFlag==2){
			data.repeatCount = $("#repeatCount").val();
			endDate = "2100-01-01";
		}
		
		var endDateStr = endDate +" "+$("#startTimeH").val()+":"+$("#startTimeM").val()+":00";
		data.endTime = endDateStr.parseDate().format("yyyy-MM-dd hh:mm:ss");
		data.endDate = endDateStr.parseDate().format("yyyy-MM-dd hh:mm:ss");//$("#endTime").val()+" "+$("#startTimeH").val()>0+":"+$("#startTimeM").val()+":00";
	}
	//永久会议创建
	var endTypeFlag = $('input:radio[name="bytype"]:checked').val();
	if(endTypeFlag && endTypeFlag=="1"){
		var endDate = $("#endTime").val();
		var endHour = $("#endTimeH").val();
		var endMinute = $("#endTimeM").val();
		var dateStr = endDate+" "+endHour+":"+endMinute+":00";
		data.endTime = dateStr; 
		data.duration = "";
	}
	
	//永久会议修改
	var permanentConf = $('#permanentConf_id').val();
	if(permanentConf && permanentConf=="1"){
		data.permanentConf = permanentConf;
		var endDate = $("#endTime").val();
		var endHour = $("#endTimeH").val();
		var endMinute = $("#endTimeM").val();
		var dateStr = endDate+" "+endHour+":"+endMinute+":00";
		data.endTime = dateStr;
	}
	//会议类型
	var confType=0;
	var isOnlyNetConf=false;
	var isNetAndPhontConf=false;
	var isVideoConf=false;
	if($("input:radio[name=voice]:eq(0)").attr("checked")){
		isOnlyNetConf=true;
	}
	if($("input:radio[name=voice]:eq(1)").attr("checked")){
		isOnlyNetConf=true;
		isNetAndPhontConf=true;
	}
	if($("input:checkbox[name=meetType]:eq(0)").attr("checked")){
		isVideoConf=true;
	}
	//选择网络语音、不选择视频时
	if(isOnlyNetConf && !isNetAndPhontConf && !isVideoConf){
		confType=0;
	}
	//选择网络语音、并且选择视频时
	if(isOnlyNetConf && !isNetAndPhontConf && isVideoConf){
		confType=2;
	}
	//选择网络语音+电话语音、不选择视频时
	if(isOnlyNetConf && isNetAndPhontConf && !isVideoConf){
		confType=1;
	}
	//选择网络语音+电话语音、并且选择视频时
	if(isOnlyNetConf && isNetAndPhontConf && isVideoConf){
		confType=3;
	}
	data.confType=confType;
	
//	var confType_value =[];    
//	$('input[name="meetType"]:checked').each(function(){    
//		confType_value.push($(this).val());    
//	});
//	if(confType_value.length>0){
//		data.confType = confType_value.length==1?confType_value[0]:3;	
//	}
	
	
	//外呼
	if(confType==1 || confType==3) {
		var micStatus = $('input:radio[name="allowCall"]:checked').val();
		if(micStatus==1) {
			data.allowCall = micStatus;
		} else {
			data.allowCall = 0;
		}
	}
	//会议模式
	var confModel = $('input:radio[name="meetMode"]:checked').val();
	if(confModel==1){
		data.confModel = confModel;
	}
	//麦克风状态
	var micStatus = $('input:radio[name="mikeMode"]:checked').val();
	if(micStatus==1) {
		data.micStatus = micStatus;
	}
	//最大音频、视频、视频画质、提前时间
	data.maxVideo = $("#maxVideo").val();
	data.maxAudio = $("#maxAudio").val();
	data.videoType = $('input:radio[name="videoQuality"]:checked').val();
	data.aheadTime = $("#aheadTime").val();
	//参会人权限、会议功能
	$('.extraPermission').each(function(){
		var index = $(this).val();
		var name = $(this).attr("name");
		var checked = $(this).attr("checked");
		if(checked=="checked"){
			data[name] = index;
		}
	});
	$('.clientOption').each(function(){
		var index = $(this).val();
		var name = $(this).attr("name");
		var checked = $(this).attr("checked");
		if(checked=="checked"){
			data[name] = index;
		}
	});

	if(confID){
		data.id = confID; 
		//data.confHwid = confHwid;
		//data.timeZone = timeZone;
		if(cycleID && cycleID!="0"){
			data.cycleId = cycleID;
			if(cycleConfType == 2) {
				//重新创建会议
				app.reCreateConf(data, function(result) {
					if(result && result.status=="1") {
						reCreateConfSuccess(result);
					}else {
						parent.errorDialog(result.message);
					}
				},{message:SCHEDULE_CONSTANT.recreate_info,ui:top.window, pageLoading: "true"});	
			}else if(cycleConfType == 1) {//现在修改周期会议
				//修改循环会议中的所有会议
				app.updateCycleMeeting(data, function(result) {
					if(result && result.status=="1") {
						updateMeetSuccess(result);
					}else {
						parent.errorDialog(result.message);
					}
				},{message:SCHEDULE_CONSTANT.modify_info,ui:top.window, pageLoading: "true"});	
			} else {
				//修改周期会议的本次会议  cycleConfType == 3
				app.updateCycleOneMeeting(data, function(result) {
					if(result && result.status=="1") {
						updateMeetSuccess(result);
					}else {
						parent.errorDialog(result.message);
					}
				},{message:SCHEDULE_CONSTANT.modify_info,ui:top.window, pageLoading: "true"});						
			}
		}else if(cycleConfType == 2) {
			//重新创建会议
			app.reCreateConf(data, function(result) {
				if(result && result.status=="1") {
					reCreateConfSuccess(result);
				}else {
					parent.errorDialog(result.message);
				}
			},{message:SCHEDULE_CONSTANT.recreate_info,ui:top.window, pageLoading: "true"});	
		}else{
			//修改单次会议
			app.updateBookMeeting(data, function(result) {
				if(result && result.status=="1") {
					updateMeetSuccess(result);
				}else {
					parent.errorDialog(result.message);
				}
			},{message:SCHEDULE_CONSTANT.modify_info,ui:top.window, pageLoading: "true"});
		}
	} else {
		//首次创建会议
		app.bookMeeting(data,function(result) {
			if(result && result.status=="1") {
				createMeetSuccess(result);
			}else {
				parent.errorDialog(result.message);
			}
		},{message:SCHEDULE_CONSTANT.create_info,ui:top.window, pageLoading: "true"});		
	}


}
jQuery(function($) {
	timer=window.setInterval(showTimeZone,1000);
	//原来的第一步的验证
	 $.formValidator.initConfig({
		formID : "scheduleForm1",
		debug : false,
		onSuccess : function() {
			$(".valid-group").eq(1).show().siblings().hide();
			//saveOrUpdateMeeting();
		},
		onError : function() {
			//alert("具体错误，请看网页上的提示");
		}
	}); 
	 
	
	//第一步的验证改成直接保存
	/**$.formValidator.initConfig({
		//validatorGroup:"2",
		formID : "scheduleForm1",
		debug : false,
		onSuccess : function() {
			saveOrUpdateMeeting();
		},
		onError:function() {
			//alert("具体错误，请看网页上的提示");
		}
	});*/
	
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
	/**原来密码校验*/
//	$("#hostKey").formValidator({
//		onShow : SCHEDULE_CONSTANT.number_length12,
//		onFocus : SCHEDULE_CONSTANT.number_length12,
//		onCorrect : ""
//	}).inputValidator({
//		min : 6,
//		max : 12,
//		empty : {
//			leftEmpty : false,
//			rightEmpty : false,
//			emptyError :SCHEDULE_CONSTANT.hostpass_unblank
//		},
//		onError : SCHEDULE_CONSTANT.number_length12
//	}).regexValidator({
//		regExp : "hostkey",
//		dataType : "enum",
//		onError : SCHEDULE_CONSTANT.number_length12
//	});
	
	$("#hostKey").formValidator({
		empty : true,
		onShow : SCHEDULE_CONSTANT.number_length12,
		onFocus : SCHEDULE_CONSTANT.number_length12,
		onCorrect : "",
		onEmpty : ""
	}).inputValidator({
		min : 6,
		max : 10,
		onError : SCHEDULE_CONSTANT.number_length12
	}).functionValidator({
		fun:function(val,elem){
			var Val = $("#hostKey").val();
			if(!ValidatorHostKey(Val)){
				return false;
			}
			return true;
		}
	});
	
	$("#confPass").formValidator({
		onShow : SCHEDULE_CONSTANT.everychar_length16,
		onFocus : SCHEDULE_CONSTANT.everychar_length16,
		onCorrect : ""
	}).inputValidator({
		min : 1,
		max : 16,
		onError :SCHEDULE_CONSTANT.everychar_length16
	});
	$("#confirmPass").formValidator({
		onShow : "",
		onFocus : "",
		onCorrect : ""
	}).inputValidator({
		min : 1,
		max : 16,
		onError : SCHEDULE_CONSTANT.everychar_length16
	}).compareValidator({
		desID : "confPass",
		operateor : "=",
		onError : SCHEDULE_CONSTANT.pass_confirm_diff
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
	
	//会议密码默认验证
	var publicPassRadio = $('input:radio[name="publicPass"]:checked').val();
	if(publicPassRadio=="2") {
		$("#confPass").unFormValidator(true);
		$("#confirmPass").unFormValidator(true);
	}
	
	//第二步的验证
	/*
	 $.formValidator.initConfig({
		validatorGroup:"2",
		formID : "scheduleForm2",
		debug : false,
		onSuccess : function() {
			if (checkTimeInfo()) {
				$(".valid-group").eq(2).show().siblings().hide();
			}
		},
		onError : function() {
			//alert("具体错误，请看网页上的提示");
		}
	});
	 */
	//第二步的验证
	$.formValidator.initConfig({
		validatorGroup:"2",
		formID : "scheduleForm2",
		debug : false,
		onSuccess : function() {
			saveOrUpdateMeeting();
		},
		onError : function() {
			//alert("具体错误，请看网页上的提示");
		}
	});
	
	$("#durationH").formValidator({
		validatorGroup:"2",
		tipID: "durationTip",
		onShow : "",
		onFocus : "",
		onCorrect : ""
	}).inputValidator({
		onError : SCHEDULE_CONSTANT.duration_zero
	}).functionValidator({
	    fun:function(val,elem){
	    	var durationH = $("#durationH").val();
			var durationM = $("#durationM").val();
			if (durationH=="0" && durationM=="0") {
			    return SCHEDULE_CONSTANT.duration_zero;
		    }else{
			    return true;
		    }
		}
	});
	
	$("#durationM").formValidator({
		validatorGroup:"2",
		tipID: "durationTip",
		onShow : "",
		onFocus : "",
		onCorrect : ""
	}).inputValidator({
		onError : SCHEDULE_CONSTANT.duration_zero
	}).functionValidator({
	    fun:function(val,elem){
	    	var durationH = $("#durationH").val();
			var durationM = $("#durationM").val();
			if (durationH=="0" && durationM=="0") {
			    return SCHEDULE_CONSTANT.duration_zero;
		    }else{
			    return true;
		    }
		}
	});
	
	$("#startTimeH").formValidator({
		validatorGroup:"2",
		tipID: "startTimeTip",
		onShow : "",
		onFocus : "",
		onCorrect : ""
	}).inputValidator({
		onError : ""
	}).functionValidator({
	    fun:function(val,elem){
	    	var startDateStr = $("#startTime").val()+" "+$("#startTimeH").val()+ ":" +$("#startTimeM").val()+":00";
		    var startTimeValue = startDateStr.parseDate();
		    //周期会议修改开始时间
		    /**/
		    var repeatRadio = $('input:radio[name="repeat"]:checked').val();
		    if( repeatRadio == "1" && serverDate && startTimeValue){
		    	return true;
		    }
		    if (serverDate && startTimeValue && startTimeValue.after(serverDate)) {
		    	return true;
		    }
			return SCHEDULE_CONSTANT.start_cantless_now;//"开始时间不能早于系统当前时间";
		}
	});
	$("#startTimeM").formValidator({
		validatorGroup:"2",
		tipID: "startTimeTip",
		onShow : "",
		onFocus : "",
		onCorrect : ""
	}).inputValidator({
		onError : ""
	}).functionValidator({
	    fun:function(val,elem){
	    	var startDateStr = $("#startTime").val()+" "+$("#startTimeH").val()+ ":" +$("#startTimeM").val()+":00";
		    var startTimeValue = startDateStr.parseDate();
		    //周期会议修改开始时间
		    var repeatRadio = $('input:radio[name="repeat"]:checked').val();
		    if( repeatRadio == "1" && serverDate && startTimeValue){
		    	return true;
		    }
		    if (serverDate && startTimeValue && startTimeValue.after(serverDate)) {
		    	return true;
		    }
			return SCHEDULE_CONSTANT.start_cantless_now;//"开始时间不能早于系统当前时间";
		}
	});
	
	$("#endTimeH").formValidator({
		validatorGroup:"2",
		tipID: "endTimeTip",
		onShow : "",
		onFocus : "",
		onCorrect : ""
	}).inputValidator({
		onError : ""
	}).functionValidator({
	    fun:function(val,elem){
	    	//如果是重复周期  不需要判断
	    	var repeatRadio = $('input:radio[name="repeat"]:checked').val();
	    	if (repeatRadio=="1") {
	    		return true;
	    	}
		    //如果是持续时间 不需要判断
	    	var bytypeRadio = $('input:radio[name="bytype"]:checked').val();
	    	if (bytypeRadio=="2") {
	    		return true;
	    	}
	    	var startDateStr = $("#startTime").val()+" "+$("#startTimeH").val()+ ":" +$("#startTimeM").val()+":00";
		    var startTimeValue = startDateStr.parseDate();
		    var endDateStr = $("#endTime").val()+" "+$("#endTimeH").val()+ ":" +$("#endTimeM").val()+":00";
		    var endTimeValue = endDateStr.parseDate();
		    if (endTimeValue && startTimeValue && endTimeValue.after(startTimeValue)) {
		    	return true;
		    }
			return  SCHEDULE_CONSTANT.end_cantless_start;//"结束时间不能早于开始时间";
		}
	});
	$("#endTimeM").formValidator({
		validatorGroup:"2",
		tipID: "endTimeTip",
		onShow : "",
		onFocus : "",
		onCorrect : ""
	}).inputValidator({
		onError : ""
	}).functionValidator({
	    fun:function(val,elem){
	    	//如果是重复周期  不需要判断
	    	var repeatRadio = $('input:radio[name="repeat"]:checked').val();
	    	if (repeatRadio=="1") {
	    		return true;
	    	}
		    //如果是持续时间 不需要判断
	    	var bytypeRadio = $('input:radio[name="bytype"]:checked').val();
	    	if (bytypeRadio=="2") {
	    		return true;
	    	}
	    	var startDateStr = $("#startTime").val()+" "+$("#startTimeH").val()+ ":" +$("#startTimeM").val()+":00";
		    var startTimeValue = startDateStr.parseDate();
		    var endDateStr = $("#endTime").val()+" "+$("#endTimeH").val()+ ":" +$("#endTimeM").val()+":00";
		    var endTimeValue = endDateStr.parseDate();
		    if (endTimeValue && startTimeValue && endTimeValue.after(startTimeValue)) {
		    	return true;
		    }
			return SCHEDULE_CONSTANT.end_cantless_start;//"结束时间不能早于开始时间";
		}
	});
	$("#cycleDayFlag").formValidator({
		validatorGroup:"2",
		onShow : "",
		onFocus : "",
		onCorrect : ""
	}).inputValidator({
		min : 1,
		max : 31,
		type:"value",
		empty : {
			leftEmpty : false,
			rightEmpty : false,
			emptyError : SCHEDULE_CONSTANT.interval_unblank
		},
		onError : SCHEDULE_CONSTANT.interval_error
	}).regexValidator({
		regExp : "intege1",
		dataType : "enum",
		onError : SCHEDULE_CONSTANT.interval_error
	});
	
	$("#eachMonthDay").formValidator({
		validatorGroup:"2",
		onShow : "",
		onFocus : "",
		onCorrect : ""
	}).inputValidator({
		min : 1,
		max : 31,
		type:"value",
		empty : {
			leftEmpty : false,
			rightEmpty : false,
			emptyError : SCHEDULE_CONSTANT.interval_unblank
		},
		onError : SCHEDULE_CONSTANT.interval_error
	}).regexValidator({
		regExp : "intege1",
		dataType : "enum",
		onError : SCHEDULE_CONSTANT.interval_error
	});
	$("#repeatCount").formValidator({
		validatorGroup:"2",
		onShow : "",
		onFocus : "",
		onCorrect : ""
	}).inputValidator({
		min : 1,
		max : 31,
		type:"value",
		onError : SCHEDULE_CONSTANT.repeat_times
	}).regexValidator({
		regExp : "intege1",
		dataType : "enum",
		onError :  SCHEDULE_CONSTANT.repeat_times
	});
	
	$(":checkbox[name='weekday']").formValidator({
		validatorGroup:"2",
		tipID: "weekdayTip",
		onShow : "",
		onFocus : "",
		onCorrect : ""
	}).inputValidator({
		min : 1,
		onError : SCHEDULE_CONSTANT.least_week
	});
	$("#startTime").formValidator({
		validatorGroup : "2",
		onShow : "",
		onFocus : "",
		onCorrect : ""
	}).functionValidator({
 		    fun:function(val,elem){
 		    	//如果是按持续时间  开始时间默认小于2100-12-12
 		    	var errorMessage = "";
 		    	var endTimeValue = "2100-12-12";
 		    	//如果是重复周期 需要判断开始日期应该小于结束日期
 		    	var repeatRadio = $('input:radio[name="repeat"]:checked').val();
 		    	if (repeatRadio=="1") {
 		    		endTimeValue = $("#endTimeForEver").val();
 		    		errorMessage = SCHEDULE_CONSTANT.start_cantless_now;
 		    	} else {
 		    		//如果是按结束时间 需要判断开始时间应该小于结束时间 
 		    		var bytypeRadio = $('input:radio[name="bytype"]:checked').val();
 		    		if (bytypeRadio=="1") {
 		 		    	endTimeValue = $("#endTime").val();
 		 		    	errorMessage = SCHEDULE_CONSTANT.end_cantless_start;
 		    		}
 		    	}
 		        if(val<=endTimeValue){
 				    return true;
 			    }else{
 				    return errorMessage;
 			    }
 			}
 	});
	$("#endTime").formValidator({
		validatorGroup : "2",
		onShow : "",
		onFocus : "",
		onCorrect : ""
	}).functionValidator({
 		    fun:function(val,elem){
 		    	//如果是重复周期  不需要判断
 		    	var repeatRadio = $('input:radio[name="repeat"]:checked').val();
 		    	if (repeatRadio=="1") {
 		    		return true;
 		    	}
 		    	//如果是持续时间 不需要判断
		    	var bytypeRadio = $('input:radio[name="bytype"]:checked').val();
		    	if (bytypeRadio=="2") {
		    		return true;
		    	}
		    	//如果是按结束时间 结束日期不能早于开始日期
 		    	var startDateStr=$("#startTime").val();
 		    	var startTimeValue = startDateStr.parseDate();
 		    	var endDateValue = val.parseDate();
 		        if((endDateValue && startTimeValue && endDateValue.equalTo(startTimeValue)) || (endDateValue && startTimeValue && endDateValue.after(startTimeValue))){
 				    return true;
 			    }
 		        return SCHEDULE_CONSTANT.end_cantless_start;
 			}
 	});
	
	$("#endTimeForEver").formValidator({
		validatorGroup : "2",
		onShow : "",
		onFocus : "",
		onCorrect : ""
	}).functionValidator({
 		    fun:function(val,elem){
 		    	//如果不是重复周期  不需要判断
 		    	var repeatRadio = $('input:radio[name="repeat"]:checked').val();
 		    	if (repeatRadio=="2") {
 		    		return true;
 		    	}
 		    	//如果是重复周期  结束日期不能早于开始日期
 		    	var startTimeStr = $("#startTime").val();
 		    	var startTimeValue = startTimeStr.parseDate();
 		    	var endTimeValue = val.parseDate();
 		        if((endTimeValue && startTimeValue && endTimeValue.equalTo(startTimeValue)) || (endTimeValue && startTimeValue && endTimeValue.after(startTimeValue))){
 				    return true;
 			    }
 		        return SCHEDULE_CONSTANT.end_cantless_start;
 			}
 	});
	//定期模式默认验证
	var ontimeRadio = $('input:radio[name="ontime"]:checked').val();
	if (ontimeRadio=="1") {
		$("#cycleDayFlag").unFormValidator(false);
		$(":checkbox[name='weekday']").unFormValidator(true);
		$("#eachMonthDay").unFormValidator(true);
	} else if(ontimeRadio=="2") {
		$("#cycleDayFlag").unFormValidator(true);
		$(":checkbox[name='weekday']").unFormValidator(false);
		$("#eachMonthDay").unFormValidator(true);
	} else if(ontimeRadio=="3") {
		$("#cycleDayFlag").unFormValidator(true);
		$(":checkbox[name='weekday']").unFormValidator(true);
		$("#eachMonthDay").unFormValidator(false);
	}
	//重复范围默认验证
	var repeatScopeeRadio = $('input:radio[name="repeatScope"]:checked').val();
	if(repeatScopeeRadio=="1") {
		$("#repeatCount").unFormValidator(true);
		$("#endTimeForEver").unFormValidator(true);
	} else if(repeatScopeeRadio=="2") {
		$("#repeatCount").unFormValidator(false);
		$("#endTimeForEver").unFormValidator(true);
	} else if(repeatScopeeRadio=="3") {
		$("#repeatCount").unFormValidator(true);
		$("#endTimeForEver").unFormValidator(false);
	}
	//第三步的验证
	$.formValidator.initConfig({
		validatorGroup:"3",
		formID : "scheduleForm3",
		debug : false,
		onSuccess : function() {
			saveOrUpdateMeeting();
		},
		onError : function() {
			//alert("具体错误，请看网页上的提示");
		}
	});
	
	$("#aheadTime").formValidator({
		validatorGroup:"3",
		onShow : SCHEDULE_CONSTANT.aheadtime_scope,
		onFocus : SCHEDULE_CONSTANT.aheadtime_scope,
		onCorrect : ""
	}).inputValidator({
		min : 1,
		max : 30,
		type:"value",
		onError : SCHEDULE_CONSTANT.aheadtime
	}).regexValidator({
		regExp : "intege1",
		dataType : "enum",
		onError : SCHEDULE_CONSTANT.ahead_time_error
	});
	//定期模式
	$(".cycleType").change(function() {
		var tab = $(this).closest('.tab');
		var name = tab.attr("data-tab");
		var panel = tab.closest('.tabview');
		// switch tabs status
		tab.addClass('active').siblings().removeClass('active');
		// switch views status
		var viewspanel = panel.find('[data-view]:first').parent();
		viewspanel.children('[data-view]').hide().filter('[data-view="' + name + '"]').show();
		if (name=="day") {
			$("#cycleDayFlag").unFormValidator(false);
			$(":checkbox[name='weekday']").unFormValidator(true);
			$("#eachMonthDay").unFormValidator(true);
		} else if(name=="week") {
			$("#cycleDayFlag").unFormValidator(true);
			$(":checkbox[name='weekday']").unFormValidator(false);
			$("#eachMonthDay").unFormValidator(true);
		} else if(name=="month") {
			$("#cycleDayFlag").unFormValidator(true);
			$(":checkbox[name='weekday']").unFormValidator(true);
			$("#eachMonthDay").unFormValidator(false);
		}
	});
	//重复范围
	$(".repeat-scope").change(function() {
		var value = $(this).val();
		if (value=="1") {
			$("#repeatCount").unFormValidator(true);
			$("#endTimeForEver").unFormValidator(true);
			$("#repeatCount").attr("disabled",'disabled');
			$("#endTimeForEver").attr("disabled",'disabled');
		} else if (value=="2") {
			$("#repeatCount").unFormValidator(false);
			$("#endTimeForEver").unFormValidator(true);
			$("#endTimeForEver").attr("disabled",'disabled');
			$("#repeatCount").removeAttr("disabled");
		} else if (value=="3") {
			$("#repeatCount").unFormValidator(true);
			$("#endTimeForEver").unFormValidator(false);
			$("#repeatCount").attr("disabled",'disabled');
			$("#endTimeForEver").removeAttr("disabled");
		}
	});
	
	//默认光标定位
	setCursor("confName", $("#confName").val().length);
	
	// 高级参数设置
	jQuery(document).on('click', '.meeting-dialog .regular .legend .config', function() {
		jQuery(this).closest('.regular').toggleClass('open');
	});

	//创建会议结束返回界面点击确定事件
	$('.button-ok').click(function (evt) {
		parent.jumpToLi("/user/conf/getControlPadConf?userRole=1","ico-conf-host");
		var frame = parent.$("#bookMeeting");
		var data = frame.data("data");
		frame.trigger("closeDialog", [data]);
		frame.trigger("closeDialog");
	});	
	$('.meeting-dialog .prev-button').click(function (evt) {
		var pageIndex = $(this).attr("page-index");
		$(".valid-group").eq(pageIndex).show().siblings().hide();
	});
	$('#schedultFailButton').click(function (evt) {
		$(".valid-group").eq(2).show().siblings().hide();
	});
	//设置重复周期
	$(".repeat-cycle").change(function() {
		var value = $(this).val();
		if (value=="1") {
			//$(".starttime-widget").hide();
			$(".endtime-widget").hide();
			$(".duration-group").hide();
			$(".duration-widget").show();
			$(".repeat-widget").show();
			$("input:radio[name=bytype]:eq(1)").attr("checked",'checked');
		} else {
			//$(".starttime-widget").show();
			$(".duration-group").show();
			$(".repeat-widget").hide();
		}
	});		
	//设置公开会议
	$(".allowPublic").change(function() {
		var value = $(this).val();
		if (value=="1") {
			$(".publicPassSet-widget").show();
			$(".assign-widget").hide();
		} else {
			$(".assign-widget").show();
			$(".publicPassSet-widget").hide();
			$(".publicPass-widget").hide();
			$("input:radio[name=publicPass]:eq(1)").attr("checked",'checked');
		}
	});	
	//设置公开会议密码
	$(".publicPass").change(function() {
		var value = $(this).val();
		if (value=="1") {
			$(".publicPass-widget").show();
			$("#confPass").unFormValidator(false);
			$("#confirmPass").unFormValidator(false);
		} else {
			$(".publicPass-widget").hide();
			$("#confPass").unFormValidator(true);
			$("#confirmPass").unFormValidator(true);
		}
	});
	//设置外乎
	$(".allowCallRadio").change(function() {
		var value = $(this).val();
		if (value=="1" && isOutCallFlag) {
			$("#allowCallTr").show();
			if(allowCall==1){
				$("input:radio[name=allowCall]:eq(0)").attr("checked",'checked');
			}
		} else {
			$("#allowCallTr").hide();
			$("input:radio[name=allowCall]:eq(1)").attr("checked",'checked');
		}
	});
	//设置打开\隐藏视频路数
	$("#meetTypeVideo").change(function() {
		if($(this).attr("checked")){
			$("#confMaxVideoNumDiv").show();
			$(".video-quality-widget").show();
		} else {
			$("#confMaxVideoNumDiv").hide();
			$("#maxVideo").val("0");
			$(".video-quality-widget").hide();
		}
	});
	//设置结束时间
	$(".bytype").change(function() {
		var value = $(this).val();
		if (value=="1") {
			$(".endtime-widget").show();
			$(".duration-widget").hide();
		} else {
			$(".endtime-widget").hide();
			$(".duration-widget").show();
		}
	});
	
	var lang = getBrowserLang(); 
	if (lang=="zh-cn") {
		$.datepicker.setDefaults( $.datepicker.regional[ "zh-CN" ] );
	} else {
		$.datepicker.setDefaults( $.datepicker.regional[ "en-GB" ] );
	}
	$("#startTime, #endTime, #endTimeForEver").datepicker({
		minDate: 0,
		changeMonth: true,
		changeYear: true,			
		dateFormat: "yy-mm-dd",
		onClose: function() {
			$(this).blur();
		}
	});
	
	$("#confDesc").keyup(function() {
		var nowLength=$(this).val().Lenb();
		if (nowLength>256) {
			$(".count").css("color", "#C91600");
		} else {
			$(".count").css("color", "#B0B0B0");
		}
		$("#nowCountSpan").text(nowLength);
	}).bind('paste', function(){
		$(this).prev('label').hide();
	});
	
});