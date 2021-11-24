package com.info.mvc1.frontcontroller.v4;

import com.info.mvc1.domain.ModelView;

import java.util.Map;

public interface ControllerV4
{
    String process(Map<String, String> paramMap, Map<String, Object> model) ;
}
