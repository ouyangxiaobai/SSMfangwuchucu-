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
						<form action="index/updateHouse.action" name="myform" method="post">
							<table width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#dddddd">
								<tr>
									<td width="28%" align="right" bgcolor="#FFFFFF">房屋名称：</td>
									<td align="left" bgcolor="#FFFFFF"><input name="housename" type="text" size="25" class="inputBg"
										value="${house.housename }" /></td>
								</tr>
								<tr>
									<td width="28%" align="right" bgcolor="#FFFFFF">房屋类型：</td>
									<td align="left" bgcolor="#FFFFFF"><select name="cateid" style="width: 160px" id="cateid"><c:forEach
												items="${cateList}" var="cate">
												<option value="${cate.cateid}">${cate.catename }</option>
											</c:forEach></select></td>
								</tr>
								<tr>
									<td width="28%" align="right" bgcolor="#FFFFFF">出租价：</td>
									<td align="left" bgcolor="#FFFFFF"><input name="price" type="text" size="25" class="inputBg"
										value="${house.price }" /></td>
								</tr>
								<tr>
									<td width="28%" align="right" bgcolor="#FFFFFF">图片：</td>
									<td align="left" bgcolor="#FFFFFF"><input type="text" name="image" size="25" class="inputBg" id="image"
										onclick="selimage();" readonly="readonly" value="${house.image }" /></td>
								</tr>
								<tr>
									<td width="28%" align="right" bgcolor="#FFFFFF">面积：</td>
									<td align="left" bgcolor="#FFFFFF"><input name="mianji" type="text" size="25" class="inputBg"
										value="${house.mianji }" /></td>
								</tr>
								<tr>
									<td width="28%" align="right" bgcolor="#FFFFFF">楼层：</td>
									<td align="left" bgcolor="#FFFFFF"><input name="louceng" type="text" size="25" class="inputBg"
										value="${house.louceng }" /></td>
								</tr>
								<tr>
									<td width="28%" align="right" bgcolor="#FFFFFF">朝向：</td>
									<td align="left" bgcolor="#FFFFFF"><input name="chaoxiang" type="text" size="25" class="inputBg"
										value="${house.chaoxiang }" /></td>
								</tr>
								<tr>
									<td width="28%" align="right" bgcolor="#FFFFFF">房屋介绍：</td>
									<td align="left" bgcolor="#FFFFFF"><script type="text/javascript" src="ckeditor/ckeditor.js"></script> <textarea
											cols="80" name="contents" id="contents" rows="10">${house.contents }</textarea> <script
											type="text/javascript">
												CKEDITOR.replace('contents', {
													language : 'zh-cn'
												});
											</script></td>
								</tr>
								<tr>
									<td colspan="2" align="center" bgcolor="#FFFFFF"><input type="hidden" name="addtime"
										value="${house.addtime }" /> <input type="hidden" name="hits" value="${house.hits }" /> <input type="hidden"
										name="usersid" value="${house.usersid }" /> <input type="hidden" name="houseid" value="${house.houseid }" />
										<input type="hidden" name="status" value="${house.status }" /> <input type="submit" class="bnt_blue_1"
										style="border: none;" value="确认修改" /></td>
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
