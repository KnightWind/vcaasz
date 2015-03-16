seajs.config({
	charset: 'utf-8',
	debug: false,
	plugins: ['nocache'],
	alias: {
		'jquery': '/assets/js/lib/jquery.js',
		'base': '/assets/js/lib/base.js',
		'json': '/assets/js/lib/json.js',
		'storage': '/assets/js/lib/storage.js',
		'mustache': '/assets/js/lib/mustache.js',
		'pagination': '/assets/js/lib/pagination.js',
		'plupload': '/assets/js/lib/plupload/plupload.full.js',
		'scrollbar': '/assets/js/lib/scrollbar.js',
		'upload.css': '/assets/css/module/upload.css',
		'upload': '/assets/js/module/upload.js',
		'keepfixed': '/assets/js/module/keepfixed.js',
		'dropdown': '/assets/js/module/dropdown.js',
		'jquery.ui': '/assets/js/lib/jquery/jquery-ui-1.10.1.custom.min.js',
		'jquery.timepicker': '/assets/js/lib/jquery/jquery-ui-timepicker-addon.js',
		'jquery.ui.css': '/assets/css/module/jquery-ui-1.10.1.custom.min.css',
		'jquery.placeholder': '/assets/js/lib/jquery/jquery.placeholder.min.js',
		'notify': '/assets/js/module/notify.js',
		'notify.css': '/assets/css/module/notify.css',
		'raphael': '/assets/js/lib/raphael.js',
		'slider': '/assets/js/lib/slider.js',
		'flower': '/assets/js/lib/flowplayer.js',
		'flower.css': '/assets/css/module/flower.css',
		'projekktor': '/assets/js/module/projekktor.js',
		'projekktor.css': '/assets/css/module/projekktor.css',
		'recorder.scroll': '/assets/js/module/recorder.scroll.js',
		'addresscascade': '/assets/js/module/address.cascade.js',
		'commoncascade': '/assets/js/module/common.cascade.js',
		'formvalidate': '/assets/js/module/jquery.validate.js',
		'autocomplete': '/assets/js/module/jquery.autocomplete.js',
		'highcharts': '/assets/js/lib/highcharts.js',
		'highchartscommon': '/assets/js/module/highcharts.common.js',
		'analyze.video': '/assets/js/module/analyze.video.js',
		'thumbs.balance': '/assets/js/module/thumbs.balance.js',
		'highlighter': '/assets/js/module/jquery.highlighter.js',
		'kindeditor': '/assets/js/module/kindeditor.js',
		'comment': '/assets/js/module/comments.js',
		'cycle': '/assets/js/module/jquery.cycle.js'
	},
	preload: [this.JSON ? '' : 'json', 'jquery', 'base']
});