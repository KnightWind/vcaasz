$(function(){var h=$("#is_mac_os").val().trim()==="true";
var k=$("#is_clickonce").val().trim()==="true";
var b=$("#checkMsg");
var a=$("#mac_download_container");
var j=$("#window_download_container");
var o=$("#check_plugin_frame");
if(k){return
}window.log=function(p){if(window.console&&window.console.log){window.console.log(p)
}};
function g(r,t,q){var u=$("#check_plugin_frame_only");
var s;
if(u.length==0){u=$('<iframe style="width:0;height:0;" id="check_plugin_frame_only"></iframe>').appendTo(document.body)
}else{s=u[0].contentWindow;
s.navigator.plugins.refresh(false);
s.location.reload(true)
}s=u[0].contentWindow;
var p=0;
if(s.navigator.plugins&&s.navigator.plugins.length){if(s.navigator.plugins[r]){p=1
}}if(p){if(t){t()
}}else{if(q){q()
}}}function d(r,p){var q=$("#check_plugin_frame_field").val();
var s=$("#check_plugin_frame_2");
if(s.length==0){s=$('<iframe style="width:0;height:0;" src="'+q+'" id="check_plugin_frame_2"></iframe>').appendTo(document.body)
}else{s[0].contentWindow.location.reload(true)
}window.callback_from_iframe=function(t){if(t){if(r){r()
}}else{if(p){p()
}}}
}function m(){var r=null;
if(navigator.mimeTypes){for(var q=0;
q<navigator.mimeTypes.length;
++q){var t=navigator.mimeTypes[q].type;
var p=t.match(/^application\/x-java-applet;jpi-version=(.*)$/);
if(p!=null){r=p[1];
if(!SB.isOpera){break
}}}}return r
}function n(q,v){var r="^(\\d+)\\.(\\d+)(?:\\.(\\d+)(?:_(\\d+))?)?$";
var s=q.match(r);
var u=v.match(r);
if(s!=null&&u!=null){var w,t;
for(var p=1;
p<u.length;
++p){if(u[p]){if(!s[p]){return false
}w=Number(s[p]);
t=Number(u[p]);
if(w<t){return false
}else{if(w>t){return true
}}}}return true
}else{return false
}}function l(){return false
}var f=$("#plugin_name").val();
if(h){var c=false;
function e(){if(!c){b.hide();
a.show();
c=true
}window.setTimeout(function(){i()
},5000)
}function i(){g(f,function(){log("checkPlugin success, start checkPluginZoomusAppExist.");
d(function(){log("checkPluginZoomusAppExist success, jump to np.");
jump("np")
},function(){log("checkPluginZoomusAppExist fail, wait for next check.");
e()
})
},function(){log("checkPlugin fail, wait for next check.");
e()
})
}i()
}else{g(f,function(){log("checkPlugin success, jump to np.");
jump("np")
},function(){log("checkPlugin fail, start check Applet support.");
if(l()){log("checkPlugin fail, support Applet, jump to jdown.");
jump("jdown");
return
}log("checkPlugin fail, Stay in this page.");
b.hide();
j.show()
})
}});



