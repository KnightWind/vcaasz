 
 
if (typeof(LOADING_CONSTANT) == "undefined") {
	LOADING_CONSTANT = {
		loadingMessage:"正在加载，请稍后..."
	};
}
$.widget("ui.showDialog",{
    options: {
    	model: true,
        dialogClass:"",
        iconClass: "",
        title: "",
        url: "",
        width: "auto",
        height: "auto",
        loadImgUrl: "/assets/images/animate/loading.gif",
        loadMessage: LOADING_CONSTANT.loadingMessage,
        type: "",
        minWidth: 450,
        minHeight: 240,
        borderWidth: 6,
        borderHeight: 43
    },
    _create: function() {
    	//this.window = window.top;
        var self = this;
        var bizTable = '<table class="biz-border">'
				   +       '<tbody>'
				   +           '<tr>'
				   +               '<td class="biz-nw"></td>'
				   +               '<td class="biz-n">'
				   +               '<div class="biz-titleBar">'
				   +               '<span class="biz-title">'
				   +			   '<i class="biz-icon"></i>'
				   +			   '</span>'
				   +               '<a class="biz-close" href="javascript:;"></a>'
				   +               '</div>'
				   +               '</td>'
				   +               '<td class="biz-ne"></td>'
				   +           '</tr>'
				   +           '<tr>'
				   +               '<td class="biz-w"></td>'
				   +               '<td class="biz-c">'
				   +                   '<div class="biz-inner">'
				   +                   '</div>'
				   +               '</td>'
				   +               '<td class="biz-e"></td>'
				   +           '</tr>'
				   +           '<tr>'
				   +               '<td class="biz-sw"></td>'
				   +               '<td class="biz-s"></td>'
				   +               '<td class="biz-se"></td>'
				   +           '</tr>'
				   +       '</tbody>'
				   +   '</table>';
        this.element.append(bizTable);
        this.topTitle = this.element.find(".biz-title");
        this.topTitle.find(".biz-icon").addClass(self.options.iconClass);
        this.topTitle.append(self.options.title);
        this.bizInner = this.element.find(".biz-inner");
        this.closeBtn = this.element.find(".biz-close");
        this.closeBtn.bind("click", function(e) {
            self.destroy();
            if ( e && e.preventDefault ) {
            	//阻止默认浏览器动作(W3C) 
            	e.preventDefault();
            } else {
            	//IE中阻止函数器默认动作的方式 
            	window.event.returnValue = false; 
            	return false;
            }
        });
    	var frameHtml="<iframe allowtransparency='true' id='dialogFrame' name='dialogFrame' src="+self.options.url+" frameborder='0'  scrolling='no' style='background:transparent;position:absolute;left:-9999em;top:-9999em;' />";
    	this.bizInner.append(frameHtml);
        self.showLoading();
        this.element.bind("closeDialog", function(event, result) {
            if (result) {
            	if (result.status=="1") {
            		self.destroy();
            		$("body").trigger(EVENT_UPDATE, [result, self.options.type]);		
	    		} else {			   
	    			$("body").trigger(EVENT_ERROR, [result, self.options.type]);
	    		}
            } else {
                self.destroy();
            }
        });
    },
    _init: function() {
        var self = this;
    	this.dialog = this.element.dialog({
            dialogClass: self.options.dialogClass,
			autoOpen: true,
			resizable: true,
			draggable: false,
			modal: self.options.model,
            minWidth: self.options.minWidth,
            minHeight: self.options.minHeight,
			close: function() {
				self.destroy();
			}
		});
        this.element.find("iframe").load(function() {
            self.hideLoading();
            $(this).show();
            
            //内容的宽度和高度
            var contentWidth = $(this).contents().find("body")[0].scrollWidth;
            var contentHeight = $(this).contents().find("body").height();
          // if(self.options.height!="auto"){
          //  	contentHeight = self.options.height;
          // }
            if(self.options.width!="auto"){
            	contentWidth = self.options.width;
            }
            var dialogWidth = contentWidth+self.options.borderWidth;
            var dialogHeight = contentHeight+self.options.borderHeight;
            
            //设置iframe,对话框的宽度和高度
            $(this).width(contentWidth).height(contentHeight).css({"position":"","left":"","top":""});
            //$(this).width(contentWidth).height(contentHeight).css({"position":"","left":"200px;","top":"100px;"});
            self.element.width(dialogWidth).height(dialogHeight);
            self.element.parent().width(dialogWidth).height(dialogHeight);
            if(self.dialog) {
                self.dialog.dialog({ position: 'center' });
            }
            $(this).contents().find(".anchor-cancel").bind("click", function() {
            	self.destroy();
            });
        });
        
//        this.element.parents(".ui-dialog").draggable({
//        	handle: self.topTitle,
//        	containment: 'document'
//        });
//        this.element.find(".close-btn").bind("click", function() {
//        	self.destroy();
//        });
    },
    destroy: function() {
        $.Widget.prototype.destroy.apply(this, arguments);
        this.element.html("").remove();
    },
    showLoading: function() {
        var self = this;
        //对话框Border的宽度
        this.loadContainer = $("<div></div>").appendTo(this.bizInner);
        var loaderHeight = self.options.minHeight - this.options.borderHeight;
        var loaderWidth = self.options.minWidth - this.options.borderWidth;
        this.loadContainer.width(loaderWidth).height(loaderHeight);
        $("<i class='icon-loading'></i>").appendTo(this.loadContainer);
        var loadingText = $("<i class='text-loading'>正在加载，请稍后...</i>").appendTo(this.loadContainer);
        if (self.options.loadMessage) {
        	loadingText.html(self.options.loadMessage);
        }
    },
    hideLoading: function() {
        this.loadContainer.hide();
    }
});

