package com.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.Article;
import com.entity.Bbs;
import com.entity.Cate;
import com.entity.Contract;
import com.entity.House;
import com.entity.Rebbs;
import com.entity.Users;
import com.service.ArticleService;
import com.service.BbsService;
import com.service.CateService;
import com.service.ContractService;
import com.service.HouseService;
import com.service.RebbsService;
import com.service.UsersService;
import com.util.VeDate;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/index")
public class IndexAction extends BaseAction {

	@Autowired
	@Resource
	private UsersService usersService;
	@Autowired
	@Resource
	private ArticleService articleService;
	@Autowired
	@Resource
	private CateService cateService;
	@Autowired
	@Resource
	private HouseService houseService;
	@Autowired
	@Resource
	private ContractService contractService;
	@Autowired
	@Resource
	private BbsService bbsService;
	@Autowired
	@Resource
	private RebbsService rebbsService;

	// 公共方法 提供公共查询数据
	private void front() {
		this.getRequest().setAttribute("cateList", this.cateService.getAllCate());
		this.getRequest().setAttribute("hotList", this.houseService.getHotHouse());
		this.getRequest().setAttribute("title", "房屋租赁系统");
	}

	// 首页显示
	@RequestMapping("index.action")
	public String index() {
		this.front();
		List<Article> articleList = this.articleService.getFrontArticle();
		this.getRequest().setAttribute("articleList", articleList);
		List<Cate> frontList = new ArrayList<Cate>();
		List<Cate> cateList = this.cateService.getFrontCate();
		for (Cate cate : cateList) {
			List<House> houseList = this.houseService.getFrontHouse(cate.getCateid());
			cate.setHouseList(houseList);
			frontList.add(cate);
		}
		this.getRequest().setAttribute("frontList", frontList);
		this.getRequest().setAttribute("newsList", this.houseService.getNewsHouse());
		return "users/index";
	}

	// 分类查询
	@RequestMapping("cate.action")
	public String cate(String id) {
		this.front();
		House house = new House();
		house.setStatus("待租");
		house.setCateid(id);
		List<House> houseList = this.houseService.getHouseByCond(house);
		this.getRequest().setAttribute("houseList", houseList);
		return "users/list";
	}

	// 模糊查询
	@RequestMapping("query.action")
	public String query() {
		this.front();
		String name = this.getRequest().getParameter("name");
		House house = new House();
		house.setStatus("待租");
		house.setHousename(name);
		List<House> houseList = this.houseService.getHouseByLike(house);
		this.getRequest().setAttribute("houseList", houseList);
		return "users/list";
	}

	// 全部查询
	@RequestMapping("all.action")
	public String all() {
		this.front();
		House house = new House();
		house.setStatus("待租");
		List<House> houseList = this.houseService.getHouseByCond(house);
		this.getRequest().setAttribute("houseList", houseList);
		return "users/list";
	}

	// 全部查询
	@RequestMapping("detail.action")
	public String detail(String id) {
		this.front();
		House house = this.houseService.getHouseById(id);
		house.setHits("" + (Integer.parseInt(house.getHits()) + 1));
		this.houseService.updateHouse(house);
		this.getRequest().setAttribute("house", house);
		return "users/detail";
	}

