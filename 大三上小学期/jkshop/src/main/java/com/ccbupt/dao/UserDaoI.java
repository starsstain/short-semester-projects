package com.ccbupt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ccbupt.entity.UserEntity;
public interface UserDaoI {
	
	@Insert("insert into t_user (username, phone, email, address) values (#{username},#{phone},#{email},#{address})")
	public int save(UserEntity user);
	
	@Delete("delete from t_user where id=#{id}")
	public int delete(int id);
	
	@Update("update t_user set username=#{username}, phone=#{phone}, email=#{email}, address=#{address} where id=#{id}")
	public int update(UserEntity user);
	
	@Select("select * from t_user")
    public List<UserEntity> getAllList();
	
	@Select("select * from t_user where id=#{id}")
	public UserEntity getById(int id) ;
	
	@Select("select * from t_user where username=#{username}")
	public List<UserEntity> getListByUsername(String username);
	
	@Select("select * from t_user where username=#{username}")
	public UserEntity getByUsername(String username);
}