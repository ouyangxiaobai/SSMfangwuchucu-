<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>


<div class="footer">
	<div class="foot_con">
		<div class="blank"></div>

		<div class="blank"></div>
		<div id="bottomNav" class="rolling"></div>

		<div class="text" style="height: 1px; width: 1px; overflow: hidden;">
			<br />
		</div>

		<div class="record">
			版权所有 &nbsp; | &nbsp; 
			<a href="admin/index.jsp" target="_blank">后台管理入口</a>
		</div>
	</div>
	<div class="blank"></div>
</div>