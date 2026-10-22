package com.ccbupt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ccbupt.entity.PaymentEntity;

@Repository
public class PaymentDao implements PaymentDaoI {
    @Autowired
    BaseDao bd;

    /* ====== 增 ====== */
    public int save(PaymentEntity payment) {
        String sql = "insert into t_payment (userid, houseid, paytype, amount, paydate, status, remarks) values (?,?,?,?,?,?,?)";
        try (Connection conn = bd.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, payment.getUserid());
            stmt.setInt(2, payment.getHouseid());
            stmt.setString(3, payment.getPaytype());
            stmt.setDouble(4, payment.getAmount());
            stmt.setString(5, payment.getPaydate());
            stmt.setString(6, payment.getStatus());
            stmt.setString(7, payment.getRemarks());
            int rows = stmt.executeUpdate();
            return rows;
        } catch(Exception e) {e.printStackTrace(); return 0;}
    }

    /* ====== 删 ====== */
    public int delete(int id) {
        String sql = "delete from t_payment where id=?";
        try (Connection conn = bd.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows;
        } catch(Exception e) {e.printStackTrace(); return 0;}
    }

    /* ====== 改 ====== */
    public int update(PaymentEntity payment) {
        String sql = "update t_payment set userid=?, houseid=?, paytype=?, amount=?, paydate=?, status=?, remarks=? where id=?";
        try (Connection conn = bd.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, payment.getUserid());
            stmt.setInt(2, payment.getHouseid());
            stmt.setString(3, payment.getPaytype());
            stmt.setDouble(4, payment.getAmount());
            stmt.setString(5, payment.getPaydate());
            stmt.setString(6, payment.getStatus());
            stmt.setString(7, payment.getRemarks());
            stmt.setInt(8, payment.getId());
            int rows = stmt.executeUpdate();
            return rows;
        } catch(Exception e) {e.printStackTrace(); return 0;}
    }

    /* ====== 全查 ====== */
    public List<PaymentEntity> getAllList() {
        PaymentEntity payment = null;
        ArrayList<PaymentEntity> paymentList = new ArrayList<>();
        String sql = "SELECT * FROM t_payment";
        try(Connection conn = bd.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rst = pstmt.executeQuery()){
            while(rst.next()){
                payment = new PaymentEntity();
                payment.setId(rst.getInt("id"));
                payment.setUserid(rst.getInt("userid"));
                payment.setHouseid(rst.getInt("houseid"));
                payment.setPaytype(rst.getString("paytype"));
                payment.setAmount(rst.getDouble("amount"));
                payment.setPaydate(rst.getString("paydate"));
                payment.setStatus(rst.getString("status"));
                payment.setRemarks(rst.getString("remarks"));
                paymentList.add(payment);
            }
            return paymentList;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* ====== 单查 ====== */
    public PaymentEntity getById(int id) {
        String sql = "SELECT * FROM t_payment where id=?";
        PaymentEntity payment = null;
        try (Connection conn = bd.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    payment = new PaymentEntity();
                    payment.setId(rs.getInt("id"));
                    payment.setUserid(rs.getInt("userid"));
                    payment.setHouseid(rs.getInt("houseid"));
                    payment.setPaytype(rs.getString("paytype"));
                    payment.setAmount(rs.getDouble("amount"));
                    payment.setPaydate(rs.getString("paydate"));
                    payment.setStatus(rs.getString("status"));
                    payment.setRemarks(rs.getString("remarks"));
                    return payment;
                }
                return null;
            }
        } catch(Exception e) {e.printStackTrace(); return null;}
    }

    /* ====== 按用户ID查询 ====== */
    public List<PaymentEntity> getListByUserId(int userid) {
        PaymentEntity payment = null;
        ArrayList<PaymentEntity> paymentList = new ArrayList<>();
        String sql = "SELECT * FROM t_payment where userid=?";
        try(Connection conn = bd.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, userid);
            try(ResultSet rst = pstmt.executeQuery()){
                while(rst.next()){
                    payment = new PaymentEntity();
                    payment.setId(rst.getInt("id"));
                    payment.setUserid(rst.getInt("userid"));
                    payment.setHouseid(rst.getInt("houseid"));
                    payment.setPaytype(rst.getString("paytype"));
                    payment.setAmount(rst.getDouble("amount"));
                    payment.setPaydate(rst.getString("paydate"));
                    payment.setStatus(rst.getString("status"));
                    payment.setRemarks(rst.getString("remarks"));
                    paymentList.add(payment);
                }
            }
            return paymentList;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* ====== 按缴费类型查询 ====== */
    public List<PaymentEntity> getListByPayType(String paytype) {
        PaymentEntity payment = null;
        ArrayList<PaymentEntity> paymentList = new ArrayList<>();
        String sql = "SELECT * FROM t_payment where paytype=?";
        try(Connection conn = bd.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, paytype);
            try(ResultSet rst = pstmt.executeQuery()){
                while(rst.next()){
                    payment = new PaymentEntity();
                    payment.setId(rst.getInt("id"));
                    payment.setUserid(rst.getInt("userid"));
                    payment.setHouseid(rst.getInt("houseid"));
                    payment.setPaytype(rst.getString("paytype"));
                    payment.setAmount(rst.getDouble("amount"));
                    payment.setPaydate(rst.getString("paydate"));
                    payment.setStatus(rst.getString("status"));
                    payment.setRemarks(rst.getString("remarks"));
                    paymentList.add(payment);
                }
            }
            return paymentList;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* ====== 按状态查询 ====== */
    public List<PaymentEntity> getListByStatus(String status) {
        PaymentEntity payment = null;
        ArrayList<PaymentEntity> paymentList = new ArrayList<>();
        String sql = "SELECT * FROM t_payment where status=?";
        try(Connection conn = bd.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, status);
            try(ResultSet rst = pstmt.executeQuery()){
                while(rst.next()){
                    payment = new PaymentEntity();
                    payment.setId(rst.getInt("id"));
                    payment.setUserid(rst.getInt("userid"));
                    payment.setHouseid(rst.getInt("houseid"));
                    payment.setPaytype(rst.getString("paytype"));
                    payment.setAmount(rst.getDouble("amount"));
                    payment.setPaydate(rst.getString("paydate"));
                    payment.setStatus(rst.getString("status"));
                    payment.setRemarks(rst.getString("remarks"));
                    paymentList.add(payment);
                }
            }
            return paymentList;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}