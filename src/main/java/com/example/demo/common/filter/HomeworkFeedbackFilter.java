package com.example.demo.common.filter;

import com.alibaba.fastjson.JSON;
import com.example.demo.common.config.FilterUrlConfig;
import com.example.demo.common.response.Response;
import com.example.demo.common.response.ResponseCode;
import com.example.demo.common.util.JwtUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class HomeworkFeedbackFilter implements Filter {
    @Resource
    private FilterUrlConfig filterUrlConfig;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        String token = request.getHeader("token");
        boolean result = true;
        if (!filterUrlConfig.isFilterUrl(requestURI)) {
            result = executeValid(response, token);
        }
        if (result) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }

    public boolean executeValid(HttpServletResponse response, String token) {
        Response<Long> result = JwtUtil.validationToken(token);
        if (result.getCode() != ResponseCode.OK.getCode()) {
            PrintWriter writer = null;
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/json; charset=utf-8");
            try {
                writer = response.getWriter();
                writer.print(JSON.toJSONString(result));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null)
                    writer.close();
            }
            return false;
        }
        return true;
    }
}
