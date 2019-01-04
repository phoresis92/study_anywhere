package bean;

import java.util.Date;

public class MemberBean {
	private String MEMBER_ID;
	private String MEMBER_PW;
	private String MEMBER_TEMPPASS;
	private int MEMBER_SETTEMP;
	private String MEMBER_EMAIL;
	private int MEMBER_CHECKED;
	private Date MEMBER_DATE;
	private Date MEMBER_SUSPENED;
	
	public String getMEMBER_ID() {
		return MEMBER_ID;
	}
	public void setMEMBER_ID(String mEMBER_ID) {
		MEMBER_ID = mEMBER_ID;
	}
	public String getMEMBER_PW() {
		return MEMBER_PW;
	}
	public void setMEMBER_PW(String mEMBER_PW) {
		MEMBER_PW = mEMBER_PW;
	}
	public String getMEMBER_TEMPPASS() {
		return MEMBER_TEMPPASS;
	}
	public void setMEMBER_TEMPPASS(String mEMBER_TEMPPASS) {
		MEMBER_TEMPPASS = mEMBER_TEMPPASS;
	}
	public int getMEMBER_SETTEMP() {
		return MEMBER_SETTEMP;
	}
	public void setMEMBER_SETTEMP(int mEMBER_SETTEMP) {
		MEMBER_SETTEMP = mEMBER_SETTEMP;
	}
	public String getMEMBER_EMAIL() {
		return MEMBER_EMAIL;
	}
	public void setMEMBER_EMAIL(String mEMBER_EMAIL) {
		MEMBER_EMAIL = mEMBER_EMAIL;
	}
	public int getMEMBER_CHECKED() {
		return MEMBER_CHECKED;
	}
	public void setMEMBER_CHECKED(int mEMBER_CHECKED) {
		MEMBER_CHECKED = mEMBER_CHECKED;
	}
	public Date getMEMBER_DATE() {
		return MEMBER_DATE;
	}
	public void setMEMBER_DATE(Date mEMBER_DATE) {
		MEMBER_DATE = mEMBER_DATE;
	}
	public Date getMEMBER_SUSPENED() {
		return MEMBER_SUSPENED;
	}
	public void setMEMBER_SUSPENED(Date mEMBER_SUSPENED) {
		MEMBER_SUSPENED = mEMBER_SUSPENED;
	}

}
