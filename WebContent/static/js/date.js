

String.prototype.Trim = function() {
    return this.replace(/(^\s*)|(\s*$)/g, "");
};


String.prototype.trim = function() {
    return this.replace(/(^\s*)|(\s*$)/g, "");
};

String.prototype.removeSpace=function(){
    return this.replace(/\s/g, "");
};

String.prototype.isEmpty=function(){
    if (this == null || this.trim().length <= 0) {
        return true;
    } else {
        return false;
    }
};

/**
 *复制日期 
 */
Date.prototype.clone = function () {
    return new Date(this.getTime());
};

/**
 *验证是否为正整数 
 */
String.prototype.isInteger = function() {
    if (this.isEmpty()) {
        return false;
    }
    var regex = /^[1-9]\d*$/g;
    return regex.test(this);
};

/**
 *验证是否为数字 
 */
String.prototype.isDigit = function() {
    if (this.isEmpty()) {
        return false;
    }
    var regex = /^\d+$/;
    return regex.test(this);
};
/**
 *验证是否为整数 
 */
String.prototype.isFullInteger = function() {
    if (this.isEmpty()) {
        return false;
    }
    var regex = /^-?\d+$/;
    return regex.test(this);
};
/*
String.prototype.isDate=function(){
    var dateStatus=false;
    if(this.isEmpty()){
        return dateStatus;
    }
    var dateStr=this.removeSpace();
    var regex=/^\d{4}-(0?[1-9])|(1[1-2])-(0?[1-9])|([1-2]\d)|(3[0-1])$/g;
    var tmpStatus = regex.test(dateStr);
    regex = null;
    if(!tmpStatus){
        dateStr = null;
        return dateStatus;
    }
    tmpStatus=null;
    
    var tmpArr=dateStr.split("-");
    if(tmpArr==null || tmpArr.length != 3){
         return dateStatus;
    }
    var yearNum=parseInt(tmpArr[0],10);
    var monthNum=parseInt(tmpArr[1],10);
    var dayNum=parseInt(tmpArr[2],10);
    tmpArr = null;
    
    
    if(monthNum <=0 || monthNum>=13 || dayNum<=0){
        return dateStatus;
    }
    var maxDay=getMonthMaxDay(yearNum,monthNum);
    if(dayNum > maxDay){
        return dateStatus;
    }else{
       dateStatus=true; 
    }
    yearNum = null;
    monthNum = null;
    dayNum = null;
    maxDay = null;
    return dateStatus;
};

*/
 
 


/**
 *验证字符串是否为日期型格式
 */
String.prototype.isDate = function() {
    if (this.isEmpty()) {
        return false;
    }
    var regex = /^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))( (([0-1]?\d)|(2[0-3]))(:([0-5]?\d)){2})?$/g;
    return regex.test(this);
};
/**
 *验证是否为时间格式   
 */
String.prototype.isTime=function(){
    if (this.isEmpty()) {
        return false;
    }
     var regex = /^(([0-1]?\d)|(2[0-3]))(:([0-5]?\d)){2}$/g;
    return regex.test(this);
};

/**
 *将字符串转成日期型 
 */
String.prototype.parseDate=function(){
    if(!this.isDate()){
        return null;
    }
    var tmpArr=this.split(/[-:\s]/);
    if(tmpArr.length != 3 && tmpArr.length != 6){
        return null;
    }
    var date=null;
    date=new Date(tmpArr[0],(parseInt(tmpArr[1],10)-1),tmpArr[2]);
    if(tmpArr.length == 6){
        date=new Date(tmpArr[0],(parseInt(tmpArr[1],10)-1),tmpArr[2],tmpArr[3],tmpArr[4],tmpArr[5]);
    }
    tmpArr=null;
    return date;
};


/**
 *将日期型转成字符串
 */

Date.prototype.format=function(fmt){
    if(this==null){
        return null;
    }
	var o = {   
		//"y+" : this.getFullYear(),                 //年份    
		"M+" : this.getMonth()+1,                 //月份    
		"d+" : this.getDate(),                    //日    
		"h+" : this.getHours(),                   //小时    
		"m+" : this.getMinutes(),                 //分    
		"s+" : this.getSeconds(),                 //秒    
		"q+" : Math.floor((this.getMonth()+3)/3), //季度    
		"S"  : this.getMilliseconds()             //毫秒    
	};   
	if(/(y+)/.test(fmt)){ 
		fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
	}
	for(var k in o){  
		if(new RegExp("("+ k +")").test(fmt)) {  
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));  
		}
	}
	return fmt;   
};

