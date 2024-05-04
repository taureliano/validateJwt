package com.example.validatejwt.bean;

public class JwtClaims {
	private String name;
	private String role;
	private Integer seed;

	public JwtClaims(String name, String role, Integer seed) {
		super();
		this.name = name;
		this.role = role;
		this.seed = seed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getSeed() {
		return seed;
	}

	public void setSeed(Integer seed) {
		this.seed = seed;
	}
}
