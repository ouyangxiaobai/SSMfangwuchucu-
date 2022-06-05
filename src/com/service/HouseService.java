package com.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.entity.House;

@Service("houseService")
public interface HouseService {
	// 插入数据 调用houseDAO里的insertHouse配置
	public int insertHouse(House house);

	// 更新数据 调用houseDAO里的updateHouse配置
	public int updateHouse(House house);

	// 删除数据 调用houseDAO里的deleteHouse配置
	public int deleteHouse(String houseid);

	// 查询全部数据 调用houseDAO里的getAllHouse配置
	public List<House> getAllHouse();

	public List<House> getHotHouse();

	public List<House> getNewsHouse();

	public List<House> getFrontHouse(String cateid);

	// 按照House类里面的字段名称精确查询 调用houseDAO里的getHouseByCond配置
	public List<House> getHouseByCond(House house);

	// 按照House类里面的字段名称模糊查询 调用houseDAO里的getHouseByLike配置
	public List<House> getHouseByLike(House house);

	// 按主键查询表返回单一的House实例 调用houseDAO里的getHouseById配置
	public House getHouseById(String houseid);

}
