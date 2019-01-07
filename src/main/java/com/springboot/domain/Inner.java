package com.springboot.domain;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author: yinchengjian
 * @Description:
 * @Date: 2018/6/14
 * @Modified By:
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Inner {

    private Long id;
}
