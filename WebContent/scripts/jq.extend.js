/**
 * $ object extend
 */
$.fn.extend({
		//为元素注册一个单击事件-选中元素中的内容,callback() 自定义选中之后执行
		initfocuSelect:function(callback){
			$(this).click(function(){
				$(this).select();
			});
			if($.isFunction(callback))
				callback($(this));
		},
		//检查是$ object 是否存在,callback(是否存在,原对象)
		elementExists:function(callback){
			var $e=$(this);
			var r=false;
			if(typeof $e!="undefined"&&$e!=null&&$e.length!=0)
				r= true
			if($.isFunction(callback))
				callback(r,$e,$e.length);
			
			return r;
		},
		loadTip:function(status,option){
			var target=$(this);
			if(status=='loading'){
				
				var tipText=option?(option.text?option.text:"正在处理，请稍候。。。"):'正在处理，请稍候。。。';
				
				if(option&&target.get(0).tagName.toLowerCase().Contains('body')){
					$("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");     
		            $("<div class=\"datagrid-mask-msg\"></div>").html(tipText).appendTo("body").css({display:"block",  
		                                             left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});
				}else{
					$("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(target).height()}).appendTo(target);     
		            $("<div class=\"datagrid-mask-msg\"></div>").html(tipText).appendTo(target).css({display:"block",  
		                                             left:($(target).outerWidth(true) - 190) / 2,top:($(target).height() - 40) / 2});
				}
			}else{
				target.find('.datagrid-mask,.datagrid-mask-msg').remove();
			}
		}
});

/**
 * $ extend
 */
$.extend({
		
});

//对小数进行四舍五入
//params:值,options{isfxd:是否进行小数四舍五入,isfmt:是否自动格式化金额样式，point:四舍五入小数位,bit:切割位数，formatt：string,ismlt启用倍化}
//return {isfmt & ismlt STRING,isfmt STRING,ismlt FLOAT}
function floatformat(value,options){
	var _default={isfxd:true,point:4,isfmt:false,bit:3,format:",",ismlt:true};
	var _options=$.extend(_default,options);
	var _value=value;
		if(typeof _value=="string"){
			_value=_value.toLowerCase();
			//集中判断如果存在格式字符先替换再进行
			if(_value.indexOf(_options.format)!=-1)
				_value=_value.replace(/,/g, "");
			if(_value.indexOf("%")!=-1)
				_value=_value.replace(/%/g, "");
		}
		if(_options.ismlt){
			var kmb={k:1000,m:1000000,b:1000000000}
			var charArray=Enumerable.From(String(_value)).ToArray();
			var _value=parseFloat(Enumerable.From(charArray).Where(function(x){return (x!="k"&&x!="m"&&x!="b") }).ToArray().join(""));
			Enumerable.From(charArray).Where(function(x){return (x=="k"||x=="m"||x=="b") }).ForEach(function(c){
				switch(c){
					case "k":_value*=kmb.k;break;
					case "m":_value*=kmb.m;break;
					case "b":_value*=kmb.b;break;
				}
			});
		}
		
		_value=parseFloat(_value);
		
		if(_options.isfxd){
			_value=_value.toFixed(_options.point);
			_value= _value==parseInt(_value)?parseInt(_value):parseFloat(_value);
		}
		if(_options.isfmt){
			var isAbs=_value<0;
			_value=Math.abs(_value);
			var _rs=String(_value);
			var _tpmp=_rs.substring(0,_rs.indexOf('.')>-1?_rs.indexOf('.'):_rs.length);
			var fmtLength=_tpmp.length%_options.bit==0?
						  _tpmp.length/_options.bit:
						  (_tpmp.length-(_tpmp.length%_options.bit))/_options.bit;
			var format_rs="";
			for(var ft=fmtLength+1;ft>0;ft--){
				var startIndex=_tpmp.length-(_options.bit*ft);
				startIndex=startIndex<0?0:startIndex;
				var endIndex=_tpmp.length-((ft-1)*_options.bit);
				endIndex=endIndex<0?0:endIndex;
				if(startIndex!=endIndex)
					format_rs+=_tpmp.substring(startIndex,endIndex)+(ft!=1?_options.format:"");
			}
			_value=(isAbs?"-":"")+format_rs+(_rs.indexOf('.')>-1?_rs.substr(_rs.indexOf('.')):"");
		}

	return (typeof _value=="string"?(_value.Contains("NaN")?value:_value):(isNaN(_value)?value:_value));
}