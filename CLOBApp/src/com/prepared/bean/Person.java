package com.prepared.bean;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.Serializable;

public class Person implements Serializable{
	
	private String person_name;
	private FileReader person_image;
	
	public Person() {
		
	}
	public String getPerson_name() {
		return person_name;
	}
	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}
	public FileReader getPerson_image() {
		return person_image;
	}
	public void setPerson_image(FileReader fr) {
		this.person_image = fr;
	}
	@Override
	public String toString() {
		return "Person [person_name=" + person_name + ", person_image=" + person_image + "]";
	}
	
	
	
	
	
	
	

}
