package com.ccbupt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccbupt.entity.R;
import com.ccbupt.entity.UserEntity;
import com.ccbupt.service.UserService;

@RestController
@RequestMapping("user") 
public class UserController {
    @Autowired
    UserService us;
    
    @GetMapping //RequestMapping
    public R<List<UserEntity>> getList(){
        return us.getList();
    }
    
    @GetMapping("getById/{id}")
    public UserEntity getById(@PathVariable("id") int id) {
        return us.getById(id);
    }

    @GetMapping("{username}")
    public List<UserEntity> queryList(@PathVariable("username") String username) {
        return us.query(username);
    }

    @PostMapping
    public R<UserEntity> save(@RequestBody UserEntity ue){
        return us.save(ue);
    }

    @PostMapping("AdminLogin")
    public R<UserEntity> AdminLogin(@RequestBody UserEntity ue){
    	return us.AdminLogin(ue);
    }
    
    @PostMapping("Login")
    public R<UserEntity> Login(@RequestBody UserEntity ue){
    	return us.Login(ue);
    }
    
    @DeleteMapping("{id}")
    public R<String> delete(@PathVariable("id") int id) {
        int result = us.delete(id);
        if(result > 0) {
            return R.ok("删除成功");
        } else {
            return R.fail("删除失败");
        }
    }

    @PutMapping
    public R<UserEntity> update(@RequestBody UserEntity ue) {
    	return us.update(ue);
    }
}


