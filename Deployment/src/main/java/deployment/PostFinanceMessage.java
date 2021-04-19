package deployment;

import sharedtypes.Transaction;

public class PostFinanceMessage {
	private int p_MessageSender;
	private String p_Content;
	private Transaction p_Type;
	private double p_Amount;
	private String p_Account;
	private String p_Description;
	public PostFinanceMessage() {
	}
	public PostFinanceMessage(int p_MessageSender, String p_Content, Transaction p_Type, double p_Amount,
			String p_Account, String p_Description) {
		this.p_MessageSender = p_MessageSender;
		this.p_Content = p_Content;
		this.p_Type = p_Type;
		this.p_Amount = p_Amount;
		this.p_Account = p_Account;
		this.p_Description = p_Description;
	}
	public int getP_MessageSender() {
		return p_MessageSender;
	}
	public void setP_MessageSender(int p_MessageSender) {
		this.p_MessageSender = p_MessageSender;
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
