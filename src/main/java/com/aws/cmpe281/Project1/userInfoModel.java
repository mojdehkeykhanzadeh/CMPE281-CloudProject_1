package com.aws.cmpe281.Project1;

import org.springframework.web.multipart.MultipartFile;

public class userInfoModel {
	 private String firstName;
	 private String lastName;
	private int user_id ;
	private int file_id ;
	private String user_pass;
	private String file_description;
	 
	public userInfoModel(){
		
	}
	 public String getFirstName() {
	        return firstName;
	    }
	     
	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }
	 
	    public String getLastName() {
	        return lastName;
	    }
	 
	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }
	    public int getUserid() {
	        return user_id;
	    }
	 
	    public void setUserid(int user_id) {
	        this.user_id=user_id;
	    }
	    public int getFileid() {
	        return user_id;
	    }
	 
	    public void setFileid(int file_id) {
	        this.file_id=file_id;
	    }
	    public String getFileDes() {
	        return file_description;
	    }
	 
	    public void setFileDes(String file_description) {
	        this.file_description=file_description;
	    }
	  
	  
}
