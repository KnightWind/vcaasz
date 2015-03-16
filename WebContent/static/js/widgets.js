/**
 * custom widget for dialog
 */

$.widget("ui.showDialog",{
    options: {
        title: "",
        dialogClass: "",
        url: "",
        data: null,
        type: "",
        action: "",
        width: 200,
        height: 200,
        hideClose: null
    },
    _create: function() {
    	var self = this;
    	var left = (self.options.width-32)/2;
    	var top  = 80;
    	if(self.options.height!="auto"){
        	top  = (self.options.height-32)/2;	
    	}
    	//加载的样式图片修改隐藏
    	this.loading = $("<img src='/static/images/loading.gif' style='width:0px;height:0px;position:absolute;'>").appendTo(this.element);
    	this.loading.css({"top": top, "left": left});
    	if (self.options.data) {
    		this.element.data("data", self.options.data);
    	}
    	var frameHtml="<iframe allowTransparency='true' id='dialogFrame' name='dialogFrame' src="+self.options.url+"  width="+self.options.width+" height="+self.options.height+" frameborder='0'  scrolling='no' style='background:transparent' />";
    	this.element.append(frameHtml);
    	this.element.bind("closeDialog", function(event, result) {
    		if (result) {
        		if (result.status==1) {
            		self.destroy();
        			if (self.options.action=="create") {
        				$("body").trigger(EVENT_CREATE, [result, self.options.type]);
        			} else {
        				$("body").trigger(EVENT_UPDATE, [result, self.options.type]);
        			}		
	    		} else {			   
	    			$("body").trigger(EVENT_ERROR, [result, self.options.type]);
	    		}
    		} else {
        		self.destroy();
    		}
    	});
    	this.element.bind("refresh", function(event, url) {
    		$("#dialogFrame").attr("src", url);
    	});
    	this.element.bind("loaded", function(event, result) {
    		self.loading.hide();
    		//fixed ie iframe background transparent
    		if ($.browser.msie) {
    			if($.browser.version>8){
    	    		self.element.find("iframe").css("background-color", "transparent");		
    			} else {
    				self.element.find("iframe").css("background", "transparent");
    			}
    		}
    	});
    	this.element.bind("resize", function(event, result) {
    		self.element.find("#dialogFrame").height(result);
    		if(self.dialog) {
        		self.dialog.dialog({ position: 'center' });	
    		}
    	});   	
    },
    _init: function() {
    	var self = this;
    	var dialogModal = true;
//    	var overlay = $(".ui-widget-overlay");
//    	if(overlay && overlay.length>0){
//    		dialogModal = false;
//    	}
    	this.dialog = this.element.dialog({
    		title: self.options.title,
    		dialogClass: self.options.dialogClass,
			autoOpen: true,
			resizable: false,
			modal: dialogModal,
			width: self.options.width,
			height: "auto",
			close: function() {
				self.destroy();
			}
		});
    	if(self.options.hideClose){
    		this.element.prev().find(".ui-dialog-titlebar-close").hide();
    	}
    },
    destroy: function() {
        $.Widget.prototype.destroy.apply(this, arguments);
        this.element.empty().remove();
    }
});
$.widget("ui.warningDialog",{
    options: {
    	title: "",
        message: null,
        dialogClass: "",
        actions: [],
        callback: null
    },
    _create: function() {
    	var self = this;
    	this.element.attr("title", self.options.title);
    	this.messageContainer = $("<div style='text-align:center;margin: 20px auto;'></div>").appendTo(this.element);
    	$("<img src='/static/images/warn_bg.jpg' style='margin-right:15px;width:23px;height:26px;'/>").appendTo(this.messageContainer);
    	this.message = $("<span></span>").appendTo(this.messageContainer);
    	this.message.text(this.options.message);
    	var btnContainer = $("<div style='text-align:center'></div>").appendTo(this.element);
    	var OkBtn = $("<input class='form-button' type='button' value='"+self.options.actions[0]+"' />").appendTo(btnContainer);
    	OkBtn.click(function() {
    		self.closeDialog();
    		if(self.options.callback) {
    			self.options.callback();
    		}     	    		
    	});
    	
    },
    _init: function() {
    	var self = this;
    	this.dialog = this.element.dialog({
    		dialogClass: self.options.dialogClass,
			autoOpen: true,
			resizable: false,
			modal: true,
			width: 320,
			height: 160,
			close: function() {
				self.closeDialog();
			}
		});
    	
    },
    destroy: function() {
        $.Widget.prototype.destroy.apply(this, arguments);
        this.element.empty().remove();
    },
    closeDialog: function() {
    	var self = this;
		self.destroy();
    }
});
$.widget("ui.errorDialog",{
    options: {
    	title: "",
        message: null,
        dialogClass: "",
        actions: [],
        callback: null
    },
    _create: function() {
    	var self = this;
    	this.element.attr("title", self.options.title);
    	this.messageContainer = $("<div style='text-align:center;margin: 20px auto;'></div>").appendTo(this.element);
    	$("<img src='/static/images/lose_bg.gif' style='margin-right:15px;width:23px;height:26px;'/>").appendTo(this.messageContainer);
    	this.message = $("<span></span>").appendTo(this.messageContainer);
    	this.message.text(this.options.message);
    	var btnContainer = $("<div style='text-align:center'></div>").appendTo(this.element);
    	var OkBtn = $("<input class='form-button' type='button' value='"+self.options.actions[0]+"' />").appendTo(btnContainer);
    	OkBtn.click(function() {
    		self.closeDialog();
    		if(self.options.callback) {
    			self.options.callback();
    		}     
    	});
    	
    },
    _init: function() {
    	var self = this;
    	this.dialog = this.element.dialog({
    		dialogClass: self.options.dialogClass,
			autoOpen: true,
			resizable: false,
			modal: true,
			width: 320,
			height: 160,
			close: function() {
				self.closeDialog();
			}
		});
    	
    },
    destroy: function() {
        $.Widget.prototype.destroy.apply(this, arguments);
        this.element.empty().remove();
    },
    closeDialog: function() {
    	var self = this;
		self.destroy();	
    }
});

