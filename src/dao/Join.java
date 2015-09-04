package dao;

import java.sql.Date;

public class Join {
	private String userId;
	private String password;
	private String cellPhone;
	private String birth;

////////////////////////////////////////////////
	public Join(){
		
	}

	public Join(String userId, String password, String cellPhone, String birth) {
		super();
		this.userId = userId;
		this.password = password;
		this.cellPhone = cellPhone;
		this.birth = birth;
	}

////////////////////////////////////////////////

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String  getBirth() {
		return birth;
	}

	public void setBirth(String  birth) {
		this.birth = birth;
	}
	
	public String toString() {
		return ""+getUserId()+"\t  "+getPassword()+"\t  "+getCellPhone()+"\t"+getBirth();
	}

}
