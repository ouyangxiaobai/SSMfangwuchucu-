<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<title>${title }</title>
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="block box">
		<div class="blank"></div>
		<div id="ur_here">
			当前位置: <a href=".">首页</a>
			<code> &gt; </code>
			我的房源
		</div>
	</div>
	<div class="blank"></div>

	<div class="blank"></div>
	<div class="block clearfix">

		<div class="AreaL">
			<div class="box">
				<div class="box_1">
					<div class="userCenterBox">
						<jsp:include page="usermenu.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
		<div class="AreaR">
			<div class="box">
				<div class="box_1">
					<div class="userCenterBox boxCenterList clearfix" style="_height: 1%;">
						<h5>
							<span>我的房源</span>
						</h5>
						<div class="blank"></div>
						<table width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#dddddd">
							<tr>
								<td align="center" bgcolor="#ffffff">房屋</td>
								<td align="center" bgcolor="#ffffff">出租价</td>
								<td align="center" bgcolor="#ffffff">房屋类型</td>
								<td align="center" bgcolor="#ffffff">面积</td>
								<td align="center" bgcolor="#ffffff">楼层</td>
								<td align="center" bgcolor="#ffffff">朝向</td>
								<td align="center" bgcolor="#ffffff">发布日期</td>
								<td align="center" bgcolor="#ffffff">点击数</td>
								<td align="center" bgcolor="#ffffff">状态</td>
								<td align="center" bgcolor="#ffffff" width="10%">操作</td>
							</tr>
							<c:forEach items="${houseList}" var="house">
								<tr align="center" bgcolor="#FFFFFF">
									<td align="center">${house.housename}</td>
									<td align="center">${house.price}</td>
									<td align="center">${house.catename}</td>
									<td align="center">${house.mianji}</td>
									<td align="center">${house.louceng}</td>
									<td align="center">${house.chaoxiang}</td>
									<td align="center">${house.addtime}</td>
									<td align="center">${house.hits}</td>
									<td align="center">${house.status}</td>
									<td align="center"><a href="index/getHouseById.action?id=${house.houseid}">编辑</a>&nbsp;|&nbsp;<a href="index/deleteHouse.action?id=${house.houseid}"
										onclick="{if(confirm('确定要删除吗?')){return true;}return false;}">删除</a></td>
								</tr>
							</c:forEach>
						</table>
						<div class="blank5"></div>
						<table width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#dddddd">
							<tr>
								<td align="center" bgcolor="#ffffff">${html}</td>
							</tr>
						</table>

					</div>
				</div>
			</div>
		</div>

	</div>
	<div class="blank"></div>



	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
