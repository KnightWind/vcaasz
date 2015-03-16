<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<cc:base var="COOKIE_SPLIT"/>
<script type="text/javascript">

	
	String.prototype.trim=function(){
		return this.replace(/(^\s*)|(\s*$)/g,"");
	};
	
	String.prototype.Trim=function(){
		return this.replace(/(^\s*)|(\s*$)/g,"");
	};
	/**
	 *${LANG['bizconf.jsp.common.cookie_util.res1']}
	 */
	String.prototype.isEmpty = function() {
		if (this == null || this.trim().length == 0) {
			return true;
		} else {
			return false;
		}
	};
	
	function getMainDomain(){
		var domain=document.domain;
		if(domain!=null && domain.length>0){
			var firstDotIndex=domain.indexOf(".");
			domain=domain.substr(firstDotIndex+1);
		}
		return domain;
	}
	
	function getDomain(){
		return document.domain;
	}
	function getCookieByDomain(cName,domain){
		var valueSplit="${COOKIE_SPLIT}";
		var cookie=null;
		var cValue=null;
		if(cName!=null && cName.trim()!=""){
			cookie=document.cookie;
			var cookieArray=cookie.split(";");
			if(cookieArray!=null && cookieArray.length>0){
				var eachCookie=null;
				var eachCookieArray=null;
				var eachName=null;
				var eachValue=null;
				var eachDomain=null;
				var eachTrueValue=null;
				var eachValueArray=null;
				for(var ii=0;ii<cookieArray.length;ii++){
					if(cValue!=null && cValue.trim()!=""){
						break;
					}
					eachCookie=cookieArray[ii];
					if(eachCookie!=null && eachCookie.trim()!=""){
						eachCookieArray=eachCookie.split("=");
						if(eachCookieArray!=null && eachCookieArray.length==2){
							eachName=eachCookieArray[0];
							if(eachName.trim()==cName){
								eachValue=eachCookieArray[1];
								if(domain==null && domain.trim()==""){
									cValue=eachValue;
								}else{
									if(eachValue!=null && eachValue.trim()!=""){
										 eachValueArray=eachValue.split(valueSplit);
									}
									if(eachValueArray!=null && eachValueArray.length==2){
										eachTrueValue=eachValueArray[0];
										eachDomain=eachValueArray[1];
										if(domain==eachDomain.trim()){
											cValue=eachTrueValue;
										}
									}
								}
							}
						}
					}
					eachCookie=null;
					eachCookieArray=null;
					eachName=null;
					eachValue=null;
					eachDomain=null;
					eachTrueValue=null;
					eachValueArray=null;
				}
			}
			
		}
		
		return cValue;
	}
	


	function setCookie(cName, cValue, domain) {
		if (!cName.isEmpty() && !cValue.isEmpty()) {
			var cookieSplit="${COOKIE_SPLIT}";
			//var timeStamp = (new Date()).getTime();
			//timeStamp += (hours * 3600 * 1000);
			//var expireDate = new Date(timeStamp).toGMTString();
			var saveValue = cValue;
			if (!domain.isEmpty()) {
				saveValue = cValue + cookieSplit + domain;
			}
			document.cookie = cName + "=" +  saveValue  + ";path=/;domain=" + domain + ";expires=";// + expireDate;
			timeStamp = null;
			saveValue = null;
			//expireDate = null;
		}
	}

	/**
	 * ${LANG['bizconf.jsp.common.cookie_util.res2']}Cookie
	 * @param {Object} cName
	 * @param {Object} domain
	 */
	function clearCookie(cName, domain) {
		if (!cName.isEmpty()) {
			var timeStamp = (new Date()).getTime();
			timeStamp -= 1000;
			var expireDate = new Date(timeStamp).toGMTString();
			var saveValue = "";
			if (!domain.isEmpty()) {
				document.cookie = cName + "=" + saveValue + ";path=/;domain=" + domain + ";expires=" + expireDate;
			} else {
				document.cookie = cName + "=" + saveValue + ";path=/;expires=" + expireDate;
			}
			timeStamp = null;
			saveValue = null;
			expireDate = null;
		}
	}

	
	function getBrowserLang() {
		var domain = getMainDomain();
		var baseLang = getCookieByDomain("LANG",domain);
		if (!baseLang) {
			if (navigator.userLanguage) {
				baseLang = navigator.userLanguage.substring(0,2).toLowerCase();
			} else {
				baseLang = navigator.language.substring(0,2).toLowerCase();
			}
			/* language match */
			switch(baseLang)
			{
				case "en":
					/* english */
					baseLang = "en-us";
					break;
				case "zh":
					/* ${LANG['bizconf.jsp.common.cookie_util.res3']} */
					baseLang = "zh-cn";
					break;
				default:
					/* default no match */
			}
		}
		return baseLang;
	}	
</script>
