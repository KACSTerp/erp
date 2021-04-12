package deployment;

public class PayAmount {
	private int p_Date;
	private double p_Amount;
	private String p_Description;
	private String p_Journal;
	
	public PayAmount() {
		// TODO Auto-generated constructor stub
	}

	public PayAmount(int p_Date, double p_Amount, String p_Description, String p_Journal) {
		super();
		this.p_Date = p_Date;
		this.p_Amount = p_Amount;
		this.p_Description = p_Description;
		this.p_Journal = p_Journal;
	}

	public int getP_Date() {
		return p_Date;
	}

	public void setP_Date(int p_Date) {
		this.p_Date = p_Date;
	}

	public double getP_Amount() {
		return p_Amount;
	}

	public void setP_Amount(double p_Amount) {
		this.p_Amount = p_Amount;
	}

	public String getP_Description() {
		return p_Description;
	}

	public void setP_Description(String p_Description) {
		this.p_Description = p_Description;
	}

	public String getP_Journal() {
		return p_Journal;
	}

	public void setP_Journal(String p_Journal) {
		this.p_Journal = p_Journal;
	}

}
