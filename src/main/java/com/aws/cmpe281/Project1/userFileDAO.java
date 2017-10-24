package com.aws.cmpe281.Project1;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
@Configuration 
public class userFileDAO implements userDao {
	public  DataSource setdataSource(){
		try{
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        //MySQL database we are using
	        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	        dataSource.setUrl("jdbc:mysql://myappdb.cmubczprrm7g.us-west-1.rds.amazonaws.com");//change url
	        dataSource.setUsername("mojdeh");//change userid
	        dataSource.setPassword("Mojmoji6");//change pwd
	        Connection conn =dataSource.getConnection();
	        
	        return dataSource;
		}
		 catch (Exception e) {
			 throw new RuntimeException(e);
	        }
		
		
	}
	
	@Override
	public void create(userInfoModel user) {
		// TODO Auto-generated method stub
		
	}

}

