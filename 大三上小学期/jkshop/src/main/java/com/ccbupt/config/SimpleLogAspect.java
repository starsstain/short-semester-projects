package com.ccbupt.config; 
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;

@Aspect
@Component
public class SimpleLogAspect {
    
    private static final Logger logger = LoggerFactory.getLogger(SimpleLogAspect.class);
    // 最简单的异步：单线程池
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    //@Around("execution(* com.ccbupt.controller..*.*(..))")//拦截全部
    @Around("execution(* com.ccbupt.controller..*.delete*(..)) || " +
            "execution(* com.ccbupt.controller..*.update*(..))")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        
        Object result = null;
        String errorMsg = null;
        String ip = null;
        String userId = null;
        
        try {
            // 获取请求信息（可选）
            ServletRequestAttributes attributes = 
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                ip = request.getRemoteAddr();
                
                // 如果需要用户信息，可以从 session 或 token 获取
                // userId = (String) request.getSession().getAttribute("userId");
            }
            
            result = joinPoint.proceed();
            return result;
        } catch (Exception e) {
            errorMsg = e.getMessage();
            throw e;
        } finally {
            long cost = System.currentTimeMillis() - start;
            
            // 构建日志内容
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            StringBuilder logLine = new StringBuilder();
            logLine.append(String.format("[%s] %s", time, methodName));
            
            if (ip != null) {
                logLine.append(String.format(" | IP: %s", ip));
            }
            if (userId != null) {
                logLine.append(String.format(" | 用户: %s", userId));
            }
            
            logLine.append(String.format(" | 参数: %s", JSON.toJSONString(args)));
            //logLine.append(String.format(" | 结果: %s", 
            //    result != null ? JSON.toJSONString(result) : "null"));
            logLine.append(String.format(" | 错误: %s", errorMsg != null ? errorMsg : "无"));
            logLine.append(String.format(" | 耗时: %dms", cost));
            
            // 写入文件
            asyncWriteToFile(logLine.toString());
            
            // 控制台输出
            logger.info("{} 执行耗时: {}ms", methodName, cost);
        }
    }
    
    /**
     * 异步写入文件
     */
    private void asyncWriteToFile(String logLine) {
        executor.submit(() -> {
            try (PrintWriter writer = new PrintWriter(new FileWriter("app.log", true))) {
                writer.println(logLine);
            } catch (Exception e) {
                logger.error("写日志文件失败: {}", e.getMessage());
            }
        });
    }
}