package deployment;

public class SendEmployeeMessages {
	private int p_LeaveRequesterID;
	private int p_Starting;
	private int p_Ending;
	private String p_Content;
	public SendEmployeeMessages(int p_LeaveRequesterID, int p_Starting, int p_Ending, String p_Content) {
		this.p_LeaveRequesterID = p_LeaveRequesterID;
		this.p_Starting = p_Starting;
		this.p_Ending = p_Ending;
		this.p_Content = p_Content;
	}
	public int getP_LeaveRequesterID() {
		return p_LeaveRequesterID;
	}
	public void setP_LeaveRequesterID(int p_LeaveRequesterID) {
		this.p_LeaveRequesterID = p_LeaveRequesterID;
	}
	public int getP_Starting() {
		return p_Starting;
	}
	public void setP_Starting(int p_Starting) {
		this.p_Starting = p_Starting;
	}
	public int getP_Ending() {
		return p_Ending;
	}
	public void setP_Ending(int p_Ending) {
		this.p_Ending = p_Ending;
	}
	public String getP_Content() {
		return p_Content;
	}
	public void setP_Content(String p_Content) {
		this.p_Content = p_Content;
	}
	
}