Date.prototype.after=function(date){
	if(this==null || date==null){
		return false;
	}
	var thisTimpStamp=this.getTime();
	var dateTimeStamp=date.getTime();
	if(thisTimpStamp>dateTimeStamp){
		return true;
	}
	return false;
	
}

Date.prototype.before=function(date){
	if(this==null || date==null){
		return false;
	}
	var thisTimpStamp=this.getTime();
	var dateTimeStamp=date.getTime();
	if(thisTimpStamp<dateTimeStamp){
		return true;
	}
	return false;
}

var DateType={
		YEAR:1,
		MONTH:2,
		DAY:3,
		HOUR:4,
		MINUTE:5,
		SECOND:6
};


Date.prototype.add=function(dateType,num){
	if(dateType<DateType.YEAR && dateType>DateType.SECOND){
		return this;
	}
	var thisTimeSatmp=this.getTime();
	var retDate=new Date();
	switch(dateType){
	case DateType.YEAR:
//		retDate=this;
//		retDate.setFullYear(retDate.getFullYear()+1,retDate.getMonth());
		break;
	case DateType.MONTH:
//		retDate = this;
//      var monthNum = retDate.getMonth();
//      retDate.setMonth(monthNum + num);
		break;
	case DateType.DAY:
		
		thisTimeSatmp+=24*3600*1000;
		retDate=new Date(thisTimeSatmp);
		break;
	case DateType.HOUR:
		break;
	case DateType.MINUTE:
		break;
	case DateType.SECOND:
		break;
	}
	return retDate;
}
String.prototype.parseTimeStamp=function(){
    var timeStamp=0;
    var date=this.parseDate();
    if(date!=null){
        timeStamp=date.getTime();
    }
    date=null;
    return timeStamp;
};

/**
 * 根据日期
 * @returns {Number}
 */

Date.prototype.getTimeZone=function(){
    if (this == null) {
        return 0;
    }
	return this.getTimezoneOffset()*-1*60*1000;
}


Date.prototype.getWeekOfMonth = function() {

    var weekCount = 1;
    var yearNum = this.getFullYear();
    var monthNum = this.getMonth() + 1;
    var dayNum = this.getDate();
    var firstDay = (yearNum + "-" + monthNum + "-1" ).parseDate();
    // firstDay.setDate(1);
    var eachDayTimeStamp = firstDay.getTime();
    var eachDate = null;
    var eachDayOfWeek = 0;
    var thisTimeStamp = this.getTime();
    var dayTimeStamp = 24 * 3600 * 1000;
    var ii = 0;
    while (eachDayTimeStamp <= thisTimeStamp) {
        eachDate = new Date(eachDayTimeStamp);
        eachDayOfWeek = eachDate.getDay() + 1;
        if (ii > 0 && eachDayOfWeek == CONSTANT_WEEK.SUNDAY) {
            weekCount++;
        }
        eachDayTimeStamp += dayTimeStamp;
        ii++;
    }
    firstDay = null;
    eachDayTimeStamp = null;
    eachDate = null;
    eachDayOfWeek = null;
    thisTimeStamp = null;
    dayTimeStamp = null;
    return weekCount;
};

//格局化日期：yyyy-MM-dd  
function formatDate(date) {  
	var myyear = date.getFullYear();  
	var mymonth = date.getMonth()+1;  
	var myweekday = date.getDate();  
	if(mymonth < 10){  
		mymonth = "0" + mymonth;  
	}  
	if(myweekday < 10){  
		myweekday = "0" + myweekday;  
	}  
	return (myyear+"-"+mymonth + "-" + myweekday);  
}  
//获得本天的开始日期
function getDayStart(now) {
	var nowYear = now.getYear(); //当前年  
	nowYear += (nowYear < 2000) ? 1900 : 0; //
	var nowDay = now.getDate(); //当前日  
	var nowMonth = now.getMonth(); //当前月  
	var dayStartDate = new Date(nowYear, nowMonth, nowDay, 00, 00 , 00);
	return formatDate(dayStartDate);
}
//获得本天的结束日期
function getDayEnd(now) {
	var nowYear = now.getYear(); //当前年  
	nowYear += (nowYear < 2000) ? 1900 : 0; //
	var nowDay = now.getDate(); //当前日  
	var nowMonth = now.getMonth(); //当前月  
	var dayEndDate = new Date(nowYear, nowMonth, nowDay, 23, 59 , 59);
	return formatDate(dayEndDate);
}
//获得本周的开端日期  
function getWeekStartDate(now) {  
	var nowDayOfWeek = now.getDay(); //今天本周的第几天  
	var nowDay = now.getDate(); //当前日  
	var nowMonth = now.getMonth(); //当前月  
	var nowYear = now.getYear(); //当前年  
	nowYear += (nowYear < 2000) ? 1900 : 0; //	
	var weekStartDate = new Date(nowYear, nowMonth, nowDay - nowDayOfWeek);  
	return formatDate(weekStartDate);  
}  
  