$.widget("ui.successDialog",{
    options: {
    	title: "",
    	dialogClass: "",
        message: null,
        actions: [],
        callback: null
    },
    _create: function() {
    	var self = this;
    	this.element.attr("title", self.options.title);
    	this.messageContainer = $("<div style='text-align:center;margin: 20px auto;'></div>").appendTo(this.element);
    	$("<img src='/static/images/success_bg.jpg' style='margin-right:15px;width:27px;height:26px;'/>").appendTo(this.messageContainer);
    	this.message = $("<span></span>").appendTo(this.messageContainer);
    	this.message.text(this.options.message);
    	var btnContainer = $("<div style='text-align:center'></div>").appendTo(this.element);
    	var OkBtn = $("<input class='form-button' type='button' value='"+self.options.actions[0]+"' />").appendTo(btnContainer);
    	OkBtn.click(function() {
    		self.closeDialog();
    		if(self.options.callback) {
    			self.options.callback();
    		}        		
    	});
    },
    _init: function() {
    	var self = this;
    	this.dialog = this.element.dialog({
    		dialogClass: self.options.dialogClass,
			autoOpen: true,
			resizable: false,
			modal: true,
			width: 320,
			height: 160,
			close: function() {
				self.closeDialog();
			}
		});
    	
    },
    destroy: function() {
        $.Widget.prototype.destroy.apply(this, arguments);
        this.element.empty().remove();
    },
    closeDialog: function() {
    	var self = this;
		self.destroy(); 	
    }
});
$.widget("ui.confirmDialog",{
    options: {
    	title: "",
    	dialogClass: "",
        message: null,
        actions: [],
        callback: null
    },
    _create: function() {
    	var self = this;
    	this.element.attr("title", self.options.title);
    	this.messageContainer = $("<div style='text-align:center;margin: 20px auto;'></div>").appendTo(this.element);
    	$("<img src='/static/images/warn_bg.jpg' style='margin-right:15px;width:30px;height:26px;'/>").appendTo(this.messageContainer);
    	this.message = $("<span></span>").appendTo(this.messageContainer);
    	this.message.text(this.options.message);
    	var btnContainer = $("<div style='text-align:center'></div>").appendTo(this.element);
    	var OkBtn = $("<input class='form-button' type='button' value='"+self.options.actions[0]+"' />").appendTo(btnContainer);
    	OkBtn.click(function() {
    		self.closeDialog();
    		if(self.options.callback) {
    			self.options.callback();
    		}      		
    	});
    	var CancelBtn = $("<input class='form-button' style='margin-left: 50px;' type='button' value='"+self.options.actions[1]+"' />").appendTo(btnContainer);
    	CancelBtn.click(function() {
    		self.destroy();
    	});
    },
    _init: function() {
    	var self = this;
    	this.dialog = this.element.dialog({
    		dialogClass: self.options.dialogClass,
			autoOpen: true,
			resizable: false,
			modal: true,
			width: 320,
			height: 160,
			close: function() {
				self.closeDialog();
			}
		});
    	
    },
    destroy: function() {
        $.Widget.prototype.destroy.apply(this, arguments);
        this.element.empty().remove();
    },
    closeDialog: function() {
    	var self = this;
		self.destroy();   	
    }
});

