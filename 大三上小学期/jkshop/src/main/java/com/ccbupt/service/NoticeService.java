package com.ccbupt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccbupt.dao.NoticeDaoI;
import com.ccbupt.entity.NoticeEntity;
import com.ccbupt.entity.R;

@Service
public class NoticeService {
    @Autowired
    NoticeDaoI ndi;

    public R<NoticeEntity> save(NoticeEntity notice) {
        int result = ndi.save(notice);
        if (result > 0) {
            return R.ok(null);
        } else {
            return R.fail("保存公告失败");
        }
    }

    public int delete(int id) {
        return ndi.delete(id);
    }

    public R<NoticeEntity> update(NoticeEntity notice) {
        int result = ndi.update(notice);
        if (result > 0) {
            return R.ok(null);
        } else {
            return R.fail("更新公告失败");
        }
    }

    public List<NoticeEntity> getAllList() {
        List<NoticeEntity> list = ndi.getAllList();
        return list != null ? list : new ArrayList<>();
    }

    public NoticeEntity getById(int id) {
        return ndi.getById(id);
    }

    public List<NoticeEntity> getActiveList() {
        List<NoticeEntity> list = ndi.getActiveList();
        return list != null ? list : new ArrayList<>();
    }
}