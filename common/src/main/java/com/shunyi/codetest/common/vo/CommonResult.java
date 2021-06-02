package com.shunyi.codetest.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Shunyi Chen
 * @create 2021-06-02 16:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonResult<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;
}
