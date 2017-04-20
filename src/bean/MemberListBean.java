package bean;

import java.util.List;

/**
 * Created by Cookie on 2017/4/20.
 * <p>
 * description：联系人列表实体类
 */

public class MemberListBean extends ResultBean {

    private List<MemberBean> members;

    public List<MemberBean> getMembers() {
        return members;
    }

    public void setMembers(List<MemberBean> members) {
        this.members = members;
    }
}
