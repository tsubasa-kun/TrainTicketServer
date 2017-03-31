package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.ResultBean;
import db.DBHelper;

public class RegisterServlet extends HttpServlet {

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
		// �����������
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		// ��ȡ����
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String realName = request.getParameter("realName");
		String idNumber = request.getParameter("idNumber");

		// ִ�����ݿ����
		String sql_que = "select * from users where account = '" + account
				+ "'";
		String sql_ins = "insert into users(account, password, real_name, id_number) values('"
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
		Connection conn = new DBHelper().getConnect();
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql_que);
			if (rs.next()) {
				resultBean.setResStatus("failed");
				resultBean.setResMsg("���˻��Ѵ���");
			} else {
				int row = stat.executeUpdate(sql_ins);
				if (row == 1) {
					resultBean.setResStatus("success");
					resultBean.setResMsg("ע��ɹ�");
				} else {
					resultBean.setResStatus("failed");
					resultBean.setResMsg("ע��ʧ��");
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		// ͨ���������ҵ���߼��Ľ�����
		Gson gson = new Gson();
		String result = gson.toJson(resultBean);
		out.print(result);
		out.flush();
		out.close();
	}

}
