package com.shunyi.codetest.common.vo;

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
public class WeatherForecast implements Serializable {
    private Coordinate coord;
    private Weather[] weather;
    private String base;
    private Main main;
    private int visibility;
    private Wind wind;
    private Clouds clouds;
    private long dt;
    private Sys sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;
}
