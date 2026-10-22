package com.ccbupt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccbupt.dao.PaymentDaoI;
import com.ccbupt.entity.R;
import com.ccbupt.entity.PaymentEntity;

@Service
public class PaymentService {

    @Autowired
    PaymentDaoI pdi;

    public R<PaymentEntity> save(PaymentEntity payment) {
        int result = pdi.save(payment);
        if(result > 0) {
            return R.ok(payment);
        } else {
            return R.fail("保存失败");
        }
    }
    
    public int delete(int id) {
        return pdi.delete(id);
    }
    
    public R<PaymentEntity> update(PaymentEntity payment) {
        int result = pdi.update(payment);
        if(result > 0) {
            return R.ok(payment);
        } else {
            return R.fail("更新失败");
        }
    }
    
    public PaymentEntity getById(int id) {
        return pdi.getById(id);
    }
    
    public R<List<PaymentEntity>> getList(){
        try {
            List<PaymentEntity> payments = pdi.getAllList();
            return R.ok(payments);
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail("获取缴费列表失败：" + e.getMessage());
        }
    }
    
    public List<PaymentEntity> queryByUserId(int userid){
        try {
            return pdi.getListByUserId(userid);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<PaymentEntity> queryByPayType(String paytype){
        try {
            return pdi.getListByPayType(paytype);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<PaymentEntity> queryByStatus(String status){
        try {
            return pdi.getListByStatus(status);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


