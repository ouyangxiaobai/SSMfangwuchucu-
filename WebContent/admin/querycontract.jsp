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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
			<td bgcolor="#FFFFFF"><table width="96%" border="0" align="center" cellpadding="4" cellspacing="1"
					bgcolor="#aec3de">
					<tr align="left" bgcolor="#F2FDFF">
						<td colspan="20" class="optiontitle">租赁合同信息列表</td>
					</tr>
					<tr align="center">
						<td align="center" bgcolor="#ebf0f7">合同号</td>
						<td align="center" bgcolor="#ebf0f7">出租人</td>
						<td align="center" bgcolor="#ebf0f7">房屋</td>
						<td align="center" bgcolor="#ebf0f7">创建日期</td>
						<td align="center" bgcolor="#ebf0f7">开始日期</td>
						<td align="center" bgcolor="#ebf0f7">结束日期</td>
						<td align="center" bgcolor="#ebf0f7">状态</td>
					</tr>
					<c:forEach items="${contractList}" var="contract">
						<tr align="center" bgcolor="#FFFFFF">
							<td align="center">${contract.cno}</td>
							<td align="center">${contract.username}</td>
							<td align="center">${contract.housename}</td>
							<td align="center">${contract.addtime}</td>
							<td align="center">${contract.thestart}</td>
							<td align="center">${contract.theend}</td>
							<td align="center">${contract.status}</td>
						</tr>
					</c:forEach>
					<tr align="right" bgcolor="#ebf0f7">
						<td colspan="20"><form action="contract/queryContractByCond.action" name="myform" method="post">
								查询条件<select name="cond" style="width: 100px"><option value="cno">按合同号查询</option>
									<option value="usersid">按出租人查询</option>
									<option value="houseid">按房屋查询</option>
									<option value="addtime">按创建日期查询</option>
									<option value="thestart">按开始日期查询</option>
									<option value="theend">按结束日期查询</option>
									<option value="status">按状态查询</option></select>关键字<input type="text" name="name" style="width: 100px" /><input
									type="submit" value="查询" />
							</form></td>
					</tr>
				</table></td>
		</tr>
	</table>
</body>
</html>