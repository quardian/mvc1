package com.info.mvc1.frontcontroller.v3;

import com.info.mvc1.domain.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface ControllerV3
{
    ModelView process(Map<String, String> paramMap) ;
}
