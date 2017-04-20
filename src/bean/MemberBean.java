package bean;

/**
 * Created by Cookie on 2017/4/20.
 * <p>
 * description：联系人实体类
 */

public class MemberBean extends ResultBean {
	private int id;// ID
	private int userId;// 用户ID
	private String memberRealName;// 联系人真实姓名
	private String memberIdNumber;// 联系人身份证号

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getMemberRealName() {
		return memberRealName;
	}

	public void setMemberRealName(String memberRealName) {
		this.memberRealName = memberRealName;
	}

	public String getMemberIdNumber() {
		return memberIdNumber;
	}

	public void setMemberIdNumber(String memberIdNumber) {
		this.memberIdNumber = memberIdNumber;
	}
}
