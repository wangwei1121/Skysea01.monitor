<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>monitor</title>
<link href="${ctx}/styles/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
<link href="${ctx}/styles/flat-ui/css/flat-ui.css" rel="stylesheet"/>
<link rel="shortcut icon" href="${ctx}/favicon.ico"/>
<script type="text/javascript"  src="${ctx}/scripts/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-confirm.js"></script>
<script type="text/javascript" src="${ctx}/scripts/highcharts-4.2.5/highcharts.js"></script>
</head>
<body style="text-align:center;">
	<div style="display:none;">
		<hr>
		<div class="row">
            <div class="col-md-12" style="text-align:left;">
                <h4>
                	<em class="fui-time text-success" title="Monitoring"></em><a>a</a>
                   	<small><a href="http://192.168.100.129:8080/a/service/rest/article/getArticle.xml?id=253" target="_blank" class="text-muted">http://192.168.100.129:8080/a/service/rest/article/getArticle.xml?id=253</a>
                  	[GET]
                    </small>
                </h4>
            </div>
            <div style="height:300px"></div>
        </div>
	</div>
	<div class="container" id="container"></div>
<script type="text/javascript">
	$(function(){
		$.ajax({
			   type: "GET",
			   url: "list",
			   dataType:'json',
			   success: function(data){
				   if(data.flag == false){
					   alert(data.message);
				   }else if(null == data || null == data.result || data.result.length == 0){
					  alert('data is empty');
			       }else{
			    	 var containers = [];
			     		for(var i=0;i<data.result.length;i++){
			     			containers.push('<hr>');
			     			containers.push('<div class="row">');
			     			containers.push('<div class="col-md-12" style="text-align:left;">');
			     			containers.push('<h4>');
			     			containers.push('<em class="fui-time text-success" title="Monitoring"></em>&nbsp;<a>'+data.result[i].name+'</a>&nbsp;');
			     			containers.push('<small><a href="'+data.result[i].url+'" target="_blank" class="text-muted">'+data.result[i].url+'</a>&nbsp;['+data.result[i].requestMethod+']</small>');
			     			containers.push('</h4>');
			     			containers.push('</div>');
			     			containers.push('<div id="chart_'+data.result[i].id+'" style="height:300px"></div>');
			     			containers.push('</div>');
			     		}
			     		$('#container').append(containers.join(''));
			     		for(var i=0;i<data.result.length;i++){
			     			drawChart(data.result[i]);
			     		}
			     }
			   }
			});
	});
	
	function drawChart(obj){
		$.getJSON("getHeartbeatData/" + obj.id,function(data){
			var categoriesData = [];
			var seriesData = [];
			for(var i=0;i<data.result.length;i++){
				categoriesData.push(data.result[i].createTime.split(' ')[1]);
				seriesData.push(data.result[i].connTime);
			}
			 $('#chart_' + obj.id).highcharts({
			        title: {
			        	 text: 'Frequency:' + obj.frequency + 's Max conn:' + obj.max_conn + 'ms Request method:' + obj.requestMethod,
			            x: -320 
			        },
			        credits: {  
			        	  enabled: false  
			        	},
			        xAxis: {
			            categories: categoriesData
			        },
			        yAxis: {
			            title: {
			                text: 'Connection time (ms)'
			            },
			            plotLines: [{
			                value: 0,
			                width: 1,
			                color: '#808080'
			            }]
			        },
			        tooltip: {
			            valueSuffix: 'ms'
			        },
			        legend: {
			            layout: 'vertical',
			            align: 'right',
			            verticalAlign: 'middle',
			            borderWidth: 0
			        },
			        series: [{
			            name: 'conn time',
			            data: seriesData
			        }]
			    });
		});
	}
</script>
</body>
</html>
