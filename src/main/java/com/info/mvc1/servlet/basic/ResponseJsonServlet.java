package com.info.mvc1.servlet.basic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.info.mvc1.domain.HelloData;
import com.info.mvc1.util.ServeltUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;

// @ServletComponentScan 활성화시 작동함
@WebServlet(name="responseJsonServlet", urlPatterns = "/servlet/basic/response-json")
public class ResponseJsonServlet extends HttpServlet
{
    ObjectMapper oejbctMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Content-
        ServeltUtils.setContentJson(response);

        HelloData helloData = new HelloData("kim", 20);
        String json = oejbctMapper.writeValueAsString(helloData);

        response.getWriter().write(json);

    }
}
