package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class UpdateDbApi
 */
@WebServlet("/UpdateDbApi")
public class UpdateDbApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		int role = Integer.parseInt(request.getParameter("role"));
		Map<Integer,String> data = new HashMap<>();
		try {
			// データベースとの接続の確立
			Class.forName("org.h2.Driver");
			String url = "jdbc:h2:file:C:/pleiades/workspace/data/testDB";
			Connection con = DriverManager.getConnection(url, "sa", "");
			//SQL文のテンプレート作成
			String sql =
					"SELECT * " +
					"FROM USER WHERE ROLE = ?";
			//SQLインジェクション対策
			PreparedStatement prepStmt = con.prepareStatement(sql);
			//SQL文"?"の箇所に値を埋める
			prepStmt.setInt(1, role);
			//DBに対しQuery実行。rsに実行結果を蓄積。
			ResultSet rs = prepStmt.executeQuery();
			//SQLの実行結果の処理
			while (rs.next()) {
					String role_name="";
					String user = rs.getString("USERNAME");
					Integer id = rs.getInt("ID");
					role = rs.getInt("ROLE");
					if( role == 1) role_name = "管理者";
					else if( role == 2) role_name = "編集者";
					else role_name = "寄稿者";
					String email = rs.getString("EMAIL");
					String zdata = user + ":" + email + ":" + role_name;
					data.put(id,zdata);
			}
			//DBのクローズ
			rs.close();
			prepStmt.close();
			con.close();
			} catch (ClassNotFoundException e) {
						e.printStackTrace();
			} catch (SQLException e) {
						e.printStackTrace();
			}
		//ネットストリームに書き込む
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<div id='result_box'>");
		out.println("<h2>検索結果</h2>");
		for(Map.Entry<Integer,String> entry:data.entrySet()) {
			out.print(entry.getKey()+":"+entry.getValue()+"<br>");
		}
		out.println("</div>");
	}
}