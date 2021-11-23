package com.info.mvc1.frontcontroller.v3.controller;

import com.info.mvc1.domain.ModelView;
import com.info.mvc1.frontcontroller.domain.MyView;
import com.info.mvc1.frontcontroller.v2.ControllerV2;
import com.info.mvc1.frontcontroller.v3.ControllerV3;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3
{
    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
    }
}
