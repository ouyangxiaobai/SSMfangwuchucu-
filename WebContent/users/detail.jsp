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
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="block box">
		<div class="blank"></div>
		<div id="ur_here">
			当前位置: <a href=".">首页</a>
			<code> &gt; </code>
			<a href="index/all.action">全部商品</a>
			<code> &gt; </code>
			<a href="index/cate.action?id=${house.cateid }">${house.housename }</a>
			<code> &gt; </code>
			${house.housename }
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

			<div id="goodsInfo" class="clearfix">
				<div class="imgInfo">
					<img src="${house.image}" alt="${house.housename }" width="360px;" height="360px" />
					<div class="blank5"></div>
					<div class="blank"></div>
				</div>
				<div class="textInfo">
					<h1 class="clearfix">${house.housename }</h1>
					<ul class="ul2 clearfix">
						<li class="clearfix" style="width: 100%">
							<dd>
								<strong>租金：</strong><font class="shop" id="ECS_SHOPPRICE">￥${house.price }元/月</font>

							</dd>
						</li>
						<li class="clearfix" style="width: 100%">
							<dd>
								<strong>房源类型：</strong><a href="index/cate.action?id=${house.cateid }">${house.catename }</a>
							</dd>
						</li>
						<li class="clearfix" style="width: 100%">
							<dd>
								<strong>发布日期：</strong>${house.addtime }
							</dd>
						</li>
						<li class="clearfix" style="width: 100%">
							<dd>
								<strong>面积：</strong>${house.mianji }
							</dd>
						</li>
						<li class="clearfix" style="width: 100%">
							<dd>
								<strong>楼层：</strong>${house.louceng }
							</dd>
						</li>
						<li class="clearfix" style="width: 100%">
							<dd>
								<strong>朝向：</strong>${house.chaoxiang }
							</dd>
						</li>
						<li class="clearfix" style="width: 100%">
							<dd>
								<strong>点击数：</strong>${house.hits}
							</dd>
						</li>
					</ul>
					<ul class="bnt_ul">
						<%-- <li class="padd" ><a href="http://wpa.qq.com/msgrd?v=3&uin=${house.contact }&site=qq&menu=no">联系房东</a></li> --%>
						<li class="padd" onclick="window.open('tencent://message/?uin=${house.contact }','_self')"><a>联系房东</a></li>
					</ul>
				</div>
			</div>
			<div class="blank"></div>


			<div class="box">
				<div style="padding: 0 0px;">
					<div id="com_b" class="history clearfix">
						<h2>房源描述</h2>
					</div>
				</div>
				<div class="box_1">
					<div id="com_v" class="  " style="padding: 6px;"></div>
					<div id="com_h">
						<blockquote>${house.contents}</blockquote>
					</div>
				</div>
			</div>
		</div>

	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
