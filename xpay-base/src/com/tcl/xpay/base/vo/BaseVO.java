package com.tcl.xpay.base.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * <br>
 * <b>功能：</b>VO基础类<br>
 */
public abstract class BaseVO implements Serializable {

    /**
     * 业务主键值
     *
     * @return
     */
    //public abstract Serializable getId();

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
