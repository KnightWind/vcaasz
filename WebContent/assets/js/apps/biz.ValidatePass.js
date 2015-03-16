
/**
 * 该方法只校验密码的规则正确性
 * 校验规则是：允许以数字、字母、特殊字符等(其中任意两种)混合组合做为密码
 * */
function ValidatorPass(str){		//校验密码
    var rC = {
        lW:'[a-z]',
        uW:'[A-Z]',
        nW:'[0-9]',
        sW:'[\\u0020-\\u002F\\u003A-\\u0040\\u005B-\\u0060\\u007B-\\u007E]'
    };
    function Reg(str, rStr){
        var reg = new RegExp(rStr);
        if(reg.test(str)) return true;
        else return false;
    }
    
    var tR = {
        l:Reg(str, rC.lW),//小写字母
        u:Reg(str, rC.uW),//大写字母
        n:Reg(str, rC.nW),//数字
        s:Reg(str, rC.sW) //符号
    };
    
    if((tR.l && tR.n) || (tR.l && tR.s) || (tR.u && tR.n) || (tR.u && tR.s) 
    		|| (tR.s && tR.n) || (tR.l && tR.u && tR.n) || (tR.l && tR.u && tR.s) || (tR.l && tR.n && tR.s) 
    		|| (tR.u && tR.n && tR.s) || (tR.l && tR.u && tR.n && tR.s) ){
    	return true;
    }else{
    	return false;
    }
}

/**
 * 验证主持人密码，只限数字和字母
 * */
function ValidatorHostKey(str){		//校验密码
    var rstr = '^[A-Za-z0-9]+$';
    var reg = new RegExp(rstr);
    if(reg.test(str)){
    	return true;
    }else{
    	return false;
    }
}