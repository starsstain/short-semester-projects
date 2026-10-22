package com.ccbupt.dao;

import java.util.List;

import com.ccbupt.entity.NoticeEntity;

public interface NoticeDaoI {
    public int save(NoticeEntity notice);
    public int delete(int id);
    public int update(NoticeEntity notice);
    public List<NoticeEntity> getAllList();
    public NoticeEntity getById(int id);
    public List<NoticeEntity> getActiveList();
}