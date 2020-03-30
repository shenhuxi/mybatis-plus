package com.baomidou.mybatisplus.samples.quickstart.service.impl;

import com.baomidou.mybatisplus.samples.quickstart.entity.PeopleFlow;
import com.baomidou.mybatisplus.samples.quickstart.mapper.PeopleFlowMapper;
import com.baomidou.mybatisplus.samples.quickstart.service.IPeopleFlowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zengpeng
 * @since 2020-03-27
 */
@Service
public class PeopleFlowServiceImpl extends ServiceImpl<PeopleFlowMapper, PeopleFlow> implements IPeopleFlowService {

}
