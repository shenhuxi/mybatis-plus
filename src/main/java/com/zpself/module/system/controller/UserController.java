package com.zpself.module.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zpself.module.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @ResponseBody
    @RequestMapping("/page")
    public Object selectPage(Model model) {

        Page page = new Page(1, 10);          //1表示当前页，而10表示每页的显示显示的条目数
        page = userService.selectUserPage(page, 1);
        return page;
    }
}