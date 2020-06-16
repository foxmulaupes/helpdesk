package com.javahelps.mysqlrestservice.controller;
import com.javahelps.mysqlrestservice.Repository.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import com.javahelps.mysqlrestservice.data.User;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.javahelps.mysqlrestservice.files.UserService;
import com.mysql.jdbc.Connection;
@RestController

public class HomeController{

	@Autowired
	private UserService userService;
	@GetMapping("/user")
	public List<User> index(){
		return userService.getUsers();
	}	
	@RequestMapping(value = "/Testing", method = RequestMethod.POST)
	 public String authenticateUser(@RequestParam("username") String empIDTocheck , @RequestParam("password") String passwordTocheck) throws NoSuchAlgorithmException{
		
		System.out.println("EmployeeID:   " +empIDTocheck +"Password  : "+ passwordTocheck); 
		Connection con;
	    PreparedStatement prepared_statement;
	    String pwdinMD5="";
	    boolean flag = false;
            MessageDigest md = MessageDigest.getInstance("MD5"); 
            byte[] messageDigest = md.digest(passwordTocheck.getBytes()); 
            BigInteger no = new BigInteger(1, messageDigest); 
            pwdinMD5 = no.toString(16); 
            while (pwdinMD5.length() < 32) { 
              pwdinMD5 = "0" + pwdinMD5; 
            }
    try{ 
	    Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/helpdesk?user=root&password=1234");
            prepared_statement = (PreparedStatement) con.prepareStatement("select * from login where EmpID=? and Password=?");		  
	    prepared_statement.setString(1,empIDTocheck);
	    prepared_statement.setString(2, pwdinMD5);
            ResultSet resultset = prepared_statement.executeQuery();
	    System.out.println("Records Exist "+resultset.next());
	    if(resultset!=null){
		    String empIDtoCompare = resultset.getString("EmpID");
		    String pwtoCompare = resultset.getString("Password");
		    if((empIDTocheck.equals(empIDtoCompare)) && (pwdinMD5.equals(pwtoCompare))){
			    flag = true;
			    System.out.println("Username and password exist");
		    }
		    if(!flag){
			    System.out.println("Please check again");
		    }
	    } 
	    resultset.close();
	    con.close();
    }
	    catch(Exception e){
		    System.out.println(e.toString());
	    }
	   
        return "API is connected";
    }

}