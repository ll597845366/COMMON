package com.KongJian.pojo;

import java.io.Serializable;
import java.util.Date;

import org.jboss.logging.FormatWith;
import org.springframework.format.annotation.DateTimeFormat;

public class BasePojo implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
private Date createdTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
private Date updatedTime;
public Date getCreatedTime() {
	return createdTime;
}
public void setCreatedTime(Date createdTime) {
	this.createdTime = createdTime;
}
public Date getUpdatedTime() {
	return updatedTime;
}
public void setUpdatedTime(Date updatedTime) {
	this.updatedTime = updatedTime;
}

}