$.widget("ui.excelDialog",{
    options: {
    	title: "",
    	dialogClass: "",
        message: null,
        actions: [],
        width: null,
        height: null,
        callback: null
    },
    _create: function() {
    	var self = this;
    	this.element.attr("title", self.options.title);
    	this.messageContainer = $("<div style='text-align:center;margin: 20px auto;'></div>").appendTo(this.element);
    	$("<img src='/static/images/success_bg.jpg' style='margin-right:15px;width:27px;height:26px;'/>").appendTo(this.messageContainer);
    	this.message = $("<span></span>").appendTo(this.messageContainer);
    	this.message.html(this.options.message);
    	var btnContainer = $("<div style='text-align:center'></div>").appendTo(this.element);
    	var OkBtn = $("<input class='form-button' type='button' value='"+self.options.actions[0]+"' />").appendTo(btnContainer);
    	OkBtn.click(function() {
    		self.closeDialog();
    		if(self.options.callback) {
    			self.options.callback();
    		}        		
    	});
    },
    _init: function() {
    	var self = this;
    	var width = 320;
    	if(self.options.width){
    		width = self.options.width;
    	}
    	var height = 160;
    	if(self.options.height) {
    		height = self.options.height;
    	}
    	this.dialog = this.element.dialog({
    		dialogClass: self.options.dialogClass,
			autoOpen: true,
			resizable: false,
			modal: true,
			width: width,
			height: height,
			close: function() {
				self.closeDialog();
			}
		});
    	
    },
    destroy: function() {
        $.Widget.prototype.destroy.apply(this, arguments);
        this.element.empty().remove();
    },
    closeDialog: function() {
    	var self = this;
		self.destroy(); 	
    }
});
$.widget("ui.alertDialog",{
    options: {
    	title: "",
    	dialogClass: "",
    	type: "success", //success, error, confirm
        message: null,
        actions: [],
        callback: null
    },
    _create: function() {
    	var self = this;
    	var table = $('<table class="overlay-panel" cellspacing="0" cellpadding="0" border="0"></table>').appendTo(this.element);
    	//header
    	var hearTR = $('<tr class="no-header"></tr>').appendTo(table);
    	$('<td class="overlay-hdL"></td>').appendTo(hearTR);
    	$('<td class="overlay-hdC"></td>').appendTo(hearTR);
    	$('<td class="overlay-hdR"></td>').appendTo(hearTR);
    	//center
    	var centerTR = $('<tr></tr>').appendTo(table);
    	$('<td class="overlay-bdL"></td>').appendTo(centerTR);
    	var centerTD = $('<td class="overlay-content" style="width:450px;"></td>').appendTo(centerTR);
    	var msgTop = $('<div class="prompt_message_top"></div>').appendTo(centerTD);
    	var closeLink = $('<a href="javascript:;"></a>').appendTo(msgTop);
    	closeLink.click(function() {
    		self.closeDialog();
    	});
    	var tips = $('<h3></h3>').appendTo(msgTop);
    	tips.text(self.options.title);
    	$('<img class="toa" width="410" height="1" src="/static/images/min.jpg">').appendTo(centerTD);
    	var msgMain = $('<div class="prompt_message_main" style="background-color:#FFFFFF;"></div>').appendTo(centerTD);
    	var dl = $('<dl></dl>').appendTo(msgMain);
    	var dt = $('<dt></dt>').appendTo(dl);
    	if(self.options.type=="success"){
    		$('<img width="33" height="26" src="/static/images/add_messages.png">').appendTo(dt);
    	} else if(self.options.type=="error") {
    		$('<img width="26" height="26" src="/static/images/add_messages02.png">').appendTo(dt);
    	} else if(self.options.type=="confirm") {
    		$('<img width="31" height="28" src="/static/images/add_messages03.png">').appendTo(dt);
    	}
    	var dd = $('<dd></dd>').appendTo(dl);
    	var msg = $('<p>操作成功！</p>').appendTo(dd);
    	msg.text(self.options.message);
    	if(self.options.type=="success" || self.options.type=="error"){
    		this.okButton = $('<a class="prompt_message_btn03" href="javascript:;"></a>').appendTo(centerTD);
    		$('<img width="16" height="14" align="absmiddle" style=" padding-right:5px;" src="/static/images/ys_r_bg.png"></img>').appendTo(this.okButton);
    		var okText = $('<span></span>').appendTo(this.okButton);
    		okText.text(self.options.actions[0]);
    	} else if(self.options.type=="confirm") {
    		this.cancelButton = $('<a class="prompt_message_btn01" href="javascript:;"></a>').appendTo(centerTD);
    		$('<img width="14" height="14" align="absmiddle" style=" padding-right:5px;" src="/static/images/quxiao.png">').appendTo(this.cancelButton);
    		var cancelText = $('<span></span>').appendTo(this.cancelButton);
    		cancelText.text(self.options.actions[0]);
    		this.okButton = $('<a class="prompt_message_btn02" href="javascript:;"></a>').appendTo(centerTD);
    		$('<img width="16" height="14" align="absmiddle" style=" padding-right:5px;" src="/static/images/ys_r_bg.png">').appendTo(this.okButton);
    		var okText = $('<span></span>').appendTo(this.okButton);
    		okText.text(self.options.actions[1]); 
    	}
    	$('<td class="overlay-bdR"></td>').appendTo(centerTR);
    	//footer
    	var footerTR = $('<tr></tr>').appendTo(table);
    	$('<td class="overlay-ftL"></td>').appendTo(footerTR);
    	$('<td class="overlay-ftC"></td>').appendTo(footerTR);
    	$('<td class="overlay-ftR"></td>').appendTo(footerTR);
    	this.okButton.click(function() {
    		self.closeDialog();
    		if(self.options.callback) {
    			self.options.callback();
    		}
    	});
    	if(this.cancelButton){
        	this.cancelButton.click(function() {
        		self.closeDialog();
        	});	
    	}
    },
    _init: function() {
    	var self = this;
    	this.dialog = this.element.dialog({
    		dialogClass: self.options.dialogClass,
			autoOpen: true,
			resizable: false,
			modal: true,
			minWidth: 474,
			width: 474,
			height: "auto",
			close: function() {
				self.closeDialog();
			}
		});
    	
    },
    destroy: function() {
        $.Widget.prototype.destroy.apply(this, arguments);
        this.element.empty().remove();
    },
    closeDialog: function() {
    	var self = this;
		self.destroy();     	
    }
});


