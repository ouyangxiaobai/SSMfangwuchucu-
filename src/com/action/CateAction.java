package com.action;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.entity.Cate;
import com.service.CateService;
import com.util.VeDate;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/cate")
public class CateAction extends BaseAction {
	// 注入Service 由于标签的存在 所以不需要getter setter
	@Autowired
	@Resource
	private CateService cateService;

	// 准备添加数据
	@RequestMapping("createCate.action")
	public String createCate() {
		return "admin/addcate";
	}

	// 添加数据
	@RequestMapping("addCate.action")
	public String addCate(Cate cate) {
		cate.setAddtime(VeDate.getStringDateShort());
		this.cateService.insertCate(cate);
		return "redirect:/cate/createCate.action";
	}

	// 通过主键删除数据
	@RequestMapping("deleteCate.action")
	public String deleteCate(String id) {
		this.cateService.deleteCate(id);
		return "redirect:/cate/getAllCate.action";
	}

	// 批量删除数据
	@RequestMapping("deleteCateByIds.action")
	public String deleteCateByIds() {
		String[] ids = this.getRequest().getParameterValues("cateid");
		for (String cateid : ids) {
			this.cateService.deleteCate(cateid);
		}
		return "redirect:/cate/getAllCate.action";
	}

	// 更新数据
	@RequestMapping("updateCate.action")
	public String updateCate(Cate cate) {
		this.cateService.updateCate(cate);
		return "redirect:/cate/getAllCate.action";
	}

	// 显示全部数据
	@RequestMapping("getAllCate.action")
	public String getAllCate(String number) {
		List<Cate> cateList = new ArrayList<Cate>();
		List<Cate> tempList = new ArrayList<Cate>();
		tempList = this.cateService.getAllCate();
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
			Cate cate = tempList.get(i);
			cateList.add(cate);
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
			buffer.append("<a href=\"cate/getAllCate.action?number=0\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"cate/getAllCate.action?number=" + (Integer.parseInt(number) - 1) + "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"cate/getAllCate.action?number=" + (Integer.parseInt(number) + 1) + "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"cate/getAllCate.action?number=" + (maxPage - 1) + "\">尾页</a>");
		}
		html = buffer.toString();
		this.getRequest().setAttribute("html", html);
		this.getRequest().setAttribute("cateList", cateList);
		return "admin/listcate";
	}

	// 按条件查询数据 (模糊查询)
	@RequestMapping("queryCateByCond.action")
	public String queryCateByCond(String cond, String name) {
		List<Cate> cateList = new ArrayList<Cate>();
		Cate cate = new Cate();
		if (cond != null) {
			if ("catename".equals(cond)) {
				cate.setCatename(name);
				cateList = this.cateService.getCateByLike(cate);
			}
			if ("memo".equals(cond)) {
				cate.setMemo(name);
				cateList = this.cateService.getCateByLike(cate);
			}
			if ("addtime".equals(cond)) {
				cate.setAddtime(name);
				cateList = this.cateService.getCateByLike(cate);
			}
		}
		this.getRequest().setAttribute("cateList", cateList);
		return "admin/querycate";
	}

	// 按主键查询数据
	@RequestMapping("getCateById.action")
	public String getCateById(String id) {
		Cate cate = this.cateService.getCateById(id);
		this.getRequest().setAttribute("cate", cate);
		return "admin/editcate";
	}

	public CateService getCateService() {
		return cateService;
	}

	public void setCateService(CateService cateService) {
		this.cateService = cateService;
	}

}
