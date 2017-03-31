package bean;

/**
 * 用户实体类
 * 
 * @author cookie
 * 
 */
public class UserBean extends ResultBean {
	private String userId;// 用户ID
	private String account;// 账号
	private String password;// 密码
	private String realName;// 真实姓名
	private String idNumber;// 身份证号码

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
