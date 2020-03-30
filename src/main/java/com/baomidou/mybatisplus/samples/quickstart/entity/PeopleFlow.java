package com.baomidou.mybatisplus.samples.quickstart.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zengpeng
 * @since 2020-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("people_flow")
public class PeopleFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("lineId")
    private Integer lineId;

    private String time;

    private Integer type;

    private Integer value;

    @TableLogic
    private Integer deleted=0;

    @Version
    private Integer version;
}
