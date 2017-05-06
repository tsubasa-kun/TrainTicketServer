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
 * 添加用户Servlet
 * @author cookie
 *
 */
public class AddUserServlet extends HttpServlet {

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
		String realName = URLDecoder.decode(URLDecoder.decode(request.getParameter("realName"), "UTF-8"), "UTF-8");
		String idNumber = request.getParameter("idNumber");

		// 执行数据库操作
		//查询
		String sql_que = "SELECT * FROM users WHERE account = '" + account
				+ "'";
		//添加进users
		String sql_ins = "INSERT INTO users(account, password, real_name, id_number) VALUES('"
				+ account
				+ "', '"
				+ password
				+ "', '"
				+ realName
				+ "', '"
				+ idNumber + "')";
		Statement stat = null;
		ResultSet rs = null;
		ResultBean resultBean = new ResultBean();
		resultBean.setResStatus("failed");
		resultBean.setResMsg("添加失败");
		Connection conn = new DBHelper().getConnect();
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql_que);
			if (rs.next()) {
				resultBean.setResStatus("failed");
				resultBean.setResMsg("该账户已存在");
			} else {
				int row = stat.executeUpdate(sql_ins);
				if (row == 1) {
					rs = stat.getGeneratedKeys(); // 获取结果
					if (rs.next()) {
						//添加进联系人
						String sql_ins2 = "INSERT INTO members(user_id, member_real_name, member_id_number) VALUES('"
								+ rs.getInt(1)
								+ "', '"
								+ realName
								+ "', '"
								+ idNumber + "')";
						row = stat.executeUpdate(sql_ins2);
						if (row == 1) {
							resultBean.setResStatus("success");
							resultBean.setResMsg("添加成功");
						}
					}
				} else {
					resultBean.setResStatus("failed");
					resultBean.setResMsg("添加失败");
				}
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
