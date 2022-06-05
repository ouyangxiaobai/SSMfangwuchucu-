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
			我的合同
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
							<span>我的合同</span>
						</h5>
						<div class="blank"></div>
						<table width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#dddddd">
							<tr>
								<td align="center" bgcolor="#ffffff">合同号</td>
								<td align="center" bgcolor="#ffffff">房屋</td>
								<td align="center" bgcolor="#ffffff">创建日期</td>
								<td align="center" bgcolor="#ffffff">开始日期</td>
								<td align="center" bgcolor="#ffffff">结束日期</td>
								<td align="center" bgcolor="#ffffff">状态</td>
								<td align="center" bgcolor="#ffffff" width="10%">操作</td>
							</tr>
							<c:forEach items="${contractList}" var="contract">
								<tr align="center" bgcolor="#FFFFFF">
									<td align="center">${contract.cno}</td>
									<td align="center">${contract.housename}</td>
									<td align="center">${contract.addtime}</td>
									<td align="center">${contract.thestart}</td>
									<td align="center">${contract.theend}</td>
									<td align="center">${contract.status}</td>
									<td align="center"><a href="${contract.files}">查看合同</a>&nbsp;|&nbsp;<c:if
											test="${contract.status eq '未完成'}">
											<a href="index/over.action?id=${contract.contractid}">完成</a>
										</c:if> <c:if test="${contract.status eq '完成'}">
											<a href="index/deleteContract.action?id=${contract.contractid}"
												onclick="{if(confirm('确定要删除吗?')){return true;}return false;}">删除</a>
										</c:if></td>
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
