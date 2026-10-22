package com.ccbupt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ccbupt.entity.HouseEntity;

@Repository
public class HouseDao implements HouseDaoI {
    @Autowired
    BaseDao bd;

    /* ====== 增 ====== */
    public int save(HouseEntity hd) {
        String sql = "insert into t_house (housecode, housename, area, floor, roomnum, imgurl, ownerid) values (?,?,?,?,?,?,?)";
        try (Connection conn = bd.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, hd.getHousecode());
            stmt.setString(2, hd.getHousename());
            stmt.setDouble(3, hd.getArea());
            stmt.setInt(4, hd.getFloor());
            stmt.setString(5, hd.getRoomnum());
            stmt.setString(6, hd.getImgurl());
            stmt.setInt(7, hd.getOwnerid());
            int rows = stmt.executeUpdate();
            return rows;
        } catch(Exception e) {e.printStackTrace(); return 0;}
    }

    /* ====== 删 ====== */
    public int delete(int id) {
        String sql = "delete from t_house where id=?";
        try (Connection conn = bd.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows;
        } catch(Exception e) {e.printStackTrace(); return 0;}
    }

    /* ====== 改 ====== */
    public int update(HouseEntity hd) {
        String sql = "update t_house set housecode=?, housename=?, area=?, floor=?, roomnum=?, imgurl=?, ownerid=? where id=?";
        try (Connection conn = bd.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, hd.getHousecode());
            stmt.setString(2, hd.getHousename());
            stmt.setDouble(3, hd.getArea());
            stmt.setInt(4, hd.getFloor());
            stmt.setString(5, hd.getRoomnum());
            stmt.setString(6, hd.getImgurl());
            stmt.setInt(7, hd.getOwnerid());
            stmt.setInt(8, hd.getId());
            int rows = stmt.executeUpdate();
            return rows;
        } catch(Exception e) {e.printStackTrace(); return 0;}
    }

    /* ====== 全查 ====== */
    public List<HouseEntity> getAllList() {
        HouseEntity hd = null;
        ArrayList<HouseEntity> hdList = new ArrayList<>();
        String sql = "SELECT * FROM t_house order by id desc";
        try(Connection conn = bd.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rst = pstmt.executeQuery()){
            while(rst.next()){
                hd = new HouseEntity();
                hd.setId(rst.getInt("id"));
                hd.setHousecode(rst.getString("housecode"));
                hd.setHousename(rst.getString("housename"));
                hd.setArea(rst.getDouble("area"));
                hd.setFloor(rst.getInt("floor"));
                hd.setRoomnum(rst.getString("roomnum"));
                hd.setImgurl(rst.getString("imgurl"));
                hd.setOwnerid(rst.getInt("ownerid"));
                hdList.add(hd);
            }
            return hdList;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* ====== 单查 ====== */
    public HouseEntity getById(int id) {
        String sql = "SELECT * FROM t_house where id=?";
        HouseEntity hd = null;
        try (Connection conn = bd.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    hd = new HouseEntity();
                    hd.setId(rs.getInt("id"));
                    hd.setHousecode(rs.getString("housecode"));
                    hd.setHousename(rs.getString("housename"));
                    hd.setArea(rs.getDouble("area"));
                    hd.setFloor(rs.getInt("floor"));
                    hd.setRoomnum(rs.getString("roomnum"));
                    hd.setImgurl(rs.getString("imgurl"));
                    hd.setOwnerid(rs.getInt("ownerid"));
                    return hd;
                }
                return null;
            }
        } catch(Exception e) {e.printStackTrace(); return null;}
    }

    /* ====== 按房屋名称查询 ====== */
    public List<HouseEntity> getListByHousename(String housename) {
        HouseEntity hd = null;
        ArrayList<HouseEntity> hdList = new ArrayList<>();
        String sql = "SELECT * FROM t_house where housename=?";
        try(Connection conn = bd.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, housename);
            try(ResultSet rst = pstmt.executeQuery()){
                while(rst.next()){
                    hd = new HouseEntity();
                    hd.setId(rst.getInt("id"));
                    hd.setHousecode(rst.getString("housecode"));
                    hd.setHousename(rst.getString("housename"));
                    hd.setArea(rst.getDouble("area"));
                    hd.setFloor(rst.getInt("floor"));
                    hd.setRoomnum(rst.getString("roomnum"));
                    hd.setImgurl(rst.getString("imgurl"));
                    hd.setOwnerid(rst.getInt("ownerid"));
                    hdList.add(hd);
                }
            }
            return hdList;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* ====== 按房屋编码查询 ====== */
    public HouseEntity getByCode(String housecode) {
        String sql = "SELECT * FROM t_house where housecode=?";
        HouseEntity hd = null;
        try (Connection conn = bd.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, housecode);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    hd = new HouseEntity();
                    hd.setId(rs.getInt("id"));
                    hd.setHousecode(rs.getString("housecode"));
                    hd.setHousename(rs.getString("housename"));
                    hd.setArea(rs.getDouble("area"));
                    hd.setFloor(rs.getInt("floor"));
                    hd.setRoomnum(rs.getString("roomnum"));
                    hd.setImgurl(rs.getString("imgurl"));
                    hd.setOwnerid(rs.getInt("ownerid"));
                    return hd;
                }
                return null;
            }
        } catch(Exception e) {e.printStackTrace(); return null;}
    }
}