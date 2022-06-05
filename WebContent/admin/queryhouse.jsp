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
						<td colspan="20" class="optiontitle">房屋信息列表</td>
					</tr>
					<tr align="center">
						<td align="center" bgcolor="#ebf0f7">用户</td>
						<td align="center" bgcolor="#ebf0f7">房屋</td>
						<td align="center" bgcolor="#ebf0f7">出租价</td>
						<td align="center" bgcolor="#ebf0f7">房屋类型</td>
						<td align="center" bgcolor="#ebf0f7">面积</td>
						<td align="center" bgcolor="#ebf0f7">楼层</td>
						<td align="center" bgcolor="#ebf0f7">朝向</td>
						<td align="center" bgcolor="#ebf0f7">发布日期</td>
						<td align="center" bgcolor="#ebf0f7">点击数</td>
						<td align="center" bgcolor="#ebf0f7">状态</td>
					</tr>
					<c:forEach items="${houseList}" var="house">
						<tr align="center" bgcolor="#FFFFFF">
							<td align="center">${house.username}</td>
							<td align="center">${house.housename}</td>
							<td align="center">${house.price}</td>
							<td align="center">${house.catename}</td>
							<td align="center">${house.mianji}</td>
							<td align="center">${house.louceng}</td>
							<td align="center">${house.chaoxiang}</td>
							<td align="center">${house.addtime}</td>
							<td align="center">${house.hits}</td>
							<td align="center">${house.status}</td>
						</tr>
					</c:forEach>
					<tr align="right" bgcolor="#ebf0f7">
						<td colspan="20"><form action="house/queryHouseByCond.action" name="myform" method="post">
								查询条件<select name="cond" style="width: 100px"><option value="usersid">按用户查询</option>
									<option value="housename">按房屋查询</option>
									<option value="price">按出租价查询</option>
									<option value="cateid">按房屋类型查询</option>
									<option value="mianji">按面积查询</option>
									<option value="louceng">按楼层查询</option>
									<option value="chaoxiang">按朝向查询</option>
									<option value="addtime">按发布日期查询</option>
									<option value="hits">按点击数查询</option>
									<option value="status">按状态查询</option></select>关键字<input type="text" name="name" style="width: 100px" /><input
									type="submit" value="查询" />
							</form></td>
					</tr>
				</table></td>
		</tr>
	</table>
</body>
</html>