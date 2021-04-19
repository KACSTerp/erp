package deployment;

import java.util.Date;

import sharedtypes.Transaction;

public class SendFinanceMessage {
	private int p_Sender;
	private String p_Content;
	private Transaction p_Type;
	private double p_Amount;
	private Date p_Date;
	private String p_Account;
	private String p_Description;
	public SendFinanceMessage() {
		
	}
	public SendFinanceMessage(int p_Sender, String p_Content, Transaction p_Type, double p_Amount, Date p_Date,
			String p_Account, String p_Description) {
		this.p_Sender = p_Sender;
		this.p_Content = p_Content;
		this.p_Type = p_Type;
		this.p_Amount = p_Amount;
		this.p_Date = p_Date;
		this.p_Account = p_Account;
		this.p_Description = p_Description;
	}
	public int getP_Sender() {
		return p_Sender;
	}
	public void setP_Sender(int p_Sender) {
		this.p_Sender = p_Sender;
	}
	public String getP_Content() {
		return p_Content;
	}
	public void setP_Content(String p_Content) {
		this.p_Content = p_Content;
	}
	public Transaction getP_Type() {
		return p_Type;
	}
	public void setP_Type(Transaction p_Type) {
		this.p_Type = p_Type;
	}
	public double getP_Amount() {
		return p_Amount;
	}
	public void setP_Amount(double p_Amount) {
		this.p_Amount = p_Amount;
	}
	public Date getP_Date() {
		return p_Date;
	}
	public void setP_Date(Date p_Date) {
		this.p_Date = p_Date;
	}
	public String getP_Account() {
		return p_Account;
	}
	public void setP_Account(String p_Account) {
		this.p_Account = p_Account;
	}
	public String getP_Description() {
		return p_Description;
	}
	public void setP_Description(String p_Description) {
		this.p_Description = p_Description;
	}

}
