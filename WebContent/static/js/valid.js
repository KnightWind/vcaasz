/**
 * all form valid
 */

/*============================预约会议==============================*/
function checkForm(pageIndex) {
	if(pageIndex=="1"){
		var confName = $("#confName").val();
		if(!confName || confName.length==0){
			parent.errorDialog(validString.pageRequired.confName, function() {
				$("#confName").focus();
			});
			return false;
		}
		if (confName.length<1 || confName.length>32) {
			parent.errorDialog(validString.pageRequired.confNameLength, function() {
				$("#confName").focus();
			});
			return false;
		}
		var allowPublic = $('input:radio[name="allowPublic"]:checked').val();
		var publicPass = $('input:radio[name="passSetRadio"]:checked').val();
		if(allowPublic=="1" && publicPass && publicPass=="1"){
			var confPass = $("#confPass").val().trim();
			if(!confPass){
				parent.errorDialog(validString.pageRequired.confPass, function() {
					$("#confPass").focus();
				});
				return false;
			}
			if(confPass.length<6 ||confPass.length>16){
				parent.errorDialog(validString.pageRequired.passLength, function() {
					$("#confPass").focus();
				});
				return false;
			}
			var confirmPass = $("#confirmPass").val().trim();
			if(!confirmPass){
				parent.errorDialog(validString.pageRequired.confPass2, function() {
					$("#confirmPass").focus();
				});
				return false;
			}
			if(confirmPass.length<6 ||confirmPass.length>16){
				parent.errorDialog(validString.pageRequired.passLength, function() {
					$("#confirmPass").focus();
				});
				return false;
			}
			if(confPass!=confirmPass){
				parent.errorDialog(validString.pageRequired.confirmPass, function() {
					if(!confPass){
						$("#confPass").focus();	
					} else if(!confirmPass){
						$("#confirmPass").focus();
					}
				});
				return false;
			}			
		}
		var hostKey = $("#hostKey").val();
		if(!hostKey || hostKey.length==0){
			parent.errorDialog(validString.pageRequired.hostKey, function() {
				$("#hostKey").focus();
			});
			return false;
		}
		if(hostKey && !hostKey.isDigit()){
			parent.errorDialog(validString.pageRequired.hostKeyType, function() {
				$("#hostKey").focus();
			});
			return false;
		}
		if(hostKey.length<6 ||hostKey.length>12){
			parent.errorDialog(validString.pageRequired.hostKeyType, function() {
				$("#hostKey").focus();
			});
			return false;
		}
		var confDesc = $("#confDesc").val();
		if(confDesc && (confDesc.length<1 ||confDesc.length>150)){
			parent.errorDialog(validString.pageRequired.confDescLength, function() {
				$("#confDesc").focus();
			});
			return false;
		}
	}
	if(pageIndex=="2"){
		var startTimeH = $("#startTimeH").val();
		var startTimeM = $("#startTimeM").val();
		var cycleOption = $('input:radio[name="cycleOption"]:checked').val();//周期选项1是开启,2是关闭
		if(cycleOption==1 && cycleConfType!="1"){
			var cycleType = $('input:radio[name="cycleType"]:checked').val();
			var cycleValue = 0;
			if(cycleType==1){
				var cycleDayValue = $("#cycleDayFlag").val();
				if(!cycleDayValue || cycleDayValue<1 || cycleDayValue>31 || !cycleDayValue.isInteger()){
					parent.errorDialog(validString.pageRequired.dayInterval, function() {
						$("#cycleDayFlag").focus();
					});
					return false;
				}
				cycleValue = cycleDayValue;
			} else if (cycleType==2) {
				var chk_value =[];    
				$('input[name="cycleWeekFlag"]:checked').each(function(){    
				   chk_value.push($(this).val());    
				});
				if(chk_value.length==0){
					parent.errorDialog(validString.pageRequired.weekFlag, function() {
						
					});
					return false;
				}
				cycleValue = chk_value.toString();
			} else {
				var monthFlag = $('input:radio[name="cycleMonthRadio"]:checked').val();
				if(monthFlag==1) {
					var eachMonthDay = $("#eachMonthDay").val();
					if(!eachMonthDay || eachMonthDay<1 || eachMonthDay>31 || !eachMonthDay.isInteger()){
						parent.errorDialog(validString.pageRequired.dayInterval, function() {
							$("#eachMonthDay").focus();
						});
						return false;
					}
					cycleValue = eachMonthDay;
				} else {
					var week = $("#weekFlag").val();
					if(!week){
						parent.errorDialog(validString.pageRequired.weekInterval, function() {
							$("#weekFlag").focus();
						});
						return false;
					}
					var weekDay = $("#weekDay").val();
					cycleValue = week+";"+weekDay;
				}
			}
			var startTime = $("#startTime").val();
			if(!startTime){
				parent.errorDialog(validString.pageRequired.weekStartDate, function() {
					$("#startTime").focus();
				});
				return false;				
			}
			var endTime = $("#endTime").val();
			
			//无限循环会议
//			var infiniteFlag = $('input:radio[name="infiniteFlag"]:checked').val();
//			if(infiniteFlag==0){
//				
//			}
			

//			非无限循环会议
			var infiniteFlag = $('input:radio[name="infiniteFlag"]:checked').val();
			if(infiniteFlag==3) {
				if(!endTime || endTime=="结束日期"){
					parent.errorDialog(validString.pageRequired.weekEndDate, function() {
						$("#endTime").focus();
					});
					return false;				
				}
				//判断循环的会议
				var dateList = getDateListFromCycleScope(startTime, endTime, cycleType, cycleValue, false);
				if(dateList==null || dateList.length==0){
					parent.errorDialog(validString.pageRequired.cycleError, function() {
						
					});
					return false;								
				}
				if(dateList.length>31){
					parent.errorDialog(validString.pageRequired.maxInterval, function() {
						
					});
					return false;								
				}	
			}
			//判断当天的会议开始时间不能小于系统当前时间
			if(dateList && dateList.length==1){
				var date = new Date(Date.parse(dateList[0]));
				date.setHours(startTimeH);
				date.setMinutes(startTimeM);
				if(date.getTime() <= defaultDate.getTime()){
					parent.errorDialog(validString.pageRequired.cycleError, function() {
						
					});
					return false;
				}
			}
			//判断循环的会议不能小于周期的开始时间
			if(dateList && dateList.length==1){
				var date = new Date(Date.parse(dateList[0]));
				date.setHours(startTimeH);
				date.setMinutes(startTimeM);
				var tempStartTime = startTime.replace(/-/g,"/");
				if(date.getTime() <= new Date(tempStartTime).getTime()){
					parent.errorDialog(validString.pageRequired.cycleError, function() {
						
					});
					return false;
				}
			}
		} else {
			var endTypeFlag = $('input:radio[name="endType"]:checked').val();
			if(endTypeFlag && endTypeFlag=="1"){
				var startDateTemp = $("#startDate").val()+" "+$("#startTimeH").val()+ ":" +$("#startTimeM").val()+":00";
				startDateTemp=startDateTemp.parseDate();
				var endDateTemp = $("#endDate").val()+" "+$("#endTimeH").val()+ ":" +$("#endTimeM").val()+":00";
				endDateTemp=endDateTemp.parseDate();
				if(endDateTemp.before(startDateTemp)){
					parent.errorDialog(validString.pageRequired.afterStart, function() {
						
					});
					return false;
				}
			}
		}
		
		if(cycleOption!=1) {
			var startDate = $("#startDate").val();
			if(!startDate){
				parent.errorDialog(validString.pageRequired.startDate, function() {
					$("#startDate").focus();
				});
				return false;				
			}	
		}
		var tempDate = startDate +" "+ startTimeH +":"+startTimeM+":00";
		if(!compareNow(tempDate, defaultDate)){
			parent.errorDialog(validString.pageRequired.timeError, function() {
				$("#startDate").focus();
			});
			return false;
		}
		var durationH = parseInt($("#meetingTimeH").val(), 10);
		var durationM = parseInt($("#meetingTimeM").val(), 10);
		var duration = durationH*60 + durationM;
		var durationTR = $(".durationTR");
		if(durationTR.is(":visible")){
			if(duration==0){
				parent.errorDialog(validString.maxlength.duration, function() {
					$("#meetingTimeM").focus();
				});
				return false;				
			}			
		}
		var endDateTr = $(".endDateTr");
		if(endDateTr.is(":visible")){
			var startDateTemp = $("#startDate").val()+$("#startTimeH").val()+$("#startTimeM").val();
			startDateTemp=startDateTemp.replace(/-/g,"");
			var endDateTemp = $("#endDate").val()+$("#endTimeH").val()+$("#endTimeM").val();
			endDateTemp=endDateTemp.replace(/-/g,"");
			if(endDateTemp<startDateTemp){
				parent.errorDialog(validString.pageRequired.afterStart, function() {
					
				});
				return false;
			}
		}
	}
	if(pageIndex=="3"){
		/*
		var maxUser = $("#maxUser").val();
		var license = $("#maxLicense").text();
		if(!maxUser){
			parent.errorDialog(validString.pageRequired.maxUser, function() {
				$("#maxUser").focus();
			});
			return false;
		}			
		if(maxUser && license){
			if(!maxUser.isInteger()){
				parent.errorDialog(validString.pageRequired.maxUserNumber, function() {
					$("#maxUser").focus();
				});
				return false;
			}
			maxUser = parseInt($("#maxUser").val(), 10);
			license = parseInt($("#maxLicense").text(), 10);
			if(maxUser>license || maxUser<2){
				parent.errorDialog(validString.pageRequired.maxLicense+license, function() {
					$("#maxUser").focus();
				});
				return false;
			}
		}
		*/
		var aheadTime = $("#aheadTime").val();
		if(aheadTime == "" || aheadTime == null){
			parent.errorDialog(validString.pageRequired.aheadTimeInt, function() {
				$("#aheadTime").focus();
			});
			return false;
		}
		if(aheadTime && !aheadTime.isInteger()){
			parent.errorDialog(validString.pageRequired.aheadTimeInt, function() {
				$("#aheadTime").focus();
			});
			return false;
		}
		if(aheadTime && (parseInt(aheadTime, 10)>60 || parseInt(aheadTime, 10)<0) ){
			parent.errorDialog(validString.pageRequired.aheadTime, function() {
				$("#aheadTime").focus();
			});
			return false;
		}
	}
	return true;
}
function editMeeting(){
	var data = {};
	//基本信息
	data.confName = $("#confName").val();
	data.confDesc = $("#confDesc").val();
	data.publicFlag = 2;
	var allowPublic = $('input:radio[name="allowPublic"]:checked').val();
	if(allowPublic=="1"){
		data.publicFlag = 1;
		var publicPass = $('input:radio[name="passSetRadio"]:checked').val();//$(".passSetCheck").attr("checked");
		if(publicPass && publicPass=="1"){
			data.publicConfPass = $("#confPass").val();	
		}
	} else {
		var confInviter = $('input:radio[name="confInviterRadio"]:checked').val();
		if(confInviter=="0"){
			data.publicFlag = 3;
		}
	}
	//主持人密码
	var hostKey = $("#hostKey").val();
	if(hostKey) {
		data.hostKey = hostKey;
	}
	
	//周期信息
	var cycleOption = $('input:radio[name="cycleOption"]:checked').val();//周期选项1是开启,2是关闭
	data.cycleOption = cycleOption;
	if(cycleOption==1 && cycleConfType!="1"){
		var cycleType = $('input:radio[name="cycleType"]:checked').val();
		data.cycleType = cycleType;
		if(cycleType==1){//按天
			data.cycleValue = $("#cycleDayFlag").val();
		} else if (cycleType==2) {//按周
			var chk_value =[];    
			$('input[name="cycleWeekFlag"]:checked').each(function(){    
			   chk_value.push($(this).val());    
			});
			data.cycleValue = chk_value.toString();
		} else {//按月
			var monthFlag = $('input:radio[name="cycleMonthRadio"]:checked').val();
			if(monthFlag==1) {
				data.cycleValue = $("#eachMonthDay").val();
			} else {
				var week = $("#weekFlag").val();
				var weekDay = $("#weekDay").val();
				data.cycleValue = week+";"+weekDay;
			}
		}
		var startDateStr=$("#startTime").val()+" "+$("#startTimeH").val()+":"+$("#startTimeM").val()+":00";
		data.beginDate = startDateStr.parseDate().format("yyyy-MM-dd hh:mm:ss");// $("#startTime").val()+" "+$("#startTimeH").val()>0+":"+$("#startTimeM").val()+":00";
		
		var endTime = $("#endTime").val();
		var infiniteFlag = $('input:radio[name="infiniteFlag"]:checked').val();
		//	无限循环会议
		if(infiniteFlag==1){
			data.infiniteFlag = 1;
			endTime = "2100-01-01";
		}
		//	按次数循环
		if(infiniteFlag==2){
			data.repeatCount = $("#repeatCount").val();
			endTime = "2100-01-01";
		}
		
		var endDateStr = endTime +" "+$("#startTimeH").val()+":"+$("#startTimeM").val()+":00";
		data.endDate = endDateStr.parseDate().format("yyyy-MM-dd hh:mm:ss");//$("#endTime").val()+" "+$("#startTimeH").val()>0+":"+$("#startTimeM").val()+":00";
		data.startTime = data.beginDate;
	} else {
		var startDate = $("#startDate").val();
		var startHour = $("#startTimeH").val();
		var startMinute = $("#startTimeM").val();
		var dateStr = startDate+" "+startHour+":"+startMinute+":00";
		data.startTime = dateStr.parseDate().format("yyyy-MM-dd hh:mm:ss");
//		data.startTime = dateStr;
	}
	var durationH = parseInt($("#meetingTimeH").val(), 10);
	var durationM = parseInt($("#meetingTimeM").val(), 10);
	var duration = durationH*60 + durationM; 
	if(duration>0){
		data.duration = duration;
	}
	
	//永久会议创建
	var endTypeFlag = $('input:radio[name="endType"]:checked').val();
	if(endTypeFlag && endTypeFlag=="1"){
		var endDate = $("#endDate").val();
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
		var endDate = $("#endDate").val();
		var endHour = $("#endTimeH").val();
		var endMinute = $("#endTimeM").val();
		var dateStr = endDate+" "+endHour+":"+endMinute+":00";
		data.endTime = dateStr;
	}
	
	//会议功能
	var confType_value =[];    
	$('input[name="confType"]:checked').each(function(){    
		confType_value.push($(this).val());    
	});
	if(confType_value.length>0){
		data.confType = confType_value.length==1?confType_value[0]:3;	
	}
//	data.maxUser = $("#maxUser").val();
	//外乎
	var checkConfType = $("input:checkbox[name=confType]:eq(1)").attr("checked");
	if(checkConfType) {
		var micStatus = $('input:radio[name="allowCall"]:checked').val();
		if(micStatus==1) {
			data.allowCall = micStatus;
		} else {
			data.allowCall = 0;
		}
	}
	//会议模式
	var confModel = $('input:radio[name="confModel"]:checked').val();
	if(confModel==1){
		data.confModel = confModel;
	} 
	//麦克风状态
	var micStatus = $('input:radio[name="micStatus"]:checked').val();
	if(micStatus==1) {
		data.micStatus = micStatus;
	}
	data.maxVideo = $("#maxVideo").val();
	data.maxAudio = $("#maxAudio").val();
	data.videoType = $('input:radio[name="videoType"]:checked').val();
	data.aheadTime = $("#aheadTime").val();
	
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
		data.confHwid = confHwid;
		data.timeZone = timeZone;
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
				}, {message:validString.pageRequired.recreateConf, ui:parent});	
			}else if(cycleConfType == 1) {
				//修改循环会议中的所有会议
//				alert("修改循环会议中的所有会议");
				app.updateCycleMeeting(data, function(result) {
					if(result && result.status=="1") {
						updateMeetSuccess(result);
					}else {
						parent.errorDialog(result.message);
					}
				}, {message:validString.pageRequired.updateConf, ui:parent});	
			} else {
				//修改循环中的一条
//				alert("修改循环中的一条");
				app.updateCycleOneMeeting(data, function(result) {
					if(result && result.status=="1") {
						updateMeetSuccess(result);
					}else {
						parent.errorDialog(result.message);
					}
				}, {message:validString.pageRequired.updateConf, ui:parent});						
			}
		}else if(cycleConfType == 2) {
			//重新创建会议
			app.reCreateConf(data, function(result) {
				if(result && result.status=="1") {
					reCreateConfSuccess(result);
				}else {
					parent.errorDialog(result.message);
				}
			}, {message:validString.pageRequired.recreateConf, ui:parent});	
		}else {
			//修改单次会议
			app.updateBookMeeting(data, function(result) {
				if(result && result.status=="1") {
					updateMeetSuccess(result);
				}else {
					parent.errorDialog(result.message);
				}
			}, {message:validString.pageRequired.updateConf, ui:parent});
		}
	} else {
		app.bookMeeting(data,function(result) {
			if(result && result.status=="1") {
				createMeetSuccess(result);
			}else {
				parent.errorDialog(result.message);
			}
		},{message:validString.pageRequired.createConf, ui:parent});		
	}

};
//发送通知邮件
function sendNoticeEmail() {
	var confId = $("#confIdForEmail").val();
	var data = {};
	data.confId = confId;
	app.sendNoticeEmail(data, function(result) {
		if(result){
			if(result.status && result.status==1){
				parent.successDialog(result.message);
			} else {
				parent.errorDialog(result.message);
			}
		}
	},{message:validString.pageRequired.addCanlendar, ui:parent});
}
//修改会议完成后返回会议信息到成功页面
function updateMeetSuccess(result) {
	var confBase = result.confBase[0];
	$("#confIdForEmail").val(confBase.id);
	var frame = parent.$("#bookMeeting");
	frame.trigger("resize", [520]);
	frame.data("data", result);
	$("#confNameSpan").text(confBase.confName);   //会议主题
	$(".achieve_text").text(validString.pageRequired.updateConfSuccess);
	$(".cycleTypeTR").hide();     //周期类型
	$(".cycleTimeTRD").hide();	 //周期时间
	var confType = confBase.confType;
	if(confBase.confType == 1){
		confType = validString.pageRequired.secConfType;       //会议功能 :电话功能 
	}
	if(confBase.confType == 2){
		confType = validString.pageRequired.thirdConfType;  	//会议功能 :视频功能    
	}
	if(confBase.confType == 3){
		confType = validString.pageRequired.fourConfType;  		//会议功能 :视频功能、电话功能   
	}
	$("#confTypeSpan").text(confType);         //会议功能    
	if(confBase.confType == 0){
		$(".confTypeTR").hide();	 //会议功能
//		confType = validString.pageRequired.firConfType;       //会议功能 :数据会议功能   
	}
	$("#maxUserSpan").text(confBase.maxUser);         //最大参会人数
	$("#userSecureSpan").text(confBase.userSecure); 		//与会者安全会议号
	$("#confDescSpan").text(confBase.confDesc); 		//会议描述
	var duraH = parseInt(confBase.duration/60);
	var duraM = confBase.duration%60;
	var duraString = "";
	if(duraM > 1){
		duraString = duraM + " "  + validString.pageRequired.minutes;
	}else if(duraM == 1){
		duraString = duraM + " "  + validString.pageRequired.minute;
	}
	
	if(duraH > 1){
		duraString = duraH + " " + validString.pageRequired.hours + " " +duraString; 
	}else if(duraH == 1){
		duraString = duraH + " " + validString.pageRequired.hour + " " +duraString; 
	}
	$("#durationSpan").text(duraString);    //会议时长 ：1小时30分
	$("#step4").show().siblings().hide();
}
//重新创建会议完成后返回会议信息到成功页面
function reCreateConfSuccess(result) {
	var confBase = result.confBase[0];
	$("#confIdForEmail").val(confBase.id);
	var frame = parent.$("#bookMeeting");
	frame.trigger("resize", [520]);
	frame.data("data", result);
	$("#confNameSpan").text(confBase.confName);   //会议主题
	$(".achieve_text").text(validString.pageRequired.recreateConfSuccess);
	$(".cycleTypeTR").hide();     //周期类型
	$(".cycleTimeTRD").hide();	 //周期时间
	var confType = confBase.confType;
	if(confBase.confType == 1){
		confType = validString.pageRequired.secConfType;       //会议功能 :电话功能    
	}
	if(confBase.confType == 2){
		confType = validString.pageRequired.thirdConfType;  	//会议功能 :视频功能   
	}
	if(confBase.confType == 3){
		confType = validString.pageRequired.fourConfType;  		//会议功能 :视频功能、电话功能   
	}
	$("#confTypeSpan").text(confType);         //会议功能    
	if(confBase.confType == 0){
		$(".confTypeTR").hide();	 //会议功能
//		confType = validString.pageRequired.firConfType;       //会议功能 :数据会议功能   
	}
	$("#maxUserSpan").text(confBase.maxUser);         //最大参会人数
	$("#userSecureSpan").text(confBase.userSecure); 		//与会者安全会议号
	$("#confDescSpan").text(confBase.confDesc); 		//会议描述
	var duraH = parseInt(confBase.duration/60);
	var duraM = confBase.duration%60;
	var duraString = "";
	if(duraM > 1){
		duraString = duraM + " "  + validString.pageRequired.minutes;
	}else if(duraM == 1){
		duraString = duraM + " "  + validString.pageRequired.minute;
	}
	
	if(duraH > 1){
		duraString = duraH + " " + validString.pageRequired.hours + " " +duraString; 
	}else if(duraH == 1){
		duraString = duraH + " " + validString.pageRequired.hour + " " +duraString; 
	}
	$("#durationSpan").text(duraString);    //会议时长 ：1小时30分
	$("#step4").show().siblings().hide();
}