//获得本周的停止日期  
function getWeekEndDate(now) {
	var nowDayOfWeek = now.getDay(); //今天本周的第几天  
	var nowDay = now.getDate(); //当前日  
	var nowMonth = now.getMonth(); //当前月  
	var nowYear = now.getYear(); //当前年  
	nowYear += (nowYear < 2000) ? 1900 : 0; //	
	var weekEndDate = new Date(nowYear, nowMonth, nowDay + (6 - nowDayOfWeek));  
	return formatDate(weekEndDate);  
}
//获得某月的天数  
function getMonthDays(nowYear, nowMonth){  
	var monthStartDate = new Date(nowYear, nowMonth, 1);  
	var monthEndDate = new Date(nowYear, nowMonth + 1, 1);  
	var days = (monthEndDate - monthStartDate)/(1000 * 60 * 60 * 24);  
	return days;  
}  
//获得本月的开端日期  
function getMonthStartDate(now){
	var nowMonth = now.getMonth(); //当前月  
	var nowYear = now.getYear(); //当前年  
	nowYear += (nowYear < 2000) ? 1900 : 0; //		
	var monthStartDate = new Date(nowYear, nowMonth, 1);  
	return formatDate(monthStartDate);  
}  
  
//获得本月的停止日期  
function getMonthEndDate(now){
	var nowMonth = now.getMonth(); //当前月  
	var nowYear = now.getYear(); //当前年  
	nowYear += (nowYear < 2000) ? 1900 : 0; //	
	var monthEndDate = new Date(nowYear, nowMonth, getMonthDays(nowYear, nowMonth));  
	return formatDate(monthEndDate);  
}   
/**
 * 根据年、月获取月的最大天数
 * @param {Object} yearNum
 * @param {Object} monthNum
 */
function getMonthMaxDay(yearNum, monthNum) {
    var maxDay = 0;
    if ((!isNumberType(yearNum) || isNaN(yearNum)) || (!isNumberType(monthNum) || isNaN(monthNum))) {
        return maxDay;
    }
    var date=new Date(yearNum, monthNum,1);
    var timeStamp=date.getTime();
    timeStamp-=(24*3600*1000);
    date.setTime(timeStamp);
    maxDay=date.getDate();
    date=null;
    timeStamp=null;
    return maxDay;
}


/**
 * 验证是否有数值型的数据类型
 * @param {Object} num
 */
function isNumberType(num) {
    var typeStatus = false;
    if (num != null) {
        var typeName = typeof (num);
        if (typeName.toLowerCase().trim() === "number") {
            typeStatus = true;
        }
    }
    return typeStatus;
}


/**********************************************************
 * JavaScript实现的ArrayList方法
 * Method:
 *      size();
 *      clear();
 *      add(element);
 *      addAt(index, element);
 *      get(index);
 *      remove(index);
 *DEMO:
 *      var arrList = new ArrayList();
 *      //var arrList = new ArrayList(10);
 *      arrList.add("000");
 *      arrList.add("001");
 *      arrList.add("002");
 *********************************************************/

