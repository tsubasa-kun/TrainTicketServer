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

import bean.TicketBean;
import bean.TicketListBean;
import bean.UserBean;
import bean.UserListBean;

import com.google.gson.Gson;

import db.DBHelper;

public class QueryAllUserServlet extends HttpServlet {

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

		// 执行数据库操作
		String sql_que = "SELECT * FROM users ORDER BY user_id ASC";
		Statement stat = null;
		ResultSet rs = null;
		UserListBean userListBean = new UserListBean();
		userListBean.setResStatus("success");
		userListBean.setResMsg("");
		List<UserBean> users = new ArrayList<UserBean>();
		Connection conn = new DBHelper().getConnect();
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql_que);
			while (rs.next()) {
				UserBean userBean = new UserBean();
				userBean.setUserId(rs.getInt("user_id") + "");// 用户ID
				userBean.setAccount(rs.getString("account"));// 账号
				userBean.setPassword(rs.getString("password"));// 密码
				userBean.setRealName(rs.getString("real_name"));// 真实姓名
				userBean.setIdNumber(rs.getString("id_number"));// 出身份证号

				users.add(userBean);
				userListBean.setResStatus("success");
				userListBean.setResMsg("查询成功");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			userListBean.setResStatus("failer");
			userListBean.setResMsg("查询失败");
		}

		userListBean.setUsers(users);
		Gson gson = new Gson();
		String result = gson.toJson(userListBean);
		// 通过输出流把业务逻辑的结果输出
		out.print(result);
		out.flush();
		out.close();
	}

}
