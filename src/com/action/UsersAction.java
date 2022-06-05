package com.action;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.entity.Users;
import com.service.UsersService;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/users")
public class UsersAction extends BaseAction {
	// 注入Service 由于标签的存在 所以不需要getter setter
	@Autowired
	@Resource
	private UsersService usersService;

	// 准备添加数据
	@RequestMapping("createUsers.action")
	public String createUsers() {
		return "admin/addusers";
	}

	// 添加数据
	@RequestMapping("addUsers.action")
	public String addUsers(Users users) {
		this.usersService.insertUsers(users);
		return "redirect:/users/createUsers.action";
	}

	// 通过主键删除数据
	@RequestMapping("deleteUsers.action")
	public String deleteUsers(String id) {
		this.usersService.deleteUsers(id);
		return "redirect:/users/getAllUsers.action";
	}

	// 批量删除数据
	@RequestMapping("deleteUsersByIds.action")
	public String deleteUsersByIds() {
		String[] ids = this.getRequest().getParameterValues("usersid");
		for (String usersid : ids) {
			this.usersService.deleteUsers(usersid);
		}
		return "redirect:/users/getAllUsers.action";
	}

	// 更新数据
	@RequestMapping("updateUsers.action")
	public String updateUsers(Users users) {
		this.usersService.updateUsers(users);
		return "redirect:/users/getAllUsers.action";
	}

	// 显示全部数据
	@RequestMapping("getAllUsers.action")
	public String getAllUsers(String number) {
		List<Users> usersList = new ArrayList<Users>();
		List<Users> tempList = new ArrayList<Users>();
		tempList = this.usersService.getAllUsers();
		int pageNumber = tempList.size();
		int maxPage = pageNumber;
		if (maxPage % 10 == 0) {
			maxPage = maxPage / 10;
		} else {
			maxPage = maxPage / 10 + 1;
		}
		if (number == null) {
			number = "0";
		}
		int start = Integer.parseInt(number) * 10;
		int over = (Integer.parseInt(number) + 1) * 10;
		int count = pageNumber - over;
		if (count <= 0) {
			over = pageNumber;
		}
		for (int i = start; i < over; i++) {
			Users users = tempList.get(i);
			usersList.add(users);
		}
		String html = "";
		StringBuffer buffer = new StringBuffer();
		buffer.append("&nbsp;&nbsp;共为");
		buffer.append(maxPage);
		buffer.append("页&nbsp; 共有");
		buffer.append(pageNumber);
		buffer.append("条&nbsp; 当前为第");
		buffer.append((Integer.parseInt(number) + 1));
		buffer.append("页 &nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("首页");
		} else {
			buffer.append("<a href=\"users/getAllUsers.action?number=0\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"users/getAllUsers.action?number=" + (Integer.parseInt(number) - 1) + "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"users/getAllUsers.action?number=" + (Integer.parseInt(number) + 1) + "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"users/getAllUsers.action?number=" + (maxPage - 1) + "\">尾页</a>");
		}
		html = buffer.toString();
		this.getRequest().setAttribute("html", html);
		this.getRequest().setAttribute("usersList", usersList);
		return "admin/listusers";
	}

	// 按条件查询数据 (模糊查询)
	@RequestMapping("queryUsersByCond.action")
	public String queryUsersByCond(String cond, String name) {
		List<Users> usersList = new ArrayList<Users>();
		Users users = new Users();
		if (cond != null) {
			if ("username".equals(cond)) {
				users.setUsername(name);
				usersList = this.usersService.getUsersByLike(users);
			}
			if ("password".equals(cond)) {
				users.setPassword(name);
				usersList = this.usersService.getUsersByLike(users);
			}
			if ("realname".equals(cond)) {
				users.setRealname(name);
				usersList = this.usersService.getUsersByLike(users);
			}
			if ("sex".equals(cond)) {
				users.setSex(name);
				usersList = this.usersService.getUsersByLike(users);
			}
			if ("birthday".equals(cond)) {
				users.setBirthday(name);
				usersList = this.usersService.getUsersByLike(users);
			}
			if ("contact".equals(cond)) {
				users.setContact(name);
				usersList = this.usersService.getUsersByLike(users);
			}
			if ("image".equals(cond)) {
				users.setImage(name);
				usersList = this.usersService.getUsersByLike(users);
			}
			if ("regdate".equals(cond)) {
				users.setRegdate(name);
				usersList = this.usersService.getUsersByLike(users);
			}
		}
		this.getRequest().setAttribute("usersList", usersList);
		return "admin/queryusers";
	}

	// 按主键查询数据
	@RequestMapping("getUsersById.action")
	public String getUsersById(String id) {
		Users users = this.usersService.getUsersById(id);
		this.getRequest().setAttribute("users", users);
		return "admin/editusers";
	}

	public UsersService getUsersService() {
		return usersService;
	}

	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

}
