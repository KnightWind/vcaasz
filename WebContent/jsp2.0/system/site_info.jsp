<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${LANG['bizconf.jsp.system.site_info.res1']}</title>
<style type="text/css" title="currentStyle">
@import "/static/js/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css";
@import "/static/js/DataTables-1.9.4/media/css/jquery.dataTables_themeroller.css";
</style>
<script type="text/javascript" language="javascript" src="/static/js/jquery-ui-1.9.2.custom/js/jquery-1.8.3.js"></script>
<script type="text/javascript" language="javascript" src="/static/js/jquery-ui-1.9.2.custom/js/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" language="javascript" src="/static/js/DataTables-1.9.4/media/js/jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript" src="/static/js/jquery.stepy-1.1.0/js/jquery.stepy.js"></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#site-info').dataTable({
			"bJQueryUI": true,
			"bPaginate" : true,
			"iDisplayLength": 5,
			"bProcessing": true,
	        "bServerSide": true,
	        "sAjaxDataProp": "result",
			"sPaginationType": "full_numbers",
			"sAjaxSource": '/system/site/siteList',
			"aoColumns": [
                { "mData": "siteName" },
                { "mData": "siteSign" },
                { "mData": "siteFlag" },
                { "mData": "license" },
                { "mData": "expireDate" },
                { "mData": "clientName" },
                { "mData": "lockFlag", "bSortable": false},
                { "mData": "id", "bSortable": false}
            ],
            "aoColumnDefs": [
				{
				    "mRender": function ( data, type, row ) {
				        return  row.expireDate.date;
				    },
				    "aTargets": [ 4 ]
				},
                {
                    "mRender": function ( data, type, row ) {
                        return  '<a href=\"/' + row.lockFlag + '\">${LANG['bizconf.jsp.system.site_info.res2']}</a>';
                    },
                    "aTargets": [ 6 ]
                },
                {
                    "mRender": function ( data, type, row ) {
                        return  '<a href=\"Details/' + row.id + '\">${LANG['bizconf.jsp.system.email_template_list.res7']}</a>';
                    },
                    "aTargets": [ 7 ]
                }
            ],
            "oLanguage": {  
                "sProcessing": "${LANG['bizconf.jsp.system.site_info.res3']}...",
                "sZeroRecords": "${LANG['bizconf.jsp.system.site_info.res4']}...",  
                "sInfo": "${LANG['bizconf.jsp.system.site_info.res5']} _TOTAL_ ${LANG['bizconf.jsp.system.site_info.res6']}",  
                "sInfoEmpty": "${LANG['bizconf.jsp.system.site_info.res7']} 0 ${LANG['bizconf.jsp.admin.mybilling_list.res4']} 0 ${LANG['bizconf.jsp.system.site_info.res8']} 0 ${LANG['bizconf.jsp.system.site_info.res9']}",  
                "sInfoFiltered": "(_MAX_)",
                "oPaginate": {
                    "sFirst": "${LANG['bizconf.jsp.system.site_info.res10']}",
                    "sLast": "${LANG['bizconf.jsp.system.site_info.res11']}",
                    "sPrevious": "${LANG['bizconf.jsp.system.site_info.res12']}",
                    "sNext": "${LANG['bizconf.jsp.system.site_info.res13']}"
                }
            },
		});

		$("#create-site").click(function() {
			window.parent.editSiteDialog();
		});

	} );
</script>
</head>
<body>
<div>
	<div style="float: left;"><input type="text"/>${LANG['bizconf.jsp.system.site_info.res14']}</div>
	<div style="float: right;">
		<div><input id="create-site" type="button" value="${LANG['bizconf.jsp.system.site_info.res15']}"/></div>
	</div>
</div>
<table cellpadding="0" cellspacing="0" border="0" class="display" id="site-info">
	<thead>
		<tr>
			<th width="12%">${LANG['bizconf.jsp.system.site_info.res16']}</th>
			<th width="12%">${LANG['bizconf.jsp.system.site_info.res17']}</th>
			<th width="12%">${LANG['bizconf.jsp.system.site_info.res18']}</th>
			<th width="12%">${LANG['bizconf.jsp.system.site_info.res19']}</th>
			<th width="12%">${LANG['bizconf.jsp.system.site_info.res20']}</th>
			<th width="12%">${LANG['bizconf.jsp.admin.arrange_org_user.res8']}</th>
			<th width="10%">${LANG['bizconf.jsp.system.site_info.res21']}</th>
			<th width="10%">${LANG['bizconf.jsp.admin.site_org_list.res6']}</th>
		</tr>
	</thead>
	<tbody>
		
	</tbody>

</table>
</body>
</html>
