package com.mlsama.springsecurity.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mlsama.springsecurity.entity.BaseResponse;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * DESC: 响应工具类,返回json格式的数据
 * AUTHOR:mlsama
 * 2020/6/28 10:11
 */
public class ResponseUtil {
    public static ResponseUtil INSTANT = new ResponseUtil();
    private ResponseUtil(){}
    private static final ObjectMapper mapper = new ObjectMapper();

    public void write(HttpServletResponse httpServletResponse, BaseResponse resp) throws IOException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter out = httpServletResponse.getWriter();
        out.write(mapper.writeValueAsString(resp));
        out.flush();
        out.close();
    }
}
