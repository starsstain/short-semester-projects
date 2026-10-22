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
import com.ccbupt.entity.RepairEntity;
import com.ccbupt.service.RepairService;


@RestController
@RequestMapping("repair") 
public class RepairController {
    @Autowired
    RepairService rs;
    
    @GetMapping 
    public R<List<RepairEntity>> getList(){
        return rs.getList();
    }
    
    @GetMapping("getById/{id}")
    public RepairEntity getById(@PathVariable("id") int id) {
        return rs.getById(id);
    }

    @GetMapping("getByUserId/{userid}")
    public List<RepairEntity> getByUserId(@PathVariable("userid") int userid) {
        return rs.queryByUserId(userid);
    }

    @GetMapping("getByHouseId/{houseid}")
    public List<RepairEntity> getByHouseId(@PathVariable("houseid") int houseid) {
        return rs.queryByHouseId(houseid);
    }

    @GetMapping("getByStatus/{status}")
    public List<RepairEntity> getByStatus(@PathVariable("status") String status) {
        return rs.queryByStatus(status);
    }

    @PostMapping
    public R<RepairEntity> save(@RequestBody RepairEntity re){
        return rs.save(re);
    }

    @DeleteMapping("{id}")
    public R<String> delete(@PathVariable("id") int id) {
        int result = rs.delete(id);
        if(result > 0) {
            return R.ok("删除成功");
        } else {
            return R.fail("删除失败");
        }
    }

    @PutMapping
    public R<RepairEntity> update(@RequestBody RepairEntity re) {
        return rs.update(re);
    }
}
