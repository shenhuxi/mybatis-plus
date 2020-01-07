package com.zpself.module.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zpself.module.system.entity.User;
import com.zpself.module.system.mapper.UserMapper;
import com.zpself.module.system.service.IUserService;
import org.springframework.stereotype.Service;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {
    public Page<User> selectUserPage(Page<User> page, Integer state) {

        page.setRecords(baseMapper.selectUserList(page,state));
        return page;
    }
}