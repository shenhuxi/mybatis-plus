package com.baomidou.mybatisplus.samples.quickstart.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.samples.quickstart.enumutil.AgeEnum;
import lombok.Data;

@Data
//@KeySequence(value = "nextval('seq_article')")
public class User {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    private String name;

    private AgeEnum age;

    @TableField(fill = FieldFill.INSERT_UPDATE,update = "CONCAT(%s,'*')")
    private String email;

    @TableLogic
    private Integer deleted=0;
}