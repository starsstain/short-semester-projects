package com.ccbupt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccbupt.entity.NoticeEntity;
import com.ccbupt.entity.R;
import com.ccbupt.service.NoticeService;

@RestController
@RequestMapping("/notice")
@CrossOrigin("*")
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    // 获取所有公告
    @GetMapping
    public R<List<NoticeEntity>> getAllList() {
        List<NoticeEntity> list = noticeService.getAllList();
        return R.ok(list);
    }

    // 获取激活状态的公告（用于首页展示）
    @GetMapping("/active")
    public R<List<NoticeEntity>> getActiveList() {
        List<NoticeEntity> list = noticeService.getActiveList();
        return R.ok(list);
    }

    // 根据ID获取公告
    @GetMapping("/{id}")
    public R<NoticeEntity> getById(@PathVariable("id") int id) {
        NoticeEntity notice = noticeService.getById(id);
        return R.ok(notice);
    }

    // 创建新公告
    @PostMapping
    public R<NoticeEntity> save(@RequestBody NoticeEntity notice) {
        return noticeService.save(notice);
    }

    // 更新公告
    @PutMapping
    public R<NoticeEntity> update(@RequestBody NoticeEntity notice) {
        return noticeService.update(notice);
    }

    // 删除公告
    @DeleteMapping("/{id}")
    public R<String> delete(@PathVariable("id") int id) {
        int result = noticeService.delete(id);
        if (result > 0) {
            return R.ok("删除成功");
        } else {
            return R.fail("删除失败");
        }
    }
}