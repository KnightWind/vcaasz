/*=======================================*/
/*	Alan Liu                             */
/*	email:mingliu@bizconf.cn	         */
/*	2013-07-23                           */
/*=======================================*/

(function($){
    $.fn.contentTabs = function(o){
        // default options
        var options = $.extend({
            activeClass:'active',
            tabLinks:'a'
        },o);
        return this.each(function(){
            var tabUl = $(this);//ul
            var tabLinks = tabUl.find(options.tabLinks);//a
            var tabLinksParents = tabLinks.parent();//li
            var prevActiveLink = tabLinks.eq(0), currentTab;
            // init tabLinks
            tabLinks.each(function(){
                var link = $(this);
                // event handler
                link.bind("click", function(e){
                    if(link != prevActiveLink) {
                        switchTab(prevActiveLink, link);
                        prevActiveLink = link;
                    }
                    e.preventDefault();
                });
            });

            // tab switch function
            function switchTab(oldLink, newLink) {
                // refresh pagination links
                var parent = newLink.parent();
                tabLinksParents.removeClass(options.activeClass);
                parent.addClass(options.activeClass);
            }
        });
    }
}(jQuery));

/**
 *jquery timeago
 *
 */

(function($){
    // default options
    var defaults = {
        defaultDate: "",
        refreshMillis: 60000,
        allowFuture: true,
        friendly: true,
        strings: {
            prefixAgo: null,
            prefixFromNow: null,
            suffixAgo: "ago",
            suffixFromNow: "from now",
            seconds: "less than a minute",
            minute: "about a minute",
            minutes: "%d minutes",
            hour: "about an hour",
            hours: "about %d hours",
            day: "a day",
            days: "%d days",
            month: "about a month",
            months: "%d months",
            year: "about a year",
            years: "%d years"
        }
    };
    var methods = {
        init: function(options){
            defaults = $.extend(true, defaults, options);
            var refresh_el = $.proxy(refresh, this);
            refresh_el();
            if (defaults.refreshMillis > 0) {
                setInterval(refresh_el, defaults.refreshMillis);
            }
        },
        update: function(time){
          $(this).data('timeago', { datetime: parse(time) });
          refresh.apply(this);
        }
    };

    $.fn.tinyTimeAgo = function() {
        var method = arguments[0];
        if(methods[method]) {
            method = methods[method];
            arguments = Array.prototype.slice.call(arguments, 1);
        } else if( typeof(method) == 'object' || !method ) {
            method = methods.init;
        } else {
            $.error( 'Method ' +  method + ' does not exist on jQuery.pluginName' );
            return this;
        }
        return this.each(function(){
            method.apply(this, arguments);
        });
    }
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
    function parse(dateStr) {
        var tmpArr=dateStr.split(/[-:\s]/);
        if(tmpArr.length != 3 && tmpArr.length != 6){
            return null;
        }
        var date = new Date(tmpArr[0],(parseInt(tmpArr[1],10)-1),tmpArr[2]);
        if(tmpArr.length == 6){
            date=new Date(tmpArr[0],(parseInt(tmpArr[1],10)-1),tmpArr[2],tmpArr[3],tmpArr[4],tmpArr[5]);
        }
        tmpArr=null;
        return date;
    }
    function isTime(elem) {
      // jQuery's `is()` doesn't play well with HTML5 in IE
      return $(elem).get(0).tagName.toLowerCase() === "time"; // $(elem).is("time");
    }
    function datetime(elem) {
        var dateStr = isTime(elem) ? $(elem).attr("datetime") : $(elem).attr("title");
        return parse(dateStr);
    }    

    function prepareData(element) {
        element = $(element);
        if (!element.data("timeago")) {
            element.data("timeago", { datetime: datetime(element) });
        }
        return element.data("timeago");
    }

    function getCurrentDate() {
        var currentDate = new Date();
        if(defaults.defaultDate) {
            currentDate = parse(defaults.defaultDate);
        }
        return currentDate;
    }

    function distance(date) {
        var currentDate = getCurrentDate();
        return (currentDate.getTime() - date.getTime());
    }

    function substitute(string, number) {
        return string.replace(/%d/i, number);
    }

    function getWords(distanceMillis) {
        var $l = defaults.strings;
        var seconds = Math.abs(distanceMillis) / 1000;
        var minutes = seconds / 60;
        var hours = minutes / 60;
        var days = hours / 24;
        var years = days / 365;
        var words = seconds < 45 && substitute($l.seconds, Math.round(seconds)) ||
            seconds < 90 && substitute($l.minute, 1) ||
            minutes < 45 && substitute($l.minutes, Math.round(minutes)) ||
            minutes < 90 && substitute($l.hour, 1) ||
            hours < 24 && substitute($l.hours, Math.round(hours)) ||
            hours < 42 && substitute($l.day, 1) ||
            days < 30 && substitute($l.days, Math.round(days)) ||
            days < 45 && substitute($l.month, 1) ||
            days < 365 && substitute($l.months, Math.round(days / 30)) ||
            years < 1.5 && substitute($l.year, 1) ||
            substitute($l.years, Math.round(years));
        return words;
    }

    function switchTitle(elem, date) {
        var distanceMillis = distance(date);
        var words = getWords(distanceMillis);
        var $l = defaults.strings;
        var prefix = $l.prefixAgo;
        var suffix = $l.suffixAgo;
        if (defaults.allowFuture) {
            if (distanceMillis < 0) {
                prefix = $l.prefixFromNow;
                suffix = $l.suffixFromNow;
            }
        }
        var status = $(elem).attr("status");
        if (status=="past") {
            prefix = "";
            suffix = "前加入的会议";
        } else if(status=="come"){
            prefix = "";
            suffix = "后开始会议";
        } else if(status=="run"){
            prefix = "会议已开始";
            suffix = "";    
        } else if(status=="miss"){
            prefix = "";
            suffix = "前错过的会议";    
        }
        return $.trim([prefix, words, suffix].join(" "));
    }
  
    function switchWords(elem, startDate) {
        var text = formatDate(startDate);
        var currentDate = getCurrentDate();
        if(currentDate.getFullYear()==startDate.getFullYear() && currentDate.getMonth()==startDate.getMonth()){ 
            var diffDay = startDate.getDate() - currentDate.getDate(); 
            if(diffDay==0){
                text = "今天";
            } else if(diffDay==1) {
                text = "明天";
            } else if(diffDay==-1) {
                text = "昨天";
            } 
        } 
        return text;
    }

    function refresh() {
        var data = prepareData(this);
        var title = switchTitle(this, data.datetime);
        if (isTime(this)) {
            $(this).attr("datetime", title);
        } else {
            $(this).attr("title", title);
        }

        if (defaults.friendly) {
            var text = switchWords(this, data.datetime);
            $(this).find(".date").text(text);
        } 
        return this;
    }
}(jQuery));

