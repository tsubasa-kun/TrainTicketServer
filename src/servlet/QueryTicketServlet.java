package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.OrderBean;
import bean.OrderListBean;
import bean.TicketBean;
import bean.TicketListBean;

import com.google.gson.Gson;

import db.DBHelper;

/**
 * 查询车票Servlet
 * 
 * @author Administrator
 * 
 */
public class QueryTicketServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求编码
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		// 获取参数
		String startStationName = request.getParameter("startStationName");
		String toStationName = request.getParameter("toStationName");

		// 执行数据库操作
		String sql_que = "SELECT * FROM tickets WHERE start_station_name LIKE '%"
				+ startStationName.replace("东", "").replace("西", "")
						.replace("南", "").replace("北", "")
				+ "%' AND to_station_name LIKE '%"
				+ toStationName.replace("东", "").replace("西", "")
						.replace("南", "").replace("北", "")
				+ "%' ORDER BY start_time ASC";
		Statement stat = null;
		ResultSet rs = null;
		TicketListBean ticketListBean = new TicketListBean();
		ticketListBean.setResStatus("success");
		ticketListBean.setResMsg("");
		List<TicketBean> tickets = new ArrayList<TicketBean>();
		Connection conn = new DBHelper().getConnect();
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql_que);
			while (rs.next()) {
				TicketBean ticketBean = new TicketBean();
				ticketBean.setTrainId(rs.getInt("train_id") + "");// 火车ID
				ticketBean.setTrainCode(rs.getString("train_code"));// 车次
				ticketBean.setStartDate(rs.getString("start_date"));// 日期
				ticketBean.setStartStationName(rs
						.getString("start_station_name"));// 出发站
				ticketBean.setStartTime(rs.getString("start_time"));// 出发时间
				ticketBean.setLishi(rs.getString("lishi"));// 历时
				ticketBean.setToStationName(rs.getString("to_station_name"));// 目的地
				ticketBean.setArriveTime(rs.getString("arrive_time"));// 到达时间
				ticketBean.setWzNum(rs.getString("wz_num"));// 无座数量
				ticketBean.setYwNum(rs.getString("yw_num"));// 硬卧数量
				ticketBean.setYzNum(rs.getString("yz_num"));// 硬座数量
				ticketBean.setZeNum(rs.getString("ze_num"));// 二等座数量
				ticketBean.setZyNum(rs.getString("zy_num"));// 一等座数量
				ticketBean.setSwzNum(rs.getString("swz_num"));// 商务座数量
				ticketBean.setWzMoney(rs.getString("wz_money"));// 无座价格
				ticketBean.setYwMoney(rs.getString("yw_money"));// 硬卧价格
				ticketBean.setYzMoney(rs.getString("yz_money"));// 硬座价格
				ticketBean.setZeMoney(rs.getString("ze_money"));// 二等座价格
				ticketBean.setZyMoney(rs.getString("zy_money"));// 一等座价格
				ticketBean.setSwzMoney(rs.getString("swz_money"));// /商务座价格
				tickets.add(ticketBean);
				ticketListBean.setResStatus("success");
				ticketListBean.setResMsg("查询成功");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			ticketListBean.setResStatus("failer");
			ticketListBean.setResMsg("查询失败");
		}

		ticketListBean.setTickets(tickets);
		Gson gson = new Gson();
		String result = gson.toJson(ticketListBean);
		// 通过输出流把业务逻辑的结果输出
		out.print(result);
		out.flush();
		out.close();
	}

}