$.widget("ui.callOnline",{
    options: {
    	position:"left"  //right
    },
    _create: function() {
    	var self = this;
    	this.miniPanel = $("<div class='miniPanel' style='cursor: pointer;position:absolute;width:30px;height:110px;background: url(/static/images/spa_left.png) no-repeat scroll 0 0 transparent;display:none'></div>").appendTo(this.element);
    	$("<div style='background: url(/static/images/openIcon.gif) no-repeat scroll 0 0 transparent;height: 26px;width: 26px;margin:0px auto;'></div>").appendTo(this.miniPanel);
    	$("<div style='font-size:14px;word-wrap:break-word;word-break:nomal;width: 14px;margin:5px auto;color:#fff'>在线客服</div>").appendTo(this.miniPanel);
    	this.miniPanel.bind("click", function() {
    		self.element.width(170).height(280);
    		if(self.options.position=="left"){
        		self.mainPanel.animate({left: 0}, "fast");	
    		} else if(self.options.position=="right"){
    			self.mainPanel.animate({right: 0}, "fast");
    		}
    		self.miniPanel.hide();
    	});
    	this.mainPanel = $("<div class='mainPanel' style='width:170px;position:absolute;background: #fff;border:2px solid #0770C3;'></div>").appendTo(this.element);
    	var closeButton = $("<div style='background: url(/static/images/closeIcon.gif) no-repeat scroll 0 0 transparent;cursor: pointer;display: block;height: 25px;position: absolute;right: 5px;top: 5px;width: 25px;z-index: 2;'></div>").appendTo(this.mainPanel);
    	closeButton.bind("click", function() {
    		self.element.width(30).height(110);
    		self.miniPanel.show();
    		if(self.options.position=="left"){
        		self.mainPanel.animate({left: -175}, "fast");	
    		} else if(self.options.position=="right"){
        		self.mainPanel.animate({right: -175}, "fast");	
    		}
    	});
    	var titlePanel = $("<div class='titlePanel' style='width:100%;height:35px;line-height:35px;background: url(/static/images/spa_top.png) no-repeat scroll 0 0 transparent;'></div>").appendTo(this.mainPanel);
    	$("<span style='color:#ffffff;font-family:微软雅黑;font-size:14px;margin-left:10px;'>客服中心</span>").appendTo(titlePanel);
    	var contentPanel = $("<div class='contentPanel' style='width:170px;height:auto;margin:10px auto;'></div>").appendTo(this.mainPanel);
    	var qqLink1 = $("<div class='qqLink' style='position:relative;display:inline-block;width:100%;margin: 2px 0px;'></div>").appendTo(contentPanel);
    	$("<div style='color:#444444;font-family:微软雅黑;font-size:14px;float:left;width:46%;height:25px;line-height:25px;text-align:right;'>售前咨询: </div>").appendTo(qqLink1);
    	$('<a style="float:left;margin-left:3px;" target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=398831118&site=qq&menu=yes"><img border="0" src="/static/images/spa.jpg" alt="点击这里给我发消息" title="点击这里给我发消息"/></a>').appendTo(qqLink1);
    	var qqLink2 = $("<div class='qqLink' style='position:relative;display:inline-block;width:100%;margin: 2px 0px;'></div>").appendTo(contentPanel);
    	$("<div style='color:#444444;font-family:微软雅黑;font-size:14px;float:left;width:46%;height:25px;line-height:25px;text-align:right;'>售前咨询: </div>").appendTo(qqLink2);
    	$('<a style="float:left;margin-left:3px;" target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=398831118&site=qq&menu=yes"><img border="0" src="/static/images/spa.jpg" alt="点击这里给我发消息" title="点击这里给我发消息"/></a>').appendTo(qqLink2);
    	var qqLink3 = $("<div class='qqLink' style='position:relative;display:inline-block;width:100%;margin: 2px 0px;'></div>").appendTo(contentPanel);
    	$("<div style='color:#444444;font-family:微软雅黑;font-size:14px;float:left;width:46%;height:25px;line-height:25px;text-align:right;'>售后咨询: </div>").appendTo(qqLink3);
    	$('<a style="float:left;margin-left:3px;" target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=398831118&site=qq&menu=yes"><img border="0" src="/static/images/spa.jpg" alt="点击这里给我发消息" title="点击这里给我发消息"/></a>').appendTo(qqLink3);
    	var qqLink4 = $("<div class='qqLink' style='position:relative;display:inline-block;width:100%;margin: 2px 0px;'></div>").appendTo(contentPanel);
    	$("<div style='color:#444444;font-family:微软雅黑;font-size:14px;float:left;width:46%;height:25px;line-height:25px;text-align:right;'>技术支持: </div>").appendTo(qqLink4);
    	$('<a style="float:left;margin-left:3px;" target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=398831118&site=qq&menu=yes"><img border="0" src="/static/images/spa.jpg" alt="点击这里给我发消息" title="点击这里给我发消息"/></a>').appendTo(qqLink4);
    	var qqLink5 = $("<div class='qqLink' style='position:relative;display:inline-block;width:100%;margin: 2px 0px;'></div>").appendTo(contentPanel);
    	$("<div style='color:#444444;font-family:微软雅黑;font-size:14px;float:left;width:46%;height:25px;line-height:25px;text-align:right;'>建议/投诉: </div>").appendTo(qqLink5);
    	$('<a style="float:left;margin-left:3px;" target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=398831118&site=qq&menu=yes"><img border="0" src="/static/images/spa.jpg" alt="点击这里给我发消息" title="点击这里给我发消息"/></a>').appendTo(qqLink5);
    	var telPanel = $("<div class='telPanel' style='clear:both;width:90%;margin:8px auto;text-align:center;background:#fff;border-top: 1px dotted #CCCCCC;'></div>").appendTo(this.mainPanel);
    	$("<span style='color: #555555;display: block;font-family: 微软雅黑;font-size: 16px;line-height: 35px;'>服务热线</span>").appendTo(telPanel);
    	$("<span style='color: #555555;display: block;font-family: 微软雅黑;font-size: 20px;font-weight: bold;line-height: 25px;'>100-100-100</span>").appendTo(telPanel);
    	$(window).scroll(function(){
    		var top =$(window).scrollTop()+200;
    		self.element.stop(true,false).delay(250).animate({top:top},"slow");
    	});
    },
    _init: function() {
    	var self = this;    	
    },
    destroy: function() {
        $.Widget.prototype.destroy.apply(this, arguments);
        this.element.empty().remove();
    }
});

/**
 *jquery tabs
 *
 */

(function($){
    $.fn.slideBar = function(o){
        // default options
        var options = $.extend({
            activeClass:'active',
            panelOffClass: 'nav-ul-off',
            panelOnClass: 'nav-ul-on',
            tabLinks:'a',
            clickCallback: null
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
                if(!link.hasClass("ignore")){
                    link.bind("click", function(e){
                        switchTab(prevActiveLink, link);
                        if(link != prevActiveLink) {
                            prevActiveLink = link;
                        }
                        if (options.clickCallback) {
                            options.clickCallback(link);
                        } 
                        e.preventDefault();
                    });
                }
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
}(jQuery));