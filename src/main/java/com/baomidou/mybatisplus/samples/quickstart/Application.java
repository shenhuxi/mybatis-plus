package com.baomidou.mybatisplus.samples.quickstart;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.samples.quickstart.entity.User;
import com.baomidou.mybatisplus.samples.quickstart.enumutil.AgeEnum;
import com.baomidou.mybatisplus.samples.quickstart.mapper.UserMapper;
import com.baomidou.mybatisplus.samples.quickstart.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zengpeng on 2019/3/28
 */
@SpringBootApplication
@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper")
@RestController
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;


    @GetMapping("/test")//上传
    public String test(){
        User user = userService.getById(1);
        //插入
        userMapper.insert(user);

        //查询
        System.out.println(user);
        List<User> jack = userService.lambdaQuery().eq(User::getName, "Jone").list();
        userService.list(new QueryWrapper<User>().eq("name",null)
                .eq("name","Jack"));
        userService.getOne(new QueryWrapper<User>().allEq(new HashMap<>()),true);
        userService.getOne(new QueryWrapper<User>().le(false,"age","1"));
        userService.getOne(new QueryWrapper<User>().between(false,"age","2","20"));
        userService.getOne(new QueryWrapper<User>().in(false,"age",new ArrayList<>()));

        //修改
        userService.update(user,new UpdateWrapper<User>().eq("name","Jack"));
        userService.lambdaUpdate().eq(User::getName,"Jack").update(user);
        userMapper.updateById(user);

        //分页
        Page<User> page = userService.page(new Page<>(1,5));
        System.out.println(page);

        //统计
        int count = userService.count();
        System.out.println(count);

        return "test";
    }


    @GetMapping("/api")
    public Object api() {
        User one =null;
        List<User> list =null;
        one = userService.getOne(new QueryWrapper<User>()
        .apply("id=1")
        .last("limit 1"));

//        one = userService.getOne(new QueryWrapper<User>()
//                .exists("select id from user where id =4"));

        list = userService.list(new QueryWrapper<User>()
                .select("id", "name", "age"));

        userService.update(one, new UpdateWrapper<User>()
                .eq("id","1")
                .set("name","jeck"));

        User user = new User();
        //user.setId(6L);
        user.setAge(AgeEnum.ONE);
        user.setName("麻子");
        userService.save(user);
        userService.removeById(user.getId());
        return one;
    }







}
