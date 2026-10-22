package com.ccbupt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ccbupt.entity.NoticeEntity;

@Repository
public class NoticeDao implements NoticeDaoI {
    @Autowired
    BaseDao bd;

    /* ====== 增 ====== */
    public int save(NoticeEntity notice) {
        String sql = "insert into t_notice (title, content, create_time, update_time, status) values (?,?,?,?,?)";
        try (Connection conn = bd.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, notice.getTitle());
            stmt.setString(2, notice.getContent());
            stmt.setTimestamp(3, new java.sql.Timestamp(notice.getCreateTime().getTime()));
            stmt.setTimestamp(4, new java.sql.Timestamp(notice.getUpdateTime().getTime()));
            stmt.setInt(5, notice.getStatus());
            int rows = stmt.executeUpdate();
            return rows;
        } catch(Exception e) {e.printStackTrace(); return 0;}
    }

    /* ====== 删 ====== */
    public int delete(int id) {
        String sql = "delete from t_notice where id=?";
        try (Connection conn = bd.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows;
        } catch(Exception e) {e.printStackTrace(); return 0;}
    }

    /* ====== 改 ====== */
    public int update(NoticeEntity notice) {
        String sql = "update t_notice set title=?, content=?, update_time=?, status=? where id=?";
        try (Connection conn = bd.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, notice.getTitle());
            stmt.setString(2, notice.getContent());
            stmt.setTimestamp(3, new java.sql.Timestamp(notice.getUpdateTime().getTime()));
            stmt.setInt(4, notice.getStatus());
            stmt.setInt(5, notice.getId());
            int rows = stmt.executeUpdate();
            return rows;
        } catch(Exception e) {e.printStackTrace(); return 0;}
    }

    /* ====== 全查 ====== */
    public List<NoticeEntity> getAllList() {
        NoticeEntity notice = null;
        ArrayList<NoticeEntity> noticeList = new ArrayList<>();
        String sql = "SELECT * FROM t_notice order by create_time desc";
        try(Connection conn = bd.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rst = pstmt.executeQuery()){
            while(rst.next()){
                notice = new NoticeEntity();
                notice.setId(rst.getInt("id"));
                notice.setTitle(rst.getString("title"));
                notice.setContent(rst.getString("content"));
                notice.setCreateTime(rst.getTimestamp("create_time"));
                notice.setUpdateTime(rst.getTimestamp("update_time"));
                notice.setStatus(rst.getInt("status"));
                noticeList.add(notice);
            }
            return noticeList;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* ====== 单查 ====== */
    public NoticeEntity getById(int id) {
        String sql = "SELECT * FROM t_notice where id=?";
        NoticeEntity notice = null;
        try (Connection conn = bd.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    notice = new NoticeEntity();
                    notice.setId(rs.getInt("id"));
                    notice.setTitle(rs.getString("title"));
                    notice.setContent(rs.getString("content"));
                    notice.setCreateTime(rs.getTimestamp("create_time"));
                    notice.setUpdateTime(rs.getTimestamp("update_time"));
                    notice.setStatus(rs.getInt("status"));
                    return notice;
                }
                return null;
            }
        } catch(Exception e) {e.printStackTrace(); return null;}
    }

    /* ====== 获取激活状态的公告 ====== */
    public List<NoticeEntity> getActiveList() {
        NoticeEntity notice = null;
        ArrayList<NoticeEntity> noticeList = new ArrayList<>();
        String sql = "SELECT * FROM t_notice where status=1 order by create_time desc";
        try(Connection conn = bd.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rst = pstmt.executeQuery()){
            while(rst.next()){
                notice = new NoticeEntity();
                notice.setId(rst.getInt("id"));
                notice.setTitle(rst.getString("title"));
                notice.setContent(rst.getString("content"));
                notice.setCreateTime(rst.getTimestamp("create_time"));
                notice.setUpdateTime(rst.getTimestamp("update_time"));
                notice.setStatus(rst.getInt("status"));
                noticeList.add(notice);
            }
            return noticeList;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}