var ArrayList = function() {
    this.elements = new Array();
    var elementCount = 0;
    this.size = function() {
        return elementCount;
    };
    this.clear = function() {
        this.elements = new Array();
        elementCount = 0;
    };
    this.add = function(element) {
        this.elements.push(element);
        elementCount++;
    };
    this.addAt = function(index, element) {
        if (index >= elementCount) {
            this.elements.push(element);
        }
        if (index <= 0) {
            var tmpElement = this.elements.reverse();
            tmpElement.push(element);
            this.elements = tmpElement.reverse();
            tmpElement = null;
        }
        if (index > 0 && index < elementCount) {
            var tmpElement = new Array(elementCount + 1);
            for (var ii = 0; ii < tmpElement.length; ii++) {
                if (index > ii) {
                    tmpElement[ii] = this.elements[ii];
                }
                if (index == ii) {
                    tmpElement[ii] = element;
                }
                if (index < ii) {
                    tmpElement[ii] = this.elements[ii - 1];
                }
            }
            this.elements = tmpElement;
            tmpElement = null;
        }
        elementCount++;
    };
    this.get = function(index) {
        if (index >= elementCount) {
            alert(" Index Out Of Bounds, " + index + " >= " + elementCount);
            return;
        }
        return this.elements[index];
    };
    this.remove = function(index) {
        if (index >= elementCount) {
            alert(" Index Out Of Bounds, " + index + " >= " + elementCount);
        }
        var tmpElement = new Array(elementCount - 1);
        for (var ii = 0; ii < elementCount; ii++) {
            if (index > ii) {
                tmpElement[ii] = this.elements[ii];
            }
            if (index < ii) {
                tmpElement[ii - 1] = this.elements[ii];
            }
        }
        this.elements = tmpElement;
        tmpElement = null;
        elementCount--;
    };
};



    
var CONSTANT_CYCLE = {
    DAILY : 1, //按日循环
    WEEKLY : 2, //按周循环
    MONTHLY : 3, //按月循环
    YEARLY : 4, //按年循环
    WEEKS_SPLIT : ";",
    DAYS_SPLIT : ","
};



var CONSTANT_WEEK = {
    SUNDAY : 1,
    MONDAY : 2,
    TUESDAY : 3,
    WEDNESDAY : 4,
    THURSDAY : 5,
    FRIDAY : 6,
    SATURDAY : 7
};


/**
 * 根据周期范围、周期类型、周期值取到整个周期中的日期列表
 * 
 * @param {Object} beginDate  开始日期，字符串格式：2013-1-1(或者 2013-01-01)
 * @param {Object} endDate  结束日期，字符串格式：2013-1-1(或者 2013-01-01)
 * @param {Object} cycleType  
 *  			1：按日循环；		2：按周循环；		3：按月循环；	
 * @param {Object} cycleValue
 * 			按日循环时：数字
 *      	按周循环时：由周日、到周六的编码组成的字符串,中间用英文的逗号分开，如：2,3,4,5(表示每周周一、周二、周三、周四)
 *      				对应关系：  1==周日；2==周一；3==周二；4==周三；5==周四；6==周五；7==周六
 *      
 *      	按月循环时：
 *      			如果是每月第几天时，Value是一个数字（数字>=1并且<=31）
 *      
 *      			由周数+周对应的编码组成的字符串,第几周对应的数字与周几用英文的分号分开，如：2;2（表示每月第2周的周一的会议）
 * 
 * 
 * @param {Object} isWorkDay   如果是按天、按月（每月第几天）循环时，调用 是否为工作日 。true ：范围中的工作日； false ：工作日+周末
 * 			工作日：周一到周五按工作日算
 * 			周末按非工作日算
 * 
 * 
 * return array;(由Js 的Date组成 的Array数组)
 */

function getDateListFromCycleScope(beginDate, endDate, cycleType, cycleValue,isWorkDay) {

    var dateArray = null;
    if (!beginDate.isDate() || !endDate.isDate() || !cycleType.isInteger() || cycleValue.isEmpty()) {
        return dateArray;
    }
    var beginTimeStamp = beginDate.parseTimeStamp();
    var endTimeStamp = endDate.parseTimeStamp();
    if (beginTimeStamp > endTimeStamp) {
        return dateArray;
    }
    endTimeStamp=null;
    endTimeStamp=null;
    var dateList = null;
    if (cycleType == CONSTANT_CYCLE.DAILY) {
        dateList = getDailyDays(beginDate, endDate, cycleValue, isWorkDay);
    } else if (cycleType == CONSTANT_CYCLE.WEEKLY) {
        dateList = getWeeklyDays(beginDate, endDate, cycleValue);
    } else if (cycleType == CONSTANT_CYCLE.MONTHLY) {
        dateList = getMonthlyDays(beginDate, endDate, cycleValue, isWorkDay);
    } else if (cycleType == CONSTANT_CYCLE.YEARLY) {

    }

    if (dateList != null && dateList.size() > 0) {
        dateArray = new Array();
        for (var ii = 0; ii < dateList.size(); ii++) {
            dateArray.push(dateList.get(ii));
        }
    }
    dateList = null;
    return dateArray;
}

