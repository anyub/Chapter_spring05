package com.anyb.ssm.po;

public class DemoObj {
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DemoObj(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public DemoObj() {}
	@Override
	public String toString() {
		return "DemoObj [id=" + id + ", name=" + name + "]";
	}
}
