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
			当前位置: <a href="<%=basePath%>">首页</a>
			<code> &gt; </code>
			新闻公告
		</div>
	</div>
	<div class="blank"></div>
	<div class="block clearfix">

		<div class="AreaL">
			<div id="category_tree">
				<div class="tit">房源类型</div>
				<dl class="clearfix" style="overflow: hidden;">
					<c:forEach items="${cateList}" var="cate">
						<div class="box1 cate" id="cate">
							<h1 style="border-top: none">
								<a href="index/cate.action?id=${cate.cateid }" class="  f_l">${cate.catename }</a>
							</h1>
						</div>
						<div style="clear: both"></div>
					</c:forEach>
				</dl>
			</div>
			<div class="blank"></div>
			<div class="box" id='history_div'>
				<div class="box_1">
					<h3>
						<span>热门房源</span>
					</h3>
					<div class="boxCenterList clearfix" id='history_list'>
						<c:forEach items="${hotList}" var="house">
							<ul class="clearfix">
								<li class="goodsimg"><a href="index/detail.action?id=${house.houseid }" target="_blank"><img
										src="${house.image }" alt="${house.housename }" class="B_blue" /> </a></li>
								<li><a href="index/detail.action?id=${house.houseid }" target="_blank" title="${house.housename }">${house.housename }</a>
									<br /> 租金： <font class="f1">￥${house.price }元/月</font></li>
							</ul>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="blank5"></div>
		</div>

		<div class="AreaR">
			<div>
				<div class="art_cat_box">
					<table width="100%" border="0" cellpadding="5" cellspacing="0">
						<tr>
							<th style="background: #e5e5e5">文章标题</th>
							<th style="background: #e5e5e5">作者</th>
							<th style="background: #e5e5e5">添加日期</th>
						</tr>
						<c:forEach items="${articleList}" var="article">
							<tr>
								<td><a title="${article.title }" href="index/read.action?id=${article.articleid }"
									style="text-decoration: none" class="f6">${article.title }</a></td>
								<td align="center">管理员</td>
								<td align="center">${article.addtime }</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<div class="blank5"></div>

		</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
