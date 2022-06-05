<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<script type="text/javascript" src="js/cate.js" charset="utf-8"></script>
<script language="javascript" type="text/javascript" src="<%=basePath%>My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<link href="css/four.css" rel="stylesheet" type="text/css" />
</head>
<%
	String message = (String) request.getAttribute("message");
	if (message == null) {
		message = "";
	}
	if (!message.trim().equals("")) {
		out.println("<script language='javascript'>");
		out.println("alert('" + message + "');");
		out.println("</script>");
	}
	request.removeAttribute("message");
%><body>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr valign="top">
			<td bgcolor="#FFFFFF"><form action="cate/updateCate.action" name="myform" method="post"
					onsubmit="return check()">
					<table width="40%" border="0" align="center" cellpadding="4" cellspacing="1" bgcolor="#aec3de">
						<tr align="center" bgcolor="#F2FDFF">
							<td align="left" colspan="2" class="optiontitle">修改房屋类型<input type="hidden" name="cateid"
								value="${cate.cateid}" /><input type="hidden" name="addtime" id="addtime" value="${cate.addtime}" /></td>
						</tr>
						<tr align="center" bgcolor="#F2FDFF">
							<td width="20%" align="right">类型名称</td>
							<td align="left"><input type="text" name="catename" style="width: 160px" id="catename"
								value="${cate.catename}" /></td>
						</tr>
						<tr align="center" bgcolor="#F2FDFF">
							<td width="20%" align="right">备注</td>
							<td align="left"><input type="text" name="memo" style="width: 160px" id="memo" value="${cate.memo}" /></td>
						</tr>
						<tr align="center" bgcolor="#ebf0f7">
							<td colspan="2"><input type="submit" name="Submit" value="提交" />&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset"
								name="Submit2" value="重置" /></td>
						</tr>
					</table>
				</form></td>
		</tr>
	</table>
</body>
</html>