/**
 * custom widget for alert dialog
 */
$.widget("ui.alertDialog",{
    options: {
        title: "提醒",
        dialogClass: "dialog-alert",
        iconClass: "icon-prompt",
        type: "success", //success, error, warning, confirm, prompt
        message: "提醒",
        buttonOk: null,
        buttonCancel: null,
        successCallback: jQuery.noop()
    },
    _create: function() {
        var self = this;

        var header = $("<div class='dialog-header'></div>").appendTo(this.element);
        var h3 = $("<h3 class='title'>"+self.options.title+"</h3>").appendTo(header);
        $("<i class='icon "+self.options.iconClass+"'></i>").prependTo(h3);
        var closeButton = $("<a title='关闭' class='close'></a>").appendTo(header);
        closeButton.bind("click", function() {
        	self.closeDialog();
        });
        
        var messageContainer = $("<div class='dialog-message'></div>").appendTo(this.element);
        var messageContent = $("<div class='dialog-message-content'></div>").appendTo(messageContainer);
        var promptImg = $("<img src='/assets/images/bg/prompt.png'>").appendTo(messageContent);
        if(self.options.type=="success") {
        	promptImg.attr("src", "/assets/images/bg/success-small.png");
        } else if(self.options.type=="error") {
        	promptImg.attr("src", "/assets/images/bg/failure-small.png");
        } else if(self.options.type=="prompt") {
        	promptImg.attr("src", "/assets/images/bg/prompt.png");
        } 
        var containerClass = self.options.type;
        messageContainer.addClass(containerClass);
        messageContent.append(self.options.message);
        var footer = $("<div class='dialog-footer'></div>").appendTo(this.element);
        if (self.options.buttonOk) {
            var successBtn = $("<input type='button' class='button input-gray' value="+self.options.buttonOk+">").appendTo(footer);
            successBtn.bind("click", function() {
            	self.closeDialog();
            	if(self.options.successCallback) {
            		self.options.successCallback();
            	}
            });
        }
        if (self.options.buttonCancel) {
            var cancelBtn = $("<a class='button anchor-cancel'>"+self.options.buttonCancel+"</a>").appendTo(footer);
            cancelBtn.bind("click", function() {
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
            width: 450,
            height: "auto",
            minHeight: 50,
            close: function() {
                self.closeDialog();
            }
        });
        
        if($.browser.msie && $.browser.version<10){
        	var pop = self.element.parent()
        	pop.css("top",220);
        	pop.css("left",450);
        }
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

/**
 * custom widget for iframe Loading
 */
$.widget("ui.showLoading",{
    options: {
        loadImage: "loading.gif",
        loadText: "数据正在加载中...",
        iframeSrc: "listSite",
        iframeId: "mainFrame",
        callback: null
    },
    _create: function() {
    	var self = this;
    	var containerWidth = this.element.width();
    	var containerHeight = this.element.height();
    	this.showLoading(containerWidth, containerHeight);
        this.iframe = document.createElement("iframe");
        this.iframe.src = this.options.iframeSrc;
        this.iframe.width = "100%";
        this.iframe.id = this.options.iframeId;
        this.iframe.frameBorder = 0;
        this.iframe.scrolling = "no";
        this.iframe.style.display = "none";
    	if (this.iframe.attachEvent){
    		this.iframe.attachEvent("onload", function(){
    	    	self.iframeLoaded();
    	    });
    	} else {
    		this.iframe.onload = function(){
    	    	self.iframeLoaded();
    	    };
    	}
    	this.element.append(this.iframe);
    },
    _init: function() {

    },
    destroy: function() {
        $.Widget.prototype.destroy.apply(this, arguments);
        this.element.empty().remove();
    },
    closeDialog: function() {
    	this.destroy();
    },
    refresh: function(src) {
    	this.iframe.style.dispaly = "none";
    	this.iframe.src = src;
    	this.showLoading();
    },
    iframeLoaded: function() {
    	this.hideLoading();
    	$("body").trigger("pageLoaded");
    	this.iframe.style.display = "block";
    },
    showLoading: function(parentW, parentH) {
    	this.loadingContainer = this.element.find("#loadingContainer");
    	if (!this.loadingContainer || this.loadingContainer.length==0){
        	this.loadingContainer = $("<div></div>");
        	var loadingImg = $("<i class='icon-loading'>&nbsp;</i>").prependTo(this.loadingContainer);
        	var bgUrl = "url('/resources/images/'"+this.options.loadImage+") no-repeat scroll center center transparent";
        	loadingImg.css("background", bgUrl);
        	this.loadingContainer.text(this.options.loadText);
        	this.loadingContainer.appendTo(this.element);
    	}
    	this.loadingContainer.show();
    },
    hideLoading: function() {
    	this.loadingContainer.hide();
    }
});

/*commons js*/
function log() {
    if( window.console )
        console.debug.apply( console, arguments );
    else
        alert( [].join.apply( arguments, [' '] ) );
}

String.prototype.format = function(){
 var args = arguments;  /* 将参数保存到args中，以便于在stringobject.replace函数中被使用*/
 return this.replace(/\{(\d+)\}/g,
   function(m,s,i,t){
    return args[s]; /*s是模式中子表达式匹配的字符串，正是{0},{1}中的0,1*/
   });
}