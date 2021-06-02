package com.shunyi.codetest.common.entity;

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
public class City implements Serializable {
    private Long id;
    private String name;
}
