//POJO class used by HashMap for setting and getting values for USER
public class userticketsinfo {
	private String ticketid;
	private String issue;
	private String status;
	private String assign;
	private String userid;
	private String ticketcat;
	private String issuedate;
	private String resolvedate;
	private String priority;
	
	public userticketsinfo () {}
	
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getTicketid() {
		return ticketid;
	}

	public void setTicketid(String ticketid) {
		this.ticketid = ticketid;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAssign() {
		return assign;
	}

	public void setAssign(String assign) {
		this.assign = assign;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTicketcat() {
		return ticketcat;
	}

	public void setTicketcat(String ticketcat) {
		this.ticketcat = ticketcat;
	}

	public String getIssuedate() {
		return issuedate;
	}

	public void setIssuedate(String issuedate) {
		this.issuedate = issuedate;
	}

	public String getResolvedate() {
		return resolvedate;
	}

	public void setResolvedate(String resolvedate) {
		this.resolvedate = resolvedate;
	}
	
	public userticketsinfo( String ticketid, String issue, String status,String assign, String userid, String ticketcat, String priority, String issuedate, String resolvedate){
	    this.ticketid = ticketid;
	    this.issue = issue;
	    this.status = status;
	    this.assign = assign;
	    this.userid = userid;
	    this.ticketcat = ticketcat;
	    this.priority = priority;
	    this.issuedate = issuedate;
	    this.resolvedate = resolvedate;
	}
}