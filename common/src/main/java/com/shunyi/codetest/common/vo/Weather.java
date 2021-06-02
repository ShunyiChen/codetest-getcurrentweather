package com.shunyi.codetest.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Shunyi Chen
 * @create 2021-06-02 16:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather implements Serializable {
    private int id;
    private String main;
    private String description;
    private String icon;
}
