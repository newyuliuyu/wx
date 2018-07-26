/** 
 * Project Name:easydata.commons 
 * File Name:DateStyle.java 
 * Package Name:com.ez.commons.util 
 * Date:2017年3月15日上午11:00:10 
 * Copyright (c) 2017, easytnt All Rights Reserved. 
 * 
 */
package com.ez.common.util;

/**
 * ClassName: DateStyle <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年3月15日 上午11:00:10 <br/>
 * 
 * @author 刘海林
 * @version v1.0
 * @since JDK 1.7+
 */
public enum DateStyle {
	MM_DD("MM-dd"), YYYY_MM("yyyy-MM"), YYYY_MM_DD("yyyy-MM-dd"), MM_DD_HH_MM("MM-dd HH:mm"), MM_DD_HH_MM_SS(
			"MM-dd HH:mm:ss"), YYYY_MM_DD_HH_MM("yyyy-MM-dd HH:mm"), YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),

	MM_DD_EN("MM/dd"), YYYY_MM_EN("yyyy/MM"), YYYY_MM_DD_EN("yyyy/MM/dd"), MM_DD_HH_MM_EN(
			"MM/dd HH:mm"), MM_DD_HH_MM_SS_EN("MM/dd HH:mm:ss"), YYYY_MM_DD_HH_MM_EN(
					"yyyy/MM/dd HH:mm"), YYYY_MM_DD_HH_MM_SS_EN("yyyy/MM/dd HH:mm:ss"),

	MM_DD_CN("MM月dd日"), YYYY_MM_CN("yyyy年MM月"), YYYY_MM_DD_CN("yyyy年MM月dd日"), MM_DD_HH_MM_CN(
			"MM月dd日 HH:mm"), MM_DD_HH_MM_SS_CN("MM月dd日 HH:mm:ss"), YYYY_MM_DD_HH_MM_CN(
					"yyyy年MM月dd日 HH:mm"), YYYY_MM_DD_HH_MM_SS_CN("yyyy年MM月dd日 HH:mm:ss"),

	HH_MM("HH:mm"), HH_MM_SS("HH:mm:ss");

	private String value;

	DateStyle(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