/**
 *jquery tabs
 *
 */

(function($){
    $.fn.slideBar = function(o){
        // default options
        var options = $.extend({
            activeClass: 'active',
            hoverClass: 'hover',
            ignoreClass: 'ignore',
            panelOffClass: 'nav-ul-off',
            panelOnClass: 'nav-ul-on',
            tabLinks:'a',
            clickCallback: null
        },o);
        return this.each(function(){
            var tabUl = $(this);//ul
            tabUl.data("slideBar", options);
            var tabLinks = tabUl.find(options.tabLinks);//a
            var tabLinksParents = tabLinks.parent();//li
            var prevActiveLink = tabLinks.eq(0), currentTab;
            // init tabLinks
            tabLinks.each(function(){ 
                var link = $(this);
                // event handler
                if(!link.hasClass(options.ignoreClass)){
                    link.bind("click", function(e){
                        switchTab(prevActiveLink, link);
                        if(link != prevActiveLink) {
                            prevActiveLink = link;
                        }
                        if (options.clickCallback) {
                            options.clickCallback(link);
                        } 
                        link.blur();
                        e.preventDefault();
                    });
                }
                link.hover(
            		function () {
            			if(!link.parent().hasClass(options.activeClass)) {
	            			$(this).addClass(options.hoverClass);	
            			}
        			},
        			function () {
        				$(this).removeClass(options.hoverClass);
        			}
        		);
            });

            // tab switch function
            function switchTab(oldLink, newLink) {
                // refresh pagination links
                var parent = newLink.parent();
                var subUL = newLink.next();
                if (subUL && subUL.length>0) {
                    if (subUL.is(":visible")) {
                        newLink.removeClass(options.panelOnClass).addClass(options.panelOffClass);
                        if ($.browser.msie && $.browser.version<8) {
                            subUL.hide();
                        } else {
                            subUL.slideUp();   
                        }
                    } else{
                        newLink.removeClass(options.panelOffClass).addClass(options.panelOnClass);
                        if ($.browser.msie && $.browser.version<8) {
                            subUL.show();
                        } else {
                            subUL.slideDown();
                        }
                    };
                } else {
                    tabLinksParents.removeClass(options.activeClass);
                    parent.addClass(options.activeClass);  
                } 
            }
        });
    };

    $.fn.jumpTo = function(index) {
        var tabUl = $(this);//ul
        var options = tabUl.data("slideBar");
        if(options){
            var tabLinks = tabUl.find(options.tabLinks);//a
            var tabLinksParents = tabLinks.parent();//li
            tabLinksParents.removeClass(options.activeClass);
            tabLinksParents.eq(index-1).addClass(options.activeClass);
        }
    };

    $.fn.findTo = function(className) {
        var tabUl = $(this);//ul
        var options = tabUl.data("slideBar");
        if(options){
            var tabLinks = tabUl.find(options.tabLinks);//a
            var tabLinksParents = tabLinks.parent();//li
            var tabLi = tabUl.find(className).parent();//li
            tabLinksParents.removeClass(options.activeClass);
            tabLi.addClass(options.activeClass);
        }
    };
}(jQuery));


