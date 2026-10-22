package com.ccbupt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ccbupt.entity.UserEntity;

@Repository
public class UserDao implements UserDaoI {
    @Autowired
    BaseDao bd;

    /* ====== 增 ====== */
    public int save(UserEntity user) {
        String sql = "insert into t_user (username, password, phone, email, address) values (?,?,?,?,?)";
        try (Connection conn = bd.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getPhone());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getAddress()); // 设置地址
            int rows = stmt.executeUpdate();
            return rows;
        } catch(Exception e) {return 0;}
    }

    /* ====== 改 ====== */
    public int update(UserEntity user) {
        String sql = "update t_user set username=?, password=?, phone=?, email=?, address=? where id=?";
        try (Connection conn = bd.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getPhone());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getAddress()); // 设置新地址
            stmt.setInt(6, user.getId());
            int rows = stmt.executeUpdate();
            return rows;
        } catch(Exception e) {return 0;}
    }

    /* ====== 删 ====== */
    public int delete(int id) {
        String sql = "delete from t_user where id=?";
        try (Connection conn = bd.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows;
        } catch(Exception e) {return 0;}
    }

    /* ====== 单查 ====== */
    public UserEntity getById(int id) {
        String sql = "SELECT * FROM t_user where id=?";
        UserEntity user = null;
        try (Connection conn = bd.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    user = new UserEntity();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setPhone(rs.getString("phone"));
                    user.setEmail(rs.getString("email"));
                    user.setAddress(rs.getString("address")); // 获取地址
                    return user;
                }
                return null;
            }
        } catch(Exception e) {return null;}
    }

    /* ====== 全查 ====== */
    public List<UserEntity> getAllList() {
        UserEntity user = null;
        ArrayList<UserEntity> userList = new ArrayList<>();
        String sql = "SELECT * FROM t_user";
        try(
                Connection conn = bd.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rst = pstmt.executeQuery()){
            while(rst.next()){
                user = new UserEntity();
                user.setId(rst.getInt("id"));
                user.setUsername(rst.getString("username"));
                user.setPassword(rst.getString("password"));
                user.setPhone(rst.getString("phone"));
                user.setEmail(rst.getString("email"));
                user.setAddress(rst.getString("address")); // 获取地址
                userList.add(user);
            }
            return userList;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* ====== 按用户名查询 ====== */
    public List<UserEntity> getListByUsername(String username) {
        UserEntity user = null;
        ArrayList<UserEntity> userList = new ArrayList<>();
        String sql = "SELECT * FROM t_user where username=?";
        try(
                Connection conn = bd.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, username);
            try(ResultSet rst = pstmt.executeQuery()){
                while(rst.next()){
                    user = new UserEntity();
                    user.setId(rst.getInt("id"));
                    user.setUsername(rst.getString("username"));
                    user.setPassword(rst.getString("password"));
                    user.setPhone(rst.getString("phone"));
                    user.setEmail(rst.getString("email"));
                    user.setAddress(rst.getString("address")); // 获取地址
                    userList.add(user);
                }
            }
            return userList;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* ====== 按邮箱查询 ====== */
    public UserEntity getByEmail(String email) {
        String sql = "SELECT * FROM t_user where email=?";
        UserEntity user = null;
        try (Connection conn = bd.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    user = new UserEntity();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setPhone(rs.getString("phone"));
                    user.setEmail(rs.getString("email"));
                    user.setAddress(rs.getString("address")); // 获取地址
                    return user;
                }
                return null;
            }
        } catch(Exception e) {return null;}
    }

    /* ====== 按手机号查询 ====== */
    public UserEntity getByPhone(String phone) {
        String sql = "SELECT * FROM t_user where phone=?";
        UserEntity user = null;
        try (Connection conn = bd.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, phone);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    user = new UserEntity();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setPhone(rs.getString("phone"));
                    user.setEmail(rs.getString("email"));
                    user.setAddress(rs.getString("address")); // 获取地址
                    return user;
                }
                return null;
            }
        } catch(Exception e) {return null;}
    }
    
    /* ====== 按用户名查询单条记录 ====== */
    public UserEntity getByUsername(String username) {
        String sql = "SELECT * FROM t_user where username=?";
        UserEntity user = null;
        try (Connection conn = bd.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    user = new UserEntity();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setPhone(rs.getString("phone"));
                    user.setEmail(rs.getString("email"));
                    user.setAddress(rs.getString("address")); // 获取地址
                    return user;
                }
                return null;
            }
        } catch(Exception e) {return null;}
    }
}