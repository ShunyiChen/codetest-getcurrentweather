package com.shunyi.codetest.city.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
    private Long id;
    private String name;
}
