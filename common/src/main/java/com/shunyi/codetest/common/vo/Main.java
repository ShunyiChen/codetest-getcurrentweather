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
public class Main implements Serializable {
    private float temp;
    private float feels_like;
    private float temp_min;
    private float temp_max;
    private int pressure;
    private int humidity;
}
