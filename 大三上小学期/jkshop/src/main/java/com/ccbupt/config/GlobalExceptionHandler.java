package com.ccbupt.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ccbupt.entity.R;

@RestControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /* 1. 主动抛的业务异常（controller/service 里 throw new BizException） */
    @ExceptionHandler(BizException.class)
    public R<Void> biz(BizException e) {
        log.warn("BizException: {}", e.getMessage());
        return R.fail(e.getCode(), e.getMessage());
    }
    //if (stock < 0) throw new BizException(3001, "库存不足");
    /* 2. 被动撞到的所有异常（参数错误、空指针、404、500 …） */
    @ExceptionHandler(Exception.class)
    public R<Void> all(Exception e) {
        log.error("System Error", e);          // 后台留堆栈
        return R.fail( "服务器忙，请稍后再试");  // 前端只收到这一句
    }
}
