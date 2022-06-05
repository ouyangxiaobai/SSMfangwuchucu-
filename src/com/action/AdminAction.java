package com.action;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.entity.Admin;
import com.service.AdminService;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/admin")
public class AdminAction extends BaseAction {
	// 注入Service 由于标签的存在 所以不需要getter setter
	@Autowired
	@Resource
	private AdminService adminService;

	// 管理员登录 1 验证用户名是否存在 2 验证密码是否正确
	@RequestMapping("login.action")
	public String login() {
		String username = this.getRequest().getParameter("username");
		String password = this.getRequest().getParameter("password");
		Admin adminEntity = new Admin();
		adminEntity.setUsername(username);
		List<Admin> adminlist = this.adminService.getAdminByCond(adminEntity);
		if (adminlist.size() == 0) {
			this.getRequest().setAttribute("message", "用户名不存在");
			return "admin/index";
		} else {
			Admin admin = adminlist.get(0);
			if (password.equals(admin.getPassword())) {
				this.getSession().setAttribute("adminid", admin.getAdminid());
				this.getSession().setAttribute("adminname", admin.getUsername());
				this.getSession().setAttribute("realname", admin.getRealname());
			} else {
				this.getRequest().setAttribute("message", "密码错误");
				return "admin/index";
			}
		}
		return "admin/main";
	}

	// 修改密码
	@RequestMapping("editpwd.action")
	public String editpwd() {
		String adminid = (String) this.getSession().getAttribute("adminid");
		String password = this.getRequest().getParameter("password");
		String repassword = this.getRequest().getParameter("repassword");
		Admin admin = this.adminService.getAdminById(adminid);
		if (password.equals(admin.getPassword())) {
			admin.setPassword(repassword);
			this.adminService.updateAdmin(admin);
		} else {
			this.getRequest().setAttribute("message", "旧密码错误");
		}
		return "admin/editpwd";
	}

	// 管理员退出登录
	@RequestMapping("exit.action")
	public String exit() {
		this.getSession().removeAttribute("adminid");
		this.getSession().removeAttribute("adminname");
		this.getSession().removeAttribute("realname");
		return "admin/index";
	}

	// 准备添加数据
	@RequestMapping("createAdmin.action")
	public String createAdmin() {
		return "admin/addadmin";
	}

	// 添加数据
	@RequestMapping("addAdmin.action")
	public String addAdmin(Admin admin) {
		this.adminService.insertAdmin(admin);
		return "redirect:/admin/createAdmin.action";
	}

	// 通过主键删除数据
	@RequestMapping("deleteAdmin.action")
	public String deleteAdmin(String id) {
		this.adminService.deleteAdmin(id);
		return "redirect:/admin/getAllAdmin.action";
	}

	// 批量删除数据
	@RequestMapping("deleteAdminByIds.action")
	public String deleteAdminByIds() {
		String[] ids = this.getRequest().getParameterValues("adminid");
		for (String adminid : ids) {
			this.adminService.deleteAdmin(adminid);
		}
		return "redirect:/admin/getAllAdmin.action";
	}

	// 更新数据
	@RequestMapping("updateAdmin.action")
	public String updateAdmin(Admin admin) {
		this.adminService.updateAdmin(admin);
		return "redirect:/admin/getAllAdmin.action";
	}

	// 显示全部数据
	@RequestMapping("getAllAdmin.action")
	public String getAllAdmin(String number) {
		List<Admin> adminList = new ArrayList<Admin>();
		List<Admin> tempList = new ArrayList<Admin>();
		tempList = this.adminService.getAllAdmin();
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
			Admin admin = tempList.get(i);
			adminList.add(admin);
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
			buffer.append("<a href=\"admin/getAllAdmin.action?number=0\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"admin/getAllAdmin.action?number=" + (Integer.parseInt(number) - 1) + "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"admin/getAllAdmin.action?number=" + (Integer.parseInt(number) + 1) + "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"admin/getAllAdmin.action?number=" + (maxPage - 1) + "\">尾页</a>");
		}
		html = buffer.toString();
		this.getRequest().setAttribute("html", html);
		this.getRequest().setAttribute("adminList", adminList);
		return "admin/listadmin";
	}

	// 按条件查询数据 (模糊查询)
	@RequestMapping("queryAdminByCond.action")
	public String queryAdminByCond(String cond, String name) {
		List<Admin> adminList = new ArrayList<Admin>();
		Admin admin = new Admin();
		if (cond != null) {
			if ("username".equals(cond)) {
				admin.setUsername(name);
				adminList = this.adminService.getAdminByLike(admin);
			}
			if ("password".equals(cond)) {
				admin.setPassword(name);
				adminList = this.adminService.getAdminByLike(admin);
			}
			if ("realname".equals(cond)) {
				admin.setRealname(name);
				adminList = this.adminService.getAdminByLike(admin);
			}
			if ("contact".equals(cond)) {
				admin.setContact(name);
				adminList = this.adminService.getAdminByLike(admin);
			}
		}
		this.getRequest().setAttribute("adminList", adminList);
		return "admin/queryadmin";
	}

	// 按主键查询数据
	@RequestMapping("getAdminById.action")
	public String getAdminById(String id) {
		Admin admin = this.adminService.getAdminById(id);
		this.getRequest().setAttribute("admin", admin);
		return "admin/editadmin";
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

}
