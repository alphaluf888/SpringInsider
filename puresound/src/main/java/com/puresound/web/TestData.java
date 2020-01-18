package com.puresound.web;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonGetter;



public class TestData {
	private String name;
	private int days;
	private Map<String, String> properties;
	
	@JsonAnyGetter
	public Map<String, String> getProperties() {
		return properties;
	}
	
	@JsonGetter("name")
	public String getTheName() {
		return name;
	}
	
	public static void main(String[] args) {
		Singleton s = Singleton.getInstance();
	}
}
