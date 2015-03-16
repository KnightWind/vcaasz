/*!
 * jQuery Cookie Plugin v1.3.0
 * https://github.com/carhartl/jquery-cookie
 *
 * Copyright 2013 Klaus Hartl
 * Released under the MIT license
 */
(function(g,b,h){var a=/\+/g;
function e(i){return i;}function c(i){return f(decodeURIComponent(i.replace(a," ")));}function f(i){if(i.indexOf('"')===0){i=i.slice(1,-1).replace(/\\"/g,'"').replace(/\\\\/g,"\\");
}return i;}var d=g.cookie=function(r,q,w){if(q!==h){w=g.extend({},d.defaults,w);if(q===null){w.expires=-1;}if(typeof w.expires==="number"){var s=w.expires,v=w.expires=new Date();
v.setDate(v.getDate()+s);}q=d.json?JSON.stringify(q):String(q);return(b.cookie=[encodeURIComponent(r),"=",d.raw?q:encodeURIComponent(q),w.expires?"; expires="+w.expires.toUTCString():"",w.path?"; path="+w.path:"",w.domain?"; domain="+w.domain:"",w.secure?"; secure":""].join(""));
}var j=d.raw?e:c;var u=b.cookie.split("; ");var x=r?null:{};for(var p=0,n=u.length;p<n;p++){var o=u[p].split("=");var k=j(o.shift());var m=j(o.join("="));
if(d.json){m=JSON.parse(m);}if(r&&r===k){x=m;break;}if(!r){x[k]=m;}}return x;};d.defaults={};g.removeCookie=function(j,i){if(g.cookie(j)!==null){g.cookie(j,null,i);
return true;}return false;};})(jQuery,document);
/*
 * jquery timers 1.2.0
 * http://jquery.offput.ca/every/
 */
jQuery.fn.extend({everyTime:function(b,c,d,e,a){return this.each(function(){jQuery.timer.add(this,b,c,d,e,a);});},oneTime:function(a,b,c){return this.each(function(){jQuery.timer.add(this,a,b,c,1);
});},stopTime:function(a,b){return this.each(function(){jQuery.timer.remove(this,a,b);});}});jQuery.extend({timer:{guid:1,global:{},regex:/^([0-9]+)\s*(.*s)?$/,powers:{ms:1,cs:10,ds:100,s:1000,das:10000,hs:100000,ks:1000000},timeParse:function(c){if(c==undefined||c==null){return null;
}var a=this.regex.exec(jQuery.trim(c.toString()));if(a[2]){var b=parseInt(a[1],10);var d=this.powers[a[2]]||1;return b*d;}else{return c;}},add:function(e,c,d,g,h,b){var a=0;
if(jQuery.isFunction(d)){if(!h){h=g;}g=d;d=c;}c=jQuery.timer.timeParse(c);if(typeof c!="number"||isNaN(c)||c<=0){return;}if(h&&h.constructor!=Number){b=!!h;
h=0;}h=h||0;b=b||false;if(!e.$timers){e.$timers={};}if(!e.$timers[d]){e.$timers[d]={};}g.$timerID=g.$timerID||this.guid++;var f=function(){if(b&&this.inProgress){return;
}this.inProgress=true;if((++a>h&&h!==0)||g.call(e,a)===false){jQuery.timer.remove(e,d,g);}this.inProgress=false;};f.$timerID=g.$timerID;if(!e.$timers[d][g.$timerID]){e.$timers[d][g.$timerID]=window.setInterval(f,c);
}if(!this.global[d]){this.global[d]=[];}this.global[d].push(e);},remove:function(c,b,d){var e=c.$timers,a;if(e){if(!b){for(b in e){this.remove(c,b,d);}}else{if(e[b]){if(d){if(d.$timerID){window.clearInterval(e[b][d.$timerID]);
delete e[b][d.$timerID];}}else{for(var d in e[b]){window.clearInterval(e[b][d]);delete e[b][d];}}for(a in e[b]){break;}if(!a){a=null;delete e[b];}}}for(a in e){break;
}if(!a){c.$timers=null;}}}}});if(jQuery.browser.msie){jQuery(window).one("unload",function(){var d=jQuery.timer.global;for(var a in d){var c=d[a],b=c.length;
while(--b){jQuery.timer.remove(c[b],a);}}});}
/*
Watermark v2.0 (June 2, 2009) plugin for jQuery
Copyright (c) 2009 Todd Northrop
http://www.speednet.biz/
Licensed under GPL 3, see  <http://www.gnu.org/licenses/>
*/
(function(e){var c,j="watermark",g="watermarkClass",b="watermarkFocus",h="watermarkSubmit",d="watermarkMaxLength",f="watermarkPassword",k="watermarkText",a=":data("+j+")",i=":text,:password,textarea";e.extend(e.expr[":"],{data:function(m,l,o,q){var n,p=/^((?:[^=!^$*]|[!^$*](?!=))+)(?:([!^$*]?=)(.*))?$/.exec(o[3]);if(p){n=e(m).data(p[1]);if(n!==c){if(p[2]){n=""+n;switch(p[2]){case"=":return(n==p[3]);case"!=":return(n!=p[3]);case"^=":return(n.slice(0,p[3].length)==p[3]);case"$=":return(n.slice(-p[3].length)==p[3]);case"*=":return(n.indexOf(p[3])!==-1)}}return true}}return false}});e.watermark={className:"watermark",hide:function(l){e(l).filter(a).each(function(){e.watermark._hide(e(this))})},_hide:function(o,m){if(o.val()==o.data(k)){o.val("");if(o.data(f)){if(o.attr("type")==="text"){var n=o.data(f),l=o.parent();l[0].removeChild(o[0]);l[0].appendChild(n[0]);o=n}}if(o.data(d)){o.attr("maxLength",o.data(d));o.removeData(d)}if(m){o.attr("autocomplete","off");window.setTimeout(function(){o.select()},0)}}o.removeClass(o.data(g))},show:function(l){e(l).filter(a).each(function(){e.watermark._show(e(this))})},_show:function(q){var p=q.val(),o=q.data(k);if(((p.length==0)||(p==o))&&(!q.data(b))){if(q.data(f)){if(q.attr("type")==="password"){var n=q.data(f),m=q.parent();m[0].removeChild(q[0]);m[0].appendChild(n[0]);q=n}}if(q.attr("type")==="text"){var l=q.attr("maxLength");if((l>0)&&(o.length>l)){q.data(d,l);q.attr("maxLength",o.length)}}q.val(o);q.addClass(q.data(g))}else{e.watermark._hide(q)}},hideAll:function(){e.watermark.hide(i)},showAll:function(){e.watermark.show(i)}};e.fn.watermark=function(o,n){var m=(typeof(o)==="string"),l=(typeof(n)==="string");return this.filter(i).each(function(){var s=e(this);if(s.data(j)){if(m||l){e.watermark._hide(s);if(m){s.data(k,o)}if(l){s.data(g,n)}}}else{s.data(k,m?o:"");s.data(g,l?n:e.watermark.className);s.data(j,1);if(s.attr("type")==="password"){var p=s.wrap("<span>").parent();var r=e(p.html().replace(/type=["']?password["']?/i,'type="text"'));r.data(k,s.data(k));r.data(g,s.data(g));r.data(j,1);r.focus(function(){e.watermark._hide(r,true)});s.blur(function(){e.watermark._show(s)});r.data(f,s);s.data(f,r)}else{s.focus(function(){s.data(b,1);e.watermark._hide(s,true)}).blur(function(){s.data(b,0);e.watermark._show(s)})}var q=e(this.form);if(!q.data(h)){q.data(h,this.form.submit);q.submit(e.watermark.hideAll);this.form.submit=function(){e.watermark.hideAll();q.data(h).apply(q[0],arguments)}}}e.watermark._show(s)}).end()}})(jQuery);

//tipsy, facebook style tooltips for jquery
//version 1.0.0a
//(c) 2008-2010 jason frame [jason@onehackoranother.com]
//released under the MIT license
(function(c){function b(f,e){return(typeof f=="function")?(f.call(e)):f;}function d(e){while(e=e.parentNode){if(e==document){return true;}}return false;
}function a(f,e){this.$element=c(f);this.options=e;this.enabled=true;this.fixTitle();}a.prototype={show:function(){var h=this.getTitle();if(h&&this.enabled){var g=this.tip();
g.find(".tipsy-inner")[this.options.html?"html":"text"](h);g[0].className="tipsy";g.remove().css({top:0,left:0,visibility:"hidden",display:"block"}).prependTo(document.body);
var k=c.extend({},this.$element.offset(),{width:this.$element[0].offsetWidth,height:this.$element[0].offsetHeight});var e=g[0].offsetWidth,j=g[0].offsetHeight,i=b(this.options.gravity,this.$element[0]);
var f;switch(i.charAt(0)){case"n":f={top:k.top+k.height+this.options.offset,left:k.left+k.width/2-e/2};break;case"s":f={top:k.top-j-this.options.offset,left:k.left+k.width/2-e/2};
break;case"e":f={top:k.top+k.height/2-j/2,left:k.left-e-this.options.offset};break;case"w":f={top:k.top+k.height/2-j/2,left:k.left+k.width+this.options.offset};
break;}if(i.length==2){if(i.charAt(1)=="w"){f.left=k.left+k.width/2-15;}else{f.left=k.left+k.width/2-e+15;}}g.css(f).addClass("tipsy-"+i);g.find(".tipsy-arrow")[0].className="tipsy-arrow tipsy-arrow-"+i.charAt(0);
if(this.options.className){g.addClass(b(this.options.className,this.$element[0]));}if(this.options.fade){g.stop().css({opacity:0,display:"block",visibility:"visible"}).animate({opacity:this.options.opacity});
}else{g.css({visibility:"visible",opacity:this.options.opacity});}}},hide:function(){if(this.options.fade){this.tip().stop().fadeOut(function(){c(this).remove();
});}else{this.tip().remove();}},fixTitle:function(){var e=this.$element;if(e.attr("title")||typeof(e.attr("original-title"))!="string"){e.attr("original-title",e.attr("title")||"").removeAttr("title");
}},getTitle:function(){var g,e=this.$element,f=this.options;this.fixTitle();var g,f=this.options;if(typeof f.title=="string"){g=e.attr(f.title=="title"?"original-title":f.title);
}else{if(typeof f.title=="function"){g=f.title.call(e[0]);}}g=(""+g).replace(/(^\s*|\s*$)/,"");return g||f.fallback;},tip:function(){if(!this.$tip){this.$tip=c('<div class="tipsy"></div>').html('<div class="tipsy-arrow"></div><div class="tipsy-inner"></div>');
this.$tip.data("tipsy-pointee",this.$element[0]);}return this.$tip;},validate:function(){if(!this.$element[0].parentNode){this.hide();this.$element=null;
this.options=null;}},enable:function(){this.enabled=true;},disable:function(){this.enabled=false;},toggleEnabled:function(){this.enabled=!this.enabled;
}};c.fn.tipsy=function(i){if(i===true){return this.data("tipsy");}else{if(typeof i=="string"){var k=this.data("tipsy");if(k){k[i]();}return this;}}i=c.extend({},c.fn.tipsy.defaults,i);
function h(m){var n=c.data(m,"tipsy");if(!n){n=new a(m,c.fn.tipsy.elementOptions(m,i));c.data(m,"tipsy",n);}return n;}function l(){var m=h(this);m.hoverState="in";
if(i.delayIn==0){m.show();}else{m.fixTitle();setTimeout(function(){if(m.hoverState=="in"){m.show();}},i.delayIn);}}function g(){var m=h(this);m.hoverState="out";
if(i.delayOut==0){m.hide();}else{setTimeout(function(){if(m.hoverState=="out"){m.hide();}},i.delayOut);}}if(!i.live){this.each(function(){h(this);});}if(i.trigger!="manual"){var e=i.live?"live":"bind",j=i.trigger=="hover"?"mouseenter":"focus",f=i.trigger=="hover"?"mouseleave":"blur";
this[e](j,l)[e](f,g);}return this;};c.fn.tipsy.defaults={className:null,delayIn:0,delayOut:0,fade:false,fallback:"",gravity:"n",html:false,live:false,offset:0,opacity:0.8,title:"title",trigger:"hover"};
c.fn.tipsy.revalidate=function(){c(".tipsy").each(function(){var e=c.data(this,"tipsy-pointee");if(!e||!d(e)){c(this).remove();}});};c.fn.tipsy.elementOptions=function(f,e){return c.metadata?c.extend({},e,c(f).metadata()):e;
};c.fn.tipsy.autoNS=function(){return c(this).offset().top>(c(document).scrollTop()+c(window).height()/2)?"s":"n";};c.fn.tipsy.autoWE=function(){return c(this).offset().left>(c(document).scrollLeft()+c(window).width()/2)?"e":"w";
};c.fn.tipsy.autoBounds=function(f,e){return function(){var g={ns:e[0],ew:(e.length>1?e[1]:false)},j=c(document).scrollTop()+f,h=c(document).scrollLeft()+f,i=c(this);
if(i.offset().top<j){g.ns="n";}if(i.offset().left<h){g.ew="w";}if(c(window).width()+c(document).scrollLeft()-i.offset().left<f){g.ew="e";}if(c(window).height()+c(document).scrollTop()-i.offset().top<f){g.ns="s";
}return g.ns+(g.ew?g.ew:"");};};})(jQuery);