package com.wh.bdgw.aspect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 文件名:DoubleClick
 * 创建者:zed
 * 创建日期:2019/3/7 13:52
 * 描述:允许频繁点击
 */

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD})
public @interface MultiClick {
}
