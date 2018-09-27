package com.thinkgem.jeesite.controller;

import com.alibaba.fastjson.JSON;
import com.thinkgem.jeesite.entity.SqlEntity;
import com.thinkgem.jeesite.service.impl.AdminServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author TianYu
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminServiceImpl adminService;

    @RequestMapping(value = "/mysql", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String executeMySQL(@RequestBody SqlEntity param) {
        if (StringUtils.isEmpty(param.getSql())) {
            return "参数为空";
        }
        return JSON.toJSONString(adminService.mysql(param.getSql()));
    }

    @RequestMapping(value = "/oracle", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String executeOracle(@RequestBody SqlEntity param) {
        if (StringUtils.isEmpty(param.getSql())) {
            return "参数为空";
        }
        return JSON.toJSONString(adminService.oracle(param.getSql()));
    }

}
