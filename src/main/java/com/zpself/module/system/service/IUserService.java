package com.zpself.module.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zpself.module.system.entity.User;

public interface IUserService {
    public Page<User> selectUserPage(Page<User> page, Integer state);
}
