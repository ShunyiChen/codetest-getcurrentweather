package com.shunyi.codetest.city.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Shunyi Chen
 * @create 2021-06-02 16:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "wcity")
public class City implements Serializable {
    @ApiModelProperty(value = "主键ID", example = "123")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "City name", example = "Sydney, New South Wales, Australia")
    private String name;
}
