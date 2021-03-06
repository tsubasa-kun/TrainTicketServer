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
import bean.UserBean;

import com.google.gson.Gson;

import db.DBHelper;

/**
 * 退票Servlet
 * 
 * @author cookie
 * 
 */
public class TuiTicketServlet extends HttpServlet {

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
		String id = request.getParameter("id");

		// 执行数据库操作
		String theTrainNo = "";
		String theSeat = "";
		//查询车次
		String sql_que = "SELECT * FROM orders WHERE id = '" + id + "'";
        //退票把status改成2，orderID改成0
		String sql_upd = "UPDATE orders SET order_id = '0', pay_status = '2' WHERE id = '" + id + "'";
		Statement stat = null;
		ResultSet rs = null;
		ResultBean resultBean = new ResultBean();
		resultBean.setResStatus("failed");
		resultBean.setResMsg("退票失败");
		Connection conn = new DBHelper().getConnect();
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql_que); // 获取结果
			while (rs.next()) {
				
				theTrainNo = rs.getString("train_no");
				theSeat = rs.getString("seat");
			}
			
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
			
			int row = stat.executeUpdate(sql_upd);
			if (row == 1) {
				resultBean.setResStatus("success");
				resultBean.setResMsg("退票成功");
			} else {
				resultBean.setResStatus("failed");
				resultBean.setResMsg("退票失败");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			resultBean.setResStatus("failed");
			resultBean.setResMsg("退票失败");
		}

		// 通过输出流把业务逻辑的结果输出
		Gson gson = new Gson();
		String result = gson.toJson(resultBean);
		out.print(result);
		out.flush();
		out.close();
	}

}
