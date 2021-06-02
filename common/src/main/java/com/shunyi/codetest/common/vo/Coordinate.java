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
public class Coordinate implements Serializable {
    private float lon;
    private float lat;
}