/**
 *jquery tiny tabs
 *
 */

(function($){
    $.fn.tinyTabs = function(o){
        // default options
        var options = $.extend({
            activeClass:'active',
            activeTabClass:'tab-active',
            panelOffClass: 'nav-ul-off',
            panelOnClass: 'nav-ul-on',
            tabLinks:'a',
            clickCallback: null
        },o);
        return this.each(function(){
            var tabUl = $(this);//ul
            tabUl.data("slideBar", options);
            var tabLinks = tabUl.find(options.tabLinks);//a
            var tabLinksParents = tabLinks.parent();//li
            var prevActiveLink = tabLinks.eq(0), prevActiveTab;
            // init tabLinks
            tabLinks.each(function(){ 
                var link = $(this);
                var parent = link.parent();
                var tabId = link.attr("id");
                var tab =$("#"+tabId, window.parent.document);

                if (parent.hasClass(options.activeClass)) {
                    prevActiveLink = link;
                    prevActiveTab = tab;
                }
                // event handler
                link.bind("click", function(e){
                    var currentTabId = $(this).attr("id");
                    var currentTab = $("#"+currentTabId, window.parent.document);
                    switchMenu(prevActiveLink, link);
                    switchTab(prevActiveTab, currentTab);
                    if(link != prevActiveLink) {
                        prevActiveLink = link;
                        prevActiveTab = currentTab;
                    }
                    if (options.clickCallback) {
                        options.clickCallback(link);
                    } 
                    e.preventDefault();
                });
            });

            // tab switch function
            function switchMenu(oldLink, newLink) {
                // refresh pagination links
                var parent = newLink.parent();
                var subUL = newLink.next();
                if (subUL && subUL.length>0) {
                    if (subUL.is(":visible")) {
                        newLink.removeClass(options.panelOnClass).addClass(options.panelOffClass);
                        if ($.browser.msie && $.browser.version<8) {
                            subUL.hide();
                        } else {
                            subUL.slideUp();   
                        }
                    } else{
                        newLink.removeClass(options.panelOffClass).addClass(options.panelOnClass);
                        if ($.browser.msie && $.browser.version<8) {
                            subUL.show();
                        } else {
                            subUL.slideDown();
                        }
                    }
                } else {
                    if (oldLink!=newLink) {
                        oldLink.parent().removeClass(options.activeClass);
                        parent.addClass(options.activeClass);  
                    }
                } 
            }

            function switchTab(oldTab, newTab) {
                if (oldTab!=newTab) {
                    oldTab.removeClass(options.activeTabClass);
                    newTab.addClass(options.activeTabClass);  
                }
            }
        });
    };
}(jQuery));

/**
 *jquery tiny load
 *
 */

(function($){
    $.fn.tinyLoad = function(o){
        // default options
        var options = $.extend({
            message:'登录中...',
            color: '#BBBBBB',
            trigger: 'manual'  //manual
        },o);
        return this.each(function(){
            var elem = $(this);//bind element
            var elemText = $(this).text();
            var loadingImg = $("<i class='icon-button dn'>&nbsp;</i>").prependTo(elem);
            elem.bind(options.trigger, function() {
            	elem.attr("disabled", "disabled");
            	loadingImg.removeClass("dn").addClass("dib");
            	elem.val(options.message);
            });
            elem.bind("loaded", function() {
            	elem.removeAttr("disabled");
            	loadingImg.removeClass("dib").addClass("dn");
            	elem.val(elemText);
            });
        });
    };
}(jQuery));

/**
 *jquery tiny table sort
 *
 */

(function($){
    $.fn.tinySort = function(o){
        // default options
        var options = $.extend({
        	upIconClass: 'tbl-sort-icon-s',
        	downIconClass: 'tbl-sort-icon-n',
        	iconClass: 'tbl-sort-icon-d'
        },o);
        
        return this.each(function(){
            var elem = $(this);
            var icon = elem.find("."+options.iconClass);
            elem.toggle(function() {
            	icon.removeClass(options.iconClass);
            	icon.removeClass(options.upIconClass);
            	icon.addClass(options.downIconClass);
            }, function() {
            	icon.removeClass(options.iconClass);
            	icon.removeClass(options.downIconClass);
            	icon.addClass(options.upIconClass);
            });
        });
    };
}(jQuery));