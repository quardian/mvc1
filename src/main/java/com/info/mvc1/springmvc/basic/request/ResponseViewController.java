package com.info.mvc1.springmvc.basic.request;

import com.info.mvc1.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * 뷰를 호출하는 방법
 */
@Slf4j
@RestController
public class ResponseViewController
{

    /**
     * ModelAndView 반환
     * @return
     */
    @RequestMapping("response-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView mv = new ModelAndView("response/hello");

        mv.addObject("data", "hello!");

        return mv;
    }


    /**
     * 뷰 경로를 문자열로 반환 ( 최근 트랜드 )
     * @param model
     * @return
     */
    @RequestMapping("response-view-v2")
    public String responseViewV2(Model model){

        model.addAttribute("data", "hello!");

        return "response/hello";
    }

    /**
     * URL의 앞에 / 를 제외하고, 동일한 경로의 view를 찾음. (권장 안함)
     * 찾는 조건) @Controller를 사용하고, HttpServletResponse, OutputStream(Writer) 같은
     * HTTP 메시지 바디를 처리하는 파라미터가 없으면 요청 URL을 참고해서 논리 뷰 이름으로 사용
     * 요청 URL : /response/hello
     * 실행 : templates/response/hello.html
     * @param model
     */
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model){
        model.addAttribute("data", "hello!");
    }

}
