package com.showmeyourcode.projects.springmvc.presentation.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.HandlerInterceptor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

@Slf4j
public class ApiPerformanceMonitorInterceptor implements HandlerInterceptor {

    private static final DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 'o' hh:mm:ss");
    // The Java ThreadLocal class enables you to create variables that can only be read and written by the same thread.
    // Thus, even if two threads are executing the same code,
    // and the code has a reference to the same ThreadLocal variable,
    // the two threads cannot see each other's ThreadLocal variables.
    private final ThreadLocal<StopWatch> stopwatch = new ThreadLocal<>();

    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception exception
    ) throws Exception {
        if (isApiRequest(request)) {
            StopWatch stopWatch = stopwatch.get();
            stopWatch.stop();

            log.info("Total request time (ms): {}", stopWatch.getTotalTimeMillis());

            stopwatch.remove();
        }
    }

    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {
        if (isApiRequest(request)) {
            StopWatch stopWatch = new StopWatch(handler.toString());
            stopWatch.start(handler.toString());
            stopwatch.set(stopWatch);
            log.debug("Request's path: '{}' Request started at: {}", getURLPath(request), getCurrentTime());
        }
        return true;
    }

    private String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        return formatter.format(calendar.getTime());
    }

    private String getURLPath(HttpServletRequest request) {
        return request.getRequestURI() + (Objects.isNull(request.getQueryString()) ? "" : "?" + request.getQueryString());
    }

    private boolean isApiRequest(HttpServletRequest request) {
        return request.getRequestURI().contains("/api/");
    }
}
