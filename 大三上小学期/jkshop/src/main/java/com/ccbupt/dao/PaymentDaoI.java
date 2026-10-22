package com.ccbupt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ccbupt.entity.PaymentEntity;
public interface PaymentDaoI {
    
    @Insert("insert into t_payment (userid, houseid, paytype, amount, paydate, status, remarks) values (#{userid},#{houseid},#{paytype},#{amount},#{paydate},#{status},#{remarks})")
    public int save(PaymentEntity payment);
    
    @Delete("delete from t_payment where id=#{id}")
    public int delete(int id);
    
    @Update("update t_payment set userid=#{userid}, houseid=#{houseid}, paytype=#{paytype}, amount=#{amount}, paydate=#{paydate}, status=#{status}, remarks=#{remarks} where id=#{id}")
    public int update(PaymentEntity payment);
    
    @Select("select * from t_payment")
    public List<PaymentEntity> getAllList();
    
    @Select("select * from t_payment where id=#{id}")
    public PaymentEntity getById(int id) ;
    
    @Select("select * from t_payment where userid=#{userid}")
    public List<PaymentEntity> getListByUserId(int userid);
    
    @Select("select * from t_payment where paytype=#{paytype}")
    public List<PaymentEntity> getListByPayType(String paytype);
    
    @Select("select * from t_payment where status=#{status}")
    public List<PaymentEntity> getListByStatus(String status);
}