//创建会议完成后返回会议信息到成功页面
function createMeetSuccess(result) {
	var confBase = result.confBase[0];
	$("#confIdForEmail").val(confBase.id);
	var confCycle = null;
	if(result.confCycle){
		confCycle = result.confCycle[0];	
	}
	var frame = parent.$("#bookMeeting");
	frame.trigger("resize", [520]);
	frame.data("data", result);
	$("#confNameSpan").text(confBase.confName);   //会议主题
	var cycleType = confCycle.cycleType;
	var infiniteFlag = confCycle.infiniteFlag;
	var repeatCount = confCycle.repeatCount;
	var cycleOption = 1;
	if(confCycle.cycleType == 1){
		cycleType = validString.pageRequired.firCycleType;    //周期类型:日循环会议
	}else if(confCycle.cycleType == 2){
		cycleType = validString.pageRequired.secCycleType;    //周期类型:周循环会议
	}else if(confCycle.cycleType == 3){
		cycleType = validString.pageRequired.thirdCycleType;   //周期类型:月循环会议
	}else{
		cycleOption = 0;
		cycleType = validString.pageRequired.fourCycleType;	   //周期类型:非周期循环会议
	}
	if(cycleOption == 0){
		$(".cycleTypeTR").hide();     //周期类型
	}else{
		$("#cycleTypeSpan").text(cycleType);     //周期类型
	}
	var cycleTimeLz = confCycle.beginDate;
	var cycleTimeRz = confCycle.endDate;
	var cycleTime = validString.pageRequired.fourCycleType;
	if(cycleOption != 0){
		cycleTime = cycleTimeLz.substring(0, 10) + "----" + cycleTimeRz.substring(0, 10);
		$("#cycleTimeSpan").text(cycleTime);	 //周期时间
		if(infiniteFlag == 1){
			$("#cycleTimeSpan").text(validString.pageRequired.infiniteType);
		}
		if(repeatCount != 0){
			var repeatText = validString.pageRequired.repeatCountLeft + repeatCount + validString.pageRequired.repeatCountRight;
			$("#cycleTimeSpan").text(repeatText);
		}
	}else{
		$(".cycleTimeTRD").hide();	 //周期时间
	}	
	var confType = confBase.confType;
	if(confBase.confType == 1){
		confType = validString.pageRequired.secConfType;       //会议功能 :电话功能    
	}
	if(confBase.confType == 2){
		confType = validString.pageRequired.thirdConfType;  	//会议功能 :视频功能   
	}
	if(confBase.confType == 3){
		confType = validString.pageRequired.fourConfType;  		//会议功能 :视频功能、电话功能  
	}
	$("#confTypeSpan").text(confType);         //会议功能    
	if(confBase.confType == 0){
		$(".confTypeTR").hide();	 //会议功能
//		confType = validString.pageRequired.firConfType;       //会议功能 :数据会议功能   
	}
	$("#maxUserSpan").text(confBase.maxUser);         //最大参会人数
	$("#userSecureSpan").text(confBase.userSecure); 		//与会者安全会议号
	$("#confDescSpan").text(confBase.confDesc); 		//会议描述
	
	
	var duraH = parseInt(confBase.duration/60);
	var duraM = confBase.duration%60;
	var duraString = "";
	if(duraM > 1){
		duraString = duraM + " " + validString.pageRequired.minutes;
	}else if(duraM == 1){
		duraString = duraM + " " + validString.pageRequired.minute;
	}
	
	if(duraH > 1){
		duraString = duraH + " " + validString.pageRequired.hours + " " +duraString; 
	}else if(duraH == 1){
		duraString = duraH + " " + validString.pageRequired.hour + " " +duraString; 
	}
	$("#durationSpan").text(duraString);    //会议时长 ：1小时30分
	$("#step4").show().siblings().hide();
	showInventDialog(confBase);
}

