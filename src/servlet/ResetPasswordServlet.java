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

import bean.UserBean;

import com.google.gson.Gson;

import db.DBHelper;

/**
 * 修改密码Servlet
 * 
 * @author cookie
 * 
 */
public class ResetPasswordServlet extends HttpServlet {

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
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");

		// 执行数据库操作
		String sql_upd = "UPDATE users SET password = '" + newPassword
				+ "' WHERE account = '" + account + "' AND password = '"
				+ oldPassword + "'";
		String sql_que = "SELECT * FROM users WHERE account = '" + account
				+ "'";
		Statement stat = null;
		ResultSet rs = null;
		UserBean userBean = new UserBean();
		userBean.setResStatus("failed");
		userBean.setResMsg("修改失败");
		Connection conn = new DBHelper().getConnect();
		try {
			stat = conn.createStatement();
			int row = stat.executeUpdate(sql_upd);
			if (row == 1) {
				userBean.setResStatus("success");
				userBean.setResMsg("修改成功");
				rs = stat.executeQuery(sql_que);
				while (rs.next()) {
					userBean.setUserId(rs.getInt("user_id") + "");
					userBean.setAccount(rs.getString("account"));
					userBean.setPassword(rs.getString("password"));
					userBean.setRealName(rs.getString("real_name"));
					userBean.setIdNumber(rs.getString("id_number"));
				}
			} else {
				userBean.setResStatus("failed");
				userBean.setResMsg("原密码错误");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			userBean.setResStatus("failed");
			userBean.setResMsg("修改失败");
		}

		// 通过输出流把业务逻辑的结果输出
		Gson gson = new Gson();
		String result = gson.toJson(userBean);
		out.print(result);
		out.flush();
		out.close();
	}

}
