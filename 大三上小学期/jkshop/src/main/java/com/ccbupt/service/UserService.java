package com.ccbupt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccbupt.dao.UserDaoI;
import com.ccbupt.entity.R;
import com.ccbupt.entity.UserEntity;
import com.ccbupt.util.MD5Util;

@Service
public class UserService {

    @Autowired
    UserDaoI udi;

    public R<UserEntity> save(UserEntity user) {
        UserEntity dbue = udi.getByUsername(user.getUsername());
        if(null == dbue) {
            int result = udi.save(user);
            if(result > 0) {
                return R.ok(user);
            } else {
                return R.fail("保存失败");
            }
        } else {
            return R.fail("该用户名已存在！");
        }
    }
    
    public int delete(int id) {
        return udi.delete(id);
    }
    
    public R<UserEntity> update(UserEntity user) {
        int result = udi.update(user);
        if(result > 0) {
            return R.ok(user);
        } else {
            return R.fail("更新失败");
        }
    }
    
    public UserEntity getById(int id) {
        return udi.getById(id);
    }
    
    public R<List<UserEntity>> getList(){
        try {
            List<UserEntity> users = udi.getAllList();
            return R.ok(users);
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail("获取用户列表失败：" + e.getMessage());
        }
    }
    
    public List<UserEntity> query(String username){
        try {
            return udi.getListByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public R<UserEntity> AdminLogin(UserEntity ue){
        if(!"admin".equals(ue.getUsername())) {
            return R.fail("用户名错误");
        }			
        UserEntity dbue = udi.getByUsername(ue.getUsername());
        if(null == dbue) {
            return R.fail("查无此人");
        } else if(dbue.getPassword().equals(MD5Util.toMD5(ue.getPassword()))){
            dbue.setPassword(null);
            return R.ok(dbue);
        } else {
            return R.fail("密码错误");
        }
    } 
    
    public R<UserEntity> Login(UserEntity ue){
        UserEntity dbue = udi.getByUsername(ue.getUsername());
        if(null == dbue) {
            return R.fail("查无此人");
        } else if(dbue.getPassword().equals(MD5Util.toMD5(ue.getPassword()))){
            dbue.setPassword(null);
            return R.ok(dbue);
        } else {
            return R.fail("密码错误");
        }
    }
}


