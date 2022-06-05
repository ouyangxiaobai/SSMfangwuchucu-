<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<base href="<%=basePath%>" />
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	background-color: #ecf4ff;
}

.dtree {
	font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #0000ff;
	white-space: nowrap;
}

.dtree img {
	border: 0px;
	vertical-align: middle;
}

.dtree a {
	color: #333;
	text-decoration: none;
}

.dtree a.node, .dtree a.nodeSel {
	white-space: nowrap;
	padding: 1px 2px 1px 2px;
}

.dtree a.node:hover, .dtree a.nodeSel:hover {
	color: #0000ff;
}

.dtree a.nodeSel {
	background-color: #AED0F4;
}

.dtree .clip {
	overflow: hidden;
}
-->
</style>
<link href="css/four.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/dtree.js"></script>
</head>
<body>
	<table width="96%" border="0" cellpadding="10" cellspacing="0" align="center">
		<tr>
			<td valign="top"><div class="menu">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td><script type="text/javascript">
							d = new dTree('d');
							d.config.target="main";
							d.add(0,-1,'管理菜单');
							d.add(1, 0, '管理员', '');
							d.add(11, 1, '新增管理员', 'admin/createAdmin.action');
							d.add(12, 1, '管理员列表','admin/getAllAdmin.action');
							d.add(13, 1, '查询管理员','admin/queryAdminByCond.action');
							d.add(2, 0, '用户', '');
							d.add(22, 2, '用户列表','users/getAllUsers.action');
							d.add(23, 2, '查询用户','users/queryUsersByCond.action');
							d.add(3, 0, '新闻公告', '');
							d.add(31, 3, '新增新闻公告', 'article/createArticle.action');
							d.add(32, 3, '新闻公告列表','article/getAllArticle.action');
							d.add(33, 3, '查询新闻公告','article/queryArticleByCond.action');
							d.add(4, 0, '房屋类型', '');
							d.add(41, 4, '新增房屋类型', 'cate/createCate.action');
							d.add(42, 4, '房屋类型列表','cate/getAllCate.action');
							d.add(43, 4, '查询房屋类型','cate/queryCateByCond.action');
							d.add(5, 0, '房屋', '');
							d.add(52, 5, '房屋列表','house/getAllHouse.action');
							d.add(53, 5, '查询房屋','house/queryHouseByCond.action');
							d.add(6, 0, '租赁合同', '');
							d.add(62, 6, '租赁合同列表','contract/getAllContract.action');
							d.add(63, 6, '查询租赁合同','contract/queryContractByCond.action');
							d.add(7, 0, '留言交流', '');
							d.add(72, 7, '留言交流列表','bbs/getAllBbs.action');
							d.add(73, 7, '查询留言交流','bbs/queryBbsByCond.action');
							d.add(8, 0, '留言回复', '');
							d.add(82, 8, '留言回复列表','rebbs/getAllRebbs.action');
							d.add(83, 8, '查询留言回复','rebbs/queryRebbsByCond.action');
							document.write(d);
							</script></td>
						</tr>
					</table>
				</div></td>
		</tr>
	</table>
</body>
</html>