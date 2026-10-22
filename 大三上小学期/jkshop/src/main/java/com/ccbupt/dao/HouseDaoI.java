package com.ccbupt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ccbupt.entity.HouseEntity;
public interface HouseDaoI {
	// 移除type字段
	@Insert("insert into t_house (housecode, housename, area, floor, roomnum, imgurl, ownerid)  VALUES (#{housecode}, #{housename}, #{area}, #{floor}, #{roomnum}, #{imgurl}, #{ownerid})")
	public int save(HouseEntity hd);
	
	@Delete("delete from t_house where id=#{id}")
	public int delete(int id);
	
	// 移除type字段，修复重复housecode问题
	@Update("update t_house SET housecode = #{housecode}, housename = #{housename}, area = #{area}, floor = #{floor}, roomnum = #{roomnum}, imgurl = #{imgurl}, ownerid = #{ownerid} where id=#{id}")
	public int update(HouseEntity hd);
	
	// 移除关联t_housetype的查询，只查t_house
	@Select("select * from t_house order by id desc")
	public List<HouseEntity>getAllList();
	
	// 移除关联t_housetype的查询，只查t_house
	@Select("select * from t_house where id=#{id}")
	public HouseEntity getById(int id) ;
	
	@Select("select * from t_house  where housename=#{housename}")
	public List<HouseEntity>getListByHousename(String housename);
	
	@Select("select * from t_house  where housecode=#{housecode}")
	public HouseEntity getByCode(String housecode);
}