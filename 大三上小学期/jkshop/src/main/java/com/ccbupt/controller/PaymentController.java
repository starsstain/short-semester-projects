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
import com.ccbupt.entity.PaymentEntity;
import com.ccbupt.service.PaymentService;

@RestController
@RequestMapping("payment") 
public class PaymentController {
    @Autowired
    PaymentService ps;
    
    @GetMapping 
    public R<List<PaymentEntity>> getList(){
        return ps.getList();
    }
    
    @GetMapping("getById/{id}")
    public PaymentEntity getById(@PathVariable("id") int id) {
        return ps.getById(id);
    }

    @GetMapping("getByUserId/{userid}")
    public List<PaymentEntity> getByUserId(@PathVariable("userid") int userid) {
        return ps.queryByUserId(userid);
    }

    @GetMapping("getByPayType/{paytype}")
    public List<PaymentEntity> getByPayType(@PathVariable("paytype") String paytype) {
        return ps.queryByPayType(paytype);
    }

    @GetMapping("getByStatus/{status}")
    public List<PaymentEntity> getByStatus(@PathVariable("status") String status) {
        return ps.queryByStatus(status);
    }

    @PostMapping
    public R<PaymentEntity> save(@RequestBody PaymentEntity pe){
        return ps.save(pe);
    }

    @DeleteMapping("{id}")
    public R<String> delete(@PathVariable("id") int id) {
        int result = ps.delete(id);
        if(result > 0) {
            return R.ok("删除成功");
        } else {
            return R.fail("删除失败");
        }
    }

    @PutMapping
    public R<PaymentEntity> update(@RequestBody PaymentEntity pe) {
        return ps.update(pe);
    }
}


