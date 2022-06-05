package com.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.ContractDAO;
import com.entity.Contract;
import com.service.ContractService;

@Service("contractService")
public class ContractServiceImpl implements ContractService {
	@Autowired
	@Resource
	private ContractDAO contractDAO;

	@Override // 继承接口的新增 返回值0(失败),1(成功)
	public int insertContract(Contract contract) {
		return this.contractDAO.insertContract(contract);
	}

	@Override // 继承接口的更新 返回值0(失败),1(成功)
	public int updateContract(Contract contract) {
		return this.contractDAO.updateContract(contract);
	}

	@Override // 继承接口的删除 返回值0(失败),1(成功)
	public int deleteContract(String contractid) {
		return this.contractDAO.deleteContract(contractid);
	}

	@Override // 继承接口的查询全部
	public List<Contract> getAllContract() {
		return this.contractDAO.getAllContract();
	}

	@Override // 继承接口的按条件精确查询
	public List<Contract> getContractByCond(Contract contract) {
		return this.contractDAO.getContractByCond(contract);
	}

	@Override // 继承接口的按条件模糊查询
	public List<Contract> getContractByLike(Contract contract) {
		return this.contractDAO.getContractByLike(contract);
	}

	@Override // 继承接口的按主键查询 返回Entity实例
	public Contract getContractById(String contractid) {
		return this.contractDAO.getContractById(contractid);
	}

}

