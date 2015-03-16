
/*
 * input输入框 显示提示信息
 */
function remindInfo(obj,info){
		var $obj = $(obj);
		if(!($obj.val())){
			$obj.val(info);
		}
		$obj.bind("click",function(){     
           	if($obj.val()== info){
           		$obj.val("");		
           	}
        });
		$obj.bind("blur",function(){
			if(!($obj.val())){
           		$obj.val(info);		
           	}
		});
		
		$obj.bind("submitForm",function(){
			if($obj.val()== info){
           		$obj.val("");
           	}
			if($("#pageNo").length>0){
				$("#pageNo").val("");
			}
		});
		
		$obj.bind("keyup",function(event){
			if(event.keyCode=='13'){
				$(this).trigger('submitForm');
				$(this).closest("form").submit();
			}
		});
}