package com.shunyi.codetest.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shunyi Chen
 * @create 2021-06-04 7:22
 */
@RestController
@Slf4j
@Api(tags = "用户信息管理")
public class HelloController {

    private static final String HELLO_MSG = "Hello Swagger3.0!";

    @GetMapping(value = "/swagger/hello")
    @ApiOperation("Say Hello")
    @ApiImplicitParam(name = "id", value = "用户编号", required = false)
    public String sayHello() {
        log.info("********输出结果："+ HELLO_MSG);
        return HELLO_MSG;
    }
}