	// 公告
	@RequestMapping("article.action")
	public String article(String number) {
		this.front();
		List<Article> articleList = new ArrayList<Article>();
		List<Article> tempList = this.articleService.getAllArticle();
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
			Article x = tempList.get(i);
			articleList.add(x);
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
			buffer.append("<a href=\"index/article.action?number=0\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"index/article.action?number=" + (Integer.parseInt(number) - 1) + "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"index/article.action?number=" + (Integer.parseInt(number) + 1) + "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"index/article.action?number=" + (maxPage - 1) + "\">尾页</a>");
		}
		html = buffer.toString();
		this.getRequest().setAttribute("html", html);
		this.getRequest().setAttribute("articleList", articleList);
		return "users/article";
	}

	// 阅读公告
	@RequestMapping("read.action")
	public String read(String id) {
		this.front();
		Article article = this.articleService.getArticleById(id);
		article.setHits("" + (Integer.parseInt(article.getHits()) + 1));
		this.articleService.updateArticle(article);
		this.getRequest().setAttribute("article", article);
		return "users/read";
	}

	// 准备登录
	@RequestMapping("preLogin.action")
	public String prelogin() {
		this.front();
		return "users/login";
	}

	// 用户登录
	@RequestMapping("login.action")
	public String login() {
		this.front();
		String username = this.getRequest().getParameter("username");
		String password = this.getRequest().getParameter("password");
		Users u = new Users();
		u.setUsername(username);
		List<Users> usersList = this.usersService.getUsersByCond(u);
		if (usersList.size() == 0) {
			this.getSession().setAttribute("message", "用户名不存在");
			return "redirect:/index/preLogin.action";
		} else {
			Users users = usersList.get(0);
			if (password.equals(users.getPassword())) {
				this.getSession().setAttribute("userid", users.getUsersid());
				this.getSession().setAttribute("username", users.getUsername());
				this.getSession().setAttribute("users", users);
				return "redirect:/index/index.action";
			} else {
				this.getSession().setAttribute("message", "密码错误");
				return "redirect:/index/preLogin.action";
			}
		}
	}

	// 准备注册
	@RequestMapping("preReg.action")
	public String preReg() {
		this.front();
		return "users/register";
	}

	// 用户注册
	@RequestMapping("register.action")
	public String register(Users users) {
		this.front();
		Users u = new Users();
		u.setUsername(users.getUsername());
		List<Users> usersList = this.usersService.getUsersByCond(u);
		if (usersList.size() == 0) {
			users.setRegdate(VeDate.getStringDateShort());
			this.usersService.insertUsers(users);
		} else {
			this.getSession().setAttribute("message", "用户名已存在");
			return "redirect:/index/preReg.action";
		}

		return "redirect:/index/preLogin.action";
	}

	// 退出登录
	@RequestMapping("exit.action")
	public String exit() {
		this.front();
		this.getSession().removeAttribute("userid");
		this.getSession().removeAttribute("username");
		this.getSession().removeAttribute("users");
		return "index";
	}

	// 准备修改密码
	@RequestMapping("prePwd.action")
	public String prePwd() {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		return "users/editpwd";
	}

	// 修改密码
	@RequestMapping("editpwd.action")
	public String editpwd() {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) this.getSession().getAttribute("userid");
		String password = this.getRequest().getParameter("password");
		String repassword = this.getRequest().getParameter("repassword");
		Users users = this.usersService.getUsersById(userid);
		if (password.equals(users.getPassword())) {
			users.setPassword(repassword);
			this.usersService.updateUsers(users);
		} else {
			this.getSession().setAttribute("message", "旧密码错误");
			return "redirect:/index/prePwd.action";
		}
		return "redirect:/index/prePwd.action";
	}

	@RequestMapping("usercenter.action")
	public String usercenter() {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		return "users/usercenter";
	}

	@RequestMapping("userinfo.action")
	public String userinfo() {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) this.getSession().getAttribute("userid");
		this.getSession().setAttribute("users", this.usersService.getUsersById(userid));
		return "users/userinfo";
	}

	@RequestMapping("personal.action")
	public String personal(Users users) {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		this.usersService.updateUsers(users);
		return "redirect:/index/userinfo.action";
	}

	// 留言板
	@RequestMapping("bbs.action")
	public String bbs() {
		this.front();
		List<Bbs> bbsList = this.bbsService.getAllBbs();
		this.getRequest().setAttribute("bbsList", bbsList);
		return "users/bbs";
	}

	// 发布留言
	@RequestMapping("addbbs.action")
	public String addbbs() {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) this.getSession().getAttribute("userid");
		Bbs bbs = new Bbs();
		bbs.setAddtime(VeDate.getStringDate());
		bbs.setContents(getRequest().getParameter("contents"));
		bbs.setHits("0");
		bbs.setRepnum("0");
		bbs.setTitle(getRequest().getParameter("title"));
		bbs.setUsersid(userid);
		this.bbsService.insertBbs(bbs);
		return "redirect:/index/bbs.action";
	}

	// 查看留言
	@RequestMapping("readbbs.action")
	public String readbbs() {
		this.front();
		Bbs bbs = this.bbsService.getBbsById(getRequest().getParameter("id"));
		bbs.setHits("" + (Integer.parseInt(bbs.getHits()) + 1));
		this.bbsService.updateBbs(bbs);
		this.getRequest().setAttribute("bbs", bbs);
		Rebbs rebbs = new Rebbs();
		rebbs.setBbsid(bbs.getBbsid());
		List<Rebbs> rebbsList = this.rebbsService.getRebbsByCond(rebbs);
		this.getRequest().setAttribute("rebbsList", rebbsList);
		return "users/readbbs";
	}

	// 回复留言
	@RequestMapping("rebbs.action")
	public String rebbs() {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) this.getSession().getAttribute("userid");
		Rebbs rebbs = new Rebbs();
		rebbs.setAddtime(VeDate.getStringDate());
		rebbs.setContents(getRequest().getParameter("contents"));
		rebbs.setBbsid(getRequest().getParameter("bbsid"));
		rebbs.setUsersid(userid);
		this.rebbsService.insertRebbs(rebbs);
		Bbs bbs = this.bbsService.getBbsById(rebbs.getBbsid());
		bbs.setRepnum("" + (Integer.parseInt(bbs.getRepnum()) + 1));
		this.bbsService.updateBbs(bbs);
		String path = "redirect:/index/readbbs.action?id=" + bbs.getBbsid();
		return path;
	}

	@RequestMapping("preHouse.action")
	public String preHouse() {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		return "users/addHouse";
	}

	@RequestMapping("addHouse.action")
	public String addHouse(House house) {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) this.getSession().getAttribute("userid");
		house.setAddtime(VeDate.getStringDateShort());
		house.setHits("0");
		house.setUsersid(userid);
		house.setStatus("待租");
		this.houseService.insertHouse(house);
		return "redirect:/index/preHouse.action";
	}

	@RequestMapping("myHouse.action")
	public String myHouse() {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) this.getSession().getAttribute("userid");
		House house = new House();
		house.setUsersid(userid);
		List<House> houseList = this.houseService.getHouseByCond(house);
		this.getRequest().setAttribute("houseList", houseList);
		return "users/myHouse";
	}

	@RequestMapping("deleteHouse.action")
	public String deleteHouse(String id) {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		this.houseService.deleteHouse(id);
		return "redirect:/index/myHouse.action";
	}

	@RequestMapping("getHouseById.action")
	public String getHouseById(String id) {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		House house = this.houseService.getHouseById(id);
		this.getRequest().setAttribute("house", house);
		return "users/editHouse";
	}

	@RequestMapping("updateHouse.action")
	public String updateHouse(House house) {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		this.houseService.updateHouse(house);
		return "redirect:/index/myHouse.action";
	}

	@RequestMapping("preContract.action")
	public String preContract() {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		this.getRequest().setAttribute("cno", "C" + VeDate.getStringId());
		String userid = (String) this.getSession().getAttribute("userid");
		House house = new House();
		house.setUsersid(userid);
		house.setStatus("待租");
		List<House> houseList = this.houseService.getHouseByCond(house);
		this.getRequest().setAttribute("houseList", houseList);
		return "users/addContract";
	}

	@RequestMapping("addContract.action")
	public String addContract(Contract contract) {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) this.getSession().getAttribute("userid");
		contract.setAddtime(VeDate.getStringDateShort());
		contract.setStatus("未完成");
		contract.setUsersid(userid);
		this.contractService.insertContract(contract);
		House house = this.houseService.getHouseById(contract.getHouseid());
		house.setStatus("出租");
		this.houseService.updateHouse(house);
		return "redirect:/index/preContract.action";
	}

	@RequestMapping("myContract.action")
	public String myContract() {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) this.getSession().getAttribute("userid");
		Contract contract = new Contract();
		contract.setUsersid(userid);
		List<Contract> contractList = this.contractService.getContractByCond(contract);
		this.getRequest().setAttribute("contractList", contractList);
		return "users/myContract";
	}

	@RequestMapping("over.action")
	public String over(String id) {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		Contract contract = this.contractService.getContractById(id);
		contract.setStatus("完成");
		this.contractService.updateContract(contract);
		House house = this.houseService.getHouseById(contract.getHouseid());
		house.setStatus("待租");
		this.houseService.updateHouse(house);
		return "redirect:/index/myContract.action";
	}

	@RequestMapping("deleteContract.action")
	public String deleteContract(String id) {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		Contract contract = this.contractService.getContractById(id);
		House house = this.houseService.getHouseById(contract.getHouseid());
		house.setStatus("待租");
		this.houseService.updateHouse(house);
		this.contractService.deleteContract(id);
		return "redirect:/index/myContract.action";
	}

}
