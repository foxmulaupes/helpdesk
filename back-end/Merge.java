import java.sql.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;


public class Merge {
	public void loginTodatabase() throws NoSuchAlgorithmException {
		Connection con;
		PreparedStatement prepared_statement;
		Scanner input_string = new Scanner(System.in);
		String empIDTocheck = input_string.nextLine();
		String passwordTocheck = input_string.nextLine();
		boolean flag = false;
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] messageDigest = md.digest(passwordTocheck.getBytes());
		BigInteger no = new BigInteger(1, messageDigest);
		String pwdinMD5 = no.toString(16);
		while (pwdinMD5.length() < 32) {
			pwdinMD5 = "0" + pwdinMD5;
		}
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = (Connection) DriverManager
					.getConnection("jdbc:mysql://localhost:3306/helpdesk?serverTimezone=UTC","root","Fritha@17");
			prepared_statement = (PreparedStatement) con
					.prepareStatement("select * from login where EmpID=? and Password=?");
			prepared_statement.setString(1, empIDTocheck);
			prepared_statement.setString(2, passwordTocheck);
			ResultSet resultset = prepared_statement.executeQuery();
			if(resultset.next()) {
				System.out.println("Records Exist True ");
				if (resultset != null) {
					String empIDtoCompare = resultset.getString("EmpID");
					String pwtoCompare = resultset.getString("Password");
			// 'passwordTocheck' to be replaced with 'pwdinMD5' to compare with the hash value of the password
					if ((empIDTocheck.equals(empIDtoCompare)) && (passwordTocheck.equals(pwtoCompare))) {
						flag = true;
						System.out.println("Username and password exist");
					}
				}
				resultset.close();
			}
			else {
				System.out.println("Check your username or password again");
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	public static String sanitizeInput(String garbageInp) {
		String clean = garbageInp.replaceAll("(?i)<.*?>.*?</.*?>", "").replaceAll("(?i)<.*?>", "")
				.replaceAll("(?i)</.*?>", "");
		return clean;
	}

	public void ticketToDatabase(String userID, String issueName, String issueDesc, String watcher) {

		String sanitizeUserId = sanitizeInput(userID);
		String sanitizeIssueName = sanitizeInput(issueName);
		String sanitizeIssueDesc = sanitizeInput(issueDesc);
		String sanitizeWatcher = sanitizeInput(watcher);

		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/helpdesk?serverTimezone=UTC","root","Fritha@17");

			PreparedStatement prepared_statement;
			prepared_statement = (PreparedStatement) con
					.prepareStatement("insert into ticket values(?,?,?,?,?,?,?,?,?,?)");
			
			
			prepared_statement.setString(1, sanitizeUserId);
			prepared_statement.setNull(2, Types.NULL);
			prepared_statement.setNull(3, Types.NULL);
			prepared_statement.setNull(4, Types.NULL);
			prepared_statement.setNull(5, Types.NULL);
			prepared_statement.setString(6, sanitizeIssueName);
			prepared_statement.setString(7, sanitizeIssueDesc);
			prepared_statement.setNull(8, Types.NULL);
			prepared_statement.setNull(9, Types.NULL);
			prepared_statement.setString(10, sanitizeWatcher);
			
			int i = prepared_statement.executeUpdate();

			System.out.println(i + " Your ticket has been stored in the DATABASE.");

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) throws Exception {

//		Merge mer = new Merge();
//        mer.loginTodatabase();
//		mer.ticketToDatabase();
	}

}
