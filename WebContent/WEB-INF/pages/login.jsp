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
<script type="text/javascript" src="${ctx}/scripts/jquery.cookie.js"></script>
</head>
<body style="text-align:center;">
<div class="modal fade" id="error_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-show="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-body" id="error_tip">
           ${loginError}
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
         </div>
      </div>
   </div>
</div>
<div class="container" style="height:300px;margin-top:150px;">
	<div class="row" style="margin:0 auto;width:400px;">
		  <h5 style="text-align:center;">Sign in to Monitor</h5>
		  <form action="signIn" method="post" data-toggle="validator" id="form">
          <div class="login-form">
            <div class="form-group">
              <input type="text" class="form-control login-field" value="" placeholder="Enter your name" id="login-name" name="loginName" required maxlength="50" autocomplete="on">
              <label class="login-field-icon fui-user" for="login-name"></label>
            </div>
            <div class="form-group">
              <input type="password" class="form-control login-field" value="" placeholder="Password" id="login-pass" name="password" required maxlength="50" autocomplete="on">
              <label class="login-field-icon fui-lock" for="login-pass"></label>
            </div>
            <div class="form-group" style="text-align:left;">
              <input type="checkbox" id="login-is-remember" name="isRemember">
              <label for="login-is-remember" style="cursor:pointer;">Is Rember</label>
            </div>
 			<button class="btn btn-primary btn-large btn-block" type="submit">Sign in</button>
          </div>
          </form>
   </div>
</div>
<script type="text/javascript">
	$(function(){
		if('' != '${loginError}'){
			$('#error_modal').modal('show');
		}
	  $('#form').submit(function() {
			$.post("signIn", $("#form").serialize(),function(data) {
			    console.log(data);
				if(data && data.errorMsg.length > 0){
					$('#error_tip').html(data.errorMsg);
					$('#error_modal').modal('show');
				}else{
					location.href="home"
				}
			 },"json");
			return false;
		});
	});
</script>
</body>
</html>
