import java.sql.*;

public class Ticket {

    public static String sanitizeInput(String garbageInp)
	{
		String clean = garbageInp.replaceAll("(?i)<.*?>.*?</.*?>", "").replaceAll("(?i)<.*?>", "").replaceAll("(?i)</.*?>", "");
		return clean;
	}
	

    public void toDatabase(String userID, String issueName, String issueDesc, String watcher, String dbUrl, String dbUser, String dbPass){

        String sanitizeUserId = sanitizeInput(userID);
        String sanitizeIssueName = sanitizeInput(issueName);
        String sanitizeIssueDesc = sanitizeInput(issueDesc);
        String sanitizeWatcher = sanitizeInput(watcher);

        try{
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);

            String query="insert into tableName values(?,?,?,?)";
            con.prepareStatement(query).setString(1, sanitizeUserId);
            con.prepareStatement(query).setString(2, sanitizeIssueName);
            con.prepareStatement(query).setString(3, sanitizeIssueDesc);
            con.prepareStatement(query).setString(4, sanitizeWatcher);

            int i = con.prepareStatement(query).executeUpdate();

            System.out.println(i + "Your ticket has been stored in the DATABASE.");

            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }

    }

    public static void main(String[] args) {

        System.out.println(" ");
        
    }
    
}