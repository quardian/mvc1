package com.info.mvc1.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.info.mvc1.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.net.URI;
import java.nio.charset.StandardCharsets;

/**

 */

@Slf4j
@RestController
public class ReuqestBodyJsonController
{
    private ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping( value="/springmvc/basic/requestbody/json-v1", method = RequestMethod.POST)
    public String v1(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messagebody={}", messageBody);
        Member member = objectMapper.readValue(messageBody, Member.class);
        return messageBody;
    }



    @ResponseBody
    @RequestMapping( value="/springmvc/basic/requestbody/json-v2", method = RequestMethod.POST)
    public String v2(@RequestBody String messageBody) throws IOException {

        log.info("messagebody={}", messageBody);
        Member member = objectMapper.readValue(messageBody, Member.class);
        return messageBody;
    }


    /**
     * RequestBody는 생략하면, @ModelAttribute가 되기 때문에 값이 들어오지 않는다.
     * 따라서 HttpBody의 값을 읽어서 객체 바인딩 하려면 반드시 @RequestBody를 붙여야한다.
     * @param member
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping( value="/springmvc/basic/requestbody/json-v3", method = RequestMethod.POST)
    public Member v3(@RequestBody Member member) throws IOException {
        log.info("member={}", member);
        return member;
    }
}
