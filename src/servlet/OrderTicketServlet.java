package servlet;

import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.MemberListBean;
import bean.OrderBean;
import bean.OrderListBean;
import bean.ResultBean;

import com.google.gson.Gson;

import db.DBHelper;

/**
 * 购买车票Servlet
 * 
 * @author cookie
 * 
 */
public class OrderTicketServlet extends HttpServlet {

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

		Gson gson = new Gson();

		// 获取参数
		String orderId = request.getParameter("orderId");
		String account = request.getParameter("account");
		MemberListBean members = gson.fromJson(request.getParameter("members"),
				MemberListBean.class);
		String trainNo = request.getParameter("trainNo");
		String fromStation = request.getParameter("fromStation");
		String startTime = request.getParameter("startTime");
		String toStation = request.getParameter("toStation");
		String endTime = request.getParameter("endTime");
		String date = request.getParameter("date");
		String seat = request.getParameter("seat");
		String carriage = request.getParameter("carriage");
		String seatNo = request.getParameter("seatNo");
		String money = request.getParameter("money");
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		
		//数量操作符
		if (id != null) {
			String theTrainNo = "";
			String theSeat = "";
			//查询车次
			String sql_que = "SELECT * FROM orders WHERE id = '" + id + "'";
			//改签把此票的status改成3，orderID改成0
			String sql_upd = "UPDATE orders SET order_id = '0', pay_status = '3' WHERE id = '" + id + "'";
			// 执行数据库操作
			Statement stat = null;
			Connection conn = new DBHelper().getConnect();
			try {
				stat = conn.createStatement();
				ResultSet rs = stat.executeQuery(sql_que); // 获取结果
				while (rs.next()) {
					theTrainNo = rs.getString("train_no");
					theSeat = rs.getString("seat");
				}
				stat.executeUpdate(sql_upd);
				
				// 车票+1
				String sql_upd2 = "UPDATE tickets SET";
				if (theSeat.equals("商务座")) {
					sql_upd2 = sql_upd2 + " swz_num = swz_num + 1";
				} else if (theSeat.equals("一等座")) {
					sql_upd2 = sql_upd2 + " zy_num = zy_num + 1";
				} else if (theSeat.equals("二等座")) {
					sql_upd2 = sql_upd2 + " ze_num = ze_num + 1";
				} else if (theSeat.equals("硬座")) {
					sql_upd2 = sql_upd2 + " yz_num = yz_num + 1";
				} else if (theSeat.equals("硬卧")) {
					sql_upd2 = sql_upd2 + " yw_num = yw_num + 1";
				} else if (theSeat.equals("无座")) {
					sql_upd2 = sql_upd2 + " wz_num = wz_num + 1";
				}
				sql_upd2 = sql_upd2 + " WHERE train_code = '" + theTrainNo + "'";
				stat.executeUpdate(sql_upd2);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		// 车票-1
		String sql_upd = "UPDATE tickets SET";
		if (seat.equals("商务座")) {
			sql_upd = sql_upd + " swz_num = swz_num - 1";
		} else if (seat.equals("一等座")) {
			sql_upd = sql_upd + " zy_num = zy_num - 1";
		} else if (seat.equals("二等座")) {
			sql_upd = sql_upd + " ze_num = ze_num - 1";
		} else if (seat.equals("硬座")) {
			sql_upd = sql_upd + " yz_num = yz_num - 1";
		} else if (seat.equals("硬卧")) {
			sql_upd = sql_upd + " yw_num = yw_num - 1";
		} else if (seat.equals("无座")) {
			sql_upd = sql_upd + " wz_num = wz_num - 1";
		}
		sql_upd = sql_upd + " WHERE train_code = '" + trainNo + "'";

		int n = 0;
		OrderListBean orderListBean = new OrderListBean();
		List<OrderBean> orders = new ArrayList<OrderBean>();
		for (int i = 0; i < members.getMembers().size(); i++) {
			String temp_seatNo = "无";
			if (!seatNo.equals("无")) {
				temp_seatNo = (Integer.parseInt(seatNo) + i) + "";
			}
			// 执行数据库操作
			String sql_ins = "INSERT INTO orders(order_id, account, real_name, train_no, from_station, start_time, to_station, end_time, date, seat, carriage, seat_no, money, type) VALUES('"
					+ orderId
					+ "', '"
					+ account
					+ "', '"
					+ members.getMembers().get(i).getMemberRealName()
					+ "', '"
					+ trainNo
					+ "', '"
					+ fromStation
					+ "', '"
					+ startTime
					+ "', '"
					+ toStation
					+ "', '"
					+ endTime
					+ "', '"
					+ date
					+ "', '"
					+ seat
					+ "', '"
					+ carriage
					+ "', '"
					+ temp_seatNo
					+ "', '" + money + "', '" + type + "')";
			Statement stat = null;
			OrderBean orderBean = new OrderBean();
			orderBean.setOrderId(orderId);
			orderBean.setAccount(account);
			orderBean.setRealName(members.getMembers().get(i).getMemberRealName());
			orderBean.setTrainNo(trainNo);
			orderBean.setFromStation(fromStation);
			orderBean.setStartTime(startTime);
			orderBean.setToStation(toStation);
			orderBean.setEndTime(endTime);
			orderBean.setDate(date);
			orderBean.setSeat(seat);
			orderBean.setCarriage(carriage);
			orderBean.setSeatNo(temp_seatNo);
			orderBean.setMoney(money);
			orderBean.setType(type);
			orderBean.setResStatus("failed");
			orderBean.setResMsg("生成订单失败");
			Connection conn = new DBHelper().getConnect();
			try {
				stat = conn.createStatement();
				int row = stat.executeUpdate(sql_ins);
				if (row == 1) {
					orderBean.setResStatus("success");
					orderBean.setResMsg("生成订单成功");
					ResultSet rs = stat.getGeneratedKeys(); // 获取结果
					if (rs.next()) {
						orderBean.setId(rs.getInt(1));// 取得ID
					}
					stat.executeUpdate(sql_upd);
				} else {
					orderBean.setResStatus("failed");
					orderBean.setResMsg("生成订单失败");
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				orderBean.setResStatus("failed");
				orderBean.setResMsg("生成订单失败");
			}
			orders.add(orderBean);
			n++;
		}
		
		if (n == members.getMembers().size()) {
			// 通过输出流把业务逻辑的结果输出
			orderListBean.setOrders(orders);
			orderListBean.setResMsg(orders.get(0).getResMsg());
			orderListBean.setResStatus(orders.get(0).getResStatus());
			String result = gson.toJson(orderListBean);
			out.print(result);
			out.flush();
			out.close();
		}
	}
}
