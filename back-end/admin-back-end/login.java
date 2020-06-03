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
	
	//Creating Connection 
	public login(){
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/helpdesk","root","");
			stmt = conn.createStatement();
			System.out.print("Connection Successful\n");
		}
		catch(Exception e){
			System.out.println("Error: "+e);
		}
	}
	
	//Login Validation
	public void login_validation(){
		// Manual input
		String uid = "admin"; 
		String pass = "admin";

		try {			
			// MD5 password Encryption (for testing add `pwdinMD5` value to DB)
			MessageDigest md = MessageDigest.getInstance("MD5"); 
	        byte[] messageDigest = md.digest(pass.getBytes()); 
	        BigInteger no = new BigInteger(1, messageDigest); 
	        String pwdinMD5 = no.toString(16); 
	        while (pwdinMD5.length() < 32) { 
	          pwdinMD5 = "0" + pwdinMD5; 
	        }
	        //System.out.println(pwdinMD5);
	        
			PreparedStatement stmt=conn.prepareStatement("select * from login");  
			ResultSet rs=stmt.executeQuery(); 
		    
			while (rs.next()) {
				if((rs.getString("EmpID").equals(uid)) && rs.getString("Password").equals(pwdinMD5)) {
		           	System.out.print("Admin Logged In\n");
		            flag=1;
		            flag1=flag;
		            break;
		            }
		        else if(!(rs.getString("EmpID").equals(uid) && rs.getString("Password").equals(pwdinMD5))) {
		            System.out.print("Incorrect User ID or Password");
		            flag=0;
		            break;
		            }
				}
		    }
		
		catch(Exception e) {
		       System.out.println(e);
		       }
	}
	
	
	 //Putting data into HashMap
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
					String Issuedate = rs.getString("IssueDate");
					String Resolvedate = rs.getString("ResolveDate");
				
	                t = new ticketsinfo(Ticket_ID, Issue, Status, assigned_to, Userid, Ticketcat, Issuedate, Resolvedate);
	                //Set data in the HashMap
	                map.put(Ticket_ID, t);
	            }
	        }
	        catch(Exception e) {
				 System.out.println(e);
			 }
	 }
	 
	 //Retrieving All Data from HashMap
	 public void retrieve_all_data() {
		 //Replace all print statements with the front-end display code
		 	System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------\n");
			System.out.println("User ID"+"       "+"Ticket ID"+"       "+"Issue"+"                   "+"Status"+"         "+"Assigned To"+"        "+"Ticket Cat"+"         "+"Issue Date"+"         "+"Resolve Date");
			System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------\n");
			int flag = 0;
			for(String i : map.keySet()){
	            ticketsinfo ti = map.get(i);
	            	flag = 1;
	            	System.out.println(ti.getUserid()+"          "+ti.getTicketid()+"          "+ti.getIssue()+"          "+ti.getStatus()+"          "+ti.getAssign()+"          "+ti.getTicketcat()+"          "+ti.getIssuedate()+"          "+ti.getResolvedate());
	            	System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------\n");
	            }
			if(flag==0) {
	             System.out.print("No Record Exists\n");
	             }
	 }
	 
	 //Retrieving InProgress from HashMap
	 public void retrieve_inprogress_data() {
		//Replace all print statements with the front-end display code
		 	System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------\n");
			System.out.println("User ID"+"       "+"Ticket ID"+"       "+"Issue"+"                   "+"Status"+"         "+"Assigned To"+"        "+"Ticket Cat"+"         "+"Issue Date"+"         "+"Resolve Date");
			System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------\n");
			int flag = 0;
			for(String i : map.keySet()){
	            ticketsinfo ti = map.get(i);
	            if(ti.getStatus().equals("In Progress")) {
	            	flag = 1;
	            	System.out.println(ti.getUserid()+"          "+ti.getTicketid()+"          "+ti.getIssue()+"          "+ti.getStatus()+"          "+ti.getAssign()+"          "+ti.getTicketcat()+"          "+ti.getIssuedate()+"          "+ti.getResolvedate());
	            	System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------\n");
	            }
	        }
			if(flag==0) {
	             System.out.print("No Record Exists\n");
	             }
	 }
	 
	 //Retrieving Resolved Data from HashMap
	 public void retrieve_resolved_data() {
		//Replace all print statements with the front-end display code
		 	System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------\n");
			System.out.println("User ID"+"       "+"Ticket ID"+"       "+"Issue"+"                   "+"Status"+"         "+"Assigned To"+"        "+"Ticket Cat"+"         "+"Issue Date"+"         "+"Resolve Date");
			System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------\n");
			int flag = 0;
			for(String i : map.keySet()){
	            ticketsinfo ti = map.get(i);
	            if(ti.getStatus().equals("Resolved")) {
	            	flag = 1;
	            	System.out.println(ti.getUserid()+"          "+ti.getTicketid()+"          "+ti.getIssue()+"          "+ti.getStatus()+"          "+ti.getAssign()+"          "+ti.getTicketcat()+"          "+ti.getIssuedate()+"          "+ti.getResolvedate());
	            	System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------\n");
	            }
	        }
			if(flag==0) {
	             System.out.print("No Record Exists\n");
	             }
	 }
	 
	 //Edit Ticket from HashMap and then calling put_data (is mandatory in main method to update HashMap)
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
	 
	 //Search Ticket using HashMap
	 public void search() {
		//Replace all print statements with the front-end display code
		 Scanner sc= new Scanner(System.in);
		 System.out.print("Enter Ticket ID\n");
	     String search = sc.nextLine();
	     int flag=0;

	        for(String i : map.keySet()){
	            ticketsinfo ti = map.get(i);
	            if(ti.getTicketid().equals(search)) {
	            	flag=1;
	            	System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------\n");
	    			System.out.println("User ID"+"       "+"Ticket ID"+"       "+"Issue"+"                   "+"Status"+"         "+"Assigned To"+"        "+"Ticket Cat"+"         "+"Issue Date"+"         "+"Resolve Date");
	    			System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------\n");
	    			System.out.println(ti.getUserid()+"          "+ti.getTicketid()+"          "+ti.getIssue()+"          "+ti.getStatus()+"          "+ti.getAssign()+"          "+ti.getTicketcat()+"          "+ti.getIssuedate()+"          "+ti.getResolvedate());
	            	System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------\n");
	            }
	        }
	        
	        if(flag==0) {
	             System.out.print("Incorrect Ticket ID!\n");
          }
	      sc.close();
	 }
	 
	//Delete Ticket from DB and then calling put_data (is mandatory in main method to update HashMap)
	 public void delete() {
		 // Manual Input of TID and then deleting row, replace input field with front-end code
		 	String tid;
		 	Scanner scan = new Scanner(System.in);
		 	System.out.print("Enter Ticket ID\n");
		 	tid = scan.nextLine();
		 	int flag = 0;
	        for(String i : map.keySet()){
	            ticketsinfo ti = map.get(i);
	            if(tid.equals(ti.getTicketid())) {
	             flag = 1;
	             
	    		 try{
	    			String sql = "DELETE from `ticket` where TicketID LIKE '%" + tid + "%'";
	    			stmt.execute(sql);
	 	        	System.out.println(tid+" deleted successfully\n");
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
	
	// Main function calling all other Functions
	public static void main(String args[]) throws Exception {
	    login obj= new login();
	    obj.login_validation();
	    Scanner sc= new Scanner(System.in);
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
    	System.out.print("6. Delete\n");
    	System.out.print("**********************************************************************\n");
    	int n = sc.nextInt();
       if (n == 1)
    	{obj.retrieve_all_data();}
       else if (n == 2)
    	{obj.retrieve_resolved_data();}
       else if (n == 3)
    	{obj.retrieve_inprogress_data();}
       else if (n == 4)
       	{obj.edit_ticket();
       	obj.put_data();}
       else if (n == 5)
      	{obj.search();}
       else if (n == 6)
     	{obj.delete();
     	obj.put_data();}
    }
	sc.close();
  }
}

