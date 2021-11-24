package com.info.mvc1.domain;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * 핸들러 : 컨트롤러의 이름을 더 넓은 범위인 핸들러로 변경
 */
public interface MyHandlerAdapter {

    /** 힌들러(컨트롤러)를 처리할 수 있는지 조회*/
    boolean supports(Object handler);


    /** 어댑터를 통해 실제 컨트롤러를 호출하고 ModelView를 반환 */
    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;
}
