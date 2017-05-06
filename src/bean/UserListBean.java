package bean;

import java.util.List;

/**
 * Created by Cookie on 2017/5/6.
 * <p>
 * description：用户列表实体类
 */

public class UserListBean extends ResultBean {
	private List<UserBean> users;

	public List<UserBean> getUsers() {
		return users;
	}

	public void setUsers(List<UserBean> users) {
		this.users = users;
	}

    
}
