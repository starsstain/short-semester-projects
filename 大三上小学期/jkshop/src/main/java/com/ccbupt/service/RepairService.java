package com.ccbupt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccbupt.dao.RepairDaoI;
import com.ccbupt.entity.R;
import com.ccbupt.entity.RepairEntity;

@Service
public class RepairService {

    @Autowired
    RepairDaoI rdi;

    public R<RepairEntity> save(RepairEntity repair) {
        int result = rdi.save(repair);
        if(result > 0) {
            return R.ok(repair);
        } else {
            return R.fail("保存失败");
        }
    }
    
    public int delete(int id) {
        return rdi.delete(id);
    }
    
    public R<RepairEntity> update(RepairEntity repair) {
        int result = rdi.update(repair);
        if(result > 0) {
            return R.ok(repair);
        } else {
            return R.fail("更新失败");
        }
    }
    
    public RepairEntity getById(int id) {
        return rdi.getById(id);
    }
    
    public R<List<RepairEntity>> getList(){
        try {
            List<RepairEntity> repairs = rdi.getAllList();
            return R.ok(repairs);
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail("获取维修列表失败：" + e.getMessage());
        }
    }
    
    public List<RepairEntity> queryByUserId(int userid){
        try {
            return rdi.getListByUserId(userid);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<RepairEntity> queryByHouseId(int houseid){
        try {
            return rdi.getListByHouseId(houseid);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<RepairEntity> queryByStatus(String status){
        try {
            return rdi.getListByStatus(status);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}