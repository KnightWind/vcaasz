/*=======================================*/
/*	martin Wang                            */
/*	email:martin_wang@bizconf.cn	       */
/*=======================================*/
	$(document).ready(function(){
		$(".submit-search").click(function(){
			$("#pageNo").val('');
			$(this).closest("form").submit();
		});
	});
	//计算字符比特长度
	function byteLength(str) {
		var byteLen = 0, len = str.length;
		if( !str ) return 0;
		for( var i=0; i<len; i++ )
			byteLen += str.charCodeAt(i) > 255 ? 2 : 1;
		return byteLen;
	}
	//防止退格键乱退
	$(document).keydown(function(e){ 
		var doPrevent; 
		if (e.keyCode == 8) { 
			var d = e.srcElement || e.target; 
			if (d.tagName.toUpperCase() == 'INPUT' || d.tagName.toUpperCase() == 'TEXTAREA') { 
				doPrevent = d.readOnly || d.disabled; 
			}else{
				doPrevent = true; 
			} 
		}else{
			doPrevent = false; 
		}
		if (doPrevent){
			e.preventDefault(); 
		} 
	}); 
	
	
	
	var userAgent = navigator.userAgent,   
	 rMsie = /(msie\s|trident.*rv:)([\w.]+)/,   
	 rFirefox = /(firefox)\/([\w.]+)/,   
	 rOpera = /(opera).+version\/([\w.]+)/,   
	 rChrome = /(chrome)\/([\w.]+)/,   
	 rSafari = /version\/([\w.]+).*(safari)/;  
	 var browser;  
	 var version;  
	 var ua = userAgent.toLowerCase();  
	 
function uaMatch(ua) {  
	  var match = rMsie.exec(ua);  
	  if (match != null) {  
	      return { browser : "IE", version : match[2] || "0" };  
	  }  
	  var match = rFirefox.exec(ua);  
	  if (match != null) {  
	      return { browser : match[1] || "", version : match[2] || "0" };  
	  }  
	  var match = rOpera.exec(ua);  
	  if (match != null) {  
	      return { browser : match[1] || "", version : match[2] || "0" };  
	   }  
	   var match = rChrome.exec(ua);  
	   if (match != null) {  
	      return { browser : match[1] || "", version : match[2] || "0" };  
	   }  
	   var match = rSafari.exec(ua);  
	   if (match != null) {  
	      return { browser : match[2] || "", version : match[1] || "0" };  
	   }  
	   if (match != null) {  
	                        return { browser : "", version : "0" };  
	   		}  
} 
var browserInfo = uaMatch(userAgent.toLowerCase());

function getCurrentBrowser(){
	 if (browserInfo.browser) {  
	     	return browserInfo.browser;  
	 }else{
		 return null;
	 } 
}

//判断启动会议时，是否只需要对用户名encode一次
function startMeetingEncodeOnce(){
	var type = browserInfo.browser;
	var version = parseInt(browserInfo.version);
	if(type=="IE" && version>9 || type=="safari" || type=="firefox" || type=="chrome"){
		return true;
	}
	
	return false;
}