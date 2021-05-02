package deployment;

import java.util.Date;

public class SendEmployeeBonuses {
	private String messageName;
	private String p_BonusName;
	private Date p_Starting;
	private Date p_Ending;
	private double p_Percent;
	private double p_Amount;
	public SendEmployeeBonuses(String messageName, String p_BonusName, Date p_Starting2, Date p_Ending2,
			double p_Percent2, double p_Amount2) {
		this.messageName = messageName;
		this.p_BonusName = p_BonusName;
		this.p_Starting = p_Starting2;
		this.p_Ending = p_Ending2;
		this.p_Percent = p_Percent2;
		this.p_Amount = p_Amount2;
	}
	public String getMessageName() {
		return messageName;
	}
	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}
	public String getP_BonusName() {
		return p_BonusName;
	}
	public void setP_BonusName(String p_BonusName) {
		this.p_BonusName = p_BonusName;
	}
	public Date getP_Starting() {
		return p_Starting;
	}
	public void setP_Starting(Date p_Starting) {
		this.p_Starting = p_Starting;
	}
	public Date getP_Ending() {
		return p_Ending;
	}
	public void setP_Ending(Date p_Ending) {
		this.p_Ending = p_Ending;
	}
	public double getP_Percent() {
		return p_Percent;
	}
	public void setP_Percent(double p_Percent) {
		this.p_Percent = p_Percent;
	}
	public double getP_Amount() {
		return p_Amount;
	}
	public void setP_Amount(double p_Amount) {
		this.p_Amount = p_Amount;
	}
	
}
