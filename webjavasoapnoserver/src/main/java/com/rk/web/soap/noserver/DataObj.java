package com.rk.web.soap.noserver;

public class DataObj {
	private int i;
	private int j;
	private String operation;
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public int getJ() {
		return j;
	}
	public void setJ(int j) {
		this.j = j;
	}
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	@Override
	public String toString() {
		return "DataObj [i=" + i + ", j=" + j + ", operation=" + operation + "]";
	}
	

}
