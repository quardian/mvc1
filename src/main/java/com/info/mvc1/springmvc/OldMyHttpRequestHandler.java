package com.info.mvc1.springmvc;

import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HandlerMapping
 *  - 스프링 빈의 이름으로 핸들러를 찾을 수 있는 핸들러 매핑
 *  - 0 = RequestMappingHandlerMapping  : 애노테이션 기반의 컨트롤러인 @RequestMapping에서 사용
 *  - 1 = BeanNameUrlHandlerMapping     : 스프링 빈의 이름으로 핸들러 찾음 ( V )
 *
 * HandlerAdapter
 *  - Controller 인터페이스를 실행할 수 있는 핸들러 어댑터를 찾고 실행
 *  - 0 = RequestMappingHandlerAdapter  : 애노테이션 기반의 컨트롤러인 @RequestMapping에서 사용
 *  - 1 = HttpRequestHandlerAdapter     : HttpRequestHandler 처리 ( V )
 *  - 2 = SimpleControllerHandlerAdapter: Controller 인터페이스(애노테이션X) 처리
 *
 * #application.property
 * spring.mvc.view.prefix=/WEB-INF/views/
 * spring.mvc.view.suffix=.jsp
 */

@Component("/springmvc/old-http-request-handler")
public class OldMyHttpRequestHandler implements HttpRequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("#####OldMyHttpRequestHandler.handleRequest");
    }
}