/**
 * 获取按天循环的会议日期列表
 * @param {Object} beginDate
 * @param {Object} endDate
 * @param {Object} cycleValue   每几天的会议
 * @param {Object} isWorkDay   是否工作日 
 * 
 */
function getDailyDays(beginDate, endDate, cycleValue, isWorkDay) {
    if (!cycleValue.isInteger()) {
        return null;
    }
    var cycleDiff = parseInt(cycleValue, 10);
    var diffTimeStamp = cycleDiff * 24 * 3600 * 1000;
    var eachTimeStamp = beginDate.parseTimeStamp();
    var endTimeStamp = endDate.parseTimeStamp();
    var eachDate = null;
    var eachWeekNum = 0;
    var dateList = new ArrayList();
    while (eachTimeStamp <= endTimeStamp) {
        eachDate = new Date(eachTimeStamp);
        if (isWorkDay) {
            eachWeekNum = eachDate.getDay() + 1;
            if (eachWeekNum >= CONSTANT_WEEK.MONDAY && eachWeekNum <= CONSTANT_WEEK.FRIDAY) {
                dateList.add(eachDate);
            }
        } else {
            dateList.add(eachDate);
        }
        eachTimeStamp += diffTimeStamp;
    }
    diffTimeStamp = null;
    eachTimeStamp = null;
    endTimeStamp = null;
    eachDate = null;
    eachWeekNum = null;
    return dateList;
}


/**
 * 按周循环的选择的日期 
 * @param {Object} beginDate
 * @param {Object} endDate
 * @param {Object} cycleValue  每周多天用逗号分开
 */
function getWeeklyDays(beginDate, endDate, cycleValue){
    if (cycleValue.isEmpty()) {
        return null;
    }
    var eachTimeStamp = beginDate.parseTimeStamp();
    var endTimeStamp = endDate.parseTimeStamp(); 
    var eachDate = null;
    var eachWeekNum = 0;
    var dateList = new ArrayList();
    var diffTimeStamp = 24 * 3600 * 1000;
    var cycleArray=cycleValue.split(CONSTANT_CYCLE.DAYS_SPLIT);
    var eachCycleWeekNum=0;
    while (eachTimeStamp <= endTimeStamp) {
        eachDate = new Date(eachTimeStamp);
        eachWeekNum = eachDate.getDay() + 1;
        for(var jj=0;jj<cycleArray.length;jj++){
            eachCycleWeekNum=parseInt(cycleArray[jj],10);
            if(eachWeekNum==eachCycleWeekNum){
                dateList.add(eachDate);
            }
        }
        eachTimeStamp += diffTimeStamp;
    }
    eachTimeStamp=null;
    endTimeStamp=null;
    eachDate=null;
    eachWeekNum=null;
    diffTimeStamp=null;
    cycleArray=null;
    eachCycleWeek=null;
    return dateList;
};


/**
 * 
 * @param {Object} beginDate
 * @param {Object} endDate
 * @param {Object} cycleValue
 * @param {Object} isWorkDay  是否为工作日: true：仅工作日(周一到周五)；false：一周七天都可以(周一到周日)
 * 
 */

  
