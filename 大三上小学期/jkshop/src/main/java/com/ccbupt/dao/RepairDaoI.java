// 修复后的RepairDaoI.java
package com.ccbupt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ccbupt.entity.RepairEntity;
public interface RepairDaoI {
    
    @Insert("INSERT INTO t_repair (houseid, userid, description, status, reporter, phone, address, cost, repair_date) VALUES (#{houseid}, #{userid}, #{description}, #{status}, #{reporter}, #{phone}, #{address}, #{cost}, #{repairDate})")
    public int save(RepairEntity repair);
    
    @Delete("DELETE FROM t_repair WHERE id = #{id}")
    public int delete(int id);
    
    @Update("UPDATE t_repair SET houseid = #{houseid}, userid = #{userid}, description = #{description}, status = #{status}, reporter = #{reporter}, phone = #{phone}, address = #{address}, cost = #{cost}, repair_date = #{repairDate}, updated_time = NOW() WHERE id = #{id}")
    public int update(RepairEntity repair);
    
    @Select("SELECT * FROM t_repair ORDER BY created_time DESC")
    public List<RepairEntity> getAllList();
    
    @Select("SELECT * FROM t_repair WHERE id = #{id}")
    public RepairEntity getById(int id);
    
    @Select("SELECT * FROM t_repair WHERE userid = #{userid}")
    public List<RepairEntity> getListByUserId(int userid);
    
    @Select("SELECT * FROM t_repair WHERE houseid = #{houseid}")
    public List<RepairEntity> getListByHouseId(int houseid);
    
    @Select("SELECT * FROM t_repair WHERE status = #{status}")
    public List<RepairEntity> getListByStatus(String status);
}


