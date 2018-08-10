/** 
 * Project Name:easydata.interface 
 * File Name:SystemAttribute.java 
 * Package Name:com.ez.system 
 * Date:2017年3月20日下午7:57:39 
 * Copyright (c) 2017, easytnt All Rights Reserved. 
 * 
 */
package com.ez.business.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * ClassName: SystemAttribute <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年3月20日 下午7:57:39 <br/>
 * 
 * @author 刘海林
 * @version v1.0
 * @since JDK 1.7+
 */
public class SystemAttribute implements Serializable {
	private static final long	serialVersionUID	= 1L;
	private Long id;
	private String attrName;
	private String aliasName;
	private String value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(attrName).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SystemAttribute)) {
			return false;
		}
		SystemAttribute that = (SystemAttribute) obj;
		return new EqualsBuilder().append(attrName, that.aliasName).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(attrName).append(aliasName).append(value).toString();
	}

}
