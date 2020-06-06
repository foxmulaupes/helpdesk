import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;
import java.math.BigInteger;
import java.security.MessageDigest;

public class login {
	
	public Connection conn;
	public Statement stmt;
	public ResultSet rs;
	int flag=0;
	static int flag1;
	HashMap<String,ticketsinfo> map = new HashMap<String,ticketsinfo>();
	HashMap<String,userticketsinfo> usermap = new HashMap<String,userticketsinfo>();
	
	 /*------------------------------------For Testing------------------------------------*/
	 // IN DATABASE TABLE `login`
	 // add EmpId = admin , password = 21232f297a57a5a743894a0e4a801fc3 , EmpType = ADMIN
	 // add EmpId = EMP1 , password = ac8be4aee61f5f6e21b8c5afffb52939 , EmpType = Employee
	 
	 // Manual input Employee
	 // set uid = EMP1
	 // set pass = emp
	
	// Manual input Admin
	// set uid = admin
    // set pass = admin
	/*------------------------------------------------------------------------------------*/
	
	// Manual input
	String uid = "admin"; 
	String pass = "admin";
		
	// Creating Connection 
	public login(){
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testingtest","root","");
			stmt = conn.createStatement();
			System.out.print("Connection Successful\n");
		}
		catch(Exception e){
			System.out.println("Error: "+e);
		}
	}
	// Connection End
	
	
	// Sanitizing Input
	// Conquers unstructured data problem using regular expression. Will sanitize the data.
    public static String sanitizeInput(String garbageInp) { 
		String clean = garbageInp.replaceAll("(?i)<.*?>.*?</.*?>", "").replaceAll("(?i)<.*?>", "")
				.replaceAll("(?i)</.*?>", "");
		return clean;
    }
    // Sanitizing Input END
	
    
	// Login Validation
	public void login_validation(){
		
		try {			
			// MD5 password Encryption (for testing add `pwdinMD5` value to DB)
			MessageDigest md = MessageDigest.getInstance("MD5"); 
	        byte[] messageDigest = md.digest(pass.getBytes()); 
	        BigInteger no = new BigInteger(1, messageDigest); 
	        String pwdinMD5 = no.toString(16); 
	        while (pwdinMD5.length() < 32) { 
	          pwdinMD5 = "0" + pwdinMD5; 
	        }
	        //System.out.println(pwdinMD5); /*To check Encrypted MD5 Password*/
	        
			PreparedStatement stmt=conn.prepareStatement("select * from login");  
			ResultSet rs=stmt.executeQuery(); 
		    
			while (rs.next()) {
				if((rs.getString("EmpID").equals(uid) && rs.getString("Password").equals(pwdinMD5) && rs.getString("EmpType").equals("ADMIN"))) {
		           	System.out.print("Admin Logged In\n");
		            flag=1;
		            flag1=flag;
		            break;
		            }
				else if((rs.getString("EmpID").equals(uid) && rs.getString("Password").equals(pwdinMD5) && rs.getString("EmpType").equals("Employee"))) {
					System.out.print("User Logged In\n");
		            flag=2;
		            flag1=flag;
		            break;
		            }
				}
			
			if(flag == 0) {
            System.out.print("Incorrect User ID or Password");
            }
		 }
		catch(Exception e) {
		       System.out.println(e);
		       } 
	}
	// Login Validation End

	/*---------------------------------------------HASHMAP---------------------------------------------*/
	
	// Putting Admin data into HashMap
	 public void put_data() {
	     ticketsinfo t;
	        try{
	        	PreparedStatement stmt=conn.prepareStatement("select * from ticket");  
				ResultSet rs=stmt.executeQuery();
		 
	            while(rs.next()){
	            	String Ticket_ID = rs.getString("TicketID");
					String Issue = rs.getString("TicketDesc");
					String Status = rs.getString("TicketStatus");
					String assigned_to = rs.getString("ResolvedBy");
					String Userid = rs.getString("UserID");
					String Ticketcat = rs.getString("TicketCatg");
					String Priority = rs.getString("Priority");
					String Issuedate = rs.getString("IssueDate");
					String Resolvedate = rs.getString("ResolveDate");
				
	                t = new ticketsinfo(Ticket_ID, Issue, Status, assigned_to, Userid, Ticketcat, Priority, Issuedate, Resolvedate);
	                //Set data in the HashMap
	                map.put(Ticket_ID, t);
	            }
	        }
	        catch(Exception e) {
				 System.out.println(e);
			 }
	 }
	 
	// Putting user data into HashMap
	 public void user_put_data() {
	     userticketsinfo ut;
	        try{
	        	PreparedStatement stmt=conn.prepareStatement("SELECT * FROM ticket WHERE UserID ='" + uid +"'");  
				ResultSet rs=stmt.executeQuery();
		 
	            while(rs.next()){
	            	String Ticket_ID = rs.getString("TicketID");
					String Issue = rs.getString("TicketDesc");
					String Status = rs.getString("TicketStatus");
					String assigned_to = rs.getString("ResolvedBy");
					String Userid = rs.getString("UserID");
					String Ticketcat = rs.getString("TicketCatg");
					String Priority = rs.getString("Priority");
					String Issuedate = rs.getString("IssueDate");
					String Resolvedate = rs.getString("ResolveDate");
				
	                ut = new userticketsinfo(Ticket_ID, Issue, Status, assigned_to, Userid, Ticketcat, Priority, Issuedate, Resolvedate);
	                //Set data in the HashMap
	                usermap.put(Ticket_ID, ut);
	            }
	        }
	        catch(Exception e) {
				 System.out.println(e);
			 }
	 }
	 
	 /*---------------------------------------------HASHMAP END---------------------------------------------*/
	 
	 /*---------------------------------------------RETRIEVING ADMIN DATA---------------------------------------------*/
	 
	 // Retrieving All Data from HashMap for ADMIN
	 public void retrieve_all_data() {
		 // Replace all print statements with the front-end display code
		 	System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
			System.out.println("User ID"+"       "+"Ticket ID"+"       "+"Issue"+"                   "+"Status"+"         "+"Assigned To"+"        "+"Ticket Cat"+"         "+"Priority"+"         "+"Issue Date"+"         "+"Resolve Date");
			System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
			int flag = 0;
			for(String i : map.keySet()){
	            ticketsinfo ti = map.get(i);
	            	flag = 1;
	            	System.out.println(ti.getUserid()+"          "+ti.getTicketid()+"          "+ti.getIssue()+"          "+ti.getStatus()+"          "+ti.getAssign()+"          "+ti.getTicketcat()+"          "+ti.getPriority()+"          "+ti.getIssuedate()+"          "+ti.getResolvedate());
	            	System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
	            }
			if(flag==0) {
	             System.out.print("No Record Exists\n");
	             }
	 }
	 
	 // Retrieving InProgress from HashMap for ADMIN
	 public void retrieve_inprogress_data() {
		// Replace all print statements with the front-end display code
		 	System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
		 	System.out.println("User ID"+"       "+"Ticket ID"+"       "+"Issue"+"                   "+"Status"+"         "+"Assigned To"+"        "+"Ticket Cat"+"         "+"Priority"+"         "+"Issue Date"+"         "+"Resolve Date");
			System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
			int flag = 0;
			for(String i : map.keySet()){
	            ticketsinfo ti = map.get(i);
	            if(ti.getStatus().equals("In Progress")) {
	            	flag = 1;
	            	System.out.println(ti.getUserid()+"          "+ti.getTicketid()+"          "+ti.getIssue()+"          "+ti.getStatus()+"          "+ti.getAssign()+"          "+ti.getTicketcat()+"          "+ti.getPriority()+"          "+ti.getIssuedate()+"          "+ti.getResolvedate());
	            	System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
	            }
	        }
			if(flag==0) {
	             System.out.print("No Record Exists\n");
	             }
	 }
	 
	 // Retrieving Resolved Data from HashMap for ADMIN
	 public void retrieve_resolved_data() {
		// Replace all print statements with the front-end display code
		 	System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
		 	System.out.println("User ID"+"       "+"Ticket ID"+"       "+"Issue"+"                   "+"Status"+"         "+"Assigned To"+"        "+"Ticket Cat"+"         "+"Priority"+"         "+"Issue Date"+"         "+"Resolve Date");
			System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
			int flag = 0;
			for(String i : map.keySet()){
	            ticketsinfo ti = map.get(i);
	            if(ti.getStatus().equals("Resolved")) {
	            	flag = 1;
	            	System.out.println(ti.getUserid()+"          "+ti.getTicketid()+"          "+ti.getIssue()+"          "+ti.getStatus()+"          "+ti.getAssign()+"          "+ti.getTicketcat()+"          "+ti.getPriority()+"          "+ti.getIssuedate()+"          "+ti.getResolvedate());
	            	System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
	            }
	        }
			if(flag==0) {
	             System.out.print("No Record Exists\n");
	             }
	 }
	 
	 // Edit Ticket from HashMap and then calling put_data (because it is mandatory in main method to update HashMap) for ADMIN
	 public void edit_ticket() {
		 // Manual Input of TID, New Status and New EMP Assigned and then updating database, replace input field with front-end code
		 	String tid;
		 	String updated_status;
		 	String updated_emp;
		 	Scanner scan = new Scanner(System.in);
		 	System.out.print("Enter Ticket ID\n");
		 	tid = scan.nextLine();
		 	int flag = 0;
	        for(String i : map.keySet()){
	            ticketsinfo ti = map.get(i);
	            if(tid.equals(ti.getTicketid())) {
	             flag = 1;
	             System.out.print("Enter Status\n");
	    		 updated_status = scan.nextLine();
	    		 System.out.print("Enter Assigned Employee\n");
	    		 updated_emp = scan.nextLine();
	    		 
	    		 try{
	    			String sql = "UPDATE `ticket` set TicketStatus = '" + updated_status + "', ResolvedBy = '" + updated_emp + "' where TicketID LIKE '%" + tid + "%'";
	    			stmt.executeUpdate(sql);
	 	        	System.out.println("Database updated successfully\n");
	 	        }
	 	        catch(SQLException e) {
	 				 System.out.println(e);
	 			 } 
	           }
	        }
	        if(flag==0) {
	             System.out.print("Incorrect ID!\n");
           }
	       scan.close();
	 }
	 
	 // Search Ticket using HashMap for ADMIN
	 public void search() {
		// Replace all print statements with the front-end display code
		 Scanner sc= new Scanner(System.in);
		 System.out.print("Enter Ticket ID\n");
	     String search = sc.nextLine();
	     int flag=0;

	        for(String i : map.keySet()){
	            ticketsinfo ti = map.get(i);
	            if(ti.getTicketid().equals(search)) {
	            	flag=1;
	            	System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
	            	System.out.println("User ID"+"       "+"Ticket ID"+"       "+"Issue"+"                   "+"Status"+"         "+"Assigned To"+"        "+"Ticket Cat"+"         "+"Priority"+"         "+"Issue Date"+"         "+"Resolve Date");
	    			System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
	    			System.out.println(ti.getUserid()+"          "+ti.getTicketid()+"          "+ti.getIssue()+"          "+ti.getStatus()+"          "+ti.getAssign()+"          "+ti.getTicketcat()+"          "+ti.getPriority()+"          "+ti.getIssuedate()+"          "+ti.getResolvedate());
	            	System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
	            }
	        }
	        
	        if(flag==0) {
	             System.out.print("Incorrect Ticket ID!\n");
          }
	      sc.close();
	 }
	 
	 // Inserting Comment in Ticket assigned to a particular Employee (ADMIN SIDE)
	 public void insertComment() {
		 String tid;
		 String data;
		 java.util.Date date=new java.util.Date();
		 java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());
		 Scanner scan = new Scanner(System.in);
		 System.out.print("Enter Ticket ID\n");
		 tid = scan.nextLine();
		 int flag = 0;
	       for(String i : map.keySet()){
	           ticketsinfo ti = map.get(i);
	           if(tid.equals(ti.getTicketid())) {
	            flag = 1;
	            System.out.print("Add Comment\n");
	    	 data = scan.nextLine();		 	
		 
		// Cleaning the data if unstructured or if any unwanted character is present.
	        String sanEmpID = sanitizeInput(uid);
	        String sanTicketID = sanitizeInput(tid);
	        String sanData = sanitizeInput(data);
	        
	        try{
	            PreparedStatement prepared_stmt = (PreparedStatement)conn.prepareStatement("INSERT INTO comment (Comment, CommentTime, TicketID, EmpID)VALUES(?, ?, ?,?)");
	            prepared_stmt.setString(1, sanData);
	            prepared_stmt.setTimestamp(2, sqlTime);
	            prepared_stmt.setString(3, sanTicketID);
	            prepared_stmt.setString(4, sanEmpID);

	            int exec = prepared_stmt.executeUpdate();
	            System.out.println(exec + " Comment inserted!");
	            conn.close();
	        }
	        catch(Exception e){
			    System.out.println("Error: "+e);
		    }
	      }
	    }
	      if(flag==0) {
	            System.out.print("Incorrect ID!\n");
         }
	       scan.close();
	 }
	 
	 // Viewing Comment of a particular Ticket assigned to a particular Employee (ADMIN SIDE)
	 public void viewComment() {
		 String tid;
		 Scanner scan = new Scanner(System.in);
		 System.out.print("Enter Ticket ID\n");
		 tid = scan.nextLine();
		 int flag = 0;
		 System.out.println("*******************************************************************************************************");
		 System.out.println("Comment"+"				"+"By"+"				"+"Time");
		 System.out.println("*******************************************************************************************************");
	       for(String i : map.keySet()){
	           ticketsinfo ti = map.get(i);
	           if(tid.equals(ti.getTicketid())) {
	            flag = 1;
	        try{
	        	PreparedStatement stmt=conn.prepareStatement("SELECT * FROM comment WHERE TicketID ='" + tid +"'");  
				ResultSet rs=stmt.executeQuery();
				while (rs.next()) {
					System.out.println(rs.getString("Comment")+"			"+rs.getString("EmpID")+"			"+rs.getString("CommentTime"));
					System.out.println("-------------------------------------------------------------------------------------------------------");
					}
	        }
	        catch(Exception e){
			    System.out.println("Error: "+e);
		    }
	      }
	    }
	      if(flag==0) {
	            System.out.print("Incorrect ID!\n");
         }
	       scan.close();
	 }
	 
	 /*---------------------------------------------RETRIEVING ADMIN DATA END---------------------------------------------*/
	 
	 /*---------------------------------------------RETRIEVING USER DATA---------------------------------------------*/
	 
	 // Retrieving All Data from HashMap for USER
		 public void retrieve_user_all_data() {
			 // Replace all print statements with the front-end display code
			 	System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
				System.out.println("Ticket ID"+"       "+"Issue"+"                   "+"Status"+"         "+"Assigned To"+"        "+"Ticket Cat"+"         "+"Priority"+"         "+"Issue Date"+"         "+"Resolve Date");
				System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
				int flag = 0;
				for(String i : usermap.keySet()){
		            userticketsinfo uti = usermap.get(i);
		            	flag = 1;
		            	System.out.println(uti.getTicketid()+"          "+uti.getIssue()+"          "+uti.getStatus()+"          "+uti.getAssign()+"          "+uti.getTicketcat()+"          "+uti.getPriority()+"          "+uti.getIssuedate()+"          "+uti.getResolvedate());
		            	System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
		            }
				if(flag==0) {
		             System.out.print("No Record Exists\n");
		             }
		 }
		 
		 // Retrieving InProgress from HashMap for USER
		 public void retrieve_user_inprogress_data() {
			// Replace all print statements with the front-end display code
			 	System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
			 	System.out.println("Ticket ID"+"       "+"Issue"+"                   "+"Status"+"         "+"Assigned To"+"        "+"Ticket Cat"+"         "+"Priority"+"         "+"Issue Date"+"         "+"Resolve Date");
				System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
				int flag = 0;
				for(String i : usermap.keySet()){
		            userticketsinfo uti = usermap.get(i);
		            if(uti.getStatus().equals("In Progress")) {
		            	flag = 1;
		            	System.out.println(uti.getTicketid()+"          "+uti.getIssue()+"          "+uti.getStatus()+"          "+uti.getAssign()+"          "+uti.getTicketcat()+"          "+uti.getPriority()+"          "+uti.getIssuedate()+"          "+uti.getResolvedate());
		            	System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
		            }
		        }
				if(flag==0) {
		             System.out.print("No Record Exists\n");
		             }
		 }
		 
		 // Retrieving Resolved Data from HashMap for USER
		 public void retrieve_user_resolved_data() {
			// Replace all print statements with the front-end display code
			 	System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
			 	System.out.println("Ticket ID"+"       "+"Issue"+"                   "+"Status"+"         "+"Assigned To"+"        "+"Ticket Cat"+"         "+"Priority"+"         "+"Issue Date"+"         "+"Resolve Date");
				System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
				int flag = 0;
				for(String i : usermap.keySet()){
		            userticketsinfo uti = usermap.get(i);
		            if(uti.getStatus().equals("Resolved")) {
		            	flag = 1;
		            	System.out.println(uti.getTicketid()+"          "+uti.getIssue()+"          "+uti.getStatus()+"          "+uti.getAssign()+"          "+uti.getTicketcat()+"          "+uti.getPriority()+"          "+uti.getIssuedate()+"          "+uti.getResolvedate());
		            	System.out.print("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
		            }
		        }
				if(flag==0) {
		             System.out.print("No Record Exists\n");
		             }
		 }
		 
		 // Inserting Comment in Ticket assigned to a particular Employee (EMPLOYEE SIDE)
		 public void user_insertComment() {
			 String tid;
			 String data;
			 java.util.Date date=new java.util.Date();
			 java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());
			 Scanner scan = new Scanner(System.in);
			 System.out.print("Enter Ticket ID\n");
			 tid = scan.nextLine();
			 int flag = 0;
		       for(String i : usermap.keySet()){
		           userticketsinfo uti = usermap.get(i);
		           if(tid.equals(uti.getTicketid())) {
		            flag = 1;
		            System.out.print("Add Comment\n");
		    	 data = scan.nextLine();		 	
			 
			//Cleaning the data if unstructured or if any unwanted character is present.
		        String sanEmpID = sanitizeInput(uid);
		        String sanTicketID = sanitizeInput(tid);
		        String sanData = sanitizeInput(data);
		        
		        try{
		            PreparedStatement prepared_stmt = (PreparedStatement)conn.prepareStatement("INSERT INTO comment (Comment, CommentTime, TicketID, EmpID)VALUES(?, ?, ?,?)");
		            prepared_stmt.setString(1, sanData);
		            prepared_stmt.setTimestamp(2, sqlTime);
		            prepared_stmt.setString(3, sanTicketID);
		            prepared_stmt.setString(4, sanEmpID);

		            int exec = prepared_stmt.executeUpdate();
		            System.out.println(exec + " Comment inserted!");
		            conn.close();
		        }
		        catch(Exception e){
				    System.out.println("Error: "+e);
			    }
		      }
		    }
		      if(flag==0) {
		            System.out.print("Incorrect ID!\n");
	         }
		       scan.close();
		 }
		 
		 // Viewing Comment of a particular Ticket (EMPLOYEE SIDE)
		 public void user_viewComment() {
			 String tid;
			 Scanner scan = new Scanner(System.in);
			 System.out.print("Enter Ticket ID\n");
			 tid = scan.nextLine();
			 int flag = 0;
			 System.out.println("*******************************************************************************************************");
			 System.out.println("Comment"+"				"+"By"+"				"+"Time");
			 System.out.println("*******************************************************************************************************");
		       for(String i : usermap.keySet()){
		           userticketsinfo uti = usermap.get(i);
		           if(tid.equals(uti.getTicketid())) {
		            flag = 1;
		        try{
		        	PreparedStatement stmt=conn.prepareStatement("SELECT * FROM comment WHERE TicketID ='" + tid +"'");  
					ResultSet rs=stmt.executeQuery();
					while (rs.next()) {
						System.out.println(rs.getString("Comment")+"			"+rs.getString("EmpId")+"			"+rs.getString("CommentTime"));
						System.out.println("-------------------------------------------------------------------------------------------------------");
						}
		        }
		        catch(Exception e){
				    System.out.println("Error: "+e);
			    }
		      }
		    }
		      if(flag==0) {
		            System.out.print("Incorrect ID!\n");
	         }
		       scan.close();
		 }
		 
		 /*---------------------------------------------RETRIEVING USER DATA END---------------------------------------------*/
		 
	// Main function calling all other Functions
	public static void main(String args[]) throws Exception {
	    login obj= new login();
	    Scanner sc= new Scanner(System.in);
	    obj.login_validation();
	    
	    // Replace these functions with front-end code accordingly and remove print statements
	    if(flag1==1) 
	    {
	    obj.put_data();
	    System.out.print("**********************************************************************\n");
    	System.out.print("Enter the number of your selection\n");  
    	System.out.print("1. Show all tickets\n");  
    	System.out.print("2. Show resolved tickets\n");  
    	System.out.print("3. Show In Progress tickets\n");
    	System.out.print("4. Edit\n");
    	System.out.print("5. Search\n");
    	System.out.print("6. Add Comment\n");
    	System.out.print("7. View Comments\n");
    	System.out.print("**********************************************************************\n");
    	int n = sc.nextInt();
    	switch(n) {
    	case 1 :  obj.retrieve_all_data(); break;
    	case 2 :  obj.retrieve_resolved_data(); break;
    	case 3 :  obj.retrieve_inprogress_data(); break;
    	case 4 :  obj.edit_ticket(); obj.put_data(); break;
    	case 5 :  obj.search(); break;
    	case 6 :  obj.insertComment(); break;
    	case 7 :  obj.viewComment(); break;
    		}
    	}
	    
	    if(flag1==2) 
	    {
	    obj.user_put_data();
	    System.out.print("**********************************************************************\n");
    	System.out.print("Enter the number of your selection\n");  
    	System.out.print("1. Show my all tickets\n");  
    	System.out.print("2. Show my resolved tickets\n");  
    	System.out.print("3. Show my in-progress tickets\n");
    	System.out.print("4. Add User Comment\n");
    	System.out.print("5. View Comments\n");
    	System.out.print("**********************************************************************\n");
    	int n = sc.nextInt();
    	switch(n) {
    	case 1 :  obj.retrieve_user_all_data(); break;
    	case 2 :  obj.retrieve_user_resolved_data(); break;
    	case 3 :  obj.retrieve_user_inprogress_data(); break;
    	case 4 :  obj.user_insertComment(); break;
    	case 5 :  obj.user_viewComment(); break;
    		}
    	}
	sc.close();
  }
}

