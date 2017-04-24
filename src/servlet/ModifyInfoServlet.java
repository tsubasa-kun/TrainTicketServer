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
 * 修改信息Servlet
 * 
 * @author cookie
 * 
 */
public class ModifyInfoServlet extends HttpServlet {

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
		String userId = request.getParameter("userId");
		String realName = request.getParameter("realName");
		String idNumber = request.getParameter("idNumber");

		// 执行数据库操作
		String sql_upd1 = "UPDATE users SET real_name = '" + realName
				+ "',id_number = '" + idNumber + "' WHERE user_id = '" + userId
				+ "'";
		String sql_upd2 = "UPDATE members a,(SELECT id FROM members WHERE user_id = '"
				+ userId
				+ "' limit 1) b SET member_real_name = '"
				+ realName
				+ "',member_id_number = '" + idNumber + "' WHERE a.id = b.id";
		String sql_que = "SELECT * FROM users WHERE user_id = '" + userId + "'";
		Statement stat = null;
		ResultSet rs = null;
		UserBean userBean = new UserBean();
		userBean.setResStatus("failed");
		userBean.setResMsg("修改失败");
		Connection conn = new DBHelper().getConnect();
		try {
			stat = conn.createStatement();
			int row = stat.executeUpdate(sql_upd1);
			if (row == 1) {
				int row2 = stat.executeUpdate(sql_upd2);
				if (row2 == 1) {
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
				}
			} else {
				userBean.setResStatus("failed");
				userBean.setResMsg("修改失败");
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
