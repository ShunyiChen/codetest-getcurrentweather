package com.shunyi.codetest.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Shunyi Chen
 * @create 2021-06-02 16:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sys implements Serializable {
    private int type;
    private int id;
    private String country;
    private long sunrise;
    private long sunset;
}
