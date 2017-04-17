package bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cookie on 2017/4/17 0017.
 * <p>
 * 车票列表实体类
 */
public class TicketListBean extends ResultBean {
	private List<TicketBean> tickets;

	public List<TicketBean> getTickets() {
		return tickets;
	}

	public void setTickets(List<TicketBean> tickets) {
		this.tickets = tickets;
	}

}
