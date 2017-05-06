package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
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
 * 添加车票Servlet
 * @author cookie
 *
 */
public class AddTicketServlet extends HttpServlet {

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
		String trainCode = request.getParameter("trainCode");
		String startDate = request.getParameter("startDate");
		String startStationName = URLDecoder.decode(URLDecoder.decode(
				request.getParameter("startStationName"), "UTF-8"), "UTF-8");
		String startTime = request.getParameter("startTime");
		String lishi = request.getParameter("lishi");
		String toStationName = URLDecoder.decode(URLDecoder.decode(
				request.getParameter("toStationName"), "UTF-8"), "UTF-8");
		String arriveTime = request.getParameter("arriveTime");
		String swzNum = request.getParameter("swzNum");
		String zyNum = request.getParameter("zyNum");
		String zeNum = request.getParameter("zeNum");
		String yzNum = request.getParameter("yzNum");
		String ywNum = request.getParameter("ywNum");
		String wzNum = request.getParameter("wzNum");
		String swzMoney = request.getParameter("swzMoney");
		String zyMoney = request.getParameter("zyMoney");
		String zeMoney = request.getParameter("zeMoney");
		String yzMoney = request.getParameter("yzMoney");
		String ywMoney = request.getParameter("ywMoney");
		String wzMoney = request.getParameter("wzMoney");

		// 执行数据库操作
		// 添加
		String sql_ins = "INSERT INTO tickets(train_code, start_date, start_station_name, start_time, lishi, to_station_name, arrive_time, swz_num, zy_num, ze_num, yz_num, yw_num, wz_num, swz_money, zy_money, ze_money, yz_money, yw_money, wz_money) VALUES('"
				+ trainCode
				+ "', '"
				+ startDate
				+ "', '"
				+ startStationName
				+ "', '"
				+ startTime
				+ "', '"
				+ lishi
				+ "', '"
				+ toStationName
				+ "', '"
				+ arriveTime
				+ "', '"
				+ swzNum
				+ "', '"
				+ zyNum
				+ "', '"
				+ zeNum
				+ "', '"
				+ yzNum
				+ "', '"
				+ ywNum
				+ "', '"
				+ wzNum
				+ "', '"
				+ swzMoney
				+ "', '"
				+ zyMoney
				+ "', '"
				+ zeMoney
				+ "', '"
				+ yzMoney
				+ "', '"
				+ ywMoney
				+ "', '"
				+ wzMoney + "')";
		Statement stat = null;
		ResultBean resultBean = new ResultBean();
		resultBean.setResStatus("failed");
		resultBean.setResMsg("添加失败");
		Connection conn = new DBHelper().getConnect();
		try {
			stat = conn.createStatement();
			int row = stat.executeUpdate(sql_ins);
			if (row == 1) {
				resultBean.setResStatus("success");
				resultBean.setResMsg("添加成功");
			} else {
				resultBean.setResStatus("failed");
				resultBean.setResMsg("添加失败");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			resultBean.setResStatus("failed");
			resultBean.setResMsg("添加失败");
		}

		// 通过输出流把业务逻辑的结果输出
		Gson gson = new Gson();
		String result = gson.toJson(resultBean);
		out.print(result);
		out.flush();
		out.close();
	}

}
