package com.ccbupt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ccbupt.entity.RepairEntity;

@Repository
public class RepairDao implements RepairDaoI {
    @Autowired
    BaseDao bd;

    /* ====== 增 ====== */
    public int save(RepairEntity repair) {
        String sql = "INSERT INTO t_repair (houseid, userid, description, status, reporter, phone, address, cost, repair_date) VALUES (?,?,?,?,?,?,?,?,?)";
        try (Connection conn = bd.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, repair.getHouseid());
            stmt.setInt(2, repair.getUserid());
            stmt.setString(3, repair.getDescription());
            stmt.setString(4, repair.getStatus());
            stmt.setString(5, repair.getReporter());
            stmt.setString(6, repair.getPhone());
            stmt.setString(7, repair.getAddress());
            stmt.setBigDecimal(8, repair.getCost());
            stmt.setDate(9, new java.sql.Date(repair.getRepairDate().getTime()));
            int rows = stmt.executeUpdate();
            return rows;
        } catch(Exception e) {e.printStackTrace(); return 0;}
    }

    /* ====== 删 ====== */
    public int delete(int id) {
        String sql = "DELETE FROM t_repair WHERE id = ?";
        try (Connection conn = bd.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows;
        } catch(Exception e) {e.printStackTrace(); return 0;}
    }

    /* ====== 改 ====== */
    public int update(RepairEntity repair) {
        String sql = "UPDATE t_repair SET houseid = ?, userid = ?, description = ?, status = ?, reporter = ?, phone = ?, address = ?, cost = ?, repair_date = ?, updated_time = NOW() WHERE id = ?";
        try (Connection conn = bd.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, repair.getHouseid());
            stmt.setInt(2, repair.getUserid());
            stmt.setString(3, repair.getDescription());
            stmt.setString(4, repair.getStatus());
            stmt.setString(5, repair.getReporter());
            stmt.setString(6, repair.getPhone());
            stmt.setString(7, repair.getAddress());
            stmt.setBigDecimal(8, repair.getCost());
            stmt.setDate(9, new java.sql.Date(repair.getRepairDate().getTime()));
            stmt.setInt(10, repair.getId());
            int rows = stmt.executeUpdate();
            return rows;
        } catch(Exception e) {e.printStackTrace(); return 0;}
    }

    /* ====== 全查 ====== */
    public List<RepairEntity> getAllList() {
        RepairEntity repair = null;
        ArrayList<RepairEntity> repairList = new ArrayList<>();
        String sql = "SELECT * FROM t_repair ORDER BY created_time DESC";
        try(Connection conn = bd.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rst = pstmt.executeQuery()){
            while(rst.next()){
                repair = new RepairEntity();
                repair.setId(rst.getInt("id"));
                repair.setHouseid(rst.getInt("houseid"));
                repair.setUserid(rst.getInt("userid"));
                repair.setDescription(rst.getString("description"));
                repair.setStatus(rst.getString("status"));
                repair.setReporter(rst.getString("reporter"));
                repair.setPhone(rst.getString("phone"));
                repair.setAddress(rst.getString("address"));
                repair.setCost(rst.getBigDecimal("cost"));
                repair.setRepairDate(rst.getDate("repair_date"));
                repair.setCreatedTime(rst.getTimestamp("created_time"));
                repair.setUpdatedTime(rst.getTimestamp("updated_time"));
                repairList.add(repair);
            }
            return repairList;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* ====== 单查 ====== */
    public RepairEntity getById(int id) {
        String sql = "SELECT * FROM t_repair WHERE id = ?";
        RepairEntity repair = null;
        try (Connection conn = bd.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    repair = new RepairEntity();
                    repair.setId(rs.getInt("id"));
                    repair.setHouseid(rs.getInt("houseid"));
                    repair.setUserid(rs.getInt("userid"));
                    repair.setDescription(rs.getString("description"));
                    repair.setStatus(rs.getString("status"));
                    repair.setReporter(rs.getString("reporter"));
                    repair.setPhone(rs.getString("phone"));
                    repair.setAddress(rs.getString("address"));
                    repair.setCost(rs.getBigDecimal("cost"));
                    repair.setRepairDate(rs.getDate("repair_date"));
                    repair.setCreatedTime(rs.getTimestamp("created_time"));
                    repair.setUpdatedTime(rs.getTimestamp("updated_time"));
                    return repair;
                }
                return null;
            }
        } catch(Exception e) {e.printStackTrace(); return null;}
    }

    /* ====== 按用户ID查询 ====== */
    public List<RepairEntity> getListByUserId(int userid) {
        RepairEntity repair = null;
        ArrayList<RepairEntity> repairList = new ArrayList<>();
        String sql = "SELECT * FROM t_repair WHERE userid = ?";
        try(Connection conn = bd.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, userid);
            try(ResultSet rst = pstmt.executeQuery()){
                while(rst.next()){
                    repair = new RepairEntity();
                    repair.setId(rst.getInt("id"));
                    repair.setHouseid(rst.getInt("houseid"));
                    repair.setUserid(rst.getInt("userid"));
                    repair.setDescription(rst.getString("description"));
                    repair.setStatus(rst.getString("status"));
                    repair.setReporter(rst.getString("reporter"));
                    repair.setPhone(rst.getString("phone"));
                    repair.setAddress(rst.getString("address"));
                    repair.setCost(rst.getBigDecimal("cost"));
                    repair.setRepairDate(rst.getDate("repair_date"));
                    repair.setCreatedTime(rst.getTimestamp("created_time"));
                    repair.setUpdatedTime(rst.getTimestamp("updated_time"));
                    repairList.add(repair);
                }
            }
            return repairList;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* ====== 按房屋ID查询 ====== */
    public List<RepairEntity> getListByHouseId(int houseid) {
        RepairEntity repair = null;
        ArrayList<RepairEntity> repairList = new ArrayList<>();
        String sql = "SELECT * FROM t_repair WHERE houseid = ?";
        try(Connection conn = bd.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, houseid);
            try(ResultSet rst = pstmt.executeQuery()){
                while(rst.next()){
                    repair = new RepairEntity();
                    repair.setId(rst.getInt("id"));
                    repair.setHouseid(rst.getInt("houseid"));
                    repair.setUserid(rst.getInt("userid"));
                    repair.setDescription(rst.getString("description"));
                    repair.setStatus(rst.getString("status"));
                    repair.setReporter(rst.getString("reporter"));
                    repair.setPhone(rst.getString("phone"));
                    repair.setAddress(rst.getString("address"));
                    repair.setCost(rst.getBigDecimal("cost"));
                    repair.setRepairDate(rst.getDate("repair_date"));
                    repair.setCreatedTime(rst.getTimestamp("created_time"));
                    repair.setUpdatedTime(rst.getTimestamp("updated_time"));
                    repairList.add(repair);
                }
            }
            return repairList;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* ====== 按状态查询 ====== */
    public List<RepairEntity> getListByStatus(String status) {
        RepairEntity repair = null;
        ArrayList<RepairEntity> repairList = new ArrayList<>();
        String sql = "SELECT * FROM t_repair WHERE status = ?";
        try(Connection conn = bd.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, status);
            try(ResultSet rst = pstmt.executeQuery()){
                while(rst.next()){
                    repair = new RepairEntity();
                    repair.setId(rst.getInt("id"));
                    repair.setHouseid(rst.getInt("houseid"));
                    repair.setUserid(rst.getInt("userid"));
                    repair.setDescription(rst.getString("description"));
                    repair.setStatus(rst.getString("status"));
                    repair.setReporter(rst.getString("reporter"));
                    repair.setPhone(rst.getString("phone"));
                    repair.setAddress(rst.getString("address"));
                    repair.setCost(rst.getBigDecimal("cost"));
                    repair.setRepairDate(rst.getDate("repair_date"));
                    repair.setCreatedTime(rst.getTimestamp("created_time"));
                    repair.setUpdatedTime(rst.getTimestamp("updated_time"));
                    repairList.add(repair);
                }
            }
            return repairList;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}