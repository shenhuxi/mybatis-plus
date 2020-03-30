package com.baomidou.mybatisplus.samples.quickstart.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.samples.quickstart.enumutil.AgeEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

@Data
//@KeySequence(value = "nextval('seq_article')")
public class User {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    private String name;


    private AgeEnum age;
    private String email;

    @TableLogic
    private Integer deleted=0;
}