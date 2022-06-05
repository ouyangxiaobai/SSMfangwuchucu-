package com.action;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.entity.Contract;
import com.service.ContractService;
import com.entity.Users;
import com.entity.House;
import com.service.UsersService;
import com.service.HouseService;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/contract")
public class ContractAction extends BaseAction {
	// 注入Service 由于标签的存在 所以不需要getter setter
	@Autowired
	@Resource
	private ContractService contractService;
	@Autowired
	@Resource
	private UsersService usersService;
	@Autowired
	@Resource
	private HouseService houseService;

	// 准备添加数据
	@RequestMapping("createContract.action")
	public String createContract() {
		List<Users> usersList = this.usersService.getAllUsers();
		this.getRequest().setAttribute("usersList", usersList);
		List<House> houseList = this.houseService.getAllHouse();
		this.getRequest().setAttribute("houseList", houseList);
		return "admin/addcontract";
	}

	// 添加数据
	@RequestMapping("addContract.action")
	public String addContract(Contract contract) {
		this.contractService.insertContract(contract);
		return "redirect:/contract/createContract.action";
	}

	// 通过主键删除数据
	@RequestMapping("deleteContract.action")
	public String deleteContract(String id) {
		this.contractService.deleteContract(id);
		return "redirect:/contract/getAllContract.action";
	}

	// 批量删除数据
	@RequestMapping("deleteContractByIds.action")
	public String deleteContractByIds() {
		String[] ids = this.getRequest().getParameterValues("contractid");
		for (String contractid : ids) {
			this.contractService.deleteContract(contractid);
		}
		return "redirect:/contract/getAllContract.action";
	}

	// 更新数据
	@RequestMapping("updateContract.action")
	public String updateContract(Contract contract) {
		this.contractService.updateContract(contract);
		return "redirect:/contract/getAllContract.action";
	}

	// 显示全部数据
	@RequestMapping("getAllContract.action")
	public String getAllContract(String number) {
		List<Contract> contractList = new ArrayList<Contract>();
		List<Contract> tempList = new ArrayList<Contract>();
		tempList = this.contractService.getAllContract();
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
			Contract contract = tempList.get(i);
			contractList.add(contract);
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
			buffer.append("<a href=\"contract/getAllContract.action?number=0\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"contract/getAllContract.action?number=" + (Integer.parseInt(number) - 1) + "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"contract/getAllContract.action?number=" + (Integer.parseInt(number) + 1) + "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"contract/getAllContract.action?number=" + (maxPage - 1) + "\">尾页</a>");
		}
		html = buffer.toString();
		this.getRequest().setAttribute("html", html);
		this.getRequest().setAttribute("contractList", contractList);
		return "admin/listcontract";
	}

	// 按条件查询数据 (模糊查询)
	@RequestMapping("queryContractByCond.action")
	public String queryContractByCond(String cond, String name) {
		List<Contract> contractList = new ArrayList<Contract>();
		Contract contract = new Contract();
		if (cond != null) {
			if ("cno".equals(cond)) {
				contract.setCno(name);
				contractList = this.contractService.getContractByLike(contract);
			}
			if ("usersid".equals(cond)) {
				contract.setUsersid(name);
				contractList = this.contractService.getContractByLike(contract);
			}
			if ("houseid".equals(cond)) {
				contract.setHouseid(name);
				contractList = this.contractService.getContractByLike(contract);
			}
			if ("addtime".equals(cond)) {
				contract.setAddtime(name);
				contractList = this.contractService.getContractByLike(contract);
			}
			if ("thestart".equals(cond)) {
				contract.setThestart(name);
				contractList = this.contractService.getContractByLike(contract);
			}
			if ("theend".equals(cond)) {
				contract.setTheend(name);
				contractList = this.contractService.getContractByLike(contract);
			}
			if ("files".equals(cond)) {
				contract.setFiles(name);
				contractList = this.contractService.getContractByLike(contract);
			}
			if ("status".equals(cond)) {
				contract.setStatus(name);
				contractList = this.contractService.getContractByLike(contract);
			}
		}
		this.getRequest().setAttribute("contractList", contractList);
		return "admin/querycontract";
	}

	// 按主键查询数据
	@RequestMapping("getContractById.action")
	public String getContractById(String id) {
		Contract contract = this.contractService.getContractById(id);
		this.getRequest().setAttribute("contract", contract);
		List<Users> usersList = this.usersService.getAllUsers();
		this.getRequest().setAttribute("usersList", usersList);
		List<House> houseList = this.houseService.getAllHouse();
		this.getRequest().setAttribute("houseList", houseList);
		return "admin/editcontract";
	}

	public ContractService getContractService() {
		return contractService;
	}

	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}

}
