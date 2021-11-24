package com.info.mvc1.frontcontroller.v4.controller;

import com.info.mvc1.domain.ModelView;
import com.info.mvc1.frontcontroller.v3.ControllerV3;
import com.info.mvc1.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4
{
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {

        return "new-form";
    }
}
