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

import bean.ResultBean;

import com.google.gson.Gson;

import db.DBHelper;

/**
 * 购买车票Servlet
 * 
 * @author cookie
 * 
 */
public class BuyTicketServlet extends HttpServlet {

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
		String trainNo = request.getParameter("trainNo");
		String from = request.getParameter("from");
		String startTime = request.getParameter("startTime");
		String to = request.getParameter("to");
		String endTime = request.getParameter("endTime");
		String date = request.getParameter("date");
		String seat = request.getParameter("seat");
		String carriage = request.getParameter("carriage");
		String seatNo = request.getParameter("seatNo");
		String money = request.getParameter("money");

		// 执行数据库操作
		String sql_ins = "insert into orders(order_id, train_no, from, start_time, to, end_time, date, seat, carriage, seat_no, money) values('"
				+ orderId
				+ "', '"
				+ trainNo
				+ "', '"
				+ from
				+ "', '"
				+ startTime
				+ "', '"
				+ to
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
				+ money + "')";
		Statement stat = null;
		ResultBean resultBean = new ResultBean();
		Connection conn = new DBHelper().getConnect();
		try {
			stat = conn.createStatement();

			int row = stat.executeUpdate(sql_ins);
			if (row == 1) {
				resultBean.setResStatus("success");
				resultBean.setResMsg("支付成功");
			} else {
				resultBean.setResStatus("failed");
				resultBean.setResMsg("支付失败");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		// 通过输出流把业务逻辑的结果输出
		Gson gson = new Gson();
		String result = gson.toJson(resultBean);
		out.print(result);
		out.flush();
		out.close();
	}

}
