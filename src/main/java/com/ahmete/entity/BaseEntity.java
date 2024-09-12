package com.ahmete.entity;

public class BaseEntity {
	private Integer state;
	private Long createat;
	private Long updateat;
	
	public BaseEntity() {
	}
	
	public BaseEntity(Integer state, Long createat, Long updateat) {
		this.state = state;
		this.createat = createat;
		this.updateat = updateat;
	}
	
	public Integer getState() {
		return state;
	}
	
	public void setState(Integer state) {
		this.state = state;
	}
	
	public Long getCreateat() {
		return createat;
	}
	
	public void setCreateat(Long createat) {
		this.createat = createat;
	}
	
	public Long getUpdateat() {
		return updateat;
	}
	
	public void setUpdateat(Long updateat) {
		this.updateat = updateat;
	}
}