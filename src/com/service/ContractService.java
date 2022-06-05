package com.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.entity.Contract;

@Service("contractService")
public interface ContractService {
	// 插入数据 调用contractDAO里的insertContract配置
	public int insertContract(Contract contract);

	// 更新数据 调用contractDAO里的updateContract配置
	public int updateContract(Contract contract);

	// 删除数据 调用contractDAO里的deleteContract配置
	public int deleteContract(String contractid);

	// 查询全部数据 调用contractDAO里的getAllContract配置
	public List<Contract> getAllContract();

	// 按照Contract类里面的字段名称精确查询 调用contractDAO里的getContractByCond配置
	public List<Contract> getContractByCond(Contract contract);

	// 按照Contract类里面的字段名称模糊查询 调用contractDAO里的getContractByLike配置
	public List<Contract> getContractByLike(Contract contract);

	// 按主键查询表返回单一的Contract实例 调用contractDAO里的getContractById配置
	public Contract getContractById(String contractid);

}

