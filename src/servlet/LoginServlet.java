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

import com.google.gson.Gson;

import bean.UserBean;
import db.DBHelper;

/**
 * 登录Servlet
 * @author cookie
 *
 */
public class LoginServlet extends HttpServlet {

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
		String password = request.getParameter("password");

		// 执行数据库操作
		String sql_que = "SELECT * FROM users WHERE account = '" + account
				+ "' AND password = '" + password + "'";
		Statement stat = null;
		ResultSet rs = null;
		UserBean userBean = new UserBean();
		userBean.setResStatus("failed");
		userBean.setResMsg("用户名或密码错误");
		Connection conn = new DBHelper().getConnect();
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql_que);
			while (rs.next()) {
				userBean.setResStatus("success");
				userBean.setResMsg("登录成功");
				userBean.setUserId(rs.getInt("user_id") + "");
				userBean.setAccount(rs.getString("account"));
				userBean.setPassword(rs.getString("password"));
				userBean.setRealName(rs.getString("real_name"));
				userBean.setIdNumber(rs.getString("id_number"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			userBean.setResStatus("failed");
			userBean.setResMsg("登录失败");
		}

		// 通过输出流把业务逻辑的结果输出
		Gson gson = new Gson();
		String result = gson.toJson(userBean);
		out.print(result);
		out.flush();
		out.close();
	}

}
