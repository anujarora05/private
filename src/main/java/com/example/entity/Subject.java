package com.example.entity;

import org.springframework.data.mongodb.core.mapping.Field;

public class Subject {
	@Field(name = "sub_name")
	private String subName;
	@Field(name = "Max_Marks")
	private double maxMarks;
	@Field(name = "compulsory")
	private boolean compulsory;

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public double getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(double maxMarks) {
		this.maxMarks = maxMarks;
	}

	public boolean isCompulsory() {
		return compulsory;
	}

	public void setCompulsory(boolean compulsory) {
		this.compulsory = compulsory;
	}

}
