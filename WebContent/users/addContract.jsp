<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<title>${title }</title>
<script language="javascript" type="text/javascript" src="<%=basePath%>laydate/laydate.js" charset="utf-8"></script>
<script type="text/javascript">
function selimage(){
window.open("<%=basePath%>saveimage.jsp","","toolbar=no,location=no,directories=no,status=no,menubar=no,resizable=yes,copyhistory=no,scrollbars=yes,width=400,height=240,top="+(screen.availHeight-240)/2+",left="+(screen.availWidth-400)/2+"");}
</script>
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="block box">
		<div class="blank"></div>
		<div id="ur_here">
			当前位置: <a href="<%=basePath%>">首页</a>
			<code> &gt; </code>
			发布房源
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
							<span>发布房源</span>
						</h5>
						<div class="blank"></div>
						<form action="index/addContract.action" name="myform" method="post">
							<table width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#dddddd">
								<tr>
									<td width="28%" align="right" bgcolor="#FFFFFF">合同号：</td>
									<td align="left" bgcolor="#FFFFFF"><input name="cno" readonly="readonly"
										type="text" size="25" class="inputBg" value="${cno }" /></td>
								</tr>
								<tr>
									<td width="28%" align="right" bgcolor="#FFFFFF">房屋：</td>
									<td align="left" bgcolor="#FFFFFF"><select name="houseid" style="width: 160px" id="houseid"><c:forEach
												items="${houseList}" var="house">
												<option value="${house.houseid}">${house.housename }</option>
											</c:forEach></select></td>
								</tr>
								<tr>
									<td width="28%" align="right" bgcolor="#FFFFFF">合同：</td>
									<td align="left" bgcolor="#FFFFFF"><input type="text" name="files" size="25" class="inputBg" id="image"
										onclick="selimage();" readonly="readonly" /></td>
								</tr>
								<tr>
									<td width="28%" align="right" bgcolor="#FFFFFF">开始日期：</td>
									<td align="left" bgcolor="#FFFFFF"><input name="thestart" readonly="readonly" onclick="laydate()"
										type="text" size="25" class="inputBg" /></td>
								</tr>
								<tr>
									<td width="28%" align="right" bgcolor="#FFFFFF">结束日期：</td>
									<td align="left" bgcolor="#FFFFFF"><input name="theend" readonly="readonly" onclick="laydate()"
										type="text" size="25" class="inputBg" /></td>
								</tr>
								<tr>
									<td colspan="2" align="center" bgcolor="#FFFFFF"><input type="submit" class="bnt_blue_1"
										style="border: none;" value="确认发布" /></td>
								</tr>
							</table>
						</form>

					</div>
				</div>
			</div>
		</div>

	</div>
	<div class="blank"></div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
