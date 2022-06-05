package com.action;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.entity.House;
import com.service.HouseService;
import com.entity.Users;
import com.entity.Cate;
import com.service.UsersService;
import com.service.CateService;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/house")
public class HouseAction extends BaseAction {
	// 注入Service 由于标签的存在 所以不需要getter setter
	@Autowired
	@Resource
	private HouseService houseService;
	@Autowired
	@Resource
	private UsersService usersService;
	@Autowired
	@Resource
	private CateService cateService;

	// 准备添加数据
	@RequestMapping("createHouse.action")
	public String createHouse() {
		List<Users> usersList = this.usersService.getAllUsers();
		this.getRequest().setAttribute("usersList", usersList);
		List<Cate> cateList = this.cateService.getAllCate();
		this.getRequest().setAttribute("cateList", cateList);
		return "admin/addhouse";
	}

	// 添加数据
	@RequestMapping("addHouse.action")
	public String addHouse(House house) {
		this.houseService.insertHouse(house);
		return "redirect:/house/createHouse.action";
	}

	// 通过主键删除数据
	@RequestMapping("deleteHouse.action")
	public String deleteHouse(String id) {
		this.houseService.deleteHouse(id);
		return "redirect:/house/getAllHouse.action";
	}

	// 批量删除数据
	@RequestMapping("deleteHouseByIds.action")
	public String deleteHouseByIds() {
		String[] ids = this.getRequest().getParameterValues("houseid");
		for (String houseid : ids) {
			this.houseService.deleteHouse(houseid);
		}
		return "redirect:/house/getAllHouse.action";
	}

	// 更新数据
	@RequestMapping("updateHouse.action")
	public String updateHouse(House house) {
		this.houseService.updateHouse(house);
		return "redirect:/house/getAllHouse.action";
	}

	// 显示全部数据
	@RequestMapping("getAllHouse.action")
	public String getAllHouse(String number) {
		List<House> houseList = new ArrayList<House>();
		List<House> tempList = new ArrayList<House>();
		tempList = this.houseService.getAllHouse();
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
			House house = tempList.get(i);
			houseList.add(house);
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
			buffer.append("<a href=\"house/getAllHouse.action?number=0\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"house/getAllHouse.action?number=" + (Integer.parseInt(number) - 1) + "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"house/getAllHouse.action?number=" + (Integer.parseInt(number) + 1) + "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"house/getAllHouse.action?number=" + (maxPage - 1) + "\">尾页</a>");
		}
		html = buffer.toString();
		this.getRequest().setAttribute("html", html);
		this.getRequest().setAttribute("houseList", houseList);
		return "admin/listhouse";
	}

	// 按条件查询数据 (模糊查询)
	@RequestMapping("queryHouseByCond.action")
	public String queryHouseByCond(String cond, String name) {
		List<House> houseList = new ArrayList<House>();
		House house = new House();
		if (cond != null) {
			if ("usersid".equals(cond)) {
				house.setUsersid(name);
				houseList = this.houseService.getHouseByLike(house);
			}
			if ("housename".equals(cond)) {
				house.setHousename(name);
				houseList = this.houseService.getHouseByLike(house);
			}
			if ("image".equals(cond)) {
				house.setImage(name);
				houseList = this.houseService.getHouseByLike(house);
			}
			if ("price".equals(cond)) {
				house.setPrice(name);
				houseList = this.houseService.getHouseByLike(house);
			}
			if ("cateid".equals(cond)) {
				house.setCateid(name);
				houseList = this.houseService.getHouseByLike(house);
			}
			if ("mianji".equals(cond)) {
				house.setMianji(name);
				houseList = this.houseService.getHouseByLike(house);
			}
			if ("louceng".equals(cond)) {
				house.setLouceng(name);
				houseList = this.houseService.getHouseByLike(house);
			}
			if ("chaoxiang".equals(cond)) {
				house.setChaoxiang(name);
				houseList = this.houseService.getHouseByLike(house);
			}
			if ("addtime".equals(cond)) {
				house.setAddtime(name);
				houseList = this.houseService.getHouseByLike(house);
			}
			if ("hits".equals(cond)) {
				house.setHits(name);
				houseList = this.houseService.getHouseByLike(house);
			}
			if ("status".equals(cond)) {
				house.setStatus(name);
				houseList = this.houseService.getHouseByLike(house);
			}
			if ("contents".equals(cond)) {
				house.setContents(name);
				houseList = this.houseService.getHouseByLike(house);
			}
		}
		this.getRequest().setAttribute("houseList", houseList);
		return "admin/queryhouse";
	}

	// 按主键查询数据
	@RequestMapping("getHouseById.action")
	public String getHouseById(String id) {
		House house = this.houseService.getHouseById(id);
		this.getRequest().setAttribute("house", house);
		List<Users> usersList = this.usersService.getAllUsers();
		this.getRequest().setAttribute("usersList", usersList);
		List<Cate> cateList = this.cateService.getAllCate();
		this.getRequest().setAttribute("cateList", cateList);
		return "admin/edithouse";
	}

	public HouseService getHouseService() {
		return houseService;
	}

	public void setHouseService(HouseService houseService) {
		this.houseService = houseService;
	}

}