function getMonthlyDays(beginDate, endDate, cycleValue, isWorkDay) {
	if (cycleValue.isEmpty() || !beginDate.isDate() || !endDate.isDate()) {
		return null;
	}
	
	var eachTimeStamp = beginDate.parseTimeStamp();
	var endTimeStamp = endDate.parseTimeStamp();
	
	var startTime = new Date(eachTimeStamp);
	var endTime = new Date(endTimeStamp);
	
	var isWeekLoop = false;
	if (cycleValue.indexOf(CONSTANT_CYCLE.WEEKS_SPLIT) > 0) {
		isWeekLoop = true;
	}
	
	var dateList = new ArrayList();
	var eachDate = null;
    var loopDayNum = 0;
    var cycleArray = null;
    var loopDayNumArray = null;
    
	if (isWeekLoop) {	//如果每月第几个周几
		cycleArray = cycleValue.split(CONSTANT_CYCLE.WEEKS_SPLIT);
		if (cycleArray != null && cycleArray.length == 2) {
		    if (cycleArray[0].isInteger() && !cycleArray[1].isEmpty()) {
		        loopDayNum = parseInt(cycleArray[0], 10);
		        if (cycleArray[1].indexOf(CONSTANT_CYCLE.DAYS_SPLIT) > 0) {
		            var tmpDayArray = cycleArray[1].removeSpace().split(CONSTANT_CYCLE.DAYS_SPLIT);
		            loopDayNumArray = new ArrayList();
		            for (var i = 0; i < tmpDayArray.length; i++) {
		                if (tmpDayArray[i].isInteger()) {
		                    loopDayNumArray.add(parseInt(tmpDayArray[i], 10)-1);
		                }
		            }
		        } else {
		            loopDayNumArray = new ArrayList();
		            loopDayNumArray.add(parseInt(cycleArray[1], 10)-1);
		        }
		    }
		}
		while (eachTimeStamp <= endTimeStamp) {
			eachDate = new Date(eachTimeStamp);			
			for(var j=0;j<loopDayNumArray.size();j++) {
				var tempEachDate = getWeekDayOfMonth(eachDate, loopDayNum, parseInt(loopDayNumArray.get(j),10));
				if(tempEachDate && tempEachDate.getTime()>=startTime.getTime() && tempEachDate.getTime()<=endTime.getTime()){
					dateList.add(tempEachDate);
				}
			}
			eachDate.setFullYear(eachDate.getFullYear());
			eachDate.setMonth(eachDate.getMonth()+1);
			eachDate.setDate(1);
			eachTimeStamp = eachDate.getTime();
		}	
	} else {			//每月第几天循环
		if (cycleValue.indexOf(CONSTANT_CYCLE.DAYS_SPLIT) > 0) {
            var tmpDayArray = cycleValue.removeSpace().split(CONSTANT_CYCLE.DAYS_SPLIT);
            loopDayNumArray = new ArrayList();
            for (var i = 0; i < tmpDayArray.length; i++) {
                if (tmpDayArray[i].isInteger()) {
                    loopDayNumArray.add(parseInt(tmpDayArray[i], 10));
                }
            }
        } else {
            loopDayNumArray = new ArrayList();
            loopDayNumArray.add(parseInt(cycleValue, 10));
        }
		
		while (eachTimeStamp <= endTimeStamp) {
			eachDate = new Date(eachTimeStamp);			
			for(var j=0;j<loopDayNumArray.size();j++) {
				var tempEachDate = getDateOfMonth(eachDate, parseInt(loopDayNumArray.get(j),10));
				if(tempEachDate){
					dateList.add(tempEachDate);
				}
			}
			eachDate.setFullYear(eachDate.getFullYear());
			eachDate.setMonth(eachDate.getMonth()+1);
			eachDate.setDate(1);
//			eachDate.setDate(eachDate.getMonth()+1);
			eachTimeStamp = eachDate.getTime();
		}	
	}	
	return dateList;
}

//获取每个月第几个周几的日期
function getWeekDayOfMonth(date, dayNum, day) {
	var tempDate = new Date();
	tempDate.setFullYear(date.getFullYear());
	tempDate.setMonth(date.getMonth());
	tempDate.setDate(date.getDate());
	tempDate.setHours(0);
	tempDate.setMinutes(0) ;
	tempDate.setSeconds(0) ;
	tempDate.setMilliseconds(0);
    var month = tempDate.getMonth(), dates = [];
    tempDate.setDate(1);
    // Get the first week day in the month
    while (tempDate.getDay() !== day) {
        tempDate.setDate(tempDate.getDate() + 1);
    }
    // Get all the other week days in the month
    while (tempDate.getMonth() === month) {
        dates.push(new Date(tempDate.getTime()));
        tempDate.setDate(tempDate.getDate() + 7);
    }   
    if(dates.length>0){
        return dates [dayNum-1];
    }
    return dates ;
}

//获取每月第几天
function getDateOfMonth(date, day) {
	var tempDate = new Date();
	tempDate.setFullYear(date.getFullYear());
	tempDate.setMonth(date.getMonth());
	tempDate.setDate(date.getDate());
	tempDate.setHours(0);
	tempDate.setMinutes(0) ;
	tempDate.setSeconds(0) ;
	tempDate.setMilliseconds(0);
    var month = tempDate.getMonth(), dates = [];	
    tempDate.setDate(1);
    // Get the first day in the month
    while (tempDate.getDate() !== day) {
        tempDate.setDate(tempDate.getDate() + 1);
    }
    // Make sure Get the first days in current month
    while (tempDate.getMonth() === month) {
        dates.push(new Date(tempDate.getTime()));
        tempDate.setDate(tempDate.getDate() + 1);
    }  
    return dates[0] ;
}

