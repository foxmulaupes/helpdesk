import java.sql.*;

public class Approval{
   public void User(String userId, String dbUrl, String dbUser, String dbPass)
    {
        String UID = userId;
        
        try{
	    Class.forName("com.mysql.jdbc.Driver");  

            
            Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
             
            PreparedStatement pstmt;
	    pstmt = con.prepareStatement("SELECT TicketID,TicketStatus FROM ticket WHERE UserID = ?");   
	    pstmt.setString(1, UID);
            ResultSet rs = pstmt.executeQuery(); 
            while(rs.next()){
                System.out.println(rs.getString(1)+"  "+rs.getString(2));
            }  
            con.close();  
        }catch(Exception e){ System.out.println(e);}  
    }
    public void Admin(String managerId, String employeeId, String TicketId, String decision, String dbUrl, String dbUser, String dbPass)
    {
        String MID = managerId;
        String EID = employeeId;
        String TID = TicketId;
        String App = decision;//Either Y for yes or N for no
        try{
            Class.forName("com.mysql.jdbc.Driver");  

            Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);

            if(App == "Y")
            {	
		PreparedStatement pstmt;
                pstmt = con.prepareStatement(" UPDATE ticket SET TicketApproverID = ?, TicketStatus = ?, ResolvedBy = ? WHERE TicketID = ? ");
                pstmt.setString(1, MID);
		pstmt.setString(2, App);
		pstmt.setString(3, EID);
		pstmt.setString(4, TID);
                

                int i = pstmt.executeUpdate();

                System.out.println(i + "Ticket updated and stored in the DATABASE.");
            }
            else if(App == "N")
            {   PreparedStatement pstmt;
                pstmt = con.prepareStatement(" UPDATE ticket SET TicketApproverID = ?, TicketStatus = ? WHERE TicketID = ? ");
                //As ticket is not approved therefore no one is assigned for resolution of ticket

                pstmt.setString(1, MID);
                pstmt.setString(2, App);
                pstmt.setString(3, TID);

                int i = pstmt.executeUpdate();

                System.out.println(i + "Ticket updated and stored in the DATABASE.");
            }
            else
            {
                System.out.println("Invalid input");
            }

            con.close();

        }catch(Exception e){ System.out.println(e);} 
    }
    public static void main(String[] args) {
	//Custom inputs for function testing used on a test database
	//Approval obj = new Approval();
	//String userId ="USER10";
	//String dburl = "jdbc:mysql://localhost:3306/sys";
	//String dbuser = "root";
	//String dbpass = "root";
	//String managerId = "PRESS77";
	//String TicketId = "V2220";
	//String decision1 = "Y";
	//String decision2 = "N";
	//obj.User(userId,dburl,dbuser,dbpass); 
	//obj.Admin(managerId,userId,TicketId,decision1,dburl,dbuser,dbpass);
	//obj.Admin(managerId,userId,TicketId,decision2,dburl,dbuser,dbpass);       
	System.out.println(" ");
        
    }
    
}
