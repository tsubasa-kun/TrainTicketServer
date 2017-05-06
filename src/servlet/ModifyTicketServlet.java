package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ResultBean;

import com.google.gson.Gson;

import db.DBHelper;

public class ModifyTicketServlet extends HttpServlet {

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
		String trainId = request.getParameter("trainId");
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
		// 添加进members
		String sql_upd = "UPDATE tickets SET train_code = '" + trainCode
				+ "', start_date = '" + startDate + "', start_station_name = '"
				+ startStationName + "', start_time = '" + startTime
				+ "', lishi = '" + lishi + "', to_station_name = '"
				+ toStationName + "', arrive_time = '" + arriveTime
				+ "', swz_num = '" + swzNum + "', zy_num = '" + zyNum
				+ "', ze_num = '" + zeNum + "', yz_num = '" + yzNum
				+ "', yw_num = '" + ywNum + "', wz_num = '" + wzNum
				+ "', swz_money = '" + swzMoney + "', zy_money = '" + zyMoney
				+ "', ze_money = '" + zeMoney + "', yz_money = '" + yzMoney
				+ "', yw_money = '" + ywMoney + "', wz_money = '" + wzMoney
				+ "' WHERE train_id = '" + trainId + "'";
		Statement stat = null;
		ResultBean resultBean = new ResultBean();
		resultBean.setResStatus("failed");
		resultBean.setResMsg("修改失败");
		Connection conn = new DBHelper().getConnect();
		try {
			stat = conn.createStatement();
			int row = stat.executeUpdate(sql_upd);
			if (row == 1) {
				resultBean.setResStatus("success");
				resultBean.setResMsg("修改成功");
			} else {
				resultBean.setResStatus("failed");
				resultBean.setResMsg("修改失败");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			resultBean.setResStatus("failed");
			resultBean.setResMsg("修改失败");
		}

		// 通过输出流把业务逻辑的结果输出
		Gson gson = new Gson();
		String result = gson.toJson(resultBean);
		out.print(result);
		out.flush();
		out.close();
	}

}