//    if (cycleValue.isEmpty()) {
//        return null;
//    }
//    var eachTimeStamp = beginDate.parseTimeStamp();
//    var endTimeStamp = endDate.parseTimeStamp();
//
//    var loopWeekNum = 0;
//    var dateList = new ArrayList();
//    var diffTimeStamp = 24 * 3600 * 1000;
//
//    var isWeekLoop = false;
//    if (cycleValue.indexOf(CONSTANT_CYCLE.WEEKS_SPLIT) > 0) {
//        isWeekLoop = true;
//    }
//    var loopDayNumArray = null;
//    var cycleArray = null;
//    //cycleValue.split(CONSTANT_CYCLE.DAYS_SPLIT);
//    var eachCycleWeekNum = 0;
//
//    if (isWeekLoop) {//如果每月第几个周几
//        cycleArray = cycleValue.split(CONSTANT_CYCLE.WEEKS_SPLIT);
//        if (cycleArray != null && cycleArray.length == 2) {
//            if (cycleArray[0].isInteger() && !cycleArray[1].isEmpty()) {
//                loopWeekNum = parseInt(cycleArray[0], 10);
//                if (cycleArray[1].indexOf(CONSTANT_CYCLE.DAYS_SPLIT) > 0) {
//                    var tmpWeekDayArray = cycleArray[0].removeSpace().split(CONSTANT_CYCLE.DAYS_SPLIT);
//                    loopDayNumArray = new ArrayList();
//                    for (var ii = 0; ii < tmpWeekDayArray.length; ii++) {
//                        if (tmpWeekDayArray[ii].isInteger()) {
//                            loopDayNumArray.add(parseInt(tmpWeekDayArray[ii], 10));
//                        }
//                    }
//                } else {
//                    loopDayNumArray = new ArrayList();
//                    loopDayNumArray.add(parseInt(cycleArray[1], 10));
//                }
//            }
//        }
//    } else {//每月第几天循环
//        if (cycleValue.indexOf(CONSTANT_CYCLE.DAYS_SPLIT) > 0) {
//            cycleArray = cycleValue.split(CONSTANT_CYCLE.DAYS_SPLIT);
//            if (cycleArray != null && cycleArray.length > 0) {
//                loopDayNumArray = new ArrayList();
//                for (var ii = 0; ii < cycleArray.length; ii++) {
//                    if (cycleArray[ii].isInteger()) {
//                        loopDayNumArray.add(parseInt(cycleArray[ii], 10));
//                    }
//                }
//            }
//        } else {
//            if (cycleValue.isInteger()) {
//                loopDayNumArray = new ArrayList();
//                loopDayNumArray.add(parseInt(cycleValue, 10));
//            }
//        }
//    }
//
//    var dateList = new ArrayList();
//    var eachDate = null;
//    var eachWeekNum = 0;
//    var eachMonthDay = 0;
//    var eachWeekItem = 0;
//    while (eachTimeStamp <= endTimeStamp) {
//        eachDate = new Date(eachTimeStamp);
//        if (isWeekLoop) {
//            eachWeekNum = eachDate.getWeekOfMonth();
//            if (eachWeekNum == loopWeekNum) {
//                eachWeekItem = eachDate.getDay() + 1;
//                for (var ii = 0; ii < loopDayNumArray.size(); ii++) {
//                    if ((loopDayNumArray.get[ii], 10) == eachWeekItem) {
//                        dateList.add(eachDate);
//                    }
//                }
//            }
//        } else {
//            eachMonthDay = eachDate.getDate();
//            for (var ii = 0; ii < loopDayNumArray.size(); ii++) {
//                if (parseInt(loopDayNumArray.get(ii),10) == parseInt(eachMonthDay,10)) {
//                    if (isWorkDay) {
//                        eachWeekItem = eachDate.getDay() + 1;
//                        if (eachWeekItem >= CONSTANT_WEEK.MONDAY && eachWeekItem <= CONSTANT_WEEK.FRIDAY) {
//                            dateList.add(eachDate);
//                        }
//                    } else {
//                        dateList.add(eachDate);
//                    }
//                }
//            }
//        }
//
//        eachTimeStamp += diffTimeStamp;
//    }
//
//    return dateList;


























