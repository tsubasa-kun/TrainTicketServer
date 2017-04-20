package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.OrderBean;
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

		// 获取参数
		String orderId = request.getParameter("orderId");
		String account = request.getParameter("account");
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

		// 执行数据库操作
		String sql_ins = "INSERT INTO orders(order_id, account, train_no, from_station, start_time, to_station, end_time, date, seat, carriage, seat_no, money, type) VALUES('"
				+ orderId
				+ "', '"
				+ account
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
				+ seatNo
				+ "', '"
				+ money
				+ "', '"
				+ type
				+ "')";
		Statement stat = null;
		OrderBean orderBean = new OrderBean();
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
			} else {
				orderBean.setResStatus("failed");
				orderBean.setResMsg("生成订单失败");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			orderBean.setResStatus("failed");
			orderBean.setResMsg("生成订单失败");
		}

		// 通过输出流把业务逻辑的结果输出
		Gson gson = new Gson();
		String result = gson.toJson(orderBean);
		out.print(result);
		out.flush();
		out.close();
	}
}
