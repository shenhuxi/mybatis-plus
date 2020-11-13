package com.baomidou.mybatisplus.samples.quickstart.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.samples.quickstart.entity.PeopleFlow;
import com.baomidou.mybatisplus.samples.quickstart.mapper.PeopleFlowMapper;
import com.baomidou.mybatisplus.samples.quickstart.service.IPeopleFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zengpeng
 * @since 2020-03-27
 */
@RestController
@RequestMapping("/people-flow")
public class PeopleFlowController {
    @Autowired
    IPeopleFlowService iPeopleFlowService;
    @Autowired
    PeopleFlowMapper peopleFlowMapper;


    @GetMapping("/delete")
    public Object delete() {
        peopleFlowMapper.delete(null);
        return null;
//        PeopleFlow peopleFlow = new PeopleFlow();
//        peopleFlow.setType(0).setValue(146).setTime("2020-03-25 08:00").setLineId(1);
//        iPeopleFlowService.save(peopleFlow);
//        //PeopleFlow deletebyId = iPeopleFlowService.getById(peopleFlow.getId());
//        iPeopleFlowService.removeById(peopleFlow.getId());
//        return peopleFlow;
    }

    @GetMapping("/update")
    public Object update(long id) {
        PeopleFlow deletebyId = iPeopleFlowService.getOne(new QueryWrapper<PeopleFlow>().eq("id", 1));
        deletebyId.setValue(deletebyId.getValue() + 1);
        iPeopleFlowService.updateById(deletebyId);
        return deletebyId;
    }
}
