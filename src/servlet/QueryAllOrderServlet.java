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

import com.google.gson.Gson;

import db.DBHelper;

public class QueryAllOrderServlet extends HttpServlet {

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

		// 数据库语句
		String sql_que = "SELECT * FROM orders";

		// 执行数据库操作
		Statement stat = null;
		ResultSet rs = null;
		OrderListBean orderListBean = new OrderListBean();
		orderListBean.setResStatus("success");
		orderListBean.setResMsg("");
		List<OrderBean> orders = new ArrayList<OrderBean>();
		Connection conn = new DBHelper().getConnect();
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql_que);
			while (rs.next()) {
				OrderBean orderBean = new OrderBean();
				orderBean.setId(rs.getInt("id"));
				orderBean.setOrderId(rs.getString("order_id"));
				orderBean.setAccount(rs.getString("account"));
				orderBean.setRealName(rs.getString("real_name"));
				orderBean.setTrainNo(rs.getString("train_no"));
				orderBean.setFromStation(rs.getString("from_station"));
				orderBean.setStartTime(rs.getString("start_time"));
				orderBean.setToStation(rs.getString("to_station"));
				orderBean.setEndTime(rs.getString("end_time"));
				orderBean.setDate(rs.getString("date"));
				orderBean.setSeat(rs.getString("seat"));
				orderBean.setCarriage(rs.getString("carriage"));
				orderBean.setSeatNo(rs.getString("seat_no"));
				orderBean.setMoney(rs.getString("money"));
				orderBean.setType(rs.getString("type"));
				orderBean.setStatus(rs.getInt("pay_status"));
				orders.add(orderBean);
				orderListBean.setResStatus("success");
				orderListBean.setResMsg("查询成功");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			orderListBean.setResStatus("failer");
			orderListBean.setResMsg("查询失败");
		}

		orderListBean.setOrders(orders);
		Gson gson = new Gson();
		String result = gson.toJson(orderListBean);
		// 通过输出流把业务逻辑的结果输出
		out.print(result);
		out.flush();
		out.close();
	}

}