function showInventDialog(conf) {
	if(conf.publicFlag=="2"){
		parent.confirmDialog(validString.pageRequired.sendInventors, function() {
			if ($.browser.msie && $.browser.version<7) {//fixed ie6 iframe blank
				setTimeout(inventContact, 1000);	
			} else {
				inventContact();
			}
		});
	}
}

function createSuccess() {
	var frame = parent.$("#bookMeeting");
	var data = frame.data("data");
	frame.trigger("closeDialog", [data]);
}
function updateAgain() {
	
}
function inventContact(){
	var id = $("#confIdForEmail").val();
	parent.inventContact(id);
}
var timer=null;
var nextDateTime;
var dateTimeStamp = defaultDate.getTime();
function showTimeZone(){
	if(dateTimeStamp<=0){
		window.clearInterval(timer);
	}
	nextDateTime=new Date(dateTimeStamp);
	$("#site_time").html(nextDateTime.format("yyyy-MM-dd hh:mm:ss"));
	dateTimeStamp+=1000;
}
jQuery(function($) {
	timer=window.setInterval(showTimeZone,1000);
	var lang = getBrowserLang(); 
	if (lang=="zh-cn") {
		$.datepicker.setDefaults( $.datepicker.regional[ "zh-CN" ] );
	} else {
		$.datepicker.setDefaults( $.datepicker.regional[ "en-GB" ] );
	}
	$("#startTime, #endTime, #startDate, #endDate").datepicker({
		minDate: 0,
		changeMonth: true,
		changeYear: true,			
		dateFormat: "yy-mm-dd",
		showOn: "both",
		buttonImage: "/static/images/new03.png",
		buttonImageOnly: true,
		onClose: function() {
			$(this).trigger("blur");
		}
	});
	//设置结束时间
	$(".endTypeRadio").change(function() {
		var value = $(this).val();
		if (value=="1") {
			$(".endDateTr").show();
			$(".durationTR").hide();
		} else {
			$(".endDateTr").hide();
			$(".durationTR").show();
		}
	});	
	//设置公开会议
	$(".allowPublic").change(function() {
		var value = $(this).val();
		if (value=="1") {
			$(".publicPassSet").show();
			$(".confInviterTR").hide();
			var passSetRaido = $('input:radio[name="passSetRadio"]:checked').val();
			if(passSetRaido=="1"){
				$("#confPassTR").show();
				$("#confirmPassTR").show();
			}
		} else {
			$(".publicPassSet").hide();
			$(".confInviterTR").show();
			$("#confPassTR").hide();
			$("#confPass").val("");
			$("#confirmPassTR").hide();
			$("#confirmPass").val("");
//			$.uniform.update();
		}
	});
	//设置公开会议密码
	$(".passSetRadio").change(function() {
		var value = $(this).val();
		if (value=="1") {
			$("#confPassTR").show();
			$("#confirmPassTR").show();
		} else {
			$("#confPassTR").hide();
			$("#confPass").val("");
			$("#confirmPassTR").hide();
			$("#confirmPass").val("");
		}
	});	
	
	//是否无限期循环
	$(".infiniteFlag").change(function() {
		var value = $(this).val();
		if(value == 1){
			$("#repeatCount").attr("disabled",'disabled');
			$("#endTime").attr("disabled",'disabled');
		}else if( value == 2){
			$("#repeatCount").removeAttr("disabled",'disabled');
			$("#endTime").attr("disabled",'disabled');
		}else{
			$("#endTime").removeAttr("disabled",'');
		}
	});
	
	//设置永久会议
	$(".permanentOption").change(function() {
		var value = $(this).val();
		if (value==1) {
			$(".cycleOptionTR").hide();
			$(".durationTR").hide();
		} else {
			$(".cycleOptionTR").show();
			$(".durationTR").show();
		} 
	});

	//设置会议类型
	$(".confType").change(function() {
		var value = $(this).val();
		if (value==1) {
			if(isOutCallFlag && $(this).attr("checked")){
				$("#allowCallTr").show();	
			} else {
				$("#allowCallTr").hide();
			}
		} else if(value==2){
			if($(this).attr("checked")){
				$("#videoTR").show();	
			} else {
				$("#videoTR").hide();
			}
		} 
	});
	//设置高级参数
	$(".gaoji").click(function() {
		var isMsie = false;
		if($.browser.msie && ($.browser.version=="7.0" || $.browser.version=="6.0")){
			isMsie = true;
		}
		var extraPanel = $(".extra_panel");
		if(extraPanel.is(":visible")) {
			if(isMsie){
				$(".extend_option").show();	
			} else {
				$(".extend_option").slideDown();
			}
			$(".extra_panel").hide();
			$("#gaojiicon").attr("src", "/static/images/y_gaoji.jpg");
		} else {
			if(isMsie){
				$(".extend_option").hide();
			} else {
				$(".extend_option").slideUp();	
			}
			$(".extra_panel").show();
			$("#gaojiicon").attr("src", "/static/images/y_gaoji_off.jpg");
		}
	});
	//是否开启周期会议
	$(".cycleOption").change(function() {
		var value = $(this).val();
		if (value==1) {
			$(".cycleEnable").show();//开启周期
			$(".startDateDiv").hide();
			$(".endTypeTR").hide();
			$(".endDateTr").hide();
			$(".durationTR").show();
			$('input:radio[name="endType"]:eq(1)').attr("checked",'checked');
			$.uniform.update();
		} else {
			$(".cycleEnable").hide();//关闭周期
			$(".startDateDiv").show();
			$(".endTypeTR").show();
			$(".endDateTr").hide();
			$(".durationTR").show();
		}
	});
	//选择周期类型
	$(".cycleType").change(function() {
		var value = $(this).val();
		if (value==1) {
			$(".cycleWeek").removeClass("cycleEnable").hide();
			$(".cycleMonth").removeClass("cycleEnable").hide();
			$(".cycleDay").addClass("cycleEnable").show();//按天
		} else if(value==2){
			$(".cycleDay").removeClass("cycleEnable").hide();//按周
			$(".cycleMonth").removeClass("cycleEnable").hide();		
			$(".cycleWeek").addClass("cycleEnable").show();
		} else {
			$(".cycleDay").removeClass("cycleEnable").hide();//按月
			$(".cycleWeek").removeClass("cycleEnable").hide();
			$(".cycleMonth").addClass("cycleEnable").show();
		}
	});
	//高级参数
	$(".extendOption").click(function() {
		var extraPanel = $(".extraEnable");
		if(extraPanel.is(":visible")) {
			extraPanel.hide();
		} else {
			extraPanel.show();
		}
		
	});
	$('.validation_group .next').click(function (evt) {
		var type = $(this).attr("type");
		var pageIndex = $(this).attr("pageIndex");
		if(checkForm(pageIndex)) {
			if(pageIndex=="3"){
				editMeeting();
			} else {
				var currentGroup = $(this).closest(".validation_group");
				currentGroup.hide().next().show();	
			}
		}
	});
	
	$('.validation_group .prev').click(function(){
		var currentGroup = $(this).closest(".validation_group");
		currentGroup.hide().prev().show();
	});
	
	$(".closeButton").click(function() {
		var frame = parent.$("#bookMeeting");
		frame.trigger("closeDialog");
	});
	$("#meetingForm").find("input, select").not(".skipThese").uniform();
});
