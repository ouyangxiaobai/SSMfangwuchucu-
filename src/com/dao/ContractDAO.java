package com.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.entity.Contract;

@Repository("contractDAO") // Repository标签定义数据库连接的访问 Spring中直接
public interface ContractDAO {

	/**
	 * ContractDAO 接口 可以按名称直接调用contract.xml配置文件的SQL语句
	 */

	// 插入数据 调用entity包contract.xml里的insertContract配置 返回值0(失败),1(成功)
	public int insertContract(Contract contract);

	// 更新数据 调用entity包contract.xml里的updateContract配置 返回值0(失败),1(成功)
	public int updateContract(Contract contract);

	// 删除数据 调用entity包contract.xml里的deleteContract配置 返回值0(失败),1(成功)
	public int deleteContract(String contractid);

	// 查询全部数据 调用entity包contract.xml里的getAllContract配置 返回List类型的数据
	public List<Contract> getAllContract();

	// 按照Contract类里面的值精确查询 调用entity包contract.xml里的getContractByCond配置
	// 返回List类型的数据
	public List<Contract> getContractByCond(Contract contract);

	// 按照Contract类里面的值模糊查询 调用entity包contract.xml里的getContractByLike配置
	// 返回List类型的数据
	public List<Contract> getContractByLike(Contract contract);

	// 按主键查询表返回单一的Contract实例 调用entity包contract.xml里的getContractById配置
	public Contract getContractById(String contractid);

}

