import java.sql.*;
import java.util.Scanner;
public class Withdraw {
	public void changeStatus()
	{
		Connection con;
		PreparedStatement prepared_statement;
		Scanner input_string = new Scanner(System.in);
		String tktIDTocheck = input_string.nextLine();
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = (Connection) DriverManager
					.getConnection("jdbc:mysql://localhost:3306/helpdesk?serverTimezone=UTC","root","Fritha@17");
			prepared_statement = (PreparedStatement) con
					.prepareStatement("select TicketStatus,TicketID from ticket where TicketID=?");
			prepared_statement.setString(1, tktIDTocheck);
			ResultSet resultset = prepared_statement.executeQuery();
			if(resultset.next()) 
			{
				if (resultset != null) 
				{
					String status = resultset.getString("TicketStatus");
					String tktIDTocompare = resultset.getString("TicketID");
					if (status.equals("Resolved")) 
					{

		            	System.out.println( "TICKET IS ALREADY RESOLVED");
					}
				
			     else if(tktIDTocheck.equals(tktIDTocompare))
			    {  prepared_statement = (PreparedStatement) con
					.prepareStatement("update ticket set TicketStatus='Resolved' where TicketID=?");
			        prepared_statement.setString(1, tktIDTocheck);
			
			         prepared_statement.executeUpdate();
                    
            	      System.out.println( "STATUS UPDATED SUCCESSFULLY");
                     
			    }
			    
			}
		}
			 else
	             System.out.println( "INCORRECT TICKET_ID");  
			con.close();
		
	}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       Withdraw w=new Withdraw();
       w.changeStatus();
	}

}
