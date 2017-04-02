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
import bean.UserBean;

import com.google.gson.Gson;

import db.DBHelper;

/**
 * 查询未出行订单Servlet
 * 
 * @author cookie
 * 
 */
public class QueryOrderNowServlet extends HttpServlet {

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
		String account = request.getParameter("account");
		String realName = request.getParameter("realName");
		String idNumber = request.getParameter("idNumber");

		// 执行数据库操作
		String sql_que = "select * from users where account = '" + account
				+ "'";
		Statement stat = null;
		ResultSet rs = null;
		List<OrderBean> orders = new ArrayList<OrderBean>() ;
		Connection conn = new DBHelper().getConnect();
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql_que);
			while (rs.next()) {
				OrderBean orderBean = new OrderBean();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		// 通过输出流把业务逻辑的结果输出
		out.print(orders);
		out.flush();
		out.close();
	}

}
