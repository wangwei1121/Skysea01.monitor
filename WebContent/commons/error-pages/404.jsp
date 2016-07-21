<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="false" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="refresh" content="10;URL=${ctx}/" />
<title>无法找到网页</title>
<link href="${ctx}/commons/error-pages/images/css.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div align="center">
  <table width="50%" border="0" cellspacing="0" cellpadding="0" style="margin-top:30px">
    <tr>
      <td width="17%" align="left" valign="top"><img src="${ctx}/commons/error-pages/images/1.gif" /></td>
      <td width="83%" align="left" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:30px">
          <tr>
            <td height="40"><h1>无法找到网页</h1></td>
          </tr>
          <tr>
            <td height="28px"><h2>HTTP 错误 404 - 找不到文件</h2></td>
          </tr>
          <tr>
            <td height="28px">您正在搜索的网页可能已经删除、更名或暂时不可用。 </td>
          </tr>
          <tr>
            <td height="140px" align="left">请尝试下列操作：<br />
              - 如果您在"地址"栏中键入了网页地址，请检查其拼写是否正确。<br />
              - 打开 <a href="${ctx}/">${ctx}/ </a>主页，寻找指向所需信息的链接。<br />
              - 单击 <a href="javascript:location.reload(true);">后退</a> 按钮尝试其他链接。</td>
          </tr>
          <tr>
            <td height="28px" align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="74%" align="left" style="padding-top:6px"><h3>(本页面10秒后自动转到首页)</h3></td>
                </tr>
              </table></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</div>
</body>
</html>
