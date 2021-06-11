package com.shunyi.codetest.weatherconsumer.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shunyi Chen
 * @create 2021-06-11 9:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {
    private Long id;
    private String name;
}
