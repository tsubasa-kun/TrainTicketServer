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

import bean.MemberBean;
import bean.MemberListBean;
import bean.OrderBean;
import bean.OrderListBean;

import com.google.gson.Gson;

import db.DBHelper;

public class QueryMemberServlet extends HttpServlet {

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
		
		//SQL语句
		String sql_que = "SELECT * FROM members WHERE user_id = '" + userId + "'";

		// 执行数据库操作
		Statement stat = null;
		ResultSet rs = null;
		MemberListBean memberListBean = new MemberListBean();
		memberListBean.setResStatus("success");
		memberListBean.setResMsg("");
		List<MemberBean> members = new ArrayList<MemberBean>();
		Connection conn = new DBHelper().getConnect();
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql_que);
			while (rs.next()) {
				MemberBean memberBean = new MemberBean();
				memberBean.setId(rs.getInt("id"));
				memberBean.setUserId(rs.getInt("user_id"));
				memberBean.setMemberRealName(rs.getString("member_real_name"));
				memberBean.setMemberIdNumber(rs.getString("member_id_number"));
				members.add(memberBean);
				memberListBean.setResStatus("success");
				memberListBean.setResMsg("查询成功");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			memberListBean.setResStatus("failer");
			memberListBean.setResMsg("查询失败");
		}

		memberListBean.setMembers(members);
		Gson gson = new Gson();
		String result = gson.toJson(memberListBean);
		// 通过输出流把业务逻辑的结果输出
		out.print(result);
		out.flush();
		out.close();
	}

}
