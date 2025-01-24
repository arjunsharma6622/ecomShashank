package com.shashank.ecom.models;



import jakarta.persistence.Entity;


@Entity
public class Category extends BaseModel {
	
	
	 private String name;
	 	
	   
		public Category() {
	    }

	    public Category(String name) {
	        this.name = name;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }
}
