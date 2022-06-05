package com.action;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.entity.Bbs;
import com.service.BbsService;
import com.entity.Users;
import com.service.UsersService;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/bbs")
public class BbsAction extends BaseAction {
	// 注入Service 由于标签的存在 所以不需要getter setter
	@Autowired
	@Resource
	private BbsService bbsService;
	@Autowired
	@Resource
	private UsersService usersService;

	// 准备添加数据
	@RequestMapping("createBbs.action")
	public String createBbs() {
		List<Users> usersList = this.usersService.getAllUsers();
		this.getRequest().setAttribute("usersList", usersList);
		return "admin/addbbs";
	}

	// 添加数据
	@RequestMapping("addBbs.action")
	public String addBbs(Bbs bbs) {
		this.bbsService.insertBbs(bbs);
		return "redirect:/bbs/createBbs.action";
	}

	// 通过主键删除数据
	@RequestMapping("deleteBbs.action")
	public String deleteBbs(String id) {
		this.bbsService.deleteBbs(id);
		return "redirect:/bbs/getAllBbs.action";
	}

	// 批量删除数据
	@RequestMapping("deleteBbsByIds.action")
	public String deleteBbsByIds() {
		String[] ids = this.getRequest().getParameterValues("bbsid");
		for (String bbsid : ids) {
			this.bbsService.deleteBbs(bbsid);
		}
		return "redirect:/bbs/getAllBbs.action";
	}

	// 更新数据
	@RequestMapping("updateBbs.action")
	public String updateBbs(Bbs bbs) {
		this.bbsService.updateBbs(bbs);
		return "redirect:/bbs/getAllBbs.action";
	}

	// 显示全部数据
	@RequestMapping("getAllBbs.action")
	public String getAllBbs(String number) {
		List<Bbs> bbsList = new ArrayList<Bbs>();
		List<Bbs> tempList = new ArrayList<Bbs>();
		tempList = this.bbsService.getAllBbs();
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
			Bbs bbs = tempList.get(i);
			bbsList.add(bbs);
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
			buffer.append("<a href=\"bbs/getAllBbs.action?number=0\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"bbs/getAllBbs.action?number=" + (Integer.parseInt(number) - 1) + "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"bbs/getAllBbs.action?number=" + (Integer.parseInt(number) + 1) + "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"bbs/getAllBbs.action?number=" + (maxPage - 1) + "\">尾页</a>");
		}
		html = buffer.toString();
		this.getRequest().setAttribute("html", html);
		this.getRequest().setAttribute("bbsList", bbsList);
		return "admin/listbbs";
	}

	// 按条件查询数据 (模糊查询)
	@RequestMapping("queryBbsByCond.action")
	public String queryBbsByCond(String cond, String name) {
		List<Bbs> bbsList = new ArrayList<Bbs>();
		Bbs bbs = new Bbs();
		if (cond != null) {
			if ("usersid".equals(cond)) {
				bbs.setUsersid(name);
				bbsList = this.bbsService.getBbsByLike(bbs);
			}
			if ("title".equals(cond)) {
				bbs.setTitle(name);
				bbsList = this.bbsService.getBbsByLike(bbs);
			}
			if ("contents".equals(cond)) {
				bbs.setContents(name);
				bbsList = this.bbsService.getBbsByLike(bbs);
			}
			if ("addtime".equals(cond)) {
				bbs.setAddtime(name);
				bbsList = this.bbsService.getBbsByLike(bbs);
			}
			if ("hits".equals(cond)) {
				bbs.setHits(name);
				bbsList = this.bbsService.getBbsByLike(bbs);
			}
			if ("repnum".equals(cond)) {
				bbs.setRepnum(name);
				bbsList = this.bbsService.getBbsByLike(bbs);
			}
		}
		this.getRequest().setAttribute("bbsList", bbsList);
		return "admin/querybbs";
	}

	// 按主键查询数据
	@RequestMapping("getBbsById.action")
	public String getBbsById(String id) {
		Bbs bbs = this.bbsService.getBbsById(id);
		this.getRequest().setAttribute("bbs", bbs);
		List<Users> usersList = this.usersService.getAllUsers();
		this.getRequest().setAttribute("usersList", usersList);
		return "admin/editbbs";
	}

	public BbsService getBbsService() {
		return bbsService;
	}

	public void setBbsService(BbsService bbsService) {
		this.bbsService = bbsService;
	}

